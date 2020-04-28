package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.Video;

public class VideoModel {
	Connection con;
	public VideoModel() throws Exception {
		con=DBCon.getConnection();
	}
	public Video selectbyPk(int no) throws Exception {
		Video vo=new Video();
		String sql="select * from vinfo where vicode="+no;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while (rs.next()) {
			vo.setVideoNo(Integer.parseInt(rs.getString("VICODE")));
			vo.setActor(rs.getString("ACTOR"));
			vo.setDirector(rs.getString("DIRECTOR"));
			vo.setGenre(rs.getString("GENRE"));
			vo.setVideoName(rs.getString("TITLE"));
			vo.setExp(rs.getString("DETAIL"));
		}
		rs.close();
		pstmt.close();
		return vo;
	}
	public void insertVideo(Video dao, int count) throws Exception {
		con.setAutoCommit(false);
		for (int i = 0; i < count; i++) {
			//PreparedStatement style
//			String sql1="INSERT INTO vinfo(vicode,title,genre,director,actor,detail)" + 
//					"VALUES(seq_vicode.nextval,?,?,?,?,?)";
//			PreparedStatement ps1=con.prepareStatement(sql1);
//			ps1.setString(1, dao.getVideoName());
//			ps1.setString(2, dao.getGenre());
//			ps1.setString(3, dao.getDirector());
//			ps1.setString(4, dao.getActor());
//			ps1.setString(5, dao.getExp());
			
			//Statement style
			Statement stmt=con.createStatement();
			String tit=dao.getVideoName();
			String gen=dao.getGenre();
			String dir=dao.getDirector();
			String act=dao.getActor();
			String exp=dao.getExp();
			
			String sql1="INSERT INTO vinfo(vicode,title,genre,director,actor,detail)" + 
					"VALUES(seq_vicode.nextval,'"+tit+"','"+gen+"','"+dir+"','"+act+"','"+exp+"')";
			
			
			//실행
//			int r1=ps1.executeUpdate();//pstmt
			int r1=stmt.executeUpdate(sql1);//stmt
			
			System.out.println("sql1 : "+sql1);
			if (r1!=1) {
				con.rollback();
			}
			con.commit();
			System.out.println("입력 완료");
//			ps1.close();
			stmt.close();
		}
		con.setAutoCommit(true);
	}
	public void modifyVideo(Video vo) throws Exception {
		String sql="UPDATE VINFO SET TITLE=?,genre=?,"
				+"director=?,ACTOR=?,detail=? WHERE VICODE=?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		
		if (vo.getVideoName()==null) {
			vo.setVideoName("미정");
		}
		
		
		ps.setString(1, vo.getVideoName());
		ps.setString(2, vo.getGenre());
		ps.setString(3, vo.getDirector());
		ps.setString(4, vo.getActor());
		ps.setString(5, vo.getExp());
		ps.setInt(6, vo.getVideoNo());
		
		ps.executeUpdate();//실행
		ps.close();
	}
	public void delete(Video vo) throws Exception {
		con.setAutoCommit(false);
		String sql="delete from vinfo where vicode=?";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, vo.getVideoNo());
		
		int r1=ps.executeUpdate();
		if (r1!=1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);
		
	}
	public ArrayList searchVideo(int idx, String str) throws Exception {
		//데이터베이스 video 테이블에서 검색 -> 담아서(arraylist)담아서
		//JTable로 전달
		String[] key= {"title","director"};
		String sql="SELECT VICODE,TITLE,GENRE,DIRECTOR,ACTOR FROM VINFO"
				+ " where "+key[idx]+" like '%"+str+"%'";
		System.out.println(sql);
		
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		ArrayList data=new ArrayList<>();
		while (rs.next()) {
			ArrayList temp=new ArrayList<>();
			temp.add(rs.getString("vicode"));
			temp.add(rs.getString("title"));
			temp.add(rs.getString("genre"));
			temp.add(rs.getString("director"));
			temp.add(rs.getString("actor"));
			data.add(temp);
		}
		rs.close();
		ps.close();
	
		return data;
	}
}
