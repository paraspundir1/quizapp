package mypack;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out= response.getWriter();
		response.setContentType("text/html");
		

		
		String name= request.getParameter("name");
		String password= request.getParameter("password");
		String emailid= request.getParameter("email");
		String phone=request.getParameter("phone");
		String pin= request.getParameter("key");
		
//		out.println(name);
//		out.println(password);
//		out.println(emailid);
//		out.println(phone);
//		out.println(pin);
		
		 String errorMessage = "";
		
		try {
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","1234");
		PreparedStatement pst = con.prepareStatement("insert into userdetails (name,password,emailid,phone,pin) value(?,?,?,?,?)");
		pst.setString(1, name);
		pst.setString(2, password);
		pst.setString(3, emailid);
		pst.setString(4, phone);
		pst.setString(5, pin);
		
		pst.executeUpdate();
		System.out.println("data sent");
		
		con.close();
		response.sendRedirect("login.html");
		}
		catch (Exception e) {
			
			System.out.println(e);
			out.println("  <div class='login-box'> <h1 style='color:white ;'>"+e+"</h1></div>");
		}
		
		 RequestDispatcher dc= request.getRequestDispatcher("register.html");
		dc.include(request, response);
		
	
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
