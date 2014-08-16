package com.wallmart.model.mapa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Entity;

@Entity
@SequenceGenerator(sequenceName = "SEQ_MAPA" , name = "SEQ_MAPA",allocationSize=1,initialValue=1)
public class Mapa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7911488049323665720L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAPA")
	private Long id;
	
	private String nome;
	
	@OneToMany
	@JoinColumn(name = "MAPA_ID")
	private List<Rota> rotas;

	public Mapa() {
		super();
	}
	
	public Mapa(String nome) {
		this.nome = nome;
	}

	public Mapa(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Rota> getRotas() {
		return rotas;
	}
	
	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	public void adicionarRota(String origem, String destino, int distancia) {
		List<Rota> rotas = new ArrayList<Rota>();
		if(this.getRotas() != null){
			rotas = this.getRotas();
		}
		rotas.add(new Rota(origem,destino,distancia));
		setRotas(rotas);
	}
	
}