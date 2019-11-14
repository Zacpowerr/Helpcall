/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.dao;

import br.com.helpcall.model.Mac;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
public interface MacDao extends BaseDao<Mac, String> {

    public List<Mac> listarPorQuarto(int quartoId, Session session) throws HibernateException;

    public List<Mac> listarTodos(Session session) throws HibernateException;

    public boolean listarPorLeito(Mac mac, Session session);

}
