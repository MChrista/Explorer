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
<<<<<<< HEAD
		//Dateistruktur.elementClicked( file );		
=======
		Dateistruktur.elementClicked( file );		
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
