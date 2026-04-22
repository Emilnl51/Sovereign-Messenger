package com.sovereignmessenger.client.core;

import com.sovereignmessenger.common.NetworkPacket;

public interface ViewUpdateListener {
    public void handlePacket(NetworkPacket packet);
}
