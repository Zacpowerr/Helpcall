/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.dao;

import br.com.helpcall.model.ChamadoAtivo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public interface ChamadoAtivoDao extends BaseDao<ChamadoAtivo, Long> {

    public List<ChamadoAtivo> listarChamadoAtivo(Session session) throws HibernateException;

}
