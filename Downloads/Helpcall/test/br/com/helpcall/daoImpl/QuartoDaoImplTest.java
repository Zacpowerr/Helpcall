/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.QuartoDao;
import br.com.helpcall.model.Quarto;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class QuartoDaoImplTest {
    
    private Quarto quarto;
    private Session session;
    private QuartoDao quartoDao;
    
    public QuartoDaoImplTest() {
        quartoDao = new QuartoDaoImpl();
    }

    @Test
    public void testListarTodos() {
        System.out.println("listarTodos");
    }

    @Test
    public void testSalvar() {
        System.out.println("listarTodos");
        quarto = new Quarto(null, "201");
        session = HibernateUtil.abreConexao();
        quartoDao.salvarOuAlterar(quarto, session);
        session.close();
        assertNotNull(quarto.getId());
        
    }
    
}
