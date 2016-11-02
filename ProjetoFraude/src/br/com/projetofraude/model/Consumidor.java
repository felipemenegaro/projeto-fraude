package br.com.projetofraude.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "consumidor")
public class Consumidor implements Serializable{
    
	private static final long serialVersionUID = 1L;

	
	public enum TipoConsumidor{
		RESIDENCIAL, COMERCIAL, INDUSTRIAL, RURAL
	}
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column
    private String descricao;
    
    @Column
    private String endereço;
    	
    @Column
    private double latitude;
    
    @Column
    private double longitude;
    
    @Column
    private boolean suspeitaFraude;
    
    @Column
    @Enumerated (EnumType.STRING)
    private TipoConsumidor tipo;
       
    
    public Consumidor() {
		this.id = 0;
		this.descricao = "";
		this.endereço = "";
		this.latitude = 0;
		this.longitude = 0;
		this.suspeitaFraude = false;
		this.tipo = null;
	}
    
    

    public Integer getId() {
        return id;
    }

	public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isSuspeitaFraude() {
        return suspeitaFraude;
    }

    public void setSuspeitaFraude(boolean suspeitaFraude) {
        this.suspeitaFraude = suspeitaFraude;
    }
   
    public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public TipoConsumidor getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoConsumidor tipo) {
		this.tipo = tipo;
	}

	public void clear(){
    	this.id = 0;
		this.descricao = "";
		this.endereço = "";
		this.latitude = 0;
		this.longitude = 0;
		this.suspeitaFraude = false;
		this.tipo = null;

    }

}