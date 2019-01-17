package shop.basket;

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
import cpu.CpuDBBean;

public class BasketDBBean {

	private static BasketDBBean instance = new BasketDBBean();

	public static BasketDBBean getInstance() {
		return instance;
	}

	private BasketDBBean() {
	}

	private final String namespace = "mapper.basket";

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

	// 로그인한 유저 장바구니 상품 갯수
	public List getBaskets(int userId, int start, int end) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("userId", userId);
		map.put("start", start);
		map.put("end", end);
		
		try {
			return sqlSession.selectList(namespace + ".getBaskets", map);
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 등록
	public void insertCpu(Cpu cpu) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", cpu);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Cpu 가져오기
	public Cpu getCpu(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Cpu cpu = sqlSession.selectOne(namespace + ".getCpu", map);

		sqlSession.close();
		return cpu;
	}

	// Cpu 수정 Get
	public Cpu getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Cpu cpu = sqlSession.selectOne(namespace + ".getCpu", map);

		sqlSession.close();
		return cpu;
	}

	// Cpu 수정 Post
	public void updateCpu(Cpu cpu) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", cpu);
		sqlSession.commit();

		sqlSession.close();
	}

	// Cpu 삭제
	public void deleteCpu(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteCpu", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
