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
import br.com.sabe.excecao.BeneficioExistenteException;
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
        this.verificarBeneficioExistente(beneficio);
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
    public void verificarBeneficioExistente(Beneficio beneficio) throws SQLException{
        BeneficioDAO beneficioDAO = new BeneficioDAO();
        boolean beneficioExiste = beneficioDAO.buscarByNome(beneficio.getNome()); 
        if( beneficioExiste == true){
            throw new BeneficioExistenteException("Ja existe um beneficio cadastrado com esse nome\n");
        }
    }
}
