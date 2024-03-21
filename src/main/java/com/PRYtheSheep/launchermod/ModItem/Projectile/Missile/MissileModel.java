package com.PRYtheSheep.launchermod.ModItem.Projectile.Missile;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.PRYtheSheep.launchermod.LauncherMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class MissileModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(LauncherMod.MODID, "missile"), "main");
    private final ModelPart nose;
    private final ModelPart body;
    private final ModelPart fin;
    private final ModelPart fin2;
    private final ModelPart fin3;
    private final ModelPart fin4;
    private final ModelPart fin5;
    private final ModelPart fin6;
    private final ModelPart fin7;
    private final ModelPart fin8;

    public MissileModel(ModelPart root) {
        this.nose = root.getChild("nose");
        this.body = root.getChild("body");
        this.fin = root.getChild("fin");
        this.fin2 = root.getChild("fin2");
        this.fin3 = root.getChild("fin3");
        this.fin4 = root.getChild("fin4");
        this.fin5 = root.getChild("fin5");
        this.fin6 = root.getChild("fin6");
        this.fin7 = root.getChild("fin7");
        this.fin8 = root.getChild("fin8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(33, 0).addBox(2.0F, -177.0F, -1.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -177.0F, -1.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(144, 75).addBox(-2.0F, -177.0F, -3.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(146, 164).addBox(-2.0F, -177.0F, 3.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(160, 29).addBox(-2.0F, -186.0F, -1.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(140, 25).addBox(-3.0F, -179.0F, -2.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(87, 88).addBox(-4.0F, -170.0F, -3.0F, 7.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(112, 138).addBox(-3.0F, -173.0F, -3.0F, 5.0F, 19.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(94, 66).addBox(-3.0F, -173.0F, 6.0F, 5.0F, 19.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(68, 0).addBox(-3.0F, -154.0F, 7.0F, 5.0F, 94.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(5.0F, -154.0F, 0.0F, 1.0F, 94.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(94, 33).addBox(5.0F, -60.0F, 0.0F, 3.0F, 28.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(94, 0).addBox(-9.0F, -60.0F, 0.0F, 3.0F, 28.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(128, 0).addBox(-3.0F, -60.0F, 8.0F, 5.0F, 28.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 126).addBox(-3.0F, -60.0F, -5.0F, 5.0F, 28.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0).addBox(-7.0F, -154.0F, 0.0F, 1.0F, 94.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(82, 0).addBox(-3.0F, -154.0F, -4.0F, 5.0F, 94.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(164, 136).addBox(2.0F, -167.0F, -2.0F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 160).addBox(-5.0F, -167.0F, -2.0F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(164, 122).addBox(2.0F, -167.0F, 6.0F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(163, 163).addBox(-5.0F, -167.0F, 6.0F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(132, 138).addBox(3.0F, -167.0F, -1.0F, 1.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 95).addBox(-5.0F, -167.0F, -1.0F, 1.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(121, 92).addBox(3.0F, -167.0F, 5.0F, 1.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(106, 112).addBox(-5.0F, -167.0F, 5.0F, 1.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(138, 86).addBox(3.0F, -173.0F, 0.0F, 2.0F, 19.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(136, 133).addBox(-6.0F, -173.0F, 0.0F, 2.0F, 19.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -155.0F, -3.0F, 11.0F, 123.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 44.0F, -2.0F));

        PartDefinition fin = partdefinition.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(98, 160).addBox(-9.0F, -35.0F, -1.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(126, 157).addBox(-13.0F, -21.0F, 3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 156).addBox(-13.0F, -21.0F, -2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(100, 134).addBox(-14.0F, -25.0F, -1.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(140, 157).addBox(-15.0F, -17.0F, 0.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 167).addBox(-16.0F, -11.0F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(126, 56).addBox(-12.0F, -28.0F, -1.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 24.0F, 0.0F));

        PartDefinition fin2 = partdefinition.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(24, 160).addBox(2.5F, -20.0F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(156, 0).addBox(-1.5F, -6.0F, 2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 155).addBox(-1.5F, -6.0F, -3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 134).addBox(-2.5F, -10.0F, -2.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(94, 142).addBox(-3.5F, -2.0F, -1.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(167, 18).addBox(-4.5F, 4.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 112).addBox(-0.5F, -13.0F, -2.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5F, 9.0F, 1.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition fin3 = partdefinition.addOrReplaceChild("fin3", CubeListBuilder.create().texOffs(12, 160).addBox(2.5F, -20.0F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(154, 93).addBox(-1.5F, -6.0F, 2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(111, 159).addBox(-1.5F, -6.0F, -3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 134).addBox(-2.5F, -10.0F, -2.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(126, 138).addBox(-3.5F, -2.0F, -1.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(164, 150).addBox(-4.5F, 4.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(111, 108).addBox(-0.5F, -13.0F, -2.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.0F, 12.6F, 0.0F, 1.5708F, 0.0F));

        PartDefinition fin4 = partdefinition.addOrReplaceChild("fin4", CubeListBuilder.create().texOffs(0, 160).addBox(2.5F, -20.0F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(154, 39).addBox(-1.5F, -6.0F, 2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(152, 75).addBox(-1.5F, -6.0F, -3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 134).addBox(-2.5F, -10.0F, -2.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 134).addBox(-3.5F, -2.0F, -1.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(166, 84).addBox(-4.5F, 4.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(110, 30).addBox(-0.5F, -13.0F, -2.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.0F, -11.6F, 0.0F, -1.5708F, 0.0F));

        PartDefinition fin5 = partdefinition.addOrReplaceChild("fin5", CubeListBuilder.create().texOffs(86, 156).addBox(-9.0F, -107.0F, -1.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(150, 146).addBox(-13.0F, -93.0F, 3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(150, 128).addBox(-13.0F, -93.0F, -2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(129, 112).addBox(-14.0F, -97.0F, -1.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(56, 129).addBox(-15.0F, -89.0F, 0.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(166, 75).addBox(-16.0F, -83.0F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(110, 0).addBox(-12.0F, -100.0F, -1.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition fin6 = partdefinition.addOrReplaceChild("fin6", CubeListBuilder.create().texOffs(64, 156).addBox(2.5F, -92.0F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(144, 57).addBox(-1.5F, -78.0F, 2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(76, 142).addBox(-1.5F, -78.0F, -3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 129).addBox(-2.5F, -82.0F, -2.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(76, 126).addBox(-3.5F, -74.0F, -1.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(82, 165).addBox(-4.5F, -68.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(108, 62).addBox(-0.5F, -85.0F, -2.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.5F, 9.0F, 1.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition fin7 = partdefinition.addOrReplaceChild("fin7", CubeListBuilder.create().texOffs(155, 111).addBox(2.5F, -93.0F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(142, 0).addBox(-1.5F, -79.0F, 2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(158, 57).addBox(-1.5F, -79.0F, -3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(128, 30).addBox(-2.5F, -83.0F, -2.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(115, 92).addBox(-3.5F, -75.0F, -1.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 144).addBox(-4.5F, -69.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(44, 99).addBox(-0.5F, -86.0F, -2.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.0F, 11.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition fin8 = partdefinition.addOrReplaceChild("fin8", CubeListBuilder.create().texOffs(155, 18).addBox(2.5F, -92.0F, -2.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(141, 110).addBox(-1.5F, -78.0F, 2.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(140, 39).addBox(-1.5F, -78.0F, -3.0F, 6.0F, 17.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(126, 86).addBox(-2.5F, -82.0F, -2.0F, 2.0F, 22.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(100, 112).addBox(-3.5F, -74.0F, -1.0F, 1.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(154, 164).addBox(-4.5F, -68.0F, -1.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(64, 96).addBox(-0.5F, -85.0F, -2.0F, 5.0F, 26.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.0F, -10.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin6.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin7.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        fin8.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}