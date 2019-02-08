package view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import computer.Computer;
import computer.ComputerDBBean;

public class ComputerAction extends Action {

	// 리스트
	public String listGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();

		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			session.setAttribute("pageNum", pageNum);
		}

		int category = Integer.parseInt(request.getParameter("category"));
		System.out.println("category: " + category);

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

		ComputerDBBean dbPro = ComputerDBBean.getInstance();

		int count = 0;
		int number = 0;

		List computerList = null;
		try {
			count = dbPro.getCategoryCount(category);
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				computerList = dbPro.getCategoryList(start, end, category);
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
		System.out.println("computerList: " + computerList);

		request.setAttribute("count", count);
		request.setAttribute("computerList", computerList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/view/computer/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		ComputerDBBean dbPro = ComputerDBBean.getInstance();
		Computer computer = new Computer();
		try {
			computer = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + computer.getProductDate());
		Date productDate_date = computer.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("productCode", computer.getProductCode());
		request.setAttribute("category", computer.getCategory());
		request.setAttribute("productName", computer.getProductName());
		request.setAttribute("cpu", computer.getCpu());
		request.setAttribute("mainBoard", computer.getMainBoard());
		request.setAttribute("ram", computer.getRam());
		request.setAttribute("vga", computer.getVga());
		request.setAttribute("hdd", computer.getHdd());
		request.setAttribute("ssd", computer.getSsd());
		request.setAttribute("tower", computer.getTower());
		request.setAttribute("power", computer.getPower());
		request.setAttribute("productDate", computer.getProductDate());
		request.setAttribute("regDate", computer.getRegDate());
		request.setAttribute("price", computer.getPrice());
		request.setAttribute("count", computer.getCount());
		request.setAttribute("filename", computer.getFilename());

		return "/view/computer/detail.jsp";
	}
}
