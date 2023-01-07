package tk.patsite.warmod.common.registry;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import tk.patsite.warmod.common.Warmod;
import tk.patsite.warmod.common.entities.SimpleAtomBombEntity;

public class WarmodNetworking {
    public static final Identifier SIMPLE_ATOM_BOMB_CALLER_CHANNEL = Warmod.id("simple_atom_bomb_caller_channel");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(SIMPLE_ATOM_BOMB_CALLER_CHANNEL, (server, player, handler, buf, responseSender) -> {
            double x = buf.readDouble();
            double z = buf.readDouble();

            // validate
            if(!player.getMainHandStack().isOf(WarmodItems.SIMPLE_ATOM_BOMB_CALLER_ITEM) || player.getMainHandStack().isEmpty())
                return;

            // summon atom bomb
            SimpleAtomBombEntity bomb = new SimpleAtomBombEntity(player.getWorld(), x, z);

            server.execute(() -> {
                player.getWorld().spawnEntity(bomb);

                // decrement
                player.getMainHandStack().decrement(1);
            });
        });
    }
}
