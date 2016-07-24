/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabe.apresentacao;

import br.com.sabe.entidade.Beneficiario;
import br.com.sabe.entidade.BeneficioAndBeneficiario;
import br.com.sabe.entidade.Beneficio;
import br.com.sabe.excecao.CampoObrigatorioException;
import br.com.sabe.excecao.SistemaAveriguacaoException;
import br.com.sabe.negocio.BeneficiarioBO;
import br.com.sabe.negocio.BeneficioBO;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

/**
 *
 * @author walisson
 */
public class CadastroBeneficiarioForm extends javax.swing.JFrame {
    List<Beneficio> beneficios = new ArrayList<>();
    Beneficiario beneficiario;
    Beneficio beneficio;
    BeneficioAndBeneficiario beneficioAndBeneficiario = new BeneficioAndBeneficiario() ;
    ConsultaBeneficiarioForm consultaBeneficiarioForm = null;
    CadastroPedidoAveriguacaoForm cadastroPedidoAveriguacaoForm = null;
    int acaoTela;
    /**
     * Creates new form CadastroBeneficiarioForm
     */
    public CadastroBeneficiarioForm() {
        this.acaoTela = 0;
        this.beneficiario = new Beneficiario();
        this.beneficio = new Beneficio();
        this.beneficioAndBeneficiario = new BeneficioAndBeneficiario();
        BeneficioAndBeneficiario beneficioAndBeneficiario= new BeneficioAndBeneficiario();
        this.prepararTela();
    }
    
    public CadastroBeneficiarioForm(ConsultaBeneficiarioForm consultaBeneficiarioForm,
            BeneficioAndBeneficiario beneficioAndBeneficiario){
        this.acaoTela = 1;
        this.beneficio = beneficioAndBeneficiario.getBeneficio();
        this.beneficiario = beneficioAndBeneficiario.getBeneficiario();
        this.consultaBeneficiarioForm = consultaBeneficiarioForm;
        this.beneficioAndBeneficiario = new BeneficioAndBeneficiario();
        this.prepararTela();
    }
    public void prepararTela(){
        try {
            this.initComponents();
            this.carregarComboBeneficios();
            this.limitandoCampos();
            if(acaoTela == 1){
                this.inicializarCamposTela();
                this.txtNis.setEnabled(false);
            }
        } catch (Exception e) {
            String mensagem = "Erro inesperado! Informe a mensagem de erro ao administrador do sistema.";
            mensagem += "\nMensagem de erro:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this, mensagem, "Pesquisar Usuarios", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    public void limitandoCampos(){
        txtNis.setDocument(new DocumentoLimitado(11));
        txtNome.setDocument(new DocumentoLimitado(60));
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
    public void inicializarCamposTela() throws ParseException{
        this.txtNome.setText(beneficiario.getNome());
        this.txtNis.setText(beneficiario.getNis());
        cmbBeneficios.setSelectedItem(beneficio.getNome());
        if(beneficiario.getZona().equals("R")){
            rdoRural.setSelected(true);
        }else if(beneficiario.getZona().equals("U")){
            rdoUrbano.setSelected(true);
        }else{
            throw new CampoObrigatorioException();     
        }
        this.txtBairro.setText(beneficiario.getBairro());
        this.txtLocalidade.setText(beneficiario.getLocalidade());
        this.txtRua.setText(beneficiario.getRua());        
        this.txtNumero.setText(Integer.toString(beneficiario.getNumero()));        
        this.txtRendaFamiliar.setText(String.valueOf(beneficiario.getRendaFamiliar()));
        this.txtRendaPerCapta.setText(String.valueOf(beneficiario.getRendaPerCapta()));
    }
    public void recuperarCamposTela() throws ParseException{
        this.beneficiario.setNome(txtNome.getText());
        this.beneficiario.setNis(txtNis.getText());
        int beneficioSelecionado = this.cmbBeneficios.getSelectedIndex();
        if (beneficioSelecionado > 0) {
                this.beneficio = beneficios.get(beneficioSelecionado-1);
        }else{
            throw new CampoObrigatorioException();     
        }
        if(rdoRural.isSelected()){
            beneficiario.setZona("R");
        }else if(rdoUrbano.isSelected()){
            beneficiario.setZona("U");
        }else{
            throw new CampoObrigatorioException();     
        }
        this.beneficiario.setBairro(txtBairro.getText());
        this.beneficiario.setLocalidade(txtLocalidade.getText());
        this.beneficiario.setRua(txtRua.getText());
        String strNumero = txtNumero.getText();  
        if(!strNumero.equals("")){
                this.beneficiario.setNumero(Integer.parseInt(strNumero));
        }   
        String strRendaFamiliar = txtRendaFamiliar.getText();              
        if(!strRendaFamiliar.equals("")){
            DecimalFormat formatador = new DecimalFormat("#,##0.00");
            Double rendaFamiliar = formatador.parse(strRendaFamiliar).doubleValue();
            this.beneficiario.setRendaFamiliar(rendaFamiliar);
        }
        String strRendaPerCapta = txtRendaPerCapta.getText(); 
        if(!strRendaPerCapta.equals("")){
            DecimalFormat formatador = new DecimalFormat("#,##0.00");
            Double rendaPerCapta = formatador.parse(strRendaPerCapta).doubleValue();
            this.beneficiario.setRendaPerCapta(rendaPerCapta);
        }      
        this.beneficioAndBeneficiario.setBeneficio(this.beneficio);
        this.beneficioAndBeneficiario.setBeneficiario(this.beneficiario);
    }
    public void limparCamposTela(){
        txtNome.setText("");
        txtNis.setText("");
        txtRua.setText("");
        txtBairro.setText("");
        txtLocalidade.setText("");
        txtNumero.setText("");
        txtRendaFamiliar.setText("");
        txtRendaPerCapta.setText("");
        txtRendaFamiliar.setText("");
        cmbBeneficios.setSelectedIndex(0);
        btnZona.clearSelection();
    }
    public void validarCamposObrigratorios(){
        if(txtNome.getText().trim().isEmpty() ||
            txtLocalidade.getText().trim().isEmpty())
        {
            throw new CampoObrigatorioException();
        }
    }
     private void cadastrarPedidoAveriguacao() {
        String mensagem = "Deseja cadastrar um Pedido de Averiguação?";
        String titulo = "Tela cadastro de beneficiario";
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            limparCamposTela();
            if(cadastroPedidoAveriguacaoForm == null){
                cadastroPedidoAveriguacaoForm = new CadastroPedidoAveriguacaoForm(beneficiario);
            }
            cadastroPedidoAveriguacaoForm.setVisible(true);
            this.dispose();
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

        btnZona = new javax.swing.ButtonGroup();
        pnlCadastroBeneficiario = new javax.swing.JPanel();
        lblCamposObrigatorios = new javax.swing.JLabel();
        btnPesquisar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        pnlEnderecoRural = new javax.swing.JPanel();
        lblLocalidade = new javax.swing.JLabel();
        txtLocalidade = new javax.swing.JTextField();
        lblRua = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        lblBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        lblZona = new javax.swing.JLabel();
        rdoRural = new javax.swing.JRadioButton();
        rdoUrbano = new javax.swing.JRadioButton();
        txtNumero = new javax.swing.JTextField();
        pnlTitularDoBeneficio = new javax.swing.JPanel();
        txtNis = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        lblNis = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblBeneficio = new javax.swing.JLabel();
        cmbBeneficios = new javax.swing.JComboBox<>();
        pnlRenda = new javax.swing.JPanel();
        lblRendaFamiliar = new javax.swing.JLabel();
        lblRendaPerCapta = new javax.swing.JLabel();
        txtRendaFamiliar = new javax.swing.JTextField();
        txtRendaPerCapta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Cadastro de Beneficiário");
        setExtendedState(6);

        pnlCadastroBeneficiario.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Beneficiário"));
        pnlCadastroBeneficiario.setToolTipText("TelaUsuarios");
        pnlCadastroBeneficiario.setAutoscrolls(true);

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

        pnlEnderecoRural.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereco"));

        lblLocalidade.setText("*Localidade:");

        lblRua.setText("*Rua:");

        txtRua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuaActionPerformed(evt);
            }
        });

        lblNumero.setText("Número:");

        lblBairro.setText("*Bairro:");

        lblZona.setText("*Zona:");

        btnZona.add(rdoRural);
        rdoRural.setText("Rural");

        btnZona.add(rdoUrbano);
        rdoUrbano.setText("Urbana");

        javax.swing.GroupLayout pnlEnderecoRuralLayout = new javax.swing.GroupLayout(pnlEnderecoRural);
        pnlEnderecoRural.setLayout(pnlEnderecoRuralLayout);
        pnlEnderecoRuralLayout.setHorizontalGroup(
            pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoRuralLayout.createSequentialGroup()
                .addGroup(pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEnderecoRuralLayout.createSequentialGroup()
                        .addGroup(pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLocalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEnderecoRuralLayout.createSequentialGroup()
                                .addComponent(txtRua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addComponent(txtLocalidade)
                            .addComponent(txtBairro)))
                    .addGroup(pnlEnderecoRuralLayout.createSequentialGroup()
                        .addComponent(lblZona)
                        .addGap(101, 101, 101)
                        .addComponent(rdoRural)
                        .addGap(65, 65, 65)
                        .addComponent(rdoUrbano)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEnderecoRuralLayout.setVerticalGroup(
            pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoRuralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblZona)
                    .addComponent(rdoRural)
                    .addComponent(rdoUrbano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalidade)
                    .addComponent(txtLocalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRua)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoRuralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBairro)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        pnlTitularDoBeneficio.setBorder(javax.swing.BorderFactory.createTitledBorder("Titular do Beneficio"));

        txtNis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNisFocusLost(evt);
            }
        });
        txtNis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNisActionPerformed(evt);
            }
        });

        lblNome.setText("*Nome do Titular:");

        lblNis.setText("*Número do NIS:");

        txtNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNomeFocusLost(evt);
            }
        });
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblBeneficio.setText("*Benefício:");

        cmbBeneficios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlTitularDoBeneficioLayout = new javax.swing.GroupLayout(pnlTitularDoBeneficio);
        pnlTitularDoBeneficio.setLayout(pnlTitularDoBeneficioLayout);
        pnlTitularDoBeneficioLayout.setHorizontalGroup(
            pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitularDoBeneficioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTitularDoBeneficioLayout.createSequentialGroup()
                        .addGroup(pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addComponent(lblBeneficio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbBeneficios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNome)))
                    .addGroup(pnlTitularDoBeneficioLayout.createSequentialGroup()
                        .addComponent(lblNis)
                        .addGap(20, 20, 20)
                        .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTitularDoBeneficioLayout.setVerticalGroup(
            pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitularDoBeneficioLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNis)
                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome))
                .addGap(18, 18, 18)
                .addGroup(pnlTitularDoBeneficioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeneficio)
                    .addComponent(cmbBeneficios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlRenda.setBorder(javax.swing.BorderFactory.createTitledBorder("Renda"));

        lblRendaFamiliar.setText("Renda Familiar:");

        lblRendaPerCapta.setText("Renda Per Capta:");

        txtRendaFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRendaFamiliarActionPerformed(evt);
            }
        });

        txtRendaPerCapta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRendaPerCaptaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRendaLayout = new javax.swing.GroupLayout(pnlRenda);
        pnlRenda.setLayout(pnlRendaLayout);
        pnlRendaLayout.setHorizontalGroup(
            pnlRendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRendaFamiliar)
                .addGap(31, 31, 31)
                .addComponent(txtRendaFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(lblRendaPerCapta)
                .addGap(32, 32, 32)
                .addComponent(txtRendaPerCapta, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        pnlRendaLayout.setVerticalGroup(
            pnlRendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRendaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlRendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRendaFamiliar)
                    .addComponent(lblRendaPerCapta)
                    .addComponent(txtRendaFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRendaPerCapta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout pnlCadastroBeneficiarioLayout = new javax.swing.GroupLayout(pnlCadastroBeneficiario);
        pnlCadastroBeneficiario.setLayout(pnlCadastroBeneficiarioLayout);
        pnlCadastroBeneficiarioLayout.setHorizontalGroup(
            pnlCadastroBeneficiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroBeneficiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroBeneficiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroBeneficiarioLayout.createSequentialGroup()
                        .addComponent(lblCamposObrigatorios)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCadastroBeneficiarioLayout.createSequentialGroup()
                        .addGroup(pnlCadastroBeneficiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlEnderecoRural, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlRenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlTitularDoBeneficio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastroBeneficiarioLayout.createSequentialGroup()
                                .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlCadastroBeneficiarioLayout.setVerticalGroup(
            pnlCadastroBeneficiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroBeneficiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitularDoBeneficio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEnderecoRural, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlRenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(lblCamposObrigatorios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastroBeneficiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroBeneficiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCadastroBeneficiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCadastroBeneficiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCadastroBeneficiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNisFocusLost
        
    }//GEN-LAST:event_txtNisFocusLost

    private void txtNisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNisActionPerformed
        
    }//GEN-LAST:event_txtNisActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String mensagem = "Os dados ja preenchidos serao descartados";
        String titulo = "Pesquisar Pedidos de Averiguações Cadastrados";
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            limparCamposTela();
            if(consultaBeneficiarioForm == null){
            consultaBeneficiarioForm = new ConsultaBeneficiarioForm();
            }
            consultaBeneficiarioForm.setVisible(true);
            this.dispose();
        }  
        
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNomeFocusLost
        
    }//GEN-LAST:event_txtNomeFocusLost

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed

    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        String mensagem = "Deseja fechar a tela de Cadastro de Beneficiario?"
                + "Os dados ja preenchidos serao descartados!";
        String titulo = "Fechar Tela Cadastro de Beneficiario";
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
            this.validarCamposObrigratorios();
            this.recuperarCamposTela();             
            BeneficiarioBO beneficiarioBO = new BeneficiarioBO();
            if(this.acaoTela == 0){
                beneficiarioBO.inserir(this.beneficiario, this.beneficioAndBeneficiario);           
                JOptionPane.showMessageDialog(this,
                        "Beneficiario cadastrado com sucesso!",
                        "Cadastro de beneficiario",
                        JOptionPane.INFORMATION_MESSAGE);
                    this.limparCamposTela();
                    this.cadastrarPedidoAveriguacao();
            }else{
                beneficiarioBO.atualizar(this.beneficiario, this.beneficioAndBeneficiario);           
                JOptionPane.showMessageDialog(this,
                        "Beneficiario alterado com sucesso!",
                        "Editar Cadastro de Beneficiario",
                        JOptionPane.INFORMATION_MESSAGE);
                this.limparCamposTela();
                this.consultaBeneficiarioForm.carregarTabelaBeneficiarios();
            }
            
        }catch(SistemaAveriguacaoException sae){
            JOptionPane.showMessageDialog(
                    this,
                    sae.getMessage(),
                    "Cadastro de beneficario",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        catch (Exception e) {
            System.out.println("Erro inesperado! Informe a mensagem de erro ao administrador do sistema.");
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(
                    this,
                    "Erro inesperado! Informe o erro ao administrador do sistema",
                    "Cadastro de beneficiario",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtRuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuaActionPerformed

    private void txtRendaFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRendaFamiliarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRendaFamiliarActionPerformed

    private void txtRendaPerCaptaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRendaPerCaptaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRendaPerCaptaActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroBeneficiarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroBeneficiarioForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup btnZona;
    private javax.swing.JComboBox<String> cmbBeneficios;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblBeneficio;
    private javax.swing.JLabel lblCamposObrigatorios;
    private javax.swing.JLabel lblLocalidade;
    private javax.swing.JLabel lblNis;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblRendaFamiliar;
    private javax.swing.JLabel lblRendaPerCapta;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblZona;
    private javax.swing.JPanel pnlCadastroBeneficiario;
    private javax.swing.JPanel pnlEnderecoRural;
    private javax.swing.JPanel pnlRenda;
    private javax.swing.JPanel pnlTitularDoBeneficio;
    private javax.swing.JRadioButton rdoRural;
    private javax.swing.JRadioButton rdoUrbano;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtLocalidade;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRendaFamiliar;
    private javax.swing.JTextField txtRendaPerCapta;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables

}
