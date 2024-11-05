package dev.thezexquex.menushops.shop.gui.item;

import dev.thezexquex.menushops.message.Messenger;
import dev.thezexquex.menushops.util.MiniComponent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.configurate.NodePath;
import xyz.xenondevs.inventoryaccess.component.AdventureComponentWrapper;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

public class PrevPageItem extends PageItem {
    private final ItemStack displayItem;
    private final Messenger messenger;

    public PrevPageItem(ItemStack displayItem, Messenger messenger) {
        super(false);
        this.displayItem = displayItem;
        this.messenger = messenger;
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
        var builder = new ItemBuilder(displayItem);
        builder.setDisplayName(new AdventureComponentWrapper(messenger.component(
                        NodePath.path("gui", "item", "prev-page", "display-name",
                                gui.hasPreviousPage() ? "has-prev" : "no-prev"),
                        Placeholder.parsed("next-page", String.valueOf(gui.getCurrentPage() + 2)),
                        Placeholder.parsed("prev-page", String.valueOf(gui.getCurrentPage())),
                        Placeholder.parsed("curr-page", String.valueOf(gui.getCurrentPage() + 1)),
                        Placeholder.parsed("max-page", String.valueOf(gui.getPageAmount()))
                )))
                .addLoreLines(messenger.componentList(
                                        NodePath.path("gui", "item", "prev-page", "lore",
                                                gui.hasPreviousPage() ? "has-prev" : "no-prev"),
                                        Placeholder.parsed("next-page", String.valueOf(gui.getCurrentPage() + 2)),
                                        Placeholder.parsed("prev-page", String.valueOf(gui.getCurrentPage())),
                                        Placeholder.parsed("curr-page", String.valueOf(gui.getCurrentPage() + 1)),
                                        Placeholder.parsed("max-page", String.valueOf(gui.getPageAmount()))
                                )
                                .stream()
                                .map(AdventureComponentWrapper::new)
                                .toArray(AdventureComponentWrapper[]::new)
                );

        return builder;
    }
}