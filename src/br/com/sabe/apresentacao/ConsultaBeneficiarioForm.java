/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.apresentacao;
import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.entidade.Usuario;
import br.com.sabe.excecao.CampoObrigatorioException;
import br.com.sabe.negocio.BeneficiarioBO;
import br.com.sabe.negocio.BeneficioAndBeneficiarioBO;
import br.com.sabe.negocio.BeneficioBO;
import br.com.sabe.negocio.UsuarioBO;
import br.com.sabe.persistencia.UsuarioDAO;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author walisson
 */
public class ConsultaBeneficiarioForm extends javax.swing.JFrame {
    CadastroBeneficiarioForm cadastroBeneficiario = null;
    List<Beneficiario> beneficiarios = new ArrayList<>();
    List<Beneficio> beneficios = new ArrayList<>();
    List<BeneficioAndBeneficiario> listaBeneficioAndBeneficiario = new ArrayList<>();
    BeneficioAndBeneficiario beneficioAndBeneficiario;
    Beneficiario beneficiarioEmEdicao;
    Beneficiario beneficiarioEmExclusao;
    Beneficio beneficio = null;
    /**
     * Creates new form ConsultarBeneficiario
     */
    public ConsultaBeneficiarioForm() {
        prepararTela();
    }
    public void prepararTela(){
        try {
            this.initComponents();
            this.carregarTabelaBeneficiarios();
            this.carregarComboBeneficios();
            this.limitandoCampos();
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Pesquisar Usuarios", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    public void limitandoCampos(){
        this.txtNis.setDocument(new DocumentoLimitado(11));
        this.txtNome.setDocument(new DocumentoLimitado((60)));
    }
    public void carregarComboBeneficios() throws SQLException {
        BeneficioBO beneficioBO = new BeneficioBO();
        this.beneficios = beneficioBO.buscarTodos();

        this.cmbBeneficios.removeAllItems();
        this.cmbBeneficios.addItem("Selecione");
        for(Beneficio beneficio: beneficios){
            this.cmbBeneficios.addItem(beneficio.getNome());
        }
    }
    /*public void carregarTabelaBeneficiarios() throws SQLException{
        BeneficiarioBO beneficiarioBO = new BeneficiarioBO();
        this.beneficiarios = beneficiarioBO.buscarTodos();

        ModeloTabelaAluno modelo = new ModeloTabelaAluno();
        tblBeneficiarios.setModel(modelo);
    }*/
    public void carregarTabelaBeneficiarios() throws SQLException{
        BeneficioAndBeneficiarioBO beneficioAndBeneficiarioBO = new BeneficioAndBeneficiarioBO();
        this.listaBeneficioAndBeneficiario = 
            beneficioAndBeneficiarioBO.buscarTodosBeneficioAndBeneficiario();
        ModeloTabelaBeneficiarios modelo = new ModeloTabelaBeneficiarios();
        tblBeneficiarios.setModel(modelo);
    }
    public void filtarTabelaBeneficiarios() throws SQLException{
        int beneficioSelecionado = this.cmbBeneficios.getSelectedIndex();
        Beneficio beneficioFiltro = null;
        BeneficioAndBeneficiarioBO beneficioAndBeneficiarioBO = new BeneficioAndBeneficiarioBO();
        if (beneficioSelecionado > 0){    
            for(Beneficio beneficio: beneficios){
                beneficioFiltro = beneficios.get(beneficioSelecionado-1);
                this.listaBeneficioAndBeneficiario = beneficioAndBeneficiarioBO.buscarByBeneficio(beneficioFiltro);
            }           
            ModeloTabelaBeneficiarios modelo = new ModeloTabelaBeneficiarios();
            tblBeneficiarios.setModel(modelo);
        }else if(!txtNis.getText().equals("")){ 
            this.listaBeneficioAndBeneficiario = beneficioAndBeneficiarioBO.buscarByNis(this.txtNis.getText());                   
            ModeloTabelaBeneficiarios modelo = new ModeloTabelaBeneficiarios();
            tblBeneficiarios.setModel(modelo);
        }else if(!txtNome.getText().equals("")){
            this.listaBeneficioAndBeneficiario = beneficioAndBeneficiarioBO.buscarByNome(this.txtNome.getText());                   
            ModeloTabelaBeneficiarios modelo = new ModeloTabelaBeneficiarios();
            tblBeneficiarios.setModel(modelo);
        }else{
            throw new CampoObrigatorioException();     
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

        pnlResultado2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBeneficiarios = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        pnlFiltro = new javax.swing.JPanel();
        lblNis = new javax.swing.JLabel();
        txtNis = new javax.swing.JFormattedTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCurso = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        cmbBeneficios = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Consulta de Beneficiário");
        setExtendedState(6);

        pnlResultado2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        tblBeneficiarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5", "Título 6"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBeneficiarios.setName(""); // NOI18N
        tblBeneficiarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblBeneficiarios);
        tblBeneficiarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblBeneficiarios.getColumnModel().getColumnCount() > 0) {
            tblBeneficiarios.getColumnModel().getColumn(3).setResizable(false);
            tblBeneficiarios.getColumnModel().getColumn(5).setResizable(false);
        }

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

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/cross79.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/rubbish7 (2).png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlResultado2Layout = new javax.swing.GroupLayout(pnlResultado2);
        pnlResultado2.setLayout(pnlResultado2Layout);
        pnlResultado2Layout.setHorizontalGroup(
            pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultado2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                    .addGroup(pnlResultado2Layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlResultado2Layout.setVerticalGroup(
            pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultado2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        lblNis.setText("NIS do Beneficiario Titular:");

        txtNis.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNisActionPerformed(evt);
            }
        });

        lblNome.setText("Nome do Beneficiário Titular:");

        lblCurso.setText("Beneficio:");

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/spam-control-interface-symbol-of-outlined-filter.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        cmbBeneficios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(cmbBeneficios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlFiltroLayout.createSequentialGroup()
                                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNis))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addComponent(lblNis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBeneficios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlResultado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResultado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if(cadastroBeneficiario == null){
            cadastroBeneficiario  = new CadastroBeneficiarioForm();
        }
        cadastroBeneficiario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int linhaSelecionada = tblBeneficiarios.getSelectedRow();
        try {
            BeneficioAndBeneficiario beneficiarioSelecionado = listaBeneficioAndBeneficiario.get(linhaSelecionada);
            CadastroBeneficiarioForm cadastroBeneficiarioForm = 
                    new CadastroBeneficiarioForm(this, beneficiarioSelecionado);
            cadastroBeneficiarioForm.setVisible(true);
            this.dispose();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma linha da tabela para poder editar os dados de um beneficiario.",
                    "Editar Beneficiario",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linhaSelecionada = tblBeneficiarios.getSelectedRow();
        try {
            this.beneficiarioEmExclusao = listaBeneficioAndBeneficiario.get(linhaSelecionada).getBeneficiario();
            String mensagem = "Deseja excluir beneficiario? \n"
                    + "OBS:Os dados cadastrados da averiguação também seram excluidos";
            String titulo = "Excluir Beneficiario";
            int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                BeneficiarioBO beneficiarioBO = new BeneficiarioBO();
                beneficiarioBO.excluir(beneficiarioEmExclusao);
                JOptionPane.showMessageDialog(this, "Beneficiario excluido com sucesso!", 
                    "Excluir Beneficiario", JOptionPane.INFORMATION_MESSAGE);
                carregarTabelaBeneficiarios();
            }
        } catch (SQLException ex) {
            String mensagem = null;
                mensagem += "\nMensagem de erro:\n" + ex.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Excluir Usuario", JOptionPane.ERROR_MESSAGE);
        }catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this,e.getMessage(),"Selecione uma linha da tabela para poder excluir alguma venda.", 
                    JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        try {
            this.filtarTabelaBeneficiarios();
        } catch (SQLException ex) {
            String mensagem = null;
                mensagem += "\nMensagem de erro:\n" + ex.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Filtar Beneficiario", JOptionPane.ERROR_MESSAGE);
        }
            
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void txtNisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNisActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultaBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaBeneficiarioForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox cmbBeneficios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado2;
    private javax.swing.JTable tblBeneficiarios;
    private javax.swing.JFormattedTextField txtNis;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
    private class ModeloTabelaBeneficiarios extends AbstractTableModel {

        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "Nis";
            }  else if (coluna == 1) {
                return "Nome";
            } else if (coluna == 2){                
                return "Beneficio";
            }else if (coluna == 3){                
                return "Zona";
            }else if (coluna == 4){
                return "Renda Familiar";
            }else {
                return "Renda Per Capta";
            }
        }

        @Override
        public int getRowCount() {
            return listaBeneficioAndBeneficiario.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            BeneficioAndBeneficiario beneficioAndBeneficiario = 
                listaBeneficioAndBeneficiario.get(linha);
            if (coluna == 0) {
                return beneficioAndBeneficiario.getBeneficiario().getNis();
            } else if (coluna == 1) {
                return beneficioAndBeneficiario.getBeneficiario().getNome();
            } else if (coluna == 2){ 
                return beneficioAndBeneficiario.getBeneficio().getNome();
            } else if (coluna == 3){
                if(beneficioAndBeneficiario.getBeneficiario().getZona().equals("U")){
                    return "Urbana";
                }else{
                    return "Rural";
                }                 
            }else if (coluna == 4){
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(beneficioAndBeneficiario.getBeneficiario().getRendaFamiliar());
            }else {
                DecimalFormat formatador = new DecimalFormat("#,##0.00");
                return formatador.format(beneficioAndBeneficiario.getBeneficiario().getRendaPerCapta());
            }            
        }
    }
}
