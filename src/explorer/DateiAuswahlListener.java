package explorer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


public class DateiAuswahlListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = Dateistruktur.getMainTable().getSelectedRow();
		File file = (File)Dateistruktur.getMainTable().getValueAt(row, 4);
		
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
