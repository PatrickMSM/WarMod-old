package tk.patsite.warmod.common.registry;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.registry.Registry;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.fluids.CrudeOilFluid;
import tk.patsite.warmod.common.fluids.FuelFluid;

public class WarmodFluids {
    public static final FlowableFluid STILL_CRUDE_OIL = new CrudeOilFluid.Still();
    public static final FlowableFluid FLOWING_CRUDE_OIL = new CrudeOilFluid.Flowing();
    public static final FlowableFluid STILL_FUEL = new FuelFluid.Still();
    public static final FlowableFluid FLOWING_FUEL = new FuelFluid.Flowing();

    public static void register() {
        Registry.register(Registry.FLUID, Warmod.id("crude_oil"), STILL_CRUDE_OIL);
        Registry.register(Registry.FLUID, Warmod.id("flowing_crude_oil"), FLOWING_CRUDE_OIL);
        Registry.register(Registry.FLUID, Warmod.id("fuel"), STILL_FUEL);
        Registry.register(Registry.FLUID, Warmod.id("flowing_fuel"), FLOWING_FUEL);
    }
}
