package user;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserDBBean {
	private static UserDBBean instance = new UserDBBean();
	
	public static UserDBBean getInstance(){
		return instance;
	}
	
	private UserDBBean(){
		
	}
	
	private final String namespace = "mapper.user";
	
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
	
	//회원가입 정보 입력
	public void insertUser(User user) throws Exception{
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		
	    sqlSession.insert(namespace + ".insert", user);
		sqlSession.commit();
		sqlSession.close();
	}
	
	//로그인 체크
	public int loginCheck(String email, String password){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("password", password);
		int x=-1;
		String dbPassword = sqlSession.selectOne(namespace + ".getPassword", map);
		if(dbPassword.equals(password)){
			x=1;
		}else if(!dbPassword.equals(password)){
			x=0;
		}else{
			x=-1;
		}
		
		return x;
	}
	
	//회원가입 이메일 중복 체크
	public int emailCheck(String email){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("email", email);
		int x=0;
		String dbEmail = sqlSession.selectOne(namespace + ".getEmail", map);
		System.out.println("email: " + email);
		System.out.println("dbEmail: " + dbEmail);
		if(dbEmail==null){
			x=1;
		}else if(dbEmail.equals(email)){
			x=0; //중복email o = 회원가입 실패
		}else if(!dbEmail.equals(email)){
			x=1; //중복email x = 회원가입 성공
		}
		System.out.println("check: " + x);
		return x;
	}
}
