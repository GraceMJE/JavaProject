package countries;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Hungary extends JPanel implements KeyListener, ActionListener {
	public GridLayout gr = new GridLayout(2,3,10,10);
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
	
	public String [] pic_Hungary = {
			"Hungary/Buda_Castle.jpg", 
			"Hungary/Fisherman.jpg",
			"Hungary/Pecs.jpg", 
			"Hungary/그레이트마켓.jpg",
			"Hungary/영웅광장.jpg",
			"Hungary/다뉴브강.jpg", 
			"Hungary/세체니다리.jpg",
			"Hungary/헝가리국회의사당.jpg"};
	
	//사진을 부착할 버튼과 버튼에 부착될 사진의 배열
	JButton [] imageButton = new JButton[pic_Hungary.length]; 
	ImageIcon [] imagesIcon = new ImageIcon[pic_Hungary.length];

	public JTextArea ta = new JTextArea("사진을 누르면 설명이 나옵니다.", 3, 5); //사진설명을 위한 공간
	public JTextField tf = new JTextField(70); //느낀점을 입력할 수 있는 공간
	public JButton addition = new JButton("느낀점 추가");
	
	public String [] des_Hungary = { //각 사진에 대한 설명이 들어있는 배열
			"부다페스트의 상징인 부다 왕궁. \n13세기 네오바로크 양식으로 지어진 부다 왕궁은 역사 박물관, 노동운동 박물관, 국립미술관 외에는 공개되지 않고 있다.",
			"뾰족한 고깔 모양의 어부의 요새. \n7개의 타워로 지어진 어부의 요새는 수천 년 전에 나라를 세운 7개의 마자르 족을 상징한다. \n요새에서 바라보는 부다페스트의 야경과 도나우 강의 전경은 그야말로 일품이라고 하니 놓치지 말자!.",
			"과거 헝가리 남부를 대표했던 남부 도시 페치. \n페치는 로마시대의 가톨릭 문화와 터키 문화, 헝가리의 마자르 문화까지 다양한 문화가 빚어낸 지중해풍의 건축물들로 도시 자체가 아름다운 풍광을 자랑한다. \n주요 관광지로는 도자기 공예품을 전시한 졸나이 박물관, 1900~1950년대의 헝가리 미술품을 전시하고 있는 현대 헝가리 회화관, 르네상스 시대의 조각상이 전시된 르네상스 시대 조각 박물관, \n헝가리의 대표적인 화가 마르틴의 생가를 개조해 마자르족의 전통 의상과 생활 도구를 감상할 수 있는 마르틴 페렌츠 박물관 등이 있다.",
			"그레이트 마켓홀은 부다페스트의 고딕 부흥기에 지어진 건물 내에 있는 거대한 실내 시장이다. \n이 시장에서는 헝가리 스콘과 슈트루델 같은 정통 현지 간식을 사서 먹어보거나 농장에서 기른 과일과 고급 잼 같은 신선한 제품을 구매할 수 있다. \n기념품가게도 있으니 참고하자.",
			"부다페스트에는 대형 광장이 많지만, 영웅 광장은 우뚝 솟은 기둥과 1896년에 만들어진 밀레니엄 기념비 단지로 특별히 더 유명하다. \n순수미술 박물관과 나란히 쿤스트할레 미술관이 있으며, 큰 도시 공원이 광장 바로 뒤에 있다.\n주변에는 커피나 스낵과 함께 훌륭한 문화유산의 전망을 감상할 수 있는 수많은 식당이 있으니 참고하자.",
			"다뉴브강은 크루즈를 탈 수 있다. 저녁 크루즈는 선상에서 저녁 식사를 제공하며 도시에서 가장 멋진 랜드마크의 색다른 전망을 즐길 수 있어서 꼭 가보도록 하자.",
			"세체니 다리는 부다와 페스트를 연결하는 다뉴브강의 다리이다. 노을이 질 때 산책하면 그렇게 멋있다고 한다.",
			"장엄한 네오 고딕 양식의 헝가리 국회의사당 건물은 1904년에 세워졌고, 다뉴브강의 강둑에 자리하고 있다. (참고로 헝가리에서 가장 큰 건물이라고 한다.) \n리버 크루즈나 다뉴브 서부 강둑에서 바라보면 잔잔한 강물 위에 비친 건물의 모습이 숨이 멎을 듯 아름다운 광경을 연출한다."
			};

	
	public Hungary() {
		
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
				    OutputStream output = new FileOutputStream("C:\\Users\\Mgrac\\OneDrive\\Documents/feelingHungray.txt");
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
		
		//버튼 이미지 부착하기위한 반복작업
		for (int i = 0 ; i < pic_Hungary.length; i++) {
			ImageIcon originImage = new ImageIcon(pic_Hungary[i]);
			
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
		//처음에 페이지 1이 보이게 설정
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
					ta.setText(des_Hungary[i]);
				}
			}
		}
	}
	
}
	




