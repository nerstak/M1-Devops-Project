package utils;

import javax.persistence.AttributeConverter;
import java.util.UUID;

/**
 * Class to automatically convert UUID from DB to UUID from Java
 */
@javax.persistence.Converter(autoApply = true)
public class PostgresUuidConverter implements AttributeConverter<UUID, UUID> {

    @Override
    public UUID convertToDatabaseColumn(UUID attribute) {
        return attribute;
    }

    @Override
    public UUID convertToEntityAttribute(UUID dbData) {
        return dbData;
    }

}
