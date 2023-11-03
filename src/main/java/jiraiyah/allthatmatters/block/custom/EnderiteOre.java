package jiraiyah.allthatmatters.block.custom;

import jiraiyah.allthatmatters.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class EnderiteOre extends Block
{
    public EnderiteOre(Settings settings)
    {
        super(settings);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion)
    {
        world.setBlockState(pos, ModBlocks.ORE_ENDERITE.getDefaultState());
    }
}