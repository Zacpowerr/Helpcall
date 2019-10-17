/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.ChamadoAtivoDao;
import br.com.helpcall.model.ChamadoAtivo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class ChamadoAtivoImpl extends BaseDaoImpl<ChamadoAtivo, Long> 
                            implements ChamadoAtivoDao{

    @Override
    public List<ChamadoAtivo> listarChamadoAtivo(Session session) throws HibernateException {
       return session.getNamedQuery("ChamadoAtivo.findAll").list();
    }

   
   
}
