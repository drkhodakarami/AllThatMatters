package jiraiyah.allthatmatters.block;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.custom.ChunkLoaderBlock;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import jiraiyah.allthatmatters.block.custom.GemCleanserBlock;
import jiraiyah.allthatmatters.block.entity.ChunkLoaderBlockEntity;
import jiraiyah.allthatmatters.block.entity.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.block.entity.GemCleanserBE;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities
{
    public static BlockEntityType<GemCleanserBE> GEM_CLEANSER =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, AllThatMatters.ModID + ":" + GemCleanserBlock.ID.getPath(),
                    FabricBlockEntityTypeBuilder.create(GemCleanserBE::new, ModBlocks.GEM_CLEANSER).build());

    public static BlockEntityType<ChunkLoaderBlockEntity> CHUNK_LOADER =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, AllThatMatters.ModID + ":" + ChunkLoaderBlock.ID.getPath(),
                    FabricBlockEntityTypeBuilder.create(ChunkLoaderBlockEntity::new, ModBlocks.CHUNK_LOADER).build(null));

    public static BlockEntityType<EnderiteShulkerBlockEntity> ENDERITE_SHULKER;

    public ModBlockEntities()
    {
        throw new AssertionError();
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Block Entities");

        //region HANDLING SHULKER BOXES
        List<EnderiteShulkerBoxBlock> boxes = new ArrayList<>();
        boxes.add(ModBlocks.getShulkerBlock(null));
        for (DyeColor color : DyeColor.values())
        {
            boxes.add(ModBlocks.getShulkerBlock(color));
        }

        ENDERITE_SHULKER =
                Registry.register(Registries.BLOCK_ENTITY_TYPE, AllThatMatters.ModID + ":shulker",
                        FabricBlockEntityTypeBuilder.create(EnderiteShulkerBlockEntity::new,
                                boxes.toArray(new EnderiteShulkerBoxBlock[0])).build(null));
        //endregion

        FluidStorage.SIDED.registerForBlockEntity((entity, direction) -> entity.fluidStorage, GEM_CLEANSER);
    }
}