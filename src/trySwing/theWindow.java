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
 

public class theWindow {

	private JFrame frame;
	private JTable table;
	private int num = 0;
	private JTextArea txt;
	private JComboBox cmb;
	private static theWindow window ;
	private static login _login;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					   window = new theWindow();
					window.frame.setVisible(true);
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
		 
		 
		frame.setBounds(100, 100, 606, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Load Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
                txt.setText(  txt.getText() + "\n" +  ++num);
                cmb.addItem(" " + num);
                cmb.addItem("whats wrong");
                JOptionPane.showMessageDialog( frame, "My Goodness, this is so concise" );
                
                
			}
		});
		btnNewButton.setBounds(239, 19, 117, 29);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 111, 192, 263);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		cmb = new JComboBox();
		 
		 
		cmb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		cmb.setBounds(51, 111, 165, 36);
		frame.getContentPane().add(cmb);
		
		  txt = new JTextArea();
		txt.setBounds(91, 214, 192, 113);
		frame.getContentPane().add(txt);
		
		JButton cmdLogin = new JButton("Login Page");
		cmdLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if (_login == null ) {_login = new login(frame);	}
				
		
				_login.setVisible(true);
			 	window.frame.setVisible(false);
			 //  window.frame.setEnabled(false);
				
				
			}
		});
		cmdLogin.setBounds(25, 19, 117, 29);
		frame.getContentPane().add(cmdLogin);
	}
}
