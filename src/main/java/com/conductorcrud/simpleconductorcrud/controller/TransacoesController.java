package com.conductorcrud.simpleconductorcrud.controller;

import com.conductorcrud.simpleconductorcrud.repository.TransacoesRepository;

public class TransacoesController {

    private TransacoesRepository transacoesRepository;

    public TransacoesController(TransacoesRepository transacoesRepository){
        this.transacoesRepository = transacoesRepository;
    }

}
