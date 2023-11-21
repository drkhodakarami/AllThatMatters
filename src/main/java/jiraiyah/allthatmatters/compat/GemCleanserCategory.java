package jiraiyah.allthatmatters.compat;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.recipe.ModRecipes;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.fluid.Fluids;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

// Done with the help :
// https://github.com/TeamGalacticraft/Galacticraft/tree/main (MIT License)
public class GemCleanserCategory implements DisplayCategory<BasicDisplay>
{
    public static final Identifier TEXTURE =
            AllThatMatters.identifier("textures/gui/gem_cleanser.png");

    public static final CategoryIdentifier<GemCleanserDisplay> GEM_CLEANSER =
            CategoryIdentifier.of(AllThatMatters.ModID, ModRecipes.GEM_CLEANSER_ID);

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier()
    {
        return GEM_CLEANSER;
    }

    @Override
    public Text getTitle()
    {
        return AllThatMatters.translate(ModRecipes.GEM_CLEANSER_ID);
    }

    @Override
    public Renderer getIcon()
    {
        return EntryStacks.of(ModBlocks.GEM_CLEANSER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds)
    {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 117, startPoint.y + 15))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 117, startPoint.y + 60))
                .markOutput().entries(display.getOutputEntries().get(0)));

        var slot = Widgets.createSlot(new Rectangle(startPoint.x + 85, startPoint.y + 14, 18, 63));
        slot.entry(EntryStacks.of(Fluids.WATER, FluidConstants.BUCKET * 20));
        slot.disableBackground();

        widgets.add(slot);

        widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 86, startPoint.y + 15, 16, 61),
                AllThatMatters.translate("water")));

        return widgets;
    }

    @Override
    public int getDisplayHeight()
    {
        return 90;
    }
}