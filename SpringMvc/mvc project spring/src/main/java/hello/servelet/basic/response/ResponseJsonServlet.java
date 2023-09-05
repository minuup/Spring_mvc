package hello.servelet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servelet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("Kim");
        helloData.setAge(20);

        //{"username" : "kim", "age" : 20}

        //객체를 받아서 문자로 바꿔라
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);


    }
}
