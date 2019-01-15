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

	// Graphic 등록
	public void insertGraphic(Graphic graphic) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		Long id = graphic.getId();

		Map map = new HashMap();

//		number = (int) sqlSession.selectOne(namespace + ".nextVal");

		graphic.setId(id);

		int result = sqlSession.insert(namespace + ".insert", graphic);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Graphic 가져오기
	public Graphic getGraphic(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Graphic graphic = sqlSession.selectOne(namespace + ".getGraphic", map);

		sqlSession.close();
		return graphic;
	}

	// Graphic 수정 Get
	public Graphic getUpdate(Long id) {
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
	public void deleteGraphic(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteGraphic", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
