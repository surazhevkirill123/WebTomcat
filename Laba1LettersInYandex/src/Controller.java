import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Controller", urlPatterns = {""})
public class Controller extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String messageText = request.getParameter("messageText");
        String senderAddress = request.getParameter("senderAddress");

        String recipientAddress = request.getParameter("recipientAddress");

        /*String messageText = "messageText";
        String senderAddress = "kirillsurazhev@yandex.by";
        String password = "Ivatsevichi123";
        String recipientAddress = "kirillsurazhev@yandex.by";*/

        int flag=0;
        try {
            String addressStringChecker1=senderAddress.substring(senderAddress.length()-9).trim();
            String addressStringChecker2=recipientAddress.substring(recipientAddress.length()-9).trim();
            String yandex="yandex.by";

            String product = "";
            if(addressStringChecker1.compareTo(yandex)==0 && addressStringChecker2.compareTo(yandex)==0) {
                HelloEmail.main("spam","gur.lagann@yandex.ru","kirillsurazhev@yandex.by");
                HelloEmail.main(messageText,senderAddress,recipientAddress);
                product = "Сообщение успешно отправлено";
                request.setAttribute("product", product);
                System.out.println("Сообщение успешно отправлено");
                flag=1;
            }
            if(addressStringChecker1.compareTo(yandex)!=0 && addressStringChecker2.compareTo(yandex)==0){
                product = "Первый из введённых адресов не является почтовым адресом, введите ещё раз";
                request.setAttribute("product", product);
                System.out.println("Первый из введённых адресов не является почтовым адресом, введите ещё раз");
                flag=1;
            }
            if(addressStringChecker1.compareTo(yandex)==0 && addressStringChecker2.compareTo(yandex)!=0){
                product = "Второй из введённых адресов не является почтовым адресом, введите ещё раз";
                request.setAttribute("product", product);
                System.out.println("Первый из введённых адресов не является почтовым адресом, введите ещё раз");
                flag=1;
            }
            if(addressStringChecker1.compareTo(yandex)!=0 && addressStringChecker2.compareTo(yandex)!=0){
                product = "Оба введённых адреса не являются почтовыми адресами, введите ещё раз";
                request.setAttribute("product", product);
                System.out.println("Оба введённых адреса не являются почтовыми адресами, введите ещё раз");
                flag=1;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            if(flag==0 && messageText!=null){
                String product = "Неверные адреса, введите ещё раз";
                request.setAttribute("product", product);
                System.out.println("Неверные адреса, введите ещё раз");
            }


        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}

