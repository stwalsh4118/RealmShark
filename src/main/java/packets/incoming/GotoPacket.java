package packets.incoming;

import packets.Packet;
import packets.buffer.PBuffer;
import data.WorldPosData;

/**
 * Received when an entity has moved to a new position.
 */
public class GotoPacket extends Packet {
    /**
     * The object id of the entity which moved.
     */
    public int objectId;
    /**
     * The new position of the entity.
     */
    public WorldPosData position;

    @Override
    public void deserialize(PBuffer buffer) throws Exception {
        objectId = buffer.readInt();
        position = new WorldPosData().deserialize(buffer);
        int unknown = buffer.readInt();
    }
}