package order;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import basket.Basket;
import cpu.CpuDBBean;

public class OrderDBBean {

	private static OrderDBBean instance = new OrderDBBean();

	public static OrderDBBean getInstance() {
		return instance;
	}

	private OrderDBBean() {
	}

	private final String namespace = "mapper.order";

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

	// Order 갯수
	public int getOrderCount(String email) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("email", email);

		try {
			return (int) sqlSession.selectOne(namespace + ".getOrderCount", map);
		} finally {
			sqlSession.close();
		}
	}

	// 로그인한 유저 주문 상품 리스트
	public List getOrders(String email, int start, int end) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("email", email);
		map.put("start", start);
		map.put("end", end);

		try {
			return sqlSession.selectList(namespace + ".getOrders", map);
		} finally {
			sqlSession.close();
		}
	}

	// Order 등록
	public void insertOrder(Order order) throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("order", order);
		map.put("email", order.getEmail());

		int result = sqlSession.insert(namespace + ".insert", map);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Order 삭제
	public void deleteOrder(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteOrder", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
