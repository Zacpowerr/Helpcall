/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.PortaDao;
import br.com.helpcall.model.Porta;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
public class PortaDaoImpl extends BaseDaoImpl<Porta, String> implements PortaDao {

    @Override
    public List<Porta> listarTodos(Session session) throws HibernateException {
        return session.createQuery("from Porta").list();
    }

   

}
