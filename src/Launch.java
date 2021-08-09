import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Launch implements ActionListener{
	private JFrame frame;
	private JTextField userField;
	private JPasswordField passField;
	private JLabel userLabel,passLabel,picture,typeLabel;
	private JComboBox accType;
	private JButton login,signup;
	
	private Point center;
	final int WIDTH=500,HEIGHT=500;
	ImageIcon icon = new ImageIcon("images\\login.png");
	String types[] = {"Student","Faculty","Librarian"};
	
    public Launch() {
    	frame =new JFrame();
    	userField = new JTextField();
    	passField = new JPasswordField();
    	userLabel = new JLabel("USERNAME");
    	passLabel = new JLabel("PASSWORD");
    	typeLabel = new JLabel("Account");
    	picture = new JLabel();
    	login = new JButton("LOGIN");
    	signup = new JButton("SIGNUP");
    	accType = new JComboBox(types);
    	
    	signup.addActionListener(this);
    	login.addActionListener(this);
    	
    	frame.setLayout(null);
    	
    	picture.setIcon(icon);
    	userLabel.setFont(new Font("consolas",Font.BOLD,23));
    	userField.setFont(new Font("consolas",Font.BOLD,23));
    	passLabel.setFont(new Font("consolas",Font.BOLD,23));
    	passField.setFont(new Font("consolas",Font.BOLD,23));
    	typeLabel.setFont(new Font("consolas",Font.BOLD,23));
    	accType.setFont(new Font("consolas",Font.BOLD,23));
    	login.setBackground(new Color(66,134,245));
    	signup.setBackground(new Color(66,134,245));
    	login.setForeground(Color.white);
    	signup.setForeground(Color.white);
    	userField.setBackground(new Color(229,229,229));
    	passField.setBackground(new Color(229,229,229));
    	
    	picture.setBounds(200,30,100,100);
    	userLabel.setBounds(100,170,150,40);
    	userField.setBounds(250,170,200,40);
    	passLabel.setBounds(100,230,150,40);
    	passField.setBounds(250,230,200,40);
    	typeLabel.setBounds(100,290,150,40);
    	accType.setBounds(250,290,200,40);
    	signup.setBounds(100,360,130,40);
    	login.setBounds(250,360,130,40);
    	
    	frame.add(picture);
    	frame.add(userLabel);
    	frame.add(userField);
    	frame.add(passLabel);
    	frame.add(passField);
    	frame.add(typeLabel);
    	frame.add(accType); 
    	frame.add(signup);
    	frame.add(login);
    	
    	frame.getContentPane().setBackground(Color.white);
    	center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        frame.setLocation((int)center.getX()-WIDTH/2,(int)center.getY()-HEIGHT/2);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(WIDTH,HEIGHT);
    	frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    	
    	if(ae.getSource() == signup) {
    		signup();
    	}
    	
    	if(ae.getSource() == login){
    		try {
    		   login();
    		}catch(Exception e) {
    			
    		}
    	}
    }
    
    public void signup() {
    	frame.dispose();
    	new SignUp();
    }
    
    public void login() throws Exception {
    	String username = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
		String type = (String)accType.getSelectedItem();
		String query = "";
		
		if(type.equals("Faculty")) {
			query = "Select * from Faculty where username='"+userField.getText()+"' and password='"+passField.getText()+"'";
		}else if(type.equals("Librarian")){
			query = "Select * from Librarian where username='"+userField.getText()+"' and password='"+passField.getText()+"'";
		}
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		ResultSet set = stmt.executeQuery(query);
		
		if(set.next()) {
			if(type.equals("Faculty")) {
				frame.dispose();
				new Faculty(set.getString("empid"));
			}else if(type.equals("Librarian")){
				frame.dispose();
				new LibraryManagment();
			}
		}else {
			JOptionPane.showMessageDialog(null,"Invalid Username/password","Warning",JOptionPane.WARNING_MESSAGE);
		}
    }
}
