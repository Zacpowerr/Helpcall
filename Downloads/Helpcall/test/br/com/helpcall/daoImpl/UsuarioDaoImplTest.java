/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.UsuarioDao;
import br.com.helpcall.model.Perfil;
import br.com.helpcall.model.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class UsuarioDaoImplTest {
    private Perfil perfil;
    private Session session;
    private UsuarioDao usuarioDao;
    
    public UsuarioDaoImplTest() {
        usuarioDao = new UsuarioDaoImpl();
    }

//    @Test
    public void testListarTodos() {
        System.out.println("listarTodos");
    }

//    @Test
    public void testListarPorLogin() {
        System.out.println("listarPorLogin");
    }

    @Test
    public void testCarregarGestor() {
        System.out.println("carregarGestor");
        session= HibernateUtil.abreConexao();
        perfil = usuarioDao.carregarGestor(session);
        session.close();
        assertNotNull(perfil);
    }
    
}
