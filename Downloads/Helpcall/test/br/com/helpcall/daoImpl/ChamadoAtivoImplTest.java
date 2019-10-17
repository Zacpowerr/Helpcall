/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.ChamadoAtivoDao;
import br.com.helpcall.dao.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 *
 * @author Eduardo
 */
public class ChamadoAtivoImplTest {
    
    public ChamadoAtivoImplTest() {
    }
    
   @Test
   public void chamadoAtivoTeste(){
       Session session = HibernateUtil.abreConexao();
       ChamadoAtivoDao chamadoAtivoDao = new ChamadoAtivoImpl();
       System.out.println(chamadoAtivoDao.listarChamadoAtivo(session).get(0).getIdQuarto());
       
   }
    
}
