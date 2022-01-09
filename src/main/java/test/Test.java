package test;


import domain.Presupuesto;
import domain.Tramite;
import domain.Tramite_;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
public class Test {
    public static void main(String[] args) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Date date = new Date();

        try {

            transaction = session.beginTransaction();
            Tramite tramite = new Tramite();
            tramite.setFechaTramite(new Timestamp(date.getTime()));
            tramite.setTipoTramite("Aval");
            session.save(tramite);

            Presupuesto presupuesto = new Presupuesto("federico garcia lorca");
            presupuesto.setTramite(tramite);
            session.save(presupuesto);

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);

            Root<Tramite> root = criteria.from(Tramite.class);

            criteria.select(root).where(builder.equal(root.get(Tramite_.TIPO_TRAMITE), "Aval"));

            tramite = session.createQuery(criteria).getSingleResult();
            tramite.setTipoTramite("Proyecto software");

            session.update(tramite);

            transaction.commit();

            /*
            criteria.select(root);
            List<Tramite> tramites = session.createQuery(criteria).getResultList();
            tramites.forEach(System.out::println);
            session.getTransaction().commit();
            */
        } catch (Exception e) {
            if (session != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            {
                session.close();
            }
        }


    }
}