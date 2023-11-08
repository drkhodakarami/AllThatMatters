package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.datagen.ModWorldGenerator;
import jiraiyah.allthatmatters.datagen.world.ModWorldGeneration;
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

// 5*minecraft:bedrock,30*minecraft:light_blue_wool,minecraft:light_blue_carpet;minecraft:plains;village
public class AllThatMatters implements ModInitializer 
{
	public static final String ModID = "allthatmatters";
    public static final Logger LOGGER = LoggerFactory.getLogger(ModID);

	@Override
	public void onInitialize() 
	{
		LOGGER.info(">>> Initializing : " + ModID);

		LCLTicker.initialize();
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