
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/NotesList"})
public class NotesList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reg = "НЕТ ЗАПИСЕЙ";

        DbNotesList ch = new DbNotesList();

        if (ch.conf) {

            for (int i = 0; i < ch.notesIds.size(); i++) {

                if (i == 0) {
                    reg = ch.notesIds.get(i);
                } else {
                    reg = reg + "&" + ch.notesIds.get(i);
                }

            }

        }

        response.setContentType("text/plain"); 
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(reg);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
