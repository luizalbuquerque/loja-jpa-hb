package com.portuga.gymnasium.model.DAO;
import com.portuga.gymnasium.model.bo.CondicaoPagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CondicaoPagamentoDAO implements InterfaceDAO<CondicaoPagamento>{
    
    private static CondicaoPagamentoDAO instance;
    protected EntityManager entityManager;
    
    public static CondicaoPagamentoDAO getInstance() {
        if (instance == null) {
            instance = new CondicaoPagamentoDAO();
        }
        return instance;
    }
    
    public CondicaoPagamentoDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }

    @Override
    public void create(CondicaoPagamento objeto) {
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
    public List<CondicaoPagamento> retrieve() {
        return entityManager.createQuery("SELECT c FROM CondicaoPagamento c").getResultList();
    }

    @Override
    public CondicaoPagamento retrieve(int codigo) {
        return entityManager.find(CondicaoPagamento.class, codigo);
    }

    @Override
    public CondicaoPagamento retrieve(String descricao) {
        return entityManager.find(CondicaoPagamento.class, descricao);
    }

    @Override
    public void update(CondicaoPagamento objeto) {
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
    public void delete(CondicaoPagamento objeto) {
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
