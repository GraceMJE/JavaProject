package countries;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Mongolia extends JPanel implements KeyListener, ActionListener {
	public GridLayout gr = new GridLayout(2,3,10,10);
	
	//페이지 전환에 사용될 버튼
	private JButton previous_Button = new JButton("<PREV PAGE");
	private JButton next_Button = new JButton("NEXT PAGE >");
	//페이지 수 나타내는 label
	private JLabel pages = new JLabel("1");
	
	private JPanel centerBox = new JPanel(); //main page
	private JPanel buttonBox = new JPanel(); //button detach space
	private JPanel Pic_space = new JPanel(); //페이지 부착공간
	private JPanel Pic_first = new JPanel(); //사진 부착공간1
	private JPanel Pic_second = new JPanel(); //사진 부착공간2
	private JPanel Add_space = new JPanel(); //느낀점 쓰는 공간
	CardLayout Pic;
	
	public String [] pic_Mongolia = { //사진이 들어있는 배열
			"Mongolia/울란바토르.png", 
			"Mongolia/테를지.png",
			"Mongolia/홍골리엔스.png", 
			"Mongolia/욜링암.png",
			"Mongolia/홉스굴.png", 
			"Mongolia/고비사막.png",
			"Mongolia/차강소브라가.png", 
			"Mongolia/밤하늘.png"};
	
	//사진을 부착할 버튼과 버튼에 부착될 사진의 배열
	JButton [] imageButton = new JButton[pic_Mongolia.length]; 
	ImageIcon [] imagesIcon = new ImageIcon[pic_Mongolia.length];
	
	public JTextArea ta = new JTextArea("사진을 누르면 설명이 나옵니다.", 3, 5); //사진설명을 위한 공간
	public JTextField tf = new JTextField(70); //느낀점을 입력할 수 있는 공간
	public JButton addition = new JButton("느낀점 추가");
	
	public String [] des_Mongolia = { //각 사진에 대한 설명이 들어있는 배열
			"붉은 영웅이라 뜻을 가진 몽골의 수도 울란바토르! 몽골여행의 시작지이다. ",
			"테를지는 울란바토르에서  70km 떨어진 지역으로 풍부한 자연경관을 느낄 수 있는 곳이다.\n유목민들의 이동식 전통가옥인 게르를 직접 체험할 수 있다. ",
			"홍골리엔스는 몽골에서 가장 큰 모래언덕 중 하나이다. \n바람에 의해 모래가 움직이거나 모래산이 무너져 내릴 때 나는 소리로 인해 노래하는 언덕으로도 불린다. \n낙타체험과 밤하늘에 풍등을 날리는 체험이 있으니 참고하자. ",
			"욜링암은 한여름에도 얼음이 녹지 않고 남아있는 곳이다. \n이곳은 독수리 계곡이라고 불리며 많은 야생동물을 볼 수 있다. ",
			"홉스굴 호수는 제주도보다 더 큰 규모의 호수로 캠핑을 즐길 수 있는 장소이다. \n직접 물고기를 잡아 식사를 할 수도있고 밤하늘의 아름다운 별과 함께 잠들 수 있다. (벌레가 많다고 하니 이점은 참고하자!) ",
			"세계 3대 사막인 고비사막! 이곳에서는 모래썰매를 탈 수 있다. \n내려가는 길은 쉬워도 올라가기는 어려운 법~ ",
			"아시아의 그랜드 캐니언이라고 불리는 차강 소브라이다. ",
			"몽골에서 꼭 보고싶은 밤하늘의 pearl~ "};

	public Mongolia() {
		
		setLayout(new BorderLayout());;
		
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
				    OutputStream output = new FileOutputStream
				    						("C:\\Users\\Mgrac\\OneDrive\\Documents"
				    								+ "/feeingMongolia.txt");
				    String feeling = tf.getText();
				    byte[] by=feeling.getBytes();
				    output.write(by);
				    tf.setText("");
						
				} catch (Exception x) {
			            x.getStackTrace();
				}
			}
		});
		
		
		add(centerBox, BorderLayout.CENTER);
		centerBox.setLayout(new BorderLayout());
		
		centerBox.add(Pic_space, BorderLayout.CENTER);
		Pic = new CardLayout();
		Pic_space.setLayout(Pic);
		Pic_first.setLayout(gr);
		Pic_second.setLayout(gr);
		
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
		
		add(new JScrollPane(ta), BorderLayout.SOUTH);
		
		for (int i = 0 ; i < pic_Mongolia.length; i++) {
			ImageIcon originImage = new ImageIcon(pic_Mongolia[i]);
			
			Image originalIcon = originImage.getImage();
			Image newIcon = originalIcon.getScaledInstance(300, 250, Image.SCALE_DEFAULT);
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
			
			if(i < 6) Pic_first.add(imageButton[i]);
			else Pic_second.add(imageButton[i]);
			//한 페이지에 사진 6개까지 배치하기 위한 장치
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
					ta.setText(des_Mongolia[i]);
				}
			}
		}
	}
	
}
	



