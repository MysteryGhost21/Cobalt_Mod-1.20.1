package net.ghost.cobalt.block.entity;

import net.ghost.cobalt.block.ModBlocks;
import net.ghost.cobalt.item.ModItems;
import net.ghost.cobalt.screen.OreExtractionMKOneMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OreExtractorMKOneBlockEntityBackup extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public OreExtractorMKOneBlockEntityBackup(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ORE_EXTRACTION_MKONE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> OreExtractorMKOneBlockEntityBackup.this.progress;
                    case 1 -> OreExtractorMKOneBlockEntityBackup.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> OreExtractorMKOneBlockEntityBackup.this.progress = pValue;
                    case 1 -> OreExtractorMKOneBlockEntityBackup.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.cobalt.ore_extractor_mkone");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new OreExtractionMKOneMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("ore_extraction_mkone.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("ore_extraction_mkone.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.IRON_CHUNK.get(), 1);
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        BlockPos spawnPos = this.getBlockPos();
        BlockState blockState = this.getBlockState();

        Direction facingDirection = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);

        double xOffset = 0.0;
        double zOffset = 0.0;

        switch (facingDirection) {
            case NORTH:
                zOffset = .5;
                xOffset = 1.5;
                break;
            case SOUTH:
                zOffset = .5;
                xOffset = -.5;
                break;
            case WEST:
                xOffset = .5;
                zOffset = -.5;
                break;
            case EAST:
                xOffset = .5;
                zOffset = 1.5;
                break;
        }

        ItemEntity itemEntity = new ItemEntity(this.level, spawnPos.getX() + xOffset, spawnPos.getY() + 1, spawnPos.getZ() + zOffset, result);
        itemEntity.setDeltaMovement(0, 0, 0);
        this.level.addFreshEntity(itemEntity);
    }

    private boolean hasRecipe() {
        BlockPos machinePos = this.getBlockPos();
        BlockState blockStateBeneath = this.level.getBlockState(machinePos.below());
        Block blockBeneath = blockStateBeneath.getBlock();

        boolean hasMatchingBlock = blockBeneath == ModBlocks.CONCENTRATED_IRON_ORE.get();

        ItemStack result = new ItemStack(ModItems.IRON_CHUNK.get());
        return hasMatchingBlock && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }


}