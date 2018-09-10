package br.edu.ifpb.controllers;

import br.edu.ifpb.abstractions.EmpresaService;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.domain.embeddable.Endereco;
import br.edu.ifpb.enums.TipoEmpresa;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Data;

@Named
@RequestScoped
@Data
public class EmpresaController {
    
    private Part foto;
    private Empresa empresa;
    private Endereco endereco;

    @EJB
    private EmpresaService empresaService;

    @PostConstruct
    public void initObjects() {
        this.empresa = new Empresa();
        this.endereco = new Endereco();
    }

    public String save() throws IOException {
        if (empresaService.isRegistered(empresa.getEmail())) {
            mensagemErro("Cadastro Empresa", "Já existe uma empresa "
                    + "cadastrada com o e-mail informado!");
            return null;
        } else {
            byte[] arrayFoto = new byte[(int) foto.getSize()];
            foto.getInputStream().read(arrayFoto);
            empresa.setFoto(arrayFoto);
            
            
            empresa.setEndereco(endereco);
            empresaService.save(empresa);
            return "login.xhtml";
        }
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }
    
    public SelectItem[] optionsTipoEmpresa() {
        SelectItem[] items = new SelectItem[TipoEmpresa.values().length];
        int i = 0;
        for (TipoEmpresa t : TipoEmpresa.values()) {
            items[i++] = new SelectItem(t, t.getLabel());
        }
        return items;
    }
    
}