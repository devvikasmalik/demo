package com.scheduler.form.lov;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MultiCheckBoxListComponent extends JDialog {

    private JTable MultiCheckBoxListTable;
    private JButton okButton = new JButton();
    private JButton cancelButton = new JButton();

    private String formTitle;
    private boolean formModal;

    private ArrayList<String> outSelectedIDs =new ArrayList();
    private ArrayList<String> outSelectedIdDesc=new ArrayList();

    private int posX = 0;
    private int posY = 0;

    private ArrayList<String> listIDs;
    private ArrayList<String> listIdDesc;
    private ArrayList<String> listColumnHeaders;
  
    public MultiCheckBoxListComponent() {

        this(null, "", false, 0, 0, null, null, null);
    }

    public MultiCheckBoxListComponent(Frame parent, String title,
                                      boolean modal, int X, int Y,
                                      ArrayList<String> iID,
                                      ArrayList<String> iDesc,
                                      ArrayList<String> columnHeaders) {
        super(parent, title, modal);
        try {
            formTitle = title;
            formModal = modal;
            posX = X;
            posY = Y;
            listIDs = iID;
            listIdDesc = iDesc;
            listColumnHeaders = columnHeaders;
            initListOfValues();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initListOfValues() {
   
   

    MultiCheckBoxListTable = new JTable(){  
       public boolean isCellEditable(int row,int column){  
         Object o = getValueAt(row,column);  
         if(column!=2) return false;  
         return true;  
       }  
     };//(data, columnsHeaders);
    JScrollPane jsp = new JScrollPane();
    jsp.getViewport().add(MultiCheckBoxListTable);
    
    DefaultTableModel model = new DefaultTableModel() {

                            public Class<?> getColumnClass(int column) {
                                    switch (column) {
                                    case 0:
                                            return String.class;
                                    case 1:
                                            return String.class;
                                    default:
                                            return Boolean.class;
                                    }
                            }
                    };
                    MultiCheckBoxListTable.setModel(model);
                    
    for (int i=0; i < listColumnHeaders.size(); i++) {
        model.addColumn(listColumnHeaders.get(i));
    }
    MultiCheckBoxListTable.getColumn(listColumnHeaders.get(0)).setWidth(0);
    MultiCheckBoxListTable.getColumn(listColumnHeaders.get(0)).setMinWidth(0);
    MultiCheckBoxListTable.getColumn(listColumnHeaders.get(0)).setMaxWidth(0);
    MultiCheckBoxListTable.getColumn(listColumnHeaders.get(1)).setWidth(150);
    MultiCheckBoxListTable.getColumn(listColumnHeaders.get(1)).setMinWidth(400);
    MultiCheckBoxListTable.getColumn(listColumnHeaders.get(1)).setMaxWidth(400);
    
    
    for (int i = 0; i < listIDs.size(); i++) {
        model.addRow(new Object[0]);

        for (int j = 0; j < listColumnHeaders.size(); j++) {
            if (j==0)
            {
            model.setValueAt(listIDs.get(i), i, j);
            }
            else if(j==1)
            {
            model.setValueAt(listIdDesc.get(i), i, j);
            }
            else if(j==2)
            {
                model.setValueAt(false, i, j);
            }else {
                model.setValueAt("Unknown", i, j);
            }

        }
    
    
    }
    
            
    
    MultiCheckBoxListTable.setTableHeader(null);
    okButton.setText("OK");
    cancelButton.setText("Cancel");
    
    JPanel ctrlPanel = new JPanel();
    ctrlPanel.add(okButton);
    ctrlPanel.add(cancelButton);
    
    this.setSize(new Dimension(500, 500));
    this.setLocation(posX, posY);
    this.getContentPane().setLayout(new BorderLayout());
    this.setTitle(formTitle);
    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    this.setModal(formModal);
    
    this.getContentPane().add(jsp);
    this.getContentPane().add(ctrlPanel, BorderLayout.SOUTH);
    
    okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
          closeForm(false);}
    });
    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
          closeForm(true);}
    });
    
    addWindowListener(new WindowAdapter() { public void widowClosing(WindowEvent e){closeForm(true);}
    });
    
}
    
    private void closeForm(boolean cleanIDs) 
    {
      
        outSelectedIDs.clear();
        outSelectedIdDesc.clear();
   
        for (int i=0; i<MultiCheckBoxListTable.getRowCount();i++ ) {
           Boolean isChecked = Boolean.valueOf( MultiCheckBoxListTable.getValueAt(i, 2).toString());
            
            if (isChecked) {
                    outSelectedIDs.add(MultiCheckBoxListTable.getValueAt(i, 0).toString());
                    outSelectedIdDesc.add(MultiCheckBoxListTable.getValueAt(i, 1).toString());
                }


        }
        listIDs.clear();
        listIdDesc.clear();
        listColumnHeaders.clear();
      this.setVisible(false);
    }


    public String getOutSelectedIDs() {
        String sep = "";
        String outID="";
        if (outSelectedIDs.isEmpty()==false) {
           
            for(int i=0;  i<outSelectedIDs.toArray().length; i++)
            {
              outID = outID + sep +outSelectedIDs.get(i);  
              sep = ",";
            }       
        }
        
        return outID;
    }

    public String getOutSelectedIdDesc() {
      
        String sep = "";
        String outDesc="";
        if (outSelectedIdDesc.isEmpty()==false) {
           
            for(int i=0;  i<outSelectedIdDesc.toArray().length; i++)
            {
              outDesc = outDesc + sep +outSelectedIdDesc.get(i);  
              sep = ",";
            }       
        } 
        return outDesc;
    }
}
