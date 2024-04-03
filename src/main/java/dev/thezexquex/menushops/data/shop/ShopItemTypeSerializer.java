package dev.thezexquex.menushops.data.shop;

import dev.thezexquex.menushops.shop.ShopItem;
import dev.thezexquex.menushops.shop.value.Value;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.Arrays;

public class ShopItemTypeSerializer implements TypeSerializer<ShopItem> {
    @Override
    public ShopItem deserialize(Type type, ConfigurationNode node) throws SerializationException {
        var itemStack = ItemStack.deserializeBytes(node.node("item").getString().getBytes());
        var upperBoundValue = node.node("upper-bound-value").get(Value.class);
        var lowerBoundValue = node.node("lower-bound-value").get(Value.class);

        return new ShopItem(itemStack, upperBoundValue, lowerBoundValue);
    }

    @Override
    public void serialize(Type type, @Nullable ShopItem shopItem, ConfigurationNode node) throws SerializationException {
        if (shopItem != null) {
            node.node("item").set(Arrays.toString(shopItem.itemStack().serializeAsBytes()));
            node.node("upper-bound-value").set(shopItem.upperBoundValue());
            node.node("lower-bound-value").set(shopItem.lowerBoundValue());
        }
    }
}
