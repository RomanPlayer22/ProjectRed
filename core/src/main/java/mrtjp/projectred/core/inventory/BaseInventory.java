package mrtjp.projectred.core.inventory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

/**
 * Simple extension of default vanilla Inventory class that allows for proper saving and loading
 * to a CompoundNBT. Default implementation does not load items back into their original slots.
 * <p>
 * Use BaseInventory#save and BaseInventory#load instead of Inventory#createTag and Inventory#fromTag
 */
public class BaseInventory extends SimpleContainer {

    private static final String TAG_ITEMS = "items";
    private static final String TAG_SLOT = "slot";
    private static final String TAG_ITEM_COUNT = "item_count";

    public BaseInventory(int size) {
        super(size);
    }

    public void save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt(TAG_SLOT, i);
                stack.save(itemTag);
                list.add(itemTag);
            }
        }
        tag.put(TAG_ITEMS, list);
        tag.putInt(TAG_ITEM_COUNT, list.size());
    }

    public void load(CompoundTag tag) {
        clearContent();
        ListTag list = tag.getList(TAG_ITEMS, 10);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag itemTag = list.getCompound(i);
            int slot = itemTag.contains("index") ? itemTag.getInt("index") : itemTag.getInt(TAG_SLOT); //TODO remove legacy support
            if (slot >= 0 && slot < getContainerSize()) {
                setItem(slot, ItemStack.of(itemTag));
            }
        }
    }

    public static int getItemCount(CompoundTag tag) {
        return tag.contains(TAG_ITEM_COUNT) ? tag.getInt(TAG_ITEM_COUNT) : tag.getList(TAG_ITEMS, 10).size(); //TODO remove legacy support
    }
}
