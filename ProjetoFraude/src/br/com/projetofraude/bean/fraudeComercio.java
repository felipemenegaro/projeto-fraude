package br.com.projetofraude.bean;

import br.com.projetofraude.model.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.projetofraude.dao.ConsumidorDao;
import br.com.projetofraude.dao.FraudeDao;

@ManagedBean
@ViewScoped
public class fraudeComercio implements Serializable {

	private static final long serialVersionUID = 1L;
	private MapModel simpleModel;
	private FraudeDao fraudeDao = new FraudeDao();
	private ConsumidorDao consumidorDao = new ConsumidorDao();
	private Marker marker;

	public fraudeComercio() {
	}
	
	@PostConstruct
	public void init() {
		simpleModel = new DefaultMapModel();
		insereMarcadores();
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}
	
	public void insereMarcadores() {
		List<Fraude> lista = new ArrayList<Fraude>();
		List<Consumidor> list = new ArrayList<Consumidor>();
		lista = fraudeDao.getListaFraudes();
		list = consumidorDao.getListaConsumidores();

		int i, j, set = 0;
		LatLng coord = null;
		String s;
		Marker m = null;
		Integer id_consumidor;
		String desc = null;
		
		for (i = 0; i < lista.size(); i++) {
			id_consumidor = lista.get(i).getId_consumidor();
			for (j = 0; j < list.size(); j++) {
				if(id_consumidor == list.get(j).getId() ){
					coord = new LatLng(list.get(j).getLatitude(), list.get(j).getLongitude());
					desc = list.get(j).getDescricao();
					set = j;
				}
			}
			if(list.get(set).getTipo().toString().equals("COMERCIAL")){
				m = new Marker(coord);
				m.setTitle(desc);
				m.setData(lista.get(i));
				s = "../imagens/red-dot.png";
				m.setIcon(s);
				simpleModel.addOverlay(m);
			}
		}
		
	}

	
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public Marker getMarker() {
        return marker;
    }
	
}