package jiraiyah.allthatmatters.datagen.world;

public class ModWorldGeneration
{
    public static void generateModWorldGen()
    {
        ModOreGeneration.generateOres();
        ModTreeGeneration.generateTrees();
    }
}