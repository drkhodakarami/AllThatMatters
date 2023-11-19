package jiraiyah.allthatmatters.utils.fluid;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FluidUtils
{
    public static boolean handleTankTransfer(World world, BlockPos pos, LootableContainerBlockEntity entity, SingleVariantStorage<FluidVariant> tank, int inputSlot, int outputSlot)
    {
        return handleTankTransfer(world,pos,entity,tank,inputSlot,outputSlot,true);
    }

    public static boolean handleTankTransfer(World world, BlockPos pos, LootableContainerBlockEntity entity, SingleVariantStorage<FluidVariant> tank, int inputSlot, int outputSlot, boolean isMillibucket)
    {
        if(FluidUtils.transferToTank(world, pos, entity, tank, inputSlot, outputSlot, isMillibucket))
            return true;
        if(FluidUtils.transferFromTank(world, pos, entity, tank, inputSlot, outputSlot, isMillibucket))
            return true;
        return false;
    }

    public static boolean transferFromTank(World world, BlockPos pos, LootableContainerBlockEntity entity, SingleVariantStorage<FluidVariant> tank, int inputSlot, int outputSlot)
    {
        return transferFromTank(world,pos,entity,tank,inputSlot,outputSlot,true);
    }

    /**
     * Triggers a neighbor update originating from {@code sourcePos} at
     * {@code pos}.
     *
     * @see #updateNeighborsAlways(BlockPos, Block)
     */


    /**
     * This will try to transfer fluid from a bucket full of fluid, into a single variant storage tank inside the block entity
     *
     * @param world the world entity is inside
     * @param pos block position of the entity
     * @param entity the entity itself, it should be a LootableContainerBlockEntity
     * @param tank the SingleVariantStorage of FluidVariant inside the entity
     * @param inputSlot the input slot index to get the full bucket from
     * @param outputSlot the output slot index to put the empty bucket inside
     * @param isMilliBucket are we using fabric fluid units in tank or conversion to milli buckets
     * @return returns success or failure of the transfer attempt
     *
     * @see #convertDropletsToMb(long)
     */
    public static boolean transferFromTank(World world, BlockPos pos, LootableContainerBlockEntity entity, SingleVariantStorage<FluidVariant> tank, int inputSlot, int outputSlot, boolean isMilliBucket)
    {
        // if we can't put the result bucket into the output, then don't do anything and return
        // this will happen when we have empty buckets in output and they don't stack
        if(!isOutputReceivable(entity, outputSlot))
            return false;

        // get the fluid variant from tank
        FluidVariant resource = tank.getResource();

        // convert the bucket into storage of fluid variant, this is for simulation
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(entity.getStack(inputSlot)).find(FluidStorage.ITEM);

        // if the item in stack did not return any fluid storage, or it was empty bucket return
        if(slotStorage == null || resource.isBlank())
            return false;

        try (Transaction transaction = Transaction.openOuter())
        {
            // get the bucket size as milli buckets
            var bucketSize = convertDropletsToMb(FluidConstants.BUCKET);
            // simulate transfer into bucket
            long buckeTransfer = slotStorage.insert(resource, FluidConstants.BUCKET, transaction);
            // actual attempt to transfer the fluid from tank
            long tankTransfer;
            if(isMilliBucket)
                tankTransfer = tank.extract(resource, bucketSize, transaction);
            else
                tankTransfer = tank.extract(resource, FluidConstants.BUCKET, transaction);
            // if the result is equal
            if((isMilliBucket && convertDropletsToMb(buckeTransfer) == tankTransfer) ||
                (!isMilliBucket && buckeTransfer == tankTransfer))
            {
                // finalize transfer from the tank
                transaction.commit();
                // make the bucket filling sound
                SoundEvent sound = FluidVariantAttributes.getFillSound(resource);
                // play the sound
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.BLOCKS, 1, 1, true);
                // remove one item from the input slot
                entity.removeStack(inputSlot, 1);
                // find the bucket associated to the fluid in tank to return it
                Item item = resource.getFluid().getBucketItem();
                // add one fluid bucket to the output slot
                entity.setStack(outputSlot, new ItemStack(item, entity.getStack(outputSlot).getCount() + 1));
                // mark the entity dirty
                entity.markDirty();
                // return success
                return true;
            }
        }
        // we fail for any reason, return failure
        return false;
    }

    public static boolean transferToTank(World world, BlockPos pos, LootableContainerBlockEntity entity, SingleVariantStorage<FluidVariant> tank, int inputSlot, int outputSlot)
    {
        return transferToTank(world,pos,entity,tank,inputSlot,outputSlot,true);
    }

    /**
     * This will try to transfer fluid into an empty bucket, from a single variant storage tank inside the block entity
     *
     * @param world the world entity is inside
     * @param pos block position of the entity
     * @param entity the entity itself, it should be a LootableContainerBlockEntity
     * @param tank the SingleVariantStorage of FluidVariant inside the entity
     * @param inputSlot the input slot index to get the full bucket from
     * @param outputSlot the output slot index to put the empty bucket inside
     * @param isMilliBucket are we using fabric fluid units in tank or conversion to milli buckets
     * @return returns success or failure of the transfer attempt
     *
     * @see #convertDropletsToMb(long)
     */
    public static boolean transferToTank(World world, BlockPos pos, LootableContainerBlockEntity entity, SingleVariantStorage<FluidVariant> tank, int inputSlot, int outputSlot, boolean isMilliBucket)
    {
        // if we can't put the result bucket into the output, then don't do anything and return
        // this will happen when we have a full bucket in output and they don't stack
        if(!isOutputReceivable(entity, outputSlot))
            return false;

        // convert the bucket item to it's fluid format and store it as a storage, this is for simulation
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(entity.getStack(inputSlot)).find(FluidStorage.ITEM);

        // if there is no storage for the fluid inside bucket, return
        if(slotStorage == null)
            return false;

        // get the variant of the fluid inside bucket
        FluidVariant resource = slotStorage.iterator().next().getResource();

        // if it was empty bucket, there is nothing to transfer into tank, return
        if(resource.isBlank())
            return false;

        try (Transaction transaction = Transaction.openOuter())
        {
            // get the bucket size as milli buckets
            var bucketSize = convertDropletsToMb(FluidConstants.BUCKET);
            // simulate transfer from bucket out
            long buckeTransfer = slotStorage.extract(resource, FluidConstants.BUCKET, transaction);
            // actual attempt to transfer the fluid into tank
            long tankTransfer;
            if(isMilliBucket)
                tankTransfer = tank.insert(resource, bucketSize, transaction);
            else
                tankTransfer = tank.insert(resource, FluidConstants.BUCKET, transaction);
            // if the result is equal
            if((isMilliBucket && convertDropletsToMb(buckeTransfer) == tankTransfer) ||
                (!isMilliBucket && buckeTransfer == tankTransfer))
            {
                // finalize transfer to the tank
                transaction.commit();
                // make the bucket emptying sound
                SoundEvent sound = FluidVariantAttributes.getEmptySound(resource);
                // play the sound
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.BLOCKS, 1, 1, true);
                // remove one item from the input slot
                entity.removeStack(inputSlot, 1);
                // add one empty bucket to the output slot
                entity.setStack(outputSlot, new ItemStack(Items.BUCKET, entity.getStack(outputSlot).getCount() + 1));
                // mark the entity dirty
                entity.markDirty();
                // return success
                return true;
            }
        }
        // we fail for any reason, return failure
        return false;
    }

    public static boolean isTankEmpty(SingleVariantStorage<FluidVariant> tank)
    {
        return tank.amount == 0;
    }

    public static boolean isOutputReceivable(LootableContainerBlockEntity entity, int outputSlot)
    {
        return entity.getStack(outputSlot).isEmpty() ||
                entity.getStack(outputSlot).getCount() < entity.getStack(outputSlot).getMaxCount();
    }

    public static boolean isStackEmptyBucket(LootableContainerBlockEntity entity, int slotIndex)
    {
        return entity.getStack(slotIndex).isOf(Items.BUCKET);
    }

    public static boolean isTankReceivable(LootableContainerBlockEntity entity, SingleVariantStorage<FluidVariant> tank, int inputSlot)
    {
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(entity.getStack(inputSlot)).find(FluidStorage.ITEM);
        var size = slotStorage.iterator().next().getAmount();
        return tank.amount <= tank.getCapacity() - size;
    }

    /**
     * Converts the fabric fluid units into milli buckets. Basically, each BUCKET constant 81000L, is 1000 milli buckets
     *
     * @param droplets the droplet amount from the fabric unit system
     * @return the milli bucket unit of the fluid amount
     */
    public static long convertDropletsToMb(long droplets)
    {
        return droplets / FluidConstants.BUCKET * 1000;
    }

    /**
     * Converts the milli bucket unit back to Fabric fluid unit. 1000 milli buckets is equal to 81000L or a BUCKET constant
     *
     * @param mb the milli bucket amount of fluid
     * @return the droplet unity of the fluid amount
     */
    public static long convetMbToDroplets(long mb)
    {
        return mb / 1000 * FluidConstants.BUCKET;
    }
}