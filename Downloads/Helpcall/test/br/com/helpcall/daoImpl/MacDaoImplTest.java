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
import br.com.helpcall.model.Mac;
import java.util.List;

/**
 *
 * @author Eduardo
 */
public class MacDaoImplTest {

    private Mac mac;
    private MacDao macDao;
    private Session session;

    public MacDaoImplTest() {
        macDao = new MacDaoImpl();
    }

//    @Test
    public void salvarTeste() {
        macDao = new MacDaoImpl();
        session = HibernateUtil.abreConexao();
        QuartoDao portaDao = new QuartoDaoImpl();
        Quarto quarto = portaDao.listarTodos(session).get(0);
//        Mac mac = new Mac("202:01:2019:2707:1111", "1", "1",porta);
//        macDao.salvarOuAlterar(mac, session);

    }

//    @Test
    public void testListarTodos() {
        System.out.println("listarTodos");
        Session session = null;
        MacDaoImpl instance = new MacDaoImpl();
        List<Mac> expResult = null;
        List<Mac> result = instance.listarTodos(session);

    }

//    @Test
    public void testListarPorQuarto() {
        System.out.println("listarPorQuarto");
        int quartoId = 0;
        Session session = null;
        MacDaoImpl instance = new MacDaoImpl();
        List<Mac> expResult = null;
        List<Mac> result = instance.listarPorQuarto(quartoId, session);

    }

//    @Test
    public void testListarPorLeito() {
        System.out.println("listarPorLeito");
        Mac mac = null;
        Session session = null;
        MacDaoImpl instance = new MacDaoImpl();
        boolean expResult = false;
        boolean result = instance.listarPorLeito(mac, session);

    }


//  @Test
    public void testContarMacsPorQuarto() {
        System.out.println("ContarMacsPorQuarto");
        Long quartoId = 1L;
        session = HibernateUtil.abreConexao();
        long result = macDao.ContarMacsPorQuarto(quartoId, session);
        System.out.println(result);
        session.close();
    }

}
