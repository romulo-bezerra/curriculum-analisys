package br.edu.ifpb.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class LocalDateConverter implements AttributeConverter<String, LocalDate> {

    DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate convertToDatabaseColumn(String attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return LocalDate.parse(attribute, data);
    }

    @Override
    public String convertToEntityAttribute(LocalDate dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.toString();
    }
}
