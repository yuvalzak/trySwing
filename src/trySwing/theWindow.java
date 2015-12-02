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

public class theWindow {

	private JFrame frame;
	private int num = 0;
	private JTextArea txt;
	private JComboBox cmb;
	private static theWindow window;
	private static login _login;
	private TestingData testingData;
	private JComboBox comboBox;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new theWindow();
					window.frame.setVisible(true);
					window.testingData = new TestingData();
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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 153, 0));

		frame.setBounds(100, 100, 606, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Load Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// txt.setText( txt.getText() + "\n" + ++num);
				cmb.addItem(" " + num);
				cmb.addItem("whats wrong");
				// JOptionPane.showMessageDialog( frame, "My Goodness, this is
				// so concise" );

				try {

					txt.setText(window.testingData.getData().trim());
					 
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(358, 19, 117, 29);
		frame.getContentPane().add(btnNewButton);

		cmb = new JComboBox();

		cmb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		cmb.setBounds(51, 111, 165, 36);
		frame.getContentPane().add(cmb);

		JButton cmdLogin = new JButton("Login Page");
		cmdLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (_login == null) {
					_login = new login(frame);
				}

				_login.setVisible(true);
				window.frame.setVisible(false);
				// window.frame.setEnabled(false);

			}
		});
		cmdLogin.setBounds(52, 19, 140, 29);
		frame.getContentPane().add(cmdLogin);

		comboBox = new JComboBox();
		comboBox.setBounds(51, 72, 153, 27);
		frame.getContentPane().add(comboBox);
				
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(40, 195, 277, 94);
				frame.getContentPane().add(scrollPane_1);
		
				txt = new JTextArea();
				scrollPane_1.setViewportView(txt);
				txt.setWrapStyleWord(true);
	}
}
