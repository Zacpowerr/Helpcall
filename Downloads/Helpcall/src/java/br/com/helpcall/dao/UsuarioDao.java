/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.dao;

import br.com.helpcall.model.Perfil;
import java.util.List;
import br.com.helpcall.model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
public interface UsuarioDao extends BaseDao<Usuario, Long> {

    public List<Usuario> listarTodos(Session session) throws HibernateException;

    public List<Usuario> listarGestores(Session session) throws HibernateException;

    public Perfil carregarGestor(Session session) throws HibernateException;
    
    public Usuario pesquisarPorLogin (String login, Session session) throws HibernateException;

}
