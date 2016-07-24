/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.entidade;

/**
 *
 * @author walisson
 */
public class Beneficiario {
    private int id;
    private String nis;
    private String nome;
    private String rua;
    private int numero;
    private String bairro;
    private String zona;
    private String localidade;
    private double rendaFamiliar;
    private double rendaPerCapta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public double getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(double rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }

    public double getRendaPerCapta() {
        return rendaPerCapta;
    }

    public void setRendaPerCapta(double rendaPerCapta) {
        this.rendaPerCapta = rendaPerCapta;
    }
    
}
