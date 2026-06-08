package com.shadowking21.peripheryfix;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class EmbersUELCanceller implements MixinCanceller {

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        // Avoid referencing Forge/Minecraft classes directly here: MixinCanceller is invoked
        // very early during mixin setup and those classes may not be available yet which
        // causes ClassNotFoundException. Use a lightweight class-presence check instead.

        String normalized = normalizeMixinName(mixinClassName);
        // Only care about cancelling the specific ThaumicPeriphery mixin
        if (!"teamroots.embers.mixin.thaumicperiphery.ThaumicPeripheryMixin".equals(normalized)) {
            return false;
        }

        // If the client-only Minecraft class exists, we're on the client -> don't cancel.
        // If it does not exist, treat as dedicated server and cancel the mixin.
        boolean clientClassPresent = isClassPresent("net.minecraft.client.Minecraft");
        PeripheryFix.LOGGER.info("net.minecraft.client.Minecraft present: {}", clientClassPresent);

        return !clientClassPresent; // cancel on dedicated server
    }

    private static boolean isClassPresent(String className) {
        try {
            Class.forName(className, false, Thread.currentThread().getContextClassLoader());
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    private static String normalizeMixinName(String mixinClassName) {
        return mixinClassName.indexOf('/') >= 0 ? mixinClassName.replace('/', '.') : mixinClassName;
    }
}
