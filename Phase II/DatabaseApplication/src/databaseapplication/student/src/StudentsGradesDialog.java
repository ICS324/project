
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

 class StudentsGradesDialog extends JDialog implements ActionListener
{
    private JComboBox testBox = new JComboBox();
    private JButton testButton = new JButton();
    int c = 0;

    public StudentsGradesDialog()
    {
        testBox = new JComboBox();
        testButton = new JButton("Click Me!");
        testButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(testBox);
        panel.add(testButton);
        this.add(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        testBox.addItem("test" + c++);/*
        testBox.addItem("test" + c++);
        testBox.addItem("test" + c++);
        testBox.addItem("test" + c++);*/
    }

 
    }
