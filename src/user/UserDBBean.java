package user;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserDBBean {
	private static UserDBBean instance = new UserDBBean();

	public static UserDBBean getInstance() {
		return instance;
	}

	private UserDBBean() {

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

	// 유저수 카운팅
	public int getUserCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getUserCount");
		} finally {
			sqlSession.close();
		}
	}

	// 유저 전체 리스트 보기
	public List getUserList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);

		try {
			return sqlSession.selectList(namespace + ".getUsers", map);
		} finally {
			sqlSession.close();
		}
	}

	// 해당 id 유저 정보 가져오기
	public User getUser(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		sqlSession.update(namespace + ".readCount", map);
		User user = sqlSession.selectOne(namespace + ".getUser", map);

		sqlSession.close();
		return user;
	}
	//해당 이메일 유저 정보 가져오기
	public User getUserE(String email) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("email", email);
	
		User user = sqlSession.selectOne(namespace + ".getUserEmail", map);
		sqlSession.close();
		return user;
	}
	// 해당 id 유저 정보 업데이트
	public User getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		User user = sqlSession.selectOne(namespace + ".getUser", map);

		sqlSession.close();
		return user;
	}

	// 유저 정보 업데이트
	public void updateUser(User user) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", user);
		sqlSession.commit();
		System.out.println("update user"+user);
		sqlSession.close();
	}

	// 해당 id 유저 정보 삭제
	public void deleteUser(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteUser", map);
		sqlSession.commit();

		sqlSession.close();
	}

	// 유저 정보 넣기
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();

		sqlSession.insert(namespace + ".insert", user);
		sqlSession.commit();
		sqlSession.close();
	}

	// 해당 이메일에 해당하는 비밀번호 체크
	public int loginCheck(String email, String password) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("email", email);
		map.put("password", password);
		int x = -1;
		String dbPassword = sqlSession.selectOne(namespace + ".getPassword", map);

		if (dbPassword == null) {
			x = -1;
		} else if (dbPassword.equals(password)) {
			x = 1;
		} else if (!dbPassword.equals(password)) {
			x = 0;
		}
		
		return x;
	}

	// 이메일 중복 체크
	public int emailCheck(String email) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();
		map.put("email", email);
		int x = 0;
		String dbEmail = sqlSession.selectOne(namespace + ".getEmail", map);
		System.out.println("email: " + email);
		System.out.println("dbEmail: " + dbEmail);
		if (dbEmail == null) {
			x = 1;
		} else if (dbEmail.equals(email)) {
			x = 0; // 이메일이 중복될 때
		} else if (!dbEmail.equals(email)) {
			x = 1; // 이메일이 중복되지 않을 때
		}
		System.out.println("check: " + x);
		return x;
	}
}
