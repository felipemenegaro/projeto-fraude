package br.com.projetofraude.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.projetofraude.dao.DadosConsumoDao;
import br.com.projetofraude.model.DadosConsumo;

@SuppressWarnings("serial")
public class Grafico extends LineChartModel{

    private DadosConsumoDao dadosDao = new DadosConsumoDao();
    private List<DadosConsumo> lista;
    
    public Grafico(){
    	
    }
	
	public void constroiGrafico(Integer id, Date inicio, Date fim ) {
        
		//grafico = new LineChartModel();
        
        LineChartSeries series1 = new LineChartSeries();
          
        lista = dadosDao.getListaIntervaloDadosConsumoPorConsumidor(id, inicio, fim );
        
        String s;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
        
        for(int i = 0; i < lista.size(); i++){   	
        	
            s = sdf.format(lista.get(i).getData_hora()) ;
            
        	series1.set( s , lista.get(i).getValor() );
        }
        
        series1.setLabel("Series 1");
        series1.setShowMarker(false);
        series1.setFill(false);
        
        if(lista.isEmpty()){
        	series1.set( sdf.format(inicio)  , 0);
        	series1.set( sdf.format(fim)  , 0);
        }
        this.addSeries(series1);
        
        
        //this.setZoom(true);
        
        //this.getAxis(AxisType.Y).setLabel("Values");
        
        DateAxis axis = new DateAxis();  // x
        axis.setTickAngle(-50);
        /*
        axis.setMin("2014-01-01 00:00:00");
        axis.setMax("2014-01-01 23:59:00");
        */
        axis.setTickFormat("%D %H:%M");
        this.getAxes().put(AxisType.X, axis);
    }
	
}
