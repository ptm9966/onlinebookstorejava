package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.BookStoreConstants;
import com.bittercode.constant.db.UsersDBConstants;
import com.bittercode.model.User;
import com.bittercode.model.UserRole;
import com.bittercode.service.UserService;
import com.bittercode.service.impl.UserServiceImpl;

public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserService authService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType(BookStoreConstants.CONTENT_TYPE_TEXT_HTML + "; charset=UTF-8");
        PrintWriter pw = res.getWriter();

        String uName = req.getParameter(UsersDBConstants.COLUMN_USERNAME);
        String pWord = req.getParameter(UsersDBConstants.COLUMN_PASSWORD);
        User user = null;

        try {
            user = authService.login(UserRole.CUSTOMER, uName, pWord, req.getSession());

            if (user != null) {
                RequestDispatcher rd = req.getRequestDispatcher("CustomerHome.html");
                rd.include(req, res);
                pw.println("<div id=\"topmid\"><h1>Welcome to Online <br>Book Store</h1></div>\r\n"
                        + "<br>\r\n"
                        + "<table class=\"tab\">\r\n"
                        + "<tr>\r\n"
                        + "<td><p>Welcome " + escapeHtml(user.getFirstName()) + ", Happy Learning !!</p></td>\r\n"
                        + "</tr>\r\n"
                        + "</table>");
            } else {
                handleLoginFailure(req, res, pw);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log this to a logging framework
            pw.println("<table class=\"tab\"><tr><td>Something went wrong. Please try again later.</td></tr></table>");
        }
    }

    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse res, PrintWriter pw) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
        rd.include(req, res);
        pw.println("<table class=\"tab\"><tr><td>Incorrect Username or Password</td></tr></table>");
    }

    private String escapeHtml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }
}
