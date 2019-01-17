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

import ram.Ram;
import ram.RamDBBean;

public class RamAction extends Action {

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

		return "/ram/admin/ramList.jsp";
	}

	// ?��?��
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/ram/admin/ramWriteForm.jsp";
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

			Ram ram = new Ram();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			// ram.setId(Long.parseLong(multi.getParameter("id")));
			ram.setProductName(multi.getParameter("productName"));
			ram.setProductCompany(multi.getParameter("productCompany"));
			ram.setProductSort(multi.getParameter("productSort"));
			ram.setMemoryCapacity(Integer.parseInt(multi.getParameter("memoryCapacity")));
			ram.setClock(Integer.parseInt(multi.getParameter("clock")));
			ram.setVoltage(Integer.parseInt(multi.getParameter("voltage")));
			// ram.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			ram.setPrice(Integer.parseInt(multi.getParameter("price")));
			ram.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			ram.setFilesize(filesize);

			RamDBBean dbPro = RamDBBean.getInstance();
			dbPro.insertRam(ram);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/ram/admin/ramWrite.jsp";
	}

	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String no = request.getParameter("id");
		int id = Integer.parseInt(no);

		RamDBBean dbPro = RamDBBean.getInstance();
		Ram ram = new Ram();

		try {
			ram = dbPro.getRam(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("no", no);
		request.setAttribute("ram", ram);

		return "/ram/admin/ramDetail.jsp";
	}

	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		RamDBBean dbPro = RamDBBean.getInstance();
		Ram ram = new Ram();
		try {
			ram = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + ram.getProductDate());
		Date productDate_date = ram.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("ram", ram);
		request.setAttribute("productDate", productDate);

		return "/ram/admin/ramUpdateForm.jsp";
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

			Ram ram = new Ram();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			ram.setId(Integer.parseInt(multi.getParameter("id")));
			ram.setProductName(multi.getParameter("productName"));
			ram.setProductCompany(multi.getParameter("productCompany"));
			ram.setProductSort(multi.getParameter("productSort"));
			ram.setMemoryCapacity(Integer.parseInt(multi.getParameter("memoryCapacity")));
			ram.setClock(Integer.parseInt(multi.getParameter("clock")));
			ram.setVoltage(Integer.parseInt(multi.getParameter("voltage")));
			ram.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			ram.setPrice(Integer.parseInt(multi.getParameter("price")));
			if (file != null) {
				ram.setFilename(filename);
				filesize = (int) file.length();
				ram.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					ram.setFilename(oldfilename);
					ram.setFilesize(oldfilesize);
				}
			}
			
			RamDBBean dbPro = RamDBBean.getInstance();
			System.out.println("ram: " + ram);
			dbPro.updateRam(ram);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/ram/admin/ramUpdate.jsp";
	}

	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));

		RamDBBean dbPro = RamDBBean.getInstance();

		try {
			dbPro.deleteRam(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/ram/admin/ramDelete.jsp";
	}
}