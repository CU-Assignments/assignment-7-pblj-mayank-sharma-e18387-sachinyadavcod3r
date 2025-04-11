import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("studentId");
        String name = request.getParameter("studentName");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "root", "your_password");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO attendance (id, name, date, status) VALUES (?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, date);
            ps.setString(4, status);
            ps.executeUpdate();

            RequestDispatcher rd = request.getRequestDispatcher("attendance-success.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
