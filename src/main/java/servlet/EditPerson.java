package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import daos.PersonDAO;
import entities.Person;

/**
 * Servlet implementation class EditPerson
 */
@WebServlet("/EditPerson")
public class EditPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPerson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonDAO personDAO = new PersonDAO();
		String id = request.getParameter("id" );
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		List<Person> list = personDAO.getPeople();
		Person p = new Person();
		for (Person person : list) {
			if(person.getId().equalsIgnoreCase(id)) {
				p = person;
				break;
			}
		}
		p.setName(name);
		p.setCountry(country);
		String message="";
		if(personDAO.updatePerson(p)) {
			message = "Update Person Successfully";
		} else {
			message = "Update Person Fail";
		}
		request.getSession().setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
