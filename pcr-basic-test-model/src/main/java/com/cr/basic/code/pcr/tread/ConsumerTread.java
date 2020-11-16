package com.cr.basic.code.pcr.tread;

import com.cr.basic.code.pcr.mqttdemo.ClientMQTT;

public class ConsumerTread implements Runnable {
    @Override
    public void run() {
        try {
            ClientMQTT client = new ClientMQTT();
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
