package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.DBCon;
import model.VideoModel;
import model.vo.Video;

public class VideoView extends JPanel implements ActionListener{
	JTextField tfVideoNum, tfVideoTitle, tfVideoDirector, tfVideoActor;
	JComboBox comVideoGenre;
	JTextArea taVideoContent;
	
	JCheckBox cbMultiInsert;
	JTextField tfInsertCount;
	
	JButton bVideoInsert, bVideoModify, bVideoDelete;
	
	JComboBox comVideoSearch;
	JTextField tfVideoSearch;
	JTable tableVideo;
	
	//video2�� �߰�
	VideoTableModel tbModelVideo;
	VideoModel model;
	
	public VideoView() {
		addLayout();
		initStyle();
		evenProc();
		connectDB();
		
		
	}
	public void connectDB() {
		try {
			model=new VideoModel();
			System.out.println("���� DB����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void evenProc() {
		cbMultiInsert.addActionListener(this);
		bVideoDelete.addActionListener(this);
		bVideoInsert.addActionListener(this);
		bVideoModify.addActionListener(this);
		tfVideoSearch.addActionListener(this);
		
		
		tableVideo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("���콺 Ŭ�� ��ȣ");
				int row=tableVideo.getSelectedRow();
				int col=0;
				String data=(String) tableVideo.getValueAt(row, col);
				int no=Integer.parseInt(data);
				
				try {
					Video vo=model.selectbyPk(no);
					//���� vo�� ���ڵ尡 ��� vo
					selectbyPk(vo);
					
				} catch (Exception e1) {
					
				}
				
			}
		});
	}
	public void selectbyPk(Video vo) {
		tfVideoNum.setText(String.valueOf(vo.getVideoNo()));
		tfVideoTitle.setText(vo.getVideoName());
		tfVideoDirector.setText(vo.getDirector());
		tfVideoActor.setText(vo.getActor());
		taVideoContent.setText(vo.getExp());
		comVideoGenre.setSelectedItem(vo.getGenre());
	}
	
	
	private void initStyle() {
//		tfVideoNum.setEditable(false);
		tfInsertCount.setEditable(false);
	}

	private void addLayout() {
		//VideoView ȭ�鱸��
		tfVideoNum=new JTextField();
		tfVideoTitle=new JTextField();
		tfVideoDirector=new JTextField();
		tfVideoActor=new JTextField();
		
		//�帣�� ���� �迭�� ó��
		String [] cbGenreStr= {"���","�׼�","����","�ڹ̵�","�ô��"};
		comVideoGenre=new JComboBox(cbGenreStr);
		
		taVideoContent=new JTextArea();
		
		cbMultiInsert=new JCheckBox("�����԰�");
		tfInsertCount=new JTextField("1",5);
		
		bVideoInsert=new JButton("�԰�");
		bVideoModify=new JButton("����");
		bVideoDelete=new JButton("����");
		
		String[] cbVideoSearch= {"����","����"};
		comVideoSearch=new JComboBox(cbVideoSearch);
		tfVideoSearch=new JTextField(15);
		
		//���� ���̺� ����
		tbModelVideo=new VideoTableModel();
		tableVideo=new JTable(tbModelVideo);
		tableVideo.setModel(tbModelVideo);

		
		
		//�ǳ�1 ȭ�� ����
		JPanel p_west=new JPanel();
		p_west.setLayout(new BorderLayout());
		
		//�ǳ�2 ȭ�� ����
		JPanel p_west_center=new JPanel();
		p_west_center.setLayout(new BorderLayout());
		
		//�ǳ�3 ȭ�� ����
		JPanel p_west_center_north=new JPanel();
		p_west_center_north.setLayout(new GridLayout(5,2));
		
		//�ǳ�3 �޺� ����
		p_west_center_north.add(new JLabel("���� ��ȣ"));
		p_west_center_north.add(tfVideoNum);
		p_west_center_north.add(new JLabel("�帣"));
		p_west_center_north.add(comVideoGenre);
		p_west_center_north.add(new JLabel("����"));
		p_west_center_north.add(tfVideoTitle);
		p_west_center_north.add(new JLabel("����"));
		p_west_center_north.add(tfVideoDirector);		
		p_west_center_north.add(new JLabel("���"));
		p_west_center_north.add(tfVideoActor);
		
		//�ǳ�4 ȭ�� ����
		JPanel p_west_center_center=new JPanel();
		p_west_center_center.setLayout(new BorderLayout());
		
		p_west_center_center.add(new JLabel("����"),BorderLayout.WEST);
		p_west_center_center.add(taVideoContent,BorderLayout.CENTER);
		
		//�ǳ�3,4�� �ǳ�2�� ����
		p_west_center.add(p_west_center_north,BorderLayout.NORTH);
		p_west_center.add(p_west_center_center,BorderLayout.CENTER);
		
		//border �����
		p_west_center.setBorder(new TitledBorder("���� ���� �Է�"));
		
		//�ǳ�5 west_south
		JPanel p_west_south=new JPanel();
		p_west_south.setLayout(new GridLayout(2,1));
		
		//�ǳ�5 west_south_1
		JPanel west_south_1=new JPanel();
		west_south_1.setLayout(new FlowLayout());
		
		//west_south_1�� �߰�
		west_south_1.add(cbMultiInsert);
		west_south_1.add(tfInsertCount);
		west_south_1.add(new JLabel("��"));
		west_south_1.setBorder(new TitledBorder("���� �Է½� ����"));
		
		//�ǳ�6 west_south_2 ��ư��
		JPanel west_south_2=new JPanel();
		west_south_2.setLayout(new GridLayout(1,3));
		west_south_2.add(bVideoInsert);		
		west_south_2.add(bVideoModify);		
		west_south_2.add(bVideoDelete);
		
		//p_west_south�� west_south_1,2 ����
		p_west_south.add(west_south_1);
		p_west_south.add(west_south_2);
		
		p_west.add(p_west_center,BorderLayout.CENTER);
		p_west.add(p_west_south,BorderLayout.SOUTH);
		
		//���� �ǳ�
		JPanel p_east=new JPanel();
		p_east.setLayout(new BorderLayout());
		
		JPanel p_east_north=new JPanel();
		p_east_north.add(comVideoSearch);
		p_east_north.add(tfVideoSearch);
		p_east_north.setBorder(new TitledBorder("���� �˻�"));
		
		p_east.add(p_east_north,BorderLayout.NORTH);
		p_east.add(new JScrollPane(tableVideo),BorderLayout.CENTER);
		
		setLayout(new GridLayout(1,2));
		add(p_west);
		add(p_east);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("sdf");
		Object evt=e.getSource();
		if (evt==cbMultiInsert) {
			tfInsertCount.setEditable(cbMultiInsert.isSelected());
		}else if (evt==bVideoInsert) {
			InsertVideo();
		}else if (evt==bVideoModify) {
			Modify();
		}else if (evt==bVideoDelete) {
			delete();
		}else if (evt==tfVideoSearch) {
			System.out.println("��~�� ��~��");
			int idx=comVideoSearch.getSelectedIndex();
			String str=tfVideoSearch.getText();
			
			try {
				ArrayList data=model.searchVideo(idx,str);
				
				tbModelVideo.data=data;
				tableVideo.setModel(tbModelVideo);
				tbModelVideo.fireTableDataChanged();
				//������ ���� �����͸� ���̺� �ݿ�
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	private void delete() {
		
			Video vo=new Video();
			//Video Ŭ������ ����(����)�ϱ� ����
			vo.setVideoNo(Integer.parseInt(tfVideoNum.getText()));
			vo.setVideoName(tfVideoTitle.getText());
			vo.setActor(tfVideoActor.getText());
			vo.setDirector(tfVideoDirector.getText());
			vo.setExp(taVideoContent.getText());
			vo.setGenre((String)comVideoGenre.getSelectedItem());
			
			try {
				model.delete(vo);
				JOptionPane.showMessageDialog(null, "���� ����");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	private void Modify() {
		Video vo=new Video();
		//Video Ŭ������ ����(����)�ϱ� ����
		vo.setVideoNo(Integer.parseInt(tfVideoNum.getText()));
		vo.setVideoName(tfVideoTitle.getText());
		vo.setActor(tfVideoActor.getText());
		vo.setDirector(tfVideoDirector.getText());
		vo.setExp(taVideoContent.getText());
		vo.setGenre((String)comVideoGenre.getSelectedItem());
		
		try {
			model.modifyVideo(vo);
			JOptionPane.showMessageDialog(null, "���� �Ϸ�");
			tfVideoNum.setText(null);
			tfVideoActor.setText(null);
			tfVideoDirector.setText(null);
			tfVideoTitle.setText(null);
			taVideoContent.setText(null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void InsertVideo() {
		
//			System.out.println("insertbtn press");
			Video vo=new Video();
			vo.setGenre((String) comVideoGenre.getSelectedItem());
			vo.setActor(tfVideoActor.getText());
			vo.setDirector(tfVideoDirector.getText());
			vo.setVideoName(tfVideoTitle.getText());
			vo.setExp(taVideoContent.getText());
			int count=Integer.parseInt(tfInsertCount.getText());
			//������� vo�� �Է��� �ڷ�(��ȭ����)�� ����
			
			try {
				model.insertVideo(vo,count);
				JOptionPane.showMessageDialog(null, "�԰� �Ϸ�");
				tfVideoNum.setText(null);
				tfVideoActor.setText(null);
				tfVideoDirector.setText(null);
				tfVideoTitle.setText(null);
				taVideoContent.setText(null);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	
	class VideoTableModel extends AbstractTableModel{

		ArrayList data=new ArrayList<>();
		String[] columnNames= {"���� ��ȣ","����","�帣","����","���"};
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp=(ArrayList) data.get(row);
			return temp.get(col);
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		
	}
}
