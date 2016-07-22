/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.PedidoAveriguacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author walisson
 */
public class PedidoAveriguacaoDAO {
    private static final String SQL_INSERT = "INSERT INTO PEDIDO(DATA_PEDIDO, SITUACAO, ID_BENEFICIARIO)VALUES(?, ?,?)";
    private static final String SQL_BUSCAR_TODOS = "SELECT*FROM BENEFICIARIO B JOIN PEDIDO P ON B.ID=P.ID_BENEFICIARIO"; 
    public void inserir(PedidoAveriguacao pedidoAveriguacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            java.sql.Date dataSql = new java.sql.Date(pedidoAveriguacao.getDataPedido().getTime());
            comando.setDate(1, dataSql);
            comando.setString(2, pedidoAveriguacao.getDescricao());
            comando.setInt(3, pedidoAveriguacao.beneficiario.getId());
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
    public void atualizar(PedidoAveriguacao pedidoAveriguacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            java.sql.Date dataSql = new java.sql.Date(pedidoAveriguacao.getDataPedido().getTime());
            comando.setDate(1, dataSql);
            comando.setString(2, pedidoAveriguacao.getDescricao());
            comando.setInt(3, pedidoAveriguacao.beneficiario.getId());
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
    public List<PedidoAveriguacao> buscarTodos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        List<PedidoAveriguacao> listaPedidoAveriguacao = new ArrayList();

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
                PedidoAveriguacao pedidoAveriguacao = this.extrairLinhaResultado(resultado);
                //Adiciona um item à lista que será retornada
                listaPedidoAveriguacao.add(pedidoAveriguacao);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaPedidoAveriguacao;
    }    
    private PedidoAveriguacao extrairLinhaResultado(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        PedidoAveriguacao pedidoAveriguacao = new PedidoAveriguacao(); 
        pedidoAveriguacao.setId(resultado.getInt(1));
        pedidoAveriguacao.setDescricao(resultado.getString(2));
        pedidoAveriguacao.beneficiario.setId(resultado.getInt(3));
        pedidoAveriguacao.beneficiario.setNis(resultado.getString(4));
        pedidoAveriguacao.beneficiario.setNome(resultado.getString(5));
        pedidoAveriguacao.beneficiario.setRua(resultado.getString(6));
        pedidoAveriguacao.beneficiario.setNumero(resultado.getInt(7));
        pedidoAveriguacao.beneficiario.setBairro(resultado.getString(8));
        pedidoAveriguacao.beneficiario.setZona(resultado.getString(9));
        pedidoAveriguacao.beneficiario.setLocalidade(resultado.getString(10));
        pedidoAveriguacao.beneficiario.setQtdeMembros(resultado.getInt(11));
        pedidoAveriguacao.beneficiario.setRendaFamiliar(resultado.getDouble(12));
        pedidoAveriguacao.beneficiario.setRendaPerCapta(resultado.getDouble(13));
        return pedidoAveriguacao;
    }
}
