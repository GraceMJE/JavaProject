package main;

import java.awt.*;
import javax.swing.*;
import countries.Mongolia;
import countries.Iceland;
import countries.Hungary;
import countries.US;
import event_MouseListener.ExitAction;
import countries.Austria;

public class MainDisplay extends JFrame{ 
    
	public MainDisplay(){
		setTitle("IT융합 20204228 마주은's Photo book (∗❛⌄❛∗)♥");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950,750);
		createMenu();
		
		Container main_container = getContentPane();
		main_container.setLayout(new BorderLayout());
		
		JTabbedPane country = createTabbedPane();
		//나라별 탭팬을 만들어주기 위한 작업
		
		main_container.add(new TitlePanel(), BorderLayout.NORTH);
		//메인화면 상단에 뜨는 사진첩 주제문구
		main_container.add(country, BorderLayout.CENTER);
		//메인화면 중간에 탭팬과 함께 뜰 나라별 사진 공간
		
		}
	
	//메뉴 만들기
	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu goodMenu = new JMenu("Do my best!");
		JMenu exit = new JMenu("종료");
		JMenuItem confirm = new JMenuItem("진짜?");
			
		mb.add(goodMenu);
		mb.add(exit);
		exit.add(confirm);
		confirm.addActionListener(new ExitAction());
		this.setJMenuBar(mb);
	}

	//bucket country 목록을 탭을 통해 분류
	public JTabbedPane createTabbedPane() {
		JTabbedPane country = new JTabbedPane(JTabbedPane.LEFT);
		country.addTab("몽골", new Mongolia());
		country.addTab("아이슬란드", new Iceland());
		country.addTab("헝가리", new Hungary());
		country.addTab("미국", new US());
		country.addTab("오스트리아", new Austria());
		return country;
	}

	public static void main(String[] args) {
		MainDisplay disp = new MainDisplay();
		disp.setVisible(true);
	}

}
