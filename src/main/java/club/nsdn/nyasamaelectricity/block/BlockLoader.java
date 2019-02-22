package club.nsdn.nyasamaelectricity.block;

import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import club.nsdn.nyasamaelectricity.tileblock.WireEndpoint;
import club.nsdn.nyasamaelectricity.tileblock.WireNode;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by drzzm32 on 2019.2.17.
 */
public class BlockLoader {

    private static BlockLoader instance;
    public static BlockLoader instance() {
        if (instance == null) instance = new BlockLoader();
        return instance;
    }

    public static LinkedList<Block> blocks;
    public static LinkedHashMap<Block, Item> itemBlocks;
    public static Block logo;

    public static WireNode wireNode, catenaryNode;

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        NyaSamaElectricity.logger.info("registering Blocks");
        event.getRegistry().registerAll(blocks.toArray(new Block[0]));
    }

    @SubscribeEvent
    public void registerItemBlocks(RegistryEvent.Register<Item> event) {
        NyaSamaElectricity.logger.info("registering ItemBlocks");
        for (Block b : blocks) {
            String regName = b.getUnlocalizedName().toLowerCase();
            if (b.getRegistryName() != null)
                regName = b.getRegistryName().toString().split(":")[1];
            itemBlocks.put(b, new ItemBlock(b).setRegistryName(NyaSamaElectricity.MODID, regName));
        }
        event.getRegistry().registerAll(itemBlocks.values().toArray(new Item[0]));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerItemBlockModels(ModelRegistryEvent event) {
        NyaSamaElectricity.logger.info("registering ItemBlock Models (Block's Icon)");
        for (Item i : itemBlocks.values()) {
            String regName = i.getUnlocalizedName().toLowerCase();
            if (i.getRegistryName() != null)
                regName = i.getRegistryName().toString();
            ModelLoader.setCustomModelResourceLocation(i, 0,
                    new ModelResourceLocation(regName, "inventory")
            );
        }
    }

    public BlockLoader() {
        blocks = new LinkedList<>();
        itemBlocks = new LinkedHashMap<>();

        blocks.add(new BlockSign());
        blocks.add(new BlockNSDNLogo());
        logo = new BlockLogo();
        blocks.add(logo);

        blocks.add(new BlockBigPillar());

        blocks.add(new BlockCatenary("CatenaryLong", "catenary_long", 0.25, 1.5, 1));
        blocks.add(new BlockCatenary("CatenaryShort", "catenary_short", 0.25, 1.5, 1));
        blocks.add(new BlockCatenary("CatenaryInsulator", "catenary_insulator", 0.5, 1, 1));
        blocks.add(new BlockCatenary("CatenaryInsulatorRev", "catenary_insulator_rev", 0.5, 1, 1));
        blocks.add(new BlockCatenary("CatenaryH", "catenary_h", 0.25, 1, 0.375));
        blocks.add(new BlockCatenary("CatenaryOldBase", "catenary_old_base", 0.25, 1, 0.5));
        blocks.add(new BlockCatenary("CatenaryOldBody", "catenary_old_body", 0.25, 1, 0.5));
        blocks.add(new BlockCatenary("CatenaryOldHead", "catenary_old_head", 0.25, 1, 0.5));

        blocks.add(new BlockHVSign("HVDangerA", "hv_danger_a"));
        blocks.add(new BlockHVSign("HVDangerB", "hv_danger_b"));

        blocks.add(new BlockInsulator("InsulatorBig", "insulator_big", 1, 1));
        blocks.add(new BlockInsulator("InsulatorMid", "insulator_mid", 0.75, 1));
        blocks.add(new BlockInsulator("InsulatorSmall", "insulator_small", 0.5, 1));

        blocks.add(new BlockShelf("QuadHead", "quad_head", 1, 1));
        blocks.add(new BlockShelf("QuadShelf", "quad_shelf", 1, 1));
        blocks.add(new BlockShelf("QuadSmall", "quad_small", 0.5, 1));
        blocks.add(new BlockTriShelf("QuadTriConv", "quad_tri_conv"));
        blocks.add(new BlockTriShelf("TriHead", "tri_head"));
        blocks.add(new BlockTriShelf("TriShelf", "tri_shelf"));

        blocks.add(new WireEndpoint("WireEndpoint", "wire_endpoint", 0.25, 0.25, 0.25));
        blocks.add(wireNode = new WireNode("WireNode", "wire_node", 0.25, 0.25, 0.25));
        blocks.add(new WireEndpoint("CatenaryEndpoint", "catenary_endpoint", 0.25, 1, 0.25));
        blocks.add(catenaryNode = new WireNode("CatenaryNode", "catenary_node", 0.25, 1, 0.25));
    }

}