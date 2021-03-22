package Demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        System.out.println(p);
        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(p);
        ProducerRecord<String,String> record = new
                ProducerRecord<String,String>("first_topic","Java mensagem dsjjsda");


        //Por ser assincrono o send não envia os dados, ele acumula
        producer.send(record);

        //Força os dados a serem produzidos e esvazia o fluxo
        producer.flush();

        producer.close();
    }
}
