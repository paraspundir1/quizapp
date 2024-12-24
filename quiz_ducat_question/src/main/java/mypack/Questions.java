package mypack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Questions
 */
@WebServlet("/Questions")
public class Questions  {
	String ques;
	String [] options= new String [4];
	int answer;
	
	public Questions(String ques,String [] options,int answer) {
		this.ques=ques;
		this.options=options;
		this.answer=answer;
		
	}
	 public void printQuestion() {
	        System.out.println("Question: " + this.ques);
	        for (int i = 0; i < this.options.length; i++) {
	            System.out.println((i + 1) + ". " + this.options[i]);
	        }
	        System.out.println("Correct Answer: " + this.answer);
	    }
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	 
	 
   

}
