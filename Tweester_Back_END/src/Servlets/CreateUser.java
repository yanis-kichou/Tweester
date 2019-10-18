package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import services.User;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom,prenom,login,password,tel,mail;
		char sexe;
		
		nom=request.getParameter("nom");
		prenom=request.getParameter("prenom");
		login=request.getParameter("login");
		password=request.getParameter("password");
		tel=request.getParameter("tel");
		mail=request.getParameter("mail");
		sexe=request.getParameter("sexe").charAt(0);
		
		
		response.setContentType("test/JSON");
		
		
		PrintWriter out = response.getWriter();
		try {
			out.println(User.CreateUser(nom, prenom, login, password,mail,tel,sexe));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


}
