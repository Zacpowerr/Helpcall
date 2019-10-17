/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.PortaDao;
import br.com.helpcall.model.Porta;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Eduardo
 */
public class PortaDaoImplTest {

    PortaDao portaDao;
    Session session;

    @Test
    public void salvarTeste() {
        portaDao = new PortaDaoImpl();
        session = HibernateUtil.abreConexao();
//        Porta porta = new Porta("200", 1);
//        portaDao.salvarOuAlterar(porta, session);
        

    }

}
