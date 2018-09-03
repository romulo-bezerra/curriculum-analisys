package br.edu.ifpb.controller;

import br.edu.ifpb.domain.Candidato;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Data;
import br.edu.ifpb.abstraction.LoginService;
import java.util.List;

@Named
@RequestScoped
@Data
public class LoginController {

    private String email;
    private String senha;
    private String tipoLogin;
    private HttpSession sessao;

    @EJB
    private LoginService loginService;

    public String login() {

        if (tipoLogin.equalsIgnoreCase("Candidato")) {
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

    public List<String> optionsTipoLogin() {
        return loginService.getOptionsTipoLogin();
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
