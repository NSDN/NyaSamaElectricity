package club.nsdn.nyasamaelectricity;

import club.nsdn.nyasamaelectricity.block.BlockBigPillar;
import club.nsdn.nyasamaelectricity.block.BlockCatenary;
import club.nsdn.nyasamaelectricity.block.BlockHVSign;
import club.nsdn.nyasamaelectricity.block.BlockInsulator;
import club.nsdn.nyasamaelectricity.block.BlockLogo;
import club.nsdn.nyasamaelectricity.block.BlockNSDNLogo;
import club.nsdn.nyasamaelectricity.block.BlockShelf;
import club.nsdn.nyasamaelectricity.block.BlockSign;
import club.nsdn.nyasamaelectricity.block.BlockTriShelf;
import club.nsdn.nyasamaelectricity.tileblock.CableEndpoint;
import club.nsdn.nyasamaelectricity.tileblock.CableNode;
import club.nsdn.nyasamaelectricity.tileblock.PillarEndpoint;
import club.nsdn.nyasamaelectricity.tileblock.PillarNode;
import club.nsdn.nyasamaelectricity.tileblock.WireEndpoint;
import club.nsdn.nyasamaelectricity.tileblock.WireNode;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registration for {@code nyasamaelectricity} blocks and their items.
 *
 * <p>Replaces the 1.12.2 {@code block.BlockLoader} (112 lines) wholesale. Everything that class did
 * through Forge's pre-1.13 machinery is either gone or lives somewhere else in 1.21.1:</p>
 * <ul>
 *   <li>{@code RegistryEvent.Register<Block>}/{@code <Item>} plus the hand-built
 *       {@code LinkedList<Block>}/{@code LinkedHashMap<Block, Item>} become the two
 *       {@code DeferredRegister}s below; the registry name is given at registration instead of being
 *       baked into the block constructor with {@code setRegistryName};</li>
 *   <li>{@code ModelRegistryEvent} + {@code ModelLoader.setCustomModelResourceLocation} (the item-icon
 *       pass) has no counterpart at all -- item models are JSON resources in 1.21.1;</li>
 *   <li>{@code setCreativeTab} becomes membership of the module's own tab
 *       ({@code creativetab.CreativeTabLoader});</li>
 *   <li>{@code setUnlocalizedName} becomes a translation key looked up from the registry name, which is
 *       why the {@code lang} files are keyed by block id now.</li>
 * </ul>
 *
 * <p>The 1.12.2 table is reproduced entry for entry, in the same order, with the same ids: three glass
 * panels, the big pillar, the eight catenary variants, the two HV signs, the three insulators, the three
 * quad shelves, the three tri shelves, then the six cable-network devices -- 31 blocks in all. Two
 * static fields of the old loader survive as deferred holders because other code referred to them:
 * {@link #LOGO} (the creative-tab icon) and the wire node pair behind {@link #wireNode()} /
 * {@link #catenaryNode()} (the deferred renderer needs to tell the catenary node apart from the wire
 * node, exactly as the 1.12.2 {@code WireRenderer} did).</p>
 *
 * <p>Block-type registration: 1.20.5+ requires every block subclass with a <b>custom</b> codec to be
 * registered in {@code BuiltInRegistries.BLOCK_TYPE}. Only the six classes below have one -- the three
 * glass panels inherit {@code TransparentBlock}'s codec and the six devices inherit {@code Block}'s, so
 * they need no entry (the same rule the ported {@code nyasamabuilding} and {@code nyasamaoptics}
 * follow). {@code BlockCatenary} covers eight registry entries with a single codec, so it is registered
 * once.</p>
 */
public final class ElectricityBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NyaSamaElectricity.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NyaSamaElectricity.MODID);

    /** Every custom block class needs its codec here (1.20.5+ requirement). */
    public static final DeferredRegister<MapCodec<? extends Block>> BLOCK_TYPES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_TYPE, NyaSamaElectricity.MODID);

    /** 1.12.2 {@code BlockLoader.logo}: also the creative-tab icon. */
    public static DeferredBlock<BlockLogo> LOGO;

    /*
     * The six device blocks. Held in fields (rather than only in locals) because
     * ElectricityBlockEntities has to name them as the valid blocks of their types, and because
     * 1.12.2's BlockLoader exposed wireNode/catenaryNode to the renderer -- see wireNode() below.
     */
    static DeferredBlock<WireEndpoint> WIRE_ENDPOINT;
    static DeferredBlock<WireNode> WIRE_NODE;
    static DeferredBlock<WireEndpoint> CATENARY_ENDPOINT;
    static DeferredBlock<WireNode> CATENARY_NODE;
    static DeferredBlock<CableEndpoint> CABLE_ENDPOINT;
    static DeferredBlock<CableNode> CABLE_NODE;
    static DeferredBlock<PillarEndpoint> PILLAR_ENDPOINT;
    static DeferredBlock<PillarNode> PILLAR_NODE;

    private ElectricityBlocks() {
    }

    public static void register(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        BLOCK_TYPES.register(modBus);
    }

    static {
        BLOCK_TYPES.register("big_pillar", () -> BlockBigPillar.CODEC);
        BLOCK_TYPES.register("catenary", () -> BlockCatenary.CODEC);
        BLOCK_TYPES.register("hv_sign", () -> BlockHVSign.CODEC);
        BLOCK_TYPES.register("insulator", () -> BlockInsulator.CODEC);
        BLOCK_TYPES.register("shelf", () -> BlockShelf.CODEC);
        BLOCK_TYPES.register("tri_shelf", () -> BlockTriShelf.CODEC);

        // ---- 1.12.2 BlockLoader order, top to bottom ----

        // Glass panels: Material.GLASS, hardness 2, resistance 10, light level 1 (=15), glass sound.
        DeferredBlock<BlockSign> sign = BLOCKS.register("nse_sign", () -> new BlockSign(glassProperties()));
        ITEMS.registerSimpleBlockItem(sign);
        DeferredBlock<BlockNSDNLogo> nsdnLogo = BLOCKS.register("nse_nsdn", () -> new BlockNSDNLogo(glassProperties()));
        ITEMS.registerSimpleBlockItem(nsdnLogo);
        LOGO = BLOCKS.register("nse_logo", () -> new BlockLogo(glassProperties()));
        ITEMS.registerSimpleBlockItem(LOGO);

        DeferredBlock<BlockBigPillar> bigPillar = BLOCKS.register("big_pillar",
                () -> new BlockBigPillar(ironProperties()));
        ITEMS.registerSimpleBlockItem(bigPillar);

        // name, id, x, y, z -- verbatim from BlockLoader.
        Object[][] catenaries = new Object[][] {
                { "catenary_long", 0.25, 1.5, 1.0 },
                { "catenary_short", 0.25, 1.5, 1.0 },
                { "catenary_insulator", 0.5, 1.0, 1.0 },
                { "catenary_insulator_rev", 0.5, 1.0, 1.0 },
                { "catenary_h", 0.25, 1.0, 0.375 },
                { "catenary_old_base", 0.25, 1.0, 0.5 },
                { "catenary_old_body", 0.25, 1.0, 0.5 },
                { "catenary_old_head", 0.25, 1.0, 0.5 },
        };
        for (Object[] info : catenaries) {
            String id = (String) info[0];
            double x = (Double) info[1], y = (Double) info[2], z = (Double) info[3];
            DeferredBlock<BlockCatenary> catenary = BLOCKS.register(id,
                    () -> new BlockCatenary(ironProperties(), x, y, z));
            ITEMS.registerSimpleBlockItem(catenary);
        }

        DeferredBlock<BlockHVSign> hvDangerA = BLOCKS.register("hv_danger_a",
                () -> new BlockHVSign(ironProperties()));
        ITEMS.registerSimpleBlockItem(hvDangerA);
        DeferredBlock<BlockHVSign> hvDangerB = BLOCKS.register("hv_danger_b",
                () -> new BlockHVSign(ironProperties()));
        ITEMS.registerSimpleBlockItem(hvDangerB);

        // name, id, d, h -- verbatim from BlockLoader.
        Object[][] insulators = new Object[][] {
                { "insulator_big", 1.0, 1.0 },
                { "insulator_mid", 0.75, 1.0 },
                { "insulator_small", 0.5, 1.0 },
        };
        for (Object[] info : insulators) {
            String id = (String) info[0];
            double d = (Double) info[1], h = (Double) info[2];
            DeferredBlock<BlockInsulator> insulator = BLOCKS.register(id,
                    () -> new BlockInsulator(insulatorProperties(), d, h));
            ITEMS.registerSimpleBlockItem(insulator);
        }

        Object[][] shelves = new Object[][] {
                { "quad_head", 1.0, 1.0 },
                { "quad_shelf", 1.0, 1.0 },
                { "quad_small", 0.5, 1.0 },
        };
        for (Object[] info : shelves) {
            String id = (String) info[0];
            double d = (Double) info[1], h = (Double) info[2];
            DeferredBlock<BlockShelf> shelf = BLOCKS.register(id,
                    () -> new BlockShelf(ironProperties(), d, h));
            ITEMS.registerSimpleBlockItem(shelf);
        }

        for (String id : new String[] { "quad_tri_conv", "tri_head", "tri_shelf" }) {
            DeferredBlock<BlockTriShelf> triShelf = BLOCKS.register(id,
                    () -> new BlockTriShelf(ironProperties()));
            ITEMS.registerSimpleBlockItem(triShelf);
        }

        // Wire / catenary endpoints and nodes: the box sizes come straight from BlockLoader. The
        // catenary pair shares the wire block entities' types, exactly as 1.12.2 registered one tile
        // entity per class, so both pass the same type supplier.
        WIRE_ENDPOINT = BLOCKS.register("wire_endpoint",
                () -> new WireEndpoint(deviceProperties(), ElectricityBlockEntities::wireEndpointType,
                        0.25, 0.25, 0.25));
        ITEMS.registerSimpleBlockItem(WIRE_ENDPOINT);
        WIRE_NODE = BLOCKS.register("wire_node",
                () -> new WireNode(deviceProperties(), ElectricityBlockEntities::wireNodeType,
                        0.25, 0.25, 0.25));
        ITEMS.registerSimpleBlockItem(WIRE_NODE);
        CATENARY_ENDPOINT = BLOCKS.register("catenary_endpoint",
                () -> new WireEndpoint(deviceProperties(), ElectricityBlockEntities::wireEndpointType,
                        0.25, 1.0, 0.25));
        ITEMS.registerSimpleBlockItem(CATENARY_ENDPOINT);
        CATENARY_NODE = BLOCKS.register("catenary_node",
                () -> new WireNode(deviceProperties(), ElectricityBlockEntities::wireNodeType,
                        0.25, 1.0, 0.25));
        ITEMS.registerSimpleBlockItem(CATENARY_NODE);

        CABLE_ENDPOINT = BLOCKS.register("cable_endpoint",
                () -> new CableEndpoint(deviceProperties(), ElectricityBlockEntities::cableEndpointType));
        ITEMS.registerSimpleBlockItem(CABLE_ENDPOINT);
        CABLE_NODE = BLOCKS.register("cable_node",
                () -> new CableNode(deviceProperties(), ElectricityBlockEntities::cableNodeType));
        ITEMS.registerSimpleBlockItem(CABLE_NODE);

        // The steel pillar pair is a genuine full cube in 1.12.2 (isOpaqueCube/isFullCube both true),
        // so unlike every other device it must NOT be noOcclusion().
        PILLAR_ENDPOINT = BLOCKS.register("pillar_endpoint",
                () -> new PillarEndpoint(solidDeviceProperties(), ElectricityBlockEntities::pillarEndpointType));
        ITEMS.registerSimpleBlockItem(PILLAR_ENDPOINT);
        PILLAR_NODE = BLOCKS.register("pillar_node",
                () -> new PillarNode(solidDeviceProperties(), ElectricityBlockEntities::pillarNodeType));
        ITEMS.registerSimpleBlockItem(PILLAR_NODE);
    }

    /** The wire node / catenary node instances, for the deferred renderer (1.12.2 {@code BlockLoader.wireNode}). */
    public static WireNode wireNode() {
        return WIRE_NODE == null ? null : WIRE_NODE.get();
    }

    /** The catenary node instance, for the deferred renderer (1.12.2 {@code BlockLoader.catenaryNode}). */
    public static WireNode catenaryNode() {
        return CATENARY_NODE == null ? null : CATENARY_NODE.get();
    }

    /*
     * Fresh Properties per block on purpose: Properties is mutable, so handing one shared instance to
     * thirty blocks would let a later tweak leak into every sibling (the same reason nyasamaoptics
     * builds its properties in a method). One method per 1.12.2 material/sound combination:
     *
     *   glassProperties()      Material.GLASS + SoundType.GLASS + setLightLevel(1) + setLightOpacity(0)
     *   ironProperties()       Material.IRON  + SoundType.METAL
     *   insulatorProperties()  Material.GLASS + SoundType.GLASS, no light
     *   deviceProperties()     DeviceBase(Material.ROCK) + setSoundType(METAL) + setLightOpacity(0)
     *   solidDeviceProperties() like deviceProperties() but opaque (isOpaqueCube/isFullCube were true)
     *
     * setHardness(2.0F) + setResistance(blockHardness * 5.0F) == strength(2.0F, 10.0F) in every case.
     */

    private static BlockBehaviour.Properties glassProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.NONE)
                .sound(SoundType.GLASS)
                .strength(2.0F, 10.0F)
                .lightLevel(state -> 15)
                .noOcclusion();
    }

    private static BlockBehaviour.Properties ironProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .sound(SoundType.METAL)
                .strength(2.0F, 10.0F)
                .noOcclusion();
    }

    private static BlockBehaviour.Properties insulatorProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.NONE)
                .sound(SoundType.GLASS)
                .strength(2.0F, 10.0F)
                .noOcclusion();
    }

    private static BlockBehaviour.Properties deviceProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .sound(SoundType.METAL)
                .strength(2.0F, 10.0F)
                .noOcclusion();
    }

    private static BlockBehaviour.Properties solidDeviceProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .sound(SoundType.METAL)
                .strength(2.0F, 10.0F);
    }
}
