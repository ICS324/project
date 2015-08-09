package databaseapplication.instructor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import databaseapplication.CommonMethods;

public class Main {

	public static void main(String[] args) {
		/*
	  	 * TODO delete grade cutoffs ?maybe?
	  	 * view grade cutoffs	
	  	 * assign grade cutoffs
	  	 * TODO assign grading componenets 
	  	 * delete grading componenets
	  	 * view assigned grading componenets for a section (course?)
	  	 * TODO delete points ?maybe?
	  	 * assign points
	  	 * view points
	  	 * view students in a section 
	     * view activity's points
	     * view section's statistics 
	     * 
	     *		
	     *	grading components
	     *		delete from grading_component where id = 1
	     *		insert into grading_component values ('2', 'Midterm', '25', '25', '1234', '1')
	     *				
	     *	grading cutoffs
	     *		update grading_cutoffs set value= 100 where sec_ref_num = 1231 and letter_grade='A+'
	     *		delete from grading_cutoffs where sec_ref_num = 1231 and value = 98
	     *		insert into grading_cutoffs values ('A+',98,1231)	
	     */		
		JFrame f = new JFrame();
		f.setSize(500, 400);
		f.setLayout(new GridLayout(5,3));
		
		JButton vs = new JButton("View Students");//TODO final touch //TODO extract as new table
		vs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewStudentsDialog();}});

		JButton as = new JButton("Assign Cutoffs");//TODO final touch //TODO extract as new table
		as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignCutoffsDialog();}});
		
		JButton vc = new JButton("View Grading Componenets");//TODO final touch //TODO extract as new table
		vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewGradingComponentsDialog();}});
		
		JButton vp = new JButton("View Points For A Section");//TODO final touch //TODO extract as new table
		vp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewPointsDialog();}});
		
		JButton vpa = new JButton("View Points For An Activity");//TODO final touch //TODO extract as new table
		vpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewActivityPointsDialog();}});
		
		JButton st = new JButton("View Statisics For A Section");//TODO final touch //TODO extract as new table
		st.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new InstructorViewSectionStatisticsDialog();}});
		
		JButton dc = new JButton("Delete A Grading Componenet");//TODO final touch
		dc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorDeleteGradingComponenetDialog();}});
		
		JButton vgc = new JButton("View Grading Cutoffs");//TODO final touch
		vgc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewGradingCutoffsDialog();}});
		
		JButton ap = new JButton("Assign Points");//TODO final touch
		ap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignPointsDialog();}});
		
//		JButton ac = new JButton("Assign Grading Cutoffs");//TODO final touch
//		ac.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new InstructorAssignPointsDialog();}});
		
		
		f.add(vs);f.add(as);f.add(vc);f.add(vp);
		f.add(vpa);f.add(st);f.add(dc);f.add(vgc);
		f.add(ap);
		
		
		f.setDefaultCloseOperation(2);
		f= new CommonMethods().CentralizeFrame(f);

	}

}
