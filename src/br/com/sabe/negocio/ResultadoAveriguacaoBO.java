/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.entidade.ResultadoAveriguacao;
import br.com.sabe.excecao.ResultadoAveriguacaoExistenteException;
import br.com.sabe.persistencia.PedidoAveriguacaoDAO;
import br.com.sabe.persistencia.ResultadoAveriguacaoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author walisson
 */
public class ResultadoAveriguacaoBO {
    public void inserir(ResultadoAveriguacao resultadoAveriguacao) throws SQLException{
        ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();
        resultadoAveriguacaoDAO.inserir(resultadoAveriguacao);
    }
    public void atualizar(ResultadoAveriguacao resultadoAveriguacao) throws SQLException{
        ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();
        resultadoAveriguacaoDAO.atualizar(resultadoAveriguacao);
    }
    public void excluir(ResultadoAveriguacao resultadoAveriguacao) throws SQLException{
        ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();
        resultadoAveriguacaoDAO.excluir(resultadoAveriguacao);
    }
    public List<ResultadoAveriguacao> buscarResultadoByNis(String nis) throws SQLException{
         ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();
        return resultadoAveriguacaoDAO.buscarResultadoByNis(nis);  
    }
    public PedidoAveriguacao verificarPedidoAveriguacaoExistente(String nis) throws SQLException{
        PedidoAveriguacaoDAO pedidoAveriguacaoDAO = new PedidoAveriguacaoDAO();
        List<PedidoAveriguacao> listaPedido = pedidoAveriguacaoDAO.buscarPedidoByNis(nis);
        int cont = 0;
        for(PedidoAveriguacao pedidoAveriguacao : listaPedido){           
            verificarResultadoAveriguacaoExistente(pedidoAveriguacao.getId());               
            cont++;
        }
        return listaPedido.get(cont-1);
    }
    public void verificarResultadoAveriguacaoExistente(int idPedido) throws SQLException{
        ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();        
        if(resultadoAveriguacaoDAO.buscarResultadoById(idPedido)!= true){
            throw new ResultadoAveriguacaoExistenteException("Ja existe um resultado cadastrado \n"
                + "para esse pedido de Averiguacao, cadastre um novo pedido!");
        }
    }
    public boolean buscarResultadoById(int idPedido) throws SQLException{
        ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();
        return resultadoAveriguacaoDAO.buscarResultadoById(idPedido);  
    }
    public List<ResultadoAveriguacao> buscarTodos() throws SQLException{
        ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();
        return resultadoAveriguacaoDAO.buscarTodos();  
    }
}
