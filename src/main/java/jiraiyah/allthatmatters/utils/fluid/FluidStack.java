package jiraiyah.allthatmatters.utils.fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public class FluidStack
{
    public FluidVariant fluidVariant;
    public long amount;

    public FluidStack(FluidVariant variant, long amount)
    {
        this.fluidVariant = variant;
        this.amount = amount;
    }

    public FluidVariant getFluidVariant()
    {
        return fluidVariant;
    }

    public void setFluidVariant(FluidVariant variant)
    {
        this.fluidVariant = variant;
    }

    public long getAmount()
    {
        return amount;
    }

    public void setAmount(long amount)
    {
        this.amount = amount;
    }

    public static long convertDropletsToMb(long droplets)
    {
        return droplets / FluidConstants.BUCKET * 1000;
    }

    public static long convetMbToDroplets(long mb)
    {
        return mb / 1000 * FluidConstants.BUCKET;
    }
}