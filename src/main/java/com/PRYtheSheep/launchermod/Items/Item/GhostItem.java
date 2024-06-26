package com.PRYtheSheep.launchermod.Items.Item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sun.jna.platform.win32.OaIdl;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GhostItem extends Item {

    public GhostItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.CUSTOM;
    }

    int rotation;
    int timer;
    int timer2;

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private static final HumanoidModel.ArmPose SWING_POSE = HumanoidModel.ArmPose.create("SWING", false, (model, entity, arm) -> {
                if (arm == HumanoidArm.RIGHT)
                {
                    model.rightArm.xRot = (float) (Math.random() * Math.PI * 2);
                } else
                {
                    model.leftArm.xRot = (float) (Math.random() * Math.PI * 2);
                }
            });

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack)
            {
                if (!itemStack.isEmpty())
                {
                    if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0)
                    {
                        return SWING_POSE;
                    }
                }
                return HumanoidModel.ArmPose.EMPTY;
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                applyItemArmTransform(poseStack, arm);

                if (player.getUseItem() != itemInHand) {
                    return true;
                }
                if (player.isUsingItem()) {

                    if(rotation >= 0 && rotation < 145){
                        poseStack.rotateAround(Axis.YN.rotationDegrees(rotation++), 0,0,0);
                    }
                    else if(rotation >= 145 && rotation < 200){
                        //sit for 1 second
                        if(timer < 500){
                            poseStack.rotateAround(Axis.YN.rotationDegrees(rotation), 0,0,0);
                            timer++;
                        }
                        else{
                            poseStack.rotateAround(Axis.YN.rotationDegrees(rotation++), 0,0,0);
                        }
                    }
                    else if(rotation >= 200 && rotation <= 360){
                        //sit for 1 second
                        if(timer2 < 500){
                            poseStack.rotateAround(Axis.YN.rotationDegrees(rotation), 0,0,0);
                            timer2++;
                        }
                        else{
                            poseStack.rotateAround(Axis.YN.rotationDegrees(rotation++), 0,0,0);
                        }
                    }

                }
                return true;
            }

            private void applyItemArmTransform(PoseStack poseStack, HumanoidArm arm) {
                int i = arm == HumanoidArm.RIGHT ? 1 : -1;
                poseStack.translate(i * 0.56F, -0.52F, -0.72F);
            }
        });
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 60;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        rotation = 0;
        timer = timer2 = 0;
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }
}
