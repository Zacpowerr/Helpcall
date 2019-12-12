package br.com.helpcall.control;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.UsuarioDao;
import br.com.helpcall.daoImpl.UsuarioDaoImpl;
import br.com.helpcall.model.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean
@ViewScoped
public class UsuarioLogadoControl implements Serializable {

    private Usuario usuario;
    private Session session;
    private UsuarioDao usuarioDao;
    private String login;

    public UsuarioLogadoControl() {
        pegarUsuarioSpring();

    }

    private void pegarUsuarioSpring() {
        login = resgatarLoginSpring();
        usuarioDao = new UsuarioDaoImpl();
        try {
            session = HibernateUtil.abreConexao();
            usuario = usuarioDao.pesquisarPorLogin(login, session);
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar por Login");
        } finally {
            session.close();
        }
    }

    private String resgatarLoginSpring() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        try {
            if (securityContext instanceof SecurityContext) {
                Authentication autenticado = securityContext.getAuthentication();
                if (autenticado instanceof Authentication) {
                    login = ((User) autenticado.getPrincipal()).getUsername();
                }
            }
        } catch (Exception e) {
            System.out.println("usuário ainda não logado " + e.getMessage());
        }
        return login;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
