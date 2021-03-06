package ssd;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ram.Ram;

public class SSDDBBean {

	private static SSDDBBean instance = new SSDDBBean();

	public static SSDDBBean getInstance() {
		return instance;
	}

	private SSDDBBean() {
	}

	private final String namespace = "mapper.ssd";

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

	// SSD 갯수
	public int getSSDCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getSSDCount");
		} finally {
			sqlSession.close();
		}
	}

	// SSD 리스트 가져오기
	public List getSSDList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getSSDs", map);
		} finally {
			sqlSession.close();
		}
	}

	// SSD 제품리스트 가져오기
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

	// SSD 제품리스트 가져오기
	public List getSearchProductList(int start, int end, String[] productCompanys, String productName,
			String diskType, String interFace) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("productCompanys", productCompanys);
		map.put("productName", productName);
		map.put("diskType", diskType);
		map.put("interFace", interFace);

		System.out.println("map: " + map);
		try {
			return sqlSession.selectList(namespace + ".getSearchProducts", map);
		} finally {
			sqlSession.close();
		}
	}

	// SSD 등록
	public void insertSSD(SSD ssd) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", ssd);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// SSD 가져오기
	public SSD getSSD(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		// sqlSession.update(namespace + ".readCount", map);
		SSD ssd = sqlSession.selectOne(namespace + ".getSSD", map);

		sqlSession.close();
		return ssd;
	}

	// SSD 수정 Get
	public SSD getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		SSD ssd = sqlSession.selectOne(namespace + ".getSSD", map);

		sqlSession.close();
		return ssd;
	}

	// SSD 수정 Post
	public void updateSSD(SSD ssd) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", ssd);
		sqlSession.commit();

		sqlSession.close();
	}

	// SSD 삭제
	public void deleteSSD(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteSSD", map);
		sqlSession.commit();

		sqlSession.close();
	}

	// SSD 디테일 Get
	public SSD getDetail(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		SSD ssd = sqlSession.selectOne(namespace + ".getSSD", map);

		sqlSession.close();
		return ssd;
	}
	
	// 검색된 SSD 리스트 가져오기
	public List getSearchedSSDList(String keyword) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("keyword", keyword);

		try {
			return sqlSession.selectList(namespace + ".getSearchedSSDs", map);
		} finally {
			sqlSession.close();
		}
	}
}
