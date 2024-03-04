package com.PRYtheSheep.launchermod.ModBlock.Launcher;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
        this.registerDefaultState(this.getStateDefinition().any().setValue(PART, LauncherPartIndex.P1).setValue(FACING, Direction.NORTH));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder) {
        blockBlockStateBuilder.add(FACING, PART);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(PART, LauncherPartIndex.P1).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        Direction direction = pState.getValue(FACING);

        //Yes this is stupid.
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
            case EAST -> {
                pLevel.setBlock(pPos.offset(0,0,1), pState.setValue(PART, LauncherPartIndex.P2), 2);
                pLevel.setBlock(pPos.offset(0,0,2), pState.setValue(PART, LauncherPartIndex.P3), 2);
                pLevel.setBlock(pPos.offset(-1,0,0), pState.setValue(PART, LauncherPartIndex.P4), 2);
                pLevel.setBlock(pPos.offset(-1,0,1), pState.setValue(PART, LauncherPartIndex.P5), 2);
                pLevel.setBlock(pPos.offset(-1,0,2), pState.setValue(PART, LauncherPartIndex.P6), 2);
                pLevel.setBlock(pPos.offset(-2,0,0), pState.setValue(PART, LauncherPartIndex.P7), 2);
                pLevel.setBlock(pPos.offset(-2,0,1), pState.setValue(PART, LauncherPartIndex.P8), 2);
                pLevel.setBlock(pPos.offset(-2,0,2), pState.setValue(PART, LauncherPartIndex.P9), 2);
                pLevel.setBlock(pPos.offset(-3,0,0), pState.setValue(PART, LauncherPartIndex.P10), 2);
                pLevel.setBlock(pPos.offset(-3,0,1), pState.setValue(PART, LauncherPartIndex.P11), 2);
                pLevel.setBlock(pPos.offset(-3,0,2), pState.setValue(PART, LauncherPartIndex.P12), 2);
                pLevel.setBlock(pPos.offset(-4,0,0), pState.setValue(PART, LauncherPartIndex.P13), 2);
                pLevel.setBlock(pPos.offset(-4,0,1), pState.setValue(PART, LauncherPartIndex.P14), 2);
                pLevel.setBlock(pPos.offset(-4,0,2), pState.setValue(PART, LauncherPartIndex.P15), 2);
            }
            case SOUTH -> {
                pLevel.setBlock(pPos.offset(-1,0,0), pState.setValue(PART, LauncherPartIndex.P2), 2);
                pLevel.setBlock(pPos.offset(-2,0,0), pState.setValue(PART, LauncherPartIndex.P3), 2);
                pLevel.setBlock(pPos.offset(0,0,-1), pState.setValue(PART, LauncherPartIndex.P4), 2);
                pLevel.setBlock(pPos.offset(-1,0,-1), pState.setValue(PART, LauncherPartIndex.P5), 2);
                pLevel.setBlock(pPos.offset(-2,0,-1), pState.setValue(PART, LauncherPartIndex.P6), 2);
                pLevel.setBlock(pPos.offset(0,0,-2), pState.setValue(PART, LauncherPartIndex.P7), 2);
                pLevel.setBlock(pPos.offset(-1,0,-2), pState.setValue(PART, LauncherPartIndex.P8), 2);
                pLevel.setBlock(pPos.offset(-2,0,-2), pState.setValue(PART, LauncherPartIndex.P9), 2);
                pLevel.setBlock(pPos.offset(0,0,-3), pState.setValue(PART, LauncherPartIndex.P10), 2);
                pLevel.setBlock(pPos.offset(-1,0,-3), pState.setValue(PART, LauncherPartIndex.P11), 2);
                pLevel.setBlock(pPos.offset(-2,0,-3), pState.setValue(PART, LauncherPartIndex.P12), 2);
                pLevel.setBlock(pPos.offset(0,0,-4), pState.setValue(PART, LauncherPartIndex.P13), 2);
                pLevel.setBlock(pPos.offset(-1,0,-4), pState.setValue(PART, LauncherPartIndex.P14), 2);
                pLevel.setBlock(pPos.offset(-2,0,-4), pState.setValue(PART, LauncherPartIndex.P15), 2);
            }
            case WEST -> {
                pLevel.setBlock(pPos.offset(0,0,-1), pState.setValue(PART, LauncherPartIndex.P2), 2);
                pLevel.setBlock(pPos.offset(0,0,-2), pState.setValue(PART, LauncherPartIndex.P3), 2);
                pLevel.setBlock(pPos.offset(1,0,0), pState.setValue(PART, LauncherPartIndex.P4), 2);
                pLevel.setBlock(pPos.offset(1,0,-1), pState.setValue(PART, LauncherPartIndex.P5), 2);
                pLevel.setBlock(pPos.offset(1,0,-2), pState.setValue(PART, LauncherPartIndex.P6), 2);
                pLevel.setBlock(pPos.offset(2,0,0), pState.setValue(PART, LauncherPartIndex.P7), 2);
                pLevel.setBlock(pPos.offset(2,0,-1), pState.setValue(PART, LauncherPartIndex.P8), 2);
                pLevel.setBlock(pPos.offset(2,0,-2), pState.setValue(PART, LauncherPartIndex.P9), 2);
                pLevel.setBlock(pPos.offset(3,0,0), pState.setValue(PART, LauncherPartIndex.P10), 2);
                pLevel.setBlock(pPos.offset(3,0,-1), pState.setValue(PART, LauncherPartIndex.P11), 2);
                pLevel.setBlock(pPos.offset(3,0,-2), pState.setValue(PART, LauncherPartIndex.P12), 2);
                pLevel.setBlock(pPos.offset(4,0,0), pState.setValue(PART, LauncherPartIndex.P13), 2);
                pLevel.setBlock(pPos.offset(4,0,-1), pState.setValue(PART, LauncherPartIndex.P14), 2);
                pLevel.setBlock(pPos.offset(4,0,-2), pState.setValue(PART, LauncherPartIndex.P15), 2);
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        //Only create a LauncherBE if it is part 14
        if(pState.getValue(PART) == LauncherPartIndex.P14) return new LauncherBE(pPos, pState);
        return null;
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if(!pLevel.isClientSide()){
            Level sLevel = Minecraft.getInstance().level;
            //For directions apart from north, need to apply the rotation matrix
            switch(pState.getValue(FACING)){
                case NORTH -> {

                    for(int[] coordinates : pState.getValue(PART).number){
                        //Get the pos to be checked and check the block
                        BlockPos posToBeChecked = pPos.offset(coordinates[0], 0, coordinates[1]);
                        BlockState stateToBeChecked = pLevel.getBlockState(posToBeChecked);
                        if(!stateToBeChecked.is(this)){
                            //Destroy the block if it is not a launcher block
                            sLevel.destroyBlock(pPos, false);
                            return Blocks.AIR.defaultBlockState();
                        }
                    }

                }
                case EAST -> {
                    //Create a 2d matrix and fill it with rotation values from the enum class
                    int[][] rotationMatrix = new int[2][2];
                    int count = 0;
                    for(int row=0; row<2; row++){
                        for(int col=0; col<2; col++){
                            rotationMatrix[row][col] = LauncherPartIndex.ROTATIONMATRIX[count++];
                        }
                    }

                    for(int[] coordinates : pState.getValue(PART).number){
                        //Multiply the coordinates by the rotation matrix. Multiply multiple times if needed
                        for(int i=0; i<1; i++){
                            coordinates = LauncherPartIndex.multiply(coordinates, rotationMatrix);
                        }

                        BlockPos posToBeChecked = pPos.offset(coordinates[0], 0, coordinates[1]);
                        BlockState stateToBeChecked = pLevel.getBlockState(posToBeChecked);
                        if(!stateToBeChecked.is(this)){
                            sLevel.destroyBlock(pPos, false);
                            return Blocks.AIR.defaultBlockState();
                        }
                    }

                }
                case SOUTH -> {

                    int[][] rotationMatrix = new int[2][2];
                    int count = 0;
                    for(int row=0; row<2; row++){
                        for(int col=0; col<2; col++){
                            rotationMatrix[row][col] = LauncherPartIndex.ROTATIONMATRIX[count++];
                        }
                    }

                    for(int[] coordinates : pState.getValue(PART).number){
                        for(int i=0; i<2; i++){
                            coordinates = LauncherPartIndex.multiply(coordinates, rotationMatrix);
                        }

                        BlockPos posToBeChecked = pPos.offset(coordinates[0], 0, coordinates[1]);
                        BlockState stateToBeChecked = pLevel.getBlockState(posToBeChecked);
                        if(!stateToBeChecked.is(this)){
                            sLevel.destroyBlock(pPos, false);
                            return Blocks.AIR.defaultBlockState();
                        }
                    }

                }
                case WEST -> {

                    int[][] rotationMatrix = new int[2][2];
                    int count = 0;
                    for(int row=0; row<2; row++){
                        for(int col=0; col<2; col++){
                            rotationMatrix[row][col] = LauncherPartIndex.ROTATIONMATRIX[count++];
                        }
                    }

                    for(int[] coordinates : pState.getValue(PART).number){
                        for(int i=0; i<3; i++){
                            coordinates = LauncherPartIndex.multiply(coordinates, rotationMatrix);
                        }

                        BlockPos posToBeChecked = pPos.offset(coordinates[0], 0, coordinates[1]);
                        BlockState stateToBeChecked = pLevel.getBlockState(posToBeChecked);
                        if(!stateToBeChecked.is(this)){
                            sLevel.destroyBlock(pPos, false);
                            return Blocks.AIR.defaultBlockState();
                        }
                    }

                }
            }
        }
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            // We don't have anything to do on the client side
            return null;
        } else {
            // Server side we delegate ticking to our block entity
            //Only call tickServer() if it is part 14
            return (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof LauncherBE be && state.getValue(PART) == LauncherPartIndex.P14) {
                    be.tickServer();
                }
            };
        }
    }

    @Override
    public boolean isOcclusionShapeFullBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return true;
    }
}
