package ssd;

import org.apache.ibatis.io.Resources;

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

	// Cpu 媛��닔
	public int getSSDCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getSSDCount");
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 由ъ뒪�듃
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

	// Cpu �벑濡�
	public void insertSSD(SSD ssd) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		Long id = ssd.getId();

		Map map = new HashMap();

//		number = (int) sqlSession.selectOne(namespace + ".nextVal");

		ssd.setId(id);

		int result = sqlSession.insert(namespace + ".insert", ssd);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Cpu 媛��졇�삤湲�
	public SSD getSSD(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		SSD ssd = sqlSession.selectOne(namespace + ".getSSD", map);

		sqlSession.close();
		return ssd;
	}

	// Cpu �닔�젙 Get
	public SSD getUpdate(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		SSD ssd = sqlSession.selectOne(namespace + ".getSSD", map);

		sqlSession.close();
		return ssd;
	}

	// Cpu �닔�젙 Post
	public void updateSSD(SSD ssd) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", ssd);
		sqlSession.commit();

		sqlSession.close();
	}

	// Cpu �궘�젣
	public void deleteSSD(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteSSD", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
