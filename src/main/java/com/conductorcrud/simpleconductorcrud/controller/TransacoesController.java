package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.Utils.DateAccountStatement;
import com.conductorcrud.simpleconductorcrud.model.Contas;
import com.conductorcrud.simpleconductorcrud.model.Transacoes;
import com.conductorcrud.simpleconductorcrud.repository.ContasRepository;
import com.conductorcrud.simpleconductorcrud.repository.TransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/transacoes"})
public class TransacoesController {

    @Autowired
    private TransacoesRepository transacoesRepository;
    @Autowired
    private ContasRepository contasRepository;

    @GetMapping
    public List findAll(){
        return this.transacoesRepository.findAll();
    }

    @GetMapping(path = {"/{idTransacoes}"})
    public ResponseEntity findById(@PathVariable long idTransacoes){
        return transacoesRepository.findById(idTransacoes)
                .map(transacao -> ResponseEntity.ok().body(transacao))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Recupera o extrato total
     * @param idConta
     * @return
     */
    @GetMapping(path={"/extratoTotal/{idConta}"})
    public ResponseEntity accountStatementTotal(@PathVariable("idConta") Long idConta) {
        Contas conta = this.contasRepository.getOne(idConta);
        if(conta == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(this.transacoesRepository.findByIdContaOrderByDataTransacao(conta));
    }

    /**
     * TODO
     * Recupera o extrato em um certo período passando o idConta como parâmetro da requisição
     * e a data limite no body da requisição
     * @param idConta
     * @param data
     * @return
     */
    @GetMapping(path={"/extratoParcial/{idConta}"})
    public ResponseEntity accountStatementPerPeriod(@PathVariable("idConta") Long idConta, @RequestBody DateAccountStatement data) {
        Contas conta = this.contasRepository.getOne(idConta);
        if(conta == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Transacoes create(@RequestBody Transacoes transacao){
        return transacoesRepository.save(transacao);
    }

    /**
     * Realiza operação de depósito em uma determinada conta
     * @param idConta
     * @param transacao
     * @return
     */
    @PostMapping(path={"/depositar/{idConta}"})
    public ResponseEntity deposit(@PathVariable("idConta") Long idConta, @RequestBody Transacoes transacao) {
        System.out.println(idConta);
        Contas conta = this.contasRepository.getOne(idConta);
        if(conta == null)
            return ResponseEntity.notFound().build();

        transacao.setIdConta(conta);
        transacao.setDataTransacao(new Date());
        transacao.setTipoTransacao("DEPOSITO");
        conta.setSaldo(conta.getSaldo() + transacao.getValor());
        this.transacoesRepository.save(transacao);
        this.contasRepository.save(conta);
        System.out.println(transacao.toString());
        System.out.println(conta.toString());
        return ResponseEntity.ok().build();
    }

    /**
     * Realiza operação de saque em uma determinada conta
     * @param idConta
     * @param transacao
     * @return
     */
    @PostMapping(path={"/sacar/{idConta}"})
    public ResponseEntity withdraw(@PathVariable("idConta") Long idConta, @RequestBody Transacoes transacao) {
        Contas conta = this.contasRepository.getOne(idConta);

        if(conta == null)
            return ResponseEntity.notFound().build();

        transacao.setIdConta(conta);
        transacao.setDataTransacao(new Date());
        transacao.setTipoTransacao("SAQUE");
        conta.setSaldo(conta.getSaldo() - transacao.getValor());
        this.transacoesRepository.save(transacao);
        this.contasRepository.save(conta);
        System.out.println(transacao.toString());
        System.out.println(conta.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/{idTransacoes}")
    public ResponseEntity update(@PathVariable("idTransacoes") long idTransacoes, @RequestBody Transacoes transacao){
        return this.transacoesRepository.findById(idTransacoes)
                .map(record -> {
                    record.setDataTransacao(transacao.getDataTransacao());
                    record.setValor(transacao.getValor());
                    Transacoes updatedTransacoes = transacoesRepository.save(record);
                    return ResponseEntity.ok().body(updatedTransacoes);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{idTransacoes}"})
    public ResponseEntity<?> delete(@PathVariable long idTransacoes) {
        return this.transacoesRepository.findById(idTransacoes)
                .map(transacao -> {
                    transacoesRepository.deleteById(idTransacoes);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
