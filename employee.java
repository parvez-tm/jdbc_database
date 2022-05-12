

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ParvezTM
 */
@WebServlet("/employee")
public class employee extends HttpServlet {

    int i;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
        i++;
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        out.println(i);
        
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/employee","root","");
            pst = con.prepareStatement("select fname,lname,address,emp_no,ph_no from emp_details where id =?");
            pst.setInt(1, Integer.parseInt(request.getParameter("id")));
            rs = pst.executeQuery();
        
        out.println("<b>Employee Details</b>");
        
        ResultSetMetaData rsd = rs.getMetaData();
        int coloumcount = rsd.getColumnCount();
        
        out.println("<table border=1 bgcolor=yellow>");
        out.println("<tr>");
        out.println("<td>FirstName</td>");
        out.println("<td>LastName</td>");
        out.println("<td>Address</td>");
        out.println("<td>Employee Number</td>");
        out.println("<td>Phone Number</td>");
        out.println("</tr>");
        
        
        while(rs.next()){
            out.println("<tr>");
        out.println("<td>"+rs.getString("fname")+"</td>");
        out.println("<td>"+rs.getString("lname")+"</td>");
        out.println("<td>"+rs.getString("address")+"</td>");
        out.println("<td>"+rs.getString("emp_no")+"</td>");
        out.println("<td>"+rs.getString("ph_no")+"</td>");
        out.println("</tr>");
        }
        out.println("</table>");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    } 

    public void destroy(){
        i = 0;
    }
}
