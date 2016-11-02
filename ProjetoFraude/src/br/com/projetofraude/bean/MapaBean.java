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

	private MapModel advancedModel;

	private Marker marker;

	@PostConstruct
	public void init() {
		advancedModel = new DefaultMapModel();
		simpleModel = new DefaultMapModel();

		// Shared coordinates

		System.out.println("Teste1");

		LatLng coord1 = new LatLng(-28.95112, -49.4710);
		LatLng coord2 = new LatLng(-28.94520, -49.4650);
		LatLng coord3 = new LatLng(-28.93880, -49.4820);
		LatLng coord4 = new LatLng(-28.94200, -49.4900);

		// Icons and Data

		/*
		 * simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));
		 * simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
		 * simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
		 * simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
		 * 
		 * 
		 * advancedModel.addOverlay(new Marker(coord1, "1", "imagem1.png",
		 * "http://maps.google.com/mapfiles/ms/micons/green-dot.png"));
		 * advancedModel.addOverlay(new Marker(coord2, "2", "imagem2.png",
		 * "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
		 * advancedModel.addOverlay(new Marker(coord4, "3", "imagem3.png",
		 * "http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
		 * advancedModel.addOverlay(new Marker(coord3, "4", "imagem4.png",
		 * "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));
		 */

		insereMarcadores();

	}

	public MapaBean() {

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
		FacesContext.getCurrentInstance().getExternalContext().redirect("consumidor.jsf");
	}

	public void insereMarcadores() {
		List<Consumidor> lista = new ArrayList<Consumidor>();// =
																// consumidorDao.getListaConsumidores();

		lista = consumidorDao.getListaConsumidores();

		int i;
		LatLng coord;
		String s;

		for (i = 0; i < lista.size(); i++) {
			coord = new LatLng(lista.get(i).getLatitude(), lista.get(i).getLongitude());
			// simpleModel.addOverlay(new Marker(coord,
			// lista.get(i).getDescricao()));

			if (lista.get(i).isSuspeitaFraude()) {
				s = "http://maps.google.com/mapfiles/ms/micons/red-dot.png";
			} else {
				s = "http://maps.google.com/mapfiles/ms/micons/green-dot.png";
			}

			simpleModel.addOverlay(new Marker(coord, lista.get(i).getDescricao(), "imagem1.png", s));
		}
	}
}