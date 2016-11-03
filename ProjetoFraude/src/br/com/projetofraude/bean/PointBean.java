
package br.com.projetofraude.bean;

import br.com.projetofraude.dao.ConsumidorDao;
import br.com.projetofraude.model.Consumidor;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.*;

 
@ManagedBean (name = "pointBean")
@SessionScoped
public class PointBean implements Serializable{


    private MapModel emptyModel;
    private Marker currentMarker;
    private ConsumidorDao dao;
    private Consumidor consumidor;


    public PointBean() {
        super();
    }

    @PostConstruct
    public void init() {
    	dao = new ConsumidorDao();
        emptyModel = new DefaultMapModel();    
        consumidor = new Consumidor();
    }
    
    
 
    public void saveConsumidor(){
        
        if(consumidor.getDescricao() == null || consumidor.getEndereço() == null || consumidor.getLongitude() == 0){

            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Insira uma localização" ));
        }else{
        	consumidor.setSuspeitaFraude(false);
            dao.addConsumidor(consumidor);
   
            clearConsumidor();          
               
            try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Operação realizada com sucesso" ));
            
        }
    }
    
    public void addMarker() {
        currentMarker = new Marker(new LatLng(consumidor.getLatitude(), consumidor.getLongitude()), consumidor.getDescricao());

        emptyModel.addOverlay((currentMarker));
        consumidor.setLatitude(currentMarker.getLatlng().getLat());
        consumidor.setLongitude(currentMarker.getLatlng().getLng());

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + consumidor.getLatitude() + ", Lng:" + consumidor.getLongitude()));
    }

    
//    public void onMarkerDrag(MarkerDragEvent event) {
//        
//        currentMarker = event.getMarker();
//        client.setLatitude(currentMarker.getLatlng().getLat());
//        client.setLongitude(currentMarker.getLatlng().getLng());
//
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + currentMarker.getLatlng().getLat() + ", Lng:" + currentMarker.getLatlng().getLng()));    
//    }
    
    
    
    
 

    
    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor point) {
        this.consumidor = point;
    }
    
    public void clearConsumidor() {
    	consumidor.clear();
    }
    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public void setEmptyModel(MapModel emptyModel) {
        this.emptyModel = emptyModel;
    }

    
    @SuppressWarnings("unchecked")
    public List<Consumidor>getAllConsumidor(){
        return dao.getListaConsumidores();
    }
}