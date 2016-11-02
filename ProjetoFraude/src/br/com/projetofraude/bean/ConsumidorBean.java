package br.com.projetofraude.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.map.*;

import br.com.projetofraude.model.Consumidor;
import br.com.projetofraude.model.Consumidor.TipoConsumidor;
import br.com.projetofraude.dao.ConsumidorDao;



@ManagedBean(name = "consumidorBean")
@SessionScoped
public class ConsumidorBean {

	private Consumidor consumidor = new Consumidor();
    private ConsumidorDao consumidorDao = new ConsumidorDao();
    
    private MapModel mapModel;
    private String title; //descricao  
    private double lat;  // cons.lat
    private double lng;  //cons.long
    private String input;	//cons.endereco
    
    public ConsumidorBean() {
    	mapModel = new DefaultMapModel(); 
    }

    public void addMarker(ActionEvent actionEvent) {  
        mapModel.addOverlay(new Marker(new LatLng(lat, lng), title));  
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));  
    }  

    public void submit() {
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Form submitted", "Amount markers: " + mapModel.getMarkers().size() + ", Input: " + input));
    }

    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
    
    
    
    
    
    
    
    
    
    
    public MapModel getMapModel() {
            return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
            this.mapModel = mapModel;
    }

    public String getTitle() {
            return title;
    }

    public void setTitle(String title) {
            this.title = title;
    }

    public double getLat() {
            return lat;
    }

    public void setLat(double lat) {
            this.lat = lat;
    }

    public double getLng() {
            return lng;
    }

    public void setLng(double lng) {
            this.lng = lng;
    }

    public String getInput() {
            return input;
    }

    public void setInput(String input) {
            this.input = input;
    }

	
	
	
    public String adicionarConsumidor(){
    	consumidorDao.addConsumidor(consumidor);
    	consumidor.clear();
        return "consumidor";
    }

    public Consumidor getConsumidor() {
            return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
            this.consumidor = consumidor;
    }

    public List<Consumidor> getListaConsumidor() {
            List<Consumidor> lp;
            lp= consumidorDao.getListaConsumidores();//getLista
            return lp;
    }

    public TipoConsumidor[] getTiposConsumidor() {
                    return TipoConsumidor.values();
    }


}