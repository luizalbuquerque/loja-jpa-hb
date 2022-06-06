package com.portuga.gymnasium.model.DAO;

import com.portuga.gymnasium.model.bo.Produto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProdutoDAO implements InterfaceDAO<Produto>{
    
    private static ProdutoDAO instance;
    protected EntityManager entityManager;
    
    public static ProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoDAO();
        }
        return instance;
    }
    
    public ProdutoDAO() {
        entityManager = Persistence
                .createEntityManagerFactory("loja_PU")
                .createEntityManager();
    }

    @Override
    public void create(Produto objeto) {
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
    public List<Produto> retrieve() {
        return entityManager.createQuery("SELECT p FROM Produto p").getResultList();
    }

    @Override
    public Produto retrieve(int codigo) {
        return entityManager.find(Produto.class, codigo);
    }

    @Override
    public Produto retrieve(String descricao) {
        return entityManager.find(Produto.class, descricao);
    }

    @Override
    public void update(Produto objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    public void update(Produto objeto, int estoqueAtual){
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("UPDATE EstoqueProduto e SET e.estoqueAtual =:estoque  WHERE e.produto =:produto");
            query.setParameter("estoque", estoqueAtual);
            query.setParameter("produto", objeto);
            query.executeUpdate();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Produto objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    public int getEstoqueAtual(int codigo){
        Produto produto = retrieve(codigo);
        Query query = entityManager.createQuery("SELECT e.estoqueAtual FROM EstoqueProduto e WHERE e.produto =:id",Integer.class);
        query.setParameter("id", produto);
        int estoqueAtual = Integer.parseInt(query.getSingleResult().toString());
        return estoqueAtual;
    }
    
}
