package hdd;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import graphic.Graphic;

public class HDDDBBean {

	private static HDDDBBean instance = new HDDDBBean();

	public static HDDDBBean getInstance() {
		return instance;
	}

	private HDDDBBean() {
	}

	private final String namespace = "mapper.hdd";

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

	// HDD 갯수
	public int getHDDCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getHDDCount");
		} finally {
			sqlSession.close();
		}
	}

	// HDD 리스트
	public List getHDDList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getHDDs", map);
		} finally {
			sqlSession.close();
		}
	}

	// HDD 제품리스트 가져오기
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

	// HDD 제품리스트 가져오기
	public List getSearchProductList(int start, int end, String[] productCompanys, String productName, String interFace,
			String diskSize) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("productCompanys", productCompanys);
		map.put("productName", productName);
		map.put("interFace", interFace);
		map.put("diskSize", diskSize);

		System.out.println("map: " + map);
		try {
			return sqlSession.selectList(namespace + ".getSearchProducts", map);
		} finally {
			sqlSession.close();
		}
	}

	// HDD 등록
	public void insertHDD(HDD hdd) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", hdd);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// HDD 가져오기
	public HDD getHDD(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		// sqlSession.update(namespace + ".readCount", map);
		HDD hdd = sqlSession.selectOne(namespace + ".getHDD", map);

		sqlSession.close();
		return hdd;
	}

	// HDD 수정 Get
	public HDD getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		HDD hdd = sqlSession.selectOne(namespace + ".getHDD", map);

		sqlSession.close();
		return hdd;
	}

	// HDD 수정 Post
	public void updateHDD(HDD hdd) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", hdd);
		sqlSession.commit();

		sqlSession.close();
	}

	// HDD 삭제
	public void deleteHDD(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteHDD", map);
		sqlSession.commit();

		sqlSession.close();
	}

	// HDD 디테일 Get
	public HDD getDetail(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		HDD hdd = sqlSession.selectOne(namespace + ".getHDD", map);

		sqlSession.close();
		return hdd;
	}
	
	// 검색된 HDD 리스트 가져오기
	public List getSearchedHDDList(String keyword) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("keyword", keyword);

		try {
			return sqlSession.selectList(namespace + ".getSearchedHDDs", map);
		} finally {
			sqlSession.close();
		}
	}
}
