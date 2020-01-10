package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.Utils.RequestJson;
import com.conductorcrud.simpleconductorcrud.model.Contas;
import com.conductorcrud.simpleconductorcrud.model.Pessoas;
import com.conductorcrud.simpleconductorcrud.repository.ContasRepository;
import com.conductorcrud.simpleconductorcrud.repository.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Cria conta passando o cpf e os dados da conta no body da requisição
     * ex:
     *  cpf: XXXXXXXXXXX
     *  conta: {
     *      saldo: 10.0,
     *      limiteSaqueDiario: 10.o,
     *      tipoConta: 1
     *  }
     * @param req
     * @return
     */
    @PostMapping(path={"/criarConta"})
    public ResponseEntity create(@RequestBody RequestJson req){
        Pessoas pessoa = pessoasRepository.findByCpf(req.getCpf());
        if(pessoa == null){
            return ResponseEntity.notFound().build();
        }

        req.getConta().setIdPessoa(pessoa);
        req.getConta().setFlagAtivo(true);
        req.getConta().setDataCriacao(new Date());
        contasRepository.save(req.getConta());
        return ResponseEntity.ok().body("Conta criada com sucesso!");
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

    /**
     * Ativa conta passando o idConta como parâmetro da requisição
     * @param idConta
     * @return
     */
    @PutMapping(path={"/ativarConta/{idConta}"})
    public ResponseEntity<String> activateAccount(@PathVariable("idConta") Long idConta) {
        Contas conta = this.contasRepository.getOne(idConta);
        if(conta.getFlagAtivo())
            return ResponseEntity.noContent().build();

        conta.setFlagAtivo(true);
        this.contasRepository.save(conta);
        return ResponseEntity.ok().body("Usuário ativado!");
    }

    /**
     * Desativa conta passando o idConta como parâmetro da requisição
     * @param idConta
     * @return
     */
    @PutMapping(path={"/desativarConta/{idConta}"})
    public ResponseEntity<String> desactivateAccount(@PathVariable("idConta") Long idConta) {
        Contas conta = this.contasRepository.getOne(idConta);
        if(!conta.getFlagAtivo())
            return ResponseEntity.noContent().build();

        conta.setFlagAtivo(false);
        this.contasRepository.save(conta);
        return ResponseEntity.ok().body("Usuário desativado!");
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
