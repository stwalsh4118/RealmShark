package potato.control;

import packets.Packet;
import packets.incoming.*;
import packets.incoming.ip.IpAddress;
import potato.model.DataModel;

public class PacketController {

    DataModel model;

    public PacketController(DataModel model) {
        this.model = model;
    }

    public void packets(Packet packet) {
        if (packet instanceof UpdatePacket) {
            UpdatePacket p = (UpdatePacket) packet;
            model.newRealm(p.tiles, p.pos);
            if (p.pos.x != 0 && p.pos.y != 0) {
                model.setPlayerCoords((int) p.pos.x, (int) p.pos.y);
            }
            model.updateLocations(p.tiles, p.newObjects, p.drops);
        } else if (packet instanceof NewTickPacket) {
            NewTickPacket p = (NewTickPacket) packet;
            model.setServerTime(p.serverRealTimeMS);
            model.newTickUpdates(p.status);
        } else if (packet instanceof RealmHeroesLeftPacket) {
            RealmHeroesLeftPacket p = (RealmHeroesLeftPacket) packet;
            model.setHeroesLeft(p.realmHeroesLeft);
        } else if (packet instanceof TextPacket) {
            TextPacket p = (TextPacket) packet;
            model.updateText(p.text, p.objectId);
        } else if (packet instanceof QuestObjectIdPacket) {
            QuestObjectIdPacket p = (QuestObjectIdPacket) packet;
            System.out.println(p);
        } else if (packet instanceof IpAddress) {
            IpAddress p = (IpAddress) packet;
            model.ipChanged(p.ipAddressName, p.srcAddressAsInt);
        } else if (packet instanceof MapInfoPacket) {
            MapInfoPacket p = (MapInfoPacket) packet;
            if (p.displayName.equals("{s.rotmg}")) {
                model.setInRealm(p.realmName, p.seed);
            } else {
                model.setRealmName(p.realmName);
                model.reset();
            }
        } else if (packet instanceof CreateSuccessPacket) {
            CreateSuccessPacket p = (CreateSuccessPacket) packet;
            model.setMyId(p.objectId);
        }
    }
}
