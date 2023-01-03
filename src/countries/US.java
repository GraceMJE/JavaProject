package countries;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class US extends JPanel implements KeyListener, ActionListener {
	public GridLayout gr = new GridLayout(2,2,10,10);
	//페이지 전환을 위한 공간
	
	private JButton previous_Button = new JButton("<PREV PAGE"); //이전 page 이동버튼
	private JButton next_Button = new JButton("NEXT PAGE >"); //다음 page 이동버튼
	private JLabel pages = new JLabel("1"); //page 표시
	
	private JPanel centerBox = new JPanel(); //main page
	private JPanel buttonBox = new JPanel(); //button detach space
	private JPanel Pic_space = new JPanel(); //페이지 부착공간
	private JPanel Pic_first = new JPanel(); //사진 부착공간1
	private JPanel Pic_second = new JPanel(); //사진 부착공간2
	private JPanel Add_space = new JPanel(); //느낀점 쓰는 공간
	CardLayout Pic;
	
	public String [] pic_US = { //사진이 들어있는 배열
			"US/brooklyn_bridge.jpg", 
			"US/cicago.jpg",
			"US/Goergia.jpg", 
			"US/grandcanyon.jpg",
			"US/newyork.jpg", 
			"US/sanfrancisco.jpg",
			"US/라스베이거스.jpg"};
	
	//사진을 부착할 버튼과 버튼에 부착될 사진의 배열
	JButton [] imageButton = new JButton[pic_US.length];
	ImageIcon [] imagesIcon = new ImageIcon[pic_US.length];

	public JTextArea ta = new JTextArea("사진을 누르면 설명이 나옵니다.", 3, 5); //사진설명을 위한 공간
	public JTextField tf = new JTextField(70); //느낀점을 입력할 수 있는 공간
	public JButton addition = new JButton("느낀점 추가"); 
	
	public String [] des_US = { //각 사진에 대한 설명이 들어있는 배열
			"뉴욕의 브루클린교는 뉴욕 이스트강 사이를 가로지른 다리이다. 미국 독립기념일을 기념해서 불꽃놀이가 진행된다고 한다.",
			"시카고의 밀레니엄파크는 1997년에 착공해서 2004년에 개장한 공원으로 극장, 콘서트홀이 자리 잡고 있으며 공원은 연중무휴로 운영된다. \n시간은 오전 6시에 오픈하여 오후 11시까지 개장하기 때문에 시간에 구애받지 않고 둘러보기 좋은 곳이다.",
			"애틀랜타주의 조지아 수족관은 세계에서 가장 큰 수족관이다. \n수중생물만 10만 마리 이상 살고있기 때문에 정말 멋진 장면들을 포착할 수 있을 것이다.",
			"grandcanyon에 가면 그 광경에 압도당한다고 한다. 얼마나 멋있고 웅장하고 광활하면 그렇게 될까?",
			"미국을 대표하는 동상 중 하나이다.",
			"미국여행지인 금문교의 다리기리는 총 2,800미터로 걸어서 가게 될 경우 최소 40분이 걸린다고 한다. \n꽤 시간이 걸리지만 한번쯤 걸어볼만 하다.",
			"라스베이거스는 하이롤러는 세계에서 가장 큰 대관람차로 한 바퀴를 도는 데에 총 30분이 소요된다. \n하이롤러를 타면 라스베이거스 밸리와 스트립을 한꺼번에 볼 수 있으니 꼭 타보도록 하자!"};

	
	public US() {
		
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
				    OutputStream output = new FileOutputStream("C:\\Users\\Mgrac\\OneDrive\\Documents/feelingUS.txt");
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
		
		for (int i = 0 ; i < pic_US.length; i++) {
			ImageIcon originImage = new ImageIcon(pic_US[i]);
			
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
					ta.setText(des_US[i]);
				}
			}
		}
	}
	
}