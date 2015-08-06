package databaseapplication.instructor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		/*
	  	//TODO view grade cutoffs (delete)	
	  	 * assign grade cutoffs //TODO
	  	//TODO assigning grading componenets (delete)
	  	 * view assigned grading componenets for a section (course?)
	  	//TODO assinging points (update , delete)
	  	 * view points
	 	 * view students in a section 
	     * view activity's points
	     * view section's statistics 
	     * 

				--grading components
				--delete from grading_component where id = 1
				--insert into grading_component values ('2', 'Midterm', '25', '25', '1234', '1')
				
				--grading cutoffs
				--update grading_cutoffs set value= 100 where sec_ref_num = 1231 and letter_grade='A+'
				--select letter_grade,value from grading_cutoffs where sec_ref_num = 1231 order by value desc
				--delete from grading_cutoffs where sec_ref_num = 1231 and value = 98
				--insert into grading_cutoffs values ('A+',98,1231)
				
	     */		
		JFrame f = new JFrame();
		f.setBounds(400, 300, 400, 300);
		f.setLayout(new GridLayout(4,3));
		
		JButton vs = new JButton("View Students");
		vs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewStudentsDialog();}});
		
		JButton as = new JButton("Assign Cutoffs");//TODO incompelete
		as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignCutoffsDialog();}});
		
		JButton vc = new JButton("View Grading Componenets");
		vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewGradingComponentsDialog();}});
		
		JButton vp = new JButton("View Points For A Section");
		vp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewPointsDialog();}});
		
		JButton vpa = new JButton("View Points For An Activity");
		vpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewActivityPointsDialog();}});
		
		JButton st = new JButton("View Statisics For A Section");
		st.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewSectionStatisticsDialog();}});
		
		
		f.add(vs);
		f.add(as);
		f.add(vc);
		f.add(vp);
		f.add(vpa);
		f.add(st);
		f.setDefaultCloseOperation(2);
		f.setVisible(true);

	}

}
