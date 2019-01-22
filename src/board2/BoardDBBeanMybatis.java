package board2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class BoardDBBeanMybatis {
	
	private static BoardDBBeanMybatis instance = new BoardDBBeanMybatis();
	
	public static BoardDBBeanMybatis getInstance() {
		return instance;
	}
	
	private final String namespace = "mapper.board2";
	
	private SqlSessionFactory getSqlSessionFactory() {
		String resource = "xml/mybatis-config.xml";
		InputStream inputStream;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public int getArticleCount(String boardid) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("boardid", boardid);
		try {
			return (int)sqlSession.selectOne(namespace
					+ ".getArticleCount", map);
		} finally {
			sqlSession.close();
		}
	}
	
	public List getArticleList(int start,int end,String boardid) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("boardid", boardid);
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace
					+ ".getArticles", map);
		} finally {
			sqlSession.close();
		}
	}
	
	public void insertArticle(BoardDataBean article,String boardid) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		int number = 0;
		
		int num = article.getNum();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level(); // �亯���� �Ϲݱ����� ����
		Map map = new HashMap();
		
		
		//number = (int)sqlSession.selectOne(namespace + ".nextVal");
		
		if(num !=0) {
			map.put("ref", ref);
			map.put("re_step", re_step);	
			map.put("boardid", article.getBoardid());
			
			sqlSession.update(namespace + ".insertUpdate",map);
			re_step = re_step+1;
			re_level = re_level+1;
		}else {
			ref=number;
			re_step=0;
			re_level=0;
		}
		
		article.setNum(number);
		article.setRef(ref);
		article.setRe_level(re_level);
		article.setRe_step(re_step);
		
		int result = sqlSession.insert(namespace + ".insert",article);
		
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);

	}
	public BoardDataBean getArticle(int num) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("num", num);
		
		sqlSession.update(namespace+".readCount",map);
		BoardDataBean article = (BoardDataBean)sqlSession.selectOne(namespace+".getArticle",map);
		sqlSession.close();
		return article;
	}
	
	public BoardDataBean getUpdate(int num) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		Map map = new HashMap();
		map.put("num", num);
		
		BoardDataBean article = (BoardDataBean)sqlSession.selectOne(namespace+".getArticle",map);
		sqlSession.close();
		return article;
	}
	
	public int updateArticle(BoardDataBean article) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("num", article.getNum());
		int x = -1;
		
		String dbPasswd = sqlSession.selectOne(namespace+".getPasswd",map);
		
		if(dbPasswd.equals(article.getPasswd())) {
			
			x = (int)sqlSession.update(namespace+".update",article);
			sqlSession.commit();
		}
		sqlSession.close();
		return x;
	}
	
	public int deleteArticle(int num, String passwd) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("num", num);
		int x = -1;
		
		String dbPasswd = sqlSession.selectOne(namespace+".getPasswd",map);
		
		if(dbPasswd.equals(passwd)) {
			
			x = (int)sqlSession.delete(namespace+".delete",map);
			sqlSession.commit();
		}
		sqlSession.close();
		return x;
	}
}
