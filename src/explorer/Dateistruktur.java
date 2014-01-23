/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.DropMode;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author A001278
 */
	public class Dateistruktur extends JPanel {
    
	static JTable MainTable;	
	static Object[][] data; 
	static boolean tableFilled = false;
	String[] coloumnNames = {"Name",
							"Änderungsdatum",
							"Größe"};	
	
	public Dateistruktur() {		
		MainTable = new JTable(data, coloumnNames);
		MainTable.setFillsViewportHeight(true);
		MainTable.getTableHeader().setReorderingAllowed(false);
		MainTable.setRowSelectionAllowed(true);
		MainTable.addMouseListener(new DateiAuswahlListener());
		
		
		this.setLayout( new BorderLayout() );
		this.add(MainTable.getTableHeader(),BorderLayout.PAGE_START);
		
		if ( tableFilled ) {
			this.add(MainTable , BorderLayout.CENTER);
		}
		
	}

	public static JTable getMainTable() {
		return MainTable;
	}

	public static void elementClicked ( File directory ) {
		File[] files = directory.listFiles();
		
		int i =0;
		while ( i < files.length ) {
			int j =0;
				data[i][j] = files[i].getName();
				data[i][j+1] = files[i].lastModified();
				data[i][j+2] = files[i].getTotalSpace();
				data[i][j+3] = files[i];	// schreib die datei in tabelle ( unsichtbar ) damit sie an die vorschau übergeben werden kann
			i++;
		}		
		Explorer.repaintDateistruktur();
	}
	

}