package com.cr.basic.code.pcr.tread;

import com.cr.basic.code.pcr.mqttdemo.ServerMQTT;
import com.cr.basic.code.pcr.mqttdemo.ServerMQTTUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PushMessageTread implements Runnable {
    private Integer count;

    public PushMessageTread(Integer count) {
        this.count = count;
    }

    @Override
    public void run() {
        try {
            ServerMQTT server = new ServerMQTT();
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(1);
            mqttMessage.setRetained(false);
            mqttMessage.setPayload(("Message" + count + ",推送时间 = " + System.currentTimeMillis()).getBytes());
            server.publish(null, mqttMessage);
            log.info("=====>>Message{},tread name={},推送时间:{}", count, Thread.currentThread().getName(), System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
