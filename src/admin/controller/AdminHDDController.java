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

import hdd.HDD;
import hdd.HDDDBBean;
import hdd.HDD.ProductCompany;
import product.ProductCode;

@Controller
public class AdminHDDController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	HDDDBBean dbPro;

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
		List hddList = null;

		dbPro = HDDDBBean.getInstance();

		count = dbPro.getHDDCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			hddList = dbPro.getHDDList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("hddList", hddList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/hdd/hddList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/hdd/hddWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		HDD hdd = new HDD();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			hdd.setFilename(filename);
			hdd.setFilesize((int) multi.getSize());
		} else {
			hdd.setFilename("");
			hdd.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "05";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		hdd.setCode(multipart.getParameter("code"));
		hdd.setProductCode(productCode);
		hdd.setProductName(multipart.getParameter("productName"));
		hdd.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		hdd.setInterFace(multipart.getParameter("interFace"));
		hdd.setDiskSize(multipart.getParameter("diskSize"));
		hdd.setDiskCapacity(Integer.parseInt(multipart.getParameter("diskCapacity")));
		hdd.setBufferCapacity(multipart.getParameter("bufferCapacity"));
		hdd.setRotation(multipart.getParameter("rotation"));
		hdd.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		hdd.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertHDD(hdd);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		HDD hdd = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + hdd.getProductDate());
		Date productDate_date = hdd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("hdd", hdd);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/hdd/hddUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		HDD hdd = new HDD();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		hdd.setId(Integer.parseInt(multipart.getParameter("id")));
		hdd.setCode(multipart.getParameter("code"));
		hdd.setProductCode(updateProductCode);
		hdd.setProductName(multipart.getParameter("productName"));
		hdd.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		hdd.setInterFace(multipart.getParameter("interFace"));
		hdd.setDiskSize(multipart.getParameter("diskSize"));
		hdd.setDiskCapacity(Integer.parseInt(multipart.getParameter("diskCapacity")));
		hdd.setBufferCapacity(multipart.getParameter("bufferCapacity"));
		hdd.setRotation(multipart.getParameter("rotation"));
		hdd.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		hdd.setPrice(Integer.parseInt(multipart.getParameter("price")));

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			hdd.setFilename(filename);
			hdd.setFilesize((int) multi.getSize());
		} else {
			hdd.setFilename("");
			hdd.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				hdd.setFilename(oldfilename);
				hdd.setFilesize(oldfilesize);
			}
		}

		dbPro.updateHDD(hdd);

		mv.setViewName("adminPage/hdd/hddUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = HDDDBBean.getInstance();
		HDD hdd = new HDD();
		try {
			hdd = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = hdd.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("code", hdd.getCode());
		mv.addObject("productCode", hdd.getProductCode());
		mv.addObject("productName", hdd.getProductName());
		mv.addObject("productCompany", hdd.getProductCompany());
		mv.addObject("interFace", hdd.getInterFace());
		mv.addObject("diskSize", hdd.getDiskSize());
		mv.addObject("diskCapacity", hdd.getDiskCapacity());
		mv.addObject("bufferCapacity", hdd.getBufferCapacity());
		mv.addObject("rotation", hdd.getRotation());
		mv.addObject("productDate", hdd.getProductDate());
		mv.addObject("regDate", hdd.getRegDate());
		mv.addObject("price", hdd.getPrice());
		mv.addObject("count", hdd.getCount());
		mv.addObject("filename", hdd.getFilename());
		mv.setViewName("adminPage/hdd/hddDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deleteHDD(id);
		mv.setViewName("adminPage/hdd/hddDelete");

		return mv;
	}
}
