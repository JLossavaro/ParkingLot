/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Config.ConfigMenu;

import java.awt.GridLayout;
import Controladores.Fatura;
import Controladores.Config;
import static Controladores.Fatura.lerFaturas;
import Controladores.Relogio;
import Controladores.VagaReservada;
import GUI.CarPositions.Faturamento;
import GUI.CarPositions.JanelaReservarVaga;
import GUI.Reports.ReportMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jclos
 */
public class JanelaMenuP extends javax.swing.JFrame {
    
    double total;
    Relogio relogio = new Relogio();//Criando objeto relogio para retornar metodos de data/hora
    private Config config = Config.lerConfig();
    private ArrayList<VagaReservada> vagas = VagaReservada.lerVagas();
    private ArrayList<Fatura> faturas = lerFaturas();
    private ArrayList<JButton> botoes = new ArrayList<JButton>();

    /**
     * Creates new form JanelaMenuP
     */
    public JanelaMenuP() {
        
        initComponents();  //inicia os componentes GUI NETBEANS

        this.setLocationRelativeTo(null); //coloca a posição da janela no centro, ao passar o null

        MyinitComponents();//Meu metodo que inicializa os metodos da classe no construtor

    }

    /*Metodo que inicializa os componentes em um classe separada da principal*/
    private void MyinitComponents() {
        clock();
        criaBotao();
        setFatura();
    }

    /*
    Metodo que percorre um array de fatura determinando o seu tamanho pela iterador, em seguida armazena toda as informacoes em um array.
    O metodo adiciona ao modelo a linha, assim adicionando a tabela do menu principal
    O metodo tambem contabiliza o montante da soma das faturas para mostrr na tela principal
     */
    public void setFatura() {
        
        for (int i = 0; i < faturas.size(); i++) {
            total += faturas.get(i).getFatura();
            Object[] linha = new Object[4];
            linha[0] = faturas.get(i).getPlaca();
            linha[1] = faturas.get(i).getQtdHoras();
            linha[2] = faturas.get(i).getFatura();
            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.addRow(linha);
        }
        fatTotal.setText("R$ " + String.valueOf(total));
        
    }

    /*
    Este metodo verifica se os campoes estão vazios e em seguida utiliza as informações de configuração para gerar os botões em suas respectivas fileiras
    O metodo cria um botão GUI e o lista repetidamente de acordo com o tamanho do modelo dos botões(quee foi criado nas configurações)
    
     */
    public void criaBotao() {
        if (config != null && !config.getModels().isEmpty()) {
            int maior = 0;
            
            for (int i = 0; i < config.getModels().size(); i++) {
                if (Integer.parseInt(config.getModels().get(i)) > maior) {//Pegando o valor maximo para determinar o tamnho do GRID
                    maior = Integer.parseInt(config.getModels().get(i));
                }
            }
            
            carPanel.setLayout(new GridLayout(config.getModels().size(), maior, 4, 4));// setando o tamanho do GRID e o seu espaçamento entre os objetos
            carPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);//Setando a organização da esquerda para direita
            carPanel.removeAll();
            int contador = 0;
            
            for (int i = 0; i < config.getModels().size(); i++) {
                for (int j = 0; j < Integer.parseInt(config.getModels().get(i)); j++) {
                    //botão
                    JButton botao = new JButton();
                    botao.setSize(200, 200);
                    botao.setBackground(Color.white);
                    String Nomebot = "Vaga " + (contador + 1);
                    contador++;//contador criado para determinar o numero da vaga, ele tambem sera utilizado como index
                    botao.setText(Nomebot);
                    botao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/carIcon_2.png")));
                    
                    botao.setVerticalTextPosition(SwingConstants.BOTTOM);
                    botao.setHorizontalTextPosition(SwingConstants.CENTER);
                    
                    if (vagas.size() < contador) {//se o botão não for inicializado ainda pelo arquivo ele sera inicializado aqui
                        vagas.add(new VagaReservada(contador, false));
                    }
                    if (vagas.get(contador - 1).isVagaReservada() == true) {
                        botao.setBackground(Color.red);//adicionando a cor vermelha ao botão, caso ja tenha sido reservado
                    }
                    
                    botoes.add(botao);
                    carPanel.add(botao);
                    botoes.get(contador - 1).addActionListener(listener);
                }
                
            }
        } else {
            System.out.println("adicione uma COnfiguração antes");
            
        }
        
    }

    /*Este metodo implementa um Runnable, assim instanciando um Thread, em seguida em seu run chama um metodo que obtem a data do sistema, ele realiza esa ação a cada 1000ms
    , ou seja, ele atualiza a hora a cada segundo*/
    public void clock() {
        
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                
                while (true) {
                    Jclock.setText(relogio.getTimeNow());//Método getTime, retorna uma string com o horário atual formatado a cada segundo
                    try {
                        Thread.sleep(1000); //coloca a thread em sleep por 1000ms
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                
            }
        });
        thread1.start();//Inicia a Thread

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        carPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        fatTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jConfigButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        data = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Jclock = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 1000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setAutoscrolls(true);
        jPanel1.setName("parkhour"); // NOI18N

        carPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        carPanel.setToolTipText("Carros");
        carPanel.setAutoscrolls(true);
        carPanel.setLayout(new java.awt.GridLayout());
        carPanel.setName("carros"); // NOI18N

        javax.swing.GroupLayout carPanelLayout = new javax.swing.GroupLayout(carPanel);
        carPanel.setLayout(carPanelLayout);
        carPanelLayout.setHorizontalGroup(
            carPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
        carPanelLayout.setVerticalGroup(
            carPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faturamento - " + relogio.getDateNow(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanel3.setForeground(new java.awt.Color(204, 204, 0));

        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PLACA", "Qtd Horas", "Valor Cobrado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        fatTotal.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        fatTotal.setText("R$ 65,00");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setText("Total:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fatTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(292, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(fatTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(525, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/exitIco.png"))); // NOI18N
        jButton1.setText("  Sair");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jConfigButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jConfigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/icons8-configurações-50.png"))); // NOI18N
        jConfigButton.setText("   Configurações");
        jConfigButton.setActionCommand("Configurações");
        jConfigButton.setFocusTraversalPolicyProvider(true);
        jConfigButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfigButtonActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/67360-200 (1).png"))); // NOI18N
        jButton3.setText("  Relatórios");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jConfigButton, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jConfigButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        data.setText("01/02/2032");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("PARKHOUR");
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        Jclock.setText("jLabel6");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(data)
                .addGap(322, 322, 322)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Jclock, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Jclock)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data))
                .addGap(19, 19, 19))
        );

        data.setText(relogio.getDateNow());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("PARKHOUR");
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(carPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(475, 475, 475)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(carPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCarButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new ReportMenu();//Cria um objeto ReportMenu, onde serão inseridas as configurações.
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConfigButtonActionPerformed
        boolean flag = false;
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).isVagaReservada() == true) {
                JOptionPane.showMessageDialog(null, "Remova todos os carros do estacionamento antes de alterar", "Erro", JOptionPane.ERROR_MESSAGE);
                flag = true;
            }
        }
        if (flag == false) {
            new ConfigMenu();
        }

        //Cria um objeto ConfigMenu, onde serão inseridas as configurações.
    }//GEN-LAST:event_jConfigButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed
    /*
    Este Listener atribui um ActionListener a variavel listener, em seguida esta variavel sera atribuida a cada um dos botões em sua criação utilizando
    pelo getText, tambem atribuido ao botão saber qual actionListener o botão se referenciara. 
     */
    ActionListener listener = new ActionListener() {
        @Override
        
        public void actionPerformed(ActionEvent e) {
            {
                if (e.getSource() instanceof JButton) {
                    JButton botao = ((JButton) e.getSource());
                    String array[] = new String[2];
                    array = botao.getText().split(" ");//nesa parte separar o "vagas" do valor do botao, em seguida utilizando o valor como identificador da posicao da vaga
                    if (vagas.get(Integer.parseInt(array[1]) - 1).isVagaReservada() == true) {//verificando se esta reservada ou não
                        new Faturamento(botao, vagas, faturas, jTable1, fatTotal);
                        
                    } else {
                        new JanelaReservarVaga(botao, vagas);//caso n esteja reservada, cria um objeto para atribuir uma placa a vaga
                    }
                    
                }
            }
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaMenuP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaMenuP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaMenuP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaMenuP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                new JanelaMenuP().setVisible(true);  //deixa a janela visivel

            }
            
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jclock;
    private javax.swing.JPanel carPanel;
    private javax.swing.JLabel data;
    private javax.swing.JLabel fatTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jConfigButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
