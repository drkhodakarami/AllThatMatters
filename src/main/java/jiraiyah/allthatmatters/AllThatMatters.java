package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.command.ModCommands;
import jiraiyah.allthatmatters.datagen.world.ModWorldGeneration;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItemGroups;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 5*minecraft:bedrock,30*minecraft:light_blue_wool,minecraft:light_blue_carpet;minecraft:plains;village
public class AllThatMatters implements ModInitializer
{
    public static final String ModID = "allthatmatters";
    public static final Logger LOGGER = LoggerFactory.getLogger(ModID);

    @Override
    public void onInitialize()
    {
        LOGGER.info(">>> Initializing : " + ModID);

        /*ServerWorldEvents.LOAD.register((world, entity) ->
        {
            var server = world.getOverworld().getServer();
            ModStatsHandler stats = ModStatsHandler.getServerState(server);
            if (!stats.worldCreated)
            {
                BlockState blockState;

                var blockStates = BlockPos.iterate(-500, 40, -500, 500, 130, 500);
                var overworld = world.getOverworld();

                for (var position : blockStates)
                {
                    blockState = overworld.getBlockState(position);
                    if (blockState.isOf(Blocks.GRASS) || blockState.isOf(Blocks.TALL_GRASS))
                        overworld.setBlockState(position, Blocks.AIR.getDefaultState());
                }
                stats.worldCreated = true;
                stats.markDirty();
            }
        });*/

        ModTicker.initialize();
        ModItems.register();
        ModBlocks.register();
        ModItemGroups.register();
        ModCommands.register();
        ModBlockEntities.register();
        ModScreenHandlers.register();
        ModRecipes.register();
        ModWorldGeneration.generateModWorldGen();
        ModMessages.registerC2SPackets();

        ModFluids.register();
    }

    @NotNull
    public static Identifier identifier(@NotNull String path)
    {
        return new Identifier(ModID, path);
    }
}