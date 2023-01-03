package countries;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class Austria extends JPanel implements KeyListener, ActionListener {
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
	
	public String [] pic_Austria = { //사진이 들어있는 배열
			"Austria/Salzkammergut.jpg", 
			"Austria/Salzkammergut2.jpg",
			"Austria/쉔부른궁전.jpg", 
			"Austria/bin.jpg",
			"Austria/할슈타트.jpg", 
			"Austria/할슈타트_밤.jpg",
			"Austria/호프부르크왕궁.jpg",};
	
	//사진을 부착할 버튼과 버튼에 부착될 사진의 배열
	JButton [] imageButton = new JButton[pic_Austria.length]; 
	ImageIcon [] imagesIcon = new ImageIcon[pic_Austria.length];
	
	
	public JTextArea ta = new JTextArea("사진을 누르면 설명이 나옵니다.", 3, 5); //사진설명을 위한 공간
	public JTextField tf = new JTextField(70); //느낀점을 입력할 수 있는 공간
	public JButton addition = new JButton("느낀점 추가");
	
	public String [] des_Austria = { //각 사진에 대한 설명이 들어있는 배열
			"산악열차 샤프베르그반을 탑승하고 샤프베르크 산 정상에 오르면 4개의 호수를 볼 수 있다.",
			"잘츠캄머굿은 영화 사운드 오브 뮤직 배경이 된 곳으로 평화롭고 아름다운 마을이다. ",
			"비엔나에 있는 쉔부른 궁전! 너무나 아름답다.",
			"오스트리아의 수도인 빈에서는 고 양식의 성 슈테판 대성당부터 바로크식 건축물인 카를 교회까지 시대별 건축물을 모두 볼 수 있다.",
			"할슈타트는 고대 독일어의 소금을 뜻하는 Hall과 독일어 도시를 뜻하는 Statt가 합쳐진 이름의 마을로 소금광산으로 굉장히 유명하다. \n20분이면 마을을 다 볼 수 있으니 이점을 참고하자.",
			"할슈타트의 밤의 전경도 너무나 아름답다.",
			"호프부르크 왕궁은 주변 조망이 아름답기로 유명하다. \n왕궁의 부르 큰문을 지나면 마리아테리지아 광장도 있으니 참고하자."
			};

	
	public Austria() {
		
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
				    OutputStream output = new FileOutputStream("C:\\Users\\Mgrac\\OneDrive\\Documents/feelingAustria.txt");
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
		for (int i = 0 ; i < pic_Austria.length; i++) {
			ImageIcon originImage = new ImageIcon(pic_Austria[i]);
			
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
					ta.setText(des_Austria[i]);
				}
			}
		}
	}
	
}
