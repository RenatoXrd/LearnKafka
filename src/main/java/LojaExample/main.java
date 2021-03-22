package LojaExample;

import Demo.ConsumerDemo;
import LojaExample.Bean.Cliente;
import LojaExample.Bean.Produto;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        Produto p = new Produto("Computador",2000);
        Produto p2 = new Produto("Celular",4000);
        Produto p3 = new Produto("Tablet",1990.90);
        Produto p4 = new Produto("sua tia",1);
        Cliente c = new Cliente("Renato","428723872387",2000, Arrays.asList(p,p2,p3,p4));
        String clienteJson = c.toJson();
        System.out.println(clienteJson);
        System.out.println(Cliente.jsonToObject(clienteJson));
    }
}
