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
    
	private JTable MainTable;
	private Object[][] data;
	private Explorer explorer;
	String currentPath;
	String[] coloumnNames = {"Name",
							"Änderungsdatum",
							"Größe"};	
	
	
	
	public Dateistruktur(Explorer expl) {
		explorer = expl;
		MainTable = new JTable();
		this.add(MainTable.getTableHeader(), BorderLayout.NORTH);
		this.add(MainTable , BorderLayout.CENTER);
		this.setLayout( new BorderLayout() );		
	}


	public JTable getMainTable() {
		return MainTable;
	}


	public void directorySelected( File directory ) {
		
		File[] temp = directory.listFiles();
		currentPath = directory.getAbsolutePath();
		int rowCount =0;
		int z =0;
		
		while ( z < temp.length ) {
			if ( !(temp[z].isDirectory()) ) {
				rowCount++;
				z++;
			}
			else {
				z++;
			}
		}
		
		File[] files = new File[rowCount];
		data = null;
		data = new Object[rowCount][4];
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
		while ( i < rowCount ) {
			int j =0;
				data[i][j] = files[i].getName();
				data[i][j+1] = unix2Date( files[i].lastModified() );
				data[i][j+2] = files[i].length();
				i++;		
		}
		
		DefaultTableModel dtm = new DefaultTableModel(data,coloumnNames) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		
		MainTable.invalidate();
		dtm.setRowCount(rowCount);	
		MainTable.getTableHeader().setReorderingAllowed(false);
		MainTable.setRowSelectionAllowed(true);
		MainTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		MainTable.addMouseListener(new DateiAuswahlListener(explorer));
		MainTable.setModel(dtm);
		MainTable.revalidate();
		this.add(MainTable.getTableHeader(), BorderLayout.NORTH);
		this.add(MainTable , BorderLayout.CENTER);
		this.updateUI();

	}
	
	public String unix2Date(long timestamp) {
		
		Date temp = new Date(timestamp);
		return temp.toLocaleString();
		
	}
	
	public String getCurrentPath() {
		return currentPath;
	}
	
}

