package com.portuga.gymnasium.model.DAO;

import com.portuga.gymnasium.model.bo.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ClienteDAO implements InterfaceDAO<Cliente> {
    
    private static ClienteDAO instance;
    protected EntityManager entityManager;
    
    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }
    
    public ClienteDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }

    @Override
    public void create(Cliente objeto) {
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
    public List<Cliente> retrieve() {
        return entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
    }

    @Override
    public Cliente retrieve(int codigo) {
        return entityManager.find(Cliente.class, codigo);
    }

    @Override
    public Cliente retrieve(String descricao) {
        return entityManager.find(Cliente.class, descricao);
    }

    @Override
    public void update(Cliente objeto) {
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
    public void delete(Cliente objeto) {
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
