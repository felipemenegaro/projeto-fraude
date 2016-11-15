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
public class MapaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private MapModel simpleModel;
	private ConsumidorDao consumidorDao = new ConsumidorDao();
	private Marker marker;

	public MapaBean() {
	}
	
	@PostConstruct
	public void init() {
		simpleModel = new DefaultMapModel();
		insereMarcadores();
	}
	
	public void insereMarcadores() {
		
		List<Consumidor> lista = consumidorDao.getListaConsumidores();

		int i;
		LatLng coord;
		String s;
		Marker m;
		
		for (i = 0; i < lista.size(); i++) {
			
			coord = new LatLng(lista.get(i).getLatitude(), lista.get(i).getLongitude());			
			
			if (lista.get(i).isSuspeitaFraude()) {
				s = "faces/imagens/residencial-red.png";
			} else {
				s = "faces/imagens/residencial-green.png";
			}
			
			m = new Marker(coord);
			m.setTitle(lista.get(i).getDescricao());
			m.setData(lista.get(i));
			m.setIcon(s);	
			
			simpleModel.addOverlay(m);	
		}
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public void info() throws IOException {
		
		Consumidor c = (Consumidor) marker.getData();
		
		String id = c.getId().toString();
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("pages/tabelaDados.jsf?id=" + id);
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}	

	public Marker getMarker() {
		return marker;
	}
	
}