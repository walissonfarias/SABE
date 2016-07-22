/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.negocio;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.entidade.Usuario;
import br.com.sabe.excecao.ArgumentoInvalidoException;
import br.com.sabe.excecao.UsuarioLoginExistenteException;
import br.com.sabe.persistencia.BeneficiarioDAO;
import br.com.sabe.persistencia.BeneficioAndBeneficiarioDAO;
import br.com.sabe.persistencia.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wff
 */
public class BeneficiarioBO {
    public void inserir(Beneficiario beneficiario,BeneficioAndBeneficiario BeneficioAndBeneficiario) throws SQLException{
        BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
        beneficiarioDAO.inserir(beneficiario);
        BeneficioAndBeneficiario.setBeneficiario(buscarBeneficiario(beneficiario));
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        beneficioAndBeneficiarioDAO.inserir(BeneficioAndBeneficiario);
    }
    public void verificarUsuarioLogin(String login) throws SQLException{
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.buscarByLogin(login);
        if(usuarioExistente != null){
            throw new UsuarioLoginExistenteException("Login ja existe!\n escolha outro login!");
        }
    }
    public Beneficiario buscarByNis(String nis)throws SQLException{
        BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
        Beneficiario beneficiarioExistente = null;
        beneficiarioExistente = beneficiarioDAO.buscarByNis(nis);
        if(beneficiarioExistente != null){
            return beneficiarioExistente;
        }else{
            throw new ArgumentoInvalidoException("NIS inexistente \n Verifique se o NIS está correto");
        }
    }    
    public Beneficiario buscarBeneficiario(Beneficiario beneficiario) throws SQLException{
        Beneficiario beneficiarioExistente = new Beneficiario();
        BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
        beneficiarioExistente = beneficiarioDAO.buscarByNis(beneficiario.getNis());
        if(beneficiarioExistente != null){
            return beneficiarioExistente;
        }else{
            throw new ArgumentoInvalidoException("NIS inexistente \n Verifique se o NIS está correto");
        }
    }
    /*public List<Beneficiario> buscarTodos() throws SQLException{
        BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
        return beneficiarioDAO.buscarTodos();
        BeneficioAndBeneficiarioDAO beneficioAndBeneficiarioDAO = new BeneficioAndBeneficiarioDAO();
        beneficioAndBeneficiarioDAO.buscarByBeneficiario();
    }*/
    public void atualizarDados(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizarDados(usuario);
    }

    public void excluirUsuario(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.excluirUsuario(usuario);
    }
}
