package databaseapplication.instructor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame f = new JFrame();
		JButton b = new JButton("");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new InstructorMainWindow();
				
			}
		});
		
		f.add(b);
		f.setVisible(true);
	}

}
