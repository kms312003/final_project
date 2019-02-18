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

import power.Power;
import power.PowerDBBean;
import power.Power.ProductCompany;
import product.ProductCode;

@Controller
public class AdminPowerController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	PowerDBBean dbPro;

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
		List powerList = null;

		dbPro = PowerDBBean.getInstance();

		count = dbPro.getPowerCount();
		number = count - (currentPage - 1) * pageSize;

		if (count > 0) {
			powerList = dbPro.getPowerList(start, end);
		}

		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		mv.addObject("count", count);
		mv.addObject("powerList", powerList);
		mv.addObject("number", number);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("pageSize", pageSize);
		mv.addObject("currentPage", currentPage);
		mv.setViewName("adminPage/power/powerList");

		return mv;
	}

	// 입력
	@RequestMapping("/write")
	public ModelAndView write(@RequestParam(value = "pageNum", defaultValue = "") String pageNum) throws Exception {

		mv.clear();
		mv.setViewName("adminPage/power/powerWriteForm");

		return mv;
	}

	@RequestMapping("/writePro")
	public String write(MultipartHttpServletRequest multipart) throws Exception {

		Power power = new Power();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			power.setFilename(filename);
			power.setFilesize((int) multi.getSize());
		} else {
			power.setFilename("");
			power.setFilesize(0);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductCode productC = new ProductCode();

		String productDate = multipart.getParameter("productDate");
		String productNum = "07";
		String productCode = productC.productCode(productDate, productNum);
		System.out.println("productDate1: " + productDate);
		System.out.println("productCode1: " + productCode);

		power.setCode(multipart.getParameter("code"));
		power.setProductCode(productCode);
		power.setProductName(multipart.getParameter("productName"));
		power.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		power.setProductSort(multipart.getParameter("productSort"));
		power.setNominalOutput(Integer.parseInt(multipart.getParameter("nominalOutput")));
		power.setRatedOutput(Integer.parseInt(multipart.getParameter("ratedOutput")));
		power.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		power.setPrice(Integer.parseInt(multipart.getParameter("price")));

		dbPro.insertPower(power);

		return "redirect:list";
	}

	// 수정
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);
		Power power = dbPro.getUpdate(id);

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("productDate: " + power.getProductDate());
		Date productDate_date = power.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("power", power);
		mv.addObject("productDate", productDate);
		mv.setViewName("adminPage/power/powerUpdateForm");

		return mv;
	}

	@RequestMapping("/updatePro")
	public ModelAndView updatePro(MultipartHttpServletRequest multipart,
			@RequestParam(value = "productDate", defaultValue = "") String productDate,
			@RequestParam(value = "productCode", defaultValue = "") String productCode,
			@RequestParam(value = "oldfilename", defaultValue = "") String oldfilename,
			@RequestParam(value = "oldfilesize", defaultValue = "0") int oldfilesize) throws Exception {

		Power power = new Power();
		String uploadPath = multipart.getServletContext().getRealPath("/fileSave");

		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		productDate = productDate.replaceAll("-", "").substring(4, 8);

		String updateProductCode = productCode.replace(productCode.substring(0, 4), productDate);
		System.out.println("productDate: " + productDate);
		System.out.println("productCode: " + productCode);
		System.out.println("updateProductCode: " + updateProductCode);

		power.setId(Integer.parseInt(multipart.getParameter("id")));
		power.setCode(multipart.getParameter("code"));
		power.setProductCode(updateProductCode);
		power.setProductName(multipart.getParameter("productName"));
		power.setProductCompany(ProductCompany.valueOf(multipart.getParameter("productCompany")));
		power.setProductSort(multipart.getParameter("productSort"));
		power.setNominalOutput(Integer.parseInt(multipart.getParameter("nominalOutput")));
		power.setRatedOutput(Integer.parseInt(multipart.getParameter("ratedOutput")));
		power.setProductDate(transFormat.parse(multipart.getParameter("productDate")));
		power.setPrice(Integer.parseInt(multipart.getParameter("price")));

		if (filename != null && !filename.equals("")) {
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			power.setFilename(filename);
			power.setFilesize((int) multi.getSize());
		} else {
			power.setFilename("");
			power.setFilesize(0);
			if (oldfilename != null && !oldfilename.equals("")) {
				power.setFilename(oldfilename);
				power.setFilesize(oldfilesize);
			}
		}

		dbPro.updatePower(power);

		mv.setViewName("adminPage/power/powerUpdate");

		return mv;
	}

	// 상세보기
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		String no = String.valueOf(id);

		dbPro = PowerDBBean.getInstance();
		Power power = new Power();
		try {
			power = dbPro.getDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date productDate_date = power.getProductDate();
		String productDate = transFormat.format(productDate_date);

		mv.addObject("no", no);
		mv.addObject("code", power.getCode());
		mv.addObject("productCode", power.getProductCode());
		mv.addObject("productName", power.getProductName());
		mv.addObject("productCompany", power.getProductCompany());
		mv.addObject("productSort", power.getProductSort());
		mv.addObject("nominalOutput", power.getNominalOutput());
		mv.addObject("ratedOutput", power.getRatedOutput());
		mv.addObject("productDate", power.getProductDate());
		mv.addObject("regDate", power.getRegDate());
		mv.addObject("price", power.getPrice());
		mv.addObject("count", power.getCount());
		mv.addObject("filename", power.getFilename());
		mv.setViewName("adminPage/power/powerDetailForm");

		return mv;
	}

	// 삭제
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id", defaultValue = "0") int id) throws Exception {

		mv.clear();

		dbPro.deletePower(id);
		mv.setViewName("adminPage/power/powerDelete");

		return mv;
	}
}
