import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp implements FocusListener,ActionListener{
	JFrame frame;
	JPanel panel;
	Point center;
	
	JTextField eid,fName,lName,user,pass,confPass,phn,focus;
	JComboBox accType;
	JLabel label;
	JButton btn,btn2;
	
	final int WIDTH=700;
	final int HEIGHT=650;
	final int p_width=500,p_height=550;
	int x = (WIDTH/2)-(p_width/2);
	int y = (HEIGHT/2)-(p_height/2);
	String types[] = {"Faculty","Librarian"};
	
    public SignUp() {
    	frame = new JFrame();
    	frame.setLayout(null);
    	frame.getContentPane().setBackground(new Color(53,152,220));//53,152,220
    	center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    	frame.setLocation((int)center.getX()-WIDTH/2,(int)center.getY()-HEIGHT/2); 
    	 
    	panel = new JPanel();
    	panel.setBounds(x,y,p_width,p_height);
    	panel.setBackground(Color.white);
    	panel.setLayout(null);
    	Border border = BorderFactory.createLineBorder(Color.black,3,false);
    	panel.setBorder(border);
    	
    	eid = new JTextField();
    	fName = new JTextField();
    	lName = new JTextField();
    	user = new JTextField();
    	pass = new JTextField();
    	confPass = new JTextField();
    	phn = new JTextField();
    	focus = new JTextField();
    	label = new JLabel("SIGN UP");
    	accType = new JComboBox(types);	
    	btn = new JButton("Create Account");
    	btn2 = new JButton("Cancel");
    	
    	label.setBounds(175,20,200,40);
    	eid.setBounds(75,75,350,45);
    	fName.setBounds(75,135,190,45);
        lName.setBounds(275,135,150,45);
        user.setBounds(75,195,350,45);
        pass.setBounds(75,255,350,45);
        confPass.setBounds(75,315,350,45);
        phn.setBounds(75,375,350,45);
        accType.setBounds(75,435,350,45);
        btn.setBounds(75,495,165,45);
        btn2.setBounds(250,495,165,45);
        
        label.setFont(new Font("Courier",Font.BOLD,35));
        eid.setFont(new Font("Courier",Font.PLAIN,20));
        fName.setFont(new Font("Courier",Font.PLAIN,20));
        lName.setFont(new Font("Courier",Font.PLAIN,20));
        user.setFont(new Font("Courier",Font.PLAIN,20));
        pass.setFont(new Font("Courier",Font.PLAIN,20));
        confPass.setFont(new Font("Courier",Font.PLAIN,20));
        phn.setFont(new Font("Courier",Font.PLAIN,20));
        accType.setFont(new Font("Courier",Font.PLAIN,20));
        btn.setFont(new Font("Courier",Font.BOLD,15));
        btn2.setFont(new Font("Courier",Font.BOLD,15));
        
        eid.setBackground(new Color(229,229,229));
        fName.setBackground(new Color(229,229,229));
        lName.setBackground(new Color(229,229,229));
        user.setBackground(new Color(229,229,229));
        pass.setBackground(new Color(229,229,229));
        confPass.setBackground(new Color(229,229,229));
        phn.setBackground(new Color(229,229,229));
        
        btn.setBackground(new Color(53,152,220));
    	btn.setForeground(Color.white);
    	btn2.setBackground(new Color(53,152,220));
    	btn2.setForeground(Color.white);  
    	
    	eid.addFocusListener(this);
    	fName.addFocusListener(this);
    	lName.addFocusListener(this);
    	user.addFocusListener(this);
    	pass.addFocusListener(this);
    	confPass.addFocusListener(this);
    	phn.addFocusListener(this);
    	
    	btn.addActionListener(this);
    	btn2.addActionListener(this);
    	
    	eid.setText("Employee Id");
    	fName.setText("First Name");
    	lName.setText("Last Name");
    	user.setText("Username");
    	pass.setText("Password");
    	confPass.setText("Confirm Password");
    	phn.setText("Phone no");
    	
    	eid.setBorder(null);
    	fName.setBorder(null);
    	lName.setBorder(null);
    	user.setBorder(null);
    	pass.setBorder(null);
    	confPass.setBorder(null);
    	phn.setBorder(null);
    	
    	eid.setForeground(new Color(153,153,153));
    	fName.setForeground(new Color(153,153,153));
    	lName.setForeground(new Color(153,153,153));
    	user.setForeground(new Color(153,153,153));
    	pass.setForeground(new Color(153,153,153));
    	confPass.setForeground(new Color(153,153,153));
    	phn.setForeground(new Color(153,153,153));
        
    	panel.add(focus);
        panel.add(label);
    	panel.add(eid);
    	panel.add(fName);
    	panel.add(lName);
    	panel.add(user);
    	panel.add(pass);
    	panel.add(confPass);
    	panel.add(phn);
    	panel.add(accType);
    	panel.add(btn);
    	panel.add(btn2);
    	
    	frame.add(panel);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(WIDTH,HEIGHT);
    	frame.setVisible(true);
    }

	public void focusGained(FocusEvent e) {
		
		if(e.getSource() == eid && eid.getText().equals("Employee Id")) {
			
			eid.setText("");
			eid.setForeground(Color.black);
			
		}else if(e.getSource() == fName && fName.getText().equals("First Name")) {
			
			fName.setText("");
			fName.setForeground(Color.black);
			
		}else if(e.getSource() == lName && lName.getText().equals("Last Name")) {
			
			lName.setText("");
			lName.setForeground(Color.black);
			
		}else if(e.getSource() == user && user.getText().equals("Username")) {
			
			user.setText("");
			user.setForeground(Color.black);
			
		}else if(e.getSource() == pass && pass.getText().equals("Password")) {
			
			pass.setText("");
			pass.setForeground(Color.black);
			
		}else if(e.getSource() == confPass && confPass.getText().equals("Confirm Password")) {
			
			confPass.setText("");
			confPass.setForeground(Color.black);
			
		}else if(e.getSource() == phn && phn.getText().equals("Phone no")) {
			
			phn.setText("");
			phn.setForeground(Color.black);
		}
	}

	public void focusLost(FocusEvent e) {
		if(e.getSource() == eid && eid.getText().equals("")) {
			
			eid.setText("Employee Id");
			eid.setForeground(new Color(153,153,153));
			
		}else if(e.getSource() == fName && fName.getText().equals("")) {
			
			fName.setText("First Name");
			fName.setForeground(new Color(153,153,153));
			
		}else if(e.getSource() == lName && lName.getText().equals("")) {
			
			lName.setText("Last Name");
			lName.setForeground(new Color(153,153,153));
			
		}else if(e.getSource() == user && user.getText().equals("")) {
			
			user.setText("Username");
			user.setForeground(new Color(153,153,153));
			
		}else if(e.getSource() == pass && pass.getText().equals("")) {
			
			pass.setText("Password");
			pass.setForeground(new Color(153,153,153));
			
		}else if(e.getSource() == confPass && confPass.getText().equals("")) {
			
			confPass.setText("Confirm Password");
			confPass.setForeground(new Color(153,153,153));
			
		}else if(e.getSource() == phn && phn.getText().equals("")) {
			
			phn.setText("Phone no");
			phn.setForeground(new Color(153,153,153));
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btn) {
			signup();
		}
		else if(ae.getSource() == btn2) {
			frame.dispose();
			new Launch();
		}
	}
	
	public void signup() {
		if(!areAllFieldsSet()) {
		    JOptionPane.showMessageDialog(null, "Please set all fields", "Warning", JOptionPane.WARNING_MESSAGE);
		}else {
			try {
				checkForId();
			}catch(Exception e) {
				
			}
		}
	}
	
	public boolean areAllFieldsSet() {
		String empid = eid.getText();
		String name1 = fName.getText();
		String name2 = lName.getText();
		String userN = user.getText();
		String password = pass.getText();
		String confirm = confPass.getText();
		String phone = phn.getText();
	
		if(empid.equals("Employee Id") || name1.equals("First Name") || name2.equals("Last Name") || userN.equals("Username")
				|| password.equals("Password") || confirm.equals("Confirm Password") || phone.equals("Phone no")) {
			return false;
		}else if(empid.length() <1 || name1.length() <1 || name2.length() <1 || userN.length() <1
				|| password.length() <1 || confirm.length() < 1 || phone.length() <1) {
			return false;
		}else if(!password.equals(confirm)) {
			JOptionPane.showMessageDialog(null,"Check Both Passwords","Warning",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else
			return true;
	}
	
	public void checkForId() throws Exception {
		String username = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
		String query = "Select * from Admin where empid="+eid.getText();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		boolean available = rs.next();
		
		stmt.close();
		conn.close();
		
		if(available) {
			checkDuplicate();
		}else {
			JOptionPane.showMessageDialog(null, "Employee id not Registered", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void checkDuplicate() throws Exception {
		String usernameDB = "root";
		String passwordDB = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
		String query = "";
		String type = (String)accType.getSelectedItem();	
		
		if(type.equals("Faculty")) {
			query = "Select * from Faculty where empid='"+eid.getText()+"' or username='"+user.getText()+"'";
		}else {
			query = "Select * from Librarian where empid='"+eid.getText()+"' or username='"+user.getText()+"'";
		}
				
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = conn = DriverManager.getConnection(url, usernameDB, passwordDB);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		if(rs.next()) {
			JOptionPane.showMessageDialog(null,"Employee Id/Username Already Exists","Warning",JOptionPane.WARNING_MESSAGE);
		}else {
			createAccount();
		}
	}
	
	public void createAccount() throws Exception {
		String empid = eid.getText();
		String name1 = fName.getText();
		String name2 = lName.getText();
		String userN = user.getText();
		String password = pass.getText();
		String phone = phn.getText();
		String type = (String) accType.getSelectedItem();
		
		String usernameDB = "root";
		String passwordDB = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB);
		Statement stmt;
		
		if(type.equals("Faculty")) {
			String query = "Insert into faculty(empid,first_name,last_name,username,password,phoneno)"
					+ "VALUES('"+empid+"','"+name1+"','"+name2+"','"+userN+"','"+password+"','"+phone+"')";
			
			stmt = conn.createStatement();
			
			int i = stmt.executeUpdate(query);
			
			System.out.println(i);
			if(i > 0) {
				JOptionPane.showMessageDialog(null, "Account Created Successfully");
				frame.dispose();
				new Launch();
			}
			
		}else {
			String query = "Insert into Librarian(empid,first_name,last_name,username,password,phoneno)"
					+ "VALUES('"+empid+"','"+name1+"','"+name2+"','"+userN+"','"+password+"','"+phone+"')";
			
			stmt = conn.createStatement();
			
			int i = stmt.executeUpdate(query);
			if(i > 0) {
				JOptionPane.showMessageDialog(null, "Account Created Successfully");
				frame.dispose();
				new Launch();
			}
		}
		
		stmt.close();
		conn.close();
	}
}
