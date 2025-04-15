package br.com.flavio.spring.data.repository;

import br.com.flavio.spring.data.orm.Cargo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class JpaExemplo {
    private EntityManagerFactory emf;
    private EntityManager em;

    public JpaExemplo() {
        emf = Persistence.createEntityManagerFactory("jpa-exemplo");
        em = emf.createEntityManager();
    }

    public void save(Cargo cargo) {
        em.getTransaction().begin();
        em.persist(cargo);
        em.getTransaction().commit();
        em.close();
    }

    public Cargo findById(Integer id) {
        em.getTransaction().begin();
        Cargo cargo = em.find(Cargo.class, id);
        em.getTransaction().commit();
        em.close();
        return cargo;
    }

    public void deleteById(Integer id) {
        em.getTransaction().begin();
        Cargo cargo = em.find(Cargo.class, id);
        if (cargo != null) {
            em.remove(cargo);
        }
        em.getTransaction().commit();
        em.close();
    }

//    public void close() {
//        if (em != null) {
//            em.close();
//        }
//        if (emf != null) {
//            emf.close();
//        }
//    }
}