package databaseapplication.instructor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import databaseapplication.CommonMethods;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Main {
static Connection con;
static String instructorID;
static CommonMethods cm;
	public static void main(String[] args) {
            if(args.length==0)
                instructorID = "1";
            else
                instructorID = args[0];
            cm = new CommonMethods();
            con = cm.getConnection();

                
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
		f.setSize(525, 275);
		f.setLayout(new GridLayout(4,0));
		
		JButton vs = new JButton("View");//TODO extract as new table
		vs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewStudentsDialog();}});

		JButton as = new JButton("Assign");//TODO extract as new table
		as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignCutoffsDialog();}});
		
		JButton vc = new JButton("View");//TODO extract as new table
		vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewGradingComponentsDialog();}});
		
		JButton vp = new JButton("<html>View For<br />A Section</html>");//TODO extract as new table
		vp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewPointsDialog();}});
		
		JButton vpa = new JButton("<html>View For<br />An Activity</html>");//TODO extract as new table
		vpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewActivityPointsDialog();}});
		
		JButton st = new JButton("<html>View<br />Statisics</html>");//TODO extract as new table
		st.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new InstructorViewSectionStatisticsDialog();}});
		
		JButton dc = new JButton("Delete");
		dc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorDeleteGradingComponenetDialog();}});
		
		JButton vgc = new JButton("View");
		vgc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewGradingCutoffsDialog();}});
		
		JButton ap = new JButton("Assign");
		ap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignPointsDialog();}});
		JPanel[] p = {new JPanel(),new JPanel(),new JPanel(),new JPanel()};
                GridLayout g = new GridLayout();
                g.setHgap(5);

                for (JPanel a : p){
                    a.setLayout(g);
                }
                
                
                p[0].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Student menu"));
                p[1].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Grading componenets"));
                p[2].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Points"));
                p[3].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Cutoffs"));
                
                
		p[2].add(ap);p[0].add(vs);p[3].add(as);p[1].add(vc);p[2].add(vp);
		p[2].add(vpa);p[2].add(st);p[1].add(dc);p[3].add(vgc);
		
		
                for (JPanel a : p)
                    f.add(a);
                
		f.setDefaultCloseOperation(2);
		f= new CommonMethods().CentralizeFrame(f);
	}

}
