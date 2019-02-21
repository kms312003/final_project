package graphic;

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

public class GraphicDBBean {

	private static GraphicDBBean instance = new GraphicDBBean();

	public static GraphicDBBean getInstance() {
		return instance;
	}

	private GraphicDBBean() {
	}

	private final String namespace = "mapper.graphic";

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

	// Graphic 갯수
	public int getGraphicCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getGraphicCount");
		} finally {
			sqlSession.close();
		}
	}

	// Graphic 리스트 가져오기
	public List getGraphicList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getGraphics", map);
		} finally {
			sqlSession.close();
		}
	}

	// Graphic 제품리스트 가져오기
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

	// Graphic 제품리스트 가져오기
	public List getSearchProductList(int start, int end, String[] productCompanys, String productName, String chipSetGroup,
			String interFace) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("productCompanys", productCompanys);
		map.put("productName", productName);
		map.put("chipSetGroup", chipSetGroup);
		map.put("interFace", interFace);

		System.out.println("map: " + map);
		try {
			return sqlSession.selectList(namespace + ".getSearchProducts", map);
		} finally {
			sqlSession.close();
		}
	}

	// Graphic 등록
	public void insertGraphic(Graphic graphic) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", graphic);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Graphic 가져오기
	public Graphic getGraphic(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		// sqlSession.update(namespace + ".readCount", map);
		Graphic graphic = sqlSession.selectOne(namespace + ".getGraphic", map);

		sqlSession.close();
		return graphic;
	}

	// Graphic 수정 Get
	public Graphic getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Graphic graphic = sqlSession.selectOne(namespace + ".getGraphic", map);

		sqlSession.close();
		return graphic;
	}

	// Graphic 수정 Post
	public void updateGraphic(Graphic graphic) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", graphic);
		sqlSession.commit();

		sqlSession.close();
	}

	// Graphic 삭제
	public void deleteGraphic(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteGraphic", map);
		sqlSession.commit();

		sqlSession.close();
	}

	// Graphic 디테일 Get
	public Graphic getDetail(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Graphic graphic = sqlSession.selectOne(namespace + ".getGraphic", map);

		sqlSession.close();
		return graphic;
	}
}
