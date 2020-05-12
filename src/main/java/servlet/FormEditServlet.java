package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import daos.PersonDAO;
import entities.Person;



/**
 * Servlet implementation class formEditServlet
 */
@WebServlet("/FormEditServlet")
public class FormEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDAO personDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormEditServlet() {
        super();
        personDAO = new PersonDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h1>Update Person</h1>");
		String id = request.getParameter("idPerson");
		
		List<Person> list = personDAO.getPeople();
		Person p = new Person();
		for (Person person : list) {
			if(person.getId().equalsIgnoreCase(id)) {
				p = person;
				break;
			}
		}
		
		out.print("<form action='EditPerson' method='post'>");
		out.print(	"<html><body><table>"+
						"<tr><td></td><td><input type='hidden' name='id' value='"+p.getId()+"'/></td></tr>" +
						"<tr><td>Name:</td><td><input type='text' name='name' value='"+p.getName()+"'/></td></tr>" +
						"<tr><td>Country:</td><td><input type='text' name='country' value='"+p.getCountry()+"'/></td></tr>" +
						"<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>" +
						"</table>" + 
						"</form></body></html>");
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
