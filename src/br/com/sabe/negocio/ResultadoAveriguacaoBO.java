/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.entidade.ResultadoAveriguacao;
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
    public List<ResultadoAveriguacao> buscarTodos() throws SQLException{
        ResultadoAveriguacaoDAO resultadoAveriguacaoDAO = new ResultadoAveriguacaoDAO();
        return resultadoAveriguacaoDAO.buscarTodos();  
    }
}
