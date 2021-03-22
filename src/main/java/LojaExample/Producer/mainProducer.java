package LojaExample.Producer;

import LojaExample.Bean.Cliente;
import LojaExample.Bean.Produto;
import ProducerExercice.classes.StructKafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class mainProducer {
    public static void main(String[] args) {
        Produto p = new Produto("Computador",2000);
        Produto p2 = new Produto("Celular",4000);
        Produto p3 = new Produto("Tablet",1990.90);
        Produto p4 = new Produto("Pera",1);
        Cliente c = new Cliente("Maria","3244343",23232, Arrays.asList(p,p2));
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        ProducerExercice.classes.StructKafkaProducer producer = new StructKafkaProducer(properties);
        producer.add("compras",c.toJson());
        producer.flush();
        producer.close();
    }
}
