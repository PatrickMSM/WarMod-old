package tk.patsite.warmod.common.blocks.entites;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tk.patsite.warmod.common.Util.ConnectableBlock;
import tk.patsite.warmod.common.Util.ConnectableBlockEntity;
import tk.patsite.warmod.common.registry.WarmodBlockEntities;

public class CameraControlBlockEntity extends BlockEntity implements ConnectableBlockEntity {
    private BlockPos connectedBlockPos;

    public CameraControlBlockEntity(BlockPos pos, BlockState state) {
        super(WarmodBlockEntities.CAMERA_CONTROL_BLOCK_ENTITY, pos, state);
    }

    @Override
    public boolean isParent() {
        return true;
    }

    @Override
    public boolean tryConnectWith(BlockPos pos, World world) {
        if(world.getBlockState(pos).getBlock() instanceof ConnectableBlock) {
            if(world.getBlockEntity(pos) instanceof ConnectableBlockEntity blockEntity) {
                blockEntity.connect(getPos());
            }
            this.connect(pos);
            return true;
        }

        return false;
    }

    @Override
    public boolean isConnected() {
        return connectedBlockPos != null;
    }

    @Override
    public void disconnect() {
        connectedBlockPos = null;
        markDirty();
    }

    @Override
    public BlockPos getConnectedBlockPos() {
        return connectedBlockPos;
    }

    @Override
    public void connect(BlockPos pos) {
        this.connectedBlockPos = pos;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        if(connectedBlockPos != null) {
            nbt.putLong("connectedBlockPos", connectedBlockPos.asLong());
        }
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if(nbt.contains("connectedBlockPos")) {
            connectedBlockPos = BlockPos.fromLong(nbt.getLong("connectedBlockPos"));
        }
        super.readNbt(nbt);
    }
}
