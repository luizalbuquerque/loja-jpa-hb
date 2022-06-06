package com.portuga.gymnasium.model.DAO;

import com.portuga.gymnasium.model.bo.Vendedor;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class VendedorDAO implements InterfaceDAO<Vendedor> {
    
    private static VendedorDAO instance;
    protected EntityManager entityManager;
    
    public static VendedorDAO getInstance() {
        if (instance == null) {
            instance = new VendedorDAO();
        }
        return instance;
    }
    
    public VendedorDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }

    @Override
    public void create(Vendedor objeto) {
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
    public List<Vendedor> retrieve() {
        return entityManager.createQuery("SELECT v FROM Vendedor v").getResultList();
    }

    @Override
    public Vendedor retrieve(int codigo) {
        return entityManager.find(Vendedor.class, codigo);
    }

    @Override
    public Vendedor retrieve(String descricao) {
        return entityManager.find(Vendedor.class, descricao);
    }

    @Override
    public void update(Vendedor objeto) {
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
    public void delete(Vendedor objeto) {
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
