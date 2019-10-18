package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Friends;

/**
 * Servlet implementation class Friends
 */
@WebServlet("/Friends")
public class AddFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriends() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_Friend;
		String key;
		id_Friend=Integer.parseInt(request.getParameter("idFriend"));
		key=request.getParameter("key");
		
		PrintWriter out=response.getWriter();
		out.println(Friends.addFriendship(id_Friend,key));
		
	}

}
