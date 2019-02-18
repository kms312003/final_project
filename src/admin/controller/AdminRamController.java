package admin.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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

import product.ProductCode;
import ram.Ram;
import ram.RamDBBean;
import ram.Ram.ProductCompany;

@Controller
public class AdminRamController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	RamDBBean dbPro;

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
		List ramList = null;

		dbPro = RamDBBean.getInstance();

		count = dbPro.getRamCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			ramList = dbPro.getRamList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("ramList", ramList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/ram/ramList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/ram/ramWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		Ram ram = new Ram();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			ram.setFilename(filename);
			ram.setFilesize((int) multi.getSize());
		} else {
			ram.setFilename("");
			ram.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "03";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		ram.setCode(multipart.getParameter("code"));
		ram.setProductCode(productCode);
		ram.setProductName(multipart.getParameter("productName"));
		ram.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		ram.setProductSort(multipart.getParameter("productSort"));
		ram.setMemoryCapacity(Integer.parseInt(multipart.getParameter("memoryCapacity")));
		ram.setClock(Integer.parseInt(multipart.getParameter("clock")));
		ram.setVoltage(Integer.parseInt(multipart.getParameter("voltage")));
		ram.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		ram.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertRam(ram);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		Ram ram = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + ram.getProductDate());
		Date productDate_date = ram.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("ram", ram);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/ram/ramUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		Ram ram = new Ram();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		ram.setId(Integer.parseInt(multipart.getParameter("id")));
		ram.setCode(multipart.getParameter("code"));
		ram.setProductCode(updateProductCode);
		ram.setProductName(multipart.getParameter("productName"));
		ram.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		ram.setProductSort(multipart.getParameter("productSort"));
		ram.setMemoryCapacity(Integer.parseInt(multipart.getParameter("memoryCapacity")));
		ram.setClock(Integer.parseInt(multipart.getParameter("clock")));
		ram.setVoltage(Integer.parseInt(multipart.getParameter("voltage")));
		ram.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		ram.setPrice(Integer.parseInt(multipart.getParameter("price")));


		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			ram.setFilename(filename);
			ram.setFilesize((int) multi.getSize());
		} else {
			ram.setFilename("");
			ram.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				ram.setFilename(oldfilename);
				ram.setFilesize(oldfilesize);
			}
		}

		dbPro.updateRam(ram);

		mv.setViewName("adminPage/ram/ramUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = RamDBBean.getInstance();
		Ram ram = new Ram();
		try {
			ram = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = ram.getProductDate();
		String productDate = transFormat.format(productDate_date);
		
		mv.addObject("no", no);
		mv.addObject("code", ram.getCode());
		mv.addObject("productCode", ram.getProductCode());
		mv.addObject("productName", ram.getProductName());
		mv.addObject("productCompany", ram.getProductCompany());
		mv.addObject("productSort", ram.getProductSort());
		mv.addObject("memoryCapacity", ram.getMemoryCapacity());
		mv.addObject("clock", ram.getClock());
		mv.addObject("voltage", ram.getVoltage());
		mv.addObject("productDate", ram.getProductDate());
		mv.addObject("regDate", ram.getRegDate());
		mv.addObject("price", ram.getPrice());
		mv.addObject("count", ram.getCount());
		mv.addObject("filename", ram.getFilename());
		mv.setViewName("adminPage/ram/ramDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deleteRam(id);
		mv.setViewName("adminPage/ram/ramDelete");

		return mv;
	}
}
