package br.com.projetofraude.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.projetofraude.model.Fraude;
import br.com.projetofraude.dao.ConsumidorDao;
import br.com.projetofraude.dao.FraudeDao;

@ManagedBean(name = "fraudeBean")
@SessionScoped
public class FraudeBean {

	private Fraude fraude = new Fraude();
    private FraudeDao fraudeDao = new FraudeDao();

    List<Fraude> lista;
    
    
    
    public FraudeBean() {
    }
    
    public String adicionarFraude(){
    	fraudeDao.addFraude(fraude);
    	/*
    	Consumidor c = new Consumidor();
        
        c.setId(f.getId_consumidor());
        
        c = consumidor_dao.buscaConsumidor(c);
        
        c.setSuspeitaFraude(true);
        
        consumidor_dao.updateCliente(c);
    	*/
    	fraude.clear();
        return "fraude";
    }

	public Fraude getFraude() {
		return fraude;
	}

	public void setFraude(Fraude fraude) {
		this.fraude = fraude;
	}
	
	public List<Fraude> getListaFraude() {
		List<Fraude> lp;
		lp= fraudeDao.getListaFraudes();//getLista
		return lp;
	}
	
	
	public String buscaListaFraudePorConsumidor() {
       lista = fraudeDao.getListaFraudesPorConsumidor(fraude.getId_consumidor());
       return "fraudeConsumidor";
	}
	 
	public List<Fraude> getlistaFraudeConsumidor(){
		return lista;
	}
    
    
}