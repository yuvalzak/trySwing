package trySwing;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Rectangle;
 

public class theWindow {
    private User loggedUser = null;
	private User focusUser = null; 
	private JFrame frmMainPage;
	public JFrame getFrmMainPage() {
		return frmMainPage;
	}
	
	

	public void setFrmMainPage(JFrame frmMainPage) {
		this.frmMainPage = frmMainPage;
	}
	
	public void AfterLogin(User user ){
      	loggedUser = user;
	   lblUser.setText(user.getName());
	   window.frmMainPage.setVisible(true);
	   
	}

	private int num = 0;
	private JTextArea txt;
	private static theWindow window;
	private static Login _login;
	public DAO dao;
	private JComboBox cmbTables;
	private JLabel lblUser;
	private JLabel lblHi;
	private JTable table;
	private JTextField txtFind;
	private JScrollPane scrollPane_1;

	 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					window = new theWindow();
					//window.frmMainPage.setVisible(true);
					window.dao = new DAO();
					
					openLoginPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public theWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainPage = new JFrame();
		frmMainPage.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dao.CloseConnection();
				openLoginPage();
				 frmMainPage.dispose();
			}
		});
		frmMainPage.setTitle("Main Page");
		frmMainPage.getContentPane().setBackground(new Color(153, 153, 0));

		frmMainPage.setBounds(100, 100, 793, 621);
		//frmMainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainPage.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Load Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// txt.setText( txt.getText() + "\n" + ++num);
		//		cmb.addItem(" " + num);
		//		cmb.addItem("whats wrong");
				// JOptionPane.showMessageDialog( frame, "My Goodness, this is
				// so concise" );

				String [] show = null ;
				String sql = null ;
				try {
					
					switch(cmbTables.getSelectedItem().toString()) {
					
					case "employees" :
						  show = new String[] {"first_name", "last_name", "salary"};
						  sql = "select * from employees";
						  break;
					case "users" :
					  show = new String[] {"userName", "password", "isAdmin" };
					  sql = "select * from users";
					  break;
					case "logFiles" :
						  show = new String[] {"loggedUser", "actionTaken" , "theDate"};
						  sql = "select * from logFiles";
						  break;
					}
			
           txt.setText(window.dao.getData(sql,show).trim());
					 
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(252, 19, 117, 29);
		frmMainPage.getContentPane().add(btnNewButton);

		JButton cmdLogin = new JButton("Login Page");
		cmdLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				openLoginPage();

			}

			
		});
		cmdLogin.setBounds(51, 19, 140, 29);
		frmMainPage.getContentPane().add(cmdLogin);

		cmbTables = new JComboBox();
		cmbTables.setBounds(249, 60, 130, 27);
		frmMainPage.getContentPane().add(cmbTables);
		cmbTables.addItem("logFiles");
		cmbTables.addItem("users");
		cmbTables.addItem("employees");
				
				lblUser = new JLabel("Guest");
				lblUser.setBackground(Color.WHITE);
				lblUser.setBounds(96, 46, 130, 15);
				frmMainPage.getContentPane().add(lblUser);
				
				lblHi = new JLabel("Hi");
				lblHi.setBackground(Color.WHITE);
				lblHi.setBounds(62, 46, 34, 15);
				frmMainPage.getContentPane().add(lblHi);
				
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(51, 260, 719, 170);
				frmMainPage.getContentPane().add(scrollPane_1);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					//	System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
					//	txtFind.setText(table.getValueAt(table.getSelectedRow(), 0).toString()) ;
						 int id = Integer.parseInt( table.getValueAt(table.getSelectedRow(), 1).toString())  ;
				         String name = table.getValueAt(table.getSelectedRow(), 0).toString()  ;
			     	     String password = table.getValueAt(table.getSelectedRow(), 2).toString()  ;
			     	     Boolean isAdmin = (Boolean) table.getValueAt(table.getSelectedRow(), 3)   ;
			         	txtFind.setText(name);
			     	 
						focusUser = new User( name,password, id, isAdmin);
						
					}
				});
				// this option gives double entries, but with getValueIsAdjusting acts like 
			    //  the mouseClick
				
				/* table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			        public void valueChanged(ListSelectionEvent event) {
			        	if (event.getValueIsAdjusting()) { 
			            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
			        }}
			    }); */
				scrollPane_1.setViewportView(table);
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(408, 19, 362, 155);
						frmMainPage.getContentPane().add(scrollPane);
						
						JPanel panel = new JPanel();
						FlowLayout flowLayout = (FlowLayout) panel.getLayout();
						flowLayout.setAlignment(FlowLayout.LEFT);
						panel.setBackground(Color.PINK);
						panel.setBounds(51, 211, 719, 37);
						frmMainPage.getContentPane().add(panel);
						
						JLabel lblNewLabel = new JLabel("Find");
						panel.add(lblNewLabel);
						lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
						
						txtFind = new JTextField();
						panel.add(txtFind);
						txtFind.setColumns(10);
						
						JButton cmdClear = new JButton("X");
						cmdClear.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								txtFind.setText("");
							}
						});
						panel.add(cmdClear);
						
						JButton cmdGetData = new JButton("Get Data");
						panel.add(cmdGetData);
						
						JButton cmdDelete = new JButton("Delete User");
						cmdDelete.setHorizontalAlignment(SwingConstants.RIGHT);
						cmdDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							int rt = 	JOptionPane.showConfirmDialog(null, "Delete this User? ",txtFind.getText(), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null)	; 
							if(	rt == JOptionPane.YES_OPTION) {
							if(    	dao.DeleteUser(txtFind.getText(),   loggedUser.getUserId()) == true) {
								cmdClear.doClick();
								cmdGetData.doClick();
							}}
								
							}
						});
						
						JButton cmdAllLogFiles = new JButton("Show All Logs");
						cmdAllLogFiles.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ShowLogs frame = new ShowLogs(focusUser, window);
								frame.setVisible(true);
							}
						});
						panel.add(cmdAllLogFiles);
						panel.add(cmdDelete);
						
								txt = new JTextArea();
								txt.setBounds(505, 22, 265, 82);
								frmMainPage.getContentPane().add(txt);
								txt.setFont(new Font("Adobe Hebrew", Font.BOLD, 13));
								txt.setWrapStyleWord(true);
						cmdGetData.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							List<User> lstUser = null;	
							try {
						     	String moreSql;
								if (txtFind.getText().length() > 0 ) {
								  moreSql = " Where userName like '" + txtFind.getText() + "%'";
								} else {
									moreSql = "";
								}
								lstUser =  window.dao.findUserData(moreSql);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}	
							usersTableModel model = new usersTableModel(lstUser);
						    table.setModel(model);
							
								
							}
						});
	}
	
	
	private static void openLoginPage() {
		if (_login == null) {
			_login = new Login(window);
		}

		_login.setVisible(true);
		window.frmMainPage.setVisible(false);
		 
	}

	public String getLblUser() {
		return lblUser.getText();
	}

	public void setLblUser(String str) {
		this.lblUser.setText(str);  
	}
}
