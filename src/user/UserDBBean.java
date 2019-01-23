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

import cpu.Cpu;

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

	// 유저 총 카운트
	public int getUserCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getUserCount");
		} finally {
			sqlSession.close();
		}
	}

	// 유저 리스트 가져오기
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

	// 유저 가져오기
	public User getUser(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		sqlSession.update(namespace + ".readCount", map);
		User user = sqlSession.selectOne(namespace + ".getUser", map);

		sqlSession.close();
		return user;
	}

	// 유저 수정 Get
	public User getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		User user = sqlSession.selectOne(namespace + ".getUser", map);

		sqlSession.close();
		return user;
	}

	// 유저 수정 Post
	public void updateUser(User user) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", user);
		sqlSession.commit();

		sqlSession.close();
	}

	// 유저 삭제
	public void deleteUser(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteUser", map);
		sqlSession.commit();

		sqlSession.close();
	}

	// 유저 등록
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();

		sqlSession.insert(namespace + ".insert", user);
		sqlSession.commit();
		sqlSession.close();
	}

	// 로그인 체크
	public int loginCheck(String email, String password) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		Map map = new HashMap();
		map.put("email", email);
		map.put("password", password);
		int x = -1;
		String dbPassword = sqlSession.selectOne(namespace + ".getPassword", map);
		
		if (dbPassword.equals(password)) {
			x = 1;
		} else if (!dbPassword.equals(password)) {
			x = 0;
		} else {
			x = -1;
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
			x = 0; // �ߺ�email o = ȸ������ ����
		} else if (!dbEmail.equals(email)) {
			x = 1; // �ߺ�email x = ȸ������ ����
		}
		System.out.println("check: " + x);
		return x;
	}
}
