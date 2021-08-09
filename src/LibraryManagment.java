import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class LibraryManagment extends MouseAdapter implements ActionListener {
     JFrame frame;
     Point center;
     JPanel topBar;
     JButton dash,add,issue,reissue,stat,logout;
     JButton currSelected;
     
     DefaultTableModel currModel1;
     DefaultTableModel currModel2;
     
     JPanel p1,p2,p3,p4,p5,p6;
     
     final int WIDTH = 1000;
     final int HEIGHT = 500;
    
     public LibraryManagment() {
    	 frame = new JFrame();
    	 center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    	 frame.getContentPane().setBackground(Color.white);
    	 frame.setLayout(null);
    	 
    	 topBar = new JPanel();
    	 topBar.setLayout(null);
    	 topBar.setBackground(new Color(39,54,59));
    	 topBar.setForeground(Color.white);
    	 topBar.setBounds(0,0,1000,90);
    	 Border border = BorderFactory.createMatteBorder(0, 0, 4, 0,Color.red);
    	 topBar.setBorder(border);
    	 
    	 dash = new JButton("Dashboard");
    	 add = new JButton("Add");
    	 issue = new JButton("Issue Book");
    	 reissue = new JButton("Re-issue/Return Book");
    	 stat = new JButton("Statics");
    	 logout = new JButton("Logout");
    	 
    	 ImageIcon icon1 = new ImageIcon("images\\home.png");
    	 ImageIcon icon2 = new ImageIcon("images\\add.png");
    	 ImageIcon icon3 = new ImageIcon("images\\issue.png");
    	 ImageIcon icon4 = new ImageIcon("images\\return.png");
    	 ImageIcon icon5 = new ImageIcon("images\\stats.png");
    	 ImageIcon icon6 = new ImageIcon("images\\logout.png");
    	 
    	 dash.setIcon(icon1);
    	 add.setIcon(icon2);
    	 issue.setIcon(icon3);
    	 reissue.setIcon(icon4);
    	 stat.setIcon(icon5);
    	 logout.setIcon(icon6);
    	 
    	 dash.setBounds(50,30,150,40);
    	 add.setBounds(210,30,120,40);
    	 issue.setBounds(340,30,120,40);
    	 reissue.setBounds(470,30,200,40);
    	 stat.setBounds(680,30,100,40);
    	 logout.setBounds(790,30,100,40);
    	 
    	 dash.addMouseListener(this);
    	 add.addMouseListener(this);
    	 issue.addMouseListener(this);
    	 reissue.addMouseListener(this);
    	 stat.addMouseListener(this);
    	 logout.addMouseListener(this);
    	 
    	 dash.addActionListener(this);
    	 add.addActionListener(this);
    	 issue.addActionListener(this);
    	 reissue.addActionListener(this);
    	 stat.addActionListener(this);
    	 logout.addActionListener(this);
    	 
    	 styleMenuBar(dash);
    	 styleMenuBar(add);
    	 styleMenuBar(issue);
    	 styleMenuBar(reissue);
    	 styleMenuBar(stat);
    	 styleMenuBar(logout);
    	 
    	 setSelected(dash);
         
    	 topBar.add(dash);
    	 topBar.add(add);
    	 topBar.add(issue);
    	 topBar.add(reissue);
    	 topBar.add(stat);
    	 topBar.add(logout);
    	 
    	 /////////////////////////////////////////////////
    	 JLayeredPane pane = new JLayeredPane();
    	 
    	 p1 =new JPanel();
    	 p2 =new JPanel();
    	 p3 =new JPanel();
    	 p4 =new JPanel();
    	 p5 =new JPanel();
    	 p6 =new JPanel();
    	 
    	 p1.setBounds(0,0,1000,410);
    	 p2.setBounds(0,0,1000,410);
    	 p3.setBounds(0,0,1000,410);
    	 p4.setBounds(0,0,1000,410);
    	 p5.setBounds(0,0,1000,410);
    	 p6.setBounds(0,0,1000,410);
    	 
    	 p1.setLayout(null);
    	 p2.setLayout(null);
    	 p3.setLayout(null);
    	 p4.setLayout(null);
    	 p5.setLayout(null);
    	 p6.setLayout(null);
    	 
    	 pane.add(p1);
    	 pane.add(p2);
    	 pane.add(p3);
    	 pane.add(p4);
    	 pane.add(p5);
    	 pane.add(p6);	 
    	 
    	 pane.setBounds(0,90,1000,410);
    	 frame.add(pane);
    	 setUpPanel1();
    	 /////////////////////////////////////////////////
    	 
    	 frame.add(topBar);
    	 frame.setBounds((int)center.getX()-WIDTH/2,(int)center.getY()-HEIGHT/2,WIDTH,HEIGHT);
    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 frame.setVisible(true);
     }
     
     public void styleMenuBar(JButton btn) {
    	 btn.setBackground(new Color(129,131,132));
    	 btn.setForeground(Color.white);
    	 btn.setBorder(null);
    	 btn.setFocusable(false);
     }
     
     public void setSelected(JButton btn) {
    	 Border border = BorderFactory.createMatteBorder(0, 0,3, 0,Color.red);
    	 btn.setBorder(border);
    	 currSelected = btn;
     }
     
     public void removeSelected(JButton btn) {
    	 btn.setBorder(null);
     }
     
     public void mouseEntered(MouseEvent me) {
    	 JButton btn = (JButton) me.getSource();
    	 Border border = BorderFactory.createMatteBorder(0, 0,3, 0,Color.red);
    	 btn.setBorder(border);
     }
     
     public void mouseExited(MouseEvent me) {
    	 JButton btn = (JButton) me.getSource();
    	 
    	 if(btn != currSelected) {
    		 btn.setBorder(null);
    	 }
     }
     
     public void actionPerformed(ActionEvent e) {
 		 JButton btn = (JButton)e.getSource();
 		 removeSelected(currSelected);
 		 setSelected(btn);
 		 changePanel(btn);
 	 }
     
     public void changePanel(JButton btn) {
    	 String str = btn.getActionCommand();
    	 
    	 switch(str) {
    	    case "Dashboard":
    	       setVisible(1);
    	       setUpPanel1();
    		   break;
    	    case "Add":
    	       setVisible(2);
    	       setUpPanel2();
     		   break;
    	    case "Issue Book":
    	       setVisible(3);
    	       setUpPanel3();
     		   break;
    	    case "Re-issue/Return Book":
    	       setVisible(4);
    	       setUpPanel4();
     		   break;
    	    case "Statics":
    	       setVisible(5);
    	       setUpPanel5();
     		   break;
    	    case "Logout":
    	       int i = JOptionPane.showConfirmDialog(null, "Are you sure ?");
    	       if(i == 0) {
    	    	   System.exit(0);
    	       }else if(i == 1 || i== 2) {
    	    	   setSelected(dash);
    	       }     
     		   break;
    	 }
     }
     
     public void setVisible(int i) {
    	 p1.setVisible(false);
    	 p2.setVisible(false);
    	 p3.setVisible(false);
    	 p4.setVisible(false);
    	 p5.setVisible(false);
    	 p6.setVisible(false);
    	 
    	 switch(i) {
    	   case 1:
    		   p1.setVisible(true);
    		   break;
    	   case 2:
    		   p2.setVisible(true);
    		   break;
    	   case 3:
    		   p3.setVisible(true);
    		   break;
    	   case 4:
    		   p4.setVisible(true);
    		   break;
    	   case 5:
    		   p5.setVisible(true);
    		   break;
    	   case 6:
    		   p6.setVisible(true);
    		   break;
    	 }
     }
     
     public void setUpPanel2() {
    	 p2.setLayout(null);
    	 p2.setBackground(new Color(39,54,59));
    	 
    	 JPanel book = new JPanel();
    	 JPanel table = new JPanel();
    	 
    	 book.setBackground(Color.white);
    	 table.setBackground(Color.white);
    	 //////////////////////////////////
    	 
    	 book.setLayout(null);
    	 table.setLayout(null);
    	 
    	 JButton add = new JButton("Add");
    	 JButton update = new JButton("Update");
    	 JButton search = new JButton("Search");
    	 
    	 JLabel id = new JLabel("Book Id");
    	 JLabel name = new JLabel("Name");
    	 JLabel pub = new JLabel("Publisher");
    	 JLabel edition = new JLabel("Edition");
    	 JLabel price = new JLabel("Price");
    	 
    	 JTextField idTF = new JTextField();
    	 JTextField nameTF = new JTextField();
    	 JTextField pubTF = new JTextField();
    	 JTextField editionTF = new JTextField();
    	 JTextField priceTF = new JTextField();
    	 
    	 id.setFont(new Font("Consolas",Font.BOLD,20));
    	 name.setFont(new Font("Consolas",Font.BOLD,20));
    	 pub.setFont(new Font("Consolas",Font.BOLD,20));
    	 edition.setFont(new Font("Consolas",Font.BOLD,20));
    	 price.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 idTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 nameTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 pubTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 editionTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 priceTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 add.setBackground(new Color(39,54,59));
    	 update.setBackground(new Color(39,54,59));
    	 search.setBackground(new Color(39,54,59));
    	 add.setForeground(Color.white);
    	 update.setForeground(Color.white);
    	 search.setForeground(Color.white);
  
    	 idTF.setBackground(new Color(246,246,246));
    	 nameTF.setBackground(new Color(246,246,246));
    	 pubTF.setBackground(new Color(246,246,246));
    	 editionTF.setBackground(new Color(246,246,246));
    	 priceTF.setBackground(new Color(246,246,246));
    	 
    	 idTF.setBorder(null);
    	 nameTF.setBorder(null);
    	 pubTF.setBorder(null);
    	 editionTF.setBorder(null);
    	 priceTF.setBorder(null);
    	 
    	 id.setBounds(20,30,100,40);
    	 name.setBounds(20,80,100,40);
    	 pub.setBounds(20,130,150,40);
    	 edition.setBounds(20,180,150,40);
    	 price.setBounds(20,230,150,40);
    	 
    	 idTF.setBounds(200,30,170,40);
    	 nameTF.setBounds(200,80,170,40);
    	 pubTF.setBounds(200,130,170,40);
    	 editionTF.setBounds(200,180,170,40);
    	 priceTF.setBounds(200,230,170,40);
    	 
    	 add.setBounds(40,280,100,40);
    	 update.setBounds(150,280,100,40);
    	 search.setBounds(260,280,100,40);
    	 
    	 JTable dataTable = new JTable(){
    	    	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
    	             Component comp = super.prepareRenderer(renderer, row, column);
    	             Color alternateColor = Color.white;
    	             Color whiteColor = Color.WHITE;
    	             if(!comp.getBackground().equals(getSelectionBackground())) {
    	                 Color c = (row % 2 == 0 ? whiteColor : alternateColor);
    	                 comp.setBackground(c);
    	                 c = null;
    	             }
    	            return comp;
    	          }
    	 };
    	 
    	 JTableHeader header = dataTable.getTableHeader();
    	 header.setBackground(new Color(129,131,132));
    	 header.setForeground(Color.white);
    	 dataTable.setFont(new Font("Consolas",Font.BOLD,13));
    	 dataTable.setSelectionBackground(new Color(129,131,132));
    	 
    	 JScrollPane pane = new JScrollPane(dataTable); 
    	 DefaultTableModel model = new DefaultTableModel();
    	 String headers[] = {"Book Id","Name","Publisher","Edition","Price"};
    	 model.setColumnIdentifiers(headers);
    	 dataTable.setModel(model);
    	 
    	 pane.setBounds(0,0,500,330);
    	 table.add(pane);
    	 
    	 setUpTable(model);
    	 
    	 add.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent ae) {
    			  String username = "root";
    	 		  String password = "root";
    	 		  String url = "jdbc:mysql://localhost:3306/project_college";
    	 		  String sql = "Insert into books values(?,?,?,?,?)";
    	 	      
    	 		  try {
    	 		    Class.forName("com.mysql.cj.jdbc.Driver");
    	 		    Connection conn = DriverManager.getConnection(url, username, password);
    	 		    String tempId = idTF.getText();
    	 		    String tempName = nameTF.getText();
    	 		    String tempPub = pubTF.getText();
    	 		    String tempEd = editionTF.getText();
    	 		    String tempC = priceTF.getText();
    	 		    String tempArr[] = {tempId,tempName,tempPub,tempEd,tempC};
    	 		    
    	 		    PreparedStatement ps = conn.prepareStatement(sql);
    	 		    ps.setString(1,tempId);
    	 		    ps.setString(2,tempName);
    	 		    ps.setString(3,tempPub);
    	 		    ps.setString(4,tempEd);
    	 		    ps.setString(5,tempC);
    	 		    
    	 		    int i = ps.executeUpdate();
    	 		    
    	 		    if(i > 0) {
    	 		    	JOptionPane.showMessageDialog(null, "Book Added Succesfully");
    	 		    	model.addRow(tempArr);
    	 		    	model.fireTableDataChanged();
    	 		    }else {
    	 		    	JOptionPane.showMessageDialog(null, "Book Already Exists","Warning",JOptionPane.WARNING_MESSAGE);
    	 		    }
    	 		    
    	 		    ps.close();
    	 		    conn.close();
    	 		  }catch(Exception e) {
    	 			 JOptionPane.showMessageDialog(null, "Book Already Exists","Warning",JOptionPane.WARNING_MESSAGE);
    	 		  }
    		 }
    	 });
    	 
    	 update.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent ae) {
    			  String username = "root";
    	 		  String password = "root";
    	 		  String url = "jdbc:mysql://localhost:3306/project_college";
    	 	      
    	 		  try {
    	 		    Class.forName("com.mysql.cj.jdbc.Driver");
    	 		    Connection conn = DriverManager.getConnection(url, username, password);
    	 		    
    	 		    String book_id = idTF.getText();
    	 		    String tf1 = nameTF.getText();
    	 		    String tf2 = pubTF.getText();
    	 		    String tf3 = editionTF.getText();
    	 		    String tf4 = priceTF.getText();
    	 		    
    	 		    String sql = "Update books set name='"+tf1+"',publisher='"+tf2+"',edition='"+tf3+"',price='"+tf4+"' where book_id='"+book_id+"'";
    	 		    String tempArr[] = {book_id,tf1,tf2,tf3,tf4};
    	 		    
    	 		    Statement stmt = conn.createStatement();
    	 		    int rows = stmt.executeUpdate(sql);
    	 		 
    	 		    if(rows > 0) {
    	 		    	for(int i=0;i<model.getRowCount();i++) {
    	 		    		if(book_id.equals(model.getValueAt(i, 0))) {
    	 		    			model.removeRow(i);
    	 		    		}
    	 		    	}
    	 		        model.addRow(tempArr);
    	 		        model.fireTableDataChanged();
    	 		        JOptionPane.showMessageDialog(null,"Data Updated Succesfully");
    	 		    }else {
    	 		    	JOptionPane.showMessageDialog(null, "Book Doesn't Exists","",JOptionPane.WARNING_MESSAGE);
    	 		    }
    	 		  }catch(Exception e) {
    	 			 e.printStackTrace();
    	 		  }
    		 }
    	 });
    	 
    	 search.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent ae) {
    			 String tempId = idTF.getText();
    			 String tempName,tempPub,tempEd,tempC;
    			 boolean found = false;
    			 
    			 if(tempId.length() > 0) {
    				for(int j=0;j<model.getRowCount();j++) {
    					if(tempId.equals(model.getValueAt(j, 0))) {
    						found = true;
    						tempName = (String) model.getValueAt(j, 1);
    						tempPub = (String) model.getValueAt(j, 2);
    						tempEd = (String) model.getValueAt(j, 3);
    						tempC = (String) model.getValueAt(j, 4);
    						
    						nameTF.setText(tempName);
    						pubTF.setText(tempPub);
    						editionTF.setText(tempEd);
    						priceTF.setText(tempC);
    						
    						break;
    					}
    				}
    				if(!found) {
    					JOptionPane.showMessageDialog(null, "Invalid Book Id","",JOptionPane.WARNING_MESSAGE);
    				}
    			 }else {
    				 JOptionPane.showMessageDialog(null, "Please Enter Book Id","",JOptionPane.WARNING_MESSAGE);
    			 }
    		 }
    	 });
    	 
    	 book.add(id);
    	 book.add(name);
    	 book.add(edition);
    	 book.add(price);
    	 book.add(pub);
    	 book.add(idTF);
    	 book.add(nameTF);
    	 book.add(editionTF);
    	 book.add(priceTF);
    	 book.add(pubTF);
    	 book.add(add);
    	 book.add(update);  
    	 book.add(search);
    	 
    	 //////////////////////////////////
    	 
    	 book.setBounds(30,20,400,330);
    	 table.setBounds(450,20,500,330);
    	 
    	 p2.add(book);
    	 p2.add(table);
     }
     
     public void setUpTable(DefaultTableModel model) {
    	  String username = "root";
		  String password = "root";
		  String url = "jdbc:mysql://localhost:3306/project_college";
          String sql = "Select * from books";
          
		  try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection conn = DriverManager.getConnection(url, username, password);
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    String data[] = new String[5];
		    
		    while(rs.next()) {
		    	data[0] = rs.getString(1);
		    	data[1] = rs.getString(2);
		    	data[2] = rs.getString(3);
		    	data[3] = rs.getString(4);
		    	data[4] = rs.getString(5);
		    	model.addRow(data);
		    }
		  }catch(Exception e) {
			e.printStackTrace();
		  }
     }
     
     public void setUpPanel3() {
    	 JPanel panel = new JPanel();
    	 panel.setLayout(null);
    	 panel.setBackground(Color.white);
    	 panel.setBounds(30,20,930,330);
    	 p3.setBackground(new Color(39,54,59));
    	 
    	 JLabel id = new JLabel("Book Id");
    	 JLabel name = new JLabel("Name");
    	 JLabel pub = new JLabel("Publisher");
    	 JLabel edition = new JLabel("Edition");
    	 JLabel doi = new JLabel("Date of Issue");
    	 
    	 JLabel stdId =new JLabel("Studnet Id");
    	 JLabel stdName =new JLabel("Name");
    	 JLabel stdDept =new JLabel("Department");
    	 JLabel stdYear =new JLabel("Year");
    	 JLabel stdSec =new JLabel("Section");
    	                             
    	 JTextField idTF = new JTextField();
    	 JTextField nameTF = new JTextField();
    	 JTextField pubTF = new JTextField();
    	 JTextField editionTF = new JTextField();
    	 JTextField doiTF = new JTextField();
    	 
    	 JTextField stdIdTF = new JTextField();
    	 JTextField stdNameTF = new JTextField();
    	 JTextField stdDeptTF = new JTextField();
    	 JTextField stdYearTF = new JTextField();
    	 JTextField stdSecTF = new JTextField();
    	 
    	 ImageIcon icon = new ImageIcon("Images\\search.png");
    	 
    	 JButton search1 = new JButton();
    	 search1.setBackground(new Color(39,54,59));
    	 search1.setIcon(icon);
    	 
    	 JButton search2 = new JButton();
    	 search2.setBackground(new Color(39,54,59));
    	 search2.setIcon(icon);
    	 
    	 JButton issue = new JButton("Issue Book");
    	 issue.setBackground(new Color(39,54,59));
    	 issue.setForeground(Color.white);

    	 
    	 id.setBounds(20,30,100,40);
    	 name.setBounds(20,80,100,40);
    	 pub.setBounds(20,130,150,40);
    	 edition.setBounds(20,180,150,40);
    	 doi.setBounds(20,230,150,40);
    	 
    	 idTF.setBounds(200,30,170,40);
    	 nameTF.setBounds(200,80,210,40);
    	 pubTF.setBounds(200,130,210,40);
    	 editionTF.setBounds(200,180,210,40);
    	 doiTF.setBounds(200,230,210,40);
    	 
    	 stdId.setBounds(440,30,150,40);
    	 stdName.setBounds(440,80,100,40);
    	 stdDept.setBounds(440,130,150,40);
    	 stdYear.setBounds(440,180,150,40);
    	 stdSec.setBounds(440,230,150,40);
    	 
    	 stdIdTF.setBounds(620,30,170,40);
    	 stdNameTF.setBounds(620,80,210,40);
    	 stdDeptTF.setBounds(620,130,210,40);
    	 stdYearTF.setBounds(620,180,210,40);
    	 stdSecTF.setBounds(620,230,210,40);
    	 
    	 search1.setBounds(370,30,40,40);
    	 search2.setBounds(790,30,40,40);
    	 issue.setBounds(620,280,210,40);
    	 
    	 id.setFont(new Font("Consolas",Font.BOLD,20));
    	 name.setFont(new Font("Consolas",Font.BOLD,20));
    	 pub.setFont(new Font("Consolas",Font.BOLD,20));
    	 edition.setFont(new Font("Consolas",Font.BOLD,20));
    	 doi.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 stdId.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdName.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdDept.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdYear.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdSec.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 idTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 nameTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 pubTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 editionTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 doiTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 stdIdTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdNameTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdDeptTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdYearTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdSecTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 idTF.setBackground(new Color(246,246,246));
    	 nameTF.setBackground(new Color(246,246,246));
    	 pubTF.setBackground(new Color(246,246,246));
    	 editionTF.setBackground(new Color(246,246,246));
    	 doiTF.setBackground(new Color(246,246,246));
    	 
    	 stdIdTF.setBackground(new Color(246,246,246));
    	 stdNameTF.setBackground(new Color(246,246,246));
    	 stdDeptTF.setBackground(new Color(246,246,246));
    	 stdYearTF.setBackground(new Color(246,246,246));
    	 stdSecTF.setBackground(new Color(246,246,246));
    	 
    	 idTF.setBorder(null);
    	 nameTF.setBorder(null);
    	 pubTF.setBorder(null);
    	 editionTF.setBorder(null);
    	 doiTF.setBorder(null);
    	 
    	 stdIdTF.setBorder(null);
    	 stdNameTF.setBorder(null);
    	 stdDeptTF.setBorder(null);
    	 stdYearTF.setBorder(null);
    	 stdSecTF.setBorder(null);
    	 
    	 panel.add(id);
    	 panel.add(name);
    	 panel.add(pub);
    	 panel.add(edition);
    	 panel.add(doi);
    	 panel.add(idTF);
    	 panel.add(nameTF);
    	 panel.add(pubTF);
    	 panel.add(editionTF);
    	 panel.add(doiTF);
    	 
    	 panel.add(stdId);
    	 panel.add(stdName);
    	 panel.add(stdDept);
    	 panel.add(stdYear);
    	 panel.add(stdSec);
    	 panel.add(stdIdTF);
    	 panel.add(stdNameTF);
    	 panel.add(stdDeptTF);
    	 panel.add(stdYearTF);
    	 panel.add(stdSecTF);
    	 
    	 panel.add(search1);
    	 panel.add(search2);
    	 panel.add(issue);
    	 
    	 search1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String book_id = idTF.getText();
				
				String username = "root";
				String password = "root";
				String url = "jdbc:mysql://localhost:3306/project_college";
		        String sql = "Select * from books where book_id='"+book_id+"'";
		          
				try {
				  Class.forName("com.mysql.cj.jdbc.Driver");
				  Connection conn = DriverManager.getConnection(url, username, password);
				  Statement stmt = conn.createStatement();
				  ResultSet rs = stmt.executeQuery(sql);
				  
				  if(rs.next()) {
					 String tempName = rs.getString(2);
					 String tempPub = rs.getString(3);
					 String tempEd = rs.getString(4);
					 Calendar cal = Calendar.getInstance();
					 String date = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
					 
					 nameTF.setText(tempName);
					 pubTF.setText(tempPub);
					 editionTF.setText(tempEd);
					 doiTF.setText(date);
				  }
				  else {
					 JOptionPane.showMessageDialog(null, "Book Doesn't Exists","Warning",JOptionPane.WARNING_MESSAGE);
				  }
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
    	 });
    	 
    	 search2.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				String std_id = stdIdTF.getText();
 				String username = "root";
				String password = "root";
				String url = "jdbc:mysql://localhost:3306/project_college";
		        String sql = "Select * from student where std_id='"+std_id+"'";
		          
				try {
				  Class.forName("com.mysql.cj.jdbc.Driver");
				  Connection conn = DriverManager.getConnection(url, username, password);
				  Statement stmt = conn.createStatement();
				  ResultSet rs = stmt.executeQuery(sql);
				  
				  if(rs.next()) {
					 String tempName = rs.getString(2);
					 String tempDept = rs.getString(5);
					 String tempYear = rs.getString(6);
					 String tempSec = rs.getString(7);
					 
					 stdNameTF.setText(tempName);
					 stdDeptTF.setText(tempDept);
					 stdYearTF.setText(tempYear);
					 stdSecTF.setText(tempSec);
				  }
				  else {
					 JOptionPane.showMessageDialog(null, "Student Doesn't Exists","Warning",JOptionPane.WARNING_MESSAGE);
				  }
				}catch(Exception e2) {
					e2.printStackTrace();
				}
 			}
     	 });
    	 
    	 issue.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent ae) {
    			 String bookName = nameTF.getText();
    			 String stdName = nameTF.getText();
    			 String username = "root";
 				 String password = "root";
 				 String url = "jdbc:mysql://localhost:3306/project_college";
 		         String sql = "Insert into issuedBooks values(?,?,?,?)";
    			 
 		         try {
    			   if(bookName.length() > 0 && stdName.length() >0) {
    				  Class.forName("com.mysql.cj.jdbc.Driver");
    				  Connection conn = DriverManager.getConnection(url, username, password);
    				  PreparedStatement ps = conn.prepareStatement(sql);
    				 
    				  String tempBookId = idTF.getText();
    				  String tempStdId = stdIdTF.getText();
    				  String date1 = doiTF.getText();
    				  String date2 = addDate(date1);
    				  
    				  ps.setString(1, tempBookId);
    				  ps.setString(2, tempStdId);
    				  ps.setString(3, date1);
    				  ps.setString(4, date2);
    				  
    				  int i = ps.executeUpdate();
    				  
    			      if(i > 0) {
    			    	  JOptionPane.showMessageDialog(null, "Book Issued to "+tempStdId);
    			    	  idTF.setText("");
    			    	  nameTF.setText("");
    			    	  pubTF.setText("");
    			    	  editionTF.setText("");
    			    	  doiTF.setText("");
    			    	  
    			    	  stdIdTF.setText("");
    			    	  stdNameTF.setText("");
    			    	  stdDeptTF.setText("");
    			    	  stdYearTF.setText("");
    			    	  stdSecTF.setText("");
    			      }
    			  }else {
    			   	 JOptionPane.showMessageDialog(null,"Please Set Book/Student Details","Warning",JOptionPane.WARNING_MESSAGE);
    			  }
 		        }catch(Exception e) {
 		        	
 		        }
    		 }
    	 });
    	 
    	 p3.add(panel);
     }
     
     public void setUpPanel4() {
    	 JPanel panel = new JPanel();
    	 panel.setLayout(null);
    	 panel.setBackground(Color.white);
    	 panel.setBounds(30,20,930,330);
    	 p4.setBackground(new Color(39,54,59));
    	 
         ImageIcon icon = new ImageIcon("Images\\search.png");
    	 
    	 JButton search = new JButton();
    	 search.setBackground(new Color(39,54,59));
    	 search.setIcon(icon);
    	 
    	 JButton reissueBtn = new JButton("Re-issue");
    	 JButton returnBtn = new JButton("Return");
    	 
    	 JLabel id = new JLabel("Book Id");
    	 JLabel name = new JLabel("Title");
    	 JLabel doi = new JLabel("Issue Date");
    	 JLabel due = new JLabel("Due Date");
    	 JLabel today = new JLabel("Today's date");
    	 
    	 JLabel stdId = new JLabel("Student Id");
    	 JLabel stdName = new JLabel("Name");
    	 JLabel sec = new JLabel("Section");
    	 JLabel nod = new JLabel("No of Days");
    	 JLabel late = new JLabel("Pending Fine");
    	 
    	 JTextField idTF = new JTextField();
    	 JTextField nameTF = new JTextField();
    	 JTextField doiTF = new JTextField();
    	 JTextField dueTF = new JTextField();
    	 JTextField todayTF = new JTextField();
    	 
    	 JTextField stdIdTF = new JTextField();
    	 JTextField stdNameTF = new JTextField();
    	 JTextField secTF = new JTextField();
    	 JTextField nodTF = new JTextField();
    	 JTextField lateTF = new JTextField();
    	 
    	 id.setBounds(20,30,100,40);
    	 name.setBounds(20,80,100,40);
    	 doi.setBounds(20,130,150,40);
    	 due.setBounds(20,180,150,40);
    	 today.setBounds(20,230,150,40);
    	 
    	 idTF.setBounds(200,30,170,40);
    	 nameTF.setBounds(200,80,210,40);
    	 doiTF.setBounds(200,130,210,40);
    	 dueTF.setBounds(200,180,210,40);
    	 todayTF.setBounds(200,230,210,40);
    	 
    	 stdId.setBounds(440,30,150,40);
    	 stdName.setBounds(440,80,100,40);
    	 sec.setBounds(440,130,150,40);
    	 nod.setBounds(440,180,150,40);
    	 late.setBounds(440,230,150,40);
    	 
    	 search.setBounds(370,30,40,40);
    	 
    	 stdIdTF.setBounds(620,30,210,40);
    	 stdNameTF.setBounds(620,80,210,40);
    	 secTF.setBounds(620,130,210,40);
    	 nodTF.setBounds(620,180,210,40);
    	 lateTF.setBounds(620,230,210,40);
    	 
    	 reissueBtn.setBounds(275,280,150,40);
    	 returnBtn.setBounds(440,280,150,40);
    	 
    	 id.setFont(new Font("Consolas",Font.BOLD,20));
    	 name.setFont(new Font("Consolas",Font.BOLD,20));
    	 doi.setFont(new Font("Consolas",Font.BOLD,20));
    	 due.setFont(new Font("Consolas",Font.BOLD,20));
    	 today.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 idTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 nameTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 doiTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 dueTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 todayTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 stdId.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdName.setFont(new Font("Consolas",Font.BOLD,20));
    	 sec.setFont(new Font("Consolas",Font.BOLD,20));
    	 nod.setFont(new Font("Consolas",Font.BOLD,20));
    	 late.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 stdIdTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 stdNameTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 secTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 nodTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 lateTF.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 reissueBtn.setFont(new Font("Consolas",Font.BOLD,20));
    	 returnBtn.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 idTF.setBackground(new Color(246,246,246));
    	 nameTF.setBackground(new Color(246,246,246));
    	 doiTF.setBackground(new Color(246,246,246));
    	 dueTF.setBackground(new Color(246,246,246));
    	 todayTF.setBackground(new Color(246,246,246));
    	 
    	 stdIdTF.setBackground(new Color(246,246,246));
    	 stdNameTF.setBackground(new Color(246,246,246));
    	 secTF.setBackground(new Color(246,246,246));
    	 nodTF.setBackground(new Color(246,246,246));
    	 lateTF.setBackground(new Color(246,246,246));
    	 
    	 reissueBtn.setBackground(new Color(39,54,59));
    	 returnBtn.setBackground(new Color(39,54,59));
    	 reissueBtn.setForeground(Color.white);
    	 returnBtn.setForeground(Color.white);
    	 
    	 idTF.setBorder(null);
    	 nameTF.setBorder(null);
    	 doiTF.setBorder(null);
    	 dueTF.setBorder(null);
    	 todayTF.setBorder(null);
    	 
    	 stdIdTF.setBorder(null);
    	 stdNameTF.setBorder(null);
    	 secTF.setBorder(null);
    	 nodTF.setBorder(null);
    	 lateTF.setBorder(null);
    	 
    	 ///////////////////////////////////////////////////////
    	 
    	 search.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent ae) {
    			 String book_id = idTF.getText();
    			 
    			 String temp1 = getBookData(book_id);
    			 
    			 if(temp1.length() > 0) {
    				 String arr1[] = temp1.split(",");
        			 String temp2 = getStudentData(arr1[3]);
        			 String arr2[] = temp2.split(",");
        			
        			 Calendar cal = Calendar.getInstance();
        			 String date = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
        			 
    				 nameTF.setText(arr1[0]);
        			 doiTF.setText(arr1[1]);
        			 dueTF.setText(arr1[2]);
        			 todayTF.setText(date);
        			 stdIdTF.setText(arr1[3]);
        			 stdNameTF.setText(arr2[0]);
        			 secTF.setText(arr2[1]+"-"+arr2[2]);
                     nodTF.setText(getNOD(arr1[1]));
                     int no = Integer.parseInt(nodTF.getText());
                     lateTF.setText(""+getFine(no,stdIdTF.getText()));
                     deleteFine(stdIdTF.getText());
    			 }else {
    				 JOptionPane.showMessageDialog(null,"Books not issued","Warning",JOptionPane.WARNING_MESSAGE);
    			 }
    		 }
    	 });
    	 
    	 reissueBtn.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent ae) {
    			 if(nameTF.getText().length() > 0) {
    				int late = Integer.parseInt(lateTF.getText());
    				if(late == 0) {
    					reissueBook(idTF.getText(),stdIdTF.getText());
    				}else {
    				   JDialog dialog = new JDialog(frame,"Payment",true);
    				   dialog.getContentPane().setBackground(new Color(39,54,59));
    				   dialog.setLayout(null);
    				   
    				   JPanel tempP = new JPanel();
    				   tempP.setBackground(Color.white);
    				   tempP.setLayout(null);
    				   
    				   JTextField tf1 = new JTextField();
    				   tf1.setBackground(new Color(246,246,246));
    				   tf1.setFont(new Font("Consolas",Font.BOLD,20));
    				   tf1.setBorder(null);
    				   JTextField tf2 = new JTextField();
    				   tf2.setBackground(new Color(246,246,246));
    				   tf2.setFont(new Font("Consolas",Font.BOLD,20));
    				   tf2.setBorder(null);
    				   
    				   JLabel l1 = new JLabel("Total Fine");
    				   l1.setFont(new Font("Consolas",Font.BOLD,20));
    				   JLabel l2 = new JLabel("Payment");
    				   l2.setFont(new Font("Consolas",Font.BOLD,20));
    				   
    				   JButton btn = new JButton("Pay");
    				   btn.setBackground(new Color(39,54,59));
    				   btn.setForeground(Color.white);
    				   btn.setFont(new Font("Consolas",Font.BOLD,20));
    				   
    				   l1.setBounds(10,25,150,40);
    				   l2.setBounds(10,80,100,40);
    				   tf1.setBounds(170,25,150,40);
    				   tf2.setBounds(170,80,150,40);
    				   btn.setBounds(170,130,150,40);
    				   
    				   tf1.setText(late+"");
    				   
    				   btn.addActionListener(new ActionListener() {
    					   public void actionPerformed(ActionEvent ae) {
    						   String str = tf2.getText();
    						   
    						   if(str.length() > 0) {
    	    					   int pay = Integer.parseInt(str);
    	    					   if(pay > late) {
    	    						   JOptionPane.showMessageDialog(null,"Amount greater than fine");
    	    					   }else {
    	    						   if(pay == late) {
    	    							   payFine(pay,stdIdTF.getText());
    	    							   reissueBook(idTF.getText(),stdIdTF.getText());
    	    							   dialog.dispose();
    	    						   }else {
    	    							   if(!checkFine(stdIdTF.getText())) {
     	    								  addFine(stdIdTF.getText(),late+"");
      	    							   }
    	    							   
    	    						      payFine(pay,stdIdTF.getText());
    	    						      reissueBook(idTF.getText(),stdIdTF.getText());
    	    						      dialog.dispose();
    	    						   }
    	    					   }
    	    				   }else {
    	    					   JOptionPane.showMessageDialog(null,"Please Enter a Amount");
    	    				   }
    					   }
    				   });
    				    
    				   tempP.setBounds(25,25,330,190);
    				  
    				   tempP.add(l1);
    				   tempP.add(l2);
    				   tempP.add(tf1);
    				   tempP.add(tf2);
    				   tempP.add(btn);
    				   dialog.add(tempP);
    				   dialog.setBounds(425,220,400,270);
    				   dialog.setVisible(true);
    				}
    			 }else {
    				 JOptionPane.showMessageDialog(null,"Please Enter the Data first","Warning",JOptionPane.WARNING_MESSAGE);
    			 }
    			 
    			 resetFields(nameTF,doiTF,dueTF,todayTF,stdIdTF,stdNameTF,secTF,nodTF,lateTF);
    		 }
    	 });
    	 
    	 returnBtn.addActionListener(new ActionListener() {
    		 public void actionPerformed(ActionEvent ae) {
    			 if(nameTF.getText().length() > 0) {
    				int late = Integer.parseInt(lateTF.getText());
    				if(late == 0) {
    				   int i = returnBook(idTF.getText());
    				   if(i > 0) {
    					   JOptionPane.showMessageDialog(null,"Book retured successfully");
    				   }
    				}else {
    				   JDialog dialog = new JDialog(frame,"Payment",true);
    				   dialog.getContentPane().setBackground(new Color(39,54,59));
    				   dialog.setLayout(null);
    				   
    				   JPanel tempP = new JPanel();
    				   tempP.setBackground(Color.white);
    				   tempP.setLayout(null);
    				   
    				   JTextField tf1 = new JTextField();
    				   tf1.setBackground(new Color(246,246,246));
    				   tf1.setFont(new Font("Consolas",Font.BOLD,20));
    				   tf1.setBorder(null);
    				   JTextField tf2 = new JTextField();
    				   tf2.setBackground(new Color(246,246,246));
    				   tf2.setFont(new Font("Consolas",Font.BOLD,20));
    				   tf2.setBorder(null);
    				   
    				   JLabel l1 = new JLabel("Total Fine");
    				   l1.setFont(new Font("Consolas",Font.BOLD,20));
    				   JLabel l2 = new JLabel("Payment");
    				   l2.setFont(new Font("Consolas",Font.BOLD,20));
    				   
    				   JButton btn = new JButton("Pay");
    				   btn.setBackground(new Color(39,54,59));
    				   btn.setForeground(Color.white);
    				   btn.setFont(new Font("Consolas",Font.BOLD,20));
    				   
    				   l1.setBounds(10,25,150,40);
    				   l2.setBounds(10,80,100,40);
    				   tf1.setBounds(170,25,150,40);
    				   tf2.setBounds(170,80,150,40);
    				   btn.setBounds(170,130,150,40);
    				   
    				   tf1.setText(late+"");
    				   
    				   btn.addActionListener(new ActionListener() {
    					   public void actionPerformed(ActionEvent ae) {
    						   String str = tf2.getText();
    						   
    						   if(str.length() > 0) {
    	    					   int pay = Integer.parseInt(str);
    	    					   if(pay > late) {
    	    						   JOptionPane.showMessageDialog(null,"Amount greater than fine");
    	    					   }else {
    	    						   if(pay == late) {
    	    							  int i = returnBook(idTF.getText());
     	    	    				      if(i > 0) {
     	    	    					     JOptionPane.showMessageDialog(null,"Book retured successfully");
     	    	    					     payFine(pay,stdIdTF.getText());
     	    	    					     dialog.dispose();
     	    	    				      }
    	    						   }else {
    	    							  if(!checkFine(stdIdTF.getText())) {
    	    								  addFine(stdIdTF.getText(),late+"");
     	    							  }
    	    							  
    	    						      payFine(pay,stdIdTF.getText());
    	    						      int i = returnBook(idTF.getText());
    	    	    				      if(i > 0) {
    	    	    					     JOptionPane.showMessageDialog(null,"Book retured successfully");
    	    	    					     dialog.dispose();
    	    	    				      }
    	    						   }
    	    					   }
    	    				   }else {
    	    					   JOptionPane.showMessageDialog(null,"Please Enter a Amount");
    	    				   }
    					   }
    				   });
    				    
    				   tempP.setBounds(25,25,330,190);
    				  
    				   tempP.add(l1);
    				   tempP.add(l2);
    				   tempP.add(tf1);
    				   tempP.add(tf2);
    				   tempP.add(btn);
    				   dialog.add(tempP);
    				   dialog.setBounds(425,220,400,270);
    				   dialog.setVisible(true);
    				}
    			 }else {
    				 JOptionPane.showMessageDialog(null,"Please Enter the Data first","Warning",JOptionPane.WARNING_MESSAGE);
    			 }
    			 resetFields(nameTF,doiTF,dueTF,todayTF,stdIdTF,stdNameTF,secTF,nodTF,lateTF);
    		 }
    	 });
    	 
    	 //////////////////////////////////////////////////////
    	 
    	 panel.add(id);
    	 panel.add(search);
    	 panel.add(name);
    	 panel.add(doi);
    	 panel.add(due);
    	 panel.add(today);
    	 panel.add(idTF);
    	 panel.add(nameTF);
    	 panel.add(doiTF);
    	 panel.add(dueTF);
    	 panel.add(todayTF);
    	 
    	 panel.add(stdId);
    	 panel.add(stdName);
    	 panel.add(sec);
    	 panel.add(nod);
    	 panel.add(late);
    	 panel.add(stdIdTF);
    	 panel.add(stdNameTF);
    	 panel.add(secTF);
    	 panel.add(nodTF);
    	 panel.add(lateTF);
    	 
    	 panel.add(reissueBtn);
    	 panel.add(returnBtn);
    	 
    	 p4.add(panel);
     } 
     
     public String getNOD(String date) {
    	 String arr[] = date.split("-");
    	 Calendar cal = Calendar.getInstance();
   
    	 int leap[] = {31,29,31,30,31,30,31,31,30,31,30,31};
         int nonLeap[] = {31,28,31,30,31,30,31,31,30,31,30,31};
         
    	 int dd1 = Integer.parseInt(arr[0]);
    	 int mm1 = Integer.parseInt(arr[1]);
    	 int yy1 = Integer.parseInt(arr[2]);
    	 
    	 int dd2 = cal.get(Calendar.DATE);
    	 int mm2 = cal.get(Calendar.MONTH)+1;
    	 int yy2 = cal.get(Calendar.YEAR);
    	 
    	 int yearGap = yy2-yy1;
         
         int count=0;
         
         if(yy2 == yy1 && mm2 == mm1) {
        	 return ""+(dd2-dd1);
         }
         
         if(yearGap == 0){
            for(int i=mm1+1;i<mm2;i++){
               count += leap[i-1];
            }
            count += leap[mm1-1]-dd1;
            count += dd2;
         }
         else{
           for(int i=yy1;i<=yy2;i++){
              if(i == yy1){
                for(int j=mm1;j<=12;j++){
                  if(i % 4== 0){
                    count += leap[j-1];
                  }else{
                    count += nonLeap[j-1];  
                  }
                }
              }else if(i == yy2){
                for(int j=1;j<=mm2;j++){
                  if(i % 4== 0){
                     count += leap[j-1];
                  }else{
                     count += nonLeap[j-1];  
                  }
                }
              }else{
                 for(int j=1;j<=12;j++){
                   if(i % 4== 0){
                     count += leap[j-1];
                   }else{
                     count += nonLeap[j-1];  
                   }
                }
              }
           }
            
           count -= dd1;
           if(yy2 % 4 == 0){
              count -= (leap[mm2]-dd2);
           }else{
              count -= (nonLeap[mm2]-dd2);
           }
            count +=1;
         }
         
         return ""+count;
     }
     
     public String addDate(String date) {
    	 int leap[] = {31,29,31,30,31,30,31,31,30,31,30,31};
         int nonLeap[] = {31,28,31,30,31,30,31,31,30,31,30,31};
         int days =15;
         
         String arr[] = date.split("-");
    	 Calendar cal = Calendar.getInstance();
    	 
         int dd1 = Integer.parseInt(arr[0]);
    	 int mm1 = Integer.parseInt(arr[1]);
    	 int yy1 = Integer.parseInt(arr[2]);
    	 int dd2,mm2,yy2;
        
    	 if(yy1%4 == 0) {
    	    if(dd1+days > leap[mm1-1]) {
    		    int temp = leap[mm1-1]-dd1;
    		    dd2 = days-temp;
    		    mm2 = mm1+1;
    		    yy2 = yy1;
    		    if(mm1 == 12) {
    		    	mm2 = 1;
    		    	yy2 = yy1+1;
    		    }
    	    }else {
    	    	dd2 = dd1+days;
    	    	mm2 = mm1;
    	    	yy2 = yy1;
    	    	if(mm1 == 12) {
    	    		yy2 = yy1;
    	    	}
    	    }
    	 }else {
    		 if(dd1+days > leap[mm1-1]) {
     		    int temp = leap[mm1-1]-dd1;
     		    dd2 = days-temp;
     		    mm2 = mm1+1;
     		    yy2 = yy1;
     		    if(mm1 == 12) {
     		    	yy2 = yy1+1;
     		    }
     	    }else {
     	    	dd2 = dd1+days;
     	    	mm2 = mm1;
     	    	yy2 = yy1;
     	    } 
    	 }
    	 
    	 return dd2+"-"+mm2+"-"+yy2;
     }
     
     public String getBookData(String bookId) {
	     String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "select books.name,issuedBooks.DOI,issuedBooks.DOS,issuedBooks.std_id from books,issuedBooks where issuedBooks.book_id = '"+bookId+"' and books.book_id=issuedBooks.book_id";
		 String str = "";
		 
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(sql);
			
		  if(rs.next()) {
			 str = str+rs.getString(1)+",";
			 str = str+rs.getString(2)+",";
			 str = str+rs.getString(3)+",";
			 str = str+rs.getString(4);
		  }
		  
	     }catch(Exception e2) {
	    	 
	     } 
		return str;
     }
     
     public String getStudentData(String stdId) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "select name,department,year from Student where std_id='"+stdId+"'";
		 String str = "";
		 
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(sql);
			
		  rs.next();
		  str = str+rs.getString(1)+",";
		  str = str+rs.getString(2)+",";
		  str = str+rs.getString(3);
	     }catch(Exception e2) {
			e2.printStackTrace();
	     } 
		return str;
     }
     
     public int getFine(int nod,String id) {
		  int temp,temp2,count,fine;
		  
		  if(nod <= 15){
		      fine = 0;
		  }else{
		    temp = nod-15;
		    count = 1;
		    fine = 0;
		    
		    while(true){
		      if(temp == 0){
		         break;
		      }  
		      if(temp > 15){
		        temp = temp -15;
		        temp2 = 15;
		      }else{
		        temp2 = temp;
		        temp = 0;
		      }
		      fine = fine + count*temp2;
		      count++;
		    }    
		 }
		  
		  String username = "root";
		  String password = "root";
		  String url = "jdbc:mysql://localhost:3306/project_college";
	      String sql = "Select * from fine where std_id='"+id+"'";
		  int i=0;
			 
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				int temp3 = rs.getInt(2);
				fine = fine + temp3;
			}	
		  }catch(Exception e2) {
		    e2.printStackTrace();
		  }
	   return fine;
     }
     
     public int returnBook(String id) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Delete from issuedBooks where book_id ='"+id+"'";
		 int i=0;
		 
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  i = stmt.executeUpdate(sql);
		
	     }catch(Exception e2) {
			e2.printStackTrace();
	     } 
	   return i;
     }
     
     public void payFine(int fine,String id) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Update Fine set amount = amount -"+fine+" where std_id='"+id+"'";
		 
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  stmt.executeUpdate(sql);
		  
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
     }
     
     public void reissueBook(String bookId,String stdId) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 
		 Calendar cal = Calendar.getInstance();
		 String date1 = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		 String date2 = addDate(date1);
		 
		 String sql = "Update IssuedBooks set DOI='"+date1+"' , DOS='"+date2+"' where book_id='"+bookId+"' and std_id='"+stdId+"'";
		 
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  int i = stmt.executeUpdate(sql);
		  
		  if(i > 0) {
			 JOptionPane.showMessageDialog(null, "Book reissued Successfully");
		  }
		  
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
     }
     
     public void addFine(String stdId,String fine) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Insert into fine values('"+stdId+"',"+Integer.parseInt(fine)+")";
		 
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  int i = stmt.executeUpdate(sql);
		  
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
     }
     
     public boolean checkFine(String stdId) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
         String sql = "Select * from Fine where std_id='"+stdId+"'";
         boolean found = false;
        
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  if(rs.next()) {
			 found = true;
		  }
	      }catch(Exception e2) {
			e2.printStackTrace();
	      }
		 return found;
     }
     
     public void deleteFine(String stdId) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
         String sql = "Delete from Fine where amount=0 and std_id='"+stdId+"'";
        
		 try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
          stmt.executeUpdate(sql);		  
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
     }
     
     public void resetFields(JTextField ...fields ) {
    	 for(JTextField field : fields) {
    		 field.setText("");
    	 }
     }
     
     public void setUpPanel5() {
    	 JPanel top1 = new JPanel();
    	 JPanel top2 = new JPanel(); 
    	 
    	 JPanel panel1 = new JPanel();
    	 JPanel panel2 = new JPanel();
    	 
    	 top1.setLayout(null);
    	 top1.setBackground(Color.white);
    	 top1.setBounds(30,10,580,60);
    	 
    	 top2.setLayout(null);
    	 top2.setBackground(Color.white);
    	 top2.setBounds(620,10,350,60);
    	 
    	 panel1.setLayout(null);
    	 panel1.setBackground(Color.white);
    	 panel1.setBounds(30,80,580,270);
    	 
    	 panel2.setLayout(null);
    	 panel2.setBackground(Color.white);
    	 panel2.setBounds(620,80,350,270);
    	 
    	 ////////////////////////////////////////
    	 
         JLabel l = new JLabel("Issued on");
         JTextField tf1 = new JTextField();
         JButton search1 = new JButton("Search");
         
         Font font = new Font("Consolas",Font.BOLD,15);
         
         l.setFont(new Font("Consolas",Font.BOLD,20));
         l.setBounds(100,10,130,40);
         
    	 tf1.setBackground(new Color(246,246,246));
    	 tf1.setBorder(null);
    	 tf1.setBounds(240,10,170,40);
    	 tf1.setFont(font);
    	 
    	 search1.setBackground(new Color(39,54,59));
    	 search1.setForeground(Color.white);
    	 search1.setBounds(420,10,100,40);
    	 search1.setFont(font);
    	 
    	 top1.add(l);
    	 top1.add(tf1);
    	 top1.add(search1);
    	 
    	 /////////////////////////////////////////
         
    	 JTextField tf2 = new JTextField();
    	 JButton search2 = new JButton("Search");
    	
    	 tf2.setBackground(new Color(246,246,246));
    	 tf2.setBorder(null);
    	 tf2.setBounds(30,10,170,40);
    	 tf2.setFont(font);
    	 
    	 search2.setBackground(new Color(39,54,59));
    	 search2.setForeground(Color.white);
    	 search2.setBounds(210,10,100,40);
    	 search2.setFont(font);
    	 
    	 top2.add(tf2);
    	 top2.add(search2);
    	 
    	 ////////////////////////////////////////
    	 
    	 JTable table1 = new JTable(){
 	    	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
 	             Component comp = super.prepareRenderer(renderer, row, column);
 	             Color alternateColor = Color.white;
 	             Color whiteColor = Color.WHITE;
 	             if(!comp.getBackground().equals(getSelectionBackground())) {
 	                 Color c = (row % 2 == 0 ? whiteColor : alternateColor);
 	                 comp.setBackground(c);
 	                 c = null;
 	             }
 	            return comp;
 	          }
 	     };
 	    
 	     DefaultTableModel model1 = new DefaultTableModel();
 	     String headings[] = {"Book Id","Student Id","Date of Issue","Due Date"};
 	     model1.setColumnIdentifiers(headings);
 	     
 	     JTableHeader header = table1.getTableHeader();
 	     header.setBackground(new Color(129,131,132));
 	     header.setForeground(Color.white);
 	     table1.setFont(new Font("Consolas",Font.BOLD,13));
 	     table1.setSelectionBackground(new Color(129,131,132));
    	 
 	     setUpTable1(model1);
 	     currModel1 = model1;
 	     table1.setModel(model1);
 	     
 	     JScrollPane pane = new JScrollPane(table1);
 	     pane.setBounds(0,0,580,270);
    	 
 	     panel1.add(pane);
    	 ////////////////////////////////////////
 	     
 	     JTable table2 = new JTable(){
 	    	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
 	             Component comp = super.prepareRenderer(renderer, row, column);
 	             Color alternateColor = Color.white;
 	             Color whiteColor = Color.WHITE;
 	             if(!comp.getBackground().equals(getSelectionBackground())) {
 	                 Color c = (row % 2 == 0 ? whiteColor : alternateColor);
 	                 comp.setBackground(c);
 	                 c = null;
 	             }
 	            return comp;
 	          }
 	     };
 	    
 	     DefaultTableModel model2 = new DefaultTableModel();
 	     String headings2[] = {"Student id","Fine"};
 	     model2.setColumnIdentifiers(headings2);
 	     
 	     JTableHeader header2 = table2.getTableHeader();
 	     header2.setBackground(new Color(129,131,132));
 	     header2.setForeground(Color.white);
 	     table2.setFont(new Font("Consolas",Font.BOLD,13));
 	     table2.setSelectionBackground(new Color(129,131,132));
 	     
 	     setUpTable2(model2);
 	     currModel2 = model2;
 	     table2.setModel(model2);
 	     
 	     JScrollPane pane2 = new JScrollPane(table2);
	     pane2.setBounds(0,0,350,270);
   	 
	     panel2.add(pane2);
 	  
 	     ////////////////////////////////////////
	     
	     search1.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent ae) {
	    		 DefaultTableModel newModel = new DefaultTableModel();
	    		 String tempHeading[] = {"Book Id","Student Id","Date of Issue","Due Date"};
	     	     newModel.setColumnIdentifiers(tempHeading);
	     	     
	     	     String searchDate = tf1.getText();
	     	     setUpTable1(newModel,searchDate);
	     	     
	     	     table1.setModel(newModel);
	    	 }
	     });
	     
	     tf1.addKeyListener(new KeyAdapter() {
	    	 public void keyReleased(KeyEvent ke) {
	    		 String temp = tf1.getText();
	    		 
	    		 if(temp.length() == 0) {
	    			 table1.setModel(currModel1);
	    		 }
	    	 }
	     });
	     
	     ////////////////////////////////////////
	     
	     search2.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent ae) {
	    		 String txt = tf2.getText();
	    		 
	    		 DefaultTableModel newModel = new DefaultTableModel();
	    		 String tempHeading[] = {"Student Id","Fine"};
	    		 newModel.setColumnIdentifiers(tempHeading);
	    		 
	    		 setUpTable2(newModel,txt);
	    		 
	    		 table2.setModel(newModel);
	    	 }
	     });
	     
	     tf2.addKeyListener(new KeyAdapter() {
	    	 public void keyReleased(KeyEvent ke) {
	    		 String temp = tf2.getText();
	    		 
	    		 if(temp.length() == 0) {
	    			 table2.setModel(currModel2);
	    		 }
	    	 }
	     });
	     
	     ///////////////////////////////////////
    	 
    	 p5.setBackground(new Color(39,54,59)); 
    	 p5.add(top1);
    	 p5.add(top2);
    	 p5.add(panel1);
    	 p5.add(panel2);
     }
     
     public void setUpTable1(DefaultTableModel model) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		
		 Calendar cal = Calendar.getInstance();
		 String date = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
         String sql = "Select * from issuedBooks where DOI='"+date+"'";
         String data[] = new String[4];
         
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
           
           while(rs.next()) {
        	   data[0] = rs.getString(1);
        	   data[1] = rs.getString(2);
        	   data[2] = rs.getString(3);
        	   data[3] = rs.getString(4);
        	   
        	   model.addRow(data);
           } 
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
     }
     
     public void setUpTable1(DefaultTableModel model,String date) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
	
         String sql = "Select * from issuedBooks where DOI='"+date+"'";
         String data[] = new String[4];
         
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
           
           while(rs.next()) {
        	   data[0] = rs.getString(1);
        	   data[1] = rs.getString(2);
        	   data[2] = rs.getString(3);
        	   data[3] = rs.getString(4);
        	   
        	   model.addRow(data);
           } 
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
     }
     
     public void setUpTable2(DefaultTableModel model) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Select * from fine";
		 
		 String data[] = new String[2];
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(sql);
	           
	       while(rs.next()) {
	          data[0] = rs.getString(1);
	          data[1] = rs.getString(2);
	          model.addRow(data);
	       } 
		}catch(Exception e2) {
		   e2.printStackTrace();
		}
     }
     
     public void setUpTable2(DefaultTableModel model,String txt) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Select * from fine where std_id='"+txt+"' or amount="+txt+"";
		 
		 String data[] = new String[2];
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(sql);
	           
	       while(rs.next()) {
	          data[0] = rs.getString(1);
	          data[1] = rs.getString(2);
	          model.addRow(data);
	       } 
		}catch(Exception e2) {
		   e2.printStackTrace();
		}
     }
     
     public void setUpPanel1() {
    	 p1.setBackground(new Color(39,54,59));
    	 p1.setLayout(null);
    	 
    	 JPanel panel1 = new JPanel();
    	 panel1.setLayout(null);
    	 panel1.setBackground(Color.white);
    	 panel1.setBounds(30,20,930,330);
    	 
    	 JPanel sub1 = new JPanel();
    	 JPanel sub2 = new JPanel();
    	 JPanel sub3 = new JPanel();
    	 JPanel sub4 = new JPanel();
    	 
    	 ImageIcon icon = new ImageIcon("images\\logo.png");
    	
    	 JLabel logo = new JLabel();
    	 logo.setIcon(icon);
    	 logo.setBounds(250,20,420,120);
    	 
    	 sub1.setLayout(null);
    	 sub2.setLayout(null);
    	 sub3.setLayout(null);
    	 sub4.setLayout(null);
    	 
    	 ///////////////////////////////////////////////
    	 
    	 sub1.setBackground(new Color(0,193,237)); 
    	 sub2.setBackground(new Color(2,167,91));  
    	 sub3.setBackground(new Color(243,156,17)); 
    	 sub4.setBackground(new Color(223,76,58)); 
    	 
    	 sub1.setBounds(30,195,200,100);
    	 sub2.setBounds(250,195,200,100);
    	 sub3.setBounds(470,195,200,100);
    	 sub4.setBounds(690,195,200,100);
    	 
    	 ImageIcon icons1 = new ImageIcon("images\\books.png");
    	 JLabel iconLabel1 = new JLabel();
    	 iconLabel1.setIcon(icons1);
    	 
    	 ImageIcon icons2 = new ImageIcon("images\\issued.png");
    	 JLabel iconLabel2 = new JLabel();
    	 iconLabel2.setIcon(icons2);
    	 
    	 ImageIcon icons3 = new ImageIcon("images\\student.png");
    	 JLabel iconLabel3 = new JLabel();
    	 iconLabel3.setIcon(icons3);
    	 
    	 ImageIcon icons4 = new ImageIcon("images\\fine.png");
    	 JLabel iconLabel4 = new JLabel();
    	 iconLabel4.setIcon(icons4);
    	 
    	 iconLabel1.setBounds(130,15,64,64);
    	 iconLabel2.setBounds(130,15,64,64);
    	 iconLabel3.setBounds(130,15,64,64);
    	 iconLabel4.setBounds(130,15,64,64);
    	 
    	 JLabel sub1L1 = new JLabel("Total Books");
    	 JLabel sub2L1 = new JLabel("Total Issued");
    	 JLabel sub3L1 = new JLabel("Total Members");
    	 JLabel sub4L1 = new JLabel("Pending Fine");
    	 
    	 sub1L1.setFont(new Font("Consolas",Font.BOLD,17));
    	 sub2L1.setFont(new Font("Consolas",Font.BOLD,17));
    	 sub3L1.setFont(new Font("Consolas",Font.BOLD,17));
    	 sub4L1.setFont(new Font("Consolas",Font.BOLD,17));
    	 
    	 sub1L1.setForeground(Color.white);
    	 sub2L1.setForeground(Color.white);
    	 sub3L1.setForeground(Color.white);
    	 sub4L1.setForeground(Color.white);
    	 
    	 sub1L1.setBounds(10,70,150,40);
    	 sub2L1.setBounds(10,70,150,40);
    	 sub3L1.setBounds(10,70,150,40);
    	 sub4L1.setBounds(10,70,150,40);
    	 
    	 JTextField sub1TF = new JTextField();
    	 JTextField sub2TF = new JTextField();
    	 JTextField sub3TF = new JTextField();
    	 JTextField sub4TF = new JTextField();
    	 
    	 sub1TF.setFont(new Font("Consolas",Font.BOLD,20));
    	 sub2TF.setFont(new Font("Consolas",Font.BOLD,20));
    	 sub3TF.setFont(new Font("Consolas",Font.BOLD,20));
    	 sub4TF.setFont(new Font("Consolas",Font.BOLD,20));
    	 
    	 sub1TF.setBackground(new Color(0,193,237)); 
    	 sub2TF.setBackground(new Color(2,167,91));
    	 sub3TF.setBackground(new Color(243,156,17));
    	 sub4TF.setBackground(new Color(223,76,58));
    	 
    	 sub1TF.setForeground(Color.white);
    	 sub2TF.setForeground(Color.white);
    	 sub3TF.setForeground(Color.white);
    	 sub4TF.setForeground(Color.white);
    	 
    	 sub1TF.setBorder(null);
    	 sub2TF.setBorder(null);
    	 sub3TF.setBorder(null);
    	 sub4TF.setBorder(null);
    	 
    	 sub1TF.setEditable(false);
    	 sub2TF.setEditable(false);
    	 sub3TF.setEditable(false);
    	 sub4TF.setEditable(false);
    	 
    	 sub1TF.setText(getBooksCount()+""); // total books
    	 sub2TF.setText(getIssuedCount()+"");      // total issued
         sub3TF.setText(getStudentCount()+"");     // total members
    	 sub4TF.setText(getTotalFine()+"");      // total fine
    	 
    	 sub1TF.setBounds(10,30,100,40);
    	 sub2TF.setBounds(10,30,100,40);
    	 sub3TF.setBounds(10,30,100,40);
    	 sub4TF.setBounds(10,30,100,40);
    	 
    	 sub1.add(sub1TF);
    	 sub2.add(sub2TF);
    	 sub3.add(sub3TF);
    	 sub4.add(sub4TF);
    	 
    	 sub1.add(sub1L1);
    	 sub2.add(sub2L1);
    	 sub3.add(sub3L1);
    	 sub4.add(sub4L1);
    	 
    	 sub1.add(iconLabel1);
    	 sub2.add(iconLabel2);
    	 sub3.add(iconLabel3);
    	 sub4.add(iconLabel4);
    	 
    	 panel1.add(sub1);
    	 panel1.add(sub2);
    	 panel1.add(sub3);
    	 panel1.add(sub4);
    	 //////////////////////////////////////////////
    	 
    	 panel1.add(logo);
    	 p1.add(panel1);
     }
     
     public int getBooksCount() {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Select Count(*) from Books";
		 int count = 0;
		 
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       if(rs.next()) {
	    	   count = rs.getInt(1);
	       }else {
	    	   count = 0;
	       }
		}catch(Exception e2) {
		   e2.printStackTrace();
		}
		return count;
     }
     
     public int getIssuedCount() {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Select Count(*) from issuedBooks";
		 int count = 0;
		 
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       if(rs.next()) {
	    	   count = rs.getInt(1);
	       }else {
	    	   count = 0;
	       }
		}catch(Exception e2) {
		   e2.printStackTrace();
		}
		return count;
     }
     
     public int getStudentCount() {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Select Count(*) from Student";
		 int count = 0;
		 
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       if(rs.next()) {
	    	   count = rs.getInt(1);
	       }else {
	    	   count = 0;
	       }
		 }catch(Exception e2) {
		   e2.printStackTrace();
		 }
		return count;
     }
     
     public int getTotalFine() {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
		 String sql = "Select Sum(amount) from fine";
		 int count = 0;
		 
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(sql);
	       
	       if(rs.next()) {
	    	   count = rs.getInt(1);
	       }else {
	    	   count = 0;
	       }
		 }catch(Exception e2) {
		   e2.printStackTrace();
		 }
		 return count;
     }
}
