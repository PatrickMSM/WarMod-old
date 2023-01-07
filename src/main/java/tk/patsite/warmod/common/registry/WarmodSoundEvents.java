package tk.patsite.warmod.common.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.patsite.warmod.common.Warmod;

public class WarmodSoundEvents {
    public static final Identifier SHORT_BEEP_SOUND_ID = Warmod.id("short_beep");
    public static final SoundEvent SHORT_BEEP_SOUND_EVENT = new SoundEvent(SHORT_BEEP_SOUND_ID);
    
    public static void register() {
        Registry.register(Registry.SOUND_EVENT, SHORT_BEEP_SOUND_ID, SHORT_BEEP_SOUND_EVENT);
    }
}
