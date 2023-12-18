package com.example.exameninterfaces.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    Long id;
    private String nombre;
    private String correo;
}
