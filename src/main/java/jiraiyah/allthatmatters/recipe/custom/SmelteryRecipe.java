package jiraiyah.allthatmatters.recipe.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import jiraiyah.allthatmatters.block.entity.SmelteryBE;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class SmelteryRecipe implements Recipe<Inventory>
{
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public SmelteryRecipe(List<Ingredient> ingredients, ItemStack itemStack)
    {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(Inventory inventory, World world)
    {
        if (world.isClient())
            return false;

        return recipeItems.get(0).test(inventory.getStack(SmelteryBE.BASE_INPUT_SLOT)) &&
               recipeItems.get(1).test(inventory.getStack(SmelteryBE.CAST_SLOT));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager)
    {
        return output;
    }

    @Override
    public boolean fits(int width, int height)
    {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager)
    {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients()
    {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.SMELTERY_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.SMELTERY_TYPE;
    }

    public static class Serializer implements RecipeSerializer<SmelteryRecipe>
    {
        public static final Codec<SmelteryRecipe> CODEC = RecordCodecBuilder.create(in ->
                in.group(validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9)
                                .fieldOf("ingredients").forGetter(SmelteryRecipe::getIngredients),
                        RecipeCodecs.CRAFTING_RESULT.fieldOf("output").forGetter(r -> r.output)
                ).apply(in, SmelteryRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max)
        {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ?
                            DataResult.error(() -> "Recipe has too many ingredients!") :
                            DataResult.success(list)),
                    list -> list.isEmpty() ?
                        DataResult.error(() -> "Recipe has no ingredients!") :
                        DataResult.success(list));
        }

        @Override
        public Codec<SmelteryRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public SmelteryRecipe read(PacketByteBuf buf)
        {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
                inputs.set(i, Ingredient.fromPacket(buf));

            ItemStack output = buf.readItemStack();
            return new SmelteryRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, SmelteryRecipe recipe)
        {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients())
                ingredient.write(buf);

            buf.writeItemStack(recipe.getResult(null));
        }
    }
}