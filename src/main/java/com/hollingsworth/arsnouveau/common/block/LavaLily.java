package com.hollingsworth.arsnouveau.common.block;

import com.hollingsworth.arsnouveau.common.lib.LibBlockNames;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class LavaLily extends BushBlock {
    protected static final VoxelShape LILY_PAD_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.5D, 16.0D);

    public LavaLily() {
        super(ModBlock.defaultProperties().notSolid());
        setRegistryName(LibBlockNames.LAVA_LILY);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return LILY_PAD_AABB;
    }

    public static final IntegerProperty LOC = IntegerProperty.create("loc", 0, 2);

    public BlockState getState(World world, BlockPos pos){
        BlockState state = getDefaultState();
        if(world.getBlockState(pos.down()).getBlock() == Blocks.STONE)
            state = state.with(LOC, 0);
        if(world.getBlockState(pos.down()).getBlock() == Blocks.MAGMA_BLOCK)
            state = state.with(LOC, 1);
        if(world.getBlockState(pos.down()).getBlock() == Blocks.LAVA)
            state = state.with(LOC, 2);
        return state;
    }


    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getState(context.getWorld(), context.getPos());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LOC);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return super.isValidPosition(state, worldIn, pos);
    }

    @Override
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        FluidState fluidstate = worldIn.getFluidState(pos);
        FluidState fluidstate1 = worldIn.getFluidState(pos.up());
        return fluidstate1.getFluid() == Fluids.EMPTY;
    }
}
