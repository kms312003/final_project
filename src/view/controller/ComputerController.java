package view.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import computer.Computer;
import computer.ComputerDBBean;

@Controller
public class ComputerController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	ComputerDBBean dbPro;

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

	// 제품 리스트(높은 가격순)
	@RequestMapping("/prolist")
	public ModelAndView prolist(@RequestParam(value = "orderby", defaultValue = "") String orderby,
			@RequestParam(value = "sql", defaultValue = "") String sql) throws Exception {

		if (orderby.equals("1")) {
			orderby = "regDate desc";
		} else if (orderby.equals("2")) {
			orderby = "price asc";
		} else if (orderby.equals("3")) {
			orderby = "price desc";
		} else if (orderby.equals("4")) {
			orderby = "productName";
		} else {
			orderby = "regDate desc";
		}

		System.out.println("orderby: " + orderby);

		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;
		int pageSize = 5;

		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize;

		dbPro = ComputerDBBean.getInstance();

		int count = 0;
		int number = 0;

		List computerList = null;

		count = dbPro.getComputerCount();
		number = count - (currentPage - 1) * pageSize;

		System.out.println("orderby:::::::" + orderby);
		System.out.println("sqlsql:::::::" + sql);

		if (count > 0) {
			computerList = dbPro.getProductList(start, end, orderby, sql);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		System.out.println("computerList:::" + computerList);

		mv.addObject("count", count);
		mv.addObject("computerList", computerList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("nohead/computerList");

		return mv;
	}

	// 제품 리스트 솔팅 post
	@RequestMapping("/listPost")
	public ModelAndView list(
			@RequestParam(value = "category", defaultValue = "") String category,
			@RequestParam(value = "cpu", defaultValue = "") String cpu,
			@RequestParam(value = "mainBoard", defaultValue = "") String mainBoard,
			@RequestParam(value = "ram", defaultValue = "") String ram,
			@RequestParam(value = "vga", defaultValue = "") String vga,
			@RequestParam(value = "hdd", defaultValue = "") String hdd) throws Exception {

		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;

		int pageSize = 5;
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;

		dbPro = ComputerDBBean.getInstance();

		int count = 0;
		int number = 0;

		List computerList = null;
		count = dbPro.getComputerCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			computerList = dbPro.getSearchProductList(start, end, category, cpu, mainBoard, ram, vga, hdd);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		String cpuSql = "";
		String mainBoardSql = "";
		String ramSql = "";
		String vgaSql = "";
		String hddSql = "";

		if (cpu != null) {
			cpuSql = " and cpu like concat('%" + cpu + "%')";
		}

		if (mainBoard != null) {
			mainBoardSql = " and mainBoard like concat('%" + mainBoard + "%')";
		}

		if (ram != null) {
			ramSql = " and ram like concat('%" + ram + "%')";
		}
		
		if (vga != null) {
			vgaSql = " and vga like concat('%" + vga + "%')";
		}
		
		if (hdd != null) {
			hddSql = " and hdd like concat('%" + hdd + "%')";
		}

		String sql = "where category=" + category + cpuSql + mainBoardSql + ramSql + vgaSql + hddSql;
		System.out.println("sql111:::" + sql);
		mv.addObject("sql", sql);
		mv.addObject("category", category);
		mv.addObject("count", count);
		mv.addObject("cpu", cpu);
		mv.addObject("mainBoard", mainBoard);
		mv.addObject("ram", ram);
		mv.addObject("vga", vga);
		mv.addObject("hdd", hdd);
		mv.addObject("computerList", computerList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("mainView/computer/list");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = ComputerDBBean.getInstance();
		Computer computer = new Computer();

		computer = dbPro.getDetail(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + computer.getProductDate());
		Date productDate_date = computer.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("id", no);
		mv.addObject("productCode", computer.getProductCode());
		mv.addObject("category", computer.getCategory());
		mv.addObject("productName", computer.getProductName());
		mv.addObject("cpu", computer.getCpu());
		mv.addObject("mainBoard", computer.getMainBoard());
		mv.addObject("ram", computer.getRam());
		mv.addObject("vga", computer.getVga());
		mv.addObject("hdd", computer.getHdd());
		mv.addObject("ssd", computer.getSsd());
		mv.addObject("tower", computer.getTower());
		mv.addObject("power", computer.getPower());
		mv.addObject("productDate", computer.getProductDate());
		mv.addObject("regDate", computer.getRegDate());
		mv.addObject("price", computer.getPrice());
		mv.addObject("count", computer.getCount());
		mv.addObject("filename", computer.getFilename());
		mv.setViewName("mainView/computer/detail");

		// 상세 내용 크롤링

		String url = "http://joon-system.co.kr/shop/product_detail.html?pd_no=" + computer.getCode();
		String detailPic1 = "";

		try {

			Document doc;
			doc = Jsoup.connect(url).get();

			Elements media4 = doc.select("td.gray_6 ");

			for (int i = 0; i < media4.size(); i++) {
				Element src1 = media4.get(i);
				String templine = src1.toString();
				detailPic1 += templine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		mv.addObject("detailPic1", detailPic1);

		return mv;
	}
}
