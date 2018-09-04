package br.edu.ifpb.enums;

public enum TipoLogin {

    EMPRESA("Empresa"),
    CANDIDATO("Candidato");

    private final String label;

    private TipoLogin(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}