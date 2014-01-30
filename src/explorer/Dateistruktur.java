/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.Date;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author A001278
 */
	public class Dateistruktur extends JPanel {
    

	JTable MainTable;	
	Object[][] data; 
	private Explorer explorer;
	String currentPath;
	String[] coloumnNames = {"Name",
							"Änderungsdatum",
							"Größe"};	
	
	
	
	public Dateistruktur(Explorer expl) {
		explorer = expl;
		MainTable = new JTable(data, coloumnNames);
		MainTable.getTableHeader().setReorderingAllowed(false);
		MainTable.setRowSelectionAllowed(true);
		MainTable.addMouseListener(new DateiAuswahlListener(explorer));
		this.setLayout( new BorderLayout() );		
	}


	public JTable getMainTable() {
		return MainTable;
	}


	public void directorySelected( File directory ) {

		File[] temp = directory.listFiles();
		File[] files = new File[temp.length];
		data = new Object[temp.length][4];
		currentPath = directory.getAbsolutePath();
		
		int a= 0;
		int b= 0;
		while ( a < temp.length ) {
			if ( !temp[a].isDirectory() ) {	// wenn gefundene Datei kein directory ist
				files[b] = temp[a];
				b++; a++;
			}
			else {
				a++;
			}
		}
		
		// Tabelle füllen	
		int i =0;
		while ( i < files.length ) {
			int j =0;
			if ( !(files[i] == null) ) {

				data[i][j] = files[i].getName();
				data[i][j+1] = unix2Date( files[i].lastModified() );
				data[i][j+2] = files[i].length();
				i++;
			}
			else {
				i++;	
			}			
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data,coloumnNames) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		
		
		MainTable = new JTable(data, coloumnNames);
		MainTable.getTableHeader().setReorderingAllowed(false);
		MainTable.setRowSelectionAllowed(true);
		MainTable.addMouseListener(new DateiAuswahlListener(explorer));
		MainTable.setModel(dtm);
		this.add(MainTable.getTableHeader(), BorderLayout.NORTH);
		this.add(MainTable , BorderLayout.CENTER);
		this.updateUI();

	}
	
	public Date unix2Date(long timestamp) {
		return new Date(timestamp);
	}
	
	public String getCurrentPath() {
		return currentPath;
	}
	
}