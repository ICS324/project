/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import Frameworks.OperationResult;
import Frameworks.table.RowData;
import databaseapplication.SuperManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ibrahim
 */
public class AdminMainWindow extends JFrame{
    
    private TablesList tablesList;
    private AppDatabaseEditTable dbTable;
    private ActionsPanel panel;
    private QueryField queryField;
    
    public AdminMainWindow(){
        super("Admen");
        init();
        buildUI();
        
    }
    private void init(){
        this.tablesList = new TablesList(SuperManager.getTables());
        this.dbTable = new AppDatabaseEditTable(SuperManager.getConnectionManager());
        this.panel = new ActionsPanel();
        this.queryField = new QueryField();
        this.queryField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String query = queryField.getQuery();
                dbTable.updateData(SuperManager.getConnectionManager().getResultSetAsTable(query));
            }
        });
        this.tablesList.addListSelectionListener(new ListSelectionListener(){
        
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dbTable.selectData((String)tablesList.getSelectedValue());
            }
        });
        this.panel.setRemoveRecordAction(new RemoveAction(this));
    }
    private void buildUI(){
        super.setDefaultCloseOperation(3);
        super.setLayout(new BorderLayout());
        super.add("West",this.tablesList);
        super.add("Center",this.dbTable);
        super.add("East",this.panel);
        super.add("South",this.queryField);
        super.setSize(500,500);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        
    }
    private class RemoveAction implements ActionListener{
        
        private JFrame Parent;
        public RemoveAction(JFrame Parent){
            this.Parent = Parent;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedTableName = (String)tablesList.getSelectedValue();
                RowData selectedRow = dbTable.getSelectedRow();
                if(selectedRow == null){
                    JOptionPane.showMessageDialog(Parent, "No row was selected!", "Delete", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int retVal = JOptionPane.showConfirmDialog(this.Parent, "Are you surethat you would like to remove the record?", "Remove Record", JOptionPane.YES_NO_OPTION);
                if(retVal == JOptionPane.YES_OPTION){
                    String [] tableFields = dbTable.getColumnsNames();
                    String deleteCondition = "";
                    for(int i = 0 ; i < tableFields.length ; i++){
                        if(i != tableFields.length - 1){
                            String selectedCellData = (String)selectedRow.get(i);
                            try{
                                Integer.parseInt(selectedCellData);
                                deleteCondition+=""+tableFields[i]+" = "+selectedCellData+" and ";
                            }
                            catch(Exception ex){
                                deleteCondition+=""+tableFields[i]+" = '"+selectedCellData+"' and ";
                            }
                            
                        }
                        else{
                            String selectedCellData = (String)selectedRow.get(i);
                            try{
                                Integer.parseInt(selectedCellData);
                                deleteCondition+=""+tableFields[i]+" = "+selectedCellData+"";
                            }
                            catch(Exception ex){
                                deleteCondition+=""+tableFields[i]+" = '"+selectedCellData+"' ";
                            }
                        }
                        
                        System.out.println(deleteCondition);
                    }
                    OperationResult result = SuperManager.delete(selectedTableName,deleteCondition);
                    if(result.getResult()){
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Delete", JOptionPane.INFORMATION_MESSAGE);
                        dbTable.selectData(selectedTableName);
                    }
                    else{
                        JOptionPane.showMessageDialog(Parent, result.getMessage(), "Delete", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
        }
        
    }
}
