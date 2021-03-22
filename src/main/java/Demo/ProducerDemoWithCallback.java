package Demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        System.out.println(p);
        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(p);
        ProducerRecord<String,String> record = new
                ProducerRecord<String,String>("first_topic","Java mensagem");


        //Por ser assincrono o send não envia os dados, ele acumula
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception==null){
                    System.out.println("TOPICO: " + metadata.topic());
                    System.out.println("PARTITION " +metadata.partition());
                    System.out.println("OFFSET: " +metadata.offset());
                    System.out.println("DATA: " +metadata.timestamp());
//                    logger.info("Topic " + metadata.topic());
                }else{
                    System.out.println("deu ruim");
//                    logger.error("deu ruim");
                }
            }
        });

        //Força os dados a serem produzidos e esvazia o fluxo
        producer.flush();

        producer.close();
    }
}
