package mrtjp.projectred.transmission.item;

import codechicken.multipart.api.ItemMultipart;
import codechicken.multipart.api.part.MultiPart;
import mrtjp.projectred.transmission.ProjectRedTransmission;
import mrtjp.projectred.transmission.WireType;
import mrtjp.projectred.transmission.part.BaseWirePart;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class CenterWirePartItem extends ItemMultipart {

    private final WireType type;

    public CenterWirePartItem(WireType type) {
        super(new Item.Properties().tab(ProjectRedTransmission.TRANSMISSION_GROUP));
        this.type = type;
    }

    public WireType getType() {
        return type;
    }

    @Override
    public MultiPart newPart(UseOnContext context) {
        Direction side = context.getClickedFace();
        BaseWirePart wire = type.newPart();
        wire.preparePlacement(side);
        return wire;
    }
}
