package countries;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.*;


public class Iceland extends JPanel implements KeyListener, ActionListener {
	public GridLayout gr = new GridLayout(2,2,10,10);
	//페이지 전환을 위한 공간
	
	private JButton previous_Button = new JButton("<PREV PAGE");
	private JButton next_Button = new JButton("NEXT PAGE >");
	private JLabel pages = new JLabel("1");
	
	private JPanel centerBox = new JPanel(); //main page
	private JPanel buttonBox = new JPanel(); //button detach space
	private JPanel Pic_space = new JPanel(); //페이지 부착공간
	private JPanel Pic_first = new JPanel(); //사진 부착공간1
	private JPanel Pic_second = new JPanel(); //사진 부착공간2
	private JPanel Add_space = new JPanel(); //느낀점 쓰는 공간
	CardLayout Pic;
	
	public String [] pic_Iceland = { //사진이 들어있는 배열
			"Iceland/Hallgrimskirkja.jpg", 
			"Iceland/skogafoss.jpg",
			"Iceland/Geysir.jpg", 
			"Iceland/Jokulsarlon.jpg",
			"Iceland/Orora.jpg"};
	
	//사진을 부착할 버튼과 버튼에 부착될 사진의 배열
	JButton [] imageButton = new JButton[pic_Iceland.length]; 
	ImageIcon [] imagesIcon = new ImageIcon[pic_Iceland.length];
	
	
	public JTextArea ta = new JTextArea("사진을 누르면 설명이 나옵니다.", 3, 5); //사진설명을 위한 공간
	public JTextField tf = new JTextField(70); //느낀점을 입력할 수 있는 공간
	public JButton addition = new JButton("느낀점 추가");
	
	public String [] des_Iceland = { //각 사진에 대한 설명이 들어있는 배열
			" 아이슬란드의 수도 레이캬비크의 랜드마크인 할그림스키르캬 성당이다. \n높이 74.5m를 엘리베이터를 이용해 오르면 레이캬비크 시내 전경을 한눈에 볼 수 있으니 꼭 방문해보자!.",
			"죽기 전에 꼭 가봐야 할 세계 10대 폭포로 이름을 올린 스코가포스. \n날이 좋은 날 방문한다면 스코가포스와 어우러진 무지개를 볼 수 있다.",
			"게이시르는 아이슬란드 말로 '쏟아져 나오는 것'이라는 뜻으로 5~10분마다 최대 25m까지 온천물이 분출되는 간헐천이다.",
			"아이슬란드 여행의 백미라고 불리는 요쿨살론. \n요쿨살론은 거대한 빙하호수로, 호수 위로 흐르는 거대한 빙하조각들을 가까이에서 몸소 체험할 수 있다. 다이아몬드처럼 영롱한 빙하를 보러 가보자!",
			"아이슬란드의 오로라~"};

	
	public Iceland() {
		
		setLayout(new BorderLayout());
		requestFocusInWindow();
		addKeyListener(this);
		
		//느낀점 입력 공간 설정
		add(Add_space, BorderLayout.NORTH);
		Add_space.setLayout(new FlowLayout());
		Add_space.setBackground(Color.pink);
		Add_space.add(tf);
		Add_space.add(addition);
		
		//느낀점 추가 버튼 누르면 파일경로에 설정된 이름으로 .txt파일 생성
		addition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				    OutputStream output = new FileOutputStream("C:\\Users\\Mgrac\\OneDrive\\Documents/feelingIceland.txt");
				    String feeling = tf.getText();
				    byte[] by=feeling.getBytes();
				    output.write(by);
				    tf.setText("");
						
				} catch (Exception x) {
			            x.getStackTrace();
				}
			}
		});
		
		//사진이 부착될 공간 설정
		add(centerBox, BorderLayout.CENTER);
		centerBox.setLayout(new BorderLayout());

		//for 사진
		centerBox.add(Pic_space, BorderLayout.CENTER);
		Pic = new CardLayout();
		Pic_space.setLayout(Pic);
		Pic_first.setLayout(gr);
		Pic_second.setLayout(gr);

		//for 페이지 이동버튼
		centerBox.add(buttonBox, BorderLayout.SOUTH);
		buttonBox.setLayout(new FlowLayout());
		buttonBox.setBackground(Color.pink);
		buttonBox.setAlignmentX(CENTER_ALIGNMENT);
		buttonBox.add(previous_Button);
		buttonBox.add(pages);
		buttonBox.add(next_Button);

		previous_Button.addActionListener(this);
		next_Button.addActionListener(this);

		//페이지 이동을 위한 키리스너 모든 컴포넌트에 추가
		//(어디에 있어도 이동가능하게)
		previous_Button.addKeyListener(this);
		next_Button.addKeyListener(this);
		ta.addKeyListener(this);
		tf.addKeyListener(this);
		addition.addKeyListener(this);

		//설명나오는 공간 설정(스크롤되게)
		add(new JScrollPane(ta), BorderLayout.SOUTH);
		
		for (int i = 0 ; i < pic_Iceland.length; i++) {
			ImageIcon originImage = new ImageIcon(pic_Iceland[i]);
			
			Image originalIcon = originImage.getImage();
			Image newIcon = originalIcon.getScaledInstance(450, 300, Image.SCALE_DEFAULT);
			//사진크기를 조절하기 위한 작업
			
			imagesIcon[i] = new ImageIcon(newIcon);
			imageButton[i] = new JButton(imagesIcon[i]);
			imageButton[i].setBorderPainted(false);
			imageButton[i].setFocusPainted(false);
			imageButton[i].setContentAreaFilled(false);
			imageButton[i].addActionListener(new Clicked_Description());
			//사진을 누르면 설명이 나오도록 하기위한 이벤트리스너 추가
			imageButton[i].addKeyListener(this);
			//각 이미지 버튼에 키보드 리스너 추가 (for 페이지 이동)
			
			if(i < 4) Pic_first.add(imageButton[i]);
			else Pic_second.add(imageButton[i]);
			//한 페이지에 사진 4개까지 배치하기 위한 장치
		}
		Pic_space.add(Pic_first, "1");
		Pic_space.add(Pic_second, "2");
		
		Pic.show(Pic_space, "1");
		
	}
	
	public void actionPerformed (ActionEvent e) {
		String commandation = e.getActionCommand();
		if(commandation.equals("<PREV PAGE")) {
			Pic.show(Pic_space, "1");
			pages.setText("1");
		}
		else {
			Pic.show(Pic_space, "2");
			pages.setText("2");
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) Pic.show(Pic_space, "1");
		else if (e.getKeyCode() == 39) Pic.show(Pic_space, "2");
		}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

		
	//사진을 누르면 설명나오도록 하는 이벤트 리스너 작성
	public class Clicked_Description implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0 ; i<imageButton.length; i++) {
				if(e.getSource() == imageButton[i]) {
					ta.setText(des_Iceland[i]);
				}
			}
		}
	}
	
}
	



