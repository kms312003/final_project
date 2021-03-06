package cpu;

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

public class CpuDBBean {

	private static CpuDBBean instance = new CpuDBBean();

	public static CpuDBBean getInstance() {
		return instance;
	}

	private CpuDBBean() {
	}

	private final String namespace = "mapper.cpu";

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

	// Cpu 갯수
	public int getCpuCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getCpuCount");
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 리스트 가져오기
	public List getCpuList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getCpus", map);
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 제품리스트 가져오기
	public List getProductList(int start, int end, String orderby, String sql) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("orderby", orderby);
		map.put("sql", sql);
		
		try {
			return sqlSession.selectList(namespace + ".getProducts", map);
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 제품리스트 가져오기
	public List getSearchProductList(int start, int end, String[] productCompanys, String brand, String socket, String core)
			throws Exception {
		
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("productCompanys", productCompanys);
		map.put("brand", brand);
		map.put("socket", socket);
		map.put("core", core);

		System.out.println("map: " + map);
		try {
			return sqlSession.selectList(namespace + ".getSearchProducts", map);
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

	// Cpu 디테일 Get
	public Cpu getDetail(int id) {
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
	
	// 검색된 Cpu 리스트 가져오기
	public List getSearchedCpuList(String keyword) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("keyword", keyword);

		try {
			return sqlSession.selectList(namespace + ".getSearchedCpus", map);
		} finally {
			sqlSession.close();
		}
	}
}