package club.nsdn.nyasamaelectricity.creativetab;

import club.nsdn.nyasamaelectricity.ElectricityBlocks;
import club.nsdn.nyasamaelectricity.NyaSamaElectricity;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * The module's creative tab, ported from the 1.12.2 {@code CreativeTabLoader} (24 lines).
 *
 * <p>1.12.2 built a {@code CreativeTabs} anonymous subclass inside a constructor that was called from
 * {@code CommonProxy.preInit}, and every block joined it through {@code setCreativeTab}. In 1.21.1 the
 * tab is a registry object, so this class becomes a static holder: a
 * {@code DeferredRegister<CreativeModeTab>} plus the tab itself, registered from
 * {@code event.EventRegister#registerCommon} alongside the block registers.</p>
 *
 * <p>Two 1.12.2 details are preserved on purpose:</p>
 * <ul>
 *   <li>the tab's unlocalised name {@code tabNyaSamaElectricity}, hence the translation key
 *       {@code itemGroup.tabNyaSamaElectricity} -- the same key the existing {@code en_us}/{@code zh_cn}
 *       language files use for 1.12.2's {@code itemGroup.tabNyaSamaElectricity};</li>
 *   <li>the icon is the {@code nse_logo} block, which 1.12.2 held in {@code BlockLoader.logo} and its
 *       tab asked for by {@code itemBlocks.get(BlockLoader.logo).getDefaultInstance()}.</li>
 * </ul>
 *
 * <p>What replaces {@code setCreativeTab}: the tab enumerates its items itself, so every item the
 * module registers through {@code ElectricityBlocks.ITEMS} is shown. That is the same set 1.12.2 put in
 * the tab -- the 31 block items -- without a per-block call.</p>
 */
public class CreativeTabLoader {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NyaSamaElectricity.MODID);

    /** 1.12.2 {@code tabNyaSamaElectricity}. */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB =
            TABS.register("tab_nyasamaelectricity", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tabNyaSamaElectricity"))
                    .icon(() -> new ItemStack(ElectricityBlocks.LOGO.get()))
                    .displayItems((parameters, output) -> {
                        for (DeferredHolder<Item, ? extends Item> item : ElectricityBlocks.ITEMS.getEntries()) {
                            output.accept(item.get());
                        }
                    })
                    .build());

    private CreativeTabLoader() {
    }

    public static void register(IEventBus modBus) {
        TABS.register(modBus);
    }
}
