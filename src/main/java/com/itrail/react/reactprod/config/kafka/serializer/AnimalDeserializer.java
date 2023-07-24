package com.itrail.react.reactprod.config.kafka.serializer;

import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itrail.react.reactprod.entity.Animal;
import lombok.extern.slf4j.Slf4j;
/**
 * Десерилизатор для сущности Animal
 */
@Slf4j
public class AnimalDeserializer  implements Deserializer<Animal> {
    
    private ObjectMapper om = new ObjectMapper();

    @Override
    public Animal deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                log.info("Null received at deserializing");
                return null;
            }
            log.info( "Deserializing entity Animal...");
            return om.readValue( new String( data, "UTF-8" ), Animal.class);
        } catch ( Exception e ) {
            throw new SerializationException("Error when deserializing byte[] to Animal");
        }
    }

}
