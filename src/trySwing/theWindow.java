package trySwing;

import java.awt.EventQueue;
import java.awt.List;

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

public class theWindow {

	private JFrame frmMainPage;
	public JFrame getFrmMainPage() {
		return frmMainPage;
	}
	
	

	public void setFrmMainPage(JFrame frmMainPage) {
		this.frmMainPage = frmMainPage;
	}
	
	public void AfterLogin(String user){
	   lblUser.setText(user);
	   window.frmMainPage.setVisible(true);
	   
	}

	private int num = 0;
	private JTextArea txt;
	private static theWindow window;
	private static Login _login;
	private DAO testingData;
	private JComboBox cmbTables;
	private JScrollPane scrollPane_1;
	private JLabel lblUser;
	private JLabel lblHi;

	 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					window = new theWindow();
					window.frmMainPage.setVisible(true);
					window.testingData = new DAO();
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
				DAO d = new DAO();
				d.CloseConnection();
			}
		});
		frmMainPage.setTitle("Main Page");
		frmMainPage.getContentPane().setBackground(new Color(153, 153, 0));

		frmMainPage.setBounds(100, 100, 606, 440);
		frmMainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					  show = new String[] {"userName", "password" };
					  sql = "select * from users";
					  break;
					}
			
           txt.setText(window.testingData.getData(sql,show).trim());
					 
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(358, 19, 117, 29);
		frmMainPage.getContentPane().add(btnNewButton);

		JButton cmdLogin = new JButton("Login Page");
		cmdLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (_login == null) {
					_login = new Login(window);
				}

				_login.setVisible(true);
				window.frmMainPage.setVisible(false);

			}
		});
		cmdLogin.setBounds(51, 19, 140, 29);
		frmMainPage.getContentPane().add(cmdLogin);

		cmbTables = new JComboBox();
		cmbTables.setBounds(356, 72, 130, 27);
		frmMainPage.getContentPane().add(cmbTables);
		cmbTables.addItem("users");
		cmbTables.addItem("employees");
				
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(40, 195, 277, 94);
				frmMainPage.getContentPane().add(scrollPane_1);
		
				txt = new JTextArea();
				txt.setFont(new Font("Adobe Hebrew", Font.BOLD, 13));
				scrollPane_1.setViewportView(txt);
				txt.setWrapStyleWord(true);
				
				lblUser = new JLabel("Guest");
				lblUser.setBackground(Color.WHITE);
				lblUser.setBounds(96, 46, 130, 15);
				frmMainPage.getContentPane().add(lblUser);
				
				lblHi = new JLabel("Hi");
				lblHi.setBackground(Color.WHITE);
				lblHi.setBounds(62, 46, 34, 15);
				frmMainPage.getContentPane().add(lblHi);
				
				JButton cmdDelete = new JButton("Delete User");
				cmdDelete.setBounds(51, 299, 140, 29);
				frmMainPage.getContentPane().add(cmdDelete);
	}

	public String getLblUser() {
		return lblUser.getText();
	}

	public void setLblUser(String str) {
		this.lblUser.setText(str);  
	}
}
