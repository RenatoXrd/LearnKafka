package LojaExample.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class StructKafkaProducer {
    private Properties properties;
    private KafkaProducer<String,String> data;
    public StructKafkaProducer(Properties properties) {
        this.properties = properties;
        data =  new KafkaProducer<String,String>(this.properties);
    }
    public void add(String topic, String value){
        ProducerRecord<String,String> record = new ProducerRecord<String,String>(topic,value);
        this.data.send(record);
    }
    public void flush(){
        this.data.flush();
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public KafkaProducer<String, String> getData() {
        return data;
    }

    public void close(){
        this.data.close();
    }

}
