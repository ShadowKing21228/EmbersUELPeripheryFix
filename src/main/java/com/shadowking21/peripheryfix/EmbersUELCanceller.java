package com.shadowking21.peripheryfix;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import teamroots.embers.mixin.thaumicperiphery.ThaumicPeripheryMixin;

import java.util.List;

public class EmbersUELCanceller implements MixinCanceller {

    @Override
    public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
        return normalizeMixinName(mixinClassName).equals("teamroots.embers.mixin.thaumicperiphery.ThaumicPeripheryMixin");
    }

    private static String normalizeMixinName(String mixinClassName) {
        return mixinClassName.indexOf('/') >= 0 ? mixinClassName.replace('/', '.') : mixinClassName;
    }
}
