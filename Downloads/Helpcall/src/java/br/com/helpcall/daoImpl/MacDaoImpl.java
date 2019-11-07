/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.MacDao;
import br.com.helpcall.model.Mac;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
public class MacDaoImpl extends BaseDaoImpl<Mac, String> implements MacDao {

    @Override
    public List<Mac> listarTodos(Session session) throws HibernateException {
        return session.createQuery("from Mac").list();
    }

    @Override
    public List<Mac> listarPorQuarto(int quartoId, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Mac where quarto_id = :quarto_id");
        consulta.setParameter("quarto_id", quartoId);
        return consulta.list();
    }

    @Override
    public List<Mac> listarPorLeito(Mac mac, Session session) {
        Query consulta = session.createQuery("from Mac where quarto_id =:quarto_id and leito=:leito and status=1");
        consulta.setParameter("quarto_id", mac.getQuartoId());
        consulta.setParameter("leito", mac.getLeito());
        return consulta.list();
    }

}
