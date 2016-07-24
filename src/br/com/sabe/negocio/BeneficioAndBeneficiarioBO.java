/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.persistencia.BeneficioAndBeneficiarioDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author walisson
 */
public class BeneficioAndBeneficiarioBO  {   
    public void inserir(BeneficioAndBeneficiario beneficioAndBeneficiario) throws SQLException{
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        beneficioAndBeneficiarioDAO.inserir(beneficioAndBeneficiario);
    }
    public List<BeneficioAndBeneficiario> buscarTodosBeneficioAndBeneficiario() throws SQLException{
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        return beneficioAndBeneficiarioDAO.buscarBeneficiarioAndBeneficio();       
    }
    public List<BeneficioAndBeneficiario> buscarByBeneficio(Beneficio beneficio) throws SQLException{
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        return beneficioAndBeneficiarioDAO.buscarByBeneficio(beneficio);       
    }
    public List<BeneficioAndBeneficiario> buscarByNis(String nis) throws SQLException{
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        return beneficioAndBeneficiarioDAO.buscarByNis(nis);       
    }
    public List<BeneficioAndBeneficiario> buscarByNome(String nome) throws SQLException{
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        return beneficioAndBeneficiarioDAO.buscarByNome(nome);       
    }
    public void atualizar(BeneficioAndBeneficiario beneficioAndBeneficiario) throws SQLException{
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        beneficioAndBeneficiarioDAO.excluirBeneficioByBeneficiario(beneficioAndBeneficiario);
        beneficioAndBeneficiarioDAO.inserir(beneficioAndBeneficiario);
    }
}
