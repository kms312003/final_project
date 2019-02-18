package admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
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

import cpu.Cpu;
import cpu.CpuDBBean;
import mainboard.MainBoard;
import mainboard.MainBoardDBBean;
import mainboard.MainBoard.ProductCompany;
import product.ProductCode;

@Controller
public class AdminMainBoardController {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	MainBoardDBBean dbPro;

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
		List mainboardList = null;

		dbPro = MainBoardDBBean.getInstance();

		count = dbPro.getMainBoardCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			mainboardList = dbPro.getMainBoardList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("mainboardList", mainboardList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/mainboard/mainBoardList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/mainboard/mainBoardWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		MainBoard mainboard = new MainBoard();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			mainboard.setFilename(filename);
			mainboard.setFilesize((int) multi.getSize());
		} else {
			mainboard.setFilename("");
			mainboard.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "01";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		mainboard.setCode(multipart.getParameter("code"));
		mainboard.setProductCode(productCode);
		mainboard.setProductName(multipart.getParameter("productName"));
		mainboard.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		mainboard.setCpuSocket(multipart.getParameter("cpuSocket"));
		mainboard.setChipSet(multipart.getParameter("chipSet"));
		mainboard.setFormFactor(multipart.getParameter("formFactor"));
		mainboard.setMemoryType(multipart.getParameter("memoryType"));
		mainboard.setProductSort(multipart.getParameter("productSort"));
		mainboard.setMemorySlot(Integer.parseInt(multipart.getParameter("memorySlot")));
		mainboard.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		mainboard.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertMainBoard(mainboard);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		MainBoard mainboard = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + mainboard.getProductDate());
		Date productDate_date = mainboard.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("mainboard", mainboard);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/mainboard/mainBoardUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		MainBoard mainboard = new MainBoard();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		mainboard.setId(Integer.parseInt(multipart.getParameter("id")));
		mainboard.setCode(multipart.getParameter("code"));
		mainboard.setProductCode(updateProductCode);
		mainboard.setProductName(multipart.getParameter("productName"));
		mainboard.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		mainboard.setCpuSocket(multipart.getParameter("cpuSocket"));
		mainboard.setChipSet(multipart.getParameter("chipSet"));
		mainboard.setFormFactor(multipart.getParameter("formFactor"));
		mainboard.setMemoryType(multipart.getParameter("memoryType"));
		mainboard.setProductSort(multipart.getParameter("productSort"));
		mainboard.setMemorySlot(Integer.parseInt(multipart.getParameter("memorySlot")));
		mainboard.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		mainboard.setPrice(Integer.parseInt(multipart.getParameter("price")));

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			mainboard.setFilename(filename);
			mainboard.setFilesize((int) multi.getSize());
		} else {
			mainboard.setFilename("");
			mainboard.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				mainboard.setFilename(oldfilename);
				mainboard.setFilesize(oldfilesize);
			}
		}

		dbPro.updateMainBoard(mainboard);

		mv.setViewName("adminPage/mainboard/mainBoardUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = MainBoardDBBean.getInstance();
		MainBoard mainboard = new MainBoard();
		try {
			mainboard = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = mainboard.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("code", mainboard.getCode());
		mv.addObject("productCode", mainboard.getProductCode());
		mv.addObject("productName", mainboard.getProductName());
		mv.addObject("productCompany", mainboard.getProductCompany());
		mv.addObject("cpuSocket", mainboard.getCpuSocket());
		mv.addObject("chipSet", mainboard.getChipSet());
		mv.addObject("formFactor", mainboard.getFormFactor());
		mv.addObject("memoryType", mainboard.getMemoryType());
		mv.addObject("productSort", mainboard.getProductSort());
		mv.addObject("memorySlot", mainboard.getMemorySlot());
		mv.addObject("productDate", mainboard.getProductDate());
		mv.addObject("regDate", mainboard.getRegDate());
		mv.addObject("price", mainboard.getPrice());
		mv.addObject("count", mainboard.getCount());
		mv.addObject("filename", mainboard.getFilename());
		mv.setViewName("adminPage/mainboard/mainBoardDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deleteMainBoard(id);
		mv.setViewName("adminPage/mainboard/mainBoardDelete");

		return mv;
	}
}
