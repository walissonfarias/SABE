/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.persistencia.BeneficioDAO;
import br.com.sabe.excecao.BeneficiarioExistenteException;
import br.com.sabe.persistencia.BeneficiarioDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wff
 */
public class BeneficioBO {
    public void inserir(Beneficio beneficio) throws SQLException{
        BeneficioDAO beneficioDAO = new BeneficioDAO();
        beneficioDAO.inserir(beneficio);
    }
    public void alterar(Beneficio beneficio) throws SQLException {
        BeneficioDAO beneficioDAO = new BeneficioDAO();
        beneficioDAO.alterar(beneficio);
    }
    public List<Beneficio> buscarTodos() throws SQLException{
        BeneficioDAO beneficioDAO = new BeneficioDAO();      
        return beneficioDAO.buscarTodos();
    }

    public void excluirBeneficio(Beneficio beneficio) throws SQLException {
        BeneficioDAO beneficioDAO = new BeneficioDAO();
        beneficioDAO.excluirBeneficio(beneficio);
    }
    /*public void verificarBeneficiariosExistentes(Beneficio beneficio){
        BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
        Beneficiario beneficiarioExiste = beneficiarioDAO.buscarBeneficiarioByBeneficio(beneficio);
        if(beneficiarioExiste != null){
            throw new BeneficiarioExistenteException("impossivel excluir beneficio, "
                    + "exclua os benefiarios antes de realizar essa operação");
        }
    }*/
}
