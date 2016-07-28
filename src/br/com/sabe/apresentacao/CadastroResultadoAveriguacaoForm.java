/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.apresentacao;

import br.com.sabe.apresentacao.classesUteis.DocumentoLimitado;
import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.PedidoAveriguacao;
import br.com.sabe.entidade.ResultadoAveriguacao;
import br.com.sabe.excecao.CampoObrigatorioException;
import br.com.sabe.excecao.SistemaAveriguacaoException;
import br.com.sabe.negocio.BeneficiarioBO;
import br.com.sabe.negocio.PedidoAveriguacaoBO;
import br.com.sabe.negocio.ResultadoAveriguacaoBO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author walisson
 */
public class CadastroResultadoAveriguacaoForm extends javax.swing.JFrame {
    PedidoAveriguacao pedidoAveriguacao = null;
    ResultadoAveriguacao resultadoAveriguacao = null;
    ConsultaResultadoAveriguacaoForm consultaResultado = null;
    List<PedidoAveriguacao> listaPedidoAveriguacao = new ArrayList<>();
    List<ResultadoAveriguacao> listaResultadoAveriguacao = new ArrayList<>();
    int acaoTela = 0;
    /**
     * Creates new form CadastroResultadoAveriguacao
     */
    public CadastroResultadoAveriguacaoForm(){
        this.acaoTela = 0;
        this.resultadoAveriguacao = new ResultadoAveriguacao();
        this.initComponents();
        this.desativarCamposPedidoAveriguacao();
    }
    public CadastroResultadoAveriguacaoForm(ResultadoAveriguacao resultadoConsultado,
            ConsultaResultadoAveriguacaoForm consultaResultadoAveriguacaoForm){
        this.acaoTela = 1;
        this.resultadoAveriguacao = resultadoConsultado;
        this.pedidoAveriguacao = resultadoConsultado.getPedidoAveriguacao();
        this.prepararTela();
    }
    private void prepararTela() {
        try {
            this.initComponents();
            this.desativarCamposPedidoAveriguacao();
            this.inicializarCamposPedidoTela();
            this.limitarCampos();
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de aluno", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    public void limitarCampos(){
        txtNisTitular.setDocument(new DocumentoLimitado(11));
    }
    public void desativarCamposPedidoAveriguacao(){
        this.txtDataPedido.setEnabled(false);
        this.txtSituacao.setEnabled(false);        
    }
    public void inicializarCamposPedidoTela(){
        this.lblNomeTitularAtivo.setText(pedidoAveriguacao.getBeneficiario().getNome());
        this.txtNisTitular.setText(pedidoAveriguacao.getBeneficiario().getNis());
        this.lblNomeTitularAtivo.setEnabled(false);
        this.txtNisTitular.setEnabled(false);
        this.desativarCamposPedidoAveriguacao();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");        
        this.txtDataPedido.setText(formatador.format(this.pedidoAveriguacao.getDataPedido()));
        this.txtSituacao.setText(pedidoAveriguacao.getSituacao());
    }
    public void recuperarCamposTela() throws ParseException{
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatador.parse(txtDataResultado.getText().trim());
        this.resultadoAveriguacao.setDataResultado(data);
        this.resultadoAveriguacao.setResultado(txtDescricaoResultado.getText());
        if(cmbDecisaoAveriguacao.getSelectedIndex() > 0){
            String decisaoSelecionada = cmbDecisaoAveriguacao.getSelectedItem().toString();
            this.resultadoAveriguacao.setDecisao(decisaoSelecionada);
        }else{
            throw new CampoObrigatorioException();
        }
        this.resultadoAveriguacao.setPedidoAveriguacao(this.pedidoAveriguacao);
    }
    private void validarCamposObrigatorios(){
        if(this.txtDataPedido.getText().trim().equals("") ||
                this.txtSituacao.getText().trim().equals("") ||
                this.txtNisTitular.getText().trim().equals("") ||
                this.lblNomeTitularAtivo.getText().trim().equals("") ||
                this.txtDataResultado.getText().trim().equals("") ||
                this.txtDescricaoResultado.getText().trim().equals("")){
            throw new CampoObrigatorioException();
        }
    }
    public void limparCamposTela(){
        this.txtDataPedido.setText("");
        this.txtSituacao.setText("");
        this.txtNisTitular.setText("");
        this.lblNomeTitularAtivo.setText("");
        this.txtDataResultado.setText("");
        this.txtDescricaoResultado.setText("");
        this.cmbDecisaoAveriguacao.setSelectedIndex(0);
    }
    private String lerNisTitular() {
        String nis = txtNisTitular.getText().trim();

        String mensagem = "";

        if (nis.length() < 11) {
            mensagem = "NIS inválido";
        }

        if (!(mensagem == "" || mensagem == null)) {
            throw new CampoObrigatorioException(mensagem);
        }

        return nis;
    }
    private void setNomeBeneficiarioTela() {
        lblNomeTitular.setVisible(true);
        lblNomeTitularAtivo.setText(pedidoAveriguacao.getBeneficiario().getNome());
        lblNomeTitularAtivo.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCadastroPedidoAveriguacao = new javax.swing.JPanel();
        lblCamposObrigatorios = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNisTitular = new javax.swing.JFormattedTextField();
        lblNomeTitular = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        btnVerificarNis = new javax.swing.JButton();
        lblNomeTitularAtivo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSituacao = new javax.swing.JTextArea();
        lblDescriscaoMotivo = new javax.swing.JLabel();
        lblDataPedido = new javax.swing.JLabel();
        txtDataPedido = new javax.swing.JFormattedTextField();
        pnlResultadoAveriguacao = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricaoResultado = new javax.swing.JTextArea();
        lblDecisaoAssistenteSocial = new javax.swing.JLabel();
        lblDescricaoResultado = new javax.swing.JLabel();
        cmbDecisaoAveriguacao = new javax.swing.JComboBox<>();
        lblDataResultado = new javax.swing.JLabel();
        txtDataResultado = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Cadastro de Resultado de Averiguacao");
        setExtendedState(6);

        pnlCadastroPedidoAveriguacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultado de Averiguacao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        pnlCadastroPedidoAveriguacao.setToolTipText("TelaUsuarios");
        pnlCadastroPedidoAveriguacao.setAutoscrolls(true);

        lblCamposObrigatorios.setForeground(new java.awt.Color(230, 45, 12));
        lblCamposObrigatorios.setText("* Campos Obrigatórios");

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sabe/apresentacao/icones/1467304636_search.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setAlignmentX(0.5F);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedido de Averiguacao"));

        lblNomeTitular.setText("*Nome do Titular:");

        lblLogin.setText("*NIS do Beneficiario Titular:");

        btnVerificarNis.setText("Verificar");
        btnVerificarNis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarNisActionPerformed(evt);
            }
        });

        lblNomeTitularAtivo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblNomeTitularAtivo.setForeground(new java.awt.Color(13, 168, 250));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogin)
                    .addComponent(lblNomeTitular))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNisTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerificarNis, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblNomeTitularAtivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogin)
                    .addComponent(txtNisTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerificarNis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeTitular)
                    .addComponent(lblNomeTitularAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Motivo da Averiguação"));

        txtSituacao.setColumns(20);
        txtSituacao.setRows(5);
        jScrollPane1.setViewportView(txtSituacao);

        lblDescriscaoMotivo.setText("Situação");

        lblDataPedido.setText("Data do Pedido:");

        txtDataPedido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescriscaoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataPedido))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblDataPedido)
                        .addGap(31, 31, 31)
                        .addComponent(lblDescriscaoMotivo)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtDataPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlResultadoAveriguacao.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado da Averiguação"));

        txtDescricaoResultado.setColumns(20);
        txtDescricaoResultado.setRows(5);
        jScrollPane2.setViewportView(txtDescricaoResultado);

        lblDecisaoAssistenteSocial.setText("Decisão:");

        lblDescricaoResultado.setText("Descrição:");

        cmbDecisaoAveriguacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Recadastrar", "Bloquear ", "Cancelar ", "Suspender " }));

        lblDataResultado.setText("Data do Resultado:");

        txtDataResultado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout pnlResultadoAveriguacaoLayout = new javax.swing.GroupLayout(pnlResultadoAveriguacao);
        pnlResultadoAveriguacao.setLayout(pnlResultadoAveriguacaoLayout);
        pnlResultadoAveriguacaoLayout.setHorizontalGroup(
            pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadoAveriguacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblDescricaoResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addComponent(lblDecisaoAssistenteSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblDataResultado))
                .addGap(59, 59, 59)
                .addGroup(pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResultadoAveriguacaoLayout.createSequentialGroup()
                        .addComponent(txtDataResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmbDecisaoAveriguacao, 0, 554, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        pnlResultadoAveriguacaoLayout.setVerticalGroup(
            pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlResultadoAveriguacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataResultado)
                    .addComponent(txtDataResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlResultadoAveriguacaoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblDescricaoResultado)))
                .addGap(18, 18, 18)
                .addGroup(pnlResultadoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDecisaoAssistenteSocial, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbDecisaoAveriguacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCadastroPedidoAveriguacaoLayout = new javax.swing.GroupLayout(pnlCadastroPedidoAveriguacao);
        pnlCadastroPedidoAveriguacao.setLayout(pnlCadastroPedidoAveriguacaoLayout);
        pnlCadastroPedidoAveriguacaoLayout.setHorizontalGroup(
            pnlCadastroPedidoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroPedidoAveriguacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroPedidoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroPedidoAveriguacaoLayout.createSequentialGroup()
                        .addComponent(lblCamposObrigatorios)
                        .addGap(600, 600, 600))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastroPedidoAveriguacaoLayout.createSequentialGroup()
                        .addGroup(pnlCadastroPedidoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlResultadoAveriguacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCadastroPedidoAveriguacaoLayout.createSequentialGroup()
                                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlCadastroPedidoAveriguacaoLayout.setVerticalGroup(
            pnlCadastroPedidoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastroPedidoAveriguacaoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResultadoAveriguacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCamposObrigatorios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastroPedidoAveriguacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCadastroPedidoAveriguacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCadastroPedidoAveriguacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String mensagem = "Os dados ja preenchidos serao descartados";
        String titulo = "Pesquisar Pedidos de Averiguações Cadastrados";
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            this.limparCamposTela();
            if(this.consultaResultado == null){
                consultaResultado = new ConsultaResultadoAveriguacaoForm();
            }
            consultaResultado.setVisible(true);
            this.dispose();
        } 
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        String mensagem = "Deseja fechar a tela de cadastros de usuarios?"
                + "Os dados ja preenchidos serao descartados!";
        String titulo = "Fechar Tela Cadastro de Usuarios";
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            limparCamposTela();
            this.dispose();
        }  
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        int resposta;
        String mensagem = "Os dados ja preenchidos serao descartados";
        String titulo = "Cadastrar Novo";
        resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            limparCamposTela();
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            ResultadoAveriguacaoBO resultadoAveriguacaoBO = new ResultadoAveriguacaoBO();
            this.validarCamposObrigatorios();
            this.recuperarCamposTela();
            if(this.acaoTela == 0){
                resultadoAveriguacaoBO.inserir(this.resultadoAveriguacao);
                JOptionPane.showMessageDialog(this, "Resultaod cadastrado com sucesso!", "Cadastro de usuario", JOptionPane.INFORMATION_MESSAGE);
            }else if(this.acaoTela == 1){    
                resultadoAveriguacaoBO.atualizar(this.resultadoAveriguacao);           
                JOptionPane.showMessageDialog(this, "Resultado atualizado com sucesso!", "Editar de usuario", JOptionPane.INFORMATION_MESSAGE);
            } 
            this.limparCamposTela();
        }catch(SistemaAveriguacaoException sae){
            String mensagem = "Erro ao realizar operação:\n" + sae.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de aluno", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Cadastro de usuario", JOptionPane.ERROR_MESSAGE);
        }    
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnVerificarNisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarNisActionPerformed
       try {
            BeneficiarioBO beneficiarioBO= new BeneficiarioBO();
            String nis = this.lerNisTitular();
            ResultadoAveriguacaoBO resultadoAveriguacaoBO = new ResultadoAveriguacaoBO();
            this.pedidoAveriguacao = resultadoAveriguacaoBO.verificarPedidoAveriguacaoExistente(nis);
            this.setNomeBeneficiarioTela();
            this.inicializarCamposPedidoTela();
       } catch (SistemaAveriguacaoException se) {
            JOptionPane.showMessageDialog(this, se.getMessage(), "Consultar Beneficiario", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Não foi possivel consultar o beneficiário \nTente novamente", "Cadastro pedido de Averiguacao", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerificarNisActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroResultadoAveriguacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroResultadoAveriguacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroResultadoAveriguacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroResultadoAveriguacaoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroResultadoAveriguacaoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVerificarNis;
    private javax.swing.JComboBox<String> cmbDecisaoAveriguacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCamposObrigatorios;
    private javax.swing.JLabel lblDataPedido;
    private javax.swing.JLabel lblDataResultado;
    private javax.swing.JLabel lblDecisaoAssistenteSocial;
    private javax.swing.JLabel lblDescricaoResultado;
    private javax.swing.JLabel lblDescriscaoMotivo;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNomeTitular;
    private javax.swing.JLabel lblNomeTitularAtivo;
    private javax.swing.JPanel pnlCadastroPedidoAveriguacao;
    private javax.swing.JPanel pnlResultadoAveriguacao;
    private javax.swing.JFormattedTextField txtDataPedido;
    private javax.swing.JFormattedTextField txtDataResultado;
    private javax.swing.JTextArea txtDescricaoResultado;
    private javax.swing.JFormattedTextField txtNisTitular;
    private javax.swing.JTextArea txtSituacao;
    // End of variables declaration//GEN-END:variables

}
