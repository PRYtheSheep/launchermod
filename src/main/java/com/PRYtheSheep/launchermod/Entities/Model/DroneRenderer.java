package com.PRYtheSheep.launchermod.Entities.Model;

import com.PRYtheSheep.launchermod.Entities.Drone.DroneEntity;
import com.PRYtheSheep.launchermod.LauncherMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DroneRenderer extends MobRenderer<DroneEntity, DroneModel<DroneEntity>> {
    public DroneRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DroneModel<>(pContext.bakeLayer(ModelLayer.DRONE_LAYER_LOCATION)), 3);
    }

    @Override
    public ResourceLocation getTextureLocation(DroneEntity pEntity) {
        return new ResourceLocation(LauncherMod.MODID, "textures/entity/drone.png");
    }

    @Override
    public void render(DroneEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
