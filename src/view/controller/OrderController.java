package view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public void addAttributes(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
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

		System.out.println("basketIds:::" + basketIds);
		String email = (String) session.getAttribute("email");

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
		
		if (productNumber.equals("00")) {
			productCategory = "computer";
			orderWaitProduct = dbProComputer.getComputer(productId);
		} else if (productNumber.equals("01")) {
			productCategory = "cpu";
			orderWaitProduct = dbProCpu.getCpu(productId);
		} else if (productNumber.equals("02")) {
			productCategory = "mainboard";
			orderWaitProduct = dbProMainboard.getMainBoard(productId);
		} else if (productNumber.equals("03")) {
			productCategory = "ram";
			orderWaitProduct = dbProRam.getRam(productId);
		} else if (productNumber.equals("04")) {
			productCategory = "graphic";
			orderWaitProduct = dbProGraphic.getGraphic(productId);
		} else if (productNumber.equals("05")) {
			productCategory = "hdd";
			orderWaitProduct = dbProHDD.getHDD(productId);
		} else if (productNumber.equals("06")) {
			productCategory = "ssd";
			orderWaitProduct = dbProSSD.getSSD(productId);
		} else if (productNumber.equals("07")) {
			productCategory = "power";
			orderWaitProduct = dbProPower.getPower(productId);
		}
		
		System.out.println("email:::" + email);
		// 이메일로 유저 get(주문자 정보, 받는 사람 정보 이용하기 위함)
		User user = dbProUser.getUserE(email);

		System.out.println("user:::" + user);
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

		mv.addObject("user", user);
		mv.addObject("count", count);
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
	public void payment(@RequestParam(value = "json", defaultValue = "") String result) throws Exception {

		System.out.println("result:::" + result);

	}

	// 주문하기 결제버튼 post
	@RequestMapping("/write")
	public String write(@RequestParam(value = "productName", defaultValue = "") String productName,
			@RequestParam(value = "price", defaultValue = "0") int price,
			@RequestParam(value = "count", defaultValue = "0") int count,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "basketIds", defaultValue = "") List<Integer> basketIds) throws Exception {

		String email = (String) session.getAttribute("email");

		int total = price * count;

		String productNumber = productCode.substring(4, 6);
		String productCategory = "";

		if (productNumber.equals("00")) {
			productCategory = "computer";
		} else if (productNumber.equals("01")) {
			productCategory = "cpu";
		} else if (productNumber.equals("02")) {
			productCategory = "mainboard";
		} else if (productNumber.equals("03")) {
			productCategory = "ram";
		} else if (productNumber.equals("04")) {
			productCategory = "graphic";
		} else if (productNumber.equals("05")) {
			productCategory = "hdd";
		} else if (productNumber.equals("06")) {
			productCategory = "ssd";
		} else if (productNumber.equals("07")) {
			productCategory = "power";
		}

		Order order = new Order();
		dbProOrder = OrderDBBean.getInstance();

		order.setEmail(email);
		order.setProductCategory(productCategory);
		order.setBasketIds(basketIds);
		order.setProductName(productName);
		order.setProductCode(productCode);
		order.setPrice(total);
		order.setStatus(Status.FAIL);

		dbProOrder.insertOrder(order);

		return "redirect:list";
	}

	// 주문 내역 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		dbProOrder = OrderDBBean.getInstance();
		dbProOrder.deleteOrder(id);

		return "redirect:list";
	}
}
