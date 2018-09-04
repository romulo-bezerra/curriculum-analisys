package br.edu.ifpb.controllers;

import br.edu.ifpb.domain.Candidato;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Data;
import br.edu.ifpb.abstractions.LoginService;
import br.edu.ifpb.enums.TipoLogin;
import javax.faces.model.SelectItem;

@Named
@RequestScoped
@Data
public class LoginController {

    private String email;
    private String senha;
    private TipoLogin tipoLogin;
    private HttpSession sessao;

    @EJB
    private LoginService loginService;

    public String login() {

        if (tipoLogin.equals(TipoLogin.CANDIDATO)) {
            Candidato candidatoLogado = loginService.authenticateUser(email, senha);
            if (candidatoLogado == null) {
                mensagemErro("Login", "O usuário informado não está cadastrado!");
                return null;
            } else {
                if (!candidatoLogado.getSenha().equalsIgnoreCase(senha)) {
                    mensagemErro("Login", "Os dados informados estão incorretos!");
                    return null;
                } else {
                    sessao = (HttpSession) FacesContext.getCurrentInstance()
                            .getExternalContext().getSession(true);
                    sessao.setAttribute("candidato", candidatoLogado);
                    return "index.xhtml";
                }
            }
        }
        return null; //Temp
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
