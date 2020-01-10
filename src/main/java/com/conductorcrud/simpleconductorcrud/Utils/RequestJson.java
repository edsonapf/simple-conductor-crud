package com.conductorcrud.simpleconductorcrud.Utils;

import com.conductorcrud.simpleconductorcrud.model.Contas;
import lombok.Data;

@Data
public class RequestJson {
    private String cpf;
    private Contas conta;
}
