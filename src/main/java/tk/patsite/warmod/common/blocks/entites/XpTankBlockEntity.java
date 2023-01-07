package tk.patsite.warmod.common.blocks.entites;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import tk.patsite.warmod.common.registry.WarmodBlockEntities;

public class XpTankBlockEntity extends BlockEntity {
    private static final int MAX_XP_LEVEL = 256;

    private int xpLevel = 0;

    public XpTankBlockEntity(BlockPos pos, BlockState state) {
        super(WarmodBlockEntities.XP_TANK_BLOCK_ENTITY, pos, state);
    }

    public boolean canAdd() {
        return xpLevel <= MAX_XP_LEVEL;
    }
    public boolean canRemove() {
        return xpLevel > 0;
    }

    public void addXp() {
        xpLevel++;
        markDirty();
        BlockState state = world.getBlockState(pos);
        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
    }

    public void removeXp() {
        xpLevel--;
        markDirty();
        BlockState state = world.getBlockState(pos);
        world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
    }

    public String getLevelAsString() {
        return String.valueOf(xpLevel);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("XpLevel", xpLevel);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        xpLevel = nbt.getInt("XpLevel");
        super.readNbt(nbt);
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
