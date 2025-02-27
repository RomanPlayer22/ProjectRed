package mrtjp.projectred.transmission;

import codechicken.lib.gui.SimpleCreativeTab;
import codechicken.multipart.api.MultipartType;
import mrtjp.projectred.api.ProjectRedAPI;
import mrtjp.projectred.core.RedstonePropagator;
import mrtjp.projectred.transmission.data.TransmissionItemModelProvider;
import mrtjp.projectred.transmission.data.TransmissionItemTagsProvider;
import mrtjp.projectred.transmission.data.TransmissionLanguageProvider;
import mrtjp.projectred.transmission.data.TransmissionRecipeProvider;
import mrtjp.projectred.transmission.init.TransmissionClientInit;
import mrtjp.projectred.transmission.init.TransmissionParts;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mrtjp.projectred.transmission.ProjectRedTransmission.MOD_ID;

@Mod(MOD_ID)
public class ProjectRedTransmission {

    public static final String MOD_ID = "projectred_transmission";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<MultipartType<?>> PARTS = DeferredRegister.create(MultipartType.MULTIPART_TYPES, MOD_ID);

    public static final SimpleCreativeTab TRANSMISSION_GROUP = new SimpleCreativeTab(MOD_ID, WireType.RED_ALLOY::makeStack);

    static {
        ProjectRedAPI.transmissionAPI = TransmissionAPI.INSTANCE;

        TransmissionParts.register();
    }

    public ProjectRedTransmission() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onGatherDataEvent);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> TransmissionClientInit::init);

        ITEMS.register(modEventBus);
        PARTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(this::onServerStartEvent);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void onGatherDataEvent(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new TransmissionItemModelProvider(generator, fileHelper));
            generator.addProvider(new TransmissionLanguageProvider(generator));
        }
        if (event.includeServer()) {
            generator.addProvider(new TransmissionItemTagsProvider(generator, fileHelper));
            generator.addProvider(new TransmissionRecipeProvider(generator));
        }

    }

    private void onServerStartEvent(final ServerAboutToStartEvent event) {
        RedstonePropagator.resetPowerFlags();
    }
}
