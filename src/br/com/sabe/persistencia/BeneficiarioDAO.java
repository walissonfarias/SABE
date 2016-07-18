/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.entidade.Beneficio;
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
public class BeneficiarioDAO {
    private static final String SQL_INSERT = "INSERT INTO BENEFICIARIO ( NIS, NOME, RUA, NUMERO, BAIRRO, ZONA, LOCALIDADE, "
            + "QTDE_MEMBROS, RENDA_FAMILIAR, RENDA_PER_CAPTA )VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    private static final String SQL_BUSCAR_BY_NIS = "SELECT*FROM BENEFICIARIO WHERE NIS=?";
    private static final String SQL_BUSCAR_BENEFICIARIO_BY_BENEFICIO = "SELECT BRIO.ID, BRIO.NIS, BRIO.RUA, BRIO.NUMERO, BRIO.BAIRRO, BRIO.ZONA,\n"
            + "BRIO.LOCALIDADE, BRIO.QTDE_MEMBROS, BRIO.RENDA_FAMILIAR, \n"
            + "BRIO.RENDA_PER_CAPTA, B.NOME, B.DESCRISSAO, B.VALOR FROM BENEFICIARIO BRIO \n"
            + "JOIN BENEFICIO_BENEFICIARIO BB ON BRIO.ID = BB.ID_BENEFICIARIO \n"
            + "JOIN BENEFICIO B ON BB.ID_BENEFICIO=B.ID;";
    private static final String SQL_BUSCAR_TODOS = "SELECT*FROM BENEFICIARIO";
    private static final String SQL_EXCLUIR = "DELETE FROM BENEFICIO WHERE NOME= ? "; 
    
    public void inserir(Beneficiario beneficiario) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, beneficiario.getNis());
            comando.setString(2, beneficiario.getNome());
            comando.setString(3, beneficiario.getRua());
            comando.setInt(4, beneficiario.getNumero());
            comando.setString(5, beneficiario.getBairro());
            comando.setString(6, beneficiario.getZona());
            comando.setString(7, beneficiario.getLocalidade());
            comando.setInt(8, beneficiario.getQtdeMembros());
            comando.setDouble(9, beneficiario.getRendaFamiliar());
            comando.setDouble(10, beneficiario.getRendaPerCapta());
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
    public Beneficiario buscarByNis(String nis) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Beneficiario beneficiario = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_BY_NIS);
            comando.setString(1, nis);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                beneficiario = this.extrairLinhaResultado(resultado);
            }    
        }catch(Exception e){
            if(conexao!= null){
                conexao.rollback();
            }
            throw new RuntimeException();
        }finally{
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return beneficiario;
    }
    public List<Beneficiario> buscarTodos() throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;

        List<Beneficiario> listaBeneficiario = new ArrayList();

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
                Beneficiario beneficiario = this.extrairLinhaResultado(resultado);
                //Adiciona um item à lista que será retornada
                listaBeneficiario.add(beneficiario);
            }
        } finally {
            //Todo objeto que referencie o banco de dados deve ser fechado
            BancoDadosUtil.fecharChamadasBancoDados(conexao, comando, resultado);
        }
        return listaBeneficiario;
    }    
    private Beneficiario extrairLinhaResultado(ResultSet resultado) throws SQLException {
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
        beneficiario.setQtdeMembros(resultado.getInt(9));
        beneficiario.setRendaFamiliar(resultado.getDouble(10));
        beneficiario.setRendaPerCapta(resultado.getDouble(11));
        return beneficiario;
    }

}
