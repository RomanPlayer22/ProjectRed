package mrtjp.projectred.expansion.init;

import codechicken.multipart.api.MultipartType;
import mrtjp.projectred.expansion.inventory.container.AutoCrafterContainer;
import mrtjp.projectred.expansion.inventory.container.BatteryBoxContainer;
import mrtjp.projectred.expansion.inventory.container.ChargingBenchContainer;
import mrtjp.projectred.expansion.inventory.container.ProjectBenchContainer;
import mrtjp.projectred.expansion.part.FramePart;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static mrtjp.projectred.expansion.ProjectRedExpansion.MOD_ID;
import static mrtjp.projectred.expansion.init.ExpansionBlocks.*;
import static mrtjp.projectred.expansion.init.ExpansionItems.*;

@ObjectHolder(MOD_ID)
public class ExpansionReferences {

    // Blocks
    @ObjectHolder(ID_FRAME)
    public static Block FRAME_BLOCK = null;

    @ObjectHolder(ID_PROJECT_BENCH)
    public static Block PROJECT_BENCH_BLOCK = null;
    @ObjectHolder(ID_BATTERY_BOX)
    public static Block BATTERY_BOX_BLOCK = null;
    @ObjectHolder(ID_CHARGING_BENCH)
    public static Block CHARGING_BENCH_BLOCK = null;
    @ObjectHolder(ID_AUTO_CRAFTER)
    public static Block AUTO_CRAFTER_BLOCK = null;
    @ObjectHolder(ID_FIRE_STARTER)
    public static Block FIRE_STARTER_BLOCK = null;
    @ObjectHolder(ID_FRAME_MOTOR)
    public static Block FRAME_MOTOR_BLOCK = null;
    @ObjectHolder(ID_FRAME_ACTUATOR)
    public static Block FRAME_ACTUATOR_BLOCK = null;
//    @ObjectHolder(ID_INDUCTION_FURNACE)
//    public static Block INDUCTION_FURNACE_BLOCK = null;
//    @ObjectHolder(ID_TELEPOSER)
//    public static Block TELEPOSER_BLOCK = null;

    // Tiles
    @ObjectHolder(ID_PROJECT_BENCH)
    public static BlockEntityType<?> PROJECT_BENCH_TILE = null;
    @ObjectHolder(ID_BATTERY_BOX)
    public static BlockEntityType<?> BATTERY_BOX_TILE = null;
    @ObjectHolder(ID_CHARGING_BENCH)
    public static BlockEntityType<?> CHARGING_BENCH_TILE = null;
    @ObjectHolder(ID_AUTO_CRAFTER)
    public static BlockEntityType<?> AUTO_CRAFTER_TILE = null;
    @ObjectHolder(ID_FIRE_STARTER)
    public static BlockEntityType<?> FIRE_STARTER_TILE = null;
    @ObjectHolder(ID_FRAME_MOTOR)
    public static BlockEntityType<?> FRAME_MOTOR_TILE = null;
    @ObjectHolder(ID_FRAME_ACTUATOR)
    public static BlockEntityType<?> FRAME_ACTUATOR_TILE = null;
//    @ObjectHolder(ID_INDUCTION_FURNACE)
//    public static TileEntityType<?> INDUCTION_FURNACE_TILE = null;
//    @ObjectHolder(ID_TELEPOSER)
//    public static TileEntityType<?> TELEPOSER_TILE = null;

    // Containers
    @ObjectHolder(ID_PROJECT_BENCH)
    public static MenuType<ProjectBenchContainer> PROJECT_BENCH_CONTAINER = null;
    @ObjectHolder(ID_BATTERY_BOX)
    public static MenuType<BatteryBoxContainer> BATTERY_BOX_CONTAINER = null;
    @ObjectHolder(ID_AUTO_CRAFTER)
    public static MenuType<AutoCrafterContainer> AUTO_CRAFTER_CONTAINER = null;
    @ObjectHolder(ID_CHARGING_BENCH)
    public static MenuType<ChargingBenchContainer> CHARGING_BENCH_CONTAINER = null;

    // Items
    @ObjectHolder(ID_RECIPE_PLAN)
    public static Item RECIPE_PLAN_ITEM = null;
    @ObjectHolder(ID_BATTERY)
    public static Item BATTERY_ITEM = null;
    @ObjectHolder(ID_EMPTY_BATTERY)
    public static Item EMPTY_BATTERY_ITEM = null;
    @ObjectHolder(ID_ELECTRIC_SCREWDRIVER)
    public static Item ELECTRIC_SCREWDRIVER_ITEM = null;

    // Parts
    @ObjectHolder(ID_FRAME)
    public static MultipartType<FramePart> FRAME_PART = null;
}
