package com.portuga.gymnasium.model.DAO;
import java.util.List;
import com.portuga.gymnasium.model.bo.Endereco;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EnderecoDAO implements InterfaceDAO<Endereco> {

    private static EnderecoDAO instance;
    protected EntityManager entityManager;
    
    public static EnderecoDAO getInstance() {
        if (instance == null) {
            instance = new EnderecoDAO();
        }
        return instance;
    }
    
    public EnderecoDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }
    
    @Override
    public void create(Endereco objeto) {
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
    public List<Endereco> retrieve() {
        return entityManager.createQuery("SELECT e FROM Endereco e").getResultList();
    }

    @Override
    public Endereco retrieve(int codigo) {
        return entityManager.find(Endereco.class, codigo);
    }

    @Override
    public Endereco retrieve(String descricao) {
        return entityManager.find(Endereco.class, descricao);
    }

    @Override
    public void update(Endereco objeto) {
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
    public void delete(Endereco objeto) {
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
