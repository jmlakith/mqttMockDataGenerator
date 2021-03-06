package main.java;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import static main.java.Client.runIt;

public class ClientExecutor implements Runnable {

    private Integer executorCount = 0;

    private String payload;

    ClientExecutor(String payload){

        this.payload=payload;
    }

    public void publish() {

        String topic = "MQTT Examples";
        String content = this.payload;
        int qos = 2;
        String broker = "tcp://localhost:1883";
        String clientId = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
//            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
//            System.out.println("Connected");
//            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
//            System.out.println("Message published");
            sampleClient.disconnect();
//            System.out.println("Disconnected");
           // System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            while (runIt && payload!=null) {
                Thread.sleep(1000);
                publish();

//                System.out.printf("Executes " + ++executorCount + " times");
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}