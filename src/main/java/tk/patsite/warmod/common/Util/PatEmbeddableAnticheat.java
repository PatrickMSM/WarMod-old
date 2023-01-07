package tk.patsite.warmod.common.Util;

import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.BannedPlayerEntry;
import net.minecraft.util.Identifier;

import java.util.Date;
import java.util.List;

public final class PatEmbeddableAnticheat {
    private static final Identifier ANTICHEAT_CHANNEL = new Identifier("patembeddableanticheat", "main");
    private static final String BAN_SOURCE = null;
    private static final String BAN_MESSAGE = "You were banned for hacking. Contact the server owner for more information.";
    private static final boolean CLIENT_DISABLED = true;
    private static final boolean SERVER_DISABLED = true;
    private static final List<String> HACKS_LIST = List.of("meteor", "wurst", "xray", "ares", "freecam", "seedfinder", "seedcracker");

    private static boolean clientHasCheats = false;

    public static void initAll() {
        initClient();
        initServer();
    }

    private static void initClient() {
        if(CLIENT_DISABLED)
            return;

        checkCheats();
        ClientLoginConnectionEvents.INIT.register((handler, client)->{
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBoolean(clientHasCheats);
            ClientPlayNetworking.send(ANTICHEAT_CHANNEL, buf);
        });
    }

    private static void initServer() {
        if(SERVER_DISABLED)
            return;

        ServerPlayNetworking.registerGlobalReceiver(ANTICHEAT_CHANNEL, (server, player, handler, buf, responseSender) -> {
            if(buf.readBoolean()) {
                server.getPlayerManager().getUserBanList().add(new BannedPlayerEntry(player.getGameProfile(), new Date(), BAN_SOURCE , null, BAN_MESSAGE));
            }
        });
    }

    private static void checkCheats() {
        if(clientHasCheats)
            return;
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            for(String hack : HACKS_LIST) {
                if(mod.getMetadata().getName().contains(hack)) {
                    clientHasCheats = true;
                    return;
                }
            }
        }
    }
}

