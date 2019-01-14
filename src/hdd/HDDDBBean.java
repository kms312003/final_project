package hdd;

import org.apache.ibatis.io.Resources;

import graphic.GraphicDBBean;

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

	// Cpu 媛��닔
	public int getHDDCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getHDDCount");
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 由ъ뒪�듃
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

	// Cpu �벑濡�
	public void insertHDD(HDD hdd) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		Long id = hdd.getId();

		Map map = new HashMap();

//		number = (int) sqlSession.selectOne(namespace + ".nextVal");

		hdd.setId(id);

		int result = sqlSession.insert(namespace + ".insert", hdd);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Cpu 媛��졇�삤湲�
	public HDD getHDD(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		HDD hdd = sqlSession.selectOne(namespace + ".getHDD", map);

		sqlSession.close();
		return hdd;
	}

	// Cpu �닔�젙 Get
	public HDD getUpdate(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		HDD hdd = sqlSession.selectOne(namespace + ".getHDD", map);

		sqlSession.close();
		return hdd;
	}

	// Cpu �닔�젙 Post
	public void updateHDD(HDD hdd) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", hdd);
		sqlSession.commit();

		sqlSession.close();
	}

	// Cpu �궘�젣
	public void deleteHDD(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteHDD", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
