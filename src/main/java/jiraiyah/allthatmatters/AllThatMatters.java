package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.command.ModCommands;
import jiraiyah.allthatmatters.datagen.world.ModWorldGeneration;
import jiraiyah.allthatmatters.effect.ModEffects;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItemGroups;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This is the flat world gen custom preset I always use
// 5*minecraft:bedrock,30*minecraft:light_blue_wool,minecraft:light_blue_carpet;minecraft:plains;village

// TODO : Maybe Engineer Helmet Drawing Info in world and screen
// TODO : Cast Press Machine
// TODO : Smelter Machine
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
        ModEffects.register();
    }

    @NotNull
    public static Identifier identifier(@NotNull String path)
    {
        return new Identifier(ModID, path);
    }
}