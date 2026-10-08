package club.nsdn.nyasamaelectricity.renderer.tileblock;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

/**
 * The block-atlas sprites the three block entity renderers draw with.
 *
 * <p>Each identifier is verbatim from the 1.12.2 renderer that asked for it, where the lookup was
 * {@code Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("nyasamaelectricity:block/x")}:
 * the wire/cable/pillar textures are <b>atlas sprites</b>, not standalone textures. That is why
 * {@link #sprite} goes through the block atlas instead of {@code RenderType.entityCutoutNoCull(...)}
 * with a {@code textures/block/x.png} path -- the geometry samples the sprite's sub-rectangle of the
 * atlas ({@code getU0/getU1/getV0/getV1} in {@code Wire#draw}), so the render type that draws it must
 * have that same atlas bound. {@code RenderType.solid()} has, and it is also the layer all three
 * 1.12.2 node blocks declared through {@code getBlockLayer()}.</p>
 *
 * <p>The 1.12.2 identifiers ended in the file name without extension; the 1.21.1 sprite id keeps that
 * form ({@code nyasamaelectricity:block/cable} addresses
 * {@code assets/nyasamaelectricity/textures/block/cable.png}).</p>
 */
public final class DeviceTextures {

    /** 1.12.2 {@code CableRenderer}: {@code "nyasamaelectricity:block/cable"}. */
    public static final ResourceLocation CABLE = blocks("cable");
    /** 1.12.2 {@code WireRenderer}: {@code "nyasamaelectricity:block/wire"}. */
    public static final ResourceLocation WIRE = blocks("wire");
    /** 1.12.2 {@code PillarRenderer}: {@code "nyasamaelectricity:block/pillar"}. */
    public static final ResourceLocation PILLAR = blocks("pillar");

    private DeviceTextures() {
    }

    private static ResourceLocation blocks(String name) {
        return ResourceLocation.fromNamespaceAndPath(NyaSamaElectricity.MODID, "block/" + name);
    }

    /**
     * The block atlas sprite of an id -- the 1.21.1 spelling of 1.12.2's
     * {@code getTextureMapBlocks().getAtlasSprite(id)} (documented in
     * {@code docs/09-NEOFORGE-API-CHEATSHEET.md} §8).
     *
     * <p>The atlas is named {@link InventoryMenu#BLOCK_ATLAS}: it holds the same value as
     * {@code TextureAtlas.LOCATION_BLOCKS}, which is {@code @Deprecated} in 21.1.256, and the project
     * already reads it this way ({@code opticzCraft}'s {@code ThrowRenderer}). Using it keeps this
     * file free of deprecation notes.</p>
     */
    public static TextureAtlasSprite sprite(ResourceLocation id) {
        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(id);
    }
}
