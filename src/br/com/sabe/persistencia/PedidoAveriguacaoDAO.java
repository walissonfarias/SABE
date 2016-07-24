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
    private static final String SQL_UPDATE = "UPDATE PEDIDO SET DATA_PEDIDO=?, SITUACAO=? WHERE ID= ?";
    private static final String SQL_BUSCAR_BY_NIS = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME,BRIO.ZONA, BRIO.LOCALIDADE, "
            + "BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, BRIO.RENDA_FAMILIAR,BRIO.RENDA_PER_CAPTA,P.ID, P.DATA_PEDIDO, P.SITUACAO "
            + "FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO WHERE BRIO.NIS=?";
    private static final String SQL_BUSCAR_TODOS = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME,BRIO.ZONA, BRIO.LOCALIDADE, "
            + "BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, BRIO.RENDA_FAMILIAR,BRIO.RENDA_PER_CAPTA,P.ID, P.DATA_PEDIDO, P.SITUACAO "
            + "FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO"; 
    public static final String SQL_EXCLUIR = "DELETE FROM PEDIDO WHERE ID=?";
    
    public void inserir(PedidoAveriguacao pedidoAveriguacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            java.sql.Date dataSql = new java.sql.Date(pedidoAveriguacao.getDataPedido().getTime());
            comando.setDate(1, dataSql);
            comando.setString(2, pedidoAveriguacao.getSituacao());
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
            comando = conexao.prepareStatement(SQL_UPDATE);
            java.sql.Date dataSql = new java.sql.Date(pedidoAveriguacao.getDataPedido().getTime());
            comando.setDate(1, dataSql);
            comando.setString(2, pedidoAveriguacao.getSituacao());
            comando.setInt(3, pedidoAveriguacao.getId());
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
    public List<PedidoAveriguacao> buscarPedidoByNis(String nis) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<PedidoAveriguacao> listaPedidoAveriguacao = new ArrayList();
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_NIS);
            comando.setString(1, nis);
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
    public List<PedidoAveriguacao> buscarTodos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        List<PedidoAveriguacao> listaPedidoAveriguacao = new ArrayList<>();

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
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(resultado.getInt(1));
        beneficiario.setNis(resultado.getString(2));
        beneficiario.setNome(resultado.getString(3));
        beneficiario.setZona(resultado.getString(4));
        beneficiario.setLocalidade(resultado.getString(5));
        beneficiario.setBairro(resultado.getString(6));
        beneficiario.setRua(resultado.getString(7));
        beneficiario.setNumero(resultado.getInt(8));
        beneficiario.setRendaFamiliar(resultado.getDouble(9));
        beneficiario.setRendaPerCapta(resultado.getDouble(10));
        pedidoAveriguacao.setId(resultado.getInt(11));
        pedidoAveriguacao.setDataPedido(resultado.getTimestamp(12));
        pedidoAveriguacao.setSituacao(resultado.getString(13));
        pedidoAveriguacao.setBeneficiario(beneficiario);
        return pedidoAveriguacao;
    }
    public void excluir(PedidoAveriguacao pedidoAveriguacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_EXCLUIR);
            comando.setInt(1, pedidoAveriguacao.getId());
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
}
