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

import hdd.HDD;
import hdd.HDDDBBean;
import product.ProductCode;

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
		int end = currentPage * pageSize - 1;

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

		return "/hdd/admin/hddList.jsp";
	}

	// 입력
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/hdd/admin/hddWriteForm.jsp";
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

			HDD hdd = new HDD();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			ProductCode productC = new ProductCode();
			
			String productDate = multi.getParameter("productDate");
			String productNum = "05";
			String productCode = productC.productCode(productDate, productNum);
			
//			hdd.setId(Integer.parseInt(multi.getParameter("id")));
			hdd.setProductCode(productCode);
			hdd.setProductName(multi.getParameter("productName"));
			hdd.setProductCompany(multi.getParameter("productCompany"));
			hdd.setInterFace(multi.getParameter("interFace"));
			hdd.setDiskSize(multi.getParameter("diskSize"));
			hdd.setDiskCapacity(Integer.parseInt(multi.getParameter("diskCapacity")));
			hdd.setBufferCapacity(multi.getParameter("bufferCapacity"));
			hdd.setRotation(multi.getParameter("rotation"));
			hdd.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			hdd.setPrice(Integer.parseInt(multi.getParameter("price")));
			hdd.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			hdd.setFilesize(filesize);

			HDDDBBean dbPro = HDDDBBean.getInstance();
			dbPro.insertHDD(hdd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/hdd/admin/hddWrite.jsp";
	}

	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String no = request.getParameter("id");
		int id = Integer.parseInt(no);

		HDDDBBean dbPro = HDDDBBean.getInstance();
		HDD hdd = new HDD();

		try {
			hdd = dbPro.getHDD(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("no", no);
		request.setAttribute("hdd", hdd);

		return "/hdd/admin/hddDetail.jsp";
	}

	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		HDDDBBean dbPro = HDDDBBean.getInstance();
		HDD hdd = new HDD();
		try {
			hdd = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + hdd.getProductDate());
		Date productDate_date = hdd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("hdd", hdd);
		request.setAttribute("productDate", productDate);

		return "/hdd/admin/hddUpdateForm.jsp";
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

			HDD hdd = new HDD();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			String productDate = multi.getParameter("productDate").replaceAll("-", "").substring(4, 8);
			String productCode = multi.getParameter("productCode");
			String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
			
			hdd.setId(Integer.parseInt(multi.getParameter("id")));
			hdd.setProductCode(updateProductCode);
			hdd.setProductName(multi.getParameter("productName"));
			hdd.setProductCompany(multi.getParameter("productCompany"));
			hdd.setInterFace(multi.getParameter("interFace"));
			hdd.setDiskSize(multi.getParameter("diskSize"));
			hdd.setDiskCapacity(Integer.parseInt(multi.getParameter("diskCapacity")));
			hdd.setBufferCapacity(multi.getParameter("bufferCapacity"));
			hdd.setRotation(multi.getParameter("rotation"));
			hdd.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			hdd.setPrice(Integer.parseInt(multi.getParameter("price")));
			
			if (file != null) {
				hdd.setFilename(filename);
				filesize = (int) file.length();
				hdd.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					hdd.setFilename(oldfilename);
					hdd.setFilesize(oldfilesize);
				}
			}

			HDDDBBean dbPro = HDDDBBean.getInstance();
			System.out.println("hdd: " + hdd);
			dbPro.insertHDD(hdd);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/hdd/admin/hddUpdate.jsp";
	}

	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));

		HDDDBBean dbPro = HDDDBBean.getInstance();

		try {
			dbPro.deleteHDD(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/hdd/admin/hddDelete.jsp";
	}
}