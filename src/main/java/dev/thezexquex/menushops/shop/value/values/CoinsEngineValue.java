package dev.thezexquex.menushops.shop.value.values;

import dev.thezexquex.menushops.shop.value.Value;
import org.bukkit.entity.Player;

public class CoinsEngineValue extends Value {
    public CoinsEngineValue(int amount) {
        super(amount);
    }

    @Override
    public void withdraw(Player player, boolean stack) {

    }

    @Override
    public void deposit(Player player, boolean stack) {

    }

    @Override
    public boolean hasEnough(Player player, boolean stack) {
        return false;
    }
}
