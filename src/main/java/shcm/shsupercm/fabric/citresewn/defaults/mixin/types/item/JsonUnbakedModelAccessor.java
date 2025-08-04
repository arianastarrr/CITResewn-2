package shcm.shsupercm.fabric.citresewn.defaults.mixin.types.item;

import net.minecraft.client.render.model.json.JsonUnbakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.Map;

@Mixin(JsonUnbakedModel.class)
public interface JsonUnbakedModelAccessor {
    @Accessor("textureMap")
    Map<String, String> getTextureMap();
}