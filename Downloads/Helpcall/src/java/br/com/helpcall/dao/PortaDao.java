/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.dao;

import br.com.helpcall.model.Porta;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Thaisa
 */
public interface PortaDao extends BaseDao <Porta, String>{
    
    public List<Porta> listarTodos (Session session ) throws HibernateException;
    
}
