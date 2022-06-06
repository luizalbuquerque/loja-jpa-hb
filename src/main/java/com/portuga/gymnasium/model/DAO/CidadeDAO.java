package com.portuga.gymnasium.model.DAO;
import java.util.List;
import com.portuga.gymnasium.model.bo.Cidade;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CidadeDAO implements InterfaceDAO<Cidade>{
    
    private static CidadeDAO instance;
    protected EntityManager entityManager;
    
    public static CidadeDAO getInstance() {
        if (instance == null) {
            instance = new CidadeDAO();
        }
        return instance;
    }
    
    public CidadeDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }

    @Override
    public void create(Cidade objeto) {
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
    public List<Cidade> retrieve() {
        return entityManager.createQuery("SELECT c FROM Cidade c").getResultList();
    }
    
    @Override
    public Cidade retrieve(int codigo) {
        return entityManager.find(Cidade.class, codigo);
    }

    @Override
    public Cidade retrieve(String descricao) {
        return entityManager.find(Cidade.class, descricao);
    }

    @Override
    public void update(Cidade objeto) {
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
    public void delete(Cidade objeto) {
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
