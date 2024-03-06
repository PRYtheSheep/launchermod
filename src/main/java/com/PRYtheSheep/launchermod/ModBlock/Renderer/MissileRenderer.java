package com.PRYtheSheep.launchermod.ModBlock.Renderer;

import com.PRYtheSheep.launchermod.ModItem.Projectile.MissileItemEntity;
import com.PRYtheSheep.launchermod.ModItem.Projectile.MissileModel;
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

public class MissileRenderer extends EntityRenderer<MissileItemEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/missile.png");

    private final MissileModel model;

    public MissileRenderer(EntityRendererProvider.Context manager){
        super(manager);
        this.model = new MissileModel(manager.bakeLayer(MissileModel.LAYER_LOCATION));
    }

    @Override
    public void render(MissileItemEntity entity, float yaw, float pitch, PoseStack stack, MultiBufferSource bufferSource, int light) {
        stack.pushPose();
        stack.translate(0, -0.1, 0);

        stack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pitch, entity.yRotO, entity.getYRot()) - 90.0F));
        stack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pitch, entity.xRotO, entity.getXRot()) + 90.0F));


        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(bufferSource, this.model.renderType(this.getTextureLocation(entity)), false, entity.isFoil());
        this.model.renderToBuffer(stack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stack.popPose();
        super.render(entity, yaw, pitch, stack, bufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(MissileItemEntity pEntity) {
        return TEXTURE;
    }

}
