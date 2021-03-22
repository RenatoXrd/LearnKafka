package Demo.ConsumerDemoWithThread.classes;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerKafkaStruct {
    private Properties properties;
    private KafkaConsumer<String,String> consumer;

    public ConsumerKafkaStruct(String topic,Properties p){
        this.properties = p;
    }

    public ConsumerKafkaStruct(String topic,String groupId,String auto_offset) {
        this.properties = new Properties();
        this.properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        this.properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        this.properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        this.properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,auto_offset);
        System.out.println("oidsiosiodiodsoifsdiodsiosf");
        consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }

    public KafkaConsumer<String, String> getConsumer() {
        return consumer;
    }


    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void run(){
        Runnable runnable = new ThreadKafkaStruct(this.consumer);
        runnable.run();
    }
}
