package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.repository.PessoasRepository;

public class PessoasController {

    private PessoasRepository pessoasRepository;

    public PessoasController(PessoasRepository pessoasRepository){
        this.pessoasRepository = pessoasRepository;
    }

}
