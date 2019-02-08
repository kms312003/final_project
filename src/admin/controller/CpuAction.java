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

import cpu.Cpu;
import cpu.Cpu.ProductCompany;
import cpu.CpuDBBean;
import product.ProductCode;
 
public class CpuAction extends Action {

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

		CpuDBBean dbPro = CpuDBBean.getInstance();

		int count = 0;
		int number = 0;

		List cpuList = null;
		try {
			count = dbPro.getCpuCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				cpuList = dbPro.getCpuList(start, end);
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
		System.out.println("cpuList: " + cpuList);

		request.setAttribute("count", count);
		request.setAttribute("cpuList", cpuList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/adminPage/cpu/cpuList.jsp";
	}

	// 입력
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/adminPage/cpu/cpuWriteForm.jsp";
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

			Cpu cpu = new Cpu();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			ProductCode productC = new ProductCode();
			
			String productDate = multi.getParameter("productDate");
			String productNum = "01";
			String productCode = productC.productCode(productDate, productNum);
			System.out.println("productDate1: " + productDate);
			System.out.println("productCode1: " + productCode);
			
			cpu.setCode(multi.getParameter("code"));
			cpu.setProductCode(productCode);
			cpu.setProductName(multi.getParameter("productName"));
			cpu.setProductCompany(ProductCompany.valueOf(multi.getParameter("productCompany")));
			cpu.setBrand(multi.getParameter("brand"));
			cpu.setSocket(multi.getParameter("socket"));
			cpu.setCore(multi.getParameter("core"));
			cpu.setThread(Integer.parseInt(multi.getParameter("thread")));
			cpu.setClockSpeed(multi.getParameter("clockSpeed"));
			cpu.setTdp(Integer.parseInt(multi.getParameter("tdp")));
			cpu.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			cpu.setPrice(Integer.parseInt(multi.getParameter("price")));
			cpu.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			cpu.setFilesize(filesize);

			CpuDBBean dbPro = CpuDBBean.getInstance();
			dbPro.insertCpu(cpu);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/adminPage/cpu/cpuWrite.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		CpuDBBean dbPro = CpuDBBean.getInstance();
		Cpu cpu = new Cpu();
		try {
			cpu = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + cpu.getProductDate());
		Date productDate_date = cpu.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("code", cpu.getCode());
		request.setAttribute("productCode", cpu.getProductCode());
		request.setAttribute("productName", cpu.getProductName());
		request.setAttribute("productCompany", cpu.getProductCompany());
		request.setAttribute("brand", cpu.getBrand());
		request.setAttribute("socket", cpu.getSocket());
		request.setAttribute("core", cpu.getCore());
		request.setAttribute("thread", cpu.getThread());
		request.setAttribute("clockSpeed", cpu.getClockSpeed());
		request.setAttribute("tdp", cpu.getTdp());
		request.setAttribute("productDate", cpu.getProductDate());
		request.setAttribute("regDate", cpu.getRegDate());
		request.setAttribute("price", cpu.getPrice());
		request.setAttribute("count", cpu.getCount());
		request.setAttribute("filename", cpu.getFilename());
		
		return "/adminPage/cpu/cpuDetailForm.jsp";
	}

	// 수정
	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		CpuDBBean dbPro = CpuDBBean.getInstance();
		Cpu cpu = new Cpu();
		try {
			cpu = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + cpu.getProductDate());
		Date productDate_date = cpu.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("cpu", cpu);
		request.setAttribute("productDate", productDate);
		
		

		return "/adminPage/cpu/cpuUpdateForm.jsp";
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

			Cpu cpu = new Cpu();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			String productDate = multi.getParameter("productDate").replaceAll("-", "").substring(4, 8);
			String productCode = multi.getParameter("productCode");
			String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
			System.out.println("productDate: " + productDate);
			System.out.println("productCode: " + productCode);
			System.out.println("updateProductCode: " + updateProductCode);
			
			cpu.setId(Integer.parseInt(multi.getParameter("id")));
			cpu.setCode(multi.getParameter("code"));
			cpu.setProductCode(updateProductCode);
			cpu.setProductName(multi.getParameter("productName"));
			cpu.setProductCompany(ProductCompany.valueOf(multi.getParameter("productCompany")));
			cpu.setBrand(multi.getParameter("brand"));
			cpu.setSocket(multi.getParameter("socket"));
			cpu.setCore(multi.getParameter("core"));
			cpu.setThread(Integer.parseInt(multi.getParameter("thread")));
			cpu.setClockSpeed(multi.getParameter("clockSpeed"));
			cpu.setTdp(Integer.parseInt(multi.getParameter("tdp")));
			cpu.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			cpu.setPrice(Integer.parseInt(multi.getParameter("price")));

			if (file != null) {
				cpu.setFilename(filename);
				filesize = (int) file.length();
				cpu.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					cpu.setFilename(oldfilename);
					cpu.setFilesize(oldfilesize);
				}
			}
			
			CpuDBBean dbPro = CpuDBBean.getInstance();
			System.out.println("cpu: " + cpu);
			dbPro.updateCpu(cpu);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/adminPage/cpu/cpuUpdate.jsp";
	}

	// 삭제
	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));

		CpuDBBean dbPro = CpuDBBean.getInstance();

		try {
			dbPro.deleteCpu(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/adminPage/cpu/cpuDelete.jsp";
	}
}