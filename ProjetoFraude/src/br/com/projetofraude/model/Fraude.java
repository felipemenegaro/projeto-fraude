package br.com.projetofraude.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "fraude")
public class Fraude implements Serializable{
	
	private static final long serialVersionUID = -8451678989006050647L;
	
	/*
	public enum EstadoFraude{
		SUSPEITA,CONFIRMADO,DESCARTADO,FINALIZADO
	}
	*/
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id_fraude;
	
	@Column
	private Integer id_consumidor;
	
	@Column
	private String tipo;
	
	@Column(length = 20)
	//@Enumerated(EnumType.STRING)
	//private EstadoFraude status;
	private String status;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date data_detecção;
	
	public Fraude() {
		this.id_fraude = 0;
		this.id_consumidor = 0;
		this.tipo = "";
		this.status = "";
		this.data_detecção = null;
	}

	public Integer getId_fraude() {
		return id_fraude;
	}

	public void setId_fraude(Integer id_fraude) {
		this.id_fraude = id_fraude;
	}

	public Integer getId_consumidor() {
		return id_consumidor;
	}

	public void setId_consumidor(Integer id_consumidor) {
		this.id_consumidor = id_consumidor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData_detecção() {
		return data_detecção;
	}

	public void setData_detecção(Date data) {
		this.data_detecção = data;
	}
	public void clear() {
		this.id_fraude = 0;
		this.id_consumidor = 0;
		this.tipo = "";
		this.status = "";
		this.data_detecção = null;
	}


}
