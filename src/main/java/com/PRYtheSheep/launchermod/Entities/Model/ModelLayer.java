package com.PRYtheSheep.launchermod.Entities.Model;

import com.PRYtheSheep.launchermod.LauncherMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelLayer {
    public static final ModelLayerLocation DRONE_LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(LauncherMod.MODID, "drone_layer"), "main");
}
