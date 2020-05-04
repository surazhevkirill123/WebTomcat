package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

@WebServlet(urlPatterns = "/downloadServlet/*")
public class DownloadServlet extends HttpServlet {
    private File dir;
    private final String UPLOAD_DIR = "uploaded";

    @Override
    public void init() throws ServletException {
        super.init();
        String applicationDir = this.getServletContext().getRealPath("");//возвращает E:\STUDIES\RazrabotkaWebPrils\Laba2UploadDownload\out\artifacts\web_war_exploded\
        this.dir = new File(applicationDir + "/" + UPLOAD_DIR + "/");//возвращает uploaded
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fileName = request.getParameter("fileName");//возвращает имя файла, сконфигурированное классом Imagewriter(191ee207-7168-4217-9a54-a786a8a718d5.jpg)
            File[] filteredDir = getFile(fileName);//заносим в массив загружаемый файл
            if (filteredDir.length == 0) {
                throw new ServletException("File doesn't exist on server");
            }
            File fileToDownload = filteredDir[0];//загруженный файл

            ServletContext ctx = getServletContext();//web_war_exploded
            InputStream fis = new FileInputStream(fileToDownload);//255
            String mimeType = ctx.getMimeType(fileToDownload.getAbsolutePath());//jpeg
            response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
            response.setContentLength((int) fileToDownload.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            //создаём ServletOutputStream для загрузки файла и проделываем нужные для этого операции
            ServletOutputStream os = response.getOutputStream();
            byte[] bufferData = new byte[1024];
            int read = 0;
            while ((read = fis.read(bufferData)) != -1) {
                os.write(bufferData, 0, read);
            }
            os.flush();
            os.close();
            fis.close();
            System.out.println("File downloaded at client successfully");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File[] getFile(String fileName){//делаем массив, состоящий из файла, используя путь файла
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String file) {
                return file.equals(fileName);
            }
        });

        return files;
    }
}
