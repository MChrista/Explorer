package explorer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class OrdnerKlickListener implements MouseListener {

	File file;
	
	public OrdnerKlickListener ( File file ) {
		this.file = file;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//Dateistruktur.elementClicked( file );		
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
