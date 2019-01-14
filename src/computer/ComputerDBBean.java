package computer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;

public class ComputerDBBean {
	
	private static ComputerDBBean instance = new ComputerDBBean();

	public static ComputerDBBean getInstance() {
		return instance;
	}

	private ComputerDBBean() {
	}

	private final String namespace = "mapper.computer";

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
	public int getComputerCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getComputerCount");
		} finally {
			sqlSession.close();
		}
	}

	// Cpu 由ъ뒪�듃
	public List getComputerList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getComputers", map);
		} finally {
			sqlSession.close();
		}
	}

	// Cpu �벑濡�
	public void insertComputer(Computer computer) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		Long id = computer.getId();

		Map map = new HashMap();

//		number = (int) sqlSession.selectOne(namespace + ".nextVal");

		computer.setId(id);

		int result = sqlSession.insert(namespace + ".insert", computer);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Cpu 媛��졇�삤湲�
	public Computer getComputer(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		Computer computer = sqlSession.selectOne(namespace + ".getComputer", map);

		sqlSession.close();
		return computer;
	}

	// Cpu �닔�젙 Get
	public Computer getUpdate(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Computer computer = sqlSession.selectOne(namespace + ".getComputer", map);

		sqlSession.close();
		return computer;
	}

	// Cpu �닔�젙 Post
	public void updateComputer(Computer computer) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", computer);
		sqlSession.commit();

		sqlSession.close();
	}

	// Cpu �궘�젣
	public void deleteComputer(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteComputer", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
