package br.com.projetofraude.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "dadosConsumo")
public class DadosConsumo implements Serializable{
    
	private static final long serialVersionUID = -5681626073628261805L;

	@Id
	@Column
    private Integer id_consumidor;
    
	
	@Column
    private float valor;
	
	@Id
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private Date data_hora;

	public DadosConsumo() {
		this.id_consumidor = 0;
		this.valor = 0;
		this.data_hora = null;
	}

	public Integer getId_consumidor() {
		return id_consumidor;
	}

	public void setId_consumidor(Integer id_consumidor) {
		this.id_consumidor = id_consumidor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getData_hora() {
		return data_hora;
	}

	public void setData_hora(Date data_hora) {
		this.data_hora = data_hora;
	}
    
	public void clear(){
		this.id_consumidor = 0;
		this.valor = 0;
		this.data_hora = null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		DadosConsumo other = (DadosConsumo) obj;
		
		if (data_hora == null) {
			if (other.data_hora != null)
				return false;
		} else if (!data_hora.equals(other.data_hora))
			return false;
		
		if (id_consumidor == null) {
			if (other.id_consumidor != null)
				return false;
		} else if (!id_consumidor.equals(other.id_consumidor))
			return false;
		//if (Float.floatToIntBits(valor) != Float.floatToIntBits(other.valor))
		//	return false;
		return true;
	}

	
	
}