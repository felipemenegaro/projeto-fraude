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
		double k;
		
		dados.setId_consumidor(9);
		dados.setValor(12);
		
		Calendar c = Calendar.getInstance();
		
		
		c.set(Calendar.YEAR, 2016);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 01);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 00);
		
		dados.setData_hora(c.getTime());
		
		k = 0;		
		
		//Insere dados de consumo arbitrarios
		
		for(int d = 0; d < 100; d++){	// dias		
			
		    for(int h = 0; h < 24; h++){  	//horas
		    	
		    	dados.getData_hora().setHours(h);  
		    	
			    for(int m = 0; m < 60; m = m + 15){		//minutos
			    	
			    	dados.getData_hora().setMinutes(m);
			    	
			    	value = (float) ( 5 + (Math.sin(k/100)) + (Math.cos(k/20)));
			    	
			    	dados.setValor(value);
			    	
				    dados_dao.addDadosConsumo(dados);
			    	
				    k++;
			    }
			    
		    }
		    c.add(Calendar.DAY_OF_YEAR, 1);
		    dados.setData_hora(c.getTime());
		}
	    
		
		

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