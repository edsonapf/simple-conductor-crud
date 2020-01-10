package com.conductorcrud.simpleconductorcrud.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DateAccountStatement {
    private Date dataInicial;
    private Date dataFinal;

}
