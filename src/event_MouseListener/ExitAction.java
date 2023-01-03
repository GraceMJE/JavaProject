package event_MouseListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitAction implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		String confirmMSG = e.getActionCommand();
		if (confirmMSG.equals("진짜?")) System.exit(0);
	}

}
