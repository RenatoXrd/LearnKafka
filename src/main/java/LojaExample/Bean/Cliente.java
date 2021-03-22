package LojaExample.Bean;

import com.google.gson.Gson;

import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private double saldo;
    private List<Produto> carrinho;

    public Cliente() {

    }

    public Cliente(String nome, String cpf, double saldo, List<Produto> carrinho) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = saldo;
        this.carrinho = carrinho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    public String toJson(){
        Gson conversor = new Gson();
        return conversor.toJson(this);
    }

    public static Cliente jsonToObject(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,Cliente.class);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", Cpf='" + cpf + '\'' +
                ", saldo=" + saldo +
                ", carrinho=" + carrinho +
                '}';
    }
}
