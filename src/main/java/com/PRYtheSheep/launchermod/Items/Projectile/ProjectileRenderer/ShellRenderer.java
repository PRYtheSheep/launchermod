package com.PRYtheSheep.launchermod.Items.Projectile.ProjectileRenderer;

import com.PRYtheSheep.launchermod.Items.Projectile.Shell.ShellItemEntity;
import com.PRYtheSheep.launchermod.Items.Projectile.Shell.ShellModel;
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

public class ShellRenderer extends EntityRenderer<ShellItemEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/entity/shell.png");

    private final ShellModel model;

    public ShellRenderer(EntityRendererProvider.Context manager){
        super(manager);
        this.model = new ShellModel(manager.bakeLayer(ShellModel.LAYER_LOCATION));
    }

    @Override
    public void render(ShellItemEntity entity, float yaw, float pitch, PoseStack stack, MultiBufferSource bufferSource, int light) {
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
    public ResourceLocation getTextureLocation(ShellItemEntity pEntity) {return TEXTURE;}

}
