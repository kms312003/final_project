package payment.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;

import admin.controller.Action;

public class PaymentAction extends Action {

	public String GET(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String aaa = request.getParameter("json");
		System.out.println("aaa: " + aaa);
		
		request.setAttribute("aaa", aaa);
		
		return "/cpu/admin/test.jsp";
	}

	public String POST(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		// HashMap map = new HashMap();
		// String str =
		// "{'success':true,'imp_uid':'imp_447198725074','pay_method':'card'}";
		// JSONObject jObject = new JSONObject(str);
		// Iterator<?> keys = jObject.keys();
		// while( keys.hasNext() ){
		// String key = (String)keys.next();
		// if(!key.equals("success")) {
		// String value = jObject.getString(key);
		// map.put(key, value);
		// }
		// }
		//
		// System.out.println("json : "+jObject);
		// System.out.println("map : "+map);
		//
		// String data = IOUtils.toString(request.getInputStream(), "UTF-8");

//		Map map = (Map) request.getParameterMap();
//		Iterator keys = map.entrySet().iterator();
//		while (keys.hasNext()) {
//			Object key = keys.next();
//			System.out.println("key: " + key);
//		}
//		System.out.println("map: " + map.size());

		String aaa = request.getParameter("json");
		System.out.println("aaa: " + aaa);
		
		return "/cpu/admin/test.jsp";
	}
}
