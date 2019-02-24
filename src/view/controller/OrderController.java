package view.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import basket.Basket;
import basket.BasketDBBean;
import computer.Computer;
import computer.ComputerDBBean;
import cpu.Cpu;
import cpu.CpuDBBean;
import graphic.Graphic;
import graphic.GraphicDBBean;
import hdd.HDD;
import hdd.HDDDBBean;
import mainboard.MainBoard;
import mainboard.MainBoardDBBean;
import order.Order;
import order.Order.Status;
import order.OrderDBBean;
import power.Power;
import power.PowerDBBean;
import ram.Ram;
import ram.RamDBBean;
import ssd.SSD;
import ssd.SSDDBBean;
import user.User;
import user.UserDBBean;

@Controller
public class OrderController {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	UserDBBean dbProUser;

	@Autowired
	OrderDBBean dbProOrder;

	@ModelAttribute
	public void addAttributes(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			HttpServletRequest req) {
		this.session = req.getSession();

		if (pageNum != 0) {
			session.setAttribute("pageNum", pageNum);
		}

		if (session.getAttribute("pageNum") == null) {
			session.setAttribute("pageNum", 1);
		}
	}

	
	// 주문 후 주문목록 리스트 창
	@RequestMapping("list")
	public ModelAndView list() throws Exception { 
		
		String email = (String) session.getAttribute("email");
		
		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;
		int pageSize = 5;

		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;

		dbProOrder = OrderDBBean.getInstance();

		User user = dbProUser.getUserE(email);

		int count = 0;
		int number = 0;

		List orderList = null;

		count = dbProOrder.getOrderCount(email);
		number = count - ((currentPage - 1) * pageSize);

		if (count > 0) {
			orderList = dbProOrder.getOrders(email, start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		System.out.println("count: " + count);
		System.out.println("number: " + number);
		System.out.println("orderList: " + orderList);

		mv.addObject("user", user);
		mv.addObject("count", count);
		mv.addObject("orderList", orderList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("currentPage", currentPage);
		
		mv.setViewName("mainView/order/list");

		return mv;
	}
	// 주문 결제 대기 페이지
	@RequestMapping("/order")
	public ModelAndView order(
			@RequestParam(value = "productId", defaultValue = "0") int productId,
			@RequestParam(value = "productName", defaultValue = "") String productName,
			@RequestParam(value = "price", defaultValue = "0") int price,
			@RequestParam(value = "count", defaultValue = "0") int productCount,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "basketIds", defaultValue = "0") List<Integer> basketIds) throws Exception {

		System.out.println("productId:::" + productId);
		System.out.println("productName:::" + productName);
		System.out.println("price:::" + price);
		System.out.println("count:::" + productCount);
		System.out.println("productCode:::" + productCode);
		System.out.println("basketIds:::" + basketIds.size());
		
		String email = (String) session.getAttribute("email");
		int total = price * productCount;

		// 세션 유저 아이디
		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;
		int pageSize = 5;

		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;

		dbProOrder = OrderDBBean.getInstance();
		BasketDBBean dbProBasket = BasketDBBean.getInstance();
		dbProUser = UserDBBean.getInstance();

		// 제픔 디비 정보
		ComputerDBBean dbProComputer = ComputerDBBean.getInstance();
		CpuDBBean dbProCpu = CpuDBBean.getInstance();
		MainBoardDBBean dbProMainboard = MainBoardDBBean.getInstance();
		RamDBBean dbProRam = RamDBBean.getInstance();
		GraphicDBBean dbProGraphic = GraphicDBBean.getInstance();
		HDDDBBean dbProHDD = HDDDBBean.getInstance();
		SSDDBBean dbProSSD = SSDDBBean.getInstance();
		PowerDBBean dbProPower = PowerDBBean.getInstance();
				
		String productNumber = productCode.substring(4, 6);
		String productCategory = "";
		
		Object orderWaitProduct = new Object();
		
		System.out.println("productNumber:::" + productNumber);
		
		if (productNumber.equals("00")) {
			productCategory = "computer";
			Computer computer = dbProComputer.getComputer(productId); 
			orderWaitProduct = computer; 
			System.out.println("orderWaitProduct0000: " + orderWaitProduct);
		} else if (productNumber.equals("01")) {
			productCategory = "cpu";
			Cpu cpu = dbProCpu.getCpu(productId);
			orderWaitProduct = cpu; 
			System.out.println("orderWaitProduct1111: " + orderWaitProduct);
		} else if (productNumber.equals("02")) {
			productCategory = "mainboard";
			MainBoard mainboard = dbProMainboard.getMainBoard(productId); 
			orderWaitProduct = mainboard;
			System.out.println("orderWaitProduct2222: " + orderWaitProduct);
		} else if (productNumber.equals("03")) {
			productCategory = "ram";
			Ram ram = dbProRam.getRam(productId);
			orderWaitProduct = ram;
			System.out.println("orderWaitProduct3333: " + orderWaitProduct);
		} else if (productNumber.equals("04")) {
			productCategory = "graphic";
			Graphic graphic = dbProGraphic.getGraphic(productId);;
			orderWaitProduct = graphic;
			System.out.println("orderWaitProduct4444: " + orderWaitProduct);
		} else if (productNumber.equals("05")) {
			productCategory = "hdd";
			HDD hdd = dbProHDD.getHDD(productId);
			orderWaitProduct = hdd;
			System.out.println("orderWaitProduct5555: " + orderWaitProduct);
		} else if (productNumber.equals("06")) {
			productCategory = "ssd";
			SSD ssd = dbProSSD.getSSD(productId);
			orderWaitProduct = ssd;
			System.out.println("orderWaitProduct6666: " + orderWaitProduct);
		} else if (productNumber.equals("07")) {
			productCategory = "power";
			Power power = dbProPower.getPower(productId);
			orderWaitProduct = power; 
			System.out.println("orderWaitProduct7777: " + orderWaitProduct);
		}
		
		System.out.println("orderWaitProduct8888: " + orderWaitProduct);
		
		// 이메일로 유저 get(주문자 정보, 받는 사람 정보 이용하기 위함)
		User user = dbProUser.getUserE(email);

		int count = 0;
		int number = 0;

		// 장바구니에서 넘어온 주문대기 리스트
		List orderWaitList = null;

		count = dbProBasket.getBasketCount(email);
		number = count - ((currentPage - 1) * pageSize);

		if (count > 0) {
			orderWaitList = dbProBasket.getBaskets(email, start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		System.out.println("count: " + count);
		System.out.println("number: " + number);
		System.out.println("orderWaitList: " + orderWaitList);

		mv.addObject("email", email);
		mv.addObject("user", user);
		mv.addObject("count", count);
		mv.addObject("total", total);
		mv.addObject("productCategory", productCategory);
		// 장바구니 리스트에서 주문 창으로 넘어왔을 경우
		mv.addObject("orderWaitList", orderWaitList);
		// 제품에서 바로 주문 창으로 넘어왔을 경우(제품 하나)
		mv.addObject("orderWaitProduct", orderWaitProduct);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("mainView/order/order");

		return mv;
	}

	// 결제 결과 받아서 처리 컨트롤러
	@RequestMapping("/payment")
	public void payment(@RequestParam(value = "json", defaultValue = "") String result,
			@RequestParam(value = "category", defaultValue = "") String productCategory,
			@RequestParam(value = "productName", defaultValue = "") String productName,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "total", defaultValue = "0") int total) throws Exception {

		Order order = new Order();
		dbProOrder = OrderDBBean.getInstance();
		
		String email = (String) session.getAttribute("email");
		
		HashMap map = new HashMap();
		
		JSONObject jObject = new JSONObject(result);
		Iterator<?> keys = jObject.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			if (!key.equals("success")) {
				String value = jObject.getString(key);
				map.put(key, value);
			}
		}
		
		System.out.println("json : " + jObject);
		System.out.println("map : " + map);
		
		System.out.println("email:" + map.get("email"));
		System.out.println("imp_uid:" + map.get("imp_uid"));
		System.out.println("name:" + map.get("name"));
		System.out.println("paymethod:" + map.get("pay_method"));
		System.out.println("merchant_uid:" + map.get("merchant_uid"));
		System.out.println("status:" + map.get("status"));
		System.out.println("total:" + total);
		
		Object status = map.get("status");
		
		System.out.println("productCategory:" + productCategory);
		System.out.println("productName:" + productName);
		System.out.println("productCode:" + productCode);
		System.out.println("total:" + total);
		
		order.setEmail(email);
		order.setProductCategory(productCategory);
		order.setProductName(productName);
		order.setProductCode(productCode);
		order.setPrice(total);
		if(status.equals("paid")) {
			System.out.println("true");
			order.setStatus(Status.SUCCESS);	
		}

		dbProOrder.insertOrder(order);
		
	}

	// 주문 내역 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		dbProOrder = OrderDBBean.getInstance();
		dbProOrder.deleteOrder(id);

		return "redirect:list";
	}
}
