package graphic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;

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

	// Cpu 媛��닔
	public int getGraphicCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getGraphicCount");
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 由ъ뒪�듃
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

	// Cpu �벑濡�
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

	// Cpu 媛��졇�삤湲�
	public Graphic getGraphic(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Graphic graphic = sqlSession.selectOne(namespace + ".getGraphic", map);

		sqlSession.close();
		return graphic;
	}

	// Cpu �닔�젙 Get
	public Graphic getUpdate(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Graphic graphic = sqlSession.selectOne(namespace + ".getGraphic", map);

		sqlSession.close();
		return graphic;
	}

	// Cpu �닔�젙 Post
	public void updateGraphic(Graphic graphic) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", graphic);
		sqlSession.commit();

		sqlSession.close();
	}

	// Cpu �궘�젣
	public void deleteGraphic(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteGraphic", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
