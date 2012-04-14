package com.domain.reckless.network;

import com.esotericsoftware.kryonet.Connection;

public class Client implements NetworkCommunicator {
    com.esotericsoftware.kryonet.Client client;

    public Client() {
        client = new com.esotericsoftware.kryonet.Client();
        PacketRegistrar.registerPackets(client.getKryo());
    }

    @Override
    public void send(Connection connection, Object object) {

    }

    @Override
    public ResponseAddressee receive(Object object) {
        return ResponseAddressee.NONE;
    }
}
