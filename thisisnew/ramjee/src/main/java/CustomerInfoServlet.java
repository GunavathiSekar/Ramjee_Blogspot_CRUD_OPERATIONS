
//import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;
//import java.lang.*;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/CustomerInfoServlet", "/insert", "/update", "/delete", "/edit","/editupdate" })
public class CustomerInfoServlet extends HttpServlet {

        public void init() {
                System.out.println("-----------------------------------------------------");
                System.out.println("init method has been called and servlet is initialized");
                System.out.println("-----------------------------------------------------");

        }

        public void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                doPost(request, response);
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                                System.out.println("Post method");
                                PrintWriter out = response.getWriter();
                response.setContentType("text/html;charset=UTF-8");
                
                
                String action = request.getServletPath();
                System.out.println("action" + action);
                try {
                        switch (action) {
                        case "/insert":
                                insert(request, response);
                                show(request, response);
                                break;
                        case "/update":
                                System.out.println("update called`");
                                show(request, response);
                                break;

                        case "/edit":
                                System.out.println("edit form");
                                showedit(request, response);
                                break;
                        case "/editupdate":
                        System.out.println("editupdate");
                                update(request, response);
                                break;

                        case "/delete":
                                delete(request, response);
                                show(request, response);
                                break;
                        default:
                                System.out.println("default value called");
                                break;
                        }

                } catch (Exception se) {
                        se.printStackTrace();
                }

        //         out.println("Read data using  '<b>String getParameter(String name)</b>' method");

        //         String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        //         out.println(docType + "<html>\n" + "<head><title>" + "GET" + "</title></head>\n"
        //                         + "<body bgcolor=\"#f0f0f0\">\n" + "<ul>\n" + "  <li><b>First Name</b>: "
        //                         + request.getParameter("first_name") + "\n" + "  <li><b>Last Name</b>: "
        //                         + request.getParameter("last_name") + "\n" + "</ul>\n" + "</body></html>");

        //         out.println("\nRead data using '<b>Enumeration getParameterNames()</b>' method \n");


        //         Enumeration<String> paramNames = request.getParameterNames();

        //         out.println("<ul>");

        //         while (paramNames.hasMoreElements()) {

        //                 String paramName = paramNames.nextElement();
        //                 out.print(" <li><b>" + paramName + ":</b>");
        //                 String paramValue = request.getParameter(paramName);
        //                 out.println(paramValue + "</li>");

        //         }

        //         out.println("</ul>");

        //         out.println("\nRead data using '<b>Map getParameterMap()</b>' method\n");

        //         Map<String, String[]> paramMap = request.getParameterMap();

        //         Set<String> paramNamesSet = paramMap.keySet();

        //         out.println("<ul>");
        //         for (String paramName : paramNamesSet) {
        //                 out.print(" <li><b>" + paramName + ":</b>");
        //                 String[] paramValue = paramMap.get(paramName);
        //                 for (int i = 0; i < paramValue.length; i++) {
        //                         out.println(paramValue[i] + "</li>");
        //                 }

        //         }
        //         out.println("</ul>");
        // }

        private void showedit(HttpServletRequest request, HttpServletResponse response) throws Exception {
                String fname = request.getParameter("id");
                System.out.println(fname);
                CustomerModel cust=null;
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
                //  PreparedStatement statement;
                String query = "SELECT * FROM `lopgintable` WHERE first_name=?";
               PreparedStatement ps=con.prepareStatement(query);
               ps.setString(1, fname);
                ResultSet resultSet = ps.executeQuery();
                ArrayList<CustomerModel> listCustomer = new ArrayList<CustomerModel>();
                while (resultSet.next()) {
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                         cust = new CustomerModel(firstName, lastName);
                        //listCustomer.add(cust);

                }
                System.out.println(listCustomer+" details");
                request.setAttribute("book", cust);
                request.getRequestDispatcher("textshow.jsp").forward(request, response);
        }

        private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
                try {

                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
                        //  PreparedStatement statement;
                        String fN=request.getParameter("first_name");
                        String lN=request.getParameter("last_name");
                        String query = "insert into lopgintable values(?,?) ";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, fN);
                        ps.setString(2, lN);
                        int i = ps.executeUpdate();
                        //out.println(i);
                        if (i > 0) {
                                System.out.println("INSERTED SUCCESSFULLY");
                        }
                      // response.sendRedirect("/CustomerInfoServlet?id=fN");
                } catch (SQLException ex) {

                }
        }

        private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
                System.out.println("update method");

                try { 
                        String fname = request.getParameter("id");
                        System.out.println(fname);
                       

                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
                        //  PreparedStatement statement;
                        String query1 = "UPDATE `lopgintable` SET last_name=? WHERE first_name=?";
                        PreparedStatement ps = con.prepareStatement(query1);
                        String lname=request.getParameter("LastName");
                        System.out.println(lname+"lnaqme");
                        ps.setString(1, lname);
                        ps.setString(2, fname);
                        int i = ps.executeUpdate();
                        if (i > 0)
                                System.out.println("UPDATED SUCCESSFULLY");

                        ps.close();

                } catch (SQLException ex) {

                }
                response.sendRedirect("/update");

        }

        private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

                try {
                        String fname = request.getParameter("id");
                        System.out.println(fname+"delete\n");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
                        //  PreparedStatement statement;
                        String query2 = "DELETE FROM lopgintable where first_name = ?";
                        PreparedStatement ps = con.prepareStatement(query2);
                        ps.setString(1, fname);

                        int i = ps.executeUpdate();
                        if (i > 0)
                                System.out.println("DELETED SUCCESSFULLY");
                        ps.close();

                } catch (Exception ex) {

                }

        }

        private void show(HttpServletRequest request, HttpServletResponse response)
                        throws SQLException, IOException, ServletException {
                ArrayList<CustomerModel> listCustomer = new ArrayList<CustomerModel>();

                System.out.println("show method");

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
                //  PreparedStatement statement;
                String query1 = "select *from lopgintable";
                Statement ps = con.createStatement();
                ResultSet resultSet = ps.executeQuery(query1);
                while (resultSet.next()) {
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        CustomerModel cust = new CustomerModel(firstName, lastName);

                        listCustomer.add(cust);

                }
                System.out.println(listCustomer);

                request.setAttribute("listCustomere", listCustomer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Updateform.jsp");

                dispatcher.forward(request, response);
                // System.out.println("requestdisp");

        }

        public void destroy() {
                System.out.println("-----------------------------------------------------");
                System.out.println("destroy method has been called and servlet is destroyed");
                System.out.println("-----------------------------------------------------");
        }

}