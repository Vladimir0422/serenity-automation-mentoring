package serenity.bdd.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by : Volodymyr_Silitskyi
 * Created at : 12/2/2018
 */
public class DateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public DateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.readValueAs(String.class);
        value = value.substring(0, value.length() - 5);
        return LocalDateTime.parse(value);
    }
}
