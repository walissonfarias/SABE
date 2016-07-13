/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.excecao;

/**
 *
 * @author walisson
 */
public class BeneficiarioExistenteException extends SistemaAveriguacaoException {
    public BeneficiarioExistenteException(String message) {
        super(message);
    }
}
