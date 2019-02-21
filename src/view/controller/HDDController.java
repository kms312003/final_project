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

import hdd.HDD;
import hdd.HDDDBBean;


@Controller
public class HDDController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	HDDDBBean dbPro;

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
		} else if (orderby.equals("5")) {
			orderby = "productCompany asc";
		} else {
			orderby = "regDate desc";
		}

		System.out.println("orderby: " + orderby);

		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;
		int pageSize = 5;

		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize;

		dbPro = HDDDBBean.getInstance();

		int count = 0;
		int number = 0;

		List hddList = null;

		count = dbPro.getHDDCount();
		number = count - (currentPage - 1) * pageSize;

		System.out.println("orderby:::::::" + orderby);
		System.out.println("sqlsql:::::::" + sql);

		if (count > 0) {
			hddList = dbPro.getProductList(start, end, orderby, sql);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		System.out.println("hddList:::" + hddList);

		mv.addObject("count", count);
		mv.addObject("hddList", hddList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("nohead/hddList");

		return mv;
	}

	// 제품 리스트 솔팅 post
	@RequestMapping("/listPost")
	public ModelAndView list(@RequestParam(value = "productCompany", defaultValue = "") String[] productCompanys,
			@RequestParam(value = "productName", defaultValue = "") String productName,
			@RequestParam(value = "interFace", defaultValue = "") String interFace,
			@RequestParam(value = "diskSize", defaultValue = "") String diskSize) throws Exception {

		String line = ".";

		if (productCompanys != null) {
			for (int i = 0; i < productCompanys.length; i++) {
				line += productCompanys[i] + ".";
				System.out.println("line: " + line);
			}
			mv.addObject("line", line);
		}

		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;

		int pageSize = 5;
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;

		dbPro = HDDDBBean.getInstance();

		int count = 0;
		int number = 0;

		List hddList = null;
		count = dbPro.getHDDCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			hddList = dbPro.getSearchProductList(start, end, productCompanys, productName, interFace, diskSize);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		String productCompany = "";
		String productNameSql = "";
		String interFaceSql = "";
		String diskSizeSql = "";

		if (productName != null) {
			productNameSql = " productName like concat('%" + productName + "%')";
		}

		if (interFace != null) {
			interFaceSql = " and interFace like concat('%" + interFace + "%')";
		}

		if (diskSize != null) {
			diskSizeSql = " and diskSize like concat('%" + diskSize + "%')";
		}

		System.out.println("productCompanys:::::" + productCompanys[0]);
		System.out.println("productCompany size:::::" + productCompanys.length);

		if (productCompanys != null) {
			if (productCompanys.length == 0) {
				productCompany = "";
			} else if (productCompanys.length == 1 && !productCompanys[0].equals("")) {
				productCompany = "productCompany='" + productCompanys[0] + "' and";
			} else if (productCompanys.length == 2) {
				productCompany = "(productCompany='" + productCompanys[0] + "' or productCompany='" + productCompanys[1]
						+ "') and ";
			} else if (productCompanys.length == 3) {
				productCompany = "(productCompany='" + productCompanys[0] + "' or productCompany='" + productCompanys[1]
						+ "' or productCompany='" + productCompanys[2] + "') and ";
			} else {
				productCompany = "";
			}
		}

		String sql = "where " + productCompany + productNameSql + interFaceSql + diskSizeSql;

		mv.addObject("sql", sql);
		mv.addObject("count", count);
		mv.addObject("productName", productName);
		mv.addObject("interFace", interFace);
		mv.addObject("diskSize", diskSize);
		mv.addObject("hddList", hddList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("endPage", endPage);
		mv.addObject("pageCount", pageCount);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("mainView/hdd/list");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = HDDDBBean.getInstance();
		HDD hdd = new HDD();

		hdd = dbPro.getDetail(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + hdd.getProductDate());
		Date productDate_date = hdd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("id", no);
		mv.addObject("code", hdd.getCode());
		mv.addObject("productCode", hdd.getProductCode());
		mv.addObject("productName", hdd.getProductName());
		mv.addObject("productCompany", hdd.getProductCompany());
		mv.addObject("interFace", hdd.getInterFace());
		mv.addObject("diskSize", hdd.getDiskSize());
		mv.addObject("diskCapacity", hdd.getDiskCapacity());
		mv.addObject("bufferCapacity", hdd.getBufferCapacity());
		mv.addObject("rotation", hdd.getRotation());
		mv.addObject("productDate", hdd.getProductDate());
		mv.addObject("regDate", hdd.getRegDate());
		mv.addObject("price", hdd.getPrice());
		mv.addObject("count", hdd.getCount());
		mv.addObject("filename", hdd.getFilename());
		mv.setViewName("mainView/hdd/detail");

		// 상세 내용 크롤링

		String url = "http://joon-system.co.kr/shop/product_detail.html?pd_no=" + hdd.getCode();
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
