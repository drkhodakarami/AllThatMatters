package jiraiyah.allthatmatters.block.entity.custom;

import jiraiyah.allthatmatters.block.ModBlockEntities;
import jiraiyah.allthatmatters.recipe.custom.InfusingStationCraftingRecipe;
import jiraiyah.allthatmatters.screen.custom.InfusingStationScreenHandler;
import jiraiyah.allthatmatters.utils.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

// TODO : Liquid Container
// TODO : Energy Container
public class InfusingStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory
{
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    public static final int TOTAL_SLOTS = 7;
    public static final int DELEGATRE_SIZE = 4;

    public static final int RAW_INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int LIQUID_INPUT_SLOT = 2;
    public static final int UPGRADE_INPUT_SLOT = 3;
    public static final int MAIN_TOOL_SLOT = 4;
    public static final int SECOND_TOOL_SLOT = 5;
    public static final int THIRD_TOOL_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int liquidProgress = 0;
    private int maxLiquidProgress = 144;

    public InfusingStationBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.INFUSING_STATION_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> InfusingStationBlockEntity.this.progress;
                    case 1 -> InfusingStationBlockEntity.this.maxProgress;
                    case 2 -> InfusingStationBlockEntity.this.liquidProgress;
                    case 3 -> InfusingStationBlockEntity.this.maxLiquidProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> InfusingStationBlockEntity.this.progress = value;
                    case 1 -> InfusingStationBlockEntity.this.maxProgress = value;
                    case 2 -> InfusingStationBlockEntity.this.liquidProgress = value;
                    case 3 -> InfusingStationBlockEntity.this.maxLiquidProgress = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems()
    {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
    {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName()
    {
        return Text.translatable("allthatmatters.infusing_station");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player)
    {
        return new InfusingStationScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("atm.infusing_station.progress", progress);
        nbt.putInt("atm.infusing_station.liquid_progress", liquidProgress);
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("atm.infusing_station.progress");
        liquidProgress = nbt.getInt("atm.infusing_station.liquid_progress");
    }

    @Override
    public void markDirty()
    {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt()
    {
        return createNbt();
    }

    public void tick(World world, BlockPos pos, BlockState state)
    {
        if(world.isClient())
            return;

        if(isOutputSlotEmptyOrReceivable())
        {
            if(this.hasRecipe())
            {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished())
                {
                    this.craftItem();
                    this.resetProgress();
                }
            }
            else
            {
                this.resetProgress();
            }
        }
        else
        {
            this.resetProgress();
            markDirty(world, pos, state);
        }

        //TODO : Handle Liquid Recipes
    }

    //region ITEM INFUSING / CRAFTING
    private boolean isOutputSlotEmptyOrReceivable()
    {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    private void resetProgress()
    {
        this.progress = 0;
    }

    private boolean hasCraftingFinished()
    {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress()
    {
        progress++;
    }

    private boolean hasRecipe()
    {
        Optional<RecipeEntry<InfusingStationCraftingRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private void craftItem()
    {
        Optional<RecipeEntry<InfusingStationCraftingRecipe>> recipe = getCurrentRecipe();

        this.removeStack(RAW_INPUT_SLOT, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private Optional<RecipeEntry<InfusingStationCraftingRecipe>> getCurrentRecipe()
    {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++)
        {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(InfusingStationCraftingRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item)
    {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result)
    {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }
    //endregion

    public ItemStack getRenderStack()
    {
        if(this.getStack(OUTPUT_SLOT).isEmpty())
            return this.getStack(RAW_INPUT_SLOT);
        return this.getStack(OUTPUT_SLOT);
    }
}