package tk.patsite.warmod.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import tk.patsite.warmod.common.Util.Util;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.blocks.*;

public class WarmodBlocks {
    public static final WaterCannonBlock WATER_CANNON_BLOCK = new WaterCannonBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).nonOpaque().collidable(true).hardness(4.0F).resistance(3.0F).requiresTool());
    public static final IronGeneratorBlock IRON_GENERATOR_BLOCK = new IronGeneratorBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).collidable(true).hardness(5.0F).resistance(4.5F).requiresTool());
    public static final OreBlock URANIUM_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).collidable(true).hardness(3.0F).resistance(3.0F).luminance(5).requiresTool(), UniformIntProvider.create(7, 10));
    public static final FluidBlock CRUDE_OIL = new FluidBlock(WarmodFluids.STILL_CRUDE_OIL, FabricBlockSettings.copy(Blocks.WATER));
    public static final OneWayDirtBlock ONE_WAY_DIRT_BLOCK = new OneWayDirtBlock(FabricBlockSettings.of(Material.GLASS, MapColor.DIRT_BROWN).sounds(BlockSoundGroup.GRAVEL).nonOpaque().solidBlock(Util::never).blockVision(Util::never).strength(0.3F));
    public static final FluidBlock FUEL = new FluidBlock(WarmodFluids.STILL_FUEL, FabricBlockSettings.copy(Blocks.WATER));
    public static final OreBlock SILICON_ORE_BLOCK = new OreBlock(FabricBlockSettings.of(Material.STONE).collidable(true).hardness(3.0F).resistance(3.0F).requiresTool());
    public static final MachineBlock MACHINE_BLOCK_BLOCK = new MachineBlock(FabricBlockSettings.of(Material.STONE, MapColor.IRON_GRAY).collidable(true).strength(4.0F).nonOpaque().solidBlock(Util::never).blockVision(Util::never));
    public static final OilRefineryBlock OIL_REFINERY_BLOCK = new OilRefineryBlock(FabricBlockSettings.of(Material.GLASS, MapColor.BRIGHT_RED).sounds(BlockSoundGroup.STONE).nonOpaque().solidBlock(Util::never).blockVision(Util::never).strength(3.0F));
    public static final KeycardWriterBlock KEYCARD_WRITER_BLOCK = new KeycardWriterBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).strength(4.0F).requiresTool());
    public static final KeycardReaderBlock KEYCARD_READER_BLOCK = new KeycardReaderBlock(FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).strength(4.0F).requiresTool());
    public static final CameraControlBlock CAMERA_CONTROL_BLOCK = new CameraControlBlock(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).strength(4.0F));
    public static final CameraBlock CAMERA_BLOCK = new CameraBlock(FabricBlockSettings.of(Material.METAL, MapColor.BLACK).strength(4.0F));
    public static final XpTankBlock XP_TANK_BLOCK = new XpTankBlock(FabricBlockSettings.of(Material.GLASS, MapColor.BRIGHT_RED).nonOpaque().solidBlock(Util::never).blockVision(Util::never).strength(3.0F));
    public static final SprinklerBlock SPRINKLER_BLOCK = new SprinklerBlock(FabricBlockSettings.of(Material.STONE).solidBlock(Util::never).strength(2.0F));

    public static void register() {
        Registry.register(Registry.BLOCK, Warmod.id("water_cannon"), WATER_CANNON_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("uranium_ore"), URANIUM_ORE_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("iron_generator"), IRON_GENERATOR_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("crude_oil"), CRUDE_OIL);
        Registry.register(Registry.BLOCK, Warmod.id("one_way_dirt"), ONE_WAY_DIRT_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("fuel"), FUEL);
        Registry.register(Registry.BLOCK, Warmod.id("silicon_ore"), SILICON_ORE_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("machine_block"), MACHINE_BLOCK_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("oil_refinery"), OIL_REFINERY_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("keycard_writer"), KEYCARD_WRITER_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("keycard_reader"), KEYCARD_READER_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("camera_control"), CAMERA_CONTROL_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("camera"), CAMERA_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("xp_tank"), XP_TANK_BLOCK);
        Registry.register(Registry.BLOCK, Warmod.id("sprinkler"), SPRINKLER_BLOCK);
    }
}
