package tk.patsite.warmod.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.blocks.entites.CameraControlBlockEntity;
import tk.patsite.warmod.common.blocks.entites.KeycardReaderBlockEntity;
import tk.patsite.warmod.common.blocks.entites.OilRefineryBlockEntity;
import tk.patsite.warmod.common.blocks.entites.XpTankBlockEntity;

public class WarmodBlockEntities {
    public static final BlockEntityType<OilRefineryBlockEntity> OIL_REFINERY_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(OilRefineryBlockEntity::new, WarmodBlocks.OIL_REFINERY_BLOCK).build();
    public static final BlockEntityType<KeycardReaderBlockEntity> KEYCARD_READER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(KeycardReaderBlockEntity::new, WarmodBlocks.KEYCARD_READER_BLOCK).build();
    public static final BlockEntityType<CameraControlBlockEntity> CAMERA_CONTROL_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(CameraControlBlockEntity::new, WarmodBlocks.CAMERA_CONTROL_BLOCK).build();
    public static final BlockEntityType<XpTankBlockEntity> XP_TANK_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(XpTankBlockEntity::new, WarmodBlocks.XP_TANK_BLOCK).build();
    
    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Warmod.id("oil_refinery"), OIL_REFINERY_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Warmod.id("keycard_reader"), KEYCARD_READER_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Warmod.id("camera_control"), CAMERA_CONTROL_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, Warmod.id("xp_tank"), XP_TANK_BLOCK_ENTITY);
    }
}
