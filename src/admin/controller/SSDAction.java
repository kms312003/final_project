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

import product.ProductCode;
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
		int end = currentPage * pageSize - 1;

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

		return "/ssd/admin/ssdList.jsp";
	}

	// 입력
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/ssd/admin/ssdWriteForm.jsp";
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

			SSD ssd = new SSD();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			ProductCode productC = new ProductCode();

			String productDate = multi.getParameter("productDate");
			String productNum = "06";
			String productCode = productC.productCode(productDate, productNum);

			// ssd.setId(Integer.parseInt(multi.getParameter("id")));
			ssd.setProductCode(productCode);
			ssd.setProductName(multi.getParameter("productName"));
			ssd.setProductCompany(multi.getParameter("productCompany"));
			ssd.setDiskType(multi.getParameter("diskType"));
			ssd.setDiskCapacity(Integer.parseInt(multi.getParameter("diskCapacity")));
			ssd.setInterFace(multi.getParameter("interFace"));
			ssd.setMemoryType(multi.getParameter("memoryType"));
			ssd.setReadSpeed(Integer.parseInt(multi.getParameter("readSpeed")));
			ssd.setWriteSpeed(Integer.parseInt(multi.getParameter("writeSpeed")));
			ssd.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			ssd.setPrice(Integer.parseInt(multi.getParameter("price")));
			ssd.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			ssd.setFilesize(filesize);

			SSDDBBean dbPro = SSDDBBean.getInstance();
			dbPro.insertSSD(ssd);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/ssd/admin/ssdWrite.jsp";
	}

	// 수정
	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		SSDDBBean dbPro = SSDDBBean.getInstance();
		SSD ssd = new SSD();
		try {
			ssd = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + ssd.getProductDate());
		Date productDate_date = ssd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("ssd", ssd);
		request.setAttribute("productDate", productDate);

		return "/ssd/admin/ssdUpdateForm.jsp";
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

			SSD ssd = new SSD();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			String productDate = multi.getParameter("productDate").replaceAll("-", "").substring(4, 8);
			String productCode = multi.getParameter("productCode");
			String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);

			ssd.setId(Integer.parseInt(multi.getParameter("id")));
			ssd.setProductCode(updateProductCode);
			ssd.setProductName(multi.getParameter("productName"));
			ssd.setProductCompany(multi.getParameter("productCompany"));
			ssd.setDiskType(multi.getParameter("diskType"));
			ssd.setDiskCapacity(Integer.parseInt(multi.getParameter("diskCapacity")));
			ssd.setInterFace(multi.getParameter("interFace"));
			ssd.setMemoryType(multi.getParameter("memoryType"));
			ssd.setReadSpeed(Integer.parseInt(multi.getParameter("readSpeed")));
			ssd.setWriteSpeed(Integer.parseInt(multi.getParameter("writeSpeed")));
			ssd.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			ssd.setPrice(Integer.parseInt(multi.getParameter("price")));

			if (file != null) {
				ssd.setFilename(filename);
				filesize = (int) file.length();
				ssd.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					ssd.setFilename(oldfilename);
					ssd.setFilesize(oldfilesize);
				}
			}

			SSDDBBean dbPro = SSDDBBean.getInstance();
			System.out.println("ssd: " + ssd);
			dbPro.updateSSD(ssd);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/ssd/admin/ssdUpdate.jsp";
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

		System.out.println("ssd.getProductCode 확인");
		System.out.println(ssd.getProductCode());
		request.setAttribute("no", no);
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

		return "/ssd/admin/ssdDetailForm.jsp";
	}

	// 삭제
	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));

		SSDDBBean dbPro = SSDDBBean.getInstance();

		try {
			dbPro.deleteSSD(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/ssd/admin/ssdDelete.jsp";
	}
}