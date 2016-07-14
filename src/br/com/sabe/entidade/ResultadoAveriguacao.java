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
public class ResultadoAveriguacao {
    private int id;
    private String resultado;
    private String decisao;
    private PedidoAveriguacao pedidoAveriguacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public PedidoAveriguacao getPedidoAveriguacao() {
        return pedidoAveriguacao;
    }

    public void setPedidoAveriguacao(PedidoAveriguacao pedidoAveriguacao) {
        this.pedidoAveriguacao = pedidoAveriguacao;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDecisao() {
        return decisao;
    }

    public void setDecisao(String decisao) {
        this.decisao = decisao;
    }
    
}
