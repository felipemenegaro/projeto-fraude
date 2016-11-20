package br.com.projetofraude.bean;

import br.com.projetofraude.model.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.projetofraude.dao.ConsumidorDao;

@ManagedBean
@ViewScoped
public class MarkersView implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private MapModel simpleModel, mapaComercio, mapaResidencias, mapaIndustria;
	private ConsumidorDao consumidorDao = new ConsumidorDao();
	private Marker marker;
	
	public MarkersView(){	
	}
	
    @PostConstruct
    public void init() {																				
    	simpleModel = new DefaultMapModel();
    	mapaComercio = new DefaultMapModel();
    	mapaIndustria = new DefaultMapModel();
    	mapaResidencias = new DefaultMapModel();
    	
    	insereMarcadores();	
    }
  
	public void insereMarcadores() {
		
    	List<Consumidor> lista = consumidorDao.getListaConsumidores();

    	int i;
		LatLng coord;
		String s = null;
		Marker m;
		
		for (i = 0; i < lista.size(); i++) {
			
			coord = new LatLng(lista.get(i).getLatitude(), lista.get(i).getLongitude());			
			
			m = new Marker(coord);
			m.setTitle(lista.get(i).getDescricao());
			m.setData(lista.get(i));
		
			
			if (lista.get(i).isSuspeitaFraude()) {
				s = "../imagens/red-dot.png";
			} else {
				s = "../imagens/green-dot.png";
			}
			m.setIcon(s);
			
			if(lista.get(i).getTipo().toString() == "RESIDENCIAL"){	
				mapaResidencias.addOverlay(m);
			}else if(lista.get(i).getTipo().toString() == "COMERCIAL"){
				mapaComercio.addOverlay(m);
			}else if(lista.get(i).getTipo().toString() == "INDUSTRIAL"){
				mapaIndustria.addOverlay(m);
			}
			
			
			/*		 
			boolean f = lista.get(i).isSuspeitaFraude();
			
			if(lista.get(i).getTipo().toString() == "RESIDENCIAL"){
				if (f) {
					s = "../imagens/residencial-red.png";
				} else {
					s = "../imagens/residencial-green.png";
				}
				m.setIcon(s);
				mapaResidencias.addOverlay(m);
			}else if(lista.get(i).getTipo().toString() == "COMERCIAL"){
				if (f) {
					s = "../imagens/comercial-red.png";
				} else {
					s = "../imagens/comercial-green.png";
				}
				m.setIcon(s);
				mapaComercio.addOverlay(m);
			}else if(lista.get(i).getTipo().toString() == "INDUSTRIAL"){
				if (f) {
					s = "../imagens/industrial-red.png";
				} else {
					s = "../imagens/industrial-green.png";
				}
				m.setIcon(s);
				mapaIndustria.addOverlay(m);
			}
			 */
			
			simpleModel.addOverlay(m);
		}
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}
	
	public void info() throws IOException {
		
		Consumidor c = (Consumidor) marker.getData();
		
		String id = c.getId().toString();
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Projeto/pages/tabelaDados.jsf?id=" + id);
	}
	
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public Marker getMarker() {
        return marker;
    }

	public MapModel getMapaComercio() {
		return mapaComercio;
	}

	public MapModel getMapaResidencias() {
		return mapaResidencias;
	}

	public MapModel getMapaIndustria() {
		return mapaIndustria;
	}
	/*
	public void comercio(){
		List<Marker> lista = simpleModel.getMarkers();
		for (int i = 0; i < lista.size(); i++) {
			
			lista.get(i).setVisible(false);	
		
		}
	}
    */
    
    
}