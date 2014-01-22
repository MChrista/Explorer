/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author A001278
 */
	public class Dateistruktur extends JPanel {
    
	JTable MainTable;	
	Object[][] data = {null,null};
	boolean tableFilled = false;
	String[] coloumnNames = {"Name",
							"Änderungsdatum",
							"Größe"};	
	
	public Dateistruktur() {		
		MainTable = new JTable(data, coloumnNames);
		MainTable.setFillsViewportHeight(true);
		
		this.setLayout( new BorderLayout() );
		this.add(MainTable.getTableHeader(),BorderLayout.PAGE_START);
		
		if ( tableFilled ) {
			this.add(MainTable , BorderLayout.CENTER);
		}
		
	}

	public static void elementClicked ( String path ) {
	
	}

}