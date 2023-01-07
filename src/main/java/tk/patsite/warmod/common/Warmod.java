package tk.patsite.warmod.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.patsite.warmod.common.Util.PatEmbeddableAnticheat;
import tk.patsite.warmod.common.recipe.KeycardCloningRecipe;
import tk.patsite.warmod.common.registry.*;
import tk.patsite.warmod.common.screenhandlers.OilRefineryScreenHandler;

public final class Warmod implements ModInitializer {

	public static final String MODID = "warmod";
	public static String VERSION;
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final boolean IS_DEV_ENV = FabricLoader.getInstance().isDevelopmentEnvironment();
	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}

	// MISC
	public static final DamageSource ATOM_BOMB_DAMAGE_SOURCE = new DamageSource("atomBomb"){}.setExplosive();
	public static final DamageSource WATER_DROP_DAMAGE_SOURCE = new DamageSource("waterDrop"){};
	public static final ScreenHandlerType<OilRefineryScreenHandler> OIL_REFINERY_SCREEN_HANDLER = new ScreenHandlerType<>(OilRefineryScreenHandler::new);
	public static final int OIL_REFINERY_MAX_COOK_TIME = 200;
	public static final String WRITTEN_KEYCARD_UUID = "WrittenKeycardUUID";
	public static final Identifier MONOCHROME_SHADER = new Identifier(MODID, "shaders/post/monochrome.json");
	public static final RecipeSerializer<KeycardCloningRecipe> KEYCARD_CLONING_RECIPE_SERIALIZER = new SpecialRecipeSerializer<>(KeycardCloningRecipe::new);

	// TODO: implement ideas
	// TODO: give items with no recipes a recipe

	// TODO: finish implementing camera
	// TODO: locked door, with locked door block, has to be connected with connector, locked door is strong, only opens when locked door block is powered
	@Override
	public void onInitialize() {
		VERSION = FabricLoader.getInstance().getModContainer(MODID).map((modContainer -> modContainer.getMetadata().getVersion().getFriendlyString())).get();
		PatEmbeddableAnticheat.initAll();

		// Registry
		WarmodBlockEntities.register();
		WarmodBlocks.register();
		WarmodEntities.register();
		WarmodFluids.register();
		WarmodItems.register();
		WarmodNetworking.register();
		WarmodSoundEvents.register();
		WarmodWorldgen.register();

		// MISC
		FuelRegistry.INSTANCE.add(WarmodItems.FUEL_BUCKET, 20000); // same as lava
		FuelRegistry.INSTANCE.add(Items.LAVA_BUCKET, 10000); // halve lava cook
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MODID, "crafting_special_keycardcloning"), KEYCARD_CLONING_RECIPE_SERIALIZER);

		// SCREEN HANDLERS
		Registry.register(Registry.SCREEN_HANDLER, new Identifier(MODID, "oil_refinery"), OIL_REFINERY_SCREEN_HANDLER);
	}
}
