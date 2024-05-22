package com.PRYtheSheep.launchermod.Items.Projectile.Drone;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class DroneModel<T extends Entity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(LauncherMod.MODID, "drone"), "main");
    private final ModelPart wing2;
    private final ModelPart wing;
    private final ModelPart nose;
    private final ModelPart head;
    private final ModelPart body;

    public DroneModel(ModelPart root) {
        this.wing2 = root.getChild("wing2");
        this.wing = root.getChild("wing");
        this.nose = root.getChild("nose");
        this.head = root.getChild("head");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition wing2 = partdefinition.addOrReplaceChild("wing2", CubeListBuilder.create(), PartPose.offsetAndRotation(15.272F, 17.6765F, 8.4604F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r1 = wing2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 17).addBox(26.9139F, -0.5696F, 3.4376F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.2563F, -0.8462F, -8.9792F, -1.5708F, 1.4835F, 0.0873F));

        PartDefinition cube_r2 = wing2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(26, 68).addBox(3.2543F, -0.5696F, -6.1412F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(53, 1).addBox(8.2543F, -0.5696F, -26.1412F, 10.0F, 2.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.2563F, -0.8462F, -8.9792F, -0.0886F, 0.1739F, 1.6427F));

        PartDefinition cube_r3 = wing2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(24.4456F, 11.7033F, 3.4376F, 2.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.2563F, -0.8462F, -8.9792F, -1.5708F, 1.0472F, 0.0873F));

        PartDefinition cube_r4 = wing2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(61, 67).addBox(3.0236F, -0.5696F, -9.4099F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.2563F, -0.8462F, -8.9792F, -2.9543F, 1.0836F, -1.3176F));

        PartDefinition cube_r5 = wing2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(2, 2).addBox(-4.5839F, -0.6696F, -1.3906F, 10.0F, 2.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.2563F, -0.8462F, -8.9792F, -3.0501F, 0.3042F, -1.4561F));

        PartDefinition wing = partdefinition.addOrReplaceChild("wing", CubeListBuilder.create(), PartPose.offsetAndRotation(-14.9859F, 17.7015F, 8.5676F, 0.0F, 0.0F, 0.0873F));

        PartDefinition cube_r6 = wing.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(48, 68).addBox(-8.2543F, -0.5696F, -6.1412F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(53, 35).addBox(-18.2543F, -0.5696F, -26.1412F, 10.0F, 2.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.9691F, -0.8462F, -9.0864F, 3.053F, 0.1739F, -1.6734F));

        PartDefinition cube_r7 = wing.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 34).addBox(-26.4456F, 11.7033F, 3.4376F, 2.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.9691F, -0.8462F, -9.0864F, 1.5708F, 1.0472F, 3.0543F));

        PartDefinition cube_r8 = wing.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 51).addBox(-28.9139F, -0.5696F, 3.4376F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.9691F, -0.8462F, -9.0864F, 1.5708F, 1.4835F, 3.0543F));

        PartDefinition cube_r9 = wing.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(2, 36).addBox(-5.4161F, -0.6696F, -1.3906F, 10.0F, 2.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.9691F, -0.8462F, -9.0864F, 0.0915F, 0.3042F, 1.511F));

        PartDefinition cube_r10 = wing.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(23, 73).addBox(-7.0236F, -0.5696F, -9.4099F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.9691F, -0.8462F, -9.0864F, 0.1873F, 1.0836F, 1.6494F));

        PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 18.9857F, -10.5F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r11 = nose.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(14, 17).addBox(-1.6F, -22.7669F, -1.9226F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 78).addBox(-2.0F, -13.7331F, -3.2939F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -12.7669F, 1.6774F, 4.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 68).addBox(-2.0F, -15.7669F, -2.3226F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.9812F, 0.8226F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(1.0F, 43.8F, -16.0F));

        PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(2, 1).addBox(-0.55F, 3.2413F, -7.9286F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -25.6368F, 15.4812F, 0.0F, 1.5708F, 1.5708F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.35F, 20.6045F, 13.3526F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(24, 24).addBox(-3.0F, 18.2669F, -1.8939F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(2.0F, 18.2669F, -1.8939F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-2.0F, 18.2669F, -2.8939F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-2.0F, 18.2669F, 1.1061F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(9, 0).addBox(-2.0F, 18.3669F, -2.8939F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 63).addBox(2.0F, -3.7331F, -2.8939F, 0.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(52, 0).addBox(2.0F, -3.7331F, -2.8939F, 1.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(52, 34).addBox(-3.0F, -3.7331F, -2.8939F, 1.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(-2.0F, 0.6669F, -4.8939F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 76).addBox(-2.0F, 0.6669F, -5.8939F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 11).addBox(-2.0F, 10.6669F, -4.8939F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 11).addBox(1.0F, 0.6669F, -4.8939F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 34).addBox(-2.0F, -3.7331F, -3.8939F, 4.0F, 22.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-3.0F, -3.7331F, 2.1061F, 6.0F, 22.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, -13.8714F, 2.4413F, -1.5708F, 0.0F, -1.5708F));

        PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, -5.8939F, 12.8921F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, -13.8714F, 2.4413F, 3.1416F, 0.0F, -1.5708F));

        PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(14, 5).addBox(-0.5F, 6.6401F, 8.5242F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, -13.8714F, 2.4413F, -2.2253F, 0.0F, -1.5708F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }



    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        wing2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        wing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return null;
    }

}
