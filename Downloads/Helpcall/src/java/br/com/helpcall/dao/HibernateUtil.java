package br.com.helpcall.dao;

import br.com.helpcall.model.Chamado;
import br.com.helpcall.model.ChamadoAtivo;
import br.com.helpcall.model.Mac;
import br.com.helpcall.model.Perfil;
import br.com.helpcall.model.Quarto;
import br.com.helpcall.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Thaisa
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Usuario.class);
            cfg.addAnnotatedClass(Perfil.class);
            cfg.addAnnotatedClass(Mac.class);
            cfg.addAnnotatedClass(Chamado.class);
            cfg.addAnnotatedClass(Quarto.class);
            cfg.addAnnotatedClass(ChamadoAtivo.class);
            

            cfg.configure("/br/com/helpcall/dao/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (Throwable ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abreConexao() {
        return sessionFactory.openSession();
    }
}
