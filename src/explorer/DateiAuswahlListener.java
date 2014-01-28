package explorer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


public class DateiAuswahlListener implements MouseListener {

	
	Vorschau vor;
	Dateistruktur dat;
	
	public DateiAuswahlListener(Explorer expl) {
		this.vor = expl.getVor();
		this.dat = expl.getDst();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = dat.getMainTable().getSelectedRow();
		vor.reload(new File(dat.getCurrentPath() + "//" + dat.getMainTable().getValueAt(row, 0)) );
		
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
