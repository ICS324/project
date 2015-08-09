/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.UI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ibrahim
 */
public class LabeledTextField extends JPanel{
    private JLabel label;
    private JTextField textField;
    
    public LabeledTextField(String label){
        this.label = new JLabel(""+label);
        this.textField = new JTextField();
        
        super.setLayout(new GridLayout(2,1));
        super.add(this.label);
        super.add(this.textField);
    }
    
    public String getText(){
        return this.textField.getText();
    }
}
