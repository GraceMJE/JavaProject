package event_MouseListener;

import java.awt.*;
import java.awt.event.*;

public class ChangeBColor extends MouseAdapter{
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			int r = (int)(Math.random()*256);
			int g = (int)(Math.random()*256);
			int b = (int)(Math.random()*256);
			
			Component selected_TitlePanel = (Component)e.getSource();
			selected_TitlePanel.setBackground(new Color(r,g,b));
		}
	}
}

