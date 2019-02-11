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

	// 占쎌�占쏙옙 �룯占� 燁삳똻�뒲占쎈뱜
	public int getUserCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getUserCount");
		} finally {
			sqlSession.close();
		}
	}

	// 占쎌�占쏙옙 �뵳�딅뮞占쎈뱜 揶쏉옙占쎌죬占쎌궎疫뀐옙
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

	// 占쎌�占쏙옙 揶쏉옙占쎌죬占쎌궎疫뀐옙
	public User getUser(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		sqlSession.update(namespace + ".readCount", map);
		User user = sqlSession.selectOne(namespace + ".getUser", map);

		sqlSession.close();
		return user;
	}
	//占쎌돳占쎌뜚占쎌젟癰귨옙 揶쏉옙占쎌죬占쎌궎疫뀐옙
	public User getUserE(String email) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("email", email);
	
		User user = sqlSession.selectOne(namespace + ".getUserEmail", map);
		System.out.println("method user:"+user);
		sqlSession.close();
		return user;
	}
	// 占쎌�占쏙옙 占쎈땾占쎌젟 Get
	public User getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		User user = sqlSession.selectOne(namespace + ".getUser", map);

		sqlSession.close();
		return user;
	}

	// 占쎌�占쏙옙 占쎈땾占쎌젟 Post
	public void updateUser(User user) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", user);
		sqlSession.commit();
		System.out.println("update user"+user);
		sqlSession.close();
	}

	// 占쎌�占쏙옙 占쎄텣占쎌젫
	public void deleteUser(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteUser", map);
		sqlSession.commit();

		sqlSession.close();
	}

	// 占쎌�占쏙옙 占쎈쾻嚥∽옙
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Map map = new HashMap();

		sqlSession.insert(namespace + ".insert", user);
		sqlSession.commit();
		sqlSession.close();
	}

	// 嚥≪뮄�젃占쎌뵥 筌ｋ똾寃�
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

	// 占쎌뵠筌롫뗄�뵬 餓λ쵎�궗 筌ｋ똾寃�
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
			x = 0; // �뜝�뙥釉앹삕email o = �쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		} else if (!dbEmail.equals(email)) {
			x = 1; // �뜝�뙥釉앹삕email x = �쉶�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		}
		System.out.println("check: " + x);
		return x;
	}
}
