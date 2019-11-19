/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.HibernateUtil;
import br.com.helpcall.dao.MacDao;
import br.com.helpcall.model.Mac;
import br.com.helpcall.model.Quarto;
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
    public boolean listarPorLeito(Mac mac, Session session) {
        Query consulta = session.createQuery("from Mac m where m.quartoId.id =:idQuarto and leito=:leito and status=1");
        consulta.setParameter("idQuarto", mac.getQuartoId().getId());
        consulta.setParameter("leito", mac.getLeito());
        Mac mac2 = (Mac) consulta.uniqueResult();
        return mac2 == null;
    }


    @Override
    public long ContarMacsPorQuarto(Long quartoId, Session session) throws HibernateException {
       Query consulta = session.createQuery("select count(m.id)from Mac m where quarto_id = :quarto_id");
       consulta.setParameter("quarto_id", quartoId);
       return (Long) consulta.uniqueResult();
        
    }

}
