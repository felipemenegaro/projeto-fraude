package br.com.projetofraude.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.projetofraude.model.DadosConsumo;
import br.com.projetofraude.util.HibernateUtil;

public class DadosConsumoDao {
	
	private Session sessao;
    private Transaction trans;
    
    public void addDadosConsumo(DadosConsumo dados){
        try{
            sessao = HibernateUtil.getSessionFactory().openSession();
            trans = sessao.beginTransaction();
            DadosConsumo novo = new DadosConsumo();
            novo.setData_hora(dados.getData_hora());
            novo.setId_consumidor(dados.getId_consumidor());
            novo.setValor(dados.getValor());       
            sessao.save(novo);
            trans.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            sessao.close();
        }        
    }
    
       
    @SuppressWarnings("unchecked")
    public List<DadosConsumo> getListaDadosConsumo() {
        List<DadosConsumo> lista = new ArrayList<DadosConsumo>();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            lista = (List<DadosConsumo>) sessao.createCriteria(DadosConsumo.class).list();
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
    public List<DadosConsumo> getListaIntervaloDadosConsumoPorConsumidor(Integer id_c, Date init, Date fim) {
        List<DadosConsumo> lista = new ArrayList<DadosConsumo>();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            
            lista = (List<DadosConsumo>) sessao.createCriteria(DadosConsumo.class)
            		.add(Restrictions.and(Restrictions.between("data_hora", init, fim),
           							  Restrictions.eq("id_consumidor", id_c) ) ).list();
            
            
            
            
            /*
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fromDate = df.parse(init.toString());//"2012-04-09 00:00:00");
            Date toDate = df.parse(fim.toString());//"2012-04-09 23:59:59");

            criteria.add(Restrictions.between("dateField", fromDate, toDate));
            
            lista = (List<DadosConsumo>) sessao.createQuery("FROM dadosConsumo AS c WHERE c.data_hora BETWEEN :stDate AND :edDate ")
            .setParameter("stDate", frmDate)
            .setParameter("edDate", enDate).list();
            
            */
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