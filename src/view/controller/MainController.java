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
public class MainController extends HttpServlet {

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
	
	
	//main
	@RequestMapping("/main")
	public ModelAndView main() throws Exception {

		int countCpu = 0;
		int countMainBoard = 0;
		int countGraphic = 0;
		int countHDD = 0;
		int countPower = 0;
		int countRam = 0;
		int countSSD = 0;

		List cpuList = null;
		try {
			countCpu = dbProCpu.getCpuCount();

			if (countCpu > 0) {
				cpuList = dbProCpu.getCpuList(0, 4); // 제품을 4개만 넘겨준다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List mainboardList = null;
		try {
			countMainBoard = dbProMainBoard.getMainBoardCount();

			if (countMainBoard > 0) {
				mainboardList = dbProMainBoard.getMainBoardList(0, 4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List graphicList = null;
		try {
			countGraphic = dbProGraphic.getGraphicCount();

			if (countGraphic > 0) {
				graphicList = dbProGraphic.getGraphicList(0, 4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List hddList = null;
		try {
			countHDD = dbProHDD.getHDDCount();

			if (countHDD > 0) {
				hddList = dbProHDD.getHDDList(0, 4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List powerList = null;
		try {
			countPower = dbProPower.getPowerCount();

			if (countPower > 0) {
				powerList = dbProPower.getPowerList(0, 4); // 제품을 4개만 넘겨준다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List ramList = null;
		try {
			countRam = dbProRam.getRamCount();

			if (countRam > 0) {
				ramList = dbProRam.getRamList(0, 4); // 제품을 4개만 넘겨준다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List ssdList = null;
		try {
			countSSD = dbProSSD.getSSDCount();

			if (countSSD > 0) {
				ssdList = dbProSSD.getSSDList(0, 4); // 제품을 4개만 넘겨준다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		mv.addObject("countPower", countPower);
		mv.addObject("powerList", powerList);
		
		mv.addObject("countRam", countRam);
		mv.addObject("ramList", ramList);
		
		mv.addObject("countSSD", countSSD);
		mv.addObject("ssdList", ssdList);
		
		mv.addObject("countMainBoard", countMainBoard);
		mv.addObject("mainboardList", mainboardList);
		
		mv.addObject("countGraphic", countGraphic);
		mv.addObject("graphicList", graphicList);
		
		mv.addObject("countHDD", countHDD);
		mv.addObject("hddList", hddList);

		mv.addObject("countCpu", countCpu);
		mv.addObject("cpuList", cpuList);
		
		mv.setViewName("main/main");


		return mv;
	}
	
	// 상세보기
	@RequestMapping("/search")
	public ModelAndView detail(@RequestParam(value = "search") String search) throws Exception {
		
		String keyword = String.valueOf(search);
		
		System.out.println("keyword check : " + keyword);

		//cpu
		int countCpu = 0;
		
		List cpuList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				cpuList = dbProCpu.getSearchedCpuList(keyword);
				countCpu = cpuList.size();
				if(countCpu > 0)
					System.out.println("cpuList : " + cpuList);

			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//graphic		
		int countGraphic = 0;
		
		List graphicList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				graphicList = dbProGraphic.getSearchedGraphicList(keyword);
				countGraphic = graphicList.size();
				if(countGraphic > 0)
					System.out.println("graphicList : " + graphicList);

			}
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("countGraphic : " + countGraphic);
		
		//hdd		
		int countHDD = 0;
				
		List hddList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				graphicList = dbProHDD.getSearchedHDDList(keyword);
				countHDD = hddList.size();
				if(countHDD > 0)
					System.out.println("hddList : " + hddList);			
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//mainboard						
		int countMainBoard = 0;
						
		List mainboardList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				mainboardList = dbProMainBoard.getSearchedMainBoardList(keyword);
				countMainBoard = mainboardList.size();
				if(countMainBoard > 0)
					System.out.println("mainboardList : " + mainboardList);			
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//power						
		int countPower = 0;
								
		List powerList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				powerList = dbProPower.getSearchedPowerList(keyword);
				countPower = powerList.size();
				if(countPower > 0)
					System.out.println("powerList : " + powerList);										
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//ram
		int countRam = 0;
										
		List ramList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				ramList = dbProRam.getSearchedRamList(keyword);
				countRam = ramList.size();
				if(countRam > 0)
					System.out.println("ramList : " + ramList);										
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//ssd									
		int countSSD = 0;
												
		List ssdList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				ssdList = dbProSSD.getSearchedSSDList(keyword);
				countSSD = powerList.size();
				if(countSSD > 0)
					System.out.println("ssdList : " + ssdList);										
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//computer														
		int countComputer = 0;
														
		List computerList = null;
		try{
			if(keyword != null && !keyword.equals("")){

				computerList = dbProComputer.getSearchedComputerList(keyword);
				countComputer = computerList.size();
				if(countComputer > 0)
					System.out.println("computerList : " + computerList);										
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		mv.addObject("cpuList", cpuList);
		mv.addObject("countCpu", countCpu);
		mv.addObject("graphicList", graphicList);
		mv.addObject("countGraphic", countGraphic);
		mv.addObject("hddList", hddList);
		mv.addObject("countHDD", countHDD);
		mv.addObject("mainboardList", mainboardList);
		mv.addObject("countMainBoard", countMainBoard);
		mv.addObject("powerList", powerList);
		mv.addObject("countPower", countPower);
		mv.addObject("ramList", ramList);
		mv.addObject("countRam", countRam);
		mv.addObject("ssdList", ssdList);
		mv.addObject("countSSD", countSSD);
		mv.addObject("computerList", computerList);
		mv.addObject("countComputer", countComputer);
		mv.setViewName("main/searchList");

		return mv;
	}
	
	//main
	@RequestMapping("/diy")
	public ModelAndView diy() throws Exception {
		mv.setViewName("main/diy");
		
		return mv;
	}

	
}
