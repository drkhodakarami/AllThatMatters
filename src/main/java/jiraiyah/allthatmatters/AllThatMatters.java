package jiraiyah.allthatmatters;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.command.ModCommands;
import jiraiyah.allthatmatters.datagen.world.ModWorldGeneration;
import jiraiyah.allthatmatters.effect.ModEffects;
import jiraiyah.allthatmatters.fluid.ModFluids;
import jiraiyah.allthatmatters.item.ModItemGroups;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.item.custom.BackpackItem;
import jiraiyah.allthatmatters.networking.ModMessages;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This is the flat world gen custom preset I always use
// 5*minecraft:bedrock,30*minecraft:light_blue_wool,minecraft:light_blue_carpet;minecraft:plains;village

public class AllThatMatters implements ModInitializer
{
    public static final Identifier PACKET_RENAME_BACKPACK = identifier("packet_rename_backpack");
    public static final String ModID = "allthatmatters";
    public static final Logger LOGGER = LoggerFactory.getLogger(ModID);

    @Override
    public void onInitialize()
    {
        LOGGER.info(">>> Initializing");

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

        ServerPlayNetworking.registerGlobalReceiver(PACKET_RENAME_BACKPACK,
                (server, player, handler, buf, responseSender) ->
                {
                    final boolean def = buf.readBoolean();
                    final Hand hand = buf.readEnumConstant(Hand.class);
                    final ItemStack stack = player.getStackInHand(hand);

                    if (!stack.isEmpty() && stack.getItem() instanceof BackpackItem) {
                        if (def) {
                            stack.removeCustomName();
                        } else {
                            final String name = buf.readString(32);
                            stack.setCustomName(Text.of(name));
                        }
                    }
                });
    }

    @NotNull
    public static Identifier identifier(@NotNull String path)
    {
        return new Identifier(ModID, path);
    }

    public static MutableText translate(String key, Object... params) {
        return Text.translatable(ModID + "." + key, params);
    }
}