/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.apresentacao;

import br.com.sabe.entidade.Usuario;
import br.com.sabe.negocio.UsuarioBO;
import br.com.sabe.persistencia.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author wff
 */
public class ConsultarUsuarioForm extends javax.swing.JFrame {
    Usuario usuario;
    CadastroUsuarioForm telaCadastroUsuario = null;
    List<Usuario> usuarios = new ArrayList<>();
    
    /**
     * Creates new form TelaPesquisaUsuario
     */
    public ConsultarUsuarioForm() {
        this.prepararTela();
    }
    
    public void prepararTela(){
        try {
            this.initComponents();
            this.carregarTabelaUsuarios();
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Pesquisar Usuarios", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    public void carregarTabelaUsuarios() throws SQLException {
        UsuarioBO usuarioBO = new UsuarioBO();
        this.usuarios = usuarioBO.buscarTodos();

        ModeloTabelaAluno modelo = new ModeloTabelaAluno();
        tblResultado.setModel(modelo);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlResultado = new javax.swing.JPanel();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela Consultar Usuário ");
        setExtendedState(6);

        pnlResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/rubbish7 (2).png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/pencil113.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/text133.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/cross79_1.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        tblResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblResultado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblResultado);

        javax.swing.GroupLayout pnlResultadoLayout = new javax.swing.GroupLayout(pnlResultado);
        pnlResultado.setLayout(pnlResultadoLayout);
        pnlResultadoLayout.setHorizontalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addGroup(pnlResultadoLayout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlResultadoLayout.setVerticalGroup(
            pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linhaSelecionada = tblResultado.getSelectedRow();
        try {
            Usuario usuarioSelecionado = usuarios.get(linhaSelecionada);
            UsuarioBO usuarioBO = new UsuarioBO();               
            String mensagem = "Deseja excluir usuario?";
            String titulo = "Excluir Usuario";
            int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                usuarioBO.excluirUsuario(usuarioSelecionado);   
                JOptionPane.showMessageDialog(this, "Usuario excluido com sucesso!", "Excluir Usuario",
                    JOptionPane.INFORMATION_MESSAGE);
                carregarTabelaUsuarios();
            }
        } catch (SQLException ex) {
            String mensagem = null;
                mensagem += "\nMensagem de erro:\n" + ex.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Excluir Usuario", JOptionPane.ERROR_MESSAGE);
        }catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma linha da tabela para poder excluir alguma venda.",
                    "Exclusão de venda",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int linhaSelecionada = tblResultado.getSelectedRow();
        try{
            Usuario usuarioSelecionado = usuarios.get(linhaSelecionada);
            CadastroUsuarioForm telaCadastro = new CadastroUsuarioForm(usuarioSelecionado);
            telaCadastro.setVisible(true);
            this.dispose();
        }catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma linha da tabela para poder editar algum usuario.",
                    "Alteração de usuario",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if(telaCadastroUsuario == null){
            telaCadastroUsuario = new CadastroUsuarioForm();
        }    
        telaCadastroUsuario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNovoActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarUsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarUsuarioForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlResultado;
    private javax.swing.JTable tblResultado;
    // End of variables declaration//GEN-END:variables
    private class ModeloTabelaAluno extends AbstractTableModel {

        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "Nome";
            } else if (coluna == 1) {
                return "Login";
            } else {
                return "Cargo";
            }
        }

        @Override
        public int getRowCount() {
            return usuarios.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Usuario usuario = usuarios.get(rowIndex);
            if (columnIndex == 0) {
                return usuario.getNome();
            } else if (columnIndex == 1) {
                return usuario.getLogin();
            } else {                
                String cargo = "";
                if (usuario.getCargo().equals("AS")) {
                    cargo = "Assistente Social";
                }else if (usuario.getCargo().equals("G")) {
                    cargo = "Gestor";
                }else if (usuario.getCargo().equals("OS")) {
                    cargo = "Orientador Social";
                }else if (usuario.getCargo().equals("R")){
                    cargo = "Recepcionista";
                }
                return cargo;
            } 
        }

    }
}
