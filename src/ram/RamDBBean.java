package ram;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import power.Power;

public class RamDBBean {

	private static RamDBBean instance = new RamDBBean();

	public static RamDBBean getInstance() {
		return instance;
	}

	private RamDBBean() {
	}

	private final String namespace = "mapper.ram";

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

	// Ram 갯수
	public int getRamCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getRamCount");
		} finally {
			sqlSession.close();
		}
	}

	// Ram 리스트 가져오기
	public List getRamList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getRams", map);
		} finally {
			sqlSession.close();
		}
	}

	// Ram 등록
	public void insertRam(Ram ram) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", ram);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Ram 가져오기
	public Ram getRam(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Ram ram = sqlSession.selectOne(namespace + ".getRam", map);

		sqlSession.close();
		return ram;
	}

	// Ram 수정 Get
	public Ram getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Ram ram = sqlSession.selectOne(namespace + ".getRam", map);

		sqlSession.close();
		return ram;
	}

	// Ram 수정 Post
	public void updateRam(Ram ram) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", ram);
		sqlSession.commit();

		sqlSession.close();
	}

	// Ram 삭제
	public void deleteRam(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteRam", map);
		sqlSession.commit();

		sqlSession.close();
	}
	
	// Ram 디테일 Get
	public Ram getDetail(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Ram ram = sqlSession.selectOne(namespace + ".getRam", map);

		sqlSession.close();
		return ram;
	}
}
