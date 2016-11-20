package br.com.projetofraude.bean;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
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
@ViewScoped
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
	
    private Date dataInicio;
    private Date dataFim;
    
    private Calendar cInicio, cFim;
    
    @PostConstruct
    public void init() {
    	
        id = Integer.valueOf( FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id") );       
        
        consumidor = consumidorDao.buscaConsumidorID(id);
        createDateModel();
        //createLineModels();
    }
    

    
    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        int id_c = 14;
        
        Calendar c, c2;
        
        c = Calendar.getInstance();
        c2 = Calendar.getInstance();
        
		c.set(Calendar.YEAR, 1999);
        c.set(Calendar.MONTH, 9);
        c.set(Calendar.DAY_OF_MONTH, 25);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        
        c2.set(Calendar.YEAR, 1999);
        c2.set(Calendar.MONTH, 9);
        c2.set(Calendar.DAY_OF_MONTH, 27);
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        
        
        lista = dadosDao.getListaIntervaloDadosConsumoPorConsumidor(id_c, c.getTime(), c2.getTime() );
        
        String s;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        
        for(int i = 0; i < lista.size(); i++){
        	
        	
            s = sdf.format(lista.get(i).getData_hora()) ;
            
        	series1.set( s , lista.get(i).getValor() );
        }
        
        
        series1.setLabel("Series 1");
        series1.setShowMarker(false);
        series1.setFill(false);
        /*
        series1.set("2014-01-01 08:00:00", 51);
        series1.set("2014-01-01 08:30:00", 22);
        series1.set("2014-01-01 10:20:00", 65);
        series1.set("2014-01-01 15:10:00", 35);
        */
        dateModel.addSeries(series1);
        //dateModel.setTitle("Area Chart");
        //dateModel.setLegendPosition("ne");
        //dateModel.setStacked(true);
        //dateModel.setShowPointLabels(false);
       ///dateModel.setShadow(false);
        //dateModel.setZoom(false);
        
        
        
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        
        DateAxis axis = new DateAxis("Dates");  // x
        axis.setTickAngle(-50);
        /*
        axis.setMin("2014-01-01 00:00:00");
        axis.setMax("2014-01-01 23:59:00");
        */
        axis.setTickFormat("%H:%M:%S");
        dateModel.getAxes().put(AxisType.X, axis);
    }
    
    
    public void onDateSelect(SelectEvent event) {
    	
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        System.out.println("teste2 " + format.format(event.getObject()));
    }
     
    public void click() {        
        System.out.println("teste1" + dataInicio.toString());
        LineChartSeries series1 = new LineChartSeries();
        
        dateModel = new LineChartModel();
        
        lista = dadosDao.getListaIntervaloDadosConsumoPorConsumidor(id, dataInicio, dataFim );
        
        String s;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        
        for(int i = 0; i < lista.size(); i++){
        	
        	
            s = sdf.format(lista.get(i).getData_hora()) ;
            
        	series1.set( s , lista.get(i).getValor() );
        }
        
        
        series1.setLabel("Series 1");
        series1.setShowMarker(false);
        series1.setFill(false);
        
        dateModel.addSeries(series1);
        
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        
        DateAxis axis = new DateAxis("Dates");  // x
        axis.setTickAngle(-50);
        /*
        axis.setMin("2014-01-01 00:00:00");
        axis.setMax("2014-01-01 23:59:00");
        */
        axis.setTickFormat("%H:%M:%S");
        dateModel.getAxes().put(AxisType.X, axis);
    }
    
    
    
    public Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
		this.id = id;
	}
    
    public LineChartModel getDateModel() {
        return dateModel;
    }
    
    public Consumidor getConsumidor(){
    	return consumidor;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }
 
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
 
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
 
}