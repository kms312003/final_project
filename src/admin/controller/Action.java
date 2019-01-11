package admin.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public abstract class Action extends HttpServlet {
   

   public void doGet(// get방식의 서비스 메소드
         HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      requestPro(request, response);
   }

   protected void doPost(// post방식의 서비스 메소드
         HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      requestPro(request, response);
   }

   

   private void requestPro(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      String view = null;
      String command = "";
      try {
         
         Method[] md = this.getClass().getMethods();
         System.out.println(Arrays.asList(md));
         command = request.getRequestURI()+request.getMethod();
         
         System.out.println("command:" + command);
         if (command.indexOf(request.getContextPath()) == 0) {

            command = command.substring(request.getContextPath().length());
            command = command.substring(command.lastIndexOf("/") + 1);
         }
         System.out.println("command:" + command);
         view = null;
         for (int i = 0; i < md.length; i++) {
            // System.out.println("md:"+md[i].getName()+"="+command);
            if (md[i].getName().equals(command)) {
               view = (String) md[i].invoke(this, request, response);
               System.out.println("md:" + md[i].getName() + "=" + command);
            }
         }

      } catch (Throwable e) {
         throw new ServletException(e);
      }
        
      if (view != null) {
         RequestDispatcher dispatcher = request.getRequestDispatcher(view);
         dispatcher.forward(request, response);
      } else {
         String exc = "public String " + command
               + "(HttpServletRequest request,"
               + "\n HttpServletResponse response)  throws Throwable"
               + " { \n return  \" \"; \n} \n \t 추가하세요";
         System.out.println(exc);
         throw new MethodNotMatch(exc);
         

      }

   }
   
   
    class MethodNotMatch extends RuntimeException {
       
       MethodNotMatch(String msg) {
          super(msg);
       }

   }
}