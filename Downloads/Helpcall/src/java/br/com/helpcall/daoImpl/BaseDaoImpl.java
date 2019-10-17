/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.BaseDao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Thaisa
 * @param <T>
 * @param <ID>
 */
public class BaseDaoImpl<T, ID> implements BaseDao<T, ID>{
    
    private Transaction transacao;

    @Override
    public void salvarOuAlterar(T entidade, Session session) throws HibernateException {
        transacao = session.beginTransaction();
        session.saveOrUpdate(entidade);
        transacao.commit();
    }

    @Override
    public void excluir(T entidade, Session session) throws HibernateException {
        transacao = session.beginTransaction();
        session.delete(entidade);
        transacao.commit();
    }

    
}
