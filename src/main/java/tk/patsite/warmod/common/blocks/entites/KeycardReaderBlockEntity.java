package tk.patsite.warmod.common.blocks.entites;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import tk.patsite.warmod.common.registry.WarmodBlockEntities;

import java.util.UUID;

public class KeycardReaderBlockEntity extends BlockEntity {
    private UUID associatedWrittenKeycardUuid = null;

    public KeycardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(WarmodBlockEntities.KEYCARD_READER_BLOCK_ENTITY, pos, state);
    }

    public boolean readOrWriteUUID(UUID uuid) {
        if(associatedWrittenKeycardUuid != null) {
            return associatedWrittenKeycardUuid.equals(uuid);
        } else {
            this.associatedWrittenKeycardUuid = uuid;
            markDirty();
            return true;
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        if(associatedWrittenKeycardUuid != null) {
            tag.putUuid("associatedWrittenKeycardUUID", associatedWrittenKeycardUuid);
        }

        super.writeNbt(tag);
    }
    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);

        if(tag.containsUuid("associatedWrittenKeycardUUID")) {
            associatedWrittenKeycardUuid = tag.getUuid("associatedWrittenKeycardUUID");
        }
    }
}
