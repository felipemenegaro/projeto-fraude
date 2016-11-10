package br.com.projetofraude.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.projetofraude.dao.ConsumidorDao;
import br.com.projetofraude.model.Consumidor;

@ManagedBean

public class MarkersView implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private MapModel simpleModel;
	
	private Marker marker;
	
    @PostConstruct
    public void init() {
    	simpleModel = new DefaultMapModel();
    	ConsumidorDao dao = new ConsumidorDao();
    	List<Consumidor> consumidores = dao.getListaConsumidores();
    	
        for(Consumidor consumidor : consumidores){
    		LatLng coord1 = new LatLng(consumidor.getLatitude(), consumidor.getLongitude());
	        simpleModel.addOverlay(new Marker(coord1, consumidor.getDescricao()));
        }
    }
    
 
    public MapModel getSimpleModel() {
        return simpleModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));
    }

    
    public Marker getMarker() {
        return marker;
    }
    
}