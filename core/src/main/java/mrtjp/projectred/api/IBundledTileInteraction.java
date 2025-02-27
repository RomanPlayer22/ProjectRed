package mrtjp.projectred.api;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * Class used instead of implementing IBundledTile, where it would be very,
 * very inconvienient (such as when PR adds this functionality to other mods).
 * This class externally handles passing signal info from the tile that
 * *should* have implemented IBundledTile to the requesting device.
 *
 * This should be a standalone class that you have to create on the side,
 * you probably dont want this to be implemented on your tile.
 *
 * Register this class in the Transmission API
 */
public interface IBundledTileInteraction
{
    /**
     * Checks if this interaction can run at the given position.
     * (ie, if the position contains the tile this interaction is meant for)
     *
     * @param world The World
     * @param pos The coordinates of the block being checked
     * @param side The side in question
     * @return True if this interaction should be run at the given location
     */
    boolean isValidInteractionFor(Level world, BlockPos pos, Direction side);

    /**
     * Checks if the block at the given position can be connected to.
     *
     * @param world The World
     * @param pos The coordinates of the block being checked
     * @param side The side the wire is trying to connect to.
     * @return True if the wire should be allowed to connect to the side.
     */
    boolean canConnectBundled(Level world, BlockPos pos, Direction side);

    /**
     * Gets the bundled signal from the tile on the specified side.
     *
     * @param world The World
     * @param pos The coordinates of the block being checked
     * @param side The side we want the signal for.
     * @return The byte array of all the signals.
     */
    byte[] getBundledSignal(Level world, BlockPos pos, Direction side);
}
