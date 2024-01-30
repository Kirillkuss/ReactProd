package com.itrail.react.reactprod.config.kafka.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itrail.react.reactprod.entity.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonSerializer implements Serializer<Person> {
    
     private final ObjectMapper om = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Person person) {
        try {
            if ( person == null ){
                log.info("Null received at serializing");
                return null;
            }
            log.info( "Serializing entity Person..." );
            return om.writeValueAsBytes( person );
        } catch ( Exception e ) {
            throw new SerializationException( "Error when serializing Animal to byte[]" );
        }
    }
}
