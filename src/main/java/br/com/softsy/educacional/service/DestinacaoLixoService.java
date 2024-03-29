package br.com.softsy.educacional.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.educacional.dto.DestinacaoLixoDTO;
import br.com.softsy.educacional.infra.exception.UniqueException;
import br.com.softsy.educacional.model.DestinacaoLixo;
import br.com.softsy.educacional.repository.DestinacaoLixoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DestinacaoLixoService {

	@Autowired
	private DestinacaoLixoRepository repository;
	
	public List<DestinacaoLixo> listarTudo(){
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public DestinacaoLixoDTO buscarPorId(Long id){
		return new DestinacaoLixoDTO(repository.getReferenceById(id));
	}
	
	@Transactional
	public DestinacaoLixoDTO salvar(DestinacaoLixoDTO dto) {
		validarDestinacaoLixo(dto.getDestinacaoLixo());
		
		DestinacaoLixo destinacao = criarDestinacaoAPartirDTO(dto);
		
		destinacao = repository.save(destinacao);
		return new DestinacaoLixoDTO(destinacao);
	}
	
	private DestinacaoLixo criarDestinacaoAPartirDTO(DestinacaoLixoDTO dto) {
		DestinacaoLixo destinacao = new DestinacaoLixo();
		BeanUtils.copyProperties(dto, destinacao, "idDestinacaoLixo", "ativo", "dataCadastro");
		destinacao.setAtivo('S');
		destinacao.setDataCadastro(LocalDateTime.now());
		return destinacao;
	}
	
	@Transactional
	public DestinacaoLixoDTO atualizar(DestinacaoLixoDTO dto) {
		DestinacaoLixo destinacao = repository.getReferenceById(dto.getIdDestinacaoLixo());
		atualizaDados(destinacao, dto);
		return new DestinacaoLixoDTO(destinacao);
	}
	
	@Transactional
	public void ativaDesativa(char status, Long idDestinacaoLixo) {
		DestinacaoLixo destinacao = repository.getReferenceById(idDestinacaoLixo);
		destinacao.setAtivo(status);
	}
	
	
	private void validarDestinacaoLixo(String destinacaoLixo) {
		Optional<DestinacaoLixo> destinacaoExistente = repository.findByDestinacaoLixo(destinacaoLixo).stream().findFirst();
		if(destinacaoExistente.isPresent()) {
			throw new UniqueException("Essa destinação já existe.");
		}
	}
	
	private void atualizaDados(DestinacaoLixo destino, DestinacaoLixoDTO origem) {
		BeanUtils.copyProperties(origem, destino, "idDestinacaoLixo", "ativo", "dataCadastro");
		
	}
}
