package board2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	private BoardDBBean() {}
	
	private Connection getConnection() throws Exception{
		Connection con = null;
		try {
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbId = "scott";
			String dbPass = "1111";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(jdbcUrl,dbId,dbPass);
		}catch(Exception e) {
			e.printStackTrace();
		}return con;
	}
	//----------------------------------------------------------
	public void temp() throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			
			
			
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
	}
	//-----------------------------------------------------------
	
	public void insertArticle(BoardDataBean article,String boardid) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;
		String sql="";
		
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level(); // 답변인지 일반글인지 구분
		
		try {
			pstmt = conn.prepareStatement("select boardSer.nextval from dual");
			rs = pstmt.executeQuery();
			if(rs.next()) number=rs.getInt(1);
			
			if(num!=0) { //답글
				sql = "update board2 set re_step=re_step+1"
						+ "where ref=? and re_step>? and boardid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.setString(3, boardid);
				pstmt.executeUpdate();
				
				re_step = re_step+1;
				re_level = re_level+1;
				
			}else { //새글
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			sql = "insert into board2(num,writer,email,subject,passwd,reg_date,";
			sql+= "ref,re_step,re_level,content,ip,boardid,filename,filesize)"
					+"values(?,?,?,?,?,sysdate,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2,article.getWriter());
			pstmt.setString(3,article.getEmail());
			pstmt.setString(4,article.getSubject());
			pstmt.setString(5,article.getPasswd());
			pstmt.setInt(6, ref); 
			pstmt.setInt(7,re_step);
			pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10,article.getIp());
			pstmt.setString(11,boardid);
			pstmt.setString(12, article.getFilename());
			pstmt.setInt(13, article.getFilesize());
			pstmt.executeUpdate();
			
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
	}
	
	public int getArticleCount(String boardid) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			pstmt = conn.prepareStatement("select nvl(count(*),0) from board2 "
					+ " where boardid = ?");
			pstmt.setString(1, boardid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
			
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
		return x;
	}
	
	public List getArticleList(int start,int end,String boardid) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query="";
		
		List articleList = null;
		try {
			query = "select * from "
					+"(select rownum rnum, a.*"
					+"from (select num,writer,email,subject,passwd,"
					+"reg_date,readcount,ref,re_step,re_level,content,"
					+"ip from board2 where boardid = ? order by ref desc,re_step)"
					+"a) where rnum between ? and ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList();
					do {
						BoardDataBean article = new BoardDataBean();
						article.setNum(rs.getInt("num"));
						article.setWriter(rs.getString("writer"));
						article.setEmail(rs.getString("email"));
						article.setSubject(rs.getString("subject"));
						article.setPasswd(rs.getString("passwd"));
						article.setReg_date(rs.getTimestamp("reg_date"));
						article.setReadcount(rs.getInt("readcount"));
						article.setRef(rs.getInt("ref"));
						article.setRe_step(rs.getInt("re_step"));
						article.setRe_level(rs.getInt("re_level"));
						article.setContent(rs.getString("content"));
						article.setIp(rs.getString("ip"));
						
						articleList.add(article);
					
				
				}while(rs.next());
			}
			
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
		return articleList;
	}
	
	public BoardDataBean getArticle(int num) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		try {
			pstmt = conn.prepareStatement("select * from board2 where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardDataBean();
				
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				
				article.setFilename(rs.getString("filename"));
				article.setFilesize(rs.getInt("filesize"));
				
				article.setIp(rs.getString("ip"));
			}
			pstmt = conn.prepareStatement("update board2 set readcount = readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
				
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
		return article;
	}
	
	public BoardDataBean getUpdate(int num) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		try {
			pstmt = conn.prepareStatement("select * from board2 where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardDataBean();
				
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		
				
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
		return article;
	}	
	
	public int updateArticle(BoardDataBean article) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPasswd = "";
		String sql="";
		int x = -1;
		try {
			pstmt = conn.prepareStatement("select passwd from board2 where num=?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()) dbPasswd = rs.getString(1);
			
			if(dbPasswd.equals(article.getPasswd())) {
			
			sql = "update board2 set writer=?, email=?, subject=?, passwd=?";
			sql+= ",content=? where num=?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1,article.getWriter());
			pstmt.setString(2,article.getEmail());
			pstmt.setString(3,article.getSubject());
			pstmt.setString(4,article.getPasswd());
			pstmt.setString(5, article.getContent());
			pstmt.setInt(6, article.getNum()); 
			pstmt.executeUpdate();
			x=1;
			}else {
				x=0;
			}
			
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
		return x;
	}
	
	
	public int deleteArticle(int num, String passwd) throws Exception{
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPasswd = "";
		String sql="";
		int x = -1;
		try {
			pstmt = conn.prepareStatement("select passwd from board2 where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) dbPasswd = rs.getString(1);
			
			if(dbPasswd.equals(passwd)) {
			sql = "delete from board2 where num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num); 
			pstmt.executeUpdate();
			x=1;
			}else {
				x=0;
			}
			
		}catch(Exception ex) {ex.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex) {}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex) {}
			if(conn!=null)
				try{conn.close();}catch(SQLException ex) {}
		}
		return x;
	}
	
}
