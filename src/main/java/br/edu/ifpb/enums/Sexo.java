package br.edu.ifpb.enums;

public enum Sexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String label;

    private Sexo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
