/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
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
public class BeneficioAndBeneficiarioDAO {
    private static final String SQL_INSERT = "INSERT INTO BENEFICIO_BENEFICIARIO (ID_BENEFICIO, ID_BENEFICIARIO) VALUES (?, ?)";
    private static final String SQL_BUSCAR_BENEFICIO_BENEFICIARIO = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME, BRIO.RUA, BRIO.NUMERO, BRIO.BAIRRO, BRIO.ZONA,\n" 
        +"BRIO.LOCALIDADE, BRIO.RENDA_FAMILIAR, BRIO.RENDA_PER_CAPTA, B.ID , B.NOME, B.TIPO, B.VALOR FROM BENEFICIARIO BRIO \n" 
        +"JOIN BENEFICIO_BENEFICIARIO BB ON BRIO.ID = BB.ID_BENEFICIARIO JOIN BENEFICIO B ON BB.ID_BENEFICIO=B.ID";
    private static final String SQL_BUSCAR_BY_BENEFICIO = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME, BRIO.RUA, BRIO.NUMERO, BRIO.BAIRRO, BRIO.ZONA,\n" 
        +"BRIO.LOCALIDADE, BRIO.RENDA_FAMILIAR, BRIO.RENDA_PER_CAPTA, B.ID , B.NOME, B.TIPO, B.VALOR FROM BENEFICIARIO BRIO \n" 
        +"JOIN BENEFICIO_BENEFICIARIO BB ON BRIO.ID = BB.ID_BENEFICIARIO JOIN BENEFICIO B ON BB.ID_BENEFICIO=B.ID WHERE B.ID=?";
    private static final String SQL_BUSCAR_BY_NIS = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME, BRIO.RUA, BRIO.NUMERO, BRIO.BAIRRO, BRIO.ZONA,\n" 
        +"BRIO.LOCALIDADE, BRIO.RENDA_FAMILIAR, BRIO.RENDA_PER_CAPTA, B.ID , B.NOME, B.TIPO, B.VALOR FROM BENEFICIARIO BRIO \n" 
        +"JOIN BENEFICIO_BENEFICIARIO BB ON BRIO.ID = BB.ID_BENEFICIARIO JOIN BENEFICIO B ON BB.ID_BENEFICIO=B.ID WHERE BRIO.NIS=?";
    private static final String SQL_BUSCAR_BY_NOME = "SELECT BRIO.ID, BRIO.NIS, BRIO.NOME, BRIO.RUA, BRIO.NUMERO, BRIO.BAIRRO, BRIO.ZONA,\n" 
        +"BRIO.LOCALIDADE, BRIO.RENDA_FAMILIAR, BRIO.RENDA_PER_CAPTA, B.ID , B.NOME, B.TIPO, B.VALOR FROM BENEFICIARIO BRIO \n" 
        +"JOIN BENEFICIO_BENEFICIARIO BB ON BRIO.ID = BB.ID_BENEFICIARIO JOIN BENEFICIO B ON BB.ID_BENEFICIO=B.ID WHERE BRIO.NOME=?";
    //public static final String SQL_DELETE_BENEFICIARIO = "DELETE * FROM BENEFICIO_BENEFICIARIO WHERE ID_BENEFICIARIO = ? "; 
    public static final String SQL_DELETE_BENEFICIO = "DELETE FROM BENEFICIO_BENEFICIARIO WHERE ID_BENEFICIARIO = ? ";
    //public static final String SQL_UPDATE_BENEFICIO = "UPDATE BENEFICIO_BENEFICIARIO SET ID_BENEFICIO=? WHERE ID=?";
    
    public void inserir(BeneficioAndBeneficiario beneficioAndBeneficiario) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setInt(1, beneficioAndBeneficiario.getBeneficio().getId());
            comando.setInt(2, beneficioAndBeneficiario.getBeneficiario().getId());
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
    public List<BeneficioAndBeneficiario> buscarBeneficiarioAndBeneficio() throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        List<BeneficioAndBeneficiario> listaBeneficioAndBeneficiario = new ArrayList();

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_BENEFICIO_BENEFICIARIO);
            //Executa o comando e obtém o resultado da consulta
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                BeneficioAndBeneficiario beneficioAndBeneficiario= 
                        this.extrairLinhaResultadoBuscarTodos(resultado);
                //Adiciona um item à lista que será retornada
                listaBeneficioAndBeneficiario.add(beneficioAndBeneficiario);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaBeneficioAndBeneficiario;
    }
    public List<BeneficioAndBeneficiario> buscarByBeneficio(Beneficio beneficio) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        List<BeneficioAndBeneficiario> listaBeneficioAndBeneficiario = new ArrayList();

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_BENEFICIO);
            //Executa o comando e obtém o resultado da consulta
            comando.setInt(1, beneficio.getId());
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                BeneficioAndBeneficiario beneficioAndBeneficiario= 
                        this.extrairLinhaResultadoBuscarTodos(resultado);
                //Adiciona um item à lista que será retornada
                listaBeneficioAndBeneficiario.add(beneficioAndBeneficiario);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaBeneficioAndBeneficiario;
    }
    public List<BeneficioAndBeneficiario> buscarByNis(String nis) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        List<BeneficioAndBeneficiario> listaBeneficioAndBeneficiario = new ArrayList();

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_NIS);
            //Executa o comando e obtém o resultado da consulta
            comando.setString(1, nis);
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                BeneficioAndBeneficiario beneficioAndBeneficiario= 
                        this.extrairLinhaResultadoBuscarTodos(resultado);
                //Adiciona um item à lista que será retornada
                listaBeneficioAndBeneficiario.add(beneficioAndBeneficiario);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaBeneficioAndBeneficiario;
    }
    public List<BeneficioAndBeneficiario> buscarByNome(String nome) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        List<BeneficioAndBeneficiario> listaBeneficioAndBeneficiario = new ArrayList();

        try {
            //Recupera a conexão
            conexao = BancoDadosUtil.getConnection();
            //Cria o comando de consulta dos dados
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_NOME);
            //Executa o comando e obtém o resultado da consulta
            comando.setString(1, nome);
            resultado = comando.executeQuery();
            //O método next retornar boolean informando se existe um próximo
            //elemento para iterar
            while (resultado.next()) {
                BeneficioAndBeneficiario beneficioAndBeneficiario= 
                        this.extrairLinhaResultadoBuscarTodos(resultado);
                //Adiciona um item à lista que será retornada
                listaBeneficioAndBeneficiario.add(beneficioAndBeneficiario);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaBeneficioAndBeneficiario;
    }
    private Beneficiario extrairLinhaResultadoBeneficiario(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
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
        return beneficiario;
    }
    private Beneficio extrairLinhaResultadoBeneficio(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Beneficio beneficio = new Beneficio();
        beneficio.setId(resultado.getInt(11));
        beneficio.setNome(resultado.getString(12));
        beneficio.setTipo(resultado.getString(13));
        beneficio.setValor(resultado.getDouble(14));
        return beneficio;
    } 
    
    private BeneficioAndBeneficiario extrairLinhaResultadoBuscarTodos(ResultSet resultado) throws SQLException {
        BeneficioAndBeneficiario beneficioAndBeneficiario = new BeneficioAndBeneficiario();
        beneficioAndBeneficiario.setBeneficio(extrairLinhaResultadoBeneficio(resultado));
        beneficioAndBeneficiario.setBeneficiario(extrairLinhaResultadoBeneficiario(resultado));
        return beneficioAndBeneficiario;
    }

    /*public void excluirBeneficiarioByBeneficio(BeneficioAndBeneficiario beneficioAndBeneficiario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;       
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE_BENEFICIARIO);
            comando.setInt(1, beneficioAndBeneficiario.getBeneficio().getId());        
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
    }*/
    public void excluirBeneficioByBeneficiario(BeneficioAndBeneficiario beneficioAndBeneficiario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;       
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETE_BENEFICIO);
            comando.setInt(1, beneficioAndBeneficiario.getBeneficiario().getId());           
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
        
    /*public void atualizar(BeneficioAndBeneficiario beneficioAndBeneficiario) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_UPDATE_BENEFICIO);
            comando.setInt(1, beneficioAndBeneficiario.getBeneficio().getId());
            comando.setInt(2, beneficioAndBeneficiario.getBeneficiario().getId());
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
    }*/
}
    
