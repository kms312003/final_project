package view.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board_qa.BoardDataBean;
import computer.ComputerDBBean;
import cpu.Cpu;
import cpu.CpuDBBean;
import graphic.GraphicDBBean;
import hdd.HDDDBBean;
import mainboard.MainBoardDBBean;
import power.PowerDBBean;
import ram.RamDBBean;
import ssd.SSDDBBean;
import cpu.Cpu.ProductCompany;

import java.io.IOException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Controller
public class DiyController extends HttpServlet {

	HttpSession session;
	ModelAndView mv = new ModelAndView();

	@Autowired
	CpuDBBean dbProCpu;
	@Autowired
	GraphicDBBean dbProGraphic;
	@Autowired
	HDDDBBean dbProHDD;
	@Autowired
	MainBoardDBBean dbProMainBoard;
	@Autowired
	PowerDBBean dbProPower;
	@Autowired
	RamDBBean dbProRam;
	@Autowired
	SSDDBBean dbProSSD;
	@Autowired
	ComputerDBBean dbProComputer;
	
	@ModelAttribute
	public void addAttributes(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			HttpServletRequest req) {
		this.session = req.getSession();

		if (pageNum != 0) {
			session.setAttribute("pageNum", pageNum);
		}

		if (session.getAttribute("pageNum") == null) {
			session.setAttribute("pageNum", 1);
		}
	}

	
	@RequestMapping("/cpu")
	public ModelAndView diyCpu() throws Exception {

		int countCpu = 0;
		
		List cpuList = null;
		try {
			countCpu = dbProCpu.getCpuCount();

			if (countCpu > 0) {
				cpuList = dbProCpu.getCpuList(0, countCpu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("countGraphic", 0); //초기화
		mv.addObject("countHDD", 0); //초기화
		mv.addObject("countPower", 0); //초기화
		mv.addObject("countRam", 0); //초기화
		mv.addObject("countSSD", 0); //초기화
		mv.addObject("countMainBoard", 0); //초기화
		
		mv.addObject("countCpu", countCpu);
		mv.addObject("cpuList", cpuList);
		mv.setViewName("nohead/productList");

		return mv;
	}
	
	@RequestMapping("/mainboard")
	public ModelAndView diyMainBoard() throws Exception {

		int countMainBoard = 0;
		
		List mainboardList = null;
		try {
			countMainBoard = dbProMainBoard.getMainBoardCount();

			if (countMainBoard > 0) {
				mainboardList = dbProMainBoard.getMainBoardList(0, countMainBoard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("countMainBoard: " + countMainBoard);
		mv.addObject("countCpu", 0); //초기화
		mv.addObject("countGraphic", 0); //초기화
		mv.addObject("countHDD", 0); //초기화
		mv.addObject("countPower", 0); //초기화
		mv.addObject("countRam", 0); //초기화
		mv.addObject("countSSD", 0); //초기화
		
		mv.addObject("countMainBoard", countMainBoard);
		mv.addObject("mainboardList", mainboardList);
		mv.setViewName("nohead/productList");
		
		return mv;
	}
	
	@RequestMapping("/ram")
	public ModelAndView diyRam() throws Exception {

		int countRam = 0;
		
		List ramList = null;
		try {
			countRam = dbProRam.getRamCount();

			if (countRam > 0) {
				ramList = dbProRam.getRamList(0, countRam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("countRam: " + countRam);
		
		mv.addObject("countCpu", 0); //초기화
		mv.addObject("countGraphic", 0); //초기화
		mv.addObject("countHDD", 0); //초기화
		mv.addObject("countPower", 0); //초기화
		mv.addObject("countSSD", 0); //초기화
		mv.addObject("countMainBoard", 0); //초기화
		
		mv.addObject("countRam", countRam);
		mv.addObject("ramList", ramList);
		mv.setViewName("nohead/productList");
		
		return mv;
	}
	
	@RequestMapping("/graphic")
	public ModelAndView diyGraphic() throws Exception {

		int countGraphic = 0;
		
		List graphicList = null;
		try {
			countGraphic = dbProGraphic.getGraphicCount();

			if (countGraphic > 0) {
				graphicList = dbProGraphic.getGraphicList(0, countGraphic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("countGraphic: " + countGraphic);
		
		mv.addObject("countCpu", 0); //초기화
		mv.addObject("countHDD", 0); //초기화
		mv.addObject("countPower", 0); //초기화
		mv.addObject("countRam", 0); //초기화
		mv.addObject("countSSD", 0); //초기화
		mv.addObject("countMainBoard", 0); //초기화
		
		mv.addObject("countGraphic", countGraphic);
		mv.addObject("graphicList", graphicList);
		mv.setViewName("nohead/productList");
		
		return mv;
	}
	
	@RequestMapping("/ssd")
	public ModelAndView diySSD() throws Exception {

		int countSSD = 0;
		
		List ssdList = null;
		try {
			countSSD = dbProSSD.getSSDCount();

			if (countSSD > 0) {
				ssdList = dbProSSD.getSSDList(0, countSSD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("countSSD: " + countSSD);
		
		mv.addObject("countCpu", 0); //초기화
		mv.addObject("countGraphic", 0); //초기화
		mv.addObject("countHDD", 0); //초기화
		mv.addObject("countPower", 0); //초기화
		mv.addObject("countRam", 0); //초기화
		mv.addObject("countMainBoard", 0); //초기화
		
		mv.addObject("countSSD", countSSD);
		mv.addObject("ssdList", ssdList);
		mv.setViewName("nohead/productList");
		
		return mv;
	}
	
	@RequestMapping("/hdd")
	public ModelAndView diyHDD() throws Exception {

		int countHDD = 0;
		
		List hddList = null;
		try {
			countHDD = dbProHDD.getHDDCount();

			if (countHDD > 0) {
				hddList = dbProHDD.getHDDList(0, countHDD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("countHDD: " + countHDD);
		
		mv.addObject("countCpu", 0); //초기화
		mv.addObject("countGraphic", 0); //초기화
		mv.addObject("countPower", 0); //초기화
		mv.addObject("countRam", 0); //초기화
		mv.addObject("countSSD", 0); //초기화
		mv.addObject("countMainBoard", 0); //초기화
		
		mv.addObject("countHDD", countHDD);
		mv.addObject("hddList", hddList);
		mv.setViewName("nohead/productList");
		
		return mv;
	}
	
	@RequestMapping("/power")
	public ModelAndView diyPower() throws Exception {

		int countPower = 0;
		
		List powerList = null;
		try {
			countPower = dbProPower.getPowerCount();

			if (countPower > 0) {
				powerList = dbProPower.getPowerList(0, countPower);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("countPower: " + countPower);
		
		mv.addObject("countCpu", 0); //초기화
		mv.addObject("countGraphic", 0); //초기화
		mv.addObject("countHDD", 0); //초기화
		mv.addObject("countRam", 0); //초기화
		mv.addObject("countSSD", 0); //초기화
		mv.addObject("countMainBoard", 0); //초기화
		
		mv.addObject("countPower", countPower);
		mv.addObject("powerList", powerList);
		mv.setViewName("nohead/productList");
		
		return mv;
	}

	
}
