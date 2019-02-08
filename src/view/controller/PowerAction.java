package view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import power.Power;
import power.PowerDBBean;

public class PowerAction extends Action {

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

		PowerDBBean dbPro = PowerDBBean.getInstance();

		int count = 0;

		int number = 0;

		List powerList = null;
		try {
			count = dbPro.getPowerCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				powerList = dbPro.getPowerList(start, end);
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
		System.out.println("powerList: " + powerList);

		request.setAttribute("count", count);
		request.setAttribute("powerList", powerList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/view/power/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		PowerDBBean dbPro = PowerDBBean.getInstance();
		Power power = new Power();
		try {
			power = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + power.getProductDate());
		Date productDate_date = power.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", power.getCode());
		request.setAttribute("productCode", power.getProductCode());
		request.setAttribute("productName", power.getProductName());
		request.setAttribute("productCompany", power.getProductCompany());
		request.setAttribute("productSort", power.getProductSort());
		request.setAttribute("nominalOutput", power.getNominalOutput());
		request.setAttribute("ratedOutput", power.getRatedOutput());
		request.setAttribute("productDate", power.getProductDate());
		request.setAttribute("regDate", power.getRegDate());
		request.setAttribute("price", power.getPrice());
		request.setAttribute("count", power.getCount());
		request.setAttribute("filename", power.getFilename());

		return "/view/power/detail.jsp";
	}
}
