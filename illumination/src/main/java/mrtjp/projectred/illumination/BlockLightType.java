package mrtjp.projectred.illumination;

import codechicken.lib.colour.EnumColour;
import mrtjp.projectred.illumination.block.IllumarLampBlock;
import mrtjp.projectred.illumination.tile.IllumarLampTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public enum BlockLightType {
    ILLUMAR_LAMP("illumar_lamp", "Illumar Lamp", IllumarLampBlock::new, IllumarLampTile::new);

    private final String unlocalName;
    private final String localName;
    private final BiFunction<Integer, Boolean, Block> blockFactory;
    private final BlockLightBlockEntityFactory tileFactory;

    private final ArrayList<Supplier<Block>> blockSupplier = new ArrayList<>();
    private final ArrayList<Supplier<Block>> invertedBlockSupplier = new ArrayList<>();
    private final ArrayList<Supplier<Item>> itemBlockSupplier = new ArrayList<>();
    private final ArrayList<Supplier<Item>> invertedItemBlockSupplier = new ArrayList<>();
    private final ArrayList<Supplier<BlockEntityType<?>>> tileEntityTypeSupplier = new ArrayList<>();
    private final ArrayList<Supplier<BlockEntityType<?>>> invertedTileEntityTypeSupplier = new ArrayList<>();

    BlockLightType(String unlocalName, String localName, BiFunction<Integer, Boolean, Block> blockFactory, BlockLightBlockEntityFactory tileFactory) {
        this.unlocalName = unlocalName;
        this.localName = localName;
        this.blockFactory = blockFactory;
        this.tileFactory = tileFactory;
    }

    public void registerBlocks(DeferredRegister<Block> blockRegistry, DeferredRegister<Item> itemRegistry, DeferredRegister<BlockEntityType<?>> tileRegistry) {
        // Non-inverted
        for (int color = 0; color < 16; color++) {
            final int colorFinal = color;
            String registryID = getRegistryID(color, false);

            blockSupplier.add(color, blockRegistry.register(registryID, () -> blockFactory.apply(colorFinal, false)));
            itemBlockSupplier.add(color, itemRegistry.register(registryID, () -> new BlockItem(getBlock(colorFinal, false), new Item.Properties().tab(ProjectRedIllumination.ILLUMINATION_GROUP))));
            tileEntityTypeSupplier.add(color, tileRegistry.register(registryID, () -> BlockEntityType.Builder.of(
                            (pos, state) -> tileFactory.create(colorFinal, false, pos, state),
                            getBlock(colorFinal, false))
                    .build(null)));
        }

        // Inverted
        for (int color = 0; color < 16; color++) {
            final int colorFinal = color;
            String invertedRegistryID = getRegistryID(color, true);

            invertedBlockSupplier.add(color, blockRegistry.register(invertedRegistryID, () -> blockFactory.apply(colorFinal, true)));
            invertedItemBlockSupplier.add(color, itemRegistry.register(invertedRegistryID, () -> new BlockItem(getBlock(colorFinal, true), new Item.Properties().tab(ProjectRedIllumination.ILLUMINATION_GROUP))));
            invertedTileEntityTypeSupplier.add(color, tileRegistry.register(invertedRegistryID, () -> BlockEntityType.Builder.of(
                            (pos, state) -> tileFactory.create(colorFinal, true, pos, state),
                            getBlock(colorFinal, true))
                    .build(null)));
        }
    }

    public Block getBlock(int color, boolean inverted) {
        return inverted ? invertedBlockSupplier.get(color).get() : blockSupplier.get(color).get();
    }

    public BlockEntityType<?> getTileEntityType(int color, boolean inverted) {
        return inverted ? invertedTileEntityTypeSupplier.get(color).get() : tileEntityTypeSupplier.get(color).get();
    }

    public String getLocalBaseName() {
        return localName;
    }

    public String getRegistryID(int color, boolean inverted) {
        return EnumColour.values()[color].getSerializedName().toLowerCase() + (inverted ? "_inverted" : "") + "_" + unlocalName;
    }

    @FunctionalInterface
    private interface BlockLightBlockEntityFactory {

        BlockEntity create(int color, boolean inverted, BlockPos pos, BlockState state);
    }
}
