package com.conductorcrud.simpleconductorcrud.model;

import java.io.Serializable;

public class PessoasId implements Serializable {

    private String cpf;
    private Long idPessoa;

    public PessoasId(){}
    public PessoasId(Long idPessoa, String cpf) {
        this.idPessoa = idPessoa;
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PessoasId other = (PessoasId) obj;
        if (idPessoa == other.idPessoa)
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        }
        else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }
}
