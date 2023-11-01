package jiraiyah.allthatmatters.block.entity;

import jiraiyah.allthatmatters.AllThatMatters;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities
{
    /*public static final BlockEntityType<StripperBlockEntity> STRIPPER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(StripBlock.ModID, "stripper_be"),
                    FabricBlockEntityTypeBuilder.create(StripperBlockEntity::new, ModBlocks.STRIPPER_BLOCK).build());*/

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Block Entities for : " + AllThatMatters.ModID);
    }
}