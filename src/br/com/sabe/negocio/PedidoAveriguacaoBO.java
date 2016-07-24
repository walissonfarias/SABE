/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.entidade.ResultadoAveriguacao;
import br.com.sabe.persistencia.BeneficiarioDAO;
import br.com.sabe.persistencia.PedidoAveriguacaoDAO;
import br.com.sabe.excecao.PedidoAveriguacaoExistenteException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public void excluir(PedidoAveriguacao pedidoAveriguacao) throws SQLException{
        PedidoAveriguacaoDAO PedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        PedidoAveriguacaoDAO.excluir(pedidoAveriguacao);
    }
    public List<PedidoAveriguacao> buscarPedidoByNis(String nis) throws SQLException{
        PedidoAveriguacaoDAO PedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        return PedidoAveriguacaoDAO.buscarPedidoByNis(nis);
    }
    public List<PedidoAveriguacao> buscarTodos() throws SQLException{
        PedidoAveriguacaoDAO pedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        return pedidoAveriguacaoDAO.buscarTodos();  
    }
    public void verificarPedidoAveriguacaoExistente(String nis) throws SQLException{
        PedidoAveriguacaoDAO pedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        List<PedidoAveriguacao> listaPedido = this.buscarPedidoByNis(nis);
        for(PedidoAveriguacao pedidoAveriguacao : listaPedido){           
                verificarResultadoAveriguacaoExistente(pedidoAveriguacao.getId());               
        }
    }
    public void verificarResultadoAveriguacaoExistente(int idPedido) throws SQLException{
        ResultadoAveriguacaoBO resultadoAveriguacaoBO = new ResultadoAveriguacaoBO();        
        if(resultadoAveriguacaoBO.buscarResultadoById(idPedido)== true){
            throw new PedidoAveriguacaoExistenteException("Ja existe um pedido cadastrado \n"
                + "para esse beneficiario aguardando resultado!");
        }
    }
}
