package com.itrail.react.reactprod.config.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itrail.react.reactprod.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.errors.SerializationException;
/**
 * Сериализатор для сущности Person
 */
@Slf4j
public class PersonSerializer implements Serializer<Person> {

    private final ObjectMapper om = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Person animal) {
        try {
            if ( animal == null ){
                log.info("Null received at serializing");
                return null;
            }
            log.info( "Serializing entity Person..." );
            return om.writeValueAsBytes( animal );
        } catch ( Exception e ) {
            throw new SerializationException( "Error when serializing Person to byte[]" );
        }
    }
}
