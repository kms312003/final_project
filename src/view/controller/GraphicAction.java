package view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controller.Action;
import graphic.Graphic;
import graphic.GraphicDBBean;
import power.Power;
import power.PowerDBBean;

public class GraphicAction extends Action {

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

		GraphicDBBean dbPro = GraphicDBBean.getInstance();

		int count = 0;

		int number = 0;

		List graphicList = null;
		try {
			count = dbPro.getGraphicCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				graphicList = dbPro.getGraphicList(start, end);
				// graphicList = dbPro.getCpuList(start, end);
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
		System.out.println("cpuList: " + graphicList);

		request.setAttribute("count", count);
		request.setAttribute("graphicList", graphicList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/view/graphic/list.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		GraphicDBBean dbPro = GraphicDBBean.getInstance();
		Graphic graphic = new Graphic();
		try {
			graphic = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + graphic.getProductDate());
		Date productDate_date = graphic.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", graphic.getCode());
		request.setAttribute("productCode", graphic.getProductCode());
		request.setAttribute("productName", graphic.getProductName());
		request.setAttribute("productCompany", graphic.getProductCompany());
		request.setAttribute("chipSetGroup", graphic.getChipSetGroup());
		request.setAttribute("interFace", graphic.getInterFace());
		request.setAttribute("powerPort", graphic.getPowerPort());
		request.setAttribute("memoryCapacity", graphic.getMemoryCapacity());
		request.setAttribute("nvidiaChipSet", graphic.getNvidiaChipSet());
		request.setAttribute("maxPower", graphic.getMaxPower());
		request.setAttribute("maxMonitor", graphic.getMaxMonitor());
		request.setAttribute("length", graphic.getLength());
		request.setAttribute("productDate", graphic.getProductDate());
		request.setAttribute("regDate", graphic.getRegDate());
		request.setAttribute("price", graphic.getPrice());
		request.setAttribute("count", graphic.getCount());
		request.setAttribute("filename", graphic.getFilename());

		return "/view/graphic/detail.jsp";
	}
}
