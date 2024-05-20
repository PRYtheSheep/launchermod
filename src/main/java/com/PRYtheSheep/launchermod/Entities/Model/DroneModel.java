package com.PRYtheSheep.launchermod.Entities.Model;// Made with Blockbench 4.10.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class DroneModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart drone;

	public DroneModel(ModelPart root) {
		this.drone = root.getChild("drone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition drone = partdefinition.addOrReplaceChild("drone", CubeListBuilder.create(), PartPose.offset(1.0F, 24.0F, 0.0F));

		PartDefinition body = drone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(64, 0).addBox(-3.35F, -13.6045F, 4.5474F, 6.0F, 22.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(64, 34).addBox(-2.35F, -13.6045F, -1.4526F, 4.0F, 22.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 11).addBox(0.65F, -9.2045F, -2.4526F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(23, 11).addBox(-2.35F, 0.7955F, -2.4526F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(46, 76).addBox(-2.35F, -9.2045F, -3.4526F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 0).addBox(-2.35F, -9.2045F, -2.4526F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 34).addBox(-3.35F, -13.6045F, -0.4526F, 1.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(52, 0).addBox(1.65F, -13.6045F, -0.4526F, 1.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 63).addBox(1.65F, -13.6045F, -0.4526F, 0.0F, 22.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(9, 0).addBox(-2.35F, 8.3955F, -0.4526F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-2.35F, 8.3955F, 3.5474F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-2.35F, 8.3955F, -0.4526F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 34).addBox(1.65F, 8.3955F, 0.5474F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(24, 24).addBox(-3.35F, 8.3955F, 0.5474F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(2, 1).addBox(-0.9F, -19.0F, -1.8F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.65F, -3.3955F, 13.3526F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 4.5208F, 6.8352F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 5).addBox(-0.5F, -1.1088F, -0.7066F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.35F, 1.8955F, 5.0474F, -0.6545F, 0.0F, 0.0F));

		PartDefinition nose = drone.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(10, 68).addBox(-2.5F, -1.7857F, -1.5F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.5F, 1.2143F, 2.5F, 4.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 78).addBox(-2.5F, 0.2481F, -2.4713F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 17).addBox(-2.1F, -8.7857F, -1.1F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -5.0143F, -10.5F, 1.5708F, 0.0F, 0.0F));

		PartDefinition wing = drone.addOrReplaceChild("wing", CubeListBuilder.create().texOffs(0, 51).addBox(-13.9448F, -1.4158F, -1.6488F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.9859F, -6.2985F, 8.5676F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r3 = wing.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(48, 68).addBox(9.0F, -1.0F, 15.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(53, 35).addBox(-1.0F, -1.0F, -5.0F, 10.0F, 2.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.8472F, -0.4158F, 8.2346F, 0.0F, 1.3963F, 0.0F));

		PartDefinition cube_r4 = wing.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.8838F, 0.8195F, 3.3512F, 0.0F, 0.0F, 0.4363F));

		PartDefinition cube_r5 = wing.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(2, 36).addBox(-1.0F, -1.0F, -5.0F, 10.0F, 2.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.1988F, -0.5158F, -8.2128F, 0.0F, -1.2654F, 0.0F));

		PartDefinition cube_r6 = wing.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(23, 73).addBox(-1.0F, -1.0F, -2.0F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0476F, -0.4158F, -14.4405F, 0.0F, -0.48F, 0.0F));

		PartDefinition wing2 = drone.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(0, 17).addBox(11.9448F, -1.4158F, -1.6488F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.9859F, -6.2985F, 8.5676F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r7 = wing2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(26, 68).addBox(-14.0F, -1.0F, 15.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(53, 1).addBox(-9.0F, -1.0F, -5.0F, 10.0F, 2.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.8472F, -0.4158F, 8.2346F, 0.0F, -1.3963F, 0.0F));

		PartDefinition cube_r8 = wing2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.8838F, 0.8195F, 3.3512F, 0.0F, 0.0F, -0.4363F));

		PartDefinition cube_r9 = wing2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(61, 67).addBox(-3.0F, -1.0F, -2.0F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0476F, -0.4158F, -14.4405F, 0.0F, 0.48F, 0.0F));

		PartDefinition cube_r10 = wing2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(2, 2).addBox(-9.0F, -1.0F, -5.0F, 10.0F, 2.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.1988F, -0.5158F, -8.2128F, 0.0F, 1.2654F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		drone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return null;
	}
}