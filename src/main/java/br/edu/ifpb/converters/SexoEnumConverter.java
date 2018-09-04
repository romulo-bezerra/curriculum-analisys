package br.edu.ifpb.converters;

//package br.edu.ifpb.converter;
//
//import br.edu.ifpb.enums.Sexo;
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//
//@Converter(autoApply = false)
//public class SexoEnumConverter implements AttributeConverter<String, Sexo> {
//
//    @Override
//    public Sexo convertToDatabaseColumn(String attribute) {
//        Sexo sexo;
//        if (attribute.equalsIgnoreCase("Masculino")) {
//            return sexo = Sexo.MASCULINO;
//        }
//        return sexo = Sexo.FEMININO;
//    }
//
//    @Override
//    public String convertToEntityAttribute(Sexo dbData) {
//        if (dbData.equals(Sexo.MASCULINO)) {
//            return "MASCULINO";
//        }
//        return "FEMININO";
//    }
//}
