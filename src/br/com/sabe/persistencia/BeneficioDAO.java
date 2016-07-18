/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wff
 */
public class BeneficioDAO {
    private static final String SQL_INSERT = "INSERT INTO BENEFICIO (NOME, TIPO, VALOR) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_DADOS = "UPDATE BENEFICIO SET NOME=?, TIPO=?, VALOR=? WHERE ID=?";
    private static final String SQL_BUSCAR_TODOS = "SELECT*FROM BENEFICIO";
    private static final String SQL_EXCLUIR = "DELETE FROM BENEFICIO WHERE NOME= ? ";

    public void inserir(Beneficio beneficio) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, beneficio.getNome());
            comando.setString(2, beneficio.getTipo());
            comando.setDouble(3, beneficio.getValor());
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
    
    public void alterar(Beneficio beneficio) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_DADOS);
            comando.setString(1, beneficio.getNome());
            comando.setString(2, beneficio.getTipo());
            comando.setDouble(3, beneficio.getValor());
            comando.setDouble(4, beneficio.getId());
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    /*public Usuario buscarByNome(String nome) throws SQLException{
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_LOGIN);
            comando.setString(1, nome);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = this.extrairLinhaResultado(resultado);
            }    
        }catch(Exception e){
            if(conexao!= null){
                conexao.rollback();
            }
            throw new RuntimeException();
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return usuario;
    }*/
    public List<Beneficio> buscarTodos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        List<Beneficio> listaBeneficios = new ArrayList();

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
                Beneficio beneficio = this.extrairLinhaResultado(resultado);
                //Adiciona um item à lista que será retornada
                listaBeneficios.add(beneficio);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaBeneficios;
    }    
      
    private Beneficio extrairLinhaResultado(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Beneficio beneficio = new Beneficio();   
        beneficio.setId(resultado.getInt(1));
        beneficio.setNome(resultado.getString(2));
        beneficio.setTipo(resultado.getString(3));
        beneficio.setValor(resultado.getDouble(4));                                      
        return beneficio;
    }
    public void excluirBeneficio(Beneficio beneficio) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_EXCLUIR);
            comando.setString(1, beneficio.getNome());
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

