package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.repository.ContasRepository;

public class ContasController {

    private ContasRepository contasRepository;

    public ContasController(ContasRepository contasRepository){
        this.contasRepository = contasRepository;
    }

}
