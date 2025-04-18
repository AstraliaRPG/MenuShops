package dev.thezexquex.menushops.hooks;

import dev.thezexquex.menushops.MenuShopsPlugin;

import java.util.HashSet;
import java.util.Set;

public class PluginHookRegistry {
    private final Set<Hook> hooks;

    public PluginHookRegistry() {
        this.hooks = new HashSet<>();
    }

    public void register(MenuShopsPlugin plugin, Hook hook) {
        if (!hook.isAvailable(plugin.getServer())) {
            return;
        }
        plugin.getLogger().info("Registered plugin hook: " + hook.getIdentifier());
        this.hooks.add(hook);
        hook.setup(plugin);
    }

    public boolean isAvailable(Class<? extends Hook> hookClass) {
        return hooks.stream().anyMatch(hookClass::isInstance);
    }
}
