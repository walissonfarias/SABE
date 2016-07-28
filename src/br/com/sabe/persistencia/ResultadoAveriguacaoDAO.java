/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.LocalidadesVisitadas;
import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.entidade.ResultadoAveriguacao;
import br.com.sabe.entidade.SituacaoBeneficiarios;
import br.com.sabe.excecao.ConsultaSemResultadoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author walisson
 */
public class ResultadoAveriguacaoDAO {
    private static final String SQL_INSERT = "INSERT INTO RESULTADO(DATA_RESULTADO, RESULTADO, DECISAO, ID_PEDIDO)VALUES(?, ?,?,?)";
    private static final String SQL_BUSCAR_BY_NIS = "SELECT R.ID, R.DATA_RESULTADO, R.RESULTADO, R.DECISAO, BRIO.ID, BRIO.NIS, BRIO.NOME,BRIO.ZONA, BRIO.LOCALIDADE,"
            + "BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, BRIO.RENDA_FAMILIAR,BRIO.RENDA_PER_CAPTA,P.ID, P.DATA_PEDIDO, P.SITUACAO "
            + "FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO "
            + "JOIN RESULTADO R ON P.ID=R.ID_PEDIDO WHERE BRIO.NIS=?";
    private static final String SQL_BUSCAR_TODOS = "SELECT R.ID, R.DATA_RESULTADO, R.RESULTADO, R.DECISAO,BRIO.ID, BRIO.NIS, BRIO.NOME,BRIO.ZONA, BRIO.LOCALIDADE,"
            + "BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, BRIO.RENDA_FAMILIAR,BRIO.RENDA_PER_CAPTA,P.ID, P.DATA_PEDIDO, P.SITUACAO "
            + " FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO "
            + "JOIN RESULTADO R ON P.ID=R.ID_PEDIDO"; 
    private static final String SQL_BUSCAR_AVERIGUACOES = "SELECT R.ID, R.DATA_RESULTADO, R.RESULTADO, R.DECISAO,BRIO.ID, BRIO.NIS, BRIO.NOME,BRIO.ZONA, BRIO.LOCALIDADE,"
            + "BRIO.BAIRRO,BRIO.RUA, BRIO.NUMERO, BRIO.RENDA_FAMILIAR,BRIO.RENDA_PER_CAPTA,P.ID, P.DATA_PEDIDO, P.SITUACAO "
            + " FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO "
            + "LEFT JOIN RESULTADO R ON P.ID=R.ID_PEDIDO";
    private static final String SQL_BUSCAR_LOCALIDADES_VISITADAS = "SELECT BRIO.ZONA, BRIO.LOCALIDADE,BRIO.BAIRRO, R.DATA_RESULTADO"
            + " FROM BENEFICIARIO BRIO JOIN PEDIDO P ON BRIO.ID=P.ID_BENEFICIARIO "
            + "JOIN RESULTADO R ON P.ID=R.ID_PEDIDO WHERE R.DATA_RESULTADO >=? AND R.DATA_RESULTADO<=? ORDER BY BRIO.ZONA, BRIO.LOCALIDADE";   
    private static final String SQL_BUSCAR_BY_ID_PEDIDO = "SELECT*FROM RESULTADO WHERE ID_PEDIDO=?";
    private static final String SQL_EXCLUIR = "DELETE FROM RESULTADO WHERE ID=?";
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
    public void excluir(ResultadoAveriguacao resultadoAveriguacao) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_EXCLUIR);
            comando.setInt(1, resultadoAveriguacao.getId());
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
    
    public List<LocalidadesVisitadas> buscarLocalidadesVisitadas(Date dataInicio, Date dataTermino) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<LocalidadesVisitadas> listaLocalidadesMaisVisitadas = new ArrayList();
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_LOCALIDADES_VISITADAS);
            java.sql.Date dataInicioSql = new java.sql.Date(dataInicio.getTime());
            comando.setDate(1, dataInicioSql);
            java.sql.Date dataTerminoSql = new java.sql.Date(dataTermino.getTime());
            comando.setDate(2, dataTerminoSql);
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                LocalidadesVisitadas localidadesMaisVisitadas = new LocalidadesVisitadas();
                
                //localidadesMaisVisitadas.setDecisao(resultado.getString(1));        
                //localidadesMaisVisitadas.setNis(resultado.getString(2));
                //localidadesMaisVisitadas.setNome(resultado.getString(3));
                localidadesMaisVisitadas.setZona(resultado.getString(1));
                localidadesMaisVisitadas.setLocalidade(resultado.getString(2));
                localidadesMaisVisitadas.setBairro(resultado.getString(3));
                localidadesMaisVisitadas.setDataResultado(resultado.getDate(4));
                //Adiciona um item à lista que será retornada
                listaLocalidadesMaisVisitadas.add(localidadesMaisVisitadas);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        if (listaLocalidadesMaisVisitadas == null) {
            throw new ConsultaSemResultadoException();
        }
        return listaLocalidadesMaisVisitadas;
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
        beneficiario.setId(resultado.getInt(5));
        beneficiario.setNis(resultado.getString(6));
        beneficiario.setNome(resultado.getString(7));
        beneficiario.setZona(resultado.getString(8));
        beneficiario.setLocalidade(resultado.getString(9));
        beneficiario.setBairro(resultado.getString(10));
        beneficiario.setRua(resultado.getString(11));
        beneficiario.setNumero(resultado.getInt(12));  
        beneficiario.setRendaFamiliar(resultado.getDouble(13));
        beneficiario.setRendaPerCapta(resultado.getDouble(14));
        pedidoAveriguacao.setId(resultado.getInt(15));
        pedidoAveriguacao.setDataPedido(resultado.getTimestamp(16));
        pedidoAveriguacao.setSituacao(resultado.getString(17));
        pedidoAveriguacao.setBeneficiario(beneficiario);
        return pedidoAveriguacao;
    }
    private ResultadoAveriguacao extrairLinhaResultado(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)      
        ResultadoAveriguacao resultadoAveriguacao = new ResultadoAveriguacao();
        resultadoAveriguacao.setId(resultado.getInt(1));
        resultadoAveriguacao.setDataResultado(resultado.getTimestamp(2));
        resultadoAveriguacao.setResultado(resultado.getString(3));
        resultadoAveriguacao.setDecisao(resultado.getString(4));        
        resultadoAveriguacao.setPedidoAveriguacao(extrairLinhaResultadoPedido(resultado));
        return resultadoAveriguacao;
    }
    public List<ResultadoAveriguacao> buscarAveriguacoes() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        List<ResultadoAveriguacao> listaAveriguacao = new ArrayList();

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_AVERIGUACOES);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                ResultadoAveriguacao resultadoAveriguacao = this.extrairLinhaResultado(resultado);
                //Adiciona um item à lista que será retornada
                listaAveriguacao.add(resultadoAveriguacao);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaAveriguacao;
    }
}
