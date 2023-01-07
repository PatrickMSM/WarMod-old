package tk.patsite.warmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tk.patsite.warmod.common.Util.ConnectableBlock;
import tk.patsite.warmod.common.Util.ConnectableBlockEntity;
import tk.patsite.warmod.common.Util.Util;
import tk.patsite.warmod.common.blocks.entites.CameraControlBlockEntity;
import tk.patsite.warmod.common.entities.CameraEntity;

import java.util.List;

public class CameraControlBlock extends Block implements BlockEntityProvider, ConnectableBlock {
    public CameraControlBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CameraControlBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ConnectableBlockEntity be = (ConnectableBlockEntity) world.getBlockEntity(pos);

        if(world.isClient())
            return ActionResult.SUCCESS;

        if(be.isConnected()) {
            BlockState cameraState = world.getBlockState(be.getConnectedBlockPos());
            if (cameraState != null) {
                // Check if there is a camera in the block in front of the camera
                Box box = new Box(be.getConnectedBlockPos().offset(cameraState.get(CameraBlock.FACING)));
                List<CameraEntity> cameras = world.getNonSpectatingEntities(CameraEntity.class, box);
                if(!cameras.isEmpty()) {
                    cameras.forEach(CameraEntity::kill);
                }

                Vec3d newCameraPos = Util.centerOnFace(cameraState.get(CameraBlock.FACING), be.getConnectedBlockPos());
                CameraEntity newCamera = new CameraEntity(world, newCameraPos.getX(), newCameraPos.getY(), newCameraPos.getZ());
                newCamera.setYaw(cameraState.get(CameraBlock.FACING).asRotation());
                world.spawnEntity(newCamera);

                Vec3d playerPos = player.getPos();
                ((ServerPlayerEntity) player).setCameraEntity(newCamera);
                //player.requestTeleport(playerPos.getX(), playerPos.getY(), playerPos.getZ()); // Hacky fix to prevent teleporting to camera


                MinecraftClient.getInstance().tick();

                return ActionResult.SUCCESS;
            } else {
                be.disconnect();
            }
        }
        return ActionResult.FAIL;
    }
}
