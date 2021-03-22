package LojaExample.Consumer;

import Demo.ProducerDemoWithCallback;
import LojaExample.Bean.Cliente;
import LojaExample.Elastic.ClienteDao;
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

public class ConsumerSendElastic {
    public static void main(String[] args) {
//      Primeiro configuramos as propriedades do nosso Consumer
        Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);
        String topic = "compras";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"consumer_pedidos");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        // setamos as propriedades
        KafkaConsumer <String,String> consumer = new KafkaConsumer<String, String>(properties);

        // Nos inscrevemos em um topico
        consumer.subscribe(Arrays.asList(topic));
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord <String,String >record : records){
//              Indexando no elastic
                ClienteDao.Put(record.value(),"cliente");
            }
        }
    }
}
