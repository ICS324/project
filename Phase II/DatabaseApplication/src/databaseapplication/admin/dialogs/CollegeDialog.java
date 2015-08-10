/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin.dialogs;

import Frameworks.UI.LabeledInputMethod;
import Frameworks.UI.MyComboBox;
import Frameworks.UI.MyTextField;
import Frameworks.table.RowData;
import Frameworks.table.TableData;
import databaseapplication.SuperManager;
import javax.swing.JFrame;

/**
 *
 * @author Ibrahim
 */
public class CollegeDialog extends AddEditDialog{
    private LabeledInputMethod collegeID,collegeName,collegeAbbriv;
    public CollegeDialog(JFrame parent) {
        super(parent);
        init();
        buildUI();
    }
    public CollegeDialog(JFrame parent, RowData toEdit){
        this(parent);
        if(toEdit != null){
            this.collegeID.setValue((String)toEdit.get(0));
            this.collegeName.setValue((String)toEdit.get(1));
            this.collegeAbbriv.setValue((String)toEdit.get(2));
        }
    }
    private void init(){
        this.collegeID = new LabeledInputMethod("College ID:");
        this.collegeName = new LabeledInputMethod("Name:");
        this.collegeAbbriv = new LabeledInputMethod("Abbreiation");
        
        MyComboBox box = new MyComboBox();
        char c = 'A';
        for(int i = 0 ; i < 26 ; i++){
            box.addItem(c+"");
            c++;
        }
        this.collegeID.setInputMethod(box);
        this.collegeName.setInputMethod(new MyTextField());
        this.collegeAbbriv.setInputMethod(new MyTextField());
    }

    
    private void buildUI(){
        super.addLabeledInputMethod(collegeID);
        super.addLabeledInputMethod(this.collegeName);
        super.addLabeledInputMethod(this.collegeAbbriv);

    }
    public String getCollegeID(){
        return this.collegeID.getValue();
    }
    
    public String getCollegeName(){
        return this.collegeName.getValue();
    }

    public String getAbbreviation(){
        return this.collegeAbbriv.getValue();
    }

}
