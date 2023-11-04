package jiraiyah.allthatmatters.compat;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

// Done with the help :
// https://github.com/TeamGalacticraft/Galacticraft/tree/main (MIT License)
public class InfusingStationCategory implements DisplayCategory<BasicDisplay>
{
    public static final Identifier TEXTURE =
            new Identifier(AllThatMatters.ModID, "textures/gui/container/infusing_station.png");

    public static final CategoryIdentifier<InfusingStationDisplay> ADVANCE_INFUSING =
            CategoryIdentifier.of(AllThatMatters.ModID, "advance_infusing");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier()
    {
        return ADVANCE_INFUSING;
    }

    @Override
    public Text getTitle()
    {
        return Text.translatable("infusing_station.advance_infusing");
    }

    @Override
    public Renderer getIcon()
    {
        return EntryStacks.of(ModBlocks.INFUSING_STATION.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds)
    {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 86, startPoint.y + 15))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 86, startPoint.y + 60))
                .markOutput().entries(display.getOutputEntries().get(0)));


        return widgets;
    }

    @Override
    public int getDisplayHeight()
    {
        return 90;
    }
}