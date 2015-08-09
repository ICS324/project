/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.MyComboBox;
import Frameworks.UI.MyTextField;
import Frameworks.table.RowData;
import javax.swing.JFrame;

/**
 *
 * @author Ibrahim
 */
public class DepartmentDialog extends AddEditDialog{
     private LabeledInputMethod depID,deptName,depAbbev, collegeID, college;
    public DepartmentDialog(JFrame parent) {
        super(parent);
        init();
        buildUI();
    }
    public DepartmentDialog(JFrame parent, RowData toEdit){
        this(parent);
        if(toEdit != null){
            this.depID.setValue((String)toEdit.get(0));
            this.deptName.setValue((String)toEdit.get(1));
            this.depAbbev.setValue((String)toEdit.get(2));
            this.collegeID.setValue((String)toEdit.get(3));
        }
    }
    private void init(){
        this.depID = new LabeledInputMethod("Student ID:");
        this.deptName = new LabeledInputMethod("First Name:");
        this.depAbbev = new LabeledInputMethod("Last Name");
        this.collegeID = new LabeledInputMethod("College: ");
        
        MyComboBox box = new MyComboBox();
        char c = 'A';
        for(int i = 0 ; i < 26 ; i++){
            box.addItem(c+"");
            c++;
        }
        this.depID.setInputMethod(box);
        this.deptName.setInputMethod(new MyTextField());
        this.depAbbev.setInputMethod(new MyTextField());
    }

    
    private void buildUI(){
        super.addLabeledInputMethod(depID);
        super.addLabeledInputMethod(this.deptName);
        super.addLabeledInputMethod(this.depAbbev);

    }
    public String getCollegeID(){
        return this.depID.getValue();
    }
    
    public String getCollegeName(){
        return this.deptName.getValue();
    }

    public String getAbbreviation(){
        return this.depAbbev.getValue();
    }

}
