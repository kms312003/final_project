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
import cpu.CpuDBBean;
import graphic.Graphic;
import graphic.GraphicDBBean;
import graphic.Graphic.ProductCompany;
import product.ProductCode;

@Controller
public class AdminGraphicController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	GraphicDBBean dbPro;

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
		List graphicList = null;

		dbPro = GraphicDBBean.getInstance();

		count = dbPro.getGraphicCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			graphicList = dbPro.getGraphicList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("graphicList", graphicList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/graphic/graphicList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/graphic/graphicWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		Graphic graphic = new Graphic();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			graphic.setFilename(filename);
			graphic.setFilesize((int) multi.getSize());
		} else {
			graphic.setFilename("");
			graphic.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "04";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		graphic.setCode(multipart.getParameter("code"));
		graphic.setProductCode(productCode);
		graphic.setProductName(multipart.getParameter("productName"));
		graphic.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		graphic.setChipSetGroup(multipart.getParameter("chipSetGroup"));
		graphic.setInterFace(multipart.getParameter("interFace"));
		graphic.setPowerPort(multipart.getParameter("powerPort"));
		graphic.setMemoryCapacity(Integer.parseInt(multipart.getParameter("memoryCapacity")));
		graphic.setNvidiaChipSet(multipart.getParameter("nvidiaChipSet"));
		graphic.setMaxPower(Integer.parseInt(multipart.getParameter("maxPower")));
		graphic.setMaxMonitor(Integer.parseInt(multipart.getParameter("maxMonitor")));
		graphic.setLength(Integer.parseInt(multipart.getParameter("length")));
		graphic.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		graphic.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertGraphic(graphic);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		Graphic graphic = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + graphic.getProductDate());
		Date productDate_date = graphic.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("graphic", graphic);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/graphic/graphicUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		Graphic graphic = new Graphic();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		graphic.setId(Integer.parseInt(multipart.getParameter("id")));
		graphic.setCode(multipart.getParameter("code"));
		graphic.setProductCode(updateProductCode);
		graphic.setProductName(multipart.getParameter("productName"));
		graphic.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		graphic.setChipSetGroup(multipart.getParameter("chipSetGroup"));
		graphic.setInterFace(multipart.getParameter("interFace"));
		graphic.setPowerPort(multipart.getParameter("powerPort"));
		graphic.setMemoryCapacity(Integer.parseInt(multipart.getParameter("memoryCapacity")));
		graphic.setNvidiaChipSet(multipart.getParameter("nvidiaChipSet"));
		graphic.setMaxPower(Integer.parseInt(multipart.getParameter("maxPower")));
		graphic.setMaxMonitor(Integer.parseInt(multipart.getParameter("maxMonitor")));
		graphic.setLength(Integer.parseInt(multipart.getParameter("length")));
		graphic.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		graphic.setPrice(Integer.parseInt(multipart.getParameter("price")));

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			graphic.setFilename(filename);
			graphic.setFilesize((int) multi.getSize());
		} else {
			graphic.setFilename("");
			graphic.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				graphic.setFilename(oldfilename);
				graphic.setFilesize(oldfilesize);
			}
		}

		dbPro.updateGraphic(graphic);

		mv.setViewName("adminPage/graphic/graphicUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = GraphicDBBean.getInstance();
		Graphic graphic = new Graphic();
		try {
			graphic = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = graphic.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("code", graphic.getCode());
		mv.addObject("productCode", graphic.getProductCode());
		mv.addObject("productName", graphic.getProductName());
		mv.addObject("productCompany", graphic.getProductCompany());
		mv.addObject("chipSetGroup", graphic.getChipSetGroup());
		mv.addObject("interFace", graphic.getInterFace());
		mv.addObject("powerPort", graphic.getPowerPort());
		mv.addObject("memoryCapacity", graphic.getMemoryCapacity());
		mv.addObject("nvidiaChipSet", graphic.getNvidiaChipSet());
		mv.addObject("maxPower", graphic.getMaxPower());
		mv.addObject("maxMonitor", graphic.getMaxMonitor());
		mv.addObject("length", graphic.getLength());
		mv.addObject("productDate", graphic.getProductDate());
		mv.addObject("regDate", graphic.getRegDate());
		mv.addObject("price", graphic.getPrice());
		mv.addObject("count", graphic.getCount());
		mv.addObject("filename", graphic.getFilename());
		mv.setViewName("adminPage/graphic/graphicDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deleteGraphic(id);
		mv.setViewName("adminPage/graphic/graphicDelete");

		return mv;
	}
}
