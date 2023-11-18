package jiraiyah.allthatmatters.datagen.world;

public class ModWorldGeneration
{
    public ModWorldGeneration()
    {
        throw new AssertionError();
    }

    public static void generateModWorldGen()
    {
        ModOreGeneration.generateOres();
        ModTreeGeneration.generateTrees();
    }
}