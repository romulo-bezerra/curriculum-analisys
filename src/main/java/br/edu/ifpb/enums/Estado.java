package br.edu.ifpb.enums;

public enum Estado {

    FECHADA("Fechada"),
    ABERTA("Aberta");

    private final String label;

    private Estado(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
