package tk.patsite.warmod.common.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.items.*;

public class WarmodItems {
    public static final BlockItem WATER_CANNON_ITEM = new BlockItem(WarmodBlocks.WATER_CANNON_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE));
    public static final IronGeneratorItem IRON_GENERATOR_ITEM = new IronGeneratorItem(WarmodBlocks.IRON_GENERATOR_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE));
    public static final FastMinecartItem FAST_MINECART_ITEM = new FastMinecartItem(new FabricItemSettings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item WOODEN_COG_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item IRON_COG_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item DIAMOND_COG_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON));
    public static final BucketItem CRUDE_OIL_BUCKET = new BucketItem(WarmodFluids.STILL_CRUDE_OIL, new FabricItemSettings().recipeRemainder(Items.BUCKET).group(ItemGroup.MISC).maxCount(1));
    public static final BucketItem FUEL_BUCKET = new BucketItem(WarmodFluids.STILL_FUEL, new FabricItemSettings().recipeRemainder(Items.BUCKET).group(ItemGroup.MISC).maxCount(1));
    public static final BlockItem URANIUM_ORE_ITEM = new BlockItem(WarmodBlocks.URANIUM_ORE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item URANIUM_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item ENRICHED_URANIUM_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON));
    public static final Item CELL_PHONE_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
    public static final SimpleAtomBombCallerItem SIMPLE_ATOM_BOMB_CALLER_ITEM = new SimpleAtomBombCallerItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
    public static final Item SIMPLE_ATOM_BOMB_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
    public static final BlockItem ONE_WAY_DIRT_ITEM = new BlockItem(WarmodBlocks.ONE_WAY_DIRT_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem SILICON_ORE_ITEM = new BlockItem(WarmodBlocks.SILICON_ORE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SILICON_INGOT_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON));
    public static final BlockItem MACHINE_BLOCK_ITEM = new BlockItem(WarmodBlocks.MACHINE_BLOCK_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item PRINTED_CIRCUIT_BOARD_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final Item CPU_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final NuclearBatteryItem NUCLEAR_BATTERY_ITEM = new NuclearBatteryItem(new FabricItemSettings().group(ItemGroup.MISC).maxDamage(5000));//.maxCount(1));
    public static final BlockItem OIL_REFINERY_ITEM = new BlockItem(WarmodBlocks.OIL_REFINERY_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item PLASTIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS));
    public static final	BlockItem KEYCARD_WRITER_ITEM = new BlockItem(WarmodBlocks.KEYCARD_WRITER_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE));
    public static final BlockItem KEYCARD_READER_ITEM = new BlockItem(WarmodBlocks.KEYCARD_READER_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE));
    public static final Item EMPTY_KEYCARD_ITEM = new Item(new FabricItemSettings().group(ItemGroup.REDSTONE).maxCount(1));
    public static final Item WRITTEN_KEYCARD_ITEM = new Item(new FabricItemSettings().maxCount(1));
    public static final ConnectorItem CONNECTOR_ITEM = new ConnectorItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
    public static final BlockItem CAMERA_CONTROL_ITEM = new BlockItem(WarmodBlocks.CAMERA_CONTROL_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE));
    public static final BlockItem CAMERA_ITEM = new BlockItem(WarmodBlocks.CAMERA_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE));
    public static final BlockItem XP_TANK_ITEM = new BlockItem(WarmodBlocks.XP_TANK_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem SPRINKLER_ITEM = new BlockItem(WarmodBlocks.SPRINKLER_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE));

    public static void register() {
        Registry.register(Registry.ITEM, Warmod.id("water_cannon"), WATER_CANNON_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("iron_generator"), IRON_GENERATOR_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("fastminecart"), FAST_MINECART_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("wooden_cog"), WOODEN_COG_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("iron_cog"), IRON_COG_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("diamond_cog"), DIAMOND_COG_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("crude_oil_bucket"), CRUDE_OIL_BUCKET);
        Registry.register(Registry.ITEM, Warmod.id("fuel_bucket"), FUEL_BUCKET);
        Registry.register(Registry.ITEM, Warmod.id("uranium_ore"), URANIUM_ORE_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("uranium"), URANIUM_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("enriched_uranium"), ENRICHED_URANIUM_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("cell_phone"), CELL_PHONE_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("simple_atom_bomb_caller"), SIMPLE_ATOM_BOMB_CALLER_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("simple_atom_bomb"), SIMPLE_ATOM_BOMB_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("one_way_dirt"), ONE_WAY_DIRT_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("silicon_ore"), SILICON_ORE_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("silicon_ingot"), SILICON_INGOT_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("machine_block"), MACHINE_BLOCK_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("printed_circuit_board"), PRINTED_CIRCUIT_BOARD_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("cpu"), CPU_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("nuclear_battery"), NUCLEAR_BATTERY_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("oil_refinery"), OIL_REFINERY_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("plastic"), PLASTIC_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("keycard_writer"), KEYCARD_WRITER_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("keycard_reader"), KEYCARD_READER_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("empty_keycard"), EMPTY_KEYCARD_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("written_keycard"), WRITTEN_KEYCARD_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("connector"), CONNECTOR_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("camera_control"), CAMERA_CONTROL_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("camera"), CAMERA_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("xp_tank"), XP_TANK_ITEM);
        Registry.register(Registry.ITEM, Warmod.id("sprinkler"), SPRINKLER_ITEM);
    }
}
