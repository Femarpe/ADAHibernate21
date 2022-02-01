package PracticaU3.app;

import domain.Presupuesto;
import domain.Tramite;
import domain.Tramite_;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.Util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

public class Adaptador {
    public void consulta() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean salir = false;
        do {
            System.out.println("de que tabla desea consultar los datos\n(1)Tramite\n(2)Presupuesto");
            String eleccion = Util.sc.nextLine();

            if (eleccion.equals("1")) {

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
                Root<Tramite> root = criteria.from(Tramite.class);
                criteria.select(root);
                List<Tramite> tramites = session.createQuery(criteria).getResultList();
                tramites.forEach(System.out::println);
                salir = true;
            } else if (eleccion.equals("2")) {

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Presupuesto> criteria = builder.createQuery(Presupuesto.class);
                Root<Presupuesto> root = criteria.from(Presupuesto.class);
                criteria.select(root);
                List<Presupuesto> presupuestos = session.createQuery(criteria).getResultList();
                presupuestos.forEach(System.out::println);
                salir = true;

            } else {
                System.out.println("\n---Por favor introduzca una de las obiones proporcionadas---\n");
            }

        } while (salir == false);
        session.close();
    }

    public void añadir() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Tramite tramite = new Tramite();
            tramite.setFechaTramite(new Timestamp(Util.date.getTime()));

            System.out.println("¿Que tipo de tramite quiere insetar?");
            tramite.setTipoTramite(Util.sc.nextLine());

            session.save(tramite);

            System.out.println("¿cual es el lugar del presupuesto?");
            Presupuesto presupuesto = new Presupuesto(Util.sc.nextLine());
            presupuesto.setTramite(tramite);
            session.save(presupuesto);

        } catch (Exception e) {
            if (session != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            {
                session.getTransaction().commit();
                session.close();
            }
        }
    }

    public void modificar() {
        System.out.println("introduzca la id del tramite a Editar");
        int id = 0;
        try {
            id = Integer.parseInt(Util.sc.nextLine());
        } catch (Exception e) {
            System.err.println("Solo se aceptan valores numericos");

        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
        Root<Tramite> root = criteria.from(Tramite.class);
        criteria.select(root).where(builder.equal(root.get((Tramite_.idtramite)), id));
        Tramite tramite = session.createQuery(criteria).getSingleResult();
        System.out.println("a que tipo de tramite desea actualizar");
        String nTipoTramite = Util.sc.nextLine();
        tramite.setTipoTramite(nTipoTramite);

        session.update(tramite);

        transaction.commit();
        session.close();
    }

    public void eliminar() {
        System.out.println("introduzca la id del tramite a Eliminar");
        int id = 0;
        try {
            id = Integer.parseInt(Util.sc.nextLine());
        } catch (Exception e) {
            System.err.println("Solo se aceptan valores numericos");

        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
        Root<Tramite> root = criteria.from(Tramite.class);
        criteria.select(root).where(builder.equal(root.get((Tramite_.idtramite)), id));
        Tramite tramite = session.createQuery(criteria).getSingleResult();

        session.delete(tramite);
        session.close();
        System.out.println(tramite.toString() + " borrado");

    }
}
