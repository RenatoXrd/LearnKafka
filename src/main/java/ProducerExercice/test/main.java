package ProducerExercice.test;

import ProducerExercice.classes.StructKafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class main {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        StructKafkaProducer producer = new StructKafkaProducer(p);
        producer.add("first_topic","Hello pessoal");
        producer.add("first_topic","Hello pessoal 2");
        producer.add("first_topic","Hello pessoal 3");
        producer.add("first_topic","Hello pessoal 4");
        producer.add("first_topic","Hello pessoal 5");
        producer.flush();
        producer.close();
    }
}
