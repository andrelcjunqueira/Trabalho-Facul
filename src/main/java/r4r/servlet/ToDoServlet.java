/*
 * Save as ToDoServlet.java
 */
package r4r.servlet;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;



public class ToDoServlet extends HttpServlet {

 private ToDoClass toDoClass;
 private List<ToDoClass> toDoClasseList;
 private DateFormat dateFormat;
 private String newDate;

 @Override
 public void init() throws ServletException {
  toDoClass = new ToDoClass(null, null, null, null);
  toDoClasseList = new ArrayList<ToDoClass>();
  dateFormat = new SimpleDateFormat("dd/mm/yyyy");
  newDate = dateFormat.format(Calendar.getInstance().getTime());
 }

 protected void processRequest
	 (HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
  response.setContentType("text/html;charset=UTF-8");
  PrintWriter out = response.getWriter();
  try {
out.println("<html>");
out.println("<head>");
out.println("<title>" + getServletInfo() + "</title>");
out.println("</head>");
out.println("<body>");
out.println("<form action=\"ToDoServlet\" method=\"POST\">");
out.println("Nome da Tarefa:<input type=\"text\" name=\"title\" value=\"\" size=\"30\" maxlength=\"20\"/><BR>");
out.println("Descrição: <textarea name=\"description\" rows=\"4\" cols=\"25\"></textarea><BR>");
out.println("Prioridade: <select name=\"priority\">");
out.println("<option> BAIXA </option>");
out.println("<option> MEDIA </option>");
out.println("<option> ALTA </option></select><BR>");
out.println("<input type=\"submit\" value=\"Submit\" />");
out.println("<input type=\"reset\" value=\"Reset\" />");
out.println("</form>");
out.println("</body>");
out.println("</html>");
  } finally {
out.close();
  }
 }

 @Override
 protected void doGet
	 (HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
  processRequest(request, response);
 }

 @Override
 protected void doPost
	 (HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
  response.setContentType("text/html;charset=UTF-8");
  PrintWriter out = response.getWriter();

  String title = request.getParameter("title");
  String description = request.getParameter("description");
  String priority = request.getParameter("priority");
  String date = request.getParameter("date");

  if (!title.equals("") && !description.equals("")) {
toDoClass = new ToDoClass(title, description, priority, date);

toDoClasseList.add(toDoClass);

printHeader(out);
printDataList(out);
printFooter(out);

out.close();
  } else {
out.println("Nao deixa os campos em branco!");
out.println
("<BR><BLINK><a href=\"ToDoServlet\">Retornar para Pagina Principal:</a></BLINK>");
  }
 }

 @Override
 public String getServletInfo() {
  return "r4r.co.in-ToDoServlet";
 }

 private void printHeader(PrintWriter out) {
  out.println("<html>");
  out.println("<head>");
  out.println("<title>r4r.co.in-List</title>");
  out.println("</head>");
  out.println("<body>");
  out.println("<h1>ToDo Data list</h1>");
 }

 private void printDataList(PrintWriter out) {
  Iterator iterator = toDoClasseList.iterator();
  while (iterator.hasNext()) {
ToDoClass object = (ToDoClass) iterator.next();
out.println(" <hr align=\"left\" width=\"35%\"/>");
out.println("<BR>Nome da Tarefa : " + " <b> "
            + object.getTitle() + "</b>");
out.println("<BR>Descrição  : " + " <b> "
            + object.getDescription() + "</b>");
out.println("<BR>Prioridade : " + " <b> "
           + object.getPriority() + "</b>");
  }
  try {

Thread.sleep(1000);
  } catch (InterruptedException ex) {
Logger.getLogger(ToDoServlet.class.getName()).
	log(Level.SEVERE,
  "InterruptedException generated while processing request", ex);
  }
 }


 private void printFooter(PrintWriter out) {
  out.println("</body>");
  out.println("</html>");
  out.println("<BR><BR><BLINK><a href=\"ToDoServlet\">Adicionar mais Lista</a></BLINK>");
 }
}


class ToDoClass {


    private String title;
    private String description;
    private String priority;
    private String date;
    
    public ToDoClass
(String title, String description, String priority, String date) 
		{
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}