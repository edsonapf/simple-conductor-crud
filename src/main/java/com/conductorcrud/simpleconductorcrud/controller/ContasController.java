package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.model.Contas;
import com.conductorcrud.simpleconductorcrud.model.Pessoas;
import com.conductorcrud.simpleconductorcrud.repository.ContasRepository;
import com.conductorcrud.simpleconductorcrud.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/contas"})
public class ContasController {

    @Autowired
    private ContasRepository contasRepository;

    @Autowired
    private PessoasRepository pessoasRepository;

    @GetMapping
    public List findAll(){
        return this.contasRepository.findAll();
    }

    @GetMapping(path={"/{idConta}"})
    public ResponseEntity findById(@PathVariable Long idConta){
        return contasRepository.findById(idConta)
                .map(conta -> ResponseEntity.ok().body((conta)))
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping(path={"/{cpf}"})
    public ResponseEntity create(@PathVariable("cpf") String cpf, @RequestBody Contas conta){
        Pessoas pessoa = pessoasRepository.findByCpf(cpf);
        if(pessoa == null){
            return ResponseEntity.notFound().build();
        }

        conta.setIdPessoa(pessoa);
        conta.setDataCriacao(new Date());
        return ResponseEntity.ok().body(contasRepository.save(conta));
    }

    @PutMapping(value="/{idConta}")
    public ResponseEntity update(@PathVariable("idConta") Long idConta, @RequestBody Contas conta){
        return contasRepository.findById(idConta)
                .map(record -> {
                    record.setDataCriacao(conta.getDataCriacao());
                    record.setLimiteSaqueDiario(conta.getLimiteSaqueDiario());
                    record.setTipoConta(conta.getTipoConta());
                    record.setSaldo(conta.getSaldo());
                    Contas updatedConta = contasRepository.save(record);
                    return ResponseEntity.ok().body(updatedConta);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path={"/{idConta}"})
    public ResponseEntity<?> delete(@PathVariable Long idConta){
        return contasRepository.findById(idConta)
                .map(conta -> {
                    contasRepository.deleteById(idConta);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
