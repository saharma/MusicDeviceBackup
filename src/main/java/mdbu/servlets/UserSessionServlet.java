package mdbu.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import mdbu.ejb.UserService;
import mdbu.entities.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by saharmohamedali on 08/05/2017.
 */
public class UserSessionServlet extends HttpServlet {
    HttpSession session;
    @EJB
    UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        if(session.getAttribute("user") == null){ // login
            String username = request.getParameter("username");
            String password = request.getParameter("password");


            User user;
            try {
                user = userService.getUser(username, password);
                System.out.println(user.toString());

                session.setAttribute("user", user);


                System.out.println("User Entity Stored in session");


                if (session.getAttribute("user") == null) response.sendRedirect("login.html");
                else response.sendRedirect("upload.html");
            } catch (Exception e) {
                System.out.println("User Not Found");
                response.sendRedirect("index.html");
            }
        }
        else{                                               // logout
            session.removeAttribute("user");
            response.sendRedirect("index.html");
        }




    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get");

        try{
            User user = (User) session.getAttribute("user");

            if(user != null){
                if(!user.getUsername().equals("notauser")){
                    System.out.println("Logged in: " + user.getUserId());
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");

                    response.sendRedirect("upload.html");
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(user);
                    response.getWriter().print(json);
                    System.out.println("Response containing user Entity json written");
                }

            }
            else{
                response.sendRedirect("index.html");
                System.out.println("No One logged in");
            }
        }
        catch (NullPointerException e){

        }
    }
}
