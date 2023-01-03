package main;

import java.awt.*;
import javax.swing.*;
import event_MouseListener.ChangeBColor;

public class TitlePanel extends JPanel {
	TitlePanel(){
		setLayout(new FlowLayout());
		setBackground(Color.BLACK);
		addMouseListener(new ChangeBColor());
			//배경 더블클릭시 색상 랜덤으로 바뀌도록 하는 이벤트 리스너
		
		JLabel topic = new JLabel("My country Bucket List",
				SwingConstants.CENTER);
		topic.setForeground(Color.WHITE);
		topic.setFont(new Font("Magneto Bold", Font.ITALIC, 35));
		add(topic);
	}
}