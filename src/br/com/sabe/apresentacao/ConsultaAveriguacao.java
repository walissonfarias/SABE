/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.apresentacao;
import br.com.sabe.apresentacao.classesUteis.DocumentoLimitado;
import br.com.sabe.entidade.Beneficio;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.entidade.ResultadoAveriguacao;
import br.com.sabe.entidade.Usuario;
import br.com.sabe.excecao.CampoObrigatorioException;
import br.com.sabe.negocio.BeneficioAndBeneficiarioBO;
import br.com.sabe.negocio.BeneficioBO;
import br.com.sabe.negocio.ResultadoAveriguacaoBO;
import br.com.sabe.negocio.UsuarioBO;
import br.com.sabe.persistencia.UsuarioDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class ConsultaAveriguacao extends javax.swing.JFrame {
    ConsultaResultadoAveriguacaoForm consultaAveriguacao = null;
    List<ResultadoAveriguacao> listaAveriguacao = new ArrayList<>();
    ResultadoAveriguacao resultadoEmExclusao;
    List<Beneficio> beneficios = new ArrayList<>();
    List<BeneficioAndBeneficiario> listaBeneficioAndBeneficiario = new ArrayList<>();
    /*
     * Creates new form NovoJFrame
     */
    public ConsultaAveriguacao() {
        prepararTela();
    }  
    public void prepararTela(){
        try {
            this.initComponents();
            this.carregarTabelaAveriguacao();
            this.limitandoCampos();
            this.carregarComboBeneficios();
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Pesquisar Usuarios", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    public void limitandoCampos(){
        this.txtNis.setDocument(new DocumentoLimitado(11));
        this.txtNome.setDocument(new DocumentoLimitado(60));
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
    public void filtarTabelaAveriguacao() throws SQLException{
        int beneficioSelecionado = this.cmbBeneficios.getSelectedIndex();
        Beneficio beneficioFiltro = null;
        BeneficioAndBeneficiarioBO beneficioAndBeneficiarioBO = new BeneficioAndBeneficiarioBO();
        if (beneficioSelecionado > 0){    
            for(Beneficio beneficio: beneficios){
                beneficioFiltro = beneficios.get(beneficioSelecionado-1);
                this.listaBeneficioAndBeneficiario = beneficioAndBeneficiarioBO.buscarByBeneficio(beneficioFiltro);
            }           
            ModeloTabelaAveriguacao modelo = new ModeloTabelaAveriguacao();
            tblAveriguacao.setModel(modelo);
        }else if(!txtNis.getText().equals("")){ 
            this.listaBeneficioAndBeneficiario = beneficioAndBeneficiarioBO.buscarByNis(this.txtNis.getText());                   
            ModeloTabelaAveriguacao modelo = new ModeloTabelaAveriguacao();
            tblAveriguacao.setModel(modelo);
        }else if(!txtNome.getText().equals("")){
            this.listaBeneficioAndBeneficiario = beneficioAndBeneficiarioBO.buscarByNome(this.txtNome.getText());                   
            ModeloTabelaAveriguacao modelo = new ModeloTabelaAveriguacao();
            tblAveriguacao.setModel(modelo);
            
        }else{
            throw new CampoObrigatorioException();     
        }
    }
   /* public void carregarComboLocalidade() throws SQLException {
        PedidoAveriguacaoBO pedidoAveriguacaoBO = new PedidoAveriguacaoBO();
        this.listaPedidoAveriguacao = pedidoAveriguacaoBO.buscarTodos();

        this.cmbLocalidade.removeAllItems();
        
        for(PedidoAveriguacao pedidoAveriguacao: listaPedidoAveriguacao){
            this.cmbLocalidade.addItem(pedidoAveriguacao.beneficiario.getLocalidade());
        }
    }*/
    public void carregarTabelaAveriguacao() throws SQLException{
        ResultadoAveriguacaoBO resultadoAveriguacaoBO = new ResultadoAveriguacaoBO();
        listaAveriguacao = resultadoAveriguacaoBO.buscarAveriguacoes();

        ModeloTabelaAveriguacao modelo = new ModeloTabelaAveriguacao();
        tblAveriguacao.setModel(modelo);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFiltro = new javax.swing.JPanel();
        lblNis = new javax.swing.JLabel();
        txtNis = new javax.swing.JFormattedTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCurso = new javax.swing.JLabel();
        cmbBeneficios = new javax.swing.JComboBox();
        btnFiltar = new javax.swing.JButton();
        pnlResultado2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAveriguacao = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        pnlFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        lblNis.setText("NIS");

        txtNis.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lblNome.setText("Nome:");

        lblCurso.setText("Beneficio:");

        cmbBeneficios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFiltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/spam-control-interface-symbol-of-outlined-filter.png"))); // NOI18N
        btnFiltar.setText("Filtrar");
        btnFiltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome)
                    .addComponent(cmbBeneficios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFiltroLayout.createSequentialGroup()
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNis)
                            .addComponent(lblNome)
                            .addComponent(lblCurso)
                            .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFiltar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 323, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addComponent(lblNis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBeneficios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFiltar)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pnlResultado2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        tblAveriguacao.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAveriguacao);

        btnFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/cross79.png"))); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlResultado2Layout = new javax.swing.GroupLayout(pnlResultado2);
        pnlResultado2.setLayout(pnlResultado2Layout);
        pnlResultado2Layout.setHorizontalGroup(
            pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultado2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(pnlResultado2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlResultado2Layout.setVerticalGroup(
            pnlResultado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultado2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(pnlResultado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnFiltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltarActionPerformed
        try {
            this.filtarTabelaAveriguacao();
        } catch (SQLException ex) {
            String mensagem = null;
                mensagem += "\nMensagem de erro:\n" + ex.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Filtar Beneficiario", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltarActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultaAveriguacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaAveriguacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaAveriguacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaAveriguacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaAveriguacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnFiltar;
    private javax.swing.JComboBox cmbBeneficios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlResultado2;
    private javax.swing.JTable tblAveriguacao;
    private javax.swing.JFormattedTextField txtNis;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
    private class ModeloTabelaAveriguacao extends AbstractTableModel {
        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "NIS:";
            }  else if (coluna == 1) {
                return "Beneficiario:";
            } else if (coluna == 2){                
                return "Data do Pedido:";
            }else if (coluna == 3){                
                return "Situacao:";
            } else if (coluna == 4){                
                return "Data do Resultado:";
            } else if (coluna == 5){ 
                return "Resultado:";
            }else{
                return "Decisao:";
            }
        }

        @Override
        public int getRowCount() {
            return listaAveriguacao.size();
        }

        @Override
        public int getColumnCount() {
            return 7;
        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            ResultadoAveriguacao resultadoAveriguacao= listaAveriguacao.get(linha);
            if (coluna == 0) {
                return resultadoAveriguacao.getPedidoAveriguacao().getBeneficiario().getNis();
            } else if (coluna == 1) {
                return resultadoAveriguacao.getPedidoAveriguacao().getBeneficiario().getNome();
            } else if (coluna == 2){ 
                SimpleDateFormat formatador= new SimpleDateFormat("dd/MM/yyyy");
                return formatador.format(resultadoAveriguacao.getPedidoAveriguacao().getDataPedido());
            } else if (coluna == 3){
                return resultadoAveriguacao.getPedidoAveriguacao().getSituacao();
            }else if (coluna == 4){
                SimpleDateFormat formatador= new SimpleDateFormat("dd/MM/yyyy");
                if(resultadoAveriguacao.getDataResultado() != null){
                    return formatador.format(resultadoAveriguacao.getDataResultado());
                }else{
                    return "";
                }
            }else if (coluna == 5){
                if(resultadoAveriguacao.getResultado()!= null){
                    return resultadoAveriguacao.getResultado();
                }else{
                    return "aguardando...";
                }    
            }else{
                if(resultadoAveriguacao.getDecisao() != null){
                    return resultadoAveriguacao.getDecisao();
                }else{
                    return "";
                }
            }
        }

    }
}
