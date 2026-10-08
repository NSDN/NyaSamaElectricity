package club.nsdn.nyasamaelectricity.renderer;

import club.nsdn.nyasamaelectricity.ElectricityBlockEntities;
import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import club.nsdn.nyasamaelectricity.renderer.tileblock.CableRenderer;
import club.nsdn.nyasamaelectricity.renderer.tileblock.PillarRenderer;
import club.nsdn.nyasamaelectricity.renderer.tileblock.WireRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Client-side registration of the three cable/wire/pillar renderers.
 *
 * <p>1.12.2 bound them in {@code tileblock.TileEntityModelBinder}'s constructor -- a
 * {@code LinkedHashMap<Class<? extends TileEntityBase>, TileEntitySpecialRenderer<?>>} filled with
 * {@code WireNode -> WireRenderer}, {@code CableNode -> CableRenderer},
 * {@code PillarNode -> PillarRenderer}, which {@code EventRegister.registerClient()} registered on the
 * forge bus so its {@code ModelRegistryEvent} handler could call
 * {@code ClientRegistry.bindTileEntitySpecialRenderer}. Neither half of that survives: the model pass
 * is gone (item models are JSON resources) and 1.21.1 binds a renderer to a {@code BlockEntityType}
 * through {@link EntityRenderersEvent.RegisterRenderers}, a mod-bus event.</p>
 *
 * <p>The mapping is the 1.12.2 one, keyed by the types rather than by classes: {@code wire_node} is
 * also the type of {@code catenary_node} (1.12.2 registered {@code TileEntityWireNode} once and both
 * blocks created it), so {@link WireRenderer} is bound once and covers both -- the catenary branch
 * inside it reads the block from the block state. The three endpoint types are deliberately left
 * unbound, exactly as the 1.12.2 map left them with no renderer.</p>
 *
 * <p>This class is an {@code @EventBusSubscriber} rather than a call from the mod constructor, which
 * is what the rest of this project does for client-only registration
 * ({@code nyasamaoptics.OpticsRenderers} is the same shape): the annotation routes the class to the
 * client only, so no client type is referenced from the common entry point (the 1.12.2
 * {@code @SideOnly}/{@code ClientProxy} split, expressed with the annotation the loader understands).
 * NeoForge discovers the annotated class in its automatic subscriber scan; the
 * {@code value = Dist.CLIENT} half is what keeps it off the dedicated server.</p>
 */
@EventBusSubscriber(modid = NyaSamaElectricity.MODID, value = Dist.CLIENT)
public final class ElectricityRenderers {

    private ElectricityRenderers() {
    }

    @SubscribeEvent
    static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        bind(event, ElectricityBlockEntities.WIRE_NODE.get(), WireRenderer::new);
        bind(event, ElectricityBlockEntities.CABLE_NODE.get(), CableRenderer::new);
        bind(event, ElectricityBlockEntities.PILLAR_NODE.get(), PillarRenderer::new);
    }

    /**
     * Binds a type to a renderer despite the wildcard.
     *
     * <p>{@code registerBlockEntityRenderer} is generic in the block entity type, while the electricity
     * types are held as {@code BlockEntityType<?>} (the deferred registers erase their element type),
     * so the compiler cannot infer {@code T}. Same single unchecked cast as
     * {@code nyasamaoptics.OpticsRenderers#bind}, for the same reason.</p>
     */
    @SuppressWarnings("unchecked")
    private static <T extends BlockEntity> void bind(EntityRenderersEvent.RegisterRenderers event,
                                                     BlockEntityType<?> type,
                                                     BlockEntityRendererProvider<T> provider) {
        event.registerBlockEntityRenderer((BlockEntityType<T>) type, provider);
    }
}
