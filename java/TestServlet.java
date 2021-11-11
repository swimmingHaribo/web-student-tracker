@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Define datasource/connection pool for Resource Injection
    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;

  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set up the printwriter
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        // get a connection to the database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            // create a SQL statements
            String sql = "select * from student";
            myStmt = myConn.createStatement();

            // execute SQL query
            myRs = myStmt.executeQuery(sql);

            // process the result set
            while (myRs.next()) {
                String email = myRs.getString("email");
                out.println(email);
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
