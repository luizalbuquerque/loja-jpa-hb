package com.portuga.gymnasium.model.DAO;

import com.portuga.gymnasium.model.bo.Tamanho;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class TamanhoDAO implements InterfaceDAO<Tamanho> {
    
    private static TamanhoDAO instance;
    protected EntityManager entityManager;
    
    public static TamanhoDAO getInstance() {
        if (instance == null) {
            instance = new TamanhoDAO();
        }
        return instance;
    }
    
    public TamanhoDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }

    @Override
    public void create(Tamanho objeto) {
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
    public List<Tamanho> retrieve() {
        return entityManager.createQuery("SELECT t FROM Tamanho t").getResultList();
    }

    @Override
    public Tamanho retrieve(int codigo) {
        return entityManager.find(Tamanho.class, codigo);
    }

    @Override
    public Tamanho retrieve(String descricao) {
        return entityManager.find(Tamanho.class, descricao);
    }

    @Override
    public void update(Tamanho objeto) {
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
    public void delete(Tamanho objeto) {
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
