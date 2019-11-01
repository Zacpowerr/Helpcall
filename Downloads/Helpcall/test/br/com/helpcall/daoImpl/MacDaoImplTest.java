/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.MacDao;
import br.com.helpcall.model.Quarto;
import org.hibernate.Session;
import org.junit.Test;
import br.com.helpcall.dao.QuartoDao;

/**
 *
 * @author Eduardo
 */
public class MacDaoImplTest {

    public MacDaoImplTest() {
    }
    MacDao macDao;
    Session session;

    @Test
    public void salvarTeste() {
        macDao = new MacDaoImpl();
        session = HibernateUtil.abreConexao();
        QuartoDao portaDao = new QuartoDaoImpl();
        Quarto quarto = portaDao.listarTodos(session).get(0);
//        Mac mac = new Mac("202:01:2019:2707:1111", "1", "1",porta);
//        macDao.salvarOuAlterar(mac, session);

    }

}
