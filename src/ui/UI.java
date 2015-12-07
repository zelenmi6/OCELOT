package ui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import rtf.RtfGenerator;
import sqlite.SQLiteJDBC;

import com.tutego.jrtf.RtfHeaderStyle;

import ReportArchive.Report;
import dbConnections.DbManager;
import dbConnections.DbManagerBuilder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 263);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(137, 31, 76, 21);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setBounds(137, 72, 76, 21);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setBounds(137, 120, 76, 21);
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(137, 58, 55, 15);
		lblUsername.setText("Username");
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(137, 99, 55, 15);
		lblPassword.setText("Password");
		
		Label lblLocation = new Label(shell, SWT.NONE);
		lblLocation.setBounds(142, 10, 55, 15);
		lblLocation.setText("Location");
		
		final Button btnLocal = new Button(shell, SWT.RADIO);
		btnLocal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
			    JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Choose target databse.");
			    chooser.setAcceptAllFileFilterUsed(false);
			    String dblocation = "null";
				
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				      System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				      dblocation = chooser.getSelectedFile().getAbsolutePath();
				      System.out.println("getSelectedFile() : " + dblocation);
				      text.setText(dblocation);
				    } else {
				      System.out.println("No Selection ");
				    }
			}
		});
		btnLocal.setBounds(10, 52, 90, 16);
		btnLocal.setText("Local");
		
		final Button btnRemote = new Button(shell, SWT.RADIO);
		btnRemote.setBounds(10, 98, 90, 16);
		btnRemote.setText("Remote");
		
		final Label lblHello = new Label(shell, SWT.NONE);
		lblHello.setBounds(10, 199, 414, 15);
		lblHello.setText("Hello");
		
		final Button btnMysql = new Button(shell, SWT.CHECK);
		btnMysql.setBounds(298, 122, 93, 16);
		btnMysql.setText("MYSQL");
		
		final Button btnSqlite = new Button(shell, SWT.CHECK);
		btnSqlite.setBounds(298, 144, 93, 16);
		btnSqlite.setText("SQLite");
		
		final Button btnOracle = new Button(shell, SWT.CHECK);
		btnOracle.setBounds(298, 166, 93, 16);
		btnOracle.setText("Oracle");
		
		final Button btnViewReport = new Button(shell, SWT.NONE);
		btnViewReport.setEnabled(false);
		btnViewReport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					Process p = new ProcessBuilder("explorer.exe", "/select,src\\ReportArchive\\test.rtf").start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		btnViewReport.setBounds(332, 94, 75, 25);
		btnViewReport.setText("View Report");
		
		Button btnConnect = new Button(shell, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				DbManager manager = null;
				if(btnRemote.getSelection() == true)
				{
					try {
						dbConnections.DbManager.DbType dbtype = null;
						if(btnSqlite.getSelection() == true)
						{
							dbtype = DbManager.DbType.SQLite;
						}
						else if(btnMysql.getSelection() == true)
						{
							dbtype = DbManager.DbType.MySQL;
						}
						else if(btnOracle.getSelection() == true)
						{
							dbtype = DbManager.DbType.Oracle;
						}
						else
						{
							lblHello.setText("Error: database type not selected.");
						}
						
						try 
						{
							manager = new DbManagerBuilder(dbtype, text.getText()).buildDbManager();	
						} 
						catch (Exception e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (manager != null) 
						{
							Report report = new Report(manager, 1);
						}
						    lblHello.setText("Report Generated. Press 'View Report' to view.");
						    btnViewReport.setEnabled(true);
						}
					 catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(btnLocal.getSelection() == true)
				{								
					try {
						dbConnections.DbManager.DbType dbtype = null;
						if(btnSqlite.getSelection() == true)
						{
							dbtype = DbManager.DbType.SQLite;
						}
						else if(btnMysql.getSelection() == true)
						{
							dbtype = DbManager.DbType.MySQL;
						}
						else if(btnOracle.getSelection() == true)
						{
							dbtype = DbManager.DbType.Oracle;
						}
						else
						{
							lblHello.setText("Error: database type not selected.");
						}
						
						try 
						{
							manager = new DbManagerBuilder(dbtype, text.getText()).buildDbManager();	
						} 
						catch (Exception e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (manager != null) 
						{
							Report report = new Report(manager, 1);
						}
						    lblHello.setText("Report Generated. Press 'View Report' to view.");
						    btnViewReport.setEnabled(true);
						}
					 catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					MessageBox ms = new MessageBox(shell);
					ms.setMessage("Please select local or remote");
					ms.open();
				}
			}
		});
		btnConnect.setBounds(332, 48, 75, 25);
		btnConnect.setText("Connect");
		
		Button btnConnectWithUsername = new Button(shell, SWT.CHECK);
		btnConnectWithUsername.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(text_1.getEditable() == false){
				text_1.setEditable(true);
				text_2.setEditable(true);
				}
				else
				{
					text_1.setEditable(false);
					text_2.setEditable(false);
				}
			}
		});
		btnConnectWithUsername.setBounds(10, 166, 231, 16);
		btnConnectWithUsername.setText("Connect With Username and Password");

	}

}
