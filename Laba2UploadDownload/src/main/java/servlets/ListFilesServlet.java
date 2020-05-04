package servlets;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/listFilesServlet")
public class ListFilesServlet extends HttpServlet {
    private File dir;
    private final String UPLOAD_DIR = "uploaded";


    @Override
    public void init() throws ServletException {
        super.init();
        String applicationDir = this.getServletContext().getRealPath("");
        this.dir = new File(applicationDir + "/" + UPLOAD_DIR + "/");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        File[] files = dir.listFiles(new FilenameFilter(){
            public boolean accept(File dir, String filename){
                return filename.endsWith(".jpg");
            }
        });
        for (File image: files){
            String imagePath = image.toString();

            pw.println("<p>" + image.getName() + " <a href=\"./downloadServlet/?fileName=" + image.getName() + "\">Download</a></p>");
        }
        pw.println("<p><a href=\"./\">Come back</a></p>");
        pw.println("<p>"+UploadServlet.product+"</p>");
        UploadServlet.product="";

    }
}
