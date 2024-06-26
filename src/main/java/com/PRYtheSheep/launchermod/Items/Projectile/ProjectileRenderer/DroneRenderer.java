package com.PRYtheSheep.launchermod.Items.Projectile.ProjectileRenderer;

import com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneEntity;
import com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneModel;
import com.PRYtheSheep.launchermod.Items.Projectile.Missile.MissileItemEntity;
import com.PRYtheSheep.launchermod.Items.Projectile.Missile.MissileModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

public class DroneRenderer extends EntityRenderer<DroneEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/drone.png");

    private final DroneModel model;

    public DroneRenderer(EntityRendererProvider.Context manager) {
        super(manager);
        this.model = new DroneModel(manager.bakeLayer(DroneModel.LAYER_LOCATION));
    }

    float previousOld;
    float previousNew;

    @Override
    public void render(DroneEntity entity, float yaw, float partialTick, PoseStack stack, MultiBufferSource bufferSource, int light) {
        stack.pushPose();
        stack.translate(0, -0.1, 0);

        float old = entity.yRotO;
        if(old< 0) old+= 360;

        float neW = entity.getYRot();
        if(neW< 0) neW+= 360;

        if(neW == 0.0F) old -= 360;

        stack.mulPose(Axis.YP.rotationDegrees(Math.round(Mth.lerp(partialTick, old, neW) - 90.0F)));


        stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, entity.xRotO, entity.getXRot()) + 90.0F));


        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(bufferSource, this.model.renderType(this.getTextureLocation(entity)), false, entity.isFoil());
        this.model.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stack.popPose();
        super.render(entity, yaw, partialTick, stack, bufferSource, light);
        previousOld = old;
        previousNew = neW;
    }

    @Override
    public ResourceLocation getTextureLocation(DroneEntity pEntity) {
        return TEXTURE;
    }
}