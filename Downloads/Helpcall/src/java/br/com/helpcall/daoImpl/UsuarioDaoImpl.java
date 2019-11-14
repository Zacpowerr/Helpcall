/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.UsuarioDao;
import br.com.helpcall.model.Perfil;
import java.util.List;
import br.com.helpcall.model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario,Long> implements UsuarioDao {

    @Override
    public List<Usuario> listarTodos (Session session ) throws HibernateException{
        return session.createQuery("from Usuario").list();
        
    }

    @Override
    public Perfil carregarGestor(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Perfil p where p.tipo = 'ROLE_GESTOR'");
        
        return (Perfil) consulta.uniqueResult();
    }

    @Override
    public List<Usuario> listarGestores(Session session) throws HibernateException {
        Query consulta = session.createQuery("from Usuario u  where u.perfilId.tipo = 'ROLE_GESTOR'");
        return consulta.list();
    }
    
    public static void main(String[] args) {
        UsuarioDaoImpl u = new UsuarioDaoImpl();
        Session session = HibernateUtil.abreConexao();
       List<Usuario> gestores =  u.listarGestores(session);
        session.close();
    }

    @Override
    public Usuario pesquisarPorLogin(String login, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Usuario where login = :login");
        consulta.setParameter("login", login);
        return (Usuario)consulta.uniqueResult();
    }

}
