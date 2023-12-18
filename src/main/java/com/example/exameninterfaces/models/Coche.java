package com.example.exameninterfaces.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coche {

    private String matricula;
    private String modelo;
    private LocalDate fechaentrada;
    private LocalDate fechasalida;
    private String cliente;
    private String tarifa;
    private Integer coste;

}
