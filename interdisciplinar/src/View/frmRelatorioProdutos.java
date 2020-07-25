/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.VariaveisGlobais;
import Model.CriaTabelaClientes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author User
 */
public class frmRelatorioProdutos extends javax.swing.JFrame {

    /**
     * Creates new form frmRelatorioProdutos
     */
    public frmRelatorioProdutos() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    String route = "C:\\Users\\User\\Desktop\\Inter 2.3\\mart.db";
    Connection c = null;
    Statement stat = null;
    String op;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:mart.db");
            if (c != null) {
                System.out.println("Connected to db.");
            }
        } catch (SQLException ex) {
            System.err.println("Couldn't connect." + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CriaTabelaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbScrollPesquisarProdutos = new javax.swing.JScrollPane();
        tblPesquisar = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblPesquisar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "COD. DE BARRAS", "ESTOQUE", "NOME", "DESCRIÇÃO", "FORNECEDOR", "CATEGORIA", "PREÇO", "PRECO DE CUSTO"
            }
        ));
        tblPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPesquisarMouseClicked(evt);
            }
        });
        tbScrollPesquisarProdutos.setViewportView(tblPesquisar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbScrollPesquisarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbScrollPesquisarProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPesquisarMouseClicked
        int index;
        index = tblPesquisar.getSelectedRow();
        TableModel model = tblPesquisar.getModel();
        int id = (int) model.getValueAt(index, 0);
        dispose();
        int verificaAbertura = 1;

        new frmCadastrarProduto(id, verificaAbertura).setVisible(true);

    }//GEN-LAST:event_tblPesquisarMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        while (!frmCadastrarProduto.pil.Vazia()) {
            String idEl = frmCadastrarProduto.pil.Remover();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("id");
            model.addColumn("codBarras");
            model.addColumn("qntEstoque");
            model.addColumn("nome");
            model.addColumn("descricao");
            model.addColumn("fornecedor");
            model.addColumn("categoria");
            model.addColumn("valor");
            model.addColumn("valor_custo");

            connect();
            try {
                DefaultTableModel aModel = (DefaultTableModel) tblPesquisar.getModel();
                VariaveisGlobais VG = new VariaveisGlobais();
               
                stat = c.createStatement();
                op = "SELECT * FROM produtos where id='" +idEl+"'";
                System.out.println(op);
                ResultSet rs = stat.executeQuery(op);
                int colNo = model.getColumnCount();
                while (rs.next()) {
                    Object[] objects = new Object[colNo];
                    for (int i = 0; i < colNo; i++) {
                        objects[i] = rs.getObject(i + 1);
                    }
                    aModel.addRow(objects);
                }
                tblPesquisar.setModel(aModel);
                stat.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar o maior id no banco");
                return;
            }
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(frmPesquisaClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(frmRelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRelatorioProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRelatorioProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane tbScrollPesquisarProdutos;
    private javax.swing.JTable tblPesquisar;
    // End of variables declaration//GEN-END:variables
}