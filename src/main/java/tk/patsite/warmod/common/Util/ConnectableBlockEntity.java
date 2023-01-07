package tk.patsite.warmod.common.Util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A block entity which can be connected using a Connector item
 */
public interface ConnectableBlockEntity {
    boolean isParent();

    /**
     * Try connecting this block with another block
     * @param pos The pos to connect to
     * @param world The world to connect in
     * @return true if the connection is successful and false otherwise
     */
    boolean tryConnectWith(BlockPos pos, World world);

    /**
     * @return true if the block is connected and false otherwise
     */
    boolean isConnected();

    /**
     * Disconnects the connected block
     */
    void disconnect();

    /**
     *
     * @return the {@link BlockPos} of the connected block, null otherwise.
     */
    BlockPos getConnectedBlockPos();


    /**
     * Only used internally, sets the other block's connected value. <br>
     * Deprecated since its only used internally.
     * @param pos The pos of the block to set the connected value
     */
    @Deprecated
    void connect(BlockPos pos);
}
