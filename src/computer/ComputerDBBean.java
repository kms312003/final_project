package computer;

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

	// Computer 갯수
	public int getComputerCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getComputerCount");
		} finally {
			sqlSession.close();
		}
	}

	// 카테고리별 Computer 갯수
	public int getCategoryCount(int category) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("category", category);

		try {
			return (int) sqlSession.selectOne(namespace + ".getCategoryCount", map);
		} finally {
			sqlSession.close();
		}
	}

	// Computer 리스트 가저오기
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

	// 카테고리별 Computer 리스트 가저오기
	public List getCategoryList(int start, int end, int category) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("category", category);

		try {
			return sqlSession.selectList(namespace + ".getCategorys", map);
		} finally {
			sqlSession.close();
		}
	}

	// Computer 제품리스트 가져오기
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

	// Computer 제품리스트 가져오기
	public List getSearchProductList(int start, int end, String category, String cpu, String mainBoard,
			String ram, String vga, String hdd) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("category", category);
		map.put("cpu", cpu);
		map.put("mainBoard", mainBoard);
		map.put("ram", ram);
		map.put("vga", vga);
		map.put("hdd", hdd);
		
		System.out.println("map: " + map);
		try {
			return sqlSession.selectList(namespace + ".getSearchProducts", map);
		} finally {
			sqlSession.close();
		}
	}

	// Computer 등록
	public void insertComputer(Computer computer) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();

		int result = sqlSession.insert(namespace + ".insert", computer);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Computer 가져오기
	public Computer getComputer(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		// sqlSession.update(namespace + ".readCount", map);
		Computer computer = sqlSession.selectOne(namespace + ".getComputer", map);

		sqlSession.close();
		return computer;
	}

	// Computer 수정 Get
	public Computer getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Computer computer = sqlSession.selectOne(namespace + ".getComputer", map);

		sqlSession.close();
		return computer;
	}

	// Computer 디테일 Get
	public Computer getDetail(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Computer computer = sqlSession.selectOne(namespace + ".getComputer", map);

		sqlSession.close();
		return computer;
	}

	// Computer 수정 Post
	public void updateComputer(Computer computer) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", computer);
		sqlSession.commit();

		sqlSession.close();
	}

	// Computer 삭제
	public void deleteComputer(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteComputer", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
