package mypack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class Quiz
 */
@WebServlet("/quiz")
public class Quiz extends HttpServlet {

	ArrayList<Questions> quesdata = new ArrayList();
	String[] awardlist = new String[15];
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		Integer questionIndex = (Integer) session.getAttribute("questionIndex");
		
		
		
		
        System.out.println(questionIndex);
        
        if (questionIndex == null) {
        	
        	try {
    			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1234");
				Statement st = con.createStatement();
				String sql = ("SELECT * FROM mcq_questions;");
				ResultSet rs = st.executeQuery(sql);
				int count=1;
				while(rs.next()) {
					
				
					String questext = rs.getString("question_text");
					String option_1 = rs.getString("option_1");
					String option_2 = rs.getString("option_2");
					String option_3 = rs.getString("option_3");
					String option_4 = rs.getString("option_4");
					 int an=rs.getInt("correct_option");
					 
					 String[] options = { option_1, option_2, option_3, option_4 };
					 Questions obj = new Questions(questext, options, an);
						quesdata.add(obj);
						System.out.println("adiing questions "+count);
						count++;
				
				}
	
				con.close();
	
			} catch (Exception e) {
				System.out.println(e);
			}

        	
        	
        	
        	
            questionIndex = 0;
           
            session.setAttribute("questionIndex", questionIndex);
        }

        // Get the list of questions from session or load them from the database
        // ArrayList<Questions> quesdata = (ArrayList<Questions>) session.getAttribute("questions");
        if (quesdata == null) {
           
            session.setAttribute("questions", quesdata);
            
        }

        // Prepare to display content
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // If all questions are answered, show a completion message
        if (questionIndex >= quesdata.size()) {
            out.println("<h1>Quiz Completed! Thank you for participating.</h1>");
            return;
        }

        // Get the current question based on the index
        Questions currentQuestion = quesdata.get(questionIndex);

        // Display the question and options dynamically
        out.println("<html><head>  <link rel='stylesheet' href='style2.css'></head><body>");
        out.println("<h1 style='color:blue'>Question " + (questionIndex + 1) + ":</h1>");
        out.println("<h3>" + currentQuestion.getQues() + "</h3>");

        // Display the radio buttons for the options
        String[] options = currentQuestion.getOptions();
        out.println("<form method='post' >");
        for (int i = 0; i < options.length ; i++) {
            out.println("<input type='radio' name='answer' value='" + i + "'> " + (i + 1) + ") " + options[i] + "<br>");
      }
        out.println("<br><button type='submit'>Next Question</button>");
        out.println("</form>");
        out.println("</body></html>");
        
//        end google implemented

		
		System.out.println("if ques data");
		  // Get the current session

	    // If questionIndex is not set, initialize it
	    if (questionIndex == null) {
	        questionIndex = 0;
	       
	    } else {
	        // Increment the questionIndex to move to the next question
	        questionIndex++;
	        System.out.println("not inistializsed");
	    }

	    // Save the updated questionIndex back to the session
	    session.setAttribute("questionIndex", questionIndex);
		
		
		

		
//		code me implemented
        
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
//		
		

// english		getting data out of databse to set questions
//		
//
//		for (int i = 0; i <= 14; i++) {
//
//			int quesn = i + 1;
//			out.println("<h1 style='color:blue'> Ques  " + quesn + " > " + quesdata.get(i).getQues() + "</h1>");
//			String[] opti = quesdata.get(i).getOptions();
//			out.println("<h1 style='color:green'><input type='radio' name='answertext'> 1 ) " 
//			+ opti[0]+" <br><input type='radio' name='answertext'>  2 ) " + opti[1] + 
//			" <br><input type='radio' name='answertext'>  3 ) " + opti[2] +
//			" <br><input type='radio' name='answertext'> 4 )  " + opti[3]+"</h1>");
//			out.println("<button>submit</button>");
//
//		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("if ques data");
		  // Get the current session
	    HttpSession session = request.getSession();
	    Integer questionIndex = (Integer) session.getAttribute("questionIndex");
	    System.out.println("not inistializsed");

	    // If questionIndex is not set, initialize it
	    if (questionIndex == null) {
	        questionIndex = 0;
	       
	    } else {
	        // Increment the questionIndex to move to the next question
	        questionIndex++;
	        
	    }

	    // Save the updated questionIndex back to the session
	    session.setAttribute("questionIndex", questionIndex);

	    // Call service method to display the next question
	    service(request, response);
    
	}

}
