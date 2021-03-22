package Demo.ConsumerDemoWithThread;

import Demo.ConsumerDemoWithThread.classes.ConsumerKafkaStruct;
import Demo.ProducerDemoWithCallback;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemoWithThread {
    public static void main(String[] args) {
//      Primeiro configuramos as propriedades do nosso Consumer
//        Logger logger = LoggerFactory.getLogger(ConsumerDemoWithThread.class);
        String topic = "first_topic";

        ConsumerKafkaStruct consumer = new ConsumerKafkaStruct(topic,"my_consumer_java_4","earliest");
        consumer.run();

    }
}


