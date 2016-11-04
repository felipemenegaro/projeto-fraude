package br.com.projetofraude.bean;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import br.com.projetofraude.dao.DadosConsumoDao;
import br.com.projetofraude.model.DadosConsumo;
 

@ManagedBean
public class ChartView implements Serializable {
 
    private LineChartModel lineModel1;
    //private LineChartModel lineModel2;
    
    List<DadosConsumo> lista;
    private DadosConsumo temp = new DadosConsumo();
    private DadosConsumoDao dadosDao = new DadosConsumoDao();
    
    @PostConstruct
    public void init() {
        createLineModels();
    }
 
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 /*
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
    */
    
    private void createLineModels() {
    	
        lineModel1 = initLinearModel();
        
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        Axis xAxis = lineModel1.getAxis(AxisType.X);
       

        
        yAxis.setMin(0);
        yAxis.setMax(20);
         /*
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Category Chart");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
        */
    }
     
    private LineChartModel initLinearModel() {
    	
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        //series1.setLabel("Series 1");
        
        lista = dadosDao.getListaIntervaloDadosConsumoPorConsumidor(10, 
        		new Date(2016-1900, 9, 1, 0, 0), new Date(2016-1900, 9, 2, 0, 0));


        for(int i = 0; i < lista.size(); i++){
        	System.out.println(lista.get(i).getValor());
        	series1.set(i, lista.get(i).getValor());
        }
        

        model.addSeries(series1);
      
        return model;
    }
    
    
   /*
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 90);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
    */
 
}