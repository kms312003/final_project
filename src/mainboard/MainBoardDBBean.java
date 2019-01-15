package mainboard;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MainBoardDBBean {

	private static MainBoardDBBean instance = new MainBoardDBBean();

	public static MainBoardDBBean getInstance() {
		return instance;
	}

	private MainBoardDBBean() {
	}

	private final String namespace = "mapper.mainboard";

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

	// MainBoard 갯수
	public int getMainBoardCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			return (int) sqlSession.selectOne(namespace + ".getMainBoardCount");
		} finally {
			sqlSession.close();
		}
	}

	// MainBoard 리스트 가져오기
	public List getMainBoardList(int start, int end) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		try {
			return sqlSession.selectList(namespace + ".getMainBoards", map);
		} finally {
			sqlSession.close();
		}
	}

	// MainBoard 등록
	public void insertMainBoard(MainBoard mainboard) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int number = 0;
		Long id = mainboard.getId();

		Map map = new HashMap();

//		number = (int) sqlSession.selectOne(namespace + ".nextVal");

		mainboard.setId(id);

		int result = sqlSession.insert(namespace + ".insert", mainboard);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// MainBoard 가져오기
	public MainBoard getMainBoard(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".readCount", map);
		MainBoard mainboard = sqlSession.selectOne(namespace + ".getMainBoard", map);

		sqlSession.close();
		return mainboard;
	}

	// MainBoard 수정 Get
	public MainBoard getUpdate(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		MainBoard mainBoard = sqlSession.selectOne(namespace + ".getMainBoard", map);

		sqlSession.close();
		return mainBoard;
	}

	// MainBoard 수정 Post
	public void updateMainBoard(MainBoard mainboard) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		sqlSession.update(namespace + ".update", mainboard);
		sqlSession.commit();

		sqlSession.close();
	}

	// MainBoard 삭제
	public void deleteMainBoard(Long id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteMainBoard", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
