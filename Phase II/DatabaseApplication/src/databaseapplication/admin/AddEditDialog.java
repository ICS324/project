/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.OkCancelPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ibrahim
 */
public class AddEditDialog extends JDialog{
    private OkCancelPanel okCancelPanel;
    private JPanel inputMethodContainer;
    private LinkedList<LabeledInputMethod> inputMethods;
    private JFrame parent;
    public AddEditDialog(JFrame parent){
        this.parent = parent;
        init();
        buildUI();
    }
    private void init(){
        this.okCancelPanel = new OkCancelPanel();
        this.inputMethodContainer = new JPanel();
        this.okCancelPanel.addCancelClickEvent(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }
    private void buildUI() {
        super.add("South",this.okCancelPanel);
        super.add("Center",this.inputMethodContainer);
        super.setSize(200, 300);
        super.setLocationRelativeTo(this.parent);
    }

    private void inputMethodsChanged(){
        for (LabeledInputMethod inputMethod : this.inputMethods) {
            try {
                this.inputMethodContainer.remove(inputMethod);
            }catch(Exception ex){System.out.println(ex);}
        }
        for (LabeledInputMethod inputMethod : this.inputMethods) {
            this.inputMethodContainer.add(inputMethod);
        }
    }
    public void addLabeledInputMethod(LabeledInputMethod input){
        if(input != null){
            this.inputMethods.add(input);
            this.inputMethodsChanged();
        }
    }
    
    public void addOkButtonClickEvent(ActionListener e){
        this.okCancelPanel.addOkClickEvent(e);
    }

    

}
