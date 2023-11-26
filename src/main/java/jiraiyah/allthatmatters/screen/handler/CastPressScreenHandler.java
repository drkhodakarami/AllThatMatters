package jiraiyah.allthatmatters.screen.handler;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.networking.NetworkSide;
import io.github.cottonmc.cotton.gui.networking.ScreenNetworking;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.block.ModBlocks;
import jiraiyah.allthatmatters.block.entity.CastPressBE;
import jiraiyah.allthatmatters.item.ModItems;
import jiraiyah.allthatmatters.screen.ModScreenHandlers;
import jiraiyah.allthatmatters.utils.CastType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;

public class CastPressScreenHandler extends SyncedGuiDescription
{
    private final ScreenHandlerContext context;

    public CastPressBE loaderEntity;

    private static final Identifier BUTTON_CLICK_MESSAGE = AllThatMatters.identifier("cast_press");

    public CastPressScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(ModScreenHandlers.CAST_PRESS_SCREEN_HANDLER, syncId, playerInventory, getBlockInventory(context, CastPressBE.TOTAL_SLOTS), null);

        this.context = context;

        this.context.run((world, pos) -> loaderEntity = (CastPressBE) world.getBlockEntity(pos));

        this.setTitleVisible(false);

        WPlainPanel  root = new WPlainPanel ();
        setRootPanel(root);

        root.setSize(190, 178);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot base_input_slot = WItemSlot.of(blockInventory, CastPressBE.BASE_INPUT_SLOT);
        WItemSlot base_output_slot = WItemSlot.of(blockInventory, CastPressBE.BASE_OUTPUT_SLOT);

        base_input_slot.setInputFilter(itemStack -> itemStack.isOf(Items.GOLD_INGOT) ||
                                                    itemStack.isIn(ItemTags.PLANKS));
        base_output_slot.setInsertingAllowed(false);

        ScreenNetworking.of(this, NetworkSide.SERVER).receive(BUTTON_CLICK_MESSAGE, buf ->
        {
            this.loaderEntity.pressTheCast(buf.readEnumConstant(CastType.class));
        });

        WSprite littleArrowDown = new WSprite(AllThatMatters.identifier("textures/gui/little_arrow_down.png"));

        WButton axeCast = new WButton(new ItemIcon(ModItems.CAST_AXE))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.axe.button"));
            }
        };

        WButton bindingCast = new WButton(new ItemIcon(ModItems.CAST_BINDING))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.binding.button"));
            }
        };

        WButton gearCast = new WButton(new ItemIcon(ModItems.CAST_GEAR))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.gear.button"));
            }
        };

        WButton hammerCast = new WButton(new ItemIcon(ModItems.CAST_HAMMER))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.hammer.button"));
            }
        };

        WButton handleCast = new WButton(new ItemIcon(ModItems.CAST_HANDLE))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.handle.button"));
            }
        };

        WButton gemCast = new WButton(new ItemIcon(ModItems.CAST_GEM))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.gem.button"));
            }
        };

        WButton hoeCast = new WButton(new ItemIcon(ModItems.CAST_HOE))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.hoe.button"));
            }
        };

        WButton ingotCast = new WButton(new ItemIcon(ModItems.CAST_INGOT))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.ingot.button"));
            }
        };

        WButton nuggetCast = new WButton(new ItemIcon(ModItems.CAST_NUGGET))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.nugget.button"));
            }
        };

        WButton pickaxeCast = new WButton(new ItemIcon(ModItems.CAST_PICKAXE))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.pickaxe.button"));
            }
        };

        WButton plateCast = new WButton(new ItemIcon(ModItems.CAST_PLATE))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.plate.button"));
            }
        };

        WButton rodCast = new WButton(new ItemIcon(ModItems.CAST_ROD))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.rod.button"));
            }
        };

        WButton shovelCast = new WButton(new ItemIcon(ModItems.CAST_SHOVEL))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.shovel.button"));
            }
        };

        WButton swordCast = new WButton(new ItemIcon(ModItems.CAST_SWORD))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.sword.button"));
            }
        };

        WButton wireCast = new WButton(new ItemIcon(ModItems.CAST_WIRE))
        {
            @Override
            public void addTooltip(TooltipBuilder builder)
            {
                builder.add(AllThatMatters.translate("cast.press.wire.button"));
            }
        };

        axeCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.AXE);
        }));
        bindingCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.BINDING);
        }));
        gearCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.GEAR);
        }));
        hammerCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.HAMMER);
        }));
        handleCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.HANDLE);
        }));
        gemCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.GEM);
        }));
        hoeCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.HOE);
        }));
        ingotCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.INGOT);
        }));
        nuggetCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.NUGGET);
        }));
        pickaxeCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.PICKAXE);
        }));
        plateCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.PLATE);
        }));
        rodCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.ROD);
        }));
        shovelCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.SHOVEL);
        }));
        swordCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.SWORD);
        }));
        wireCast.setOnClick(() -> ScreenNetworking.of(this, NetworkSide.CLIENT).send(BUTTON_CLICK_MESSAGE, buf ->
        {
            buf.writeEnumConstant(CastType.WIRE);
        }));

        root.add(axeCast, 7, 10, 20, 20);
        root.add(bindingCast, 7, 32, 20, 20);
        root.add(gearCast, 7, 54, 20, 20);

        root.add(hammerCast, 29, 10, 20, 20);
        root.add(handleCast, 29, 32, 20, 20);
        root.add(gemCast, 29, 54, 20, 20);

        root.add(hoeCast, 51, 10, 20, 20);
        root.add(ingotCast, 51, 32, 20, 20);
        root.add(nuggetCast, 51, 54, 20, 20);

        root.add(pickaxeCast, 73, 10, 20, 20);
        root.add(plateCast, 73, 32, 20, 20);
        root.add(rodCast, 73, 54, 20, 20);

        root.add(shovelCast, 95, 10, 20, 20);
        root.add(swordCast, 95, 32, 20, 20);
        root.add(wireCast, 95, 54, 20, 20);

        root.add(base_input_slot, 150, 10);
        root.add(base_output_slot, 150, 55);

        root.add(littleArrowDown, 154, 38, 9, 8);

        root.add(this.createPlayerInventoryPanel(), 7, 85);

        root.validate(this);
    }

    @Override
    public boolean canUse(PlayerEntity entity)
    {
        return canUse(this.context, entity, ModBlocks.CAST_PRESS);
    }
}