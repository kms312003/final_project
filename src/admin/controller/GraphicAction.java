package admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import graphic.Graphic;
import graphic.GraphicDBBean;

public class GraphicAction extends Action {

	// 리스?��
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
		int end = currentPage * pageSize - 1;

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
				//graphicList = dbPro.getCpuList(start, end);
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
		request.setAttribute("cpuList", graphicList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/graphic/admin/graphicList.jsp";
	}

	// ?��?��
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/graphic/admin/graphicWriteForm.jsp";
	}

	public String writePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String realFolder = "";
		String encType = "utf-8";
		int maxSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath("fileSave");

		MultipartRequest multi = null;

		try {
			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

			Enumeration files = multi.getFileNames();
			String filename = "";
			int filesize = 0;
			File file = null;

			if (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				file = multi.getFile(name);
			}

			Graphic graphic = new Graphic();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			// cpu.setId(Long.parseLong(multi.getParameter("id")));
			graphic.setProductName(multi.getParameter("productName"));
			graphic.setProductCompany(multi.getParameter("productCompany"));
			graphic.setChipSetGroup(multi.getParameter("chipSetGroup"));
			graphic.setInterFace(multi.getParameter("interFace"));
			graphic.setPowerPort(multi.getParameter("powerPort"));
			graphic.setMemoryCapacity(Integer.parseInt(multi.getParameter("memoryCapacity")));
			graphic.setNvidiaChipSet(multi.getParameter("nvidiaChipSet"));
			graphic.setMaxPower(Integer.parseInt(multi.getParameter("maxPower")));
			graphic.setMaxMonitor(Integer.parseInt(multi.getParameter("maxMonitor")));
			graphic.setLength(Integer.parseInt(multi.getParameter("length")));
			graphic.setPrice(Integer.parseInt(multi.getParameter("price")));
			//graphic.setCount(Integer.parseInt(multi.getParameter("count")));
			graphic.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			graphic.setFilesize(filesize);
			GraphicDBBean dbPro = GraphicDBBean.getInstance();
			dbPro.insertGraphic(graphic);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/graphic/admin/graphicWrite.jsp";
	}

	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String no = request.getParameter("id");
		Long id = Long.parseLong(no);

		GraphicDBBean dbPro = GraphicDBBean.getInstance();
		Graphic graphic = new Graphic();

		try {
			graphic = dbPro.getGraphic(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("no", no);
		request.setAttribute("graphic", graphic);

		return "/graphic/admin/graphicDetail.jsp";
	}

	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		Long id = Long.parseLong(request.getParameter("id"));
		String no = String.valueOf(id);

		GraphicDBBean dbPro = GraphicDBBean.getInstance();
		Graphic graphic = new Graphic();
		try {
			graphic = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + graphic.getProductDate());
		Date productDate_date = graphic.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("graphic", graphic);
		request.setAttribute("productDate", productDate);

		return "/graphic/admin/graphicUpdateForm.jsp";
	}

	public String updatePOST(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String realFolder = "";
		String encType = "utf-8";
		int maxSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath("fileSave");

		MultipartRequest multi = null;

		try {
			multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			String oldfilename = multi.getParameter("oldfilename");
			int oldfilesize = Integer.parseInt(multi.getParameter("oldfilesize"));

			Enumeration files = multi.getFileNames();
			String filename = "";
			int filesize = 0;
			File file = null;

			if (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				file = multi.getFile(name);
			}

			Graphic graphic = new Graphic();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
////////////
			graphic.setId(Long.parseLong(multi.getParameter("id")));
			graphic.setProductName(multi.getParameter("productName"));
			graphic.setProductCompany(multi.getParameter("productCompany"));
			graphic.setChipSetGroup(multi.getParameter("chipSetGroup"));
			graphic.setInterFace(multi.getParameter("interFace"));
			graphic.setPowerPort(multi.getParameter("powerPort"));
			graphic.setMemoryCapacity(Integer.parseInt(multi.getParameter("memoryCapacity")));
			graphic.setNvidiaChipSet(multi.getParameter("nvidiaChipSet"));
			graphic.setMaxPower(Integer.parseInt(multi.getParameter("maxPower")));
			graphic.setMaxMonitor(Integer.parseInt(multi.getParameter("maxMonitor")));
			graphic.setLength(Integer.parseInt(multi.getParameter("length")));
			graphic.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			graphic.setPrice(Integer.parseInt(multi.getParameter("price")));
			//graphic.setCount(Integer.parseInt(multi.getParameter("count")));
			graphic.setFilename(filename);
			if (file != null) {
				graphic.setFilename(filename);
				filesize = (int) file.length();
				graphic.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					graphic.setFilename(oldfilename);
					graphic.setFilesize(oldfilesize);
				}
			}
			
			GraphicDBBean dbPro = GraphicDBBean.getInstance();
			System.out.println("grahpic: " + graphic);
			dbPro.updateGraphic(graphic);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/graphic/admin/graphicUpdate.jsp";
	}

	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		Long id = Long.parseLong(request.getParameter("id"));

		GraphicDBBean dbPro = GraphicDBBean.getInstance();
		
		try {
			dbPro.deleteGraphic(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/graphic/admin/graphicDelete.jsp";
	}
}