package tk.patsite.warmod.common.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.world.WorldView;
import tk.patsite.warmod.common.registry.WarmodBlocks;
import tk.patsite.warmod.common.registry.WarmodFluids;
import tk.patsite.warmod.common.registry.WarmodItems;

public abstract class CrudeOilFluid extends WarModFluid {
    @Override
    public Fluid getStill() {
        return WarmodFluids.STILL_CRUDE_OIL;
    }

    @Override
    public Fluid getFlowing() {
        return WarmodFluids.FLOWING_CRUDE_OIL;
    }

    @Override
    public Item getBucketItem() {
        return WarmodItems.CRUDE_OIL_BUCKET;
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 20;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return WarmodBlocks.CRUDE_OIL.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends CrudeOilFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends CrudeOilFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
