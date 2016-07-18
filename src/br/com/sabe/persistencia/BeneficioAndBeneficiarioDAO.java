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
        +"BRIO.LOCALIDADE, BRIO.QTDE_MEMBROS, BRIO.RENDA_FAMILIAR, BRIO.RENDA_PER_CAPTA, B.ID , B.NOME, B.TIPO, B.VALOR FROM BENEFICIARIO BRIO \n" 
        +"JOIN BENEFICIO_BENEFICIARIO BB ON BRIO.ID = BB.ID_BENEFICIARIO JOIN BENEFICIO B ON BB.ID_BENEFICIO=B.ID";
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
    public List<BeneficioAndBeneficiario> buscarBeneficiarioByBeneficio() throws SQLException{
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
        beneficiario.setQtdeMembros(resultado.getInt(9));
        beneficiario.setRendaFamiliar(resultado.getDouble(10));
        beneficiario.setRendaPerCapta(resultado.getDouble(11));
        return beneficiario;
    }
    private Beneficio extrairLinhaResultadoBeneficio(ResultSet resultado) throws SQLException {
        //Instancia um novo objeto e atribui os valores vindo do BD
        //(Note que no BD o index inicia por 1)
        Beneficio beneficio = new Beneficio();
        beneficio.setId(resultado.getInt(12));
        beneficio.setNome(resultado.getString(13));
        beneficio.setTipo(resultado.getString(14));
        beneficio.setValor(resultado.getDouble(15));
        return beneficio;
    } 
    
    private BeneficioAndBeneficiario extrairLinhaResultadoBuscarTodos(ResultSet resultado) throws SQLException {
        BeneficioAndBeneficiario beneficioAndBeneficiario = new BeneficioAndBeneficiario();
        beneficioAndBeneficiario.setBeneficio(extrairLinhaResultadoBeneficio(resultado));
        beneficioAndBeneficiario.setBeneficiario(extrairLinhaResultadoBeneficiario(resultado));
        return beneficioAndBeneficiario;
    }
    
}
