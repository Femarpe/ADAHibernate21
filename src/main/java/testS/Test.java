package testS;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        Spartan spartan = new Spartan();
        spartan.setCodigo(117);
        spartan.setNombre("Pipo");
        spartan.setApodo("Gran Pipo");

        try {
            transaction = session.beginTransaction();
            session.save(spartan);
        } catch (Exception e) {
            if (session != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Spartan> criteria = builder.createQuery(Spartan.class);
                Root<Spartan> root = criteria.from(Spartan.class);

                criteria.select(root);
                List<Spartan> spartans = session.createQuery(criteria).getResultList();
                spartans.forEach(System.out::println);

                session.getTransaction().commit();
                session.close();
            }
        }

    }
}
