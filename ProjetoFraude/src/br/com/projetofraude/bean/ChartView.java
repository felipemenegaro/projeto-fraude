package br.com.projetofraude.bean;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;

import br.com.projetofraude.dao.ConsumidorDao;
import br.com.projetofraude.dao.DadosConsumoDao;
import br.com.projetofraude.model.Consumidor;
import br.com.projetofraude.model.DadosConsumo;
 

@ManagedBean
@RequestScoped
public class ChartView implements Serializable {
 
	private LineChartModel lineModel1;
    //private LineChartModel lineModel2;
    private LineChartModel areaModel;
    private LineChartModel dateModel;
    private List<DadosConsumo> lista;
    private DadosConsumo temp = new DadosConsumo();
    private DadosConsumoDao dadosDao = new DadosConsumoDao();
    private Consumidor consumidor;
    private ConsumidorDao consumidorDao = new ConsumidorDao();
    private Integer id;
	
    
    @PostConstruct
    public void init() {
        id = Integer.valueOf( FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id") );       
        consumidor = consumidorDao.buscaConsumidorID(id);
        createDateModel();
        //createLineModels();
    }
    
    public Integer getId() {
		return id;
	}
    
    public LineChartModel getLineModel1() {
        return lineModel1;
    }
 
    public LineChartModel getAreaModel() {
        return areaModel;
    }
    
    public LineChartModel getDateModel() {
        return dateModel;
    }
    
    public Consumidor getConsumidor(){
    	return consumidor;
    }
    
    
 /*
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
    */
    
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setShowPointLabels(false);
        lineModel1.setStacked(false);      
        
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
        	//System.out.println(lista.get(i).getValor());
        	series1.set(i, lista.get(i).getValor());
        }
        
        model.addSeries(series1);
        return model;
    }
    
    private void createAreaModel() {
        areaModel = new LineChartModel();
 
        LineChartSeries dados = new LineChartSeries();
        dados.setFill(false);
        dados.setLabel("Dados");
        
        Integer j = 10;
        
        for(int i = 0; i < 300; i++){
        	
        	if(j > 20){
        		j = 10;
        	}else{
            	j = j + 1;
        	}
        	dados.set(i, j);
        }
    
        areaModel.addSeries(dados);
        areaModel.setTitle("Area Chart");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(false);
        areaModel.setShadow(false);
        areaModel.setZoom(true);
        
        //Axis xAxis = new CategoryAxis("Years");
        //areaModel.getAxes().put(AxisType.X, xAxis);
        Axis xAxis = areaModel.getAxis(AxisType.X);
        xAxis.setLabel("Years");
        xAxis.setMin(0);
        xAxis.setMax(300);
        xAxis.setTickInterval("10");
        xAxis.setTickCount(10);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(300);
    }
    
    
    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        
        series1.setLabel("Series 1");
        series1.setShowMarker(false);
        series1.setFill(false);
        
        series1.set("2014-01-01 08:00:00", 51);
        series1.set("2014-01-01 08:30:00", 22);
        series1.set("2014-01-01 10:20:00", 65);
        series1.set("2014-01-01 15:10:00", 35);
        
        dateModel.addSeries(series1);
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        
        axis.setMin("2014-01-01 00:00:00");
        
        axis.setMax("2014-01-01 23:59:00");
        
        axis.setTickFormat("%H:%M:%S");
        dateModel.getAxes().put(AxisType.X, axis);
    }
 
}