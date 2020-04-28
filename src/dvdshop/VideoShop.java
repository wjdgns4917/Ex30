package dvdshop;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.VideoView;

public class VideoShop extends JFrame{
	
	VideoView video;
	
	public VideoShop() {
		video=new VideoView();
		
		JTabbedPane pane=new JTabbedPane(); //tab���
		pane.addTab("���� ����", video);
		pane.addTab("���� ����2", null);
		
		pane.setSelectedIndex(0);
		add("Center",pane);
		
		setSize(800,600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VideoShop();
	}
}
