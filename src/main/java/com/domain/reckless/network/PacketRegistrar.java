package com.domain.reckless.network;

import com.domain.reckless.network.packets.MoveRequest;
import com.domain.reckless.network.packets.MoveResponse;
import com.esotericsoftware.kryo.Kryo;

public class PacketRegistrar {

    public static void registerPackets(Kryo kryo) {
        kryo.register(MoveRequest.class);
        kryo.register(MoveResponse.class);
    }
}
