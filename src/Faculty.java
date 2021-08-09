import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class Faculty extends MouseAdapter{
    JFrame frame;
    JPanel panel;
    JButton sideBar1,sideBar2,sideBar3,sideBar4,sideBar5,currentSelected;
    JPanel panel1,panel2,panel3,panel4;
    Point center;
    JLayeredPane layers;
    DefaultTableModel currModel;
    
    final String currentUser;
    final int WIDTH = 700;
    final int HEIGHT = 500;
    int currentPanel;
    
    public Faculty(String id) {
    	this.currentUser = id;
    	
    	frame = new JFrame();
    	frame.setLayout(null);
    	center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    	
    	/*======= sideBar ========*/
    	panel = new JPanel();
    	panel.setBounds(0,0,170,500);
    	panel.setBackground(new Color(68,87,121));
    	panel.setLayout(null);
    	
    	sideBar1 = new JButton("Home");
    	sideBar2 = new JButton("Student Managment");
    	sideBar3 = new JButton("ERP Managment");
    	sideBar4 = new JButton("Result Managment");
    	sideBar5 = new JButton("Logout");
        
    	sideBar1.setBounds(10,50,150,30);
    	sideBar2.setBounds(10,90,150,30);
    	sideBar3.setBounds(10,130,150,30);
    	sideBar4.setBounds(10,170,150,30);
    	sideBar5.setBounds(10,210,150,30);
    	
    	addSideBar(sideBar1);
    	addSideBar(sideBar2);
    	addSideBar(sideBar3);
    	addSideBar(sideBar4);
    	addSideBar(sideBar5);
    	
    	sideBar1.addMouseListener(this);
    	sideBar2.addMouseListener(this);
    	sideBar3.addMouseListener(this);
    	sideBar4.addMouseListener(this);
    	sideBar5.addMouseListener(this);
    	
    	setSelected(sideBar1);
    	
        panel.add(sideBar1);
        panel.add(sideBar2);
        panel.add(sideBar3);
        panel.add(sideBar4);
        panel.add(sideBar5);
        
        
        
        /* ========= Main Panels ================ */
       
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.white);
        panel4.setBackground(Color.blue);
        
        panel1.setBounds(0,0,530,500);
        panel2.setBounds(0,0,530,500);
        panel3.setBounds(0,0,530,500);
        panel4.setBounds(0,0,530,500);
        
        panel1.setLayout(null);
        panel2.setLayout(null);
        panel3.setLayout(null);
        panel4.setLayout(null);
        
        layers = new JLayeredPane();
        layers.add(panel1);
        layers.add(panel2);
        layers.add(panel3);
        layers.add(panel4);
        layers.setBounds(170,0,530,500);
        
       
        setUpPanel1();
        
        sideBar5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		int i = JOptionPane.showConfirmDialog(null, "Are you Sure ?","Logout",JOptionPane.YES_NO_OPTION);
        		if(i == 0) {
        			System.exit(0);
        		}
        	}
        });
        
    	/*========================================*/
        
        frame.add(layers);
    	frame.add(panel);
    	frame.setLocation((int)center.getX()-WIDTH/2,(int)center.getY()-HEIGHT/2);
    	frame.setSize(WIDTH,HEIGHT);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(WIDTH,HEIGHT);
    	frame.setVisible(true);
   
    }
    
    public void addSideBar(JButton sideBar) {
    	sideBar.setContentAreaFilled(false);
    	sideBar.setForeground(Color.white); 
        sideBar.setFocusable(false);
        sideBar.setBorder(null);
        sideBar.setHorizontalAlignment(JButton.LEFT);
    }
    
    public void mouseEntered(MouseEvent me) {
    	JButton btn = (JButton)me.getSource();
    	btn.setOpaque(true);
    	btn.setBackground(Color.white);
        btn.setForeground(new Color(68,87,121));
    }
    
    public void mouseExited(MouseEvent me) {
    	JButton btn = (JButton)me.getSource();
    	
    	if(btn != currentSelected) {
        	btn.setBackground(new Color(68,87,121));
        	btn.setForeground(Color.white);
    	}
    }
    
    public void mouseClicked(MouseEvent me) {
    	JButton b = (JButton)me.getSource();
    	removeSelected();
    	setSelected(b);

        if(b.getText() == "Home") {
        	panel1.setVisible(true);
        	panel2.setVisible(false);
        	panel3.setVisible(false);
        	panel4.setVisible(false);
        }else if(b.getText() == "Student Managment") {
        	setUpPanel2();
        	panel1.setVisible(false);
        	panel2.setVisible(true);
        	panel1.setVisible(false);
        	panel1.setVisible(false);
        }else if(b.getText() == "ERP Managment") {
        	setUpPanel3();
        	panel1.setVisible(false);
        	panel2.setVisible(false);
        	panel3.setVisible(true);
        	panel4.setVisible(false);
        }else if(b.getText() == "Result Managment") {
        	setUpPanel4();
        	panel1.setVisible(false);
        	panel2.setVisible(false);
        	panel3.setVisible(false);
        	panel4.setVisible(true);
        }
    }
    
    public void setSelected(JButton sideBar) {
    	sideBar.setOpaque(true);
    	sideBar.setBackground(Color.white);
        sideBar.setForeground(new Color(68,87,121));
        currentSelected = sideBar;
    }
    
    public void removeSelected() {
    	currentSelected.setBackground(new Color(68,87,121));
    	currentSelected.setForeground(Color.white);
    }
    
    public void setUpPanel1() {
    	JPanel p1 = new JPanel();
    	JPanel p2 = new JPanel();
    	
    	p1.setLayout(null);
    	p2.setLayout(null);
    	p1.setBackground(Color.white);
    	p2.setBackground(Color.white);
        
        JLabel image = new JLabel();
        ImageIcon icon = new ImageIcon("images\\profile-user.png");  
        image.setIcon(icon);
        image.setBounds(190,35,128,128);
        
        p1.add(image);
        
        JLabel idLabel = new JLabel("Employee Id");
        JLabel nameLabel = new JLabel("Employee Name");
        JLabel phnLabel = new JLabel("Phone No");
        JLabel userLabel = new JLabel("Username");
        JLabel branchLabel = new JLabel("Branch");
        
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phnField = new JTextField();
        JTextField userField = new JTextField();
        JTextField branchField = new JTextField();
        
        idLabel.setFont(new Font("Consolas",Font.BOLD,20));
        nameLabel.setFont(new Font("Consolas",Font.BOLD,20));
        phnLabel.setFont(new Font("Consolas",Font.BOLD,20));
        userLabel.setFont(new Font("Consolas",Font.BOLD,20));
        branchLabel.setFont(new Font("Consolas",Font.BOLD,20));
        
        idLabel.setBounds(50,10,150,40);
        nameLabel.setBounds(50,60,150,40);
        phnLabel.setBounds(50,110,150,40);
        userLabel.setBounds(50,160,150,40);
        branchLabel.setBounds(50,210,150,40);
        
        idField.setBounds(250,10,200,30);
        nameField.setBounds(250,60,200,30);
        phnField.setBounds(250,110,200,30);
        userField.setBounds(250,160,200,30);
        branchField.setBounds(250,210,200,30);
        
        idField.setFont(new Font("Consolas",Font.BOLD,20));
        nameField.setFont(new Font("Consolas",Font.BOLD,20));
        phnField.setFont(new Font("Consolas",Font.BOLD,20));
        userField.setFont(new Font("Consolas",Font.BOLD,20));
        branchField.setFont(new Font("Consolas",Font.BOLD,20));
        
        idField.setBackground(new Color(246,246,246));
        nameField.setBackground(new Color(246,246,246));
        phnField.setBackground(new Color(246,246,246));
        userField.setBackground(new Color(246,246,246));
        branchField.setBackground(new Color(246,246,246));
        
        idField.setBorder(null);
        nameField.setBorder(null);
        phnField.setBorder(null);
        userField.setBorder(null);
        branchField.setBorder(null);
        
        try {
          setData(idField,nameField,phnField,userField,branchField);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        p2.add(idLabel);
        p2.add(nameLabel);
        p2.add(phnLabel);
        p2.add(userLabel);
        p2.add(branchLabel);
        p2.add(idField);
        p2.add(nameField);
        p2.add(phnField);
        p2.add(userField);
        p2.add(branchField);
        
    	p1.setBounds(0,0,530,170);
    	p2.setBounds(0,170,530,372);
    	
    	panel1.add(p1);
    	panel1.add(p2);
    }
    
    public void setData(JTextField idField,JTextField nameField,JTextField phnField,JTextField userField,JTextField branchField) throws Exception{
    	String username = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
		String query1 = "SELECT F.empid,F.first_name,F.last_name,F.username,F.phoneno,A.branch from Faculty F,Admin A where F.empid = '"+currentUser+"' and F.empid=A.empid";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt1 = conn.createStatement();
		ResultSet rs1 = stmt1.executeQuery(query1);
		
        rs1.next();
        idField.setText(rs1.getString("empid"));
		nameField.setText(rs1.getString("first_name")+""+rs1.getString("last_name"));
		phnField.setText(rs1.getString("phoneno"));
		userField.setText(rs1.getString("username"));
		branchField.setText(rs1.getString("branch"));
		
		stmt1.close();
		conn.close();
    } 
    
    public void setUpPanel2() {
    	JPanel p = new JPanel();
    	p.setLayout(null);
    	
    	JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton show = new JButton("Show all");
    	
        JLabel header = new JLabel("STUDNET MANAGMENT");
    	JLabel id = new JLabel("Roll no");
    	JLabel name = new JLabel("Name");
    	JLabel phone = new JLabel("Phone no");
    	JLabel gender = new JLabel("Gender");
    	JLabel department = new JLabel("Department");
    	JLabel yearsec = new JLabel("Year/Section");
    	
    	JTextField idTF = new JTextField();
    	JTextField nameTF = new JTextField();
    	JTextField phnTF = new JTextField();
    	
    	JRadioButton male = new JRadioButton("Male");
    	JRadioButton female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        male.setSelected(true);
    	
    	String departments[] = {"CSE","EEE","ECE","ME","CE","IT"};
    	String years[] = {"I","II","III","IV"};
    	String sections[] = {"1","2","3"};
    	
    	JComboBox cb1 = new JComboBox(departments);
    	JComboBox cb2 = new JComboBox(years);
    	JComboBox cb3 = new JComboBox(sections);
    	
    	header.setFont(new Font("Consolas",Font.BOLD,30));
    	id.setFont(new Font("Consolas",Font.BOLD,20));
    	name.setFont(new Font("Consolas",Font.BOLD,20));
    	phone.setFont(new Font("Consolas",Font.BOLD,20));
    	gender.setFont(new Font("Consolas",Font.BOLD,20));
    	department.setFont(new Font("Consolas",Font.BOLD,20));
    	yearsec.setFont(new Font("Consolas",Font.BOLD,20));
    	
    	idTF.setFont(new Font("Consolas",Font.BOLD,15));
    	nameTF.setFont(new Font("Consolas",Font.BOLD,15));
    	phnTF.setFont(new Font("Consolas",Font.BOLD,15));
    	male.setFont(new Font("Consolas",Font.BOLD,15));
    	female.setFont(new Font("Consolas",Font.BOLD,15));
    	cb1.setFont(new Font("Consolas",Font.BOLD,15));
    	cb2.setFont(new Font("Consolas",Font.BOLD,15));
    	cb3.setFont(new Font("Consolas",Font.BOLD,15));
    	
    	add.setFont(new Font("Consolas",Font.BOLD,15));
    	update.setFont(new Font("Consolas",Font.BOLD,15));
    	show.setFont(new Font("Consolas",Font.BOLD,15));
    	
    	idTF.setBackground(new Color(246,246,246));
    	nameTF.setBackground(new Color(246,246,246));
    	phnTF.setBackground(new Color(246,246,246));
    	male.setBackground(new Color(246,246,246));
    	female.setBackground(new Color(246,246,246));
    	cb1.setBackground(new Color(246,246,246));
    	cb2.setBackground(new Color(246,246,246));
    	cb3.setBackground(new Color(246,246,246));
    	
    	add.setBackground(new Color(68,87,121));
    	update.setBackground(new Color(68,87,121));
    	show.setBackground(new Color(68,87,121));
    	add.setForeground(Color.white);
    	update.setForeground(Color.white);
    	show.setForeground(Color.white);
    	
    	idTF.setBorder(null);
    	nameTF.setBorder(null);
    	phnTF.setBorder(null);
    	
        add.setBorder(null);
        update.setBorder(null);
        show.setBorder(null);
           	
        header.setBounds(110,20,300,40);
    	id.setBounds(70,70,150,40);
    	name.setBounds(70,120,150,40);
    	phone.setBounds(70,170,150,40);
    	gender.setBounds(70,220,150,40);
    	department.setBounds(70,270,150,40);
    	yearsec.setBounds(70,320,150,40); 
    	
    	idTF.setBounds(270,70,200,40);
    	nameTF.setBounds(270,120,200,40);
    	phnTF.setBounds(270,170,200,40);
    	male.setBounds(270,220,90,40);
    	female.setBounds(380,220,90,40);
    	cb1.setBounds(270,270,200,40);
    	cb2.setBounds(270,320,90,40);
    	cb3.setBounds(380,320,90,40);
    	
    	add.setBounds(70,380,120,40);
    	update.setBounds(205,380,120,40);
    	show.setBounds(340,380,120,40);
    	
    	add.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			if(isDataSet(idTF,nameTF,phnTF)) {
    				String username = "root";
    				String password = "root";
    				String url = "jdbc:mysql://localhost:3306/project_college";
    				
    				String id = idTF.getText().toUpperCase();
    				String name = nameTF.getText();
    				String phn = phnTF.getText();
    				String gender ;
    				String dept = (String)cb1.getSelectedItem();
    			    String year = (String)cb2.getSelectedItem();
    			    String sec = (String)cb3.getSelectedItem();
    			    
    			    if(male.isSelected()) {
    			    	gender = "Male";
    			    }else {
    			    	gender = "Female";
    			    }
    			    
    				String sql = "Insert into Student Values('"+id+"','"+name+"','"+phn+"',"
    					    	+ "'"+gender+"','"+dept+"','"+year+"','"+sec+"','"+currentUser+"','"+id+"','"+id+"')";
    				
    				try {
    				  Class.forName("com.mysql.cj.jdbc.Driver");
    				  Connection conn = DriverManager.getConnection(url, username, password);
    				  Statement stmt = conn.createStatement();
    				  int i = stmt.executeUpdate(sql);
    				  if(i > 0) {
    					  JOptionPane.showMessageDialog(null, "Student Added Sucessfull");
    				  }
    				}catch(Exception e) {
    					JOptionPane.showMessageDialog(null, "Student Already Exists","",JOptionPane.WARNING_MESSAGE);
    				}
    			}else {
    				JOptionPane.showMessageDialog(null, "Please Add Entire Data","warning",JOptionPane.WARNING_MESSAGE);
    			}
    		}
    	});
    	
    	update.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			if(isDataSet(idTF,nameTF,phnTF)) {
    				String username = "root";
    				String password = "root";
    				String url = "jdbc:mysql://localhost:3306/project_college";
    				
    				String id = idTF.getText().toUpperCase();
    				String name = nameTF.getText();
    				String phn = phnTF.getText();
    				String gender ;
    				String dept = (String)cb1.getSelectedItem();
    			    String year = (String)cb2.getSelectedItem();
    			    String sec = (String)cb3.getSelectedItem();
    			    
    			    if(male.isSelected()) {
    			    	gender = "Male";
    			    }else {
    			    	gender = "Female";
    			    }
    			    
    			    String sql = "Update Student set name='"+name+"',phoneno='"+phn+"',"
			    		       + "gender='"+gender+"',department='"+dept+"',year='"+year+"',section='"+sec+"',"
			    			   + "added_by='"+currentUser+"' where std_id='"+id+"'";
    				
    				try {
    				  Class.forName("com.mysql.cj.jdbc.Driver");
    				  Connection conn = DriverManager.getConnection(url, username, password);
    				  Statement stmt = conn.createStatement();
    				  int i = stmt.executeUpdate(sql);
    				  if(i > 0) {
    					  JOptionPane.showMessageDialog(null, "Data Updated Sucessfull");
    				  }else {
    					  JOptionPane.showMessageDialog(null, "Student Doesn't Exists ","Warning",JOptionPane.WARNING_MESSAGE);
    				  }
    				}catch(Exception e) {
    					
    				}
    			}else {
    				JOptionPane.showMessageDialog(null, "Please Add Entire Data","warning",JOptionPane.WARNING_MESSAGE);
    			}
    		}
    	});
    	
    	show.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			try {
    			  showAll();
    			}catch(Exception e) {
    				
    			}
    		}
    	});
    		
    	//////////////////////////////////////////
    	
    	panel2.add(header);
    	panel2.add(id);
    	panel2.add(name);
    	panel2.add(phone);
    	panel2.add(gender);
    	panel2.add(department);
    	panel2.add(yearsec);
        panel2.add(idTF);
        panel2.add(nameTF);
        panel2.add(phnTF);
    	panel2.add(male);
    	panel2.add(female);
    	panel2.add(cb1);
    	panel2.add(cb2);
    	panel2.add(cb3);
    	panel2.add(add);
    	panel2.add(update);
    	panel2.add(show);
    }
    
    public boolean isDataSet(JTextField tf1,JTextField tf2,JTextField tf3) {
    	if(tf1.getText().length() > 0) {
    		if(tf2.getText().length() > 0) {
    			if(tf3.getText().length() > 0) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public void showAll() throws Exception {
    	JFrame temp = new JFrame();
    	JPanel panel = new JPanel();
    	JTable table = new JTable() {
    		public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(248, 246, 255);
                Color whiteColor = Color.WHITE;
                if(!comp.getBackground().equals(getSelectionBackground())) {
                   Color c = (row % 2 == 0 ? whiteColor : alternateColor);
                   comp.setBackground(c);
                   c = null;
                }
                return comp;
             }
    	};
    	
    	JPopupMenu pm = new JPopupMenu();
    	ImageIcon icon = new ImageIcon("images\\trash.png");
    	JMenuItem mi = new JMenuItem("Delete");
    	mi.setIcon(icon);
    	pm.add(mi);
    	
    	JScrollPane scroll = new JScrollPane(table);
        JTextField searchTF = new JTextField();
        JButton searchBtn = new JButton("Search");
        JButton refresh = new JButton("Refresh");
    	
    	panel.setBackground(Color.white);
        searchTF.setBounds(130,20,200,40);
        searchBtn.setBounds(350,20,100,40);
        refresh.setBounds(470,20,100,40);
        scroll.setBounds(50,80,600,370);  //for table
        
        searchTF.setBackground(new Color(246,246,246));
        searchTF.setFont(new Font("Consolas",Font.BOLD,15));
        searchTF.setBorder(null);
        
        searchBtn.setBackground(new Color(108,122,224));
        refresh.setBackground(new Color(108,122,224));
        searchBtn.setFont(new Font("Consolas",Font.BOLD,15));
        refresh.setFont(new Font("Consolas",Font.BOLD,15));
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(108,122,224));
        header.setForeground(Color.white);
        header.setFont(new Font("Consolas",Font.BOLD,13));
        
        table.setBorder(null);
        table.setRowHeight(30);
        table.setFont(new Font("Consolas",Font.BOLD,12));
        
        try {
        	loadTable(table);
        }catch(Exception e) {
        	
        }  
        
        table.addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent me) {
        		if(me.isPopupTrigger() && table.getSelectedRow() != -1) {
        			pm.show(me.getComponent(),me.getX(),me.getY());
        		}
        	}
        });
        
        mi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		int row = table.getSelectedRow();
        		String id = (String)table.getValueAt(row, 0);
        		int i = JOptionPane.showConfirmDialog(null, "Are You Sure to Delete?");
        		if(i == 0) {
        			try {
        				boolean isDeleted = deleteRow(id);
        				if(isDeleted) {
        					DefaultTableModel tempModel = (DefaultTableModel) table.getModel();
        					tempModel.removeRow(row);
        					tempModel.fireTableDataChanged();
        					temp.revalidate();
        				}
        			}catch(Exception e) {
        				
        			}
        		}
        	}
        });
        
        refresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		String columns[] = { "STUDENT ID","NAME","PHONE NO","GENDER","DEPARTMENT","YEAR","SECTION"};
        		try {
        			DefaultTableModel model2 = new DefaultTableModel();
        			model2.setColumnIdentifiers(columns);
        			table.setModel(model2);
        			loadTable(table);
        		}catch(Exception e) {
        			
        		}
        	}
        });
        
        searchBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		DefaultTableModel oldModel = (DefaultTableModel)table.getModel();
        		currModel = (DefaultTableModel) oldModel;
        		
        		DefaultTableModel newModel = new DefaultTableModel();
        		String columns[] = { "STUDENT ID","NAME","PHONE NO","GENDER","DEPARTMENT","YEAR","SECTION"};
       		 	newModel.setColumnIdentifiers(columns);
       		 
       		 	String ref = searchTF.getText();
       		 	String row[] = new String[7];
       		 
       		 	for(int i=0;i<oldModel.getRowCount();i++) {
       		 		row[0] = (String) oldModel.getValueAt(i,0);
       		 		row[1] = (String) oldModel.getValueAt(i,1);
       		 		row[2] = (String) oldModel.getValueAt(i,2);
       		 	    row[3] = (String) oldModel.getValueAt(i,3);
       		 	    row[4] = (String) oldModel.getValueAt(i,4);
       		 	    row[5] = (String) oldModel.getValueAt(i,5);
       		 	    row[6] = (String) oldModel.getValueAt(i,6);	
       			 
       		 		if(row[0].equals(ref) || row[1].equals(ref) || row[2].equals(ref) || row[3].equals(ref) || row[4].equals(ref) || row[5].equals(ref) || row[6].equals(ref)) {
       				  newModel.addRow(row);
       		 		}
       		 	}	
       		  table.setModel(newModel);
        	}
        });
       		 		
        searchTF.addKeyListener(new KeyAdapter() {
        	public void keyReleased(KeyEvent e) {
    			if(e.getKeyCode() == 8) {
    				String search = searchTF.getText();
    				if(search.length() < 1) {
    					table.setModel(currModel);
    				}
    			}
    		}
        });
        
    	temp.add(scroll);
    	temp.add(searchTF);
    	temp.add(searchBtn);
    	temp.add(refresh);
    	temp.add(scroll);
    	temp.add(panel);
    	temp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	temp.setLocation((int)center.getX()-700/2,(int)center.getY()-500/2);
    	temp.setSize(700,500);
    	temp.setVisible(true);
    }
    
    public void loadTable(JTable table) throws Exception {
    	DefaultTableModel model = new DefaultTableModel();
    	String columns[] = { "STUDENT ID","NAME","PHONE NO","GENDER","DEPARTMENT","YEAR","SECTION"};
    	model.setColumnIdentifiers(columns);
    	table.setModel(model);
    	
    	String username = "root";
 		String password = "root";
 		String url = "jdbc:mysql://localhost:3306/project_college";
 		String sql = "Select std_id,name,phoneno,gender,department,year,section from Student";
 		
 		Class.forName("com.mysql.cj.jdbc.Driver");
 		Connection conn = DriverManager.getConnection(url, username, password);
 		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		
 		while(rs.next()) {
 			String data[] = new String[7];
 			data[0] = rs.getString("std_id");
 			data[1] = rs.getString("name");
 			data[2] = rs.getString("phoneno");
 			data[3] = rs.getString("gender");
 			data[4] = rs.getString("department");
 			data[5] = rs.getString("year");
 			data[6] = rs.getString("section");
 			model.addRow(data);
 		}
 		
 		stmt.close();
 		conn.close();
    }
    
    public boolean deleteRow(String id) throws Exception {
    	String username = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
		String sql = "Delete From student where std_id='"+id+"'";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate(sql);
		
		if(i > 0) {
			JOptionPane.showMessageDialog(null, "Student Deleted Successfully");
			return true;
		}else {
			return false;
		}
    }
    
    public void setUpPanel4() {
    	String departments[] = {"CSE","EEE","ECE","ME","CE","IT"};
    	String years[] = {"I","II","III","IV"};
    	String sections[] = {"I","II"};
    	
    	JLabel header = new JLabel("RESULT MANAGMENT");
    	JLabel dept = new JLabel("Department");
    	JLabel year = new JLabel("Year");
    	JLabel sem = new JLabel("Semister");
    	
    	JComboBox cb1 = new JComboBox(departments);
    	JComboBox cb2 = new JComboBox(years);
    	JComboBox cb3 = new JComboBox(sections);
    	
    	JButton add = new JButton("Manage");
    	
    	header.setBounds(120,20,300,50);
    	dept.setBounds(70,100,150,40);
    	year.setBounds(70,160,150,40);
    	sem.setBounds(70,220,150,40);
    	cb1.setBounds(270,100,200,40);
    	cb2.setBounds(270,160,200,40);
    	cb3.setBounds(270,220,200,40);
    	add.setBounds(270,280,150,40);
    	
    	header.setFont(new Font("Consolas",Font.BOLD,30));
    	dept.setFont(new Font("Consolas",Font.BOLD,20));
    	year.setFont(new Font("Consolas",Font.BOLD,20));
    	sem.setFont(new Font("Consolas",Font.BOLD,20));
    	cb1.setFont(new Font("Consolas",Font.BOLD,20));
    	cb2.setFont(new Font("Consolas",Font.BOLD,20));
    	cb3.setFont(new Font("Consolas",Font.BOLD,20));
    	add.setFont(new Font("Consolas",Font.BOLD,15));
    	
    	cb1.setBackground(new Color(246,246,246));
    	cb2.setBackground(new Color(246,246,246));
    	cb3.setBackground(new Color(246,246,246));
    	add.setBackground(new Color(68,87,121));
    	
    	add.setForeground(Color.white);
    	add.setBorder(null);
    	
    	cb1.setBorder(null);
    	cb2.setBorder(null);
    	cb3.setBorder(null);
    	
    	add.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			String s1 = (String)cb1.getSelectedItem();
    			String s2 = (String)cb2.getSelectedItem();
    			String s3 = (String)cb3.getSelectedItem();
    			String clause = s2+"_"+s3;
    			
    			String username = "root";
    	 		String password = "root";
    	 		String url = "jdbc:mysql://localhost:3306/project_college";
    	 		String sql = "Select * from "+s1+"_sub where id='"+clause+"'";
    	 		
    	 		try {
    	 		Class.forName("com.mysql.cj.jdbc.Driver");
    	 		Connection conn = DriverManager.getConnection(url, username, password);
    	 		Statement stmt = conn.createStatement();
    	 		ResultSet rs = stmt.executeQuery(sql);
    	 		
    	 		rs.next();
    	 		
    	 		String str = rs.getString("subjects");
    	 		String subjects[] = str.split(",");
    	 		
    	 		resultManagment(subjects,s1,s2,s3);
    	 		}catch(Exception e) {
    	 			
    	 		}
    		}
    	});
    	
    	panel4.add(add);
    	panel4.add(header);
    	panel4.add(dept);
    	panel4.add(year);
    	panel4.add(sem);
    	panel4.add(cb1);
    	panel4.add(cb2);
    	panel4.add(cb3);
    	panel4.setBackground(Color.white);
    }
    
    public void resultManagment(String arr[],String dept,String year,String sem) {
    	JFrame temp = new JFrame();
    	temp.setLayout(null);
    	temp.getContentPane().setBackground(new Color(70,90,122));
    	Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    	String subs[]  = arr;
    	DefaultTableModel currModel;
   	 	
   	 	JPanel p1 = new JPanel();
   	 	JPanel p2 = new JPanel();
   	 	JPanel p3 = new JPanel();
   	 	JPanel p4 = new JPanel();
   	 	JPanel p5 = new JPanel(); 
   	 
   	 	JScrollPane scroll = new JScrollPane(p2);
   	 
   	 	p1.setLayout(null);
   	 	p2.setLayout(new GridLayout(subs.length,2,10,20));
   	 	p3.setLayout(null);
   	 	p4.setLayout(null);
   	 	p5.setLayout(new BorderLayout());
   	
   	 	p1.setBackground(Color.white);
   	 	p2.setBackground(Color.white);
   	 	p3.setBackground(Color.white);
   	 	p4.setBackground(Color.white);
   	 	p5.setBackground(Color.white);
   	 
   	 	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
   	 	
   	 	JLabel mainHeader = new JLabel("STUDENT RESULT MANAGMENT");
   	 	mainHeader.setFont(new Font("Consolas",Font.BOLD,30));
   	 	mainHeader.setForeground(Color.white);
   	 	mainHeader.setBounds(300,17,500,40);
   	 	
   	 	temp.add(mainHeader);
   	 /////////////////////////////////////////////
   	 /* Panel 1 */
   	 
   	 	JLabel id = new JLabel("Roll no");
   	 	JTextField idTF = new JTextField();
   	 
   	 	id.setFont(new Font("Courier",Font.BOLD,20));
   	 	idTF.setFont(new Font("Courier",Font.BOLD,20));
   	 
   	 	id.setBounds(10,15,100,40);
   	 	idTF.setBounds(150,17,170,40);
   	 
   	 	p1.add(id);
   	 	p1.add(idTF);
   	 
   	 /* Panel 2 */
   	 
   	 JLabel subLabel[] = new JLabel[subs.length];
   	 JTextField marks[] = new JTextField[subs.length];
   	 
   	 for(int i=0;i<subs.length;i++) {
   		 subLabel[i] = new JLabel(subs[i]);
   		 marks[i] = new JTextField();
   		 
   		 subLabel[i].setFont(new Font("Courier",Font.BOLD,20));
   		 marks[i].setFont(new Font("Courier",Font.BOLD,20));
   		 marks[i].setBackground(new Color(246,246,246));
   		 marks[i].setBorder(null);
   		 
   		 p2.add(subLabel[i]);
   		 p2.add(marks[i]);
   	 }
   	 
   	 /* Panel 3 */
   	 
   	 	JButton add = new JButton("Add");
   	 	JButton update = new JButton("Update");
   	 
   	 	add.setBackground(new Color(39,54,59));
   	 	update.setBackground(new Color(39,54,59));
   	 	add.setForeground(Color.white);
   	 	update.setForeground(Color.white);
   	 	add.setBorder(null);
   	 	update.setBorder(null);
   	 
   	 	add.setBounds(70,17,100,40);
   	 	update.setBounds(190,17,100,40);
   	 
   	 	p3.add(add);
   	 	p3.add(update);
   	 	
   	 	/* Panel 4 */
   	
   	 	JTextField searchTF = new JTextField();
   	 	JButton searchBtn = new JButton("Search");
   	 	JButton refresh = new JButton("Refresh");
   	 
        searchTF.setFont(new Font("Courier",Font.BOLD,20));
        searchBtn.setFont(new Font("Courier",Font.BOLD,15));
        refresh.setFont(new Font("Courier",Font.BOLD,15));
        
        searchTF.setBackground(new Color(246,246,246));
        searchBtn.setBackground(new Color(39,54,59));
        refresh.setBackground(new Color(39,54,59));
        searchBtn.setForeground(Color.white);
        refresh.setForeground(Color.white);
        searchTF.setBorder(null);
        
        searchTF.setBounds(80,15,200,40);
        searchBtn.setBounds(300,15,100,40);
        refresh.setBounds(420,15,100,40);
        
        
        
        p4.add(searchTF);
        p4.add(searchBtn);
        p4.add(refresh);
        
   	 /* Panel 5 */
        
     JTable table = new JTable() {
    	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
             Component comp = super.prepareRenderer(renderer, row, column);
             Color alternateColor = new Color(196,201,209);
             Color whiteColor = Color.WHITE;
             if(!comp.getBackground().equals(getSelectionBackground())) {
                 Color c = (row % 2 == 0 ? whiteColor : alternateColor);
                 comp.setBackground(c);
                 c = null;
             }
            return comp;
          }
      };
      
      JScrollPane tableScroll = new JScrollPane(table);
      DefaultTableModel model = new DefaultTableModel();
   	  Vector<String> header = new Vector<String>();
   	  
   	  header.add("Roll no");
   	  header.add("Name");
   	  header.add("Year");
   	  header.add("Section");
   	  
   	  for(int i=0;i<subs.length;i++) {
   		 header.add(subs[i]);
   	  }
      
   	  model.setColumnIdentifiers(header);
   	 
   	  setDataToModel(model,dept,year,sem,subs.length);
   	  table.setModel(model);
   	  tableScroll.setBounds(10,10,500,200);
   	  
   	  add.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		     String std_id = idTF.getText();

		     String tempData = getData(std_id,year,dept);
		     String tempArr[] = tempData.split(",");
		     String data[] = new String[subs.length+4];
		     data[0] = std_id;
		     data[1] = tempArr[0];
		     data[2] = year;
		     data[3] = tempArr[1];
		     int count = 5;
		     
		     String username = "root";
		 	 String password = "root";
		 	 String url = "jdbc:mysql://localhost:3306/project_college";
		 	 
		 	 String clause = dept+"_marks_"+year+"_"+sem;
		 	 String placeholder = getPlaceholders(subs.length);
		 	 String sql = "Insert into "+clause+" values"+placeholder+"";
		 			 
		 	try {
		 	  Class.forName("com.mysql.cj.jdbc.Driver");
		 	  Connection conn = DriverManager.getConnection(url, username, password);
		 	  PreparedStatement stmt = conn.prepareStatement(sql);
		 	  
		 	  stmt.setString(1,data[0]);
		 	  stmt.setString(2,data[1]);
		 	  stmt.setString(3,data[2]);
		 	  stmt.setString(4,data[3]);
		 	 
		 	  for(int i=count;i<=subs.length+4;i++) {
		 		  stmt.setString(i,marks[i-5].getText());
		 		  data[i-1] = marks[i-5].getText();
		 	  }
		 	  
		 	  int j = stmt.executeUpdate();
		 	  
		 	  if(j > 0) {
		 		  JOptionPane.showMessageDialog(null, "Student Result Added Successfully");
		 		  model.addRow(data);
		 		  model.fireTableDataChanged();
		 	  }
		 	}catch(Exception ae) {
		 		JOptionPane.showMessageDialog(null, "Student Result Already Exists","Warning",JOptionPane.WARNING_MESSAGE);
		 	}
		}
   	  });
   	  
   	  p5.add(tableScroll,BorderLayout.CENTER);
   	  
   	currModel = (DefaultTableModel) table.getModel(); 
   	
   	searchBtn.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent ae) {
    		DefaultTableModel currModel = (DefaultTableModel) table.getModel();
    		
    		DefaultTableModel newModel = new DefaultTableModel();
   		 	newModel.setColumnIdentifiers(header);
   		 
   		 	String ref = searchTF.getText();
   		 	String row[] = new String[subs.length+4];
   		 
   		 	for(int i=0;i<currModel.getRowCount();i++) {
   		 		for(int j=0;j<header.size();j++) {
   		 			row[j] = (String) currModel.getValueAt(i, j);
   		 		}
   			 
   		 		for(int j=0;j<header.size();j++) {
   		 			if(currModel.getValueAt(i, j).equals(ref)) {
   		 				newModel.addRow(row);
   		 			}
   		 		}
   		 	}	
   		  table.setModel(newModel);
    	}
    });
   		 		
    searchTF.addKeyListener(new KeyAdapter() {
    	public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == 8) {
				String search = searchTF.getText();
				if(search.length() < 1) {
					table.setModel(currModel);
				}
			}
		}
    });
    
    update.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent ae) {
	 		    String std_id = idTF.getText();
	 		    boolean contains = false;
	 		    
	 		    for(int i=0;i<currModel.getRowCount();i++) {
	 		    	if(currModel.getValueAt(i, 0).equals(std_id)) {
	 		    		contains = true;
	 		    		currModel.removeRow(i);
	 		    	}
	 		    }
	 		    
	 		    if(contains) {
	 		    	 String tempData = getData(std_id,year,dept);
	 			     String tempArr[] = tempData.split(",");
	 			     String data[] = new String[subs.length+4];
	 			     data[0] = std_id;
	 			     data[1] = tempArr[0];
	 			     data[2] = year;
	 			     data[3] = tempArr[1];
	 			     int count = 5;
	 			     
	 			     String username = "root";
	 			 	 String password = "root";
	 			 	 String url = "jdbc:mysql://localhost:3306/project_college";
	 			     
	 			     String clause = dept+"_marks_"+year+"_"+sem;
	 			 	 String placeholder = getPlaceholders(subs.length);
	 			 	 String sql = "Insert into "+clause+" values"+placeholder+"";
	 			 	
	 			 	 removeDBRow(std_id,clause);
	 			 	try {
	 			 	  Class.forName("com.mysql.cj.jdbc.Driver");
	 			 	  Connection conn = DriverManager.getConnection(url, username, password);
	 			 	  PreparedStatement stmt = conn.prepareStatement(sql);
	 			 	  
	 			 	  stmt.setString(1,data[0]);
	 			 	  stmt.setString(2,data[1]);
	 			 	  stmt.setString(3,data[2]);
	 			 	  stmt.setString(4,data[3]);
	 			 	 
	 			 	  for(int i=count;i<=subs.length+4;i++) {
	 			 		  stmt.setString(i,marks[i-5].getText());
	 			 		  data[i-1] = marks[i-5].getText();
	 			 	  }
	 			 	  
	 			 	  int j = stmt.executeUpdate();
	 			 	  
	 			 	  if(j > 0) {
	 			 		  JOptionPane.showMessageDialog(null, "Student Result Updated Successfully");
	 			 		  model.addRow(data);
	 			 		  model.fireTableDataChanged();
	 			 	  }
	 			 	}catch(Exception ae3) {
	 			 		JOptionPane.showMessageDialog(null, "Student Result Already Exists","Warning",JOptionPane.WARNING_MESSAGE);
	 			 	}
	 			     
	 		    }else{
	 		    	JOptionPane.showMessageDialog(null,"Student Doesn't Exists","Warning",JOptionPane.WARNING_MESSAGE);
	 		    }
	 		}
	 });
   	 
   	 /////////////////////////////////////////////
   	 
   	 p1.setBounds(30,80,350,70);
   	 scroll.setBounds(30,160,350,200);
   	 p3.setBounds(30,370,350,70);
   	 p4.setBounds(400,80,560,70);
   	 p5.setBounds(400,160,560,280);
   	 
   	 temp.add(p1);
   	 temp.add(scroll);
   	 temp.add(p3);
   	 temp.add(p4);
   	 temp.add(p5);
   	 
   	 temp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
   	 temp.setBounds((int)center.getX()-1000/2,(int)center.getY()-500/2,1000,500);
   	 temp.setVisible(true);
    }  
    
    public String getData(String id,String year,String dept) {
    	String username = "root";
 		String password = "root";
 		String url = "jdbc:mysql://localhost:3306/project_college";
 		String sql = "Select * from student where std_id='"+id+"' and department='"+dept+"' and year='"+year+"'";
 	    String data = "";
 	    
 		try {
 		Class.forName("com.mysql.cj.jdbc.Driver");
 		Connection conn = DriverManager.getConnection(url, username, password);
 		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		
 		rs.next();
 		data = rs.getString("name");
 		data = data+","+rs.getString("section");
 
 		}catch(Exception e) {
 			JOptionPane.showMessageDialog(null, "Invalid Student Id","",JOptionPane.WARNING_MESSAGE);
 		}
 	  return data;
    }
    
    public String getPlaceholders(int count) {
    	String str="(";
    	
    	for(int i=1;i<=count+4;i++) {
    		if(i == count+4) {
    		    str=str+"?)";
    		}else {
    			str=str+"?,";
    		}
    	}
    	
    	return str;
    }
    
    public void setDataToModel(DefaultTableModel model,String dept,String year,String sem,int len) {
    	String username = "root";
 		String password = "root";
 		String url = "jdbc:mysql://localhost:3306/project_college";
 		String sql = "Select * from "+dept+"_marks_"+year+"_"+sem;
 	    len+=4;
 	    String data[] = new String[len];
 	    
 		try {
 		Class.forName("com.mysql.cj.jdbc.Driver");
 		Connection conn = DriverManager.getConnection(url, username, password);
 		Statement stmt = conn.createStatement();
 		ResultSet rs = stmt.executeQuery(sql);
 		
 		  while(rs.next()) {
 			 for(int i=1;i<=len;i++) {
 				 data[i-1] = rs.getString(i);
 			 }
 			 model.addRow(data);
 		  }
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
    }
    
    public void removeDBRow(String std_id,String clause) {
    	  String username = "root";
 		  String password = "root";
 		  String url = "jdbc:mysql://localhost:3306/project_college";
 		  String sql = "Delete from "+clause+" where std_id='"+std_id+"'";
 	    
 		  try {
 		  Class.forName("com.mysql.cj.jdbc.Driver");
 		  Connection conn = DriverManager.getConnection(url, username, password);
 		  Statement stmt = conn.createStatement();
 		  stmt.executeUpdate(sql);
 		
 		  }catch(Exception e) {
 			e.printStackTrace();
 		  }
    }
    
    public void setUpPanel3() {
    	JPanel p = new JPanel();
    	p.setBackground(Color.white);
    	p.setLayout(null);
    	
    	String str1[] = {"CSE","EEE","ECE","ME","CE","IT"};
    	String str2[] = {"I","II","III","IV"};
    	String str3[] = {"I","II"};
    	String str4[] = {"1","2","3"};
    	
    	JComboBox cb1 = new JComboBox(str1);
    	JComboBox cb2 = new JComboBox(str2);
    	JComboBox cb3 = new JComboBox(str4);
    	JComboBox cb4 = new JComboBox(str3);
    	
    	JLabel l1 = new JLabel("Branch");
    	JLabel l2 = new JLabel("Year");
    	JLabel l3 = new JLabel("Section");
    	JLabel l4 = new JLabel("Sem");
    	JLabel l5 = new JLabel("Date");
    	
    	JTextField tf = new JTextField();
    	
    	JButton btn1 = new JButton("Add");
    	JButton btn2 = new JButton("Show");
    	
    	Font font = new Font("Consolas",Font.BOLD,20);
    	
    	l1.setFont(font);
    	l2.setFont(font);
    	l3.setFont(font);
    	l4.setFont(font);
    	l5.setFont(font);
    	
    	cb1.setFont(font);
    	cb2.setFont(font);
    	cb3.setFont(font);
    	cb4.setFont(font);
    	tf.setFont(font);
    	
    	btn1.setFont(font);
    	btn2.setFont(font);
    	
    	btn1.setBackground(new Color(68,87,121));
    	btn2.setBackground(new Color(68,87,121));
    	btn1.setForeground(Color.white);
    	btn2.setForeground(Color.white);
    	
    	l1.setBounds(70,50,150,40);
    	l2.setBounds(70,100,150,40);
    	l3.setBounds(70,150,150,40);
    	l4.setBounds(70,200,150,40);
    	l5.setBounds(70,250,150,40);
    	
    	cb1.setBounds(220,50,150,40);
    	cb2.setBounds(220,100,150,40);
    	cb3.setBounds(220,150,150,40);
    	cb4.setBounds(220,200,150,40);
    	tf.setBounds(220,250,150,40);
    	
    	Calendar cal = Calendar.getInstance();
    	String date = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
    	tf.setText(date);
    	
    	btn1.setBounds(100,300,100,40);
    	btn2.setBounds(220,300,100,40);
    	
    	btn1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			String brc = (String)cb1.getSelectedItem();
    			String yr = (String)cb2.getSelectedItem();
    			String sec = (String)cb3.getSelectedItem();
    			String s = (String)cb4.getSelectedItem();
    			
    			if(checkDate(tf.getText(),brc+"_att_"+yr+"_"+s,sec)) {
    				attendance(brc,yr,sec,s,tf.getText());
    			}else {
    				JOptionPane.showMessageDialog(null, "Invalid Date");
    			}
    		}
    	});
    	
    	btn2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			String brc = (String)cb1.getSelectedItem();
    			String yr = (String)cb2.getSelectedItem();
    			String sec = (String)cb3.getSelectedItem();
    			String s = (String)cb4.getSelectedItem();
    			String db = brc+"_att_"+yr+"_"+s;
    			
    			showAttedance(brc,yr,s,tf.getText(),sec);
    		}
    	});
    	
    	p.add(l1);
    	p.add(l2);
    	p.add(l3);
    	p.add(l4);
    	p.add(l5);
    	
    	p.add(cb1);
    	p.add(cb2);
    	p.add(cb3);
    	p.add(cb4);
    	p.add(tf);
    	
    	p.add(btn1);
    	p.add(btn2);
    	
    	p.setBounds(25,30,460,400);
    	panel3.add(p);
    }
    
    public void attendance(String branch,String year,String sec,String sem,String dd) {
    	JFrame temp = new JFrame();
    	Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    	temp.setLayout(null);
    	temp.getContentPane().setBackground(new Color(68,87,121));
    	
    	JPanel p1 = new JPanel();
    	JScrollPane pane = new JScrollPane(p1);
    	p1.setLayout(new BorderLayout());
    	p1.setBackground(Color.white);
    	pane.setBounds(25,100,440,350);
    	
    	JPanel p2 = new JPanel();
    	p2.setLayout(null);
    	p2.setBackground(Color.white);
    	p2.setBounds(25,10,440,80);
    	
    	JTextField field = new JTextField();
    	field.setBackground(new Color(246,246,246));
    	field.setBorder(null);
    	field.setBounds(30,20,150,40);
    	field.setEditable(false);
    	field.setText(dd);
    	field.setFont(new Font("Consolas",Font.BOLD,20));
    	
    	JButton btn1 = new JButton("Update");
    	JButton btn2 = new JButton("Cancel");
    	
    	btn1.setBackground(new Color(68,87,121));
    	btn1.setForeground(Color.white);
    	btn1.setBounds(190,20,100,40);
    	
    	btn2.setBackground(new Color(68,87,121));
    	btn2.setForeground(Color.white);
    	btn2.setBounds(300,20,100,40);
    	
    	JTable table = new JTable();
    	JScrollPane pane2 = new JScrollPane(table);
    	DefaultTableModel model = new DefaultTableModel() {
    		public Class<?> getColumnClass(int column){
    			switch(column) {
    			   case 0:
    				    return String.class;
    			   case 1:
   				        return Boolean.class;
    			   case 2:
   				        return String.class;
   				   default:
   					    return String.class;
    			}
    		}
    	};
    	
    	
    	String head[] = {"Roll no","Present","Student Name"};
    	model.setColumnIdentifiers(head);
    	table.setModel(model);
    	
    	//////////////////////////////
    
    	setUpModel(model,branch,year,sec);
    	
    	btn1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			String att = getAttedance(model);
    			addAttedance(branch+"_att_"+year+"_"+sem,dd,sec,att);
    			temp.dispose();
    		}
    	});
    	
    	btn2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			temp.dispose();
    		}
    	});
    	
    	/////////////////////////////
    	p2.add(field);
    	p2.add(btn1);
    	p2.add(btn2);
    	
    	p1.add(pane2);
  
    	temp.add(pane);
    	temp.add(p2);
    	temp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	temp.setBounds((int)center.getX()-500/2,(int)center.getY()-500/2,500,500);
    	temp.setVisible(true);
    }  
    
    public boolean checkDate(String date,String clause,String sec) {
    	 boolean cond1 = false;
    	 boolean cond2 = false;
    	 
    	 String arr[] = date.split("-");
    	 int leap[] = {31,29,31,30,31,30,31,31,30,31,30,31};
         int nonLeap[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    	
    	int dd = Integer.parseInt(arr[0]);
    	int mm = Integer.parseInt(arr[1]);
    	int yy = Integer.parseInt(arr[2]);
    	
    	if(yy % 4 == 0) {
    		if(dd <= leap[mm-1]) {
    			cond1 = true;
    		}
    	}else {
    		if(dd <= nonLeap[mm-1]) {
    			cond1 = true;
    		}
    	}
    	
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
	     String sql = "Select * from "+clause+" where date="+dd+" and month="+mm+" and year="+yy+" and section="+sec;
	     
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
           
           if(!rs.next()) {
        	   cond2 = true;
           } 
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
	   return cond1&&cond2;
    }
    
    public void setUpModel(DefaultTableModel model,String branch,String year,String sec) {
    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
	     String sql = "Select * from student where department='"+branch+"' and year='"+year+"' and section='"+sec+"'";
	     
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
           int count=0;
           
           while(rs.next()) {
        	   model.addRow(new Object[3]);
        	   model.setValueAt(rs.getString(1),count, 0);
        	   model.setValueAt(true,count, 1);
        	   model.setValueAt(rs.getString(2),count, 2);
        	   count++;
           }
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
    }
    
    public String getAttedance(DefaultTableModel model) {
    	int rows = model.getRowCount();
    	String att="";
    	
    	for(int i=0;i<rows;i++) {
    		boolean isSelected = (boolean) model.getValueAt(i, 1);
    		if(!isSelected) {
    			String temp = (String)model.getValueAt(i, 0);
    			att += temp+",";
    		}
    	}
    	
    	StringBuffer buff = new StringBuffer(att);
    	buff.deleteCharAt(att.length()-1);
    	
    	return new String(buff);
    }
    
    public void addAttedance(String table,String date,String sec,String att) {
    	 String arr[] = date.split("-");

    	 String username = "root";
	     String password = "root";
		 String url = "jdbc:mysql://localhost:3306/project_college";
	     String sql = "Insert into "+table+" values('"+arr[0]+"','"+arr[1]+"','"+arr[2]+"','"+sec+"','"+att+"')";
	     
		 try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
           int i = stmt.executeUpdate(sql);
           if(i > 0) {
        	   JOptionPane.showMessageDialog(null,"Attedance added Successfully");
           }   
	     }catch(Exception e2) {
			e2.printStackTrace();
	     }
    }
    
    public void showAttedance(String branch,String year,String sem,String date,String sec) {
    	String months[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    	
    	JFrame temp = new JFrame();
    	Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    	temp.setLayout(null);
    	temp.getContentPane().setBackground(new Color(68,87,121));
    	
    	JPanel p1 = new JPanel();
    	p1.setLayout(null);
    	p1.setBackground(Color.white);
    	p1.setBounds(20,20,445,60);
    	
    	JPanel p2 = new JPanel();
    	p2.setLayout(new BorderLayout());
    	p2.setBackground(Color.white);
    	p2.setBounds(20,90,445,360);
    	
    	String arr[] = date.split("-");
    	int dd = Integer.parseInt(arr[0]);
    	int mm = Integer.parseInt(arr[1]);
    	int yy = Integer.parseInt(arr[2]);
    	int leap[] = {31,29,31,30,31,30,31,31,30,31,30,31};
        int nonLeap[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        int tempMM = mm;
        
        if(yy % 4== 0) {
        	mm = leap[mm-1];
        }else {
        	mm = nonLeap[mm-1];
        }
        
        String heading = branch+" "+year+"-"+sem+" "+months[tempMM-1]+" "+yy+" Attedance";
        JLabel label = new JLabel(heading);
        label.setFont(new Font("Consolas",Font.BOLD,20));
        label.setBounds(50,10,400,40);
        p1.add(label);
        
        JTable table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        
        model.addColumn("Roll no");
        model.addColumn("Name");
        
        for(int i=1;i<=mm;i++) {
        	model.addColumn(""+i);
        }
    	
    	String username = "root";
	    String password = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
	    String sql = "Select * from student where year='"+year+"' and section='"+sec+"' and department='"+branch+"'";
	    
		try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next()) {
			  String data[] = new String[2];
			  data[0] = rs.getString(1);
			  data[1] = rs.getString(2);
			  model.addRow(data);
		  }    
	    }catch(Exception e2) {
			e2.printStackTrace();
	    }
    	
		setUpShowModel(model,branch,year,sem,sec);
		
		p2.add(scroll);
    	temp.add(p1);
    	temp.add(p2);
    	temp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	temp.setBounds((int)center.getX()-500/2,(int)center.getY()-500/2,500,500);
    	temp.setVisible(true);
    }
    
    public void setUpShowModel(DefaultTableModel model,String branch,String year,String sem,String sec) {
    	Calendar cal = Calendar.getInstance();
    	int dd = cal.get(Calendar.DATE);
    	int mm1 = cal.get(Calendar.MONTH)+1;
    	int yy = cal.get(Calendar.YEAR);
    	int mm2=0;
    	
    	int leap[] = {31,29,31,30,31,30,31,31,30,31,30,31};
        int nonLeap[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        
        if(yy % 4 == 0) {
           mm2 = leap[mm1-1];
        }else {
           mm2 = nonLeap[mm1-1];
        }
    	
        String username = "root";
	    String password = "root";
		String url = "jdbc:mysql://localhost:3306/project_college";
	    String sql = "Select * from "+branch+"_att_"+year+"_"+sem+" where month='"+mm1+"' and year='"+yy+"' and section='"+sec+"'";
	    
		try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(url, username, password);
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(sql);
		  List<String> dates = new ArrayList<String>();
		  
		  while(rs.next()) {
			  int tempDD = Integer.parseInt(rs.getString(1))+1;
			  String tempStr = rs.getString(5);
			  String absenties[] = tempStr.split(",");
			  List<String> list = Arrays.asList(absenties);
			  
			  for(int i=0;i<model.getRowCount();i++) {
				  String tempId = (String)model.getValueAt(i, 0);
				  if(list.contains(tempId)) {
					  model.setValueAt("A",i,tempDD);
				  }else {
					  model.setValueAt("X",i,tempDD);
				  }
			  }
			  dates.add(""+tempDD);
		  }
		  
		  for(int i=2;i<=dd+1;i++) {
			  if(dates.contains(i+"")) {
				  continue;
			  }else {
				  for(int j=0;j<model.getRowCount();j++) {
					 model.setValueAt("-", j, i);
				  }
			  }
		  }
	    }catch(Exception e2) {
			e2.printStackTrace();
	    }
    }
}


