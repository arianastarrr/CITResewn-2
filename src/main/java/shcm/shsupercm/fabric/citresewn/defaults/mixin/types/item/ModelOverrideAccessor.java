package shcm.shsupercm.fabric.citresewn.defaults.mixin.types.item;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.render.model.json.ModelOverride;
import net.minecraft.client.render.model.json.ModelOverrideList;

@Mixin(ModelOverride.class) {
public interface ModelOverride;
    @Accessor("conditions");
    Map<String, String> getTextureMap();
}


