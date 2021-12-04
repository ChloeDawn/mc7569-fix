package dev.sapphic.mc7569;

import net.minecraft.commands.CommandSource;
import net.minecraft.server.rcon.RconConsoleSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RconConsoleSource.class)
abstract class RconConsoleSourceMixin implements CommandSource {
  @Shadow @Final private StringBuffer buffer;

  @Inject(method = "sendMessage(Lnet/minecraft/network/chat/Component;Ljava/util/UUID;)V", at = @At("HEAD"))
  private void appendNewLineCharacter(final CallbackInfo ci) {
    if (!this.buffer.isEmpty()) {
      this.buffer.append(System.lineSeparator());
    }
  }
}
