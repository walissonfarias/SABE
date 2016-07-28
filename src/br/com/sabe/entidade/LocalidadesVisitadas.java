/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.entidade;

import java.util.Date;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author walisson
 */
public class LocalidadesVisitadas {
    private String zona;
    private String localidade;
    private String bairro;
    private Date dataResultado;

    public Date getDataResultado() {
        return dataResultado;
    }

    public void setDataResultado(Date dataResultado) {
        this.dataResultado = dataResultado;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
