package explorer;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class VorschauListener implements ComponentListener {

	Vorschau vorschau;
	
	public VorschauListener(Vorschau vor) {
		vorschau = vor;
	}
	
	public void componentResized(ComponentEvent e) {
		vorschau.reload(null);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
