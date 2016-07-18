/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.persistencia.BeneficioAndBeneficiarioDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author walisson
 */
public class BeneficioAndBeneficiarioBO {   
    public List<BeneficioAndBeneficiario> buscarTodosBeneficioAndBeneficiario() throws SQLException{
        Beneficio beneficioExistente = null;
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        return beneficioAndBeneficiarioDAO.buscarBeneficiarioByBeneficio();       
    }
    public List<BeneficioAndBeneficiario> buscarByNis() throws SQLException{
        Beneficio beneficioExistente = null;
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        return beneficioAndBeneficiarioDAO.buscarBeneficiarioByBeneficio();       
    }
}
