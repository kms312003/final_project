package view.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import computer.Computer.Category;
import cpu.Cpu;
import cpu.Cpu.ProductCompany;
import cpu.CpuDBBean;

public class CpuAction extends Action {

	// 제품 리스트(높은 가격순)
	public String prolistGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();

		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			session.setAttribute("pageNum", pageNum);
		}

		pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		String productCompany = request.getParameter("productCompany");
		
		
		String brand = request.getParameter("brand");
		String socket = request.getParameter("socket");
		String core = request.getParameter("core");
		String orderby = request.getParameter("orderby");
		
		if(orderby.equals("1")) {
			orderby = "regDate desc";
		} else if(orderby.equals("2")) {
			orderby = "price";
		} else if(orderby.equals("3")) {
			orderby = "price desc";
		} else if(orderby.equals("4")) {
			orderby = "productName";
		} else if(orderby.equals("5")) {
			orderby = "productCompany asc";
		} else {
			orderby = "regDate desc";
		}
		
		System.out.println("orderby: " + orderby);
		
		
		List<ProductCompany> productCompanys = new ArrayList<ProductCompany>(Arrays.asList(ProductCompany.values()));
		System.out.println("productCompanys " + productCompanys);

		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize;

		System.out.println("start: " + start);
		System.out.println("end: " + end);

		CpuDBBean dbPro = CpuDBBean.getInstance();

		int count = 0;
		int number = 0;

		List cpuList = null;
		try {
			count = dbPro.getCpuCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				cpuList = dbPro.getProductList(start, end, orderby);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		System.out.println("cpuList"+ cpuList);
		
		request.setAttribute("count", count);
		request.setAttribute("cpuList", cpuList);
		request.setAttribute("productCompanys", ProductCompany.values());
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/product/productList.jsp";
	}

	// 리스트
	public String listGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

//		HttpSession session = request.getSession();
//
//		String pageNum = request.getParameter("pageNum");
//		if (pageNum != null) {
//			session.setAttribute("pageNum", pageNum);
//		}
//
//		pageNum = (String) session.getAttribute("pageNum");
//		if (pageNum == null) {
//			pageNum = "1";
//		}
//
//		List<ProductCompany> productCompanys = new ArrayList<ProductCompany>(Arrays.asList(ProductCompany.values()));
//		System.out.println("productCompanys " + productCompanys);
//
//		int currentPage = Integer.parseInt(pageNum);
//		int pageSize = 5;
//		int start = (currentPage - 1) * pageSize;
//		int end = currentPage * pageSize;
//
//		System.out.println("start: " + start);
//		System.out.println("end: " + end);
//
//		CpuDBBean dbPro = CpuDBBean.getInstance();
//
//		int count = 0;
//		int number = 0;
//
//		List cpuList = null;
//		try {
//			count = dbPro.getCpuCount();
//			number = count - ((currentPage - 1) * pageSize);
//
//			if (count > 0) {
//				cpuList = dbPro.getCpuList(start, end);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		int bottomLine = 5;
//		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
//		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
//		int endPage = startPage + bottomLine - 1;
//		if (endPage > pageCount)
//			endPage = pageCount;
//
//		System.out.println("count: " + count);
//		System.out.println("number: " + number);
//		System.out.println("cpuList: " + cpuList);
//
//		request.setAttribute("count", count);
//		request.setAttribute("cpuList", cpuList);
//		request.setAttribute("productCompanys", ProductCompany.values());
//		request.setAttribute("number", number);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("bottomLine", bottomLine);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("pageCount", pageCount);
//		request.setAttribute("currentPage", currentPage);

		return "/view/cpu/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		CpuDBBean dbPro = CpuDBBean.getInstance();
		Cpu cpu = new Cpu();
		try {
			cpu = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + cpu.getProductDate());
		Date productDate_date = cpu.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", cpu.getCode());
		request.setAttribute("productCode", cpu.getProductCode());
		request.setAttribute("productName", cpu.getProductName());
		request.setAttribute("productCompany", cpu.getProductCompany());
		request.setAttribute("brand", cpu.getBrand());
		request.setAttribute("socket", cpu.getSocket());
		request.setAttribute("core", cpu.getCore());
		request.setAttribute("thread", cpu.getThread());
		request.setAttribute("clockSpeed", cpu.getClockSpeed());
		request.setAttribute("tdp", cpu.getTdp());
		request.setAttribute("productDate", cpu.getProductDate());
		request.setAttribute("regDate", cpu.getRegDate());
		request.setAttribute("price", cpu.getPrice());
		request.setAttribute("count", cpu.getCount());
		request.setAttribute("filename", cpu.getFilename());

		return "/view/cpu/detail.jsp";
	}
}
