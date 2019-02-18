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
import ssd.SSD;
import ssd.SSD.ProductCompany;
import ssd.SSDDBBean;

@Controller
public class AdminSSDController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	SSDDBBean dbPro;

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
		List ssdList = null;

		dbPro = SSDDBBean.getInstance();

		count = dbPro.getSSDCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			ssdList = dbPro.getSSDList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("ssdList", ssdList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/ssd/ssdList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/ssd/ssdWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		SSD ssd = new SSD();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			ssd.setFilename(filename);
			ssd.setFilesize((int) multi.getSize());
		} else {
			ssd.setFilename("");
			ssd.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "06";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		ssd.setCode(multipart.getParameter("code"));
		ssd.setProductCode(productCode);
		ssd.setProductName(multipart.getParameter("productName"));
		ssd.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		ssd.setDiskType(multipart.getParameter("diskType"));
		ssd.setDiskCapacity(Integer.parseInt(multipart.getParameter("diskCapacity")));
		ssd.setInterFace(multipart.getParameter("interFace"));
		ssd.setMemoryType(multipart.getParameter("memoryType"));
		ssd.setReadSpeed(Integer.parseInt(multipart.getParameter("readSpeed")));
		ssd.setWriteSpeed(Integer.parseInt(multipart.getParameter("writeSpeed")));
		ssd.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		ssd.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertSSD(ssd);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		SSD ssd = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + ssd.getProductDate());
		Date productDate_date = ssd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("ssd", ssd);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/ssd/ssdUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		SSD ssd = new SSD();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		ssd.setId(Integer.parseInt(multipart.getParameter("id")));
		ssd.setCode(multipart.getParameter("code"));
		ssd.setProductCode(updateProductCode);
		ssd.setProductName(multipart.getParameter("productName"));
		ssd.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		ssd.setDiskType(multipart.getParameter("diskType"));
		ssd.setDiskCapacity(Integer.parseInt(multipart.getParameter("diskCapacity")));
		ssd.setInterFace(multipart.getParameter("interFace"));
		ssd.setMemoryType(multipart.getParameter("memoryType"));
		ssd.setReadSpeed(Integer.parseInt(multipart.getParameter("readSpeed")));
		ssd.setWriteSpeed(Integer.parseInt(multipart.getParameter("writeSpeed")));
		ssd.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		ssd.setPrice(Integer.parseInt(multipart.getParameter("price")));

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			ssd.setFilename(filename);
			ssd.setFilesize((int) multi.getSize());
		} else {
			ssd.setFilename("");
			ssd.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				ssd.setFilename(oldfilename);
				ssd.setFilesize(oldfilesize);
			}
		}

		dbPro.updateSSD(ssd);

		mv.setViewName("adminPage/ssd/ssdUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = SSDDBBean.getInstance();
		SSD ssd = new SSD();
		try {
			ssd = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = ssd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("code", ssd.getCode());
		mv.addObject("productCode", ssd.getProductCode());
		mv.addObject("productName", ssd.getProductName());
		mv.addObject("productCompany", ssd.getProductCompany());
		mv.addObject("diskType", ssd.getDiskType());
		mv.addObject("diskCapacity", ssd.getDiskCapacity());
		mv.addObject("interFace", ssd.getInterFace());
		mv.addObject("memoryType", ssd.getMemoryType());
		mv.addObject("readSpeed", ssd.getReadSpeed());
		mv.addObject("writeSpeed", ssd.getWriteSpeed());
		mv.addObject("productDate", ssd.getProductDate());
		mv.addObject("regDate", ssd.getRegDate());
		mv.addObject("price", ssd.getPrice());
		mv.addObject("count", ssd.getCount());
		mv.addObject("filename", ssd.getFilename());
		mv.setViewName("adminPage/ssd/ssdDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deleteSSD(id);
		mv.setViewName("adminPage/ssd/ssdDelete");

		return mv;
	}
}
