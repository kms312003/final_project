package view.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import basket.Basket;
import basket.BasketDBBean;

@Controller
public class BasketController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	BasketDBBean dbPro;

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

	// 리스트
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "id", defaultValue = "0") int id,
			@RequestParam(value = "count", defaultValue = "1") int productCount,
			@RequestParam(value = "price", defaultValue = "0") int price) throws Exception {

		String email = (String) session.getAttribute("email");

		int total = productCount*price;
		
		// 세션 유저 아이디
		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;
		int pageSize = 5;

		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize;

		BasketDBBean dbPro = BasketDBBean.getInstance();

		int count = 0;
		int number = 0;

		List basketList = null;

		count = dbPro.getBasketCount(email);
		number = count - ((currentPage - 1) * pageSize);

		if (count > 0) {
			basketList = dbPro.getBaskets(email, start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		System.out.println("count: " + count);
		System.out.println("number: " + number);
		System.out.println("basketList: " + basketList);

		mv.addObject("id", id);
		mv.addObject("email", email);
		mv.addObject("count", productCount);
		mv.addObject("total", total);
		mv.addObject("count", count);
		mv.addObject("basketList", basketList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("mainView/basket/list");

		return mv;
	}

	// 입력 post
	@RequestMapping("/write")
	public String write(@RequestParam(value = "productName", defaultValue = "") String productName,
			@RequestParam(value = "price", defaultValue = "0") int price,
			@RequestParam(value = "count", defaultValue = "0") int amount,
			@RequestParam(value = "productCode", defaultValue = "") String productCode) throws Exception {

		String email = (String) session.getAttribute("email");

		int total = price * amount;

		String productNumber = productCode.substring(4, 6);
		System.out.println("productNumber" + productNumber);
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

		System.out.println("productCategory" + productCategory);

		Basket basket = new Basket();
		BasketDBBean dbPro = BasketDBBean.getInstance();

		basket.setEmail(email);
		basket.setProductCategory(productCategory);
		basket.setProductCode(productCode);
		basket.setProductName(productName);
		basket.setPrice(price);
		basket.setCount(amount);
		basket.setTotal(total);

		dbPro.insertBasket(basket);

		return "redirect:list";
	}

	// 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		BasketDBBean dbPro = BasketDBBean.getInstance();
		dbPro.deleteBasket(id);

		return "redirect:list";
	}

	// 전체삭제
	@RequestMapping("/deleteAll")
	public String deleteAll() throws Exception {

		String email = (String) session.getAttribute("email");
		
		BasketDBBean dbPro = BasketDBBean.getInstance();
		dbPro.deleteAllBasket(email);
		
		return "redirect:list";
	}
}
