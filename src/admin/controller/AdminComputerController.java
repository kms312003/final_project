package admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import computer.Computer;
import computer.ComputerDBBean;
import computer.Computer.Category;
import cpu.Cpu;
import cpu.CpuDBBean;
import cpu.Cpu.ProductCompany;
import product.ProductCode;

@Controller
public class AdminComputerController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	ComputerDBBean dbPro;

	@ModelAttribute
	public void addAttributes(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			HttpServletRequest req) {
		this.session = req.getSession();

		if (pageNum != 0)
			session.setAttribute("pageNum", pageNum);

		if (session.getAttribute("pageNum") == null)
			session.setAttribute("pageNum", 1);
	}

	// 리스트
	@RequestMapping("/list")
	public ModelAndView list() throws Exception {

		int pageNum = (int) session.getAttribute("pageNum");
		int currentPage = pageNum;
		int pageSize = 5;
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List computerList = null;

		dbPro = ComputerDBBean.getInstance();

		count = dbPro.getComputerCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			computerList = dbPro.getComputerList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("computerList", computerList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/computer/computerList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/computer/computerWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		Computer computer = new Computer();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			computer.setFilename(filename);
			computer.setFilesize((int) multi.getSize());
		} else {
			computer.setFilename("");
			computer.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "00";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		computer.setCode(multipart.getParameter("code"));
		computer.setProductCode(productCode);
		computer.setCategory(Category.valueOf(multipart.getParameter("category")));
		computer.setProductName(multipart.getParameter("productName"));
		computer.setCpu(multipart.getParameter("cpu"));
		computer.setMainBoard(multipart.getParameter("mainBoard"));
		computer.setRam(multipart.getParameter("ram"));
		computer.setVga(multipart.getParameter("vga"));
		computer.setHdd(multipart.getParameter("hdd"));
		computer.setSsd(multipart.getParameter("ssd"));
		computer.setTower(multipart.getParameter("tower"));
		computer.setPower(multipart.getParameter("power"));
		computer.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		computer.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertComputer(computer);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		Computer computer = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + computer.getProductDate());
		Date productDate_date = computer.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("computer", computer);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/computer/computerUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		Computer computer = new Computer();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		computer.setId(Integer.parseInt(multipart.getParameter("id")));
		computer.setCode(multipart.getParameter("code"));
		computer.setProductCode(updateProductCode);
		computer.setCategory(Category.valueOf(multipart.getParameter("category")));
		computer.setProductName(multipart.getParameter("productName"));
		computer.setCpu(multipart.getParameter("cpu"));
		computer.setMainBoard(multipart.getParameter("mainBoard"));
		computer.setRam(multipart.getParameter("ram"));
		computer.setVga(multipart.getParameter("vga"));
		computer.setHdd(multipart.getParameter("hdd"));
		computer.setSsd(multipart.getParameter("ssd"));
		computer.setTower(multipart.getParameter("tower"));
		computer.setPower(multipart.getParameter("power"));
		computer.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		computer.setPrice(Integer.parseInt(multipart.getParameter("price")));

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			computer.setFilename(filename);
			computer.setFilesize((int) multi.getSize());
		} else {
			computer.setFilename("");
			computer.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				computer.setFilename(oldfilename);
				computer.setFilesize(oldfilesize);
			}
		}

		dbPro.updateComputer(computer);

		mv.setViewName("adminPage/computer/computerUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = ComputerDBBean.getInstance();
		Computer computer = new Computer();
		try {
			computer = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = computer.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("code", computer.getCode());
		mv.addObject("productCode", computer.getProductCode());
		mv.addObject("category", computer.getCategory());
		mv.addObject("productName", computer.getProductName());
		mv.addObject("cpu", computer.getCpu());
		mv.addObject("mainBoard", computer.getMainBoard());
		mv.addObject("ram", computer.getRam());
		mv.addObject("vga", computer.getVga());
		mv.addObject("hdd", computer.getHdd());
		mv.addObject("ssd", computer.getSsd());
		mv.addObject("tower", computer.getTower());
		mv.addObject("power", computer.getPower());
		mv.addObject("productDate", computer.getProductDate());
		mv.addObject("regDate", computer.getRegDate());
		mv.addObject("price", computer.getPrice());
		mv.addObject("count", computer.getCount());
		mv.addObject("filename", computer.getFilename());
		mv.setViewName("adminPage/computer/computerDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deleteComputer(id);
		mv.setViewName("adminPage/computer/computerDelete");

		return mv;
	}
}
