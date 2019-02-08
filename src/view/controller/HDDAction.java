package view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import hdd.HDD;
import hdd.HDDDBBean;

public class HDDAction extends Action {

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

		HDDDBBean dbPro = HDDDBBean.getInstance();

		int count = 0;

		int number = 0;

		List hddList = null;
		try {
			count = dbPro.getHDDCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				hddList = dbPro.getHDDList(start, end);
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
		System.out.println("cpuList: " + hddList);

		request.setAttribute("count", count);
		request.setAttribute("hddList", hddList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/view/hdd/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		HDDDBBean dbPro = HDDDBBean.getInstance();
		HDD hdd = new HDD();
		try {
			hdd = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + hdd.getProductDate());
		Date productDate_date = hdd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", hdd.getCode());
		request.setAttribute("productCode", hdd.getProductCode());
		request.setAttribute("productName", hdd.getProductName());
		request.setAttribute("productCompany", hdd.getProductCompany());
		request.setAttribute("interFace", hdd.getInterFace());
		request.setAttribute("diskSize", hdd.getDiskSize());
		request.setAttribute("diskCapacity", hdd.getDiskCapacity());
		request.setAttribute("bufferCapacity", hdd.getBufferCapacity());
		request.setAttribute("rotation", hdd.getRotation());
		request.setAttribute("productDate", hdd.getProductDate());
		request.setAttribute("regDate", hdd.getRegDate());
		request.setAttribute("price", hdd.getPrice());
		request.setAttribute("count", hdd.getCount());
		request.setAttribute("filename", hdd.getFilename());

		return "/view/hdd/detail.jsp";
	}
}
