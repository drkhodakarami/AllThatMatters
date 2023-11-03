package jiraiyah.allthatmatters.block.entity;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.custom.EnderiteShulkerBoxBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities
{
    /*public static BlockEntityType<ShulkerBoxBlockEntity> ENDERITE_SHULKER_BOX_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AllThatMatters.ModID, "enderite_shulker_be"),
                    FabricBlockEntityTypeBuilder.create(ShulkerBoxBlockEntity::new, ModBlocks.ENDERITE_SHULKER_BOX).build());*/

    public static BlockEntityType<EnderiteShulkerBlockEntity> ENDERITE_SHULKER_ENTITY;

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Block Entities for : " + AllThatMatters.ModID);

        List<EnderiteShulkerBoxBlock> boxes = new ArrayList<>();
        EnderiteShulkerBoxBlock baseShulker = createShulkerBoxBlock(null);
        boxes.add(baseShulker);
        for (DyeColor color : DyeColor.values())
        {
            EnderiteShulkerBoxBlock block = createShulkerBoxBlock(color);
            boxes.add(block);
        }

        ENDERITE_SHULKER_ENTITY =
                Registry.register(Registries.BLOCK_ENTITY_TYPE, AllThatMatters.ModID + ":shulker",
                        FabricBlockEntityTypeBuilder.create(EnderiteShulkerBlockEntity::new,
                                boxes.toArray(new EnderiteShulkerBoxBlock[0])).build(null));
    }

    private static EnderiteShulkerBoxBlock createShulkerBoxBlock(DyeColor color)
    {
        AbstractBlock.ContextPredicate contextPredicate = (blockState, blockView, blockPos) ->
        {
            BlockEntity blockEntity = blockView.getBlockEntity(blockPos);
            if (!(blockEntity instanceof ShulkerBoxBlockEntity))
            {
                return true;
            }
            else
            {
                ShulkerBoxBlockEntity shulkerBoxBlockEntity = (ShulkerBoxBlockEntity) blockEntity;
                return shulkerBoxBlockEntity.suffocates();
            }
        };
        return new EnderiteShulkerBoxBlock(color);
    }
}