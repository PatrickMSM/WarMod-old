package tk.patsite.warmod.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.entities.CameraEntity;
import tk.patsite.warmod.common.entities.FastMinecartEntity;
import tk.patsite.warmod.common.entities.SimpleAtomBombEntity;
import tk.patsite.warmod.common.entities.WaterDropEntity;

public class WarmodEntities {
    public static final EntityType<FastMinecartEntity> FAST_MINECART_ENTITY = FabricEntityTypeBuilder.<FastMinecartEntity>create(SpawnGroup.MISC, (FastMinecartEntity::new)).dimensions(EntityDimensions.fixed(0.98f, 0.7f)).trackRangeChunks(8).build();
    public static final EntityType<SimpleAtomBombEntity> SIMPLE_ATOM_BOMB_ENTITY = FabricEntityTypeBuilder.<SimpleAtomBombEntity>create(SpawnGroup.MISC, (SimpleAtomBombEntity::new)).dimensions(EntityDimensions.fixed(1.0f, 2.0f)).trackRangeChunks(8).build();
    public static final EntityType<CameraEntity> CAMERA_ENTITY = FabricEntityTypeBuilder.<CameraEntity>create(SpawnGroup.MISC, (CameraEntity::new)).dimensions(EntityDimensions.fixed(0.1f, 0.1f)).trackRangeChunks(8).build();
    public static final EntityType<WaterDropEntity> WATER_DROP_ENTITY = FabricEntityTypeBuilder.<WaterDropEntity>create(SpawnGroup.MISC, (WaterDropEntity::new)).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeChunks(4).build();


    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, Warmod.id("fastminecart"), FAST_MINECART_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, Warmod.id("simple_atom_bomb"), SIMPLE_ATOM_BOMB_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, Warmod.id("camera"), CAMERA_ENTITY);
        Registry.register(Registry.ENTITY_TYPE, Warmod.id("water_drop"), WATER_DROP_ENTITY);
    }
}
