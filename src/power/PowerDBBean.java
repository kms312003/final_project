package power;

import org.apache.ibatis.io.Resources;

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

	// Cpu 媛��닔
	public int getPowerCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getPowerCount");
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 由ъ뒪�듃
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

	// Cpu �벑濡�
	public void insertPower(Power power) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		Long id = power.getId();

		Map map = new HashMap();

//		number = (int) sqlSession.selectOne(namespace + ".nextVal");

		power.setId(id);

		int result = sqlSession.insert(namespace + ".insert", power);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Cpu 媛��졇�삤湲�
	public Power getPower(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Power power = sqlSession.selectOne(namespace + ".getPower", map);

		sqlSession.close();
		return power;
	}

	// Cpu �닔�젙 Get
	public Power getUpdate(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Power power = sqlSession.selectOne(namespace + ".getPower", map);

		sqlSession.close();
		return power;
	}

	// Cpu �닔�젙 Post
	public void updatePower(Power power) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", power);
		sqlSession.commit();

		sqlSession.close();
	}

	// Cpu �궘�젣
	public void deletePower(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deletePower", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
