package view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import mainboard.MainBoard;
import mainboard.MainBoardDBBean;

public class MainBoardAction extends Action {

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

		MainBoardDBBean dbPro = MainBoardDBBean.getInstance();

		int count = 0;

		int number = 0;

		List mainboardList = null;
		try {
			count = dbPro.getMainBoardCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				mainboardList = dbPro.getMainBoardList(start, end);
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
		System.out.println("mainboardList: " + mainboardList);

		request.setAttribute("count", count);
		request.setAttribute("mainboardList", mainboardList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/view/mainboard/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		MainBoardDBBean dbPro = MainBoardDBBean.getInstance();
		MainBoard mainboard = new MainBoard();
		try {
			mainboard = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + mainboard.getProductDate());
		Date productDate_date = mainboard.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", mainboard.getCode());
		request.setAttribute("productCode", mainboard.getProductCode());
		request.setAttribute("productName", mainboard.getProductName());
		request.setAttribute("productCompany", mainboard.getProductCompany());
		request.setAttribute("cpuSocket", mainboard.getCpuSocket());
		request.setAttribute("chipSet", mainboard.getChipSet());
		request.setAttribute("formFactor", mainboard.getFormFactor());
		request.setAttribute("memoryType", mainboard.getMemoryType());
		request.setAttribute("productSort", mainboard.getProductSort());
		request.setAttribute("memorySlot", mainboard.getMemorySlot());
		request.setAttribute("productDate", mainboard.getProductDate());
		request.setAttribute("regDate", mainboard.getRegDate());
		request.setAttribute("price", mainboard.getPrice());
		request.setAttribute("count", mainboard.getCount());
		request.setAttribute("filename", mainboard.getFilename());

		return "/view/mainboard/detail.jsp";
	}
}
