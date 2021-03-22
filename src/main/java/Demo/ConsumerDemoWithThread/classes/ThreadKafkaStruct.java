package Demo.ConsumerDemoWithThread.classes;

import Demo.ProducerDemoWithCallback;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ThreadKafkaStruct implements Runnable{
    private KafkaConsumer<String,String> consumer;
    private Logger logger = LoggerFactory.getLogger(ThreadKafkaStruct.class);
    public ThreadKafkaStruct(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    logger.info("partition: " + record.partition() + " offset: " + record.offset());
                    logger.info("key: " + record.key() + " value: " + record.value());
                    System.out.println("key: " + record.key() + " value: " + record.value());
                }
            }
        }catch (Exception e){
            logger.info("Sinal enviado");
            consumer.wakeup();
        }finally {
            logger.info("fechado");
            consumer.close();
        }
    }
}
