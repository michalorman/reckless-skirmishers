package com.domain.reckless.network;

import com.esotericsoftware.kryonet.Connection;

public interface NetworkCommunicator {

    /* Sends packet to the given connection */
    void send(Connection connection, Object object);

    /* Returns response packet, otherwise null. */
    ResponseAddressee receive(Object object);
}
