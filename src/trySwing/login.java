package trySwing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
 

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JFrame _frame;

	private static Login login;
	private JButton cmdEnter;
	private NewUser newUser;
	private JPasswordField txtPassword;
	private theWindow _mainWindow = null;

	public void openLastWindow() {

		{
			_frame.setVisible(true);
		}
	}

	public Login(theWindow mainWindow) {
		_mainWindow = mainWindow;
		setTitle("Login Page");
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				openLastWindow();
			}

		});
		login = this;

		_frame = _mainWindow.getFrmMainPage(); // callingFrame;
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewUser = new JMenu("New User");
		menuBar.add(mnNewUser);

		JMenuItem mntmNewUser = new JMenuItem("New User");
		mntmNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newUser = new NewUser(login);
				newUser.setVisible(true);
				setVisible(false);
			}
		});
		mnNewUser.add(mntmNewUser);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdEnter.doClick();
				JOptionPane.showMessageDialog(login, "ahalan");
			}
		});
		mnNewUser.add(mntmClose);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 165, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUserName = new JTextField();
		txtUserName.setBounds(111, 56, 134, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblNewLabel = new JLabel("user");
		lblNewLabel.setBounds(111, 28, 61, 16);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(111, 90, 61, 16);
		contentPane.add(lblPassword);

		TestingData data = new TestingData();

		cmdEnter = new JButton("Enter");
		cmdEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strFromChar = new String(txtPassword.getPassword());
				String LogInTry = data.LoginUser(txtUserName.getText(), strFromChar);
				if (LogInTry.compareTo("Guest") != 0)
				{
					mainWindow.AfterLogin(LogInTry);
					dispose(); // Destroy the JFrame object
				} else
					JOptionPane.showMessageDialog(login, "no user with this password !!");
			}
		});
		cmdEnter.setBounds(111, 145, 117, 29);
		contentPane.add(cmdEnter);

		txtPassword = new JPasswordField();
		txtPassword.setFocusCycleRoot(true);
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdEnter.doClick();
			}
		});
		txtPassword.setBounds(111, 118, 134, 28);
		contentPane.add(txtPassword);
	}
}
