package trySwing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JCheckBox;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
    private JFrame _parentFrame;
    private JPasswordField txtPassword;
    private User loggedUser = null;
	private JCheckBox chkBox;
	 
	 
	public NewUser(JFrame  parentFrame, User loggedUser) {
		setTitle("Enter New User");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				_parentFrame.setVisible(true);
			}
		});
		  _parentFrame = parentFrame;
		  this.loggedUser = loggedUser;
		 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewUser = new JLabel("New User");
		lblNewUser.setFont(new Font("Dialog", Font.BOLD, 40));
		GridBagConstraints gbc_lblNewUser = new GridBagConstraints();
		gbc_lblNewUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewUser.gridx = 2;
		gbc_lblNewUser.gridy = 0;
		contentPane.add(lblNewUser, gbc_lblNewUser);
		
		JLabel lblName = new JLabel("User Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 2;
		contentPane.add(lblName, gbc_lblName);
		
		txtUserName = new JTextField();
		GridBagConstraints gbc_txtUserName = new GridBagConstraints();
		gbc_txtUserName.insets = new Insets(0, 0, 5, 5);
		gbc_txtUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUserName.gridx = 2;
		gbc_txtUserName.gridy = 2;
		contentPane.add(txtUserName, gbc_txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		JButton cmdEnter = new JButton("Enter");
		cmdEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtUserName.getText().equals("")   || txtPassword.getPassword().equals("")  ){
					JOptionPane.showMessageDialog(contentPane, "Enter name and password");
				} else {
				
					DAO data = new DAO();
					String strFromChar  = new String (  txtPassword.getPassword());
					UserAndMsg sc = new UserAndMsg("",null);
					sc = data.makeNewUser(txtUserName.getText(), strFromChar, loggedUser.getUserId() , chkBox.isSelected() );
					JOptionPane.showMessageDialog(contentPane, sc.getMsg());
					if(sc.getUser() != null) {
						_parentFrame.setVisible(true);
						dispose();
					}
				}
			}

			
		});
		
		txtPassword = new JPasswordField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdEnter.doClick();
			}
		});
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 4;
		contentPane.add(txtPassword, gbc_txtPassword);
		
		chkBox = new JCheckBox("is Admin");
		chkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_chkBox = new GridBagConstraints();
		gbc_chkBox.insets = new Insets(0, 0, 5, 5);
		gbc_chkBox.gridx = 1;
		gbc_chkBox.gridy = 6;
		contentPane.add(chkBox, gbc_chkBox);
		GridBagConstraints gbc_cmdEnter = new GridBagConstraints();
		gbc_cmdEnter.insets = new Insets(0, 0, 0, 5);
		gbc_cmdEnter.gridx = 2;
		gbc_cmdEnter.gridy = 7;
		contentPane.add(cmdEnter, gbc_cmdEnter);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUserName, txtPassword, cmdEnter}));
	}
 
	 
}
