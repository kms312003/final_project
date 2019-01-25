package power;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mainboard.MainBoard;

public class PowerDBBean {

	private static PowerDBBean instance = new PowerDBBean();

	public static PowerDBBean getInstance() {
		return instance;
	}

	private PowerDBBean() {
	}

	private final String namespace = "mapper.power";

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

	// Power 갯수
	public int getPowerCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getPowerCount");
		} finally {
			sqlSession.close();
		}
	}

	// Power 리스트 가져오기
	public List getPowerList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getPowers", map);
		} finally {
			sqlSession.close();
		}
	}

	// Power 등록
	public void insertPower(Power power) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", power);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Power 가져오기
	public Power getPower(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Power power = sqlSession.selectOne(namespace + ".getPower", map);

		sqlSession.close();
		return power;
	}

	// Power 수정 Get
	public Power getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Power power = sqlSession.selectOne(namespace + ".getPower", map);

		sqlSession.close();
		return power;
	}

	// Power 수정 Post
	public void updatePower(Power power) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", power);
		sqlSession.commit();

		sqlSession.close();
	}

	// Power 삭제
	public void deletePower(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deletePower", map);
		sqlSession.commit();

		sqlSession.close();
	}
	
	// Power 디테일 Get
	public Power getDetail(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Power power = sqlSession.selectOne(namespace + ".getPower", map);

		sqlSession.close();
		return power;
	}
}
