package tk.patsite.warmod.common.Util;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import tk.patsite.warmod.common.Warmod;

import java.util.List;

public final class Util {
    //private static final double[] PIXEL_LUT = new double[]{0.0D, 0.0625D, 0.125D, 0.1875D, 0.25D, 0.3125D, 0.375D, 0.4375D, 0.5D};

    public static void waterInBox(World world, Box box) {
        // Damage fire entities
        List<LivingEntity> livingEntities  = world.getEntitiesByClass(LivingEntity.class, box, LivingEntity::hurtByWater);
        if(!livingEntities.isEmpty()) {
            livingEntities.forEach((livingEntity) -> {
                livingEntity.damage(Warmod.WATER_DROP_DAMAGE_SOURCE, 20.0F);
            });
        }

        // Water axolotls
        List<AxolotlEntity> axolotlEntities = world.getNonSpectatingEntities(AxolotlEntity.class, box);
        if(!axolotlEntities.isEmpty()) {
            axolotlEntities.forEach((axolotlEntity) -> {
                axolotlEntity.setAir(axolotlEntity.getMaxAir());
            });
        }

        // Blocks
        BlockPos.Mutable pos = new BlockPos.Mutable();
        for(int x = (int) box.minX; x <= box.maxX; x++){
            for(int y = (int) box.minY; y <= box.maxY; y++){
                for(int z = (int) box.minZ; z <= box.maxZ; z++){
                    pos.set(x, y, z);

                    BlockState blockState = world.getBlockState(pos);

                    // Extinguish
                    if (blockState.isIn(BlockTags.FIRE)) {
                        world.removeBlock(pos, false);
                    } else if (AbstractCandleBlock.isLitCandle(blockState)) {
                        AbstractCandleBlock.extinguish(null, blockState, world, pos);
                    } else if (CampfireBlock.isLitCampfire(blockState)) {
                        world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, pos, 0);
                        CampfireBlock.extinguish(null, world, pos, blockState);
                        world.setBlockState(pos, blockState.with(CampfireBlock.LIT, false));
                    }

                    // Water farmland
                    if(blockState.isOf(Blocks.FARMLAND)) {
                        world.setBlockState(pos, blockState.with(FarmlandBlock.MOISTURE, FarmlandBlock.MAX_MOISTURE));
                    }
                }
            }
        }
    }

    public static Vec3d centerOnFace(Direction face, BlockPos pos) {
        // https://discord.com/channels/507304429255393322/507982478276034570/1002882197365211168
        return Vec3d.ofCenter(pos).add(Vec3d.of(face.getVector()).multiply(0.5));

    }

    public static void addItemToInventory(Inventory inventory, ItemStack itemStack) {
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if(stack.isEmpty()) {
                inventory.setStack(i, itemStack.copy());
                return;
            }
            if(stack.isOf(itemStack.getItem()) && stack.getCount() + itemStack.getCount() <= stack.getMaxCount() && stack.getCount() + itemStack.getCount() <= inventory.getMaxCountPerStack()) {
                stack.setCount(stack.getCount() + itemStack.getCount());
                return;
            }
        }
    }

    public static void sphericalStrongExplosion(World world, BlockPos startingPos, int radius, int damageRadius, float damageAmount, DamageSource source, Entity explodingEntity) {
        // Make an explosion
        circle(world, startingPos, radius, Blocks.AIR.getDefaultState(), radius <= 50);

        // Damage radius
        Box damageBox = new Box(startingPos.add(-damageRadius, -damageRadius, -damageRadius), startingPos.add(damageRadius, damageRadius, damageRadius));

        world.getOtherEntities(explodingEntity, damageBox).forEach((entity) -> entity.damage(source, damageAmount));
    }

/**
 * <H3>Makes a circle in the given world at the given position</H3>
 */
public static void circle(World world, BlockPos startingPos, int radius, BlockState replaceBlock, boolean fast) {
    BlockPos min = startingPos.add(-radius, -radius, -radius);
    BlockPos max = startingPos.add(radius, radius, radius);

    final int startingX = startingPos.getX(), startingY = startingPos.getY(), startingZ = startingPos.getZ();
    final int r2 = radius*radius;

    // optimizations:
    // https://discord.com/channels/507304429255393322/507982478276034570/995647798546542602

    BlockPos.Mutable pos = new BlockPos.Mutable();

    if(fast) {
        // https://discord.com/channels/507304429255393322/507982478276034570/993954470314655844
        // https://discord.com/channels/507304429255393322/507982478276034570/993963208975061002
        for (int x = -radius; x < radius; x++) {
            int height = (int) Math.sqrt(r2 - x * x);
            for (int y = -height; y < height; y++) {
                int width = (int) Math.sqrt(height * height - y * y);
                for (int z = -width; z < width; z++) {
                    pos.setX(x + startingX);
                    pos.setY(y + startingY);
                    pos.setZ(z + startingZ);
                    if(world.getBlockState(pos).getBlock().getBlastResistance() <= 1200)
                        world.setBlockState(pos, replaceBlock);
                }
            }
        }
    } else {
        // https://discord.com/channels/507304429255393322/507982478276034570/993951567139061770
        // https://discord.com/channels/507304429255393322/507982478276034570/993896239395516598
        for (int x = min.getX(); x <= max.getX(); x++) {
            for (int y = min.getY(); y <= max.getY(); y++) {
                for (int z = min.getZ(); z <= max.getZ(); z++) {
                    pos.setX(x);
                    pos.setY(y);
                    pos.setZ(z);
                    if((startingX-x)*(startingX-x)+(startingY-y)*(startingY-y)+(startingZ-z)*(startingZ-z) <= r2) {
                        if(world.getBlockState(pos).getBlock().getBlastResistance() <= 1200)
                            world.setBlockState(pos, replaceBlock);
                    }
                }
            }
        }
    }
    }

    public static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
}
