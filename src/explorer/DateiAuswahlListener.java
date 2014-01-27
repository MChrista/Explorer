package explorer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


public class DateiAuswahlListener implements MouseListener {
<<<<<<< HEAD
	
	Vorschau vor;
	Dateistruktur dat;
	
	public DateiAuswahlListener(Explorer expl) {
		this.vor = expl.getVor();
		this.dat = expl.getDst();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = dat.getMainTable().getSelectedRow();
		File file = (File)dat.getMainTable().getValueAt(row, 4);
		vor.reload(file);
=======

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = Dateistruktur.getMainTable().getSelectedRow();
		File file = (File)Dateistruktur.getMainTable().getValueAt(row, 4);
>>>>>>> 1907cbe8403fc9f3932bf4c0412ab46ec9c442f6
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
