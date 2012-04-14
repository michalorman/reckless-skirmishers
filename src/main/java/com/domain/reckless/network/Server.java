package com.domain.reckless.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class Server extends Listener implements NetworkCommunicator {

    com.esotericsoftware.kryonet.Server server;

    public Server() {
        server = new com.esotericsoftware.kryonet.Server();
        //TODO check if it is valid to register packets before connecting/starting server.
        PacketRegistrar.registerPackets(server.getKryo());
    }

    public void start(int tcpPort) throws IOException {
        server.start();
        server.bind(tcpPort);
        server.addListener(this);
    }

    public void received(Connection sender, Object object) {
        receive(object);
        ResponseAddressee addressee = receive(object);
        Object response = addressee.getResponse();
        switch (addressee) {
            case SENDER:
                send(sender, object);
                break;

            case BROADCAST:
                server.sendToAllTCP(response);
                break;

            case BROADCAST_NO_SENDER:
                server.sendToAllExceptTCP(sender.getID(), response);
                break;

            case NONE:
                break;
        }
    }

    @Override
    public void send(Connection connection, Object object) {
        connection.sendTCP(object);
    }

    @Override
    public ResponseAddressee receive(Object object) {
        //TODO handling packets
        return ResponseAddressee.NONE;
    }
}
