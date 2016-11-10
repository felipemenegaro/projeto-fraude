package br.com.projetofraude.bean;

import br.com.projetofraude.model.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

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
public class MapaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private MapModel simpleModel;
	private ConsumidorDao consumidorDao = new ConsumidorDao();
	//private MapModel advancedModel;
	private Marker marker;

	public MapaBean() {
	}
	
	@PostConstruct
	public void init() {
		//advancedModel = new DefaultMapModel();
		simpleModel = new DefaultMapModel();
		
		insereMarcadores();
	}

	

	public MapModel getAdvancedModel() {
		// return advancedModel;
		return simpleModel;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public Marker getMarker() {
		return marker;
	}

	public void info() throws IOException {
		String id = marker.getTitle();
		//String id = marker.getId();
		
		Consumidor c = (Consumidor) marker.getData();
		id = c.getId().toString();
		
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("pages/tabelaDados.jsf?id=" + id);
	}

	
	public void insereMarcadores() {
		List<Consumidor> lista = new ArrayList<Consumidor>();// =
																// consumidorDao.getListaConsumidores();

		lista = consumidorDao.getListaConsumidores();

		int i;
		LatLng coord;
		String s;
		Marker m;
		
		
		for (i = 0; i < lista.size(); i++) {
			coord = new LatLng(lista.get(i).getLatitude(), lista.get(i).getLongitude());

			if (lista.get(i).isSuspeitaFraude()) {
				s = "faces/imagens/red-dot.png";
			} else {
				s = "faces/imagens/green-dot.png";
			}
			
			m = new Marker(coord);
			m.setTitle(lista.get(i).getDescricao());
			m.setData(lista.get(i));
			m.setIcon(s);	
			//m.setId( lista.get(i).getId().toString() );
			
			simpleModel.addOverlay(m);
			
		}
	}
	
	public void mapaInd() {
		simpleModel.getMarkers().clear();

	}
	
}