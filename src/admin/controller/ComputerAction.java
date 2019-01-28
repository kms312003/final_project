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

import computer.Computer;
import computer.Computer.Category;
import computer.ComputerDBBean;
import cpu.Cpu;
import cpu.CpuDBBean;
import product.ProductCode;

public class ComputerAction {

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

		ComputerDBBean dbPro = ComputerDBBean.getInstance();

		int count = 0;

		int number = 0;

		List computerList = null;
		try {
			count = dbPro.getComputerCount();
			number = count - ((currentPage - 1) * pageSize);

			if (count > 0) {
				computerList = dbPro.getComputerList(start, end);
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
		System.out.println("computerList: " + computerList);

		request.setAttribute("count", count);
		request.setAttribute("computerList", computerList);
		request.setAttribute("number", number);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);

		return "/computer/admin/computerList.jsp";
	}

	// 입력
	public String writeGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = "";

		pageNum = request.getParameter("pageNum");

		return "/computer/admin/computerWriteForm.jsp";
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

			Computer computer = new Computer();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			ProductCode productC = new ProductCode();

			String productDate = multi.getParameter("productDate");
			String productNum = "01";
			String productCode = productC.productCode(productDate, productNum);
			System.out.println("productDate1: " + productDate);
			System.out.println("productCode1: " + productCode);

			computer.setProductCode(productCode);
			computer.setCategory(Category.valueOf(multi.getParameter("category")));
			computer.setProductCompany(multi.getParameter("productCompany"));
			computer.setCpu(multi.getParameter("cpu"));
			computer.setMainBoard(multi.getParameter("mainBoard"));
			computer.setRam(multi.getParameter("ram"));
			computer.setVga(multi.getParameter("vga"));
			computer.setHdd(multi.getParameter("hdd"));
			computer.setSsd(multi.getParameter("ssd"));
			computer.setTower(multi.getParameter("tower"));
			computer.setPower(multi.getParameter("power"));
			computer.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			computer.setPrice(Integer.parseInt(multi.getParameter("price")));
			computer.setFilename(filename);
			if (file != null) {
				filesize = (int) file.length();
			}
			computer.setFilesize(filesize);

			ComputerDBBean dbPro = ComputerDBBean.getInstance();
			dbPro.insertComputer(computer);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/computer/admin/computerWrite.jsp";
	}

	// 상세보기
	public String detailGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		ComputerDBBean dbPro = ComputerDBBean.getInstance();
		Computer computer = new Computer();
		try {
			computer = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + computer.getProductDate());
		Date productDate_date = computer.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("productCode", computer.getProductCode());
		request.setAttribute("category", computer.getCategory());
		request.setAttribute("productCompany", computer.getProductCompany());
		request.setAttribute("cpu", computer.getCpu());
		request.setAttribute("mainBoard", computer.getMainBoard());
		request.setAttribute("ram", computer.getRam());
		request.setAttribute("vga", computer.getVga());
		request.setAttribute("hdd", computer.getHdd());
		request.setAttribute("ssd", computer.getSsd());
		request.setAttribute("tower", computer.getTower());
		request.setAttribute("power", computer.getPower());
		request.setAttribute("productDate", computer.getProductDate());
		request.setAttribute("regDate", computer.getRegDate());
		request.setAttribute("price", computer.getPrice());
		request.setAttribute("count", computer.getCount());
		request.setAttribute("filename", computer.getFilename());

		return "/computer/admin/computerDetailForm.jsp";
	}

	// 수정
	public String updateGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));
		String no = String.valueOf(id);

		ComputerDBBean dbPro = ComputerDBBean.getInstance();
		Computer computer = new Computer();
		try {
			computer = dbPro.getUpdate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + computer.getProductDate());
		Date productDate_date = computer.getProductDate();
		String productDate = transFormat.format(productDate_date);

		request.setAttribute("no", no);
		request.setAttribute("computer", computer);
		request.setAttribute("productDate", productDate);

		return "/computer/admin/computerUpdateForm.jsp";
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

			Computer computer = new Computer();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			String productDate = multi.getParameter("productDate").replaceAll("-", "").substring(4, 8);
			String productCode = multi.getParameter("productCode");
			String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
			System.out.println("productDate: " + productDate);
			System.out.println("productCode: " + productCode);
			System.out.println("updateProductCode: " + updateProductCode);

			computer.setId(Integer.parseInt(multi.getParameter("id")));
			computer.setProductCode(updateProductCode);
			computer.setCategory(Category.valueOf(multi.getParameter("category")));
			computer.setProductCompany(multi.getParameter("productCompany"));
			computer.setCpu(multi.getParameter("cpu"));
			computer.setMainBoard(multi.getParameter("mainBoard"));
			computer.setRam(multi.getParameter("ram"));
			computer.setVga(multi.getParameter("vga"));
			computer.setHdd(multi.getParameter("hdd"));
			computer.setSsd(multi.getParameter("ssd"));
			computer.setTower(multi.getParameter("tower"));
			computer.setPower(multi.getParameter("power"));
			computer.setProductDate(transFormat.parse(multi.getParameter("productDate")));
			computer.setPrice(Integer.parseInt(multi.getParameter("price")));

			if (file != null) {
				computer.setFilename(filename);
				filesize = (int) file.length();
				computer.setFilesize(filesize);
			} else {
				if (oldfilename != null) {
					computer.setFilename(oldfilename);
					computer.setFilesize(oldfilesize);
				}
			}

			ComputerDBBean dbPro = ComputerDBBean.getInstance();
			System.out.println("computer: " + computer);
			dbPro.updateComputer(computer);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/computer/admin/computerUpdate.jsp";
	}

	// 삭제
	public String deleteGET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int id = Integer.parseInt(request.getParameter("id"));

		ComputerDBBean dbPro = ComputerDBBean.getInstance();

		try {
			dbPro.deleteComputer(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/computer/admin/computerDelete.jsp";
	}

}
