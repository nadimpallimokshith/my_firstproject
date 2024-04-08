import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/SubmitFormServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SubmitFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String region = request.getParameter("region");
        String comments = request.getParameter("comments");
        Part profilePic = request.getPart("profilePic");

        // Save profile picture to server (Tomcat)
        String fileName = "";
        if(profilePic != null){
            fileName = profilePic.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            profilePic.write(uploadPath + File.separator + fileName);
        }

        // Store data in MySQL database
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Establishing a connection to the database
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to insert form data into the database
            String sql = "INSERT INTO user_info (name, gender, age, email, phone, region, comments, profile_pic) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setString(3, age);
            statement.setString(4, email);
            statement.setString(5, phone);
            statement.setString(6, region);
            statement.setString(7, comments);
            statement.setString(8, fileName);
            statement.executeUpdate();
            
            response.sendRedirect("registrationConfirmation.jsp");
           
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle any errors here
        } finally {
            // Closing the connection and statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

     
}
