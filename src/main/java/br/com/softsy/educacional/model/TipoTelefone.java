package br.com.softsy.educacional.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_TIPO_TELEFONE")
@Data
public class TipoTelefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_TELEFONE")
	private Long idTipoTelefone;
	
	@Column(name = "TIPO_TELEFONE", nullable = false, unique = true)
	private String tipoTelefone;
	
	@Column(name = "DT_CADASTRO", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime dataCadastro;
	
	@OneToMany(mappedBy = "tipoTelefone", cascade = CascadeType.ALL)
	private Set<EscolaTelefone> tipotelefone = new HashSet<>();

}
