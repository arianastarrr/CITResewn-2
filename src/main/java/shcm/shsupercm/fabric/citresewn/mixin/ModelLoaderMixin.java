package shcm.shsupercm.fabric.citresewn.mixin;

//import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.DummyProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import shcm.shsupercm.fabric.citresewn.cit.ActiveCITs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Initializes the (re)loading of active cits in the resource manager.
 * @see ActiveCITs
 */
@Mixin(BakedModelManager.class)
public class ModelLoaderMixin {
    /**
     * @see ActiveCITs#load(ResourceManager, Profiler)
     */
    @Inject(method = "reloadModels", at = @At("HEAD"))
    private static void citresewn$loadCITs(ResourceManager resourceManager, Executor executor, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        // Use DummyProfiler instead of null to avoid crashes
        ActiveCITs.load(resourceManager, DummyProfiler.INSTANCE);
    }
}
