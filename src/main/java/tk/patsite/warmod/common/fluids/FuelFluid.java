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

public abstract class FuelFluid extends WarModFluid {
    @Override
    public Fluid getStill() {
        return WarmodFluids.STILL_FUEL;
    }

    @Override
    public Fluid getFlowing() {
        return WarmodFluids.FLOWING_FUEL;
    }

    @Override
    public Item getBucketItem() {
        return WarmodItems.FUEL_BUCKET;
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 10;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return WarmodBlocks.FUEL.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends FuelFluid {
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

    public static class Still extends FuelFluid {
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
