/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import br.com.helpcall.dao.QuartoDao;
import br.com.helpcall.model.Quarto;

/**
 *
 * @author Thaisa
 */
public class QuartoDaoImpl extends BaseDaoImpl<Quarto, Long> implements QuartoDao {

    public QuartoDaoImpl() {
    }    
    

    @Override
    public List<Quarto> listarTodos(Session session) throws HibernateException {
        return session.createQuery("from Quarto").list();
    }

    @Override
    public Quarto pesquisarPorID(Long id, Session session) throws HibernateException {
        return (Quarto) session.get(Quarto.class, id);
    }
}
