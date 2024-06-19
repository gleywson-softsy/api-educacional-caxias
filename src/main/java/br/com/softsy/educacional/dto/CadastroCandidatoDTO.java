package br.com.softsy.educacional.dto;



import br.com.softsy.educacional.model.Candidato;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CadastroCandidatoDTO {
	private Long idCandidato;
	private Long contaId;
	private Long pessoaId;
	private String candidato;
	private Long ofertaConcursoId;
	private Long tipoIngressoId;
	private String classificacao;
	private Long aluno;
	private String aprovado;
	private Long  usuarioAprovacaoId;
	
public CadastroCandidatoDTO(Candidato candidato) {
		
		this.idCandidato = candidato.getIdCandidato();
		this.contaId = candidato.getConta().getIdConta();
		this.pessoaId = candidato.getPessoa().getIdPessoa();
		this.candidato = candidato.getCandidato();
		this.ofertaConcursoId = candidato.getOfertaConcurso() != null ? candidato.getOfertaConcurso().getIdOfertaConcurso() : null;
		this.tipoIngressoId = candidato.getTipoIngresso() != null ? candidato.getTipoIngresso().getIdTipoIngresso() : null;
		this.classificacao = candidato.getClassificacao();
		this.aluno = candidato.getAluno();
		this.aprovado = candidato.getAprovado();
		this.usuarioAprovacaoId = candidato.getUsuarioAprovacao() != null ? candidato.getUsuarioAprovacao().getIdUsuario() : null;;
	
		
	}

}
