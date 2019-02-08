package view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import ssd.SSD;
import ssd.SSDDBBean;

public class SSDAction extends Action {

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

		SSDDBBean dbPro = SSDDBBean.getInstance();

		int count = 0;

		int number = 0;

		List ssdList = null;
		try {
			count = dbPro.getSSDCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				ssdList = dbPro.getSSDList(start, end);
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
		System.out.println("ssdList: " + ssdList);

		request.setAttribute("count", count);
		request.setAttribute("ssdList", ssdList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/view/ssd/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		SSDDBBean dbPro = SSDDBBean.getInstance();
		SSD ssd = new SSD();
		try {
			ssd = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + ssd.getProductDate());
		Date productDate_date = ssd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", ssd.getCode());
		request.setAttribute("productCode", ssd.getProductCode());
		request.setAttribute("productName", ssd.getProductName());
		request.setAttribute("productCompany", ssd.getProductCompany());
		request.setAttribute("diskType", ssd.getDiskType());
		request.setAttribute("diskCapacity", ssd.getDiskCapacity());
		request.setAttribute("interFace", ssd.getInterFace());
		request.setAttribute("memoryType", ssd.getMemoryType());
		request.setAttribute("readSpeed", ssd.getReadSpeed());
		request.setAttribute("writeSpeed", ssd.getWriteSpeed());
		request.setAttribute("productDate", ssd.getProductDate());
		request.setAttribute("regDate", ssd.getRegDate());
		request.setAttribute("price", ssd.getPrice());
		request.setAttribute("count", ssd.getCount());
		request.setAttribute("filename", ssd.getFilename());

		return "/view/ssd/detail.jsp";
	}
}
