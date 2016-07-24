/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.entidade.ResultadoAveriguacao;
import br.com.sabe.excecao.ConsultaSemResultadoException;
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
    private static final String SQL_BUSCAR_BY_NIS = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME,BRIO.ZONA, BRIO.LOCALIDADE, "
            + "BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, BRIO.RENDA_FAMILIAR,BRIO.RENDA_PER_CAPTA,P.ID, P.DATA_PEDIDO, P.SITUACAO "
            + "R.DATA_RESULTADO, R.RESULTADO, R.DECISAO FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO "
            + "JOIN RESULTADO R ON P.ID=R.ID_PEDIDO WHERE BRIO.NIS=?";
    private static final String SQL_BUSCAR_TODOS = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME,BRIO.ZONA, BRIO.LOCALIDADE, "
            + "BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, BRIO.RENDA_FAMILIAR,BRIO.RENDA_PER_CAPTA,P.ID, P.DATA_PEDIDO, P.SITUACAO "
            + "R.DATA_RESULTADO, R.RESULTADO, R.DECISAO FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO "
            + "JOIN RESULTADO R ON P.ID=R.ID_PEDIDO"; 
    private static final String SQL_BUSCAR_BY_ID_PEDIDO = "SELECT*FROM RESULTADO WHERE ID_PEDIDO=?";
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
    public List<ResultadoAveriguacao> buscarResultadoByNis(String nis) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<ResultadoAveriguacao> listaResultadoAveriguacao = new ArrayList();
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_NIS);
            comando.setString(1, nis);
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
        if (listaResultadoAveriguacao == null) {
            throw new ConsultaSemResultadoException();
        }
        return listaResultadoAveriguacao;
    }
    
    public boolean buscarResultadoById(int idPedido) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<ResultadoAveriguacao> listaResultadoAveriguacao = new ArrayList();
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_ID_PEDIDO);
            comando.setInt(1, idPedido);
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
        if (listaResultadoAveriguacao == null) {
            throw new ConsultaSemResultadoException();
        }
        return true;
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
    private PedidoAveriguacao extrairLinhaResultadoPedido(ResultSet resultado) throws SQLException{
        PedidoAveriguacao pedidoAveriguacao = new PedidoAveriguacao();
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(resultado.getInt(1));
        beneficiario.setNis(resultado.getString(2));
        beneficiario.setNome(resultado.getString(3));
        beneficiario.setRua(resultado.getString(4));
        beneficiario.setNumero(resultado.getInt(5));
        beneficiario.setBairro(resultado.getString(6));
        beneficiario.setZona(resultado.getString(7));
        beneficiario.setLocalidade(resultado.getString(8));
        beneficiario.setRendaFamiliar(resultado.getDouble(9));
        beneficiario.setRendaPerCapta(resultado.getDouble(10));
        pedidoAveriguacao.setId(resultado.getInt(11));
        pedidoAveriguacao.setDataPedido(resultado.getTimestamp(12));
        pedidoAveriguacao.setSituacao(resultado.getString(13));
        pedidoAveriguacao.setBeneficiario(beneficiario);
        return pedidoAveriguacao;
    }
    private ResultadoAveriguacao extrairLinhaResultado(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)      
        ResultadoAveriguacao resultadoAveriguacao = new ResultadoAveriguacao();        
        resultadoAveriguacao.setPedidoAveriguacao(extrairLinhaResultadoPedido(resultado));
        resultadoAveriguacao.setDataResultado(resultado.getTimestamp(15));
        resultadoAveriguacao.setResultado(resultado.getString(16));
        resultadoAveriguacao.setDecisao(resultado.getString(17));       
        return resultadoAveriguacao;
    }
}
