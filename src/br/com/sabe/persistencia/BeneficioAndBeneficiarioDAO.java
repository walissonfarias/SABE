/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.persistencia;

import br.com.sabe.entidade.BeneficioAndBeneficiario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author walisson
 */
public class BeneficioAndBeneficiarioDAO {
    private static final String SQL_INSERT = "INSERT INTO BENEFICIO_BENEFICIARIO (ID_BENEFICIO, ID_BENEFICIARIO) VALUES (?, ?)";
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
}
