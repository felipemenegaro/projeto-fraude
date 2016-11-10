package br.com.projetofraude.dao;

import br.com.projetofraude.model.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.projetofraude.util.HibernateUtil;


public class ConsumidorDao {
    
    private Session sessao;
    private Transaction trans;
    
    public void addConsumidor(Consumidor c){
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Consumidor novo = new Consumidor();
            novo.setDescricao(c.getDescricao());
            novo.setEndereço(c.getEndereço());
            novo.setLatitude(c.getLatitude());
            novo.setLongitude(c.getLongitude());
            novo.setSuspeitaFraude(c.isSuspeitaFraude());
            novo.setTipo(c.getTipo());
            sessao.save(novo);
            trans.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }        
    }
    
    @SuppressWarnings("unchecked")
    public List<Consumidor> getListaConsumidores() {
        List<Consumidor> lista = new ArrayList<Consumidor>();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            lista = (List<Consumidor>) sessao.createCriteria(Consumidor.class).list();
            sessao.getTransaction().commit();
        } catch (Exception e) {
            sessao.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (sessao != null) {
                try {
                    sessao.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }
    
    public Consumidor buscaConsumidor(Consumidor c) {
    	Consumidor retorno = new Consumidor();
        try {
        	sessao = HibernateUtil.getSessionFactory().openSession();
        	sessao.beginTransaction();
            retorno = (Consumidor) sessao.get(Consumidor.class, c.getId());
            sessao.getTransaction().commit();
        } catch (Exception ex) {
        	sessao.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (sessao != null) {
                try {
                    sessao.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return retorno;
    }
    
    //retorna consumidor por id
    public Consumidor buscaConsumidorID(Integer id) {
    	Consumidor retorno = new Consumidor();
        try {
        	sessao = HibernateUtil.getSessionFactory().openSession();
        	sessao.beginTransaction();
            retorno = (Consumidor) sessao.get(Consumidor.class, id);
            sessao.getTransaction().commit();
        } catch (Exception ex) {
        	sessao.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (sessao != null) {
                try {
                    sessao.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return retorno;
    }
    
  //altera dados do consumidor
    public byte updateCliente(Consumidor cliente) {
        byte transactionok = 0;
        try {
        	sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            sessao.update(cliente);
            sessao.getTransaction().commit();
            transactionok = 0;
        } catch (Exception e) {
        	sessao.getTransaction().rollback();
            transactionok = 2;
        } finally {
            if (sessao != null) {
                try {
                	sessao.close();
                } catch (Exception e) {
                    transactionok = 2;
                }
            }
        }
        return transactionok;
    }
    
    
    
}
