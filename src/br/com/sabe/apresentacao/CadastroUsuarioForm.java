/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.apresentacao;

import br.com.sabe.apresentacao.classesUteis.DocumentoLimitado;
import br.com.sabe.apresentacao.classesUteis.Criptografia;
import br.com.sabe.entidade.Usuario;
import br.com.sabe.excecao.ArgumentoInvalidoException;
import br.com.sabe.excecao.CampoObrigatorioException;
import br.com.sabe.excecao.SenhaInvalidaException;
import br.com.sabe.excecao.SistemaAveriguacaoException;
import br.com.sabe.negocio.UsuarioBO;
import java.awt.event.FocusEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

/**
 *
 * @author wff
 */
public class CadastroUsuarioForm extends javax.swing.JFrame {
    Usuario usuario;
    Usuario usuarioEmEdicao;
    Usuario usuarioPesquisado;
    ConsultarUsuarioForm telaListarUsuario;
    int acaoTela;
    /**
     * Creates new form TelaLogin
     */
    public CadastroUsuarioForm() {
        prepararTela();
        usuario = new Usuario();
        acaoTela = 0;
    }
    public CadastroUsuarioForm(Usuario usuarioConsultado){
        this.prepararTela();
        this.usuario = usuarioConsultado;
        this.inicializarCamposTela();
        this.habilitarBotoesTela();
        this.acaoTela = 1;
        this.txtLogin.setEnabled(false);
    }

    private void prepararTela() {
        try {
            this.initComponents();
            this.desabilitarBotoesTela();
            this.limitarCamposTela();
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de aluno", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }

    }
    public void limitarCamposTela(){
        this.txtLogin.setDocument(new DocumentoLimitado(10));
        this.txtNome.setDocument(new DocumentoLimitado(60));
        this.txtSenha.setDocument(new DocumentoLimitado(10));
        this.txtRepitaSenha.setDocument(new DocumentoLimitado(10));
    }
    private void desabilitarBotoesTela() throws SQLException{
        UsuarioBO usuarioBO = new UsuarioBO();
        boolean gestorExistente = usuarioBO.verificarGestorExistente();
        if(gestorExistente == true){
            chbGestor.setEnabled(false);
        }
        if(!txtNome.getText().isEmpty() && !txtLogin.getText().isEmpty() && 
            !txtSenha.getText().isEmpty()){
            btnNovo.setEnabled(false);
            btnSalvar.setEnabled(false);
        }
    }
    private void habilitarBotoesTela(){
        if(!txtNome.getText().isEmpty() && !txtLogin.getText().isEmpty() && 
            !txtSenha.getText().isEmpty()){
            btnSalvar.setEnabled(true);
            btnNovo.setEnabled(true);
        }else{
            btnSalvar.setEnabled(false);
            btnNovo.setEnabled(false);
        }
    }
    
    private void validarCamposObrigatorios() {
        if (txtNome.getText().trim().isEmpty()
                || txtLogin.getText().trim().isEmpty()
                || txtSenha.getText().trim().isEmpty()){        
            throw new CampoObrigatorioException();
        }
    }
    private void limparCamposTela(){
        txtNome.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        txtRepitaSenha.setText("");
        btnGrupoUsuarios.clearSelection();
    }
    private void recuperarCamposTela() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        usuario.setNome(txtNome.getText());
        String login = txtLogin.getText();
        String senha = txtSenha.getText();
        String repitaSenha = txtRepitaSenha.getText();
        if(senha.equals(repitaSenha)){
                this.usuario.setLogin(login);
                this.usuario.setSenha(Criptografia.exemploMD5(senha));
        }else{
               throw new SenhaInvalidaException("As senhas são diferentes. \n Elas devem ser iguais");
        }
        
        if (chbGestor.isSelected() == true){
            usuario.setCargo("G");
        }else if(chbAssistenteSocial.isSelected() == true) {
            usuario.setCargo("AS");
        }else if (chbOrientadorSocial.isSelected() == true) {
            usuario.setCargo("OS");
        }else if (chbRecepcionista.isSelected() == true){
            usuario.setCargo("R");
        }else{
            throw new CampoObrigatorioException();
        }
    }
    public void inicializarCamposTela(){
        txtNome.setText(usuario.getNome());
        txtLogin.setText(usuario.getLogin());
        switch (usuario.getCargo()) {
            case "AS":
                chbAssistenteSocial.setSelected(true);
                break;
            case "G":
                chbGestor.setEnabled(true);
                chbGestor.setSelected(true);
                break;
            case "OS":            
                chbOrientadorSocial.setSelected(true);
                break;
            default:
                chbRecepcionista.setSelected(true);
                break;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrupoUsuarios = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblCamposObrigatorios = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        pnlGrupoUsuarios = new javax.swing.JPanel();
        chbGestor = new javax.swing.JCheckBox();
        chbAssistenteSocial = new javax.swing.JCheckBox();
        chbRecepcionista = new javax.swing.JCheckBox();
        chbOrientadorSocial = new javax.swing.JCheckBox();
        lblNome = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnFechar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        lblRepitaSenha = new javax.swing.JLabel();
        txtRepitaSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Cadastro de Usuarios");
        setExtendedState(6);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestao de Usuarios"));
        jPanel1.setToolTipText("TelaUsuarios");
        jPanel1.setAutoscrolls(true);

        lblCamposObrigatorios.setForeground(new java.awt.Color(230, 45, 12));
        lblCamposObrigatorios.setText("* Campos Obrigatórios");

        txtLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLoginFocusLost(evt);
            }
        });

        pnlGrupoUsuarios.setBorder(javax.swing.BorderFactory.createTitledBorder("*Grupo de Usuários"));

        btnGrupoUsuarios.add(chbGestor);
        chbGestor.setText("Gestor");

        btnGrupoUsuarios.add(chbAssistenteSocial);
        chbAssistenteSocial.setText("Assistente Social");

        btnGrupoUsuarios.add(chbRecepcionista);
        chbRecepcionista.setText("Recepcionista");

        btnGrupoUsuarios.add(chbOrientadorSocial);
        chbOrientadorSocial.setText("Orientador Social");

        javax.swing.GroupLayout pnlGrupoUsuariosLayout = new javax.swing.GroupLayout(pnlGrupoUsuarios);
        pnlGrupoUsuarios.setLayout(pnlGrupoUsuariosLayout);
        pnlGrupoUsuariosLayout.setHorizontalGroup(
            pnlGrupoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGrupoUsuariosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlGrupoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbGestor)
                    .addComponent(chbAssistenteSocial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(pnlGrupoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbRecepcionista)
                    .addComponent(chbOrientadorSocial))
                .addGap(230, 230, 230))
        );
        pnlGrupoUsuariosLayout.setVerticalGroup(
            pnlGrupoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGrupoUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGrupoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbGestor)
                    .addComponent(chbRecepcionista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGrupoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbAssistenteSocial)
                    .addComponent(chbOrientadorSocial))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        lblNome.setText("*Nome:");

        lblLogin.setText("*Login:");

        lblSenha.setText("*Senha:");

        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNomeFocusLost(evt);
            }
        });

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/cross79.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.setAlignmentX(0.5F);
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/text133_1.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setAlignmentX(0.5F);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/save (2).png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setAlignmentX(0.5F);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/1467304636_search.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSenhaFocusLost(evt);
            }
        });

        lblRepitaSenha.setText("*Repita a Senha:");

        txtRepitaSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRepitaSenhaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGrupoUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCamposObrigatorios)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtRepitaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblRepitaSenha)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(140, 140, 140)
                                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNome)
                                    .addComponent(lblLogin)
                                    .addComponent(lblSenha))
                                .addGap(55, 55, 55)
                                .addComponent(txtNome)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogin)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRepitaSenha)
                    .addComponent(txtRepitaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlGrupoUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCamposObrigatorios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        int resposta;
        String mensagem = "Os dados ja preenchidos serao descartados";
        String titulo = "Cadastrar Novo";
        resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            limparCamposTela();
            txtLogin.setEnabled(true);
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            UsuarioBO usuarioBO = new UsuarioBO();
            this.validarCamposObrigatorios();
            this.recuperarCamposTela();
            if(this.acaoTela == 0){
                usuarioBO.criar(usuario);
                JOptionPane.showMessageDialog(this, "Usuario cadastrado com sucesso!", "Cadastro de usuario", JOptionPane.INFORMATION_MESSAGE);
            }else{    
                usuarioBO.atualizarDados(usuario);           
                JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!", "Editar de usuario", JOptionPane.INFORMATION_MESSAGE);
            } 
            this.limparCamposTela();
            desabilitarBotoesTela();
        }catch(SistemaAveriguacaoException sae){
            String mensagem = "Erro ao realizar operação:\n" + sae.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de Usuario", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de usuario", JOptionPane.ERROR_MESSAGE);
        }    
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        if (!txtNome.getText().trim().isEmpty()
                || !txtLogin.getText().trim().isEmpty()
                || !txtSenha.getText().trim().isEmpty()){        
            String mensagem = "Deseja fechar a tela de cadastros de usuarios?"
                    + "Os dados ja preenchidos serao descartados!";
            String titulo = "Fechar Tela Cadastro de Usuarios";
            int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                limparCamposTela();
                this.dispose();
            }  
        }else{
            this.dispose();
        }
    }//GEN-LAST:event_btnFecharActionPerformed

    private void txtNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusLost
        habilitarBotoesTela();
    }//GEN-LAST:event_txtNomeFocusLost

    private void txtLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoginFocusLost
        habilitarBotoesTela();
    }//GEN-LAST:event_txtLoginFocusLost

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String mensagem = "Os dados ja preenchidos serao descartados";
        String titulo = "Listar Usuarios Cadastrados";
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            limparCamposTela();
            if(telaListarUsuario == null){
                telaListarUsuario = new ConsultarUsuarioForm();
            }
            telaListarUsuario.setVisible(true);
            this.dispose();
        }   
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusLost
        habilitarBotoesTela();
    }//GEN-LAST:event_txtSenhaFocusLost

    private void txtRepitaSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRepitaSenhaFocusLost
        habilitarBotoesTela();
    }//GEN-LAST:event_txtRepitaSenhaFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroUsuarioForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.ButtonGroup btnGrupoUsuarios;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chbAssistenteSocial;
    private javax.swing.JCheckBox chbGestor;
    private javax.swing.JCheckBox chbOrientadorSocial;
    private javax.swing.JCheckBox chbRecepcionista;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCamposObrigatorios;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblRepitaSenha;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JPanel pnlGrupoUsuarios;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtRepitaSenha;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
