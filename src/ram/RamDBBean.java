package ram;

import org.apache.ibatis.io.Resources;

public class RamDBBean {

	private static RamDBBean instance = new RamDBBean();

	public static RamDBBean getInstance() {
		return instance;
	}

	private RamDBBean() {
	}

	private final String namespace = "mapper.ram";

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
	public int getRamCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getRamCount");
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 由ъ뒪�듃
	public List getRamList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getRams", map);
		} finally {
			sqlSession.close();
		}
	}

	// Cpu �벑濡�
	public void insertRam(Ram ram) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		Long id = ram.getId();

		Map map = new HashMap();

//		number = (int) sqlSession.selectOne(namespace + ".nextVal");

		ram.setId(id);

		int result = sqlSession.insert(namespace + ".insert", ram);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Cpu 媛��졇�삤湲�
	public Ram getRam(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Ram ram = sqlSession.selectOne(namespace + ".getRam", map);

		sqlSession.close();
		return ram;
	}

	// Cpu �닔�젙 Get
	public Ram getUpdate(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Ram ram = sqlSession.selectOne(namespace + ".getRam", map);

		sqlSession.close();
		return ram;
	}

	// Cpu �닔�젙 Post
	public void updateRam(Ram ram) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", ram);
		sqlSession.commit();

		sqlSession.close();
	}

	// Cpu �궘�젣
	public void deleteRam(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteRam", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
