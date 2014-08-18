package com.wallmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallmart.model.entrega.Mapa;
import com.wallmart.repository.interfaces.IMapaRepository;

@Service
@Transactional
public class MapaServiceImpl {
	
	@Autowired
	private IMapaRepository mapaRepository;
	
	public Long salvar(Mapa mapa){
		return mapaRepository.save(mapa).getId();
	}
	
	public Mapa buscarPorId(Long id){
		return mapaRepository.loadById(id);
	}
	
	public List<Mapa> buscarTodos(){
		return mapaRepository.listAll();
	}
	
	public void deletar(Mapa mapa)
	{
		mapaRepository.delete(mapa);
	}
	
	public Mapa getMapaByNome(String nome) {
		return mapaRepository.getByNome(nome);
	}

	
	public void setMapaRepository(IMapaRepository mapaRepository) {
		this.mapaRepository = mapaRepository;
	}

}
