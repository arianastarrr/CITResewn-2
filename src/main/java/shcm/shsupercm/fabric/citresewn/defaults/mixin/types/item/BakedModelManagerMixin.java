package shcm.shsupercm.fabric.citresewn.defaults.mixin.types.item;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.util.ModelIdentifier;
import shcm.shsupercm.fabric.citresewn.defaults.cit.builtin.types.TypeItem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BakedModelManager.class)
public class BakedModelManagerMixin implements TypeItem.BakedModelManagerMixinAccess {
    private BakedModel citresewn$forcedMojankModel = null;

    @Inject(method = "getModel", cancellable = true, at =
    @At("HEAD"))
    private void citresewn$getCITMojankModel(ModelIdentifier id, CallbackInfoReturnable<BakedModel> cir) {
        if (citresewn$forcedMojankModel != null) {
            cir.setReturnValue(citresewn$forcedMojankModel);
            citresewn$forcedMojankModel = null;
        }
    }

    @Override
    public void citresewn$forceMojankModel(BakedModel model) {
        this.citresewn$forcedMojankModel = model;
    }
}
