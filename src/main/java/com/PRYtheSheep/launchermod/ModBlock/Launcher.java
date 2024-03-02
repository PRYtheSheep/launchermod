package com.PRYtheSheep.launchermod.ModBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class Launcher extends Block implements EntityBlock {

    public static final EnumProperty<LauncherPartIndex> PART = EnumProperty.create("part", LauncherPartIndex.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public Launcher(Properties p_49795_) {
        super(p_49795_);
        StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<>(this);
        this.createBlockStateDefinition(builder);
        this.registerDefaultState(this.getStateDefinition().any().setValue(PART, LauncherPartIndex.P1));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(PART).add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(PART, LauncherPartIndex.P1).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        Direction direction = pState.getValue(FACING);

        //There has got to be a better way to do this
        switch(direction){
            case NORTH -> {
                pLevel.setBlock(pPos.offset(1,0,0), pState.setValue(PART, LauncherPartIndex.P2), 2);
                pLevel.setBlock(pPos.offset(2,0,0), pState.setValue(PART, LauncherPartIndex.P3), 2);
                pLevel.setBlock(pPos.offset(0,0,1), pState.setValue(PART, LauncherPartIndex.P4), 2);
                pLevel.setBlock(pPos.offset(1,0,1), pState.setValue(PART, LauncherPartIndex.P5), 2);
                pLevel.setBlock(pPos.offset(2,0,1), pState.setValue(PART, LauncherPartIndex.P6), 2);
                pLevel.setBlock(pPos.offset(0,0,2), pState.setValue(PART, LauncherPartIndex.P7), 2);
                pLevel.setBlock(pPos.offset(1,0,2), pState.setValue(PART, LauncherPartIndex.P8), 2);
                pLevel.setBlock(pPos.offset(2,0,2), pState.setValue(PART, LauncherPartIndex.P9), 2);
                pLevel.setBlock(pPos.offset(0,0,3), pState.setValue(PART, LauncherPartIndex.P10), 2);
                pLevel.setBlock(pPos.offset(1,0,3), pState.setValue(PART, LauncherPartIndex.P11), 2);
                pLevel.setBlock(pPos.offset(2,0,3), pState.setValue(PART, LauncherPartIndex.P12), 2);
                pLevel.setBlock(pPos.offset(0,0,4), pState.setValue(PART, LauncherPartIndex.P13), 2);
                pLevel.setBlock(pPos.offset(1,0,4), pState.setValue(PART, LauncherPartIndex.P14), 2);
                pLevel.setBlock(pPos.offset(2,0,4), pState.setValue(PART, LauncherPartIndex.P15), 2);
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
    }
}
