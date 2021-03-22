package Demo;

import kafka.tools.ConsoleConsumer;
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

public class ConsumerDemo {
    public static void main() {
//      Primeiro configuramos as propriedades do nosso Consumer
        Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);
        String topic = "first_topic";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"my_consumer_java_4");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        // setamos as propriedades
        KafkaConsumer <String,String> consumer = new KafkaConsumer<String, String>(properties);

        // Nos inscrevemos em um topico
        consumer.subscribe(Arrays.asList(topic));
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord <String,String >record : records){
                logger.info("partition: " + record.partition() + " offset: " + record.offset());
                logger.info("key: " + record.key() + " value: " + record.value());
                System.out.println("key: " + record.key() + " value: " + record.value());
            }
        }
    }
}
