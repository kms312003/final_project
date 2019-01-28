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

import cpu.CpuDBBean;
import graphic.Graphic;
import graphic.GraphicDBBean;
import power.Power;
import power.PowerDBBean;
import product.ProductCode;

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
		int end = currentPage * pageSize - 1;

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

		return "/power/admin/powerList.jsp";
	}

	// 입력
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/power/admin/powerWriteForm.jsp";
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

			Power power = new Power();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			ProductCode productC = new ProductCode();

			String productDate = multi.getParameter("productDate");
			String productNum = "07";
			String productCode = productC.productCode(productDate, productNum);

			// power.setId(Integer.parseInt(multi.getParameter("id")));
			power.setProductCode(productCode);
			power.setProductName(multi.getParameter("productName"));
			power.setProductCompany(multi.getParameter("productCompany"));
			power.setProductSort(multi.getParameter("productSort"));
			power.setNominalOutput(Integer.parseInt(multi.getParameter("nominalOutput")));
			power.setRatedOutput(Integer.parseInt(multi.getParameter("ratedOutput")));
			power.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			power.setPrice(Integer.parseInt(multi.getParameter("price")));
			power.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			power.setFilesize(filesize);

			PowerDBBean dbPro = PowerDBBean.getInstance();
			dbPro.insertPower(power);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/power/admin/powerWrite.jsp";
	}

	// 수정
	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		PowerDBBean dbPro = PowerDBBean.getInstance();
		Power power = new Power();
		try {
			power = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + power.getProductDate());
		Date productDate_date = power.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("power", power);
		request.setAttribute("productDate", productDate);

		return "/power/admin/powerUpdateForm.jsp";
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

			Power power = new Power();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			String productDate = multi.getParameter("productDate").replaceAll("-", "").substring(4, 8);
			String productCode = multi.getParameter("productCode");
			String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);

			power.setId(Integer.parseInt(multi.getParameter("id")));
			power.setProductCode(updateProductCode);
			power.setProductName(multi.getParameter("productName"));
			power.setProductCompany(multi.getParameter("productCompany"));
			power.setProductSort(multi.getParameter("productSort"));
			power.setNominalOutput(Integer.parseInt(multi.getParameter("nominalOutput")));
			power.setRatedOutput(Integer.parseInt(multi.getParameter("ratedOutput")));
			power.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			power.setPrice(Integer.parseInt(multi.getParameter("price")));

			if (file != null) {
				power.setFilename(filename);
				filesize = (int) file.length();
				power.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					power.setFilename(oldfilename);
					power.setFilesize(oldfilesize);
				}
			}

			PowerDBBean dbPro = PowerDBBean.getInstance();
			System.out.println("power: " + power);
			dbPro.updatePower(power);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/power/admin/powerUpdate.jsp";
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

		System.out.println("power.getProductCode 확인");
		System.out.println(power.getProductCode());
		request.setAttribute("no", no);
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

		return "/power/admin/powerDetailForm.jsp";
	}

	// 삭제
	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));

		PowerDBBean dbPro = PowerDBBean.getInstance();

		try {
			dbPro.deletePower(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/power/admin/powerDelete.jsp";
	}
}