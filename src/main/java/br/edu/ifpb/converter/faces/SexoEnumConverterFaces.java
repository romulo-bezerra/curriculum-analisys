//package br.edu.ifpb.converter.faces;
//
//import br.edu.ifpb.enums.Sexo;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//@FacesConverter(forClass = Sexo.class)
//public class SexoEnumConverterFaces implements Converter {
//
//    Sexo sexo = Sexo.FEMININO;
//
//    @Override
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value == null) {
//            return null;
//        }
//        if (value.equalsIgnoreCase("Masculino")) {
//            sexo = Sexo.MASCULINO;
//            return sexo;
//        }
//        
//        return sexo;
//    }
//
//    @Override
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value == null) {
//            return null;
//        }
//        if (value.equals(Sexo.MASCULINO)) {
//            return "Masculino";
//        }
//        return "Feminino";
//    }
//
//}