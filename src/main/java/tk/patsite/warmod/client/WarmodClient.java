package tk.patsite.warmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import tk.patsite.warmod.client.blockentityrenderers.OilRefineryBlockEntityRenderer;
import tk.patsite.warmod.client.blockentityrenderers.XpTankBlockEntityRenderer;
import tk.patsite.warmod.client.models.SimpleAtomBombEntityModel;
import tk.patsite.warmod.client.renderers.CameraEntityRenderer;
import tk.patsite.warmod.client.renderers.FastMinecartEntityRenderer;
import tk.patsite.warmod.client.renderers.SimpleAtomBombEntityRenderer;
import tk.patsite.warmod.client.renderers.WaterDropEntityRenderer;
import tk.patsite.warmod.client.screens.OilRefineryScreen;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.registry.WarmodBlockEntities;
import tk.patsite.warmod.common.registry.WarmodBlocks;
import tk.patsite.warmod.common.registry.WarmodEntities;
import tk.patsite.warmod.common.registry.WarmodFluids;

@Environment(EnvType.CLIENT)
public class WarmodClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_SIMPLE_ATOM_BOMB_LAYER = new EntityModelLayer(new Identifier(Warmod.MODID, "simple_atom_bomb"), "main");

    public static RenderLayer CIRCLE_LAYER;

    @Override
    public void onInitializeClient() {
        RenderLayer.MultiPhaseParameters multiPhaseParameters = RenderLayer.MultiPhaseParameters.builder().shader(RenderLayer.POSITION_COLOR_LIGHTMAP_SHADER).transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY).lightmap(RenderLayer.ENABLE_LIGHTMAP).cull(RenderLayer.DISABLE_CULLING).build(false);
        CIRCLE_LAYER = RenderLayer.of("water_drop", VertexFormats.POSITION_COLOR_LIGHT, VertexFormat.DrawMode.TRIANGLES, 256, false, true, multiPhaseParameters);

        FluidRenderHandlerRegistry.INSTANCE.register(WarmodFluids.STILL_CRUDE_OIL, WarmodFluids.FLOWING_CRUDE_OIL, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0x272000
        ));
        FluidRenderHandlerRegistry.INSTANCE.register(WarmodFluids.STILL_FUEL, WarmodFluids.FLOWING_FUEL, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0x594e00
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), WarmodFluids.STILL_FUEL, WarmodFluids.FLOWING_FUEL);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), WarmodFluids.STILL_CRUDE_OIL, WarmodFluids.FLOWING_CRUDE_OIL);
        BlockRenderLayerMap.INSTANCE.putBlock(WarmodBlocks.ONE_WAY_DIRT_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WarmodBlocks.MACHINE_BLOCK_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WarmodBlocks.OIL_REFINERY_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WarmodBlocks.XP_TANK_BLOCK, RenderLayer.getCutout());

        EntityRendererRegistry.register(WarmodEntities.CAMERA_ENTITY, CameraEntityRenderer::new);
        EntityRendererRegistry.register(WarmodEntities.FAST_MINECART_ENTITY, FastMinecartEntityRenderer::new);
        EntityRendererRegistry.register(WarmodEntities.SIMPLE_ATOM_BOMB_ENTITY, SimpleAtomBombEntityRenderer::new);
        EntityRendererRegistry.register(WarmodEntities.WATER_DROP_ENTITY, WaterDropEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_SIMPLE_ATOM_BOMB_LAYER, SimpleAtomBombEntityModel::getTexturedModelData);

        HandledScreens.register(Warmod.OIL_REFINERY_SCREEN_HANDLER, OilRefineryScreen::new);

        BlockEntityRendererRegistry.register(WarmodBlockEntities.OIL_REFINERY_BLOCK_ENTITY, OilRefineryBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(WarmodBlockEntities.XP_TANK_BLOCK_ENTITY, XpTankBlockEntityRenderer::new);
    }
}
