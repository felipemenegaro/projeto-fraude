package br.com.projetofraude.main;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.projetofraude.dao.*;
import br.com.projetofraude.model.DadosConsumo;


public class Teste {

	/*
	 * Preenche o banco de dados com dados de consumo
	 */
	public static void main(String[] args) {
		
		
		DadosConsumoDao dados_dao 	= new DadosConsumoDao();
		DadosConsumo 	dados 		= new DadosConsumo();
		
		float value;
		int k;
		
		dados.setId_consumidor(14);
		dados.setValor(12);
		
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.YEAR, 2010);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 0);
        
        c.set(Calendar.HOUR_OF_DAY, 18);
        c.set(Calendar.MINUTE, 50);
        c.set(Calendar.SECOND, 00);
		
		dados.setData_hora(c.getTime());
		
		k = 0;
		
		for(int m = 0; m < 35; m++){		
			
		    for(int j = 0; j < 24; j++){
		    	
		    	dados.getData_hora().setHours(j);
		    	
			    for(int i = 0; i < 60; i = i + 15){
			    	
			    	dados.getData_hora().setMinutes(i);
			    	
			    	value = (float) ( 8 + 2*(Math.sin(k*15)) );
			    	
			    	dados.setValor(value);
			    	
				    dados_dao.addDadosConsumo(dados);
			    	
				    k++;
			    }
	
		    }
		    
		    c.add(Calendar.DAY_OF_YEAR, 1);
		    dados.setData_hora(c.getTime());
		}
	    
		
		
	    //testeDatas();

	}
	
	public static void testeDatas(){
		Calendar c = Calendar.getInstance();
        
        //Date d = new Date(); 
        
        //c.setTime(d);
        /*
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_YEAR, 13); 
        c.add(Calendar.MONTH, 2);
        c.add(Calendar.YEAR, 0);
        */
		
		c.set(Calendar.YEAR, 1999);
        c.set(Calendar.MONTH, 9);
        c.set(Calendar.DAY_OF_MONTH, 25);
        
        c.set(Calendar.HOUR_OF_DAY, 18);
        c.set(Calendar.MINUTE, 50);
        c.set(Calendar.SECOND, 00);
        //c.set(Calendar.);
        
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //sdf.
        System.out.println(sdf.format(c.getTime()));
        
        
        Date d2 = c.getTime();
        
        System.out.println(d2);
	}

}