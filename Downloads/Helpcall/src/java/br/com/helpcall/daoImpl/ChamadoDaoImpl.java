package br.com.helpcall.daoImpl;

import br.com.helpcall.dao.ChamadoDao;
import br.com.helpcall.model.Chamado;
import br.com.helpcall.model.ChamadoAtivo;
import br.com.helpcall.util.ReportGenerator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Eduardo Bastos
 */
public class ChamadoDaoImpl extends BaseDaoImpl<Chamado, Long>
        implements ChamadoDao {

    @Override
    public List<Chamado> listarPorMes(Integer month, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Chamado where MONTH(horainit) = :month and YEAR(horainit) = YEAR(now())");
        consulta.setParameter("month", month);
        return consulta.list();

    }

    @Override
    public List<Chamado> listarPorAno(Integer year, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Chamado where YEAR(horainit) = YEAR(:year)");
        consulta.setParameter("year", year);
        return consulta.list();
    }

    @Override
    public List<ChamadoAtivo> listarChamadoAtivo(Session session) throws HibernateException {
        return session.getNamedQuery("ChamadoAtivo.findAll").list();
    }

    @Override
    public List<Chamado> listarTodos(Session session) throws HibernateException {
        return session.createQuery("from Chamado order by id DESC").setMaxResults(10).list();
    }

    public File gerarPdf(Integer year, Integer month, Session session) throws IOException {
        ReportGenerator generator = new ReportGenerator();
        return generator.gerarPdf(year, month, session);
    }

    public WritableWorkbook GerarExcel(Integer year, Integer month, Session session) {
        ReportGenerator generator = new ReportGenerator();
        return generator.GerarExcel(year, month, session);
    }

}
