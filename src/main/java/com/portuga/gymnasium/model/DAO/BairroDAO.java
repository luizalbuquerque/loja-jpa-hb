package com.portuga.gymnasium.model.DAO;

import java.util.List;
import com.portuga.gymnasium.model.bo.Bairro;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BairroDAO implements InterfaceDAO<Bairro>{
    
    private static BairroDAO instance;
    protected EntityManager entityManager;
    
    public static BairroDAO getInstance() {
        if (instance == null) {
            instance = new BairroDAO();
        }
        return instance;
    }
    
    public BairroDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }
    
    @Override
    public void create(Bairro objeto) {
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
    public List<Bairro> retrieve() {
        return entityManager.createQuery("SELECT b FROM Bairro b").getResultList();
    }
    @Override
    public Bairro retrieve(int codigo) {
        return entityManager.find(Bairro.class, codigo);
    }

    @Override
    public Bairro retrieve(String descricao) {
        return entityManager.find(Bairro.class, descricao);
    }

    @Override
    public void update(Bairro objeto) {
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
    public void delete(Bairro objeto) {
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
