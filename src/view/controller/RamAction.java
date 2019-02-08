package view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import ram.Ram;
import ram.RamDBBean;

public class RamAction extends Action {

	// 리스트
	public String listGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();

		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			session.setAttribute("pageNum", pageNum);
		}

		pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize;

		System.out.println("start: " + start);
		System.out.println("end: " + end);

		RamDBBean dbPro = RamDBBean.getInstance();

		int count = 0;

		int number = 0;

		List ramList = null;
		try {
			count = dbPro.getRamCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				ramList = dbPro.getRamList(start, end);
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

		System.out.println("count: " + count);
		System.out.println("number: " + number);
		System.out.println("ramList: " + ramList);

		request.setAttribute("count", count);
		request.setAttribute("ramList", ramList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/view/ram/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		RamDBBean dbPro = RamDBBean.getInstance();
		Ram ram = new Ram();
		try {
			ram = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + ram.getProductDate());
		Date productDate_date = ram.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", ram.getCode());
		request.setAttribute("productCode", ram.getProductCode());
		request.setAttribute("productName", ram.getProductName());
		request.setAttribute("productCompany", ram.getProductCompany());
		request.setAttribute("productSort", ram.getProductSort());
		request.setAttribute("memoryCapacity", ram.getMemoryCapacity());
		request.setAttribute("clock", ram.getClock());
		request.setAttribute("voltage", ram.getVoltage());
		request.setAttribute("productDate", ram.getProductDate());
		request.setAttribute("regDate", ram.getRegDate());
		request.setAttribute("price", ram.getPrice());
		request.setAttribute("count", ram.getCount());
		request.setAttribute("filename", ram.getFilename());

		return "/view/ram/detail.jsp";
	}
}
