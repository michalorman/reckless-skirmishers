package com.domain.reckless.network;

//TODO check if this is thread safe with KryoNet
public enum ResponseAddressee {
    NONE, SENDER, BROADCAST, BROADCAST_NO_SENDER;

    private Object response;

    public void setResponse(Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }
}
