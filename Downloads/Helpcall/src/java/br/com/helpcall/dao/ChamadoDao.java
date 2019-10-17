package br.com.helpcall.dao;

import br.com.helpcall.model.Chamado;
import br.com.helpcall.model.ChamadoAtivo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Eduardo Bastos
 */
public interface ChamadoDao extends BaseDao<Chamado,Long>{
     public List<Chamado> listarPorMes(Integer month, Session session)throws HibernateException;
     public List<Chamado> listarPorAno(Integer year, Session session)throws HibernateException;
     public List<ChamadoAtivo> listarChamadoAtivo(Session session) throws HibernateException;
     public List<Chamado> listarTodos(Session session) throws HibernateException;
    
}
