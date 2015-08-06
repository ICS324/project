package databaseapplication.instructor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setBounds(400, 300, 400, 300);
		f.setLayout(new GridLayout(4,3));
		
		JButton vs = new JButton("View Students");
		vs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewStudentsDialog();}});
		
		JButton as = new JButton("Assign Cutoffs");
		as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignCutoffsDialog();}});
		
		f.add(vs);
		f.add(as);

		f.setDefaultCloseOperation(2);
		f.setVisible(true);

	}

}
