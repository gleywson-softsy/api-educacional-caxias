package br.com.softsy.educacional.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softsy.educacional.dto.CadastroConcursoDTO;
import br.com.softsy.educacional.dto.ConcursoDTO;
import br.com.softsy.educacional.service.ConcursoService;

@RestController
@RequestMapping("/concursos")
public class ConcursoController {

    @Autowired
    private ConcursoService concursoService;

    @GetMapping
    public ResponseEntity<List<ConcursoDTO>> listar() {
        List<ConcursoDTO> concursos = concursoService.listarTudo();
        return ResponseEntity.ok(concursos);
    }

    @GetMapping("/{idConcurso}")
    public ResponseEntity<ConcursoDTO> buscarPorId(@PathVariable Long idConcurso) {
        ConcursoDTO concursoDto = concursoService.buscarPorId(idConcurso);
        return ResponseEntity.ok(concursoDto);
    }

    @GetMapping("/conta/{idConta}")
    public ResponseEntity<List<ConcursoDTO>> buscarPorIdConta(@PathVariable Long idConta) {
        List<ConcursoDTO> concursos = concursoService.buscarPorIdConta(idConta);
        return ResponseEntity.ok(concursos);
    }

    @PostMapping
    public ResponseEntity<ConcursoDTO> cadastrar(@RequestBody @Valid CadastroConcursoDTO dto) {
        ConcursoDTO concursoDTO = concursoService.salvar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(concursoDTO.getIdConcurso()).toUri();
        return ResponseEntity.created(uri).body(concursoDTO);
    }

    @PutMapping
    public ResponseEntity<ConcursoDTO> atualizar(@RequestBody @Valid CadastroConcursoDTO dto) {
        ConcursoDTO concursoDTO = concursoService.atualizar(dto);
        return ResponseEntity.ok(concursoDTO);
    }

    @PutMapping("/{idConcurso}/ativar")
    public ResponseEntity<?> ativar(@PathVariable Long idConcurso) {
        concursoService.ativaDesativa('S', idConcurso);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idConcurso}/desativar")
    public ResponseEntity<?> desativar(@PathVariable Long idConcurso) {
        concursoService.ativaDesativa('N', idConcurso);
        return ResponseEntity.ok().build();
    }
}
