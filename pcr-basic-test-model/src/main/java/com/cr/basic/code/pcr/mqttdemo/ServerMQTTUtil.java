package com.cr.basic.code.pcr.mqttdemo;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Title:Server 这是发送消息的服务端
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 *
 * @author rao
 */
public class ServerMQTTUtil {
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://127.0.0.1:1883";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server11";

    private MqttClient client;
    private MqttTopic mqttTopic;
    private String userName = "paho";
    private String passWord = "";

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTTUtil(String topic) throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect(topic);
    }

    /**
     *  用来连接服务器
     */
    private void connect(String topic) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
//          client.setCallback(new PushCallback());
            client.connect(options);
            mqttTopic = client.getTopic(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送消息并获取回执
    public void publish(MqttMessage message) throws MqttPersistenceException,
            MqttException, InterruptedException {
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
        System.out.println("messageId:" + token.getMessageId());
        token.getResponse();
        if (client.isConnected())
            client.disconnect(10000);
        System.out.println("Disconnected: delivery token \"" + token.hashCode()
                + "\" received: " + token.isComplete());
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public MqttTopic getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(MqttTopic mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

}
