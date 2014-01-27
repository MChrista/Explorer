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
    
<<<<<<< HEAD
	JTable MainTable;	
	Object[][] data; 
	boolean tableFilled = false;
	private Explorer explorer;
	String[] coloumnNames = {"Name",
							"ï¿½nderungsdatum",
							"Grï¿½ï¿½e"};	
	
	public Dateistruktur(Explorer expl) {		
		explorer = expl;
=======
	static JTable MainTable;	
	static Object[][] data; 
	static boolean tableFilled = false;
	String[] coloumnNames = {"Name",
							"Änderungsdatum",
							"Größe"};	
	
	public Dateistruktur() {		
>>>>>>> 1907cbe8403fc9f3932bf4c0412ab46ec9c442f6
		MainTable = new JTable(data, coloumnNames);
		MainTable.setFillsViewportHeight(true);
		MainTable.getTableHeader().setReorderingAllowed(false);
		MainTable.setRowSelectionAllowed(true);
<<<<<<< HEAD
		MainTable.addMouseListener(new DateiAuswahlListener(explorer));
=======
		MainTable.addMouseListener(new DateiAuswahlListener());
>>>>>>> 1907cbe8403fc9f3932bf4c0412ab46ec9c442f6
		
		
		this.setLayout( new BorderLayout() );
		this.add(MainTable.getTableHeader(),BorderLayout.PAGE_START);
		
		if ( tableFilled ) {
			this.add(MainTable , BorderLayout.CENTER);
		}
		
	}

<<<<<<< HEAD
	public JTable getMainTable() {
		return MainTable;
	}

	public void elementClicked ( File directory ) {
=======
	public static JTable getMainTable() {
		return MainTable;
	}

	public static void elementClicked ( File directory ) {
>>>>>>> 1907cbe8403fc9f3932bf4c0412ab46ec9c442f6
		File[] files = directory.listFiles();
		
		int i =0;
		while ( i < files.length ) {
			int j =0;
				data[i][j] = files[i].getName();
				data[i][j+1] = files[i].lastModified();
				data[i][j+2] = files[i].getTotalSpace();
<<<<<<< HEAD
				data[i][j+3] = files[i];	// schreib die datei in tabelle ( unsichtbar ) damit sie an die vorschau ï¿½bergeben werden kann
			i++;
		}
		this.repaint();
=======
				data[i][j+3] = files[i];	// schreib die datei in tabelle ( unsichtbar ) damit sie an die vorschau übergeben werden kann
			i++;
		}		
		Explorer.repaintDateistruktur();
>>>>>>> 1907cbe8403fc9f3932bf4c0412ab46ec9c442f6
	}
	

}