package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Credential;
import model.CredentialDAO;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get Parameters
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Encapsulate the parameters in a Credential object
        Credential formSubmitted = new Credential();
        formSubmitted.setUsername(username);
        formSubmitted.setPassword(password);
        // Login using CredentialDAO
        Credential credential = CredentialDAO.login(formSubmitted);
        if (credential == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Username or Password is incorrect");
        } else {
            //req.setAttribute("currentUser", credential);
            showGreeting(req, resp, credential);
        }
    }

    private void showGreeting(HttpServletRequest req, HttpServletResponse resp, Credential currentUser) throws ServletException, IOException {
        //Credential currentUser = (Credential) req.getAttribute("currentUser");
        if (currentUser == null) {
            req.getRequestDispatcher("/login.html").forward(req, resp);
        } else {
            resp.getWriter().write("Welcome back, " + currentUser.getUsername() + "!");
        }
    }
}
