package computer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ComputerDBBean {
	
	private static ComputerDBBean instance = new ComputerDBBean();

	public static ComputerDBBean getInstance() {
		return instance;
	}

	private ComputerDBBean() {
	}

	private final String namespace = "mapper.computer";

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

	// Computer 갯수
	public int getComputerCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getComputerCount");
		} finally {
			sqlSession.close();
		}
	}

	// Computer 리스트 가저오기
	public List getComputerList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getComputers", map);
		} finally {
			sqlSession.close();
		}
	}

	// Computer 등록
	public void insertComputer(Computer computer) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", computer);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Computer 가져오기
	public Computer getComputer(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Computer computer = sqlSession.selectOne(namespace + ".getComputer", map);

		sqlSession.close();
		return computer;
	}

	// Computer 수정 Get
	public Computer getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Computer computer = sqlSession.selectOne(namespace + ".getComputer", map);

		sqlSession.close();
		return computer;
	}

	// Computer 수정 Post
	public void updateComputer(Computer computer) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", computer);
		sqlSession.commit();

		sqlSession.close();
	}

	// Computer 삭제
	public void deleteComputer(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteComputer", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
