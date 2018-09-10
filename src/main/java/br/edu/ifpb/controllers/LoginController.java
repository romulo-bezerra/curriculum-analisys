package br.edu.ifpb.controllers;

import br.edu.ifpb.domain.Candidato;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Data;
import br.edu.ifpb.abstractions.LoginService;
import br.edu.ifpb.domain.Empresa;
import br.edu.ifpb.enums.TipoLogin;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

@Named
@SessionScoped
@Data
public class LoginController implements Serializable {

    private String email;
    private String senha;
    private TipoLogin tipoLogin;
    private Candidato candidatoLogado;
    private Empresa empresaLogada;

    @EJB
    private LoginService loginService;

    public String login() {

        if (tipoLogin.equals(TipoLogin.CANDIDATO)) {
            candidatoLogado = loginService.authenticateUser(email, senha);
            if (candidatoLogado == null) {
                mensagemErro("Login", "O usuário informado não está cadastrado!");
                return null;
            } else {
                if (!candidatoLogado.getSenha().equalsIgnoreCase(senha)) {
                    mensagemErro("Login", "Os dados informados estão incorretos!");
                    return null;
                } else {
                    return "index.xhtml";
                }
            }
        } else {
            empresaLogada = loginService.authenticateCompany(email, senha);
            if (empresaLogada == null) {
                mensagemErro("Login", "A empresa informada não está cadastrada!");
                return null;
            } else {
                if (!empresaLogada.getSenha().equalsIgnoreCase(senha)) {
                    mensagemErro("Login", "Os dados informados estão incorretos!");
                    return null;
                } else {
                    return "home-empresa.xhtml";
                }
            }
        }
    }

    public SelectItem[] optionsTipoLogin() {
        SelectItem[] items = new SelectItem[TipoLogin.values().length];
        int i = 0;
        for (TipoLogin t : TipoLogin.values()) {
            items[i++] = new SelectItem(t, t.getLabel());
        }
        return items;
    }

    public String realizarLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }

    public void mensagemErro(String titlePag, String content) {
        FacesMessage mensagemDeErro = new FacesMessage(content);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(titlePag, mensagemDeErro);
    }

}
