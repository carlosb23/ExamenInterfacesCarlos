package com.example.exameninterfaces;

import com.example.exameninterfaces.models.Cliente;
import com.example.exameninterfaces.models.Coche;

import java.util.ArrayList;

public class Session {

    private static Cliente clienteactual = null;
    private static ArrayList<Cliente> clientes = new ArrayList<>(0);

    private static Coche cocheactual = null;
    private static ArrayList<Coche> coches = new ArrayList<>(0);


    public static Cliente getClienteactual() {
        return clienteactual;
    }

    public static void setClienteactual(Cliente clienteactual) {
        Session.clienteactual = clienteactual;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        Session.clientes = clientes;
    }

    public static Coche getCocheactual() {
        return cocheactual;
    }

    public static void setCocheactual(Coche cocheactual) {
        Session.cocheactual = cocheactual;
    }

    public static ArrayList<Coche> getCoches() {
        return coches;
    }

    public static void setCoches(ArrayList<Coche> coches) {
        Session.coches = coches;
    }
}
