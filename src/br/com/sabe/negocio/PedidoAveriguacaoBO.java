/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.persistencia.BeneficiarioDAO;
import br.com.sabe.persistencia.PedidoAveriguacaoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author walisson
 */
public class PedidoAveriguacaoBO {
    public void inserir(PedidoAveriguacao pedidoAveriguacao) throws SQLException{
        PedidoAveriguacaoDAO PedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        PedidoAveriguacaoDAO.inserir(pedidoAveriguacao);
    }  
    public void atualizar(PedidoAveriguacao pedidoAveriguacao) throws SQLException{
        PedidoAveriguacaoDAO PedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        PedidoAveriguacaoDAO.atualizar(pedidoAveriguacao);
    } 
    public List<PedidoAveriguacao> buscarTodos() throws SQLException{
        PedidoAveriguacaoDAO pedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        return pedidoAveriguacaoDAO.buscarTodos();  
    }
}
