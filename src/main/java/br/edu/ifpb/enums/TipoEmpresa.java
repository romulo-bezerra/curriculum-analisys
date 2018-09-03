package br.edu.ifpb.enums;

public enum TipoEmpresa {

    PRESTACAO_DE_SERVICO("Prestação de Serviço"),
    COMERCIO("Comércio"),
    INDUSTRIA("Indústria"),
    OUTROS("Outros");

    private final String label;

    private TipoEmpresa(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
}