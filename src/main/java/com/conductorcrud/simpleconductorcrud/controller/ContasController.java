package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.model.Contas;
import com.conductorcrud.simpleconductorcrud.repository.ContasRepository;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/contas"})
public class ContasController {

    private ContasRepository contasRepository;

    public ContasController(ContasRepository contasRepository){
        this.contasRepository = contasRepository;
    }

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

    @PostMapping
    public Contas create(@RequestBody Contas conta){
        return contasRepository.save(conta);
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
