package br.com.projetofraude.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.projetofraude.model.Consumidor;
import br.com.projetofraude.model.Fraude;
import br.com.projetofraude.util.HibernateUtil;

public class FraudeDao {
	private Session sessao;
    private Transaction trans;
    private ConsumidorDao consumidor_dao = new ConsumidorDao();
    
    public void addFraude(Fraude f){
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            Fraude novo = new Fraude();
            
            Consumidor c = new Consumidor();
            
            c.setId(f.getId_consumidor());
            
            c = consumidor_dao.buscaConsumidor(c);
            
            c.setSuspeitaFraude(true);
            
            consumidor_dao.updateCliente(c);
            		
            novo.setId_consumidor(f.getId_consumidor());
            novo.setData_detecção(f.getData_detecção());
            novo.setStatus(f.getStatus());
            novo.setTipo(f.getTipo());           
            sessao.save(novo);
            trans.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }        
    }
    
    @SuppressWarnings("unchecked")
    public List<Fraude> getListaFraudes() {
        List<Fraude> lista = new ArrayList<Fraude>();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            lista = (List<Fraude>) sessao.createCriteria(Fraude.class).list();
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
    
    @SuppressWarnings("unchecked")
    public List<Fraude> getListaFraudesPorConsumidor(Integer id_c) {
        List<Fraude> lista = new ArrayList<Fraude>();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            lista = (List<Fraude>) sessao.createCriteria(Fraude.class).add(Restrictions.eq("id_consumidor", id_c)).list();
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
}