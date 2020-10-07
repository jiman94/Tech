package ;

import .config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStatusChanged_ChangeDeliveryStatus(@Payload DeliveryStatusChanged deliveryStatusChanged){

        if(deliveryStatusChanged.isMe()){
            System.out.println("##### listener ChangeDeliveryStatus : " + deliveryStatusChanged.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryPrepared_ChangeDeliveryStatus(@Payload DeliveryPrepared deliveryPrepared){

        if(deliveryPrepared.isMe()){
            System.out.println("##### listener ChangeDeliveryStatus : " + deliveryPrepared.toJson());
        }
    }

}
