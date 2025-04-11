import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Properties props = new Properties();
            props.load(getServletContext().getResourceAsStream("/WEB-INF/db-config.properties"));

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String pass = props.getProperty("password");

            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            String query = (id != null && !id.isEmpty())
                ? "SELECT * FROM employees WHERE id = " + id
                : "SELECT * FROM employees";

            ResultSet rs = stmt.executeQuery(query);
            out.println("<h2>Employee List</h2>");
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") +
                            ", Name: " + rs.getString("name") +
                            ", Dept: " + rs.getString("department") +
                            ", Email: " + rs.getString("email") + "<br>");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
