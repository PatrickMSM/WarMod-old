package tk.patsite.warmod.common.items;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import tk.patsite.warmod.client.screens.SimpleAtomBombCallerScreen;
import tk.patsite.warmod.common.registry.WarmodSoundEvents;

public class SimpleAtomBombCallerItem extends Item {
    public SimpleAtomBombCallerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(world.isClient()) {
            player.playSound(WarmodSoundEvents.SHORT_BEEP_SOUND_EVENT, 1, 1);
            MinecraftClient.getInstance().setScreen(new SimpleAtomBombCallerScreen());
            MinecraftClient.getInstance().gameRenderer.getCamera().getFocusedEntity();
        }
        return TypedActionResult.success(player.getStackInHand(hand), false);
    }
}
