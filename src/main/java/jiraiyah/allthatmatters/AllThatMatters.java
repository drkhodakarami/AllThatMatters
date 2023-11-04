package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.datagen.ModWorldGenerator;
import jiraiyah.allthatmatters.datagen.world.ModWorldGeneration;
import jiraiyah.allthatmatters.item.ModItemGroups;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

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

		ModItemGroups.register();
		ModItems.register();
		ModBlocks.register();
		ModBlockEntities.register();
		ModScreenHandlers.register();
		ModRecipes.register();
		ModWorldGeneration.generateModWorldGen();
	}
}