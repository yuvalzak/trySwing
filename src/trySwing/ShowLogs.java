package trySwing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class ShowLogs extends JFrame {

	private JPanel contentPane;
   private User focusUser = null;
   private theWindow window = null;
   private JTable table;
	 
	public ShowLogs(User focusUser, theWindow window) {
		setTitle("Log of Transactions");
		setBackground(new Color(153, 102, 153));
		  this.focusUser = focusUser;
		  this.window = window;
		  
		setBounds(100, 100, 623, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		table.setBackground(new Color(204, 153, 0));
		contentPane.add(table, BorderLayout.CENTER);
		doShowLogs();
	}

	  public void doShowLogs(){
		  List<LogFile> lstLogFiles = null;	
			if (focusUser != null ) {
			try {
				lstLogFiles =  window.dao.showLogFiles(focusUser.getUserId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			LogFileTableModel model = new LogFileTableModel(lstLogFiles);
		    table.setModel(model);
		 
			}	  
	  }
}
