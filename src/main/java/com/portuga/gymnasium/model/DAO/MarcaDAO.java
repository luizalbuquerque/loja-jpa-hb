package com.portuga.gymnasium.model.DAO;

import com.portuga.gymnasium.model.bo.Marca;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MarcaDAO implements InterfaceDAO<Marca> {

    private static MarcaDAO instance;
    protected EntityManager entityManager;
    
    public static MarcaDAO getInstance() {
        if (instance == null) {
            instance = new MarcaDAO();
        }
        return instance;
    }
    
    public MarcaDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }
    
    @Override
    public void create(Marca objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Marca> retrieve() {
        return entityManager.createQuery("SELECT m FROM Marca m").getResultList();
    }

    @Override
    public Marca retrieve(int codigo) {
        return entityManager.find(Marca.class, codigo);
    }

    @Override
    public Marca retrieve(String descricao) {
        return entityManager.find(Marca.class, descricao);
    }

    @Override
    public void update(Marca objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Marca objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
}
