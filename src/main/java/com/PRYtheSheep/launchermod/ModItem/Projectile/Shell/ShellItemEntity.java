package com.PRYtheSheep.launchermod.ModItem.Projectile.Shell;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import com.PRYtheSheep.launchermod.Networking.Channel;
import com.PRYtheSheep.launchermod.Networking.LauncherPayloadS2C;
import com.PRYtheSheep.launchermod.Networking.TracerPayloadS2C;
import net.minecraft.core.SectionPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.world.chunk.TicketController;

import java.util.function.Predicate;

import static com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherRenderer.FlightPathRenderer.entityPos;

public class ShellItemEntity extends AbstractArrow{

    public ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel){
        super(pEntityType, pLevel, new ItemStack(LauncherMod.SHELL_ITEM.asItem()));
    }

    protected ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pLevel, pPickupItemStack);
    }

    protected ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pX, pY, pZ, pLevel, pPickupItemStack);
    }

    protected ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pOwner, pLevel, pPickupItemStack);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        //temporary method for now
        entityPos.clear();
        markedForRemoval = true;

        if(this.level().isClientSide) return;

        //Explode if it hits an entity
        this.level().explode(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                6F,
                Level.ExplosionInteraction.TNT
        );
        this.owner.canFire = true;
        this.kill();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        //temporary method for now
        entityPos.clear();
        markedForRemoval = true;

        if(this.level().isClientSide) return;
        //Explode if it hits a block
        this.level().explode(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                6F,
                Level.ExplosionInteraction.TNT
        );
        this.owner.canFire = true;
        this.kill();
    }

    public boolean isFoil() {
        return false;
    }

    public static final TicketController SHELL_TICKET_CONTROLLER = new TicketController(new ResourceLocation(LauncherMod.MODID, "shell"));

    public float launchVelocityX;
    public float launchVelocityY;
    public float launchVelocityZ;
    int tick = 0;
    private boolean markedForRemoval = false;
    public LauncherBE owner = null;

    @Override
    public void tick() {
        tick++;
        if(this.level().isClientSide) return;
        super.tick();

        //TESTING TICKET CONTROLLER
        SHELL_TICKET_CONTROLLER.forceChunk(
                (ServerLevel) this.level(),
                this.getOnPos(),
                SectionPos.blockToSectionCoord(
                        this.getOnPos().getX()),
                SectionPos.blockToSectionCoord(
                        this.getOnPos().getZ()),
                true,
                true);
        //END OF TESTING

        this.setDeltaMovement(new Vec3(launchVelocityX, launchVelocityY + tick * -0.05F, launchVelocityZ));
        if(tick>=2000) {
            //temporary method for now
            entityPos.clear();
            markedForRemoval = true;
            this.owner.canFire = true;
            this.kill();
            return;
        }

        //TESTING
        if(!markedForRemoval){
            ServerPlayer serverPlayer = this.getServer().getPlayerList().getPlayers().get(0);

            //Send a payload to the client side
            //Payload contains the current position for the renderer to trace the flight path
            Channel.sendToPlayer(new TracerPayloadS2C(new Vec3(this.getX(), this.getY(), this.getZ())), serverPlayer);
        }

        ServerPlayer player;
        Predicate<Entity> predicate = (i) -> (i instanceof ServerPlayer);
        try{
            player = ((ServerLevel) this.level()).getPlayers(predicate, 1).get(0);
        } catch (Exception e) {
            return;
        }
        player.displayClientMessage(Component.literal(
                (int) this.position().x + " " +
                        (int) this.position().y + " " +
                        (int) this.position().z), true);
        //END OF TESTING
    }
}
