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

import cpu.Cpu;
import cpu.CpuDBBean;
import cpu.Cpu.ProductCompany;
import product.ProductCode;

@Controller
public class AdminCpuController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	CpuDBBean dbPro;

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
		List cpuList = null;

		dbPro = CpuDBBean.getInstance();

		count = dbPro.getCpuCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			cpuList = dbPro.getCpuList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("cpuList", cpuList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/cpu/cpuList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/cpu/cpuWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		Cpu cpu = new Cpu();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			cpu.setFilename(filename);
			cpu.setFilesize((int) multi.getSize());
		} else {
			cpu.setFilename("");
			cpu.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "01";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		cpu.setCode(multipart.getParameter("code"));
		cpu.setProductCode(productCode);
		cpu.setProductName(multipart.getParameter("productName"));
		cpu.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		cpu.setBrand(multipart.getParameter("brand"));
		cpu.setSocket(multipart.getParameter("socket"));
		cpu.setCore(multipart.getParameter("core"));
		cpu.setThread(Integer.parseInt(multipart.getParameter("thread")));
		cpu.setClockSpeed(multipart.getParameter("clockSpeed"));
		cpu.setTdp(Integer.parseInt(multipart.getParameter("tdp")));
		cpu.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		cpu.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertCpu(cpu);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		Cpu cpu = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + cpu.getProductDate());
		Date productDate_date = cpu.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("cpu", cpu);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/cpu/cpuUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		Cpu cpu = new Cpu();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		cpu.setId(Integer.parseInt(multipart.getParameter("id")));
		cpu.setCode(multipart.getParameter("code"));
		cpu.setProductCode(updateProductCode);
		cpu.setProductName(multipart.getParameter("productName"));
		cpu.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		cpu.setBrand(multipart.getParameter("brand"));
		cpu.setSocket(multipart.getParameter("socket"));
		cpu.setCore(multipart.getParameter("core"));
		cpu.setThread(Integer.parseInt(multipart.getParameter("thread")));
		cpu.setClockSpeed(multipart.getParameter("clockSpeed"));
		cpu.setTdp(Integer.parseInt(multipart.getParameter("tdp")));
		cpu.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		cpu.setPrice(Integer.parseInt(multipart.getParameter("price")));

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			cpu.setFilename(filename);
			cpu.setFilesize((int) multi.getSize());
		} else {
			cpu.setFilename("");
			cpu.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				cpu.setFilename(oldfilename);
				cpu.setFilesize(oldfilesize);
			}
		}

		dbPro.updateCpu(cpu);

		mv.setViewName("adminPage/cpu/cpuUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		CpuDBBean dbPro = CpuDBBean.getInstance();
		Cpu cpu = new Cpu();
		try {
			cpu = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = cpu.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("code", cpu.getCode());
		mv.addObject("productCode", cpu.getProductCode());
		mv.addObject("productName", cpu.getProductName());
		mv.addObject("productCompany", cpu.getProductCompany());
		mv.addObject("brand", cpu.getBrand());
		mv.addObject("socket", cpu.getSocket());
		mv.addObject("core", cpu.getCore());
		mv.addObject("thread", cpu.getThread());
		mv.addObject("clockSpeed", cpu.getClockSpeed());
		mv.addObject("tdp", cpu.getTdp());
		mv.addObject("productDate", cpu.getProductDate());
		mv.addObject("regDate", cpu.getRegDate());
		mv.addObject("price", cpu.getPrice());
		mv.addObject("count", cpu.getCount());
		mv.addObject("filename", cpu.getFilename());
		mv.setViewName("adminPage/cpu/cpuDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deleteCpu(id);
		mv.setViewName("adminPage/cpu/cpuDelete");

		return mv;
	}
}
