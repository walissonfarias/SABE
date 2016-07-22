/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.entidade.ResultadoAveriguacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.objects.Global.setDate;

/**
 *
 * @author walisson
 */
public class ResultadoAveriguacaoDAO {
    private static final String SQL_INSERT = "INSERT INTO RESULTADO(DATA_RESULTADO, RESULTADO, DECISAO, ID_PEDIDO)VALUES(?, ?,?,?)";
    private static final String SQL_BUSCAR_TODOS = "SELECT*FROM PEDIDO P JOIN RESULTADO R ON P.ID=R.ID_PEDIDO"; 
    
    public void inserir(ResultadoAveriguacao resultadoAveriguacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            java.sql.Date dataSql = new java.sql.Date(resultadoAveriguacao.getDataResultado().getTime());
            comando.setDate(1, dataSql);
            comando.setString(2,resultadoAveriguacao.getResultado()); 
            comando.setString(3,resultadoAveriguacao.getDecisao());
            comando.setInt(4,resultadoAveriguacao.getPedidoAveriguacao().getId());
            comando.execute();
            conexao.commit();
        }catch(Exception e){
            if(conexao!= null){
                conexao.rollback();
            }
            throw e;
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
        
    }
    public void atualizar(ResultadoAveriguacao resultadoAveriguacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            java.sql.Date dataSql = new java.sql.Date(resultadoAveriguacao.getDataResultado().getTime());
            comando.setDate(1, dataSql);
            comando.setString(2,resultadoAveriguacao.getResultado()); 
            comando.setString(3,resultadoAveriguacao.getDecisao());
            comando.setInt(4,resultadoAveriguacao.getPedidoAveriguacao().getId());
            comando.execute();
            conexao.commit();
        }catch(Exception e){
            if(conexao!= null){
                conexao.rollback();
            }
            throw e;
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando);
        }
        
    }
    public List<ResultadoAveriguacao> buscarTodos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        List<ResultadoAveriguacao> listaResultadoAveriguacao = new ArrayList();

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                ResultadoAveriguacao resultadoAveriguacao = this.extrairLinhaResultado(resultado);
                //Adiciona um item à lista que será retornada
                listaResultadoAveriguacao.add(resultadoAveriguacao);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaResultadoAveriguacao;
    }    
    private ResultadoAveriguacao extrairLinhaResultado(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        PedidoAveriguacao pedidoAveriguacao = new PedidoAveriguacao();
        ResultadoAveriguacao resultadoAveriguacao = new ResultadoAveriguacao(); 
        pedidoAveriguacao.setId(resultado.getInt(1));
        pedidoAveriguacao.setDataPedido(resultado.getTimestamp(2));
        pedidoAveriguacao.setDescricao(resultado.getString(3));
        pedidoAveriguacao.beneficiario.setId(resultado.getInt(4));
        resultadoAveriguacao.setId(resultado.getInt(5));
        resultadoAveriguacao.setDataResultado(resultado.getTimestamp(6));
        resultadoAveriguacao.setResultado(resultado.getString(7)); 
        resultadoAveriguacao.setDecisao(resultado.getString(8));  
        resultadoAveriguacao.setPedidoAveriguacao(pedidoAveriguacao);        
        return resultadoAveriguacao;
    }
}
