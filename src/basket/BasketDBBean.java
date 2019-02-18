package basket;

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
import cpu.CpuDBBean;

public class BasketDBBean {

	private static BasketDBBean instance = new BasketDBBean();

	public static BasketDBBean getInstance() {
		return instance;
	}

	private BasketDBBean() {
	}

	private final String namespace = "mapper.basket";

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

	// Basket 갯수
	public int getBasketCount(String email) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("email", email);
		
		try {
			return (int) sqlSession.selectOne(namespace + ".getBasketCount", map);
		} finally {
			sqlSession.close();
		}
	}

	// 로그인한 유저 장바구니 상품 리스트
	public List getBaskets(String email, int start, int end) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("email", email);
		map.put("start", start);
		map.put("end", end);

		try {
			return sqlSession.selectList(namespace + ".getBaskets", map);
		} finally {
			sqlSession.close();
		}
	}

	// Basket 등록
	public void insertBasket(Basket basket)	throws Exception {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

//		int total = basket.getPrice() * basket.getCount();

		Map map = new HashMap();
		map.put("basket", basket);
		map.put("email", basket.getEmail());
		map.put("productCode", basket.getProductCode());
		map.put("productName", basket.getProductName());
		map.put("price", basket.getPrice());
		map.put("count", basket.getCount());
		map.put("total", basket.getTotal());

		int result = sqlSession.insert(namespace + ".insert", map);
		sqlSession.commit();
		sqlSession.close();
		System.out.println(result);
	}

	// Basket 수정 Get
	public Basket getUpdate(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		Basket basket = sqlSession.selectOne(namespace + ".getBasket", map);

		sqlSession.close();
		return basket;
	}

	// Basket 수정 Post
	public void updateBasket(Basket basket, int count, int total) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("basket", basket);
		map.put("count", count);
		map.put("total", total);

		sqlSession.update(namespace + ".update", map);
		sqlSession.commit();
		sqlSession.close();
	}

	// Basket 삭제
	public void deleteBasket(int id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		Map map = new HashMap();
		map.put("id", id);

		sqlSession.update(namespace + ".deleteBasket", map);
		sqlSession.commit();

		sqlSession.close();
	}
}
