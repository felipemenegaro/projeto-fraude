package br.com.projetofraude.bean;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import br.com.projetofraude.dao.FraudeDao;
import br.com.projetofraude.model.Consumidor;
import br.com.projetofraude.model.DadosConsumo;
import br.com.projetofraude.model.Fraude;
import br.com.projetofraude.util.Grafico;
 

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChartView implements Serializable {
 

    private Grafico graficoDiario;
    private Grafico graficoMensal;
	private Grafico graficoSemanal;
	
    private List<DadosConsumo> lista;
    private DadosConsumo temp = new DadosConsumo();
    private DadosConsumoDao dadosDao = new DadosConsumoDao();
    private Consumidor consumidor;
    private ConsumidorDao consumidorDao = new ConsumidorDao();
    private Integer id;
	
    private Date dataInicio;
    private Date dataFim;
    private Date semana, dia, mes, dia_aux, semana_aux, mes_aux;
    
    private String labelDia, labelSemana, labelMes, labelIntevalo;
    
    private Calendar cInicio, cFim;
    
    @PostConstruct
    public void init() { 	
    	
        id = Integer.valueOf( FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id") );     
        consumidor = consumidorDao.buscaConsumidorID(id);
        
        
        
        Calendar cal = Calendar.getInstance();
        dia_aux = dia = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 00);
        dia_aux = dia = cal.getTime();

        
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        dataFim = cal.getTime();
        
        System.out.println("Dia   : "+ dia.toString());
        System.out.println("Inicio: "+ dataInicio.toString());
        System.out.println("Fim   : "+ dataFim.toString());
        
        graficoDiario = new Grafico();
        graficoDiario.constroiGrafico(id, dataInicio, dataFim);
        
        
        
        cal.setTime(dia);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        semana_aux = semana = cal.getTime();
        
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        dataFim = cal.getTime();
        
        System.out.println("Semana: "+ semana.toString());
        System.out.println("Inicio: "+ dataInicio.toString());
        System.out.println("Fim   : "+ dataFim.toString());
        
        graficoSemanal = new Grafico();
        graficoSemanal.constroiGrafico(id, dataInicio, dataFim); 
      
        
        cal.setTime(dia);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        mes_aux = mes = cal.getTime();
        
        dataInicio = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        dataFim = cal.getTime();
        
        System.out.println("Mes: "+ mes.toString());
        System.out.println("Inicio: "+ dataInicio.toString());
        System.out.println("Fim   : "+ dataFim.toString());
        
        graficoMensal = new Grafico();
        graficoMensal.constroiGrafico(id, dataInicio, dataFim);
        updateLabels();
    }
    
    public void selecaoDia(SelectEvent event) {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date)event.getObject());
        dia_aux = dia = cal.getTime();
        
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        dataFim = cal.getTime();
        
        graficoDiario = new Grafico();
        graficoDiario.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();

    }
    
    public void selecaoSemana(SelectEvent event) {
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date)event.getObject());
        
        //semanal
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        semana_aux = semana = cal.getTime();
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        dataFim = cal.getTime();
        
        graficoSemanal = new Grafico();
        graficoSemanal.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();

        
    }
    
    public void selecaoMes(SelectEvent event) {    
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date)event.getObject());
        
        cal.set(Calendar.DAY_OF_MONTH, 1);
        mes_aux = mes = cal.getTime();
        dataInicio = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        dataFim = cal.getTime();
        
        graficoMensal = new Grafico();
        graficoMensal.constroiGrafico(id, dataInicio, dataFim);
        updateLabels();

    }
     
    public void click() {        
        System.out.println("teste1" + dataInicio.toString());
        
        graficoDiario = new Grafico();
        graficoDiario.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();

    }
    
    
    public void botaoDiaAnterior() { 
    	Calendar cal = Calendar.getInstance();
        cal.setTime(dia_aux);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        dia_aux = dia = cal.getTime();
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        dataFim = cal.getTime(); 
        graficoDiario = new Grafico();
        graficoDiario.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();
    }
    
    public void botaoDiaSeguinte() { 
    	Calendar cal = Calendar.getInstance();
        cal.setTime(dia_aux);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        dia_aux = dia = cal.getTime();
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        dataFim = cal.getTime();
        graficoDiario = new Grafico();
        graficoDiario.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();
    }
    
    public void botaoSemanaAnterior() { 
    	Calendar cal = Calendar.getInstance();
        cal.setTime(semana_aux);
        cal.add(Calendar.DAY_OF_YEAR, -7);
        semana_aux = semana = cal.getTime();
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        dataFim = cal.getTime();
        graficoSemanal = new Grafico();
        graficoSemanal.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();
    }
    
    public void botaoSemanaSeguinte() { 
    	Calendar cal = Calendar.getInstance();
        cal.setTime(semana_aux);
        cal.add(Calendar.DAY_OF_YEAR, 7);
        semana_aux = semana = cal.getTime();
        dataInicio = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 7);
        dataFim = cal.getTime();  
        graficoSemanal = new Grafico();
        graficoSemanal.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();

    }
    
    public void botaoMesAnterior() { 
    	Calendar cal = Calendar.getInstance();
        cal.setTime(mes_aux); 

        dataInicio = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        dataInicio = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        dataFim = cal.getTime();
        
        mes_aux = dataInicio;
        
        graficoMensal = new Grafico();
        graficoMensal.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();

    }
    
    public void botaoMesSeguinte() { 
    	Calendar cal = Calendar.getInstance();
        cal.setTime(mes_aux);
        
        dataInicio = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        dataInicio = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        dataFim = cal.getTime();
        
        mes_aux = dataInicio;
        
        graficoMensal = new Grafico();
        graficoMensal.constroiGrafico(id, dataInicio, dataFim);    
        updateLabels();

    }
    
    
    
    public Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
		this.id = id;
	}
    
    public LineChartModel getGraficoDiario() {
        return graficoDiario;
    }
    
    public LineChartModel getGraficoMensal() {
        return graficoMensal;
    }
    
    public LineChartModel getGraficoSemanal() {
        return graficoSemanal;
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

	public Date getSemana() {
		return semana;
	}

	public void setSemana(Date semana) {
		this.semana = semana;
	}
	
	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}
	
	public Date getMes() {
		return mes;
	}

	public void setMes(Date mes) {
		this.mes = mes;
	}
	
	
	public List<Fraude> getlistaFraudeConsumidor(){
		return (new FraudeDao()).getListaFraudesPorConsumidor(id);
	}
	
	
	private void updateLabels(){
		Locale localeBR = new Locale("pt", "BR");
		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy", localeBR);
		labelDia = sdf.format(dia_aux);
		
		sdf = new SimpleDateFormat("MMMMM 'de' yyyy", localeBR);   	
		labelMes = sdf.format(mes_aux);
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(semana_aux);
		sdf = new SimpleDateFormat("dd/MM/yyyy");  
		Date d1, d2;	
		d1 = semana_aux;
		cal.add(Calendar.DAY_OF_YEAR, 7);
        d2 = cal.getTime();
		labelSemana = sdf.format(d1) + " a " + sdf.format(d2);	
		
	}
	
	public String getLabelDia(){
		 
        return labelDia;
	}
	
	public String getLabelMes(){
		
		return labelMes;
				
	}
	
	public String getLabelSemana(){
		
        return labelSemana;
	}
    
    
 
}