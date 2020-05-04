package servlets;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import writers.ImageWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/uploadServlet")
public class UploadServlet extends HttpServlet {

    private String dir;
    private ServletFileUpload upload;
    private ImageWriter writer;
    private final String UPLOAD_DIR = "uploaded";
    public static String product = "";


    @Override
    public void init() throws ServletException {
        super.init();
        String applicationDir = this.getServletContext().getRealPath("");//E:\STUDIES\RazrabotkaWebPrils\Laba2UploadDownload\out\artifacts\web_war_exploded\
        this.dir = applicationDir + "/" + UPLOAD_DIR + "/";//E:\STUDIES\RazrabotkaWebPrils\Laba2UploadDownload\out\artifacts\web_war_exploded\/uploaded/

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();///web_war_exploded
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");//web_war_exploded
        factory.setRepository(repository);

        // Create a new file upload handler
        this.upload = new ServletFileUpload(factory);
        writer = new ImageWriter(this.dir);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FileItem> items = upload.parseRequest(request);
            String extensionForChecking=".jpg";

            if(items.get(0).getName().substring(items.get(0).getName().length()-4).compareTo(extensionForChecking)!=0)
            {
                product = "Incorrect format, file not uploaded";
                request.setAttribute("product", product);
                System.out.println("Incorrect format, file not uploaded");
            }

            else {
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                writer.write(item);
                System.out.println("Succesfully uploaded file ");
            }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/listFilesServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
