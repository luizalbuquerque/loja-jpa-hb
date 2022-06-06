package com.portuga.gymnasium.model.DAO;

import com.portuga.gymnasium.model.bo.TipoProduto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class TipoProdutoDAO implements InterfaceDAO<TipoProduto> {
    
    private static TipoProdutoDAO instance;
    protected EntityManager entityManager;
    
    public static TipoProdutoDAO getInstance() {
        if (instance == null) {
            instance = new TipoProdutoDAO();
        }
        return instance;
    }
    
    public TipoProdutoDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }

    @Override
    public void create(TipoProduto objeto) {
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
    public List<TipoProduto> retrieve() {
        return entityManager.createQuery("SELECT tp FROM TipoProduto tp").getResultList();
    }

    @Override
    public TipoProduto retrieve(int codigo) {
        return entityManager.find(TipoProduto.class, codigo);
    }

    @Override
    public TipoProduto retrieve(String descricao) {
        return entityManager.find(TipoProduto.class, descricao);
    }

    @Override
    public void update(TipoProduto objeto) {
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
    public void delete(TipoProduto objeto) {
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
