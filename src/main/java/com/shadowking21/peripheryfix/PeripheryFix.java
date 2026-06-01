package com.shadowking21.peripheryfix;

import com.shadowking21.peripheryfix.proxy.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Referencee.MOD_ID, name = Referencee.MOD_NAME, version = Referencee.VERSION)
public class PeripheryFix {

    public static final Logger LOGGER = LogManager.getLogger(Referencee.MOD_NAME);

    @SidedProxy(modId = Referencee.MOD_ID, clientSide = "com.shadowking21.peripheryfix.proxy.ClientProxy", serverSide = "com.shadowking21.peripheryfix.proxy.CommonProxy")
    public static IProxy proxy;

}
