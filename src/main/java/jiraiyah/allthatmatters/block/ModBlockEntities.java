package jiraiyah.allthatmatters.block;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import jiraiyah.allthatmatters.block.entity.custom.EnderiteShulkerBlockEntity;
import jiraiyah.allthatmatters.block.entity.custom.InfusingStationBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import team.reborn.energy.api.EnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities
{
    public static BlockEntityType<InfusingStationBlockEntity> INFUSING_STATION_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AllThatMatters.ModID, "infusing_station_be"),
                    FabricBlockEntityTypeBuilder.create(InfusingStationBlockEntity::new, ModBlocks.INFUSING_STATION).build());

    public static BlockEntityType<EnderiteShulkerBlockEntity> ENDERITE_SHULKER_ENTITY;

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Block Entities for : " + AllThatMatters.ModID);

        //region HANDLING SHULKER BOXES
        List<EnderiteShulkerBoxBlock> boxes = new ArrayList<>();
        boxes.add(ModBlocks.getShulkerBlock(null));
        for (DyeColor color : DyeColor.values())
        {
            boxes.add(ModBlocks.getShulkerBlock(color));
        }

        ENDERITE_SHULKER_ENTITY =
                Registry.register(Registries.BLOCK_ENTITY_TYPE, AllThatMatters.ModID + ":shulker",
                        FabricBlockEntityTypeBuilder.create(EnderiteShulkerBlockEntity::new,
                                boxes.toArray(new EnderiteShulkerBoxBlock[0])).build(null));
        //endregion

        EnergyStorage.SIDED.registerForBlockEntity((entity, direction) -> entity.energyStorage, INFUSING_STATION_BLOCK_ENTITY);
        FluidStorage.SIDED.registerForBlockEntity((entity, direction) -> entity.fluidStorage, INFUSING_STATION_BLOCK_ENTITY);
    }
}