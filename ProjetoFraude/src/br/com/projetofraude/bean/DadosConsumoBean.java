package br.com.projetofraude.bean;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.projetofraude.model.DadosConsumo;
import br.com.projetofraude.dao.DadosConsumoDao;

@ManagedBean(name = "dadosConsumoBean")
@SessionScoped
public class DadosConsumoBean {

	private DadosConsumo temp = new DadosConsumo();
    private DadosConsumoDao dadosDao = new DadosConsumoDao();
    
    List<DadosConsumo> lista;
    
    private Date data_inicio, data_fim;
    
    
	public DadosConsumoBean() {
    }
    /*
    public String adicionarFraude(){
    	fraudeDao.addFraude(fraude);
    	fraude.clear();
        return "fraude";
    }
*/
	
	public void inserirDados() {
		new DadosConsumoDao().addDadosConsumo(temp);
		temp.clear();
	}
	
	
	public DadosConsumo getDadosConsumo() {
		return temp;
	}

	public void setFraude(DadosConsumo d) {
		this.temp = d;
	}
	
	public List<DadosConsumo> getListaDadosConsumo() {
		List<DadosConsumo> lp;
		lp= dadosDao.getListaDadosConsumo();
		return lp;
	}
	
	
	public String buscaIntevaloDadosPorConsumidor() {
       lista = dadosDao.getListaIntervaloDadosConsumoPorConsumidor(temp.getId_consumidor(), this.data_inicio, this.data_fim);

       return "dadosConsumo";
	}
	 
	public List<DadosConsumo> getlistaDados(){
		return lista;
	}
    
	
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Date getData_fim() {
		return data_fim;
	}
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
    
}