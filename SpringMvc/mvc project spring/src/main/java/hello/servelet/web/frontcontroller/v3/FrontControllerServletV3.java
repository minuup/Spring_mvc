package hello.servelet.web.frontcontroller.v3;

import hello.servelet.web.frontcontroller.ModelView;
import hello.servelet.web.frontcontroller.MyView;
import hello.servelet.web.frontcontroller.v2.ControllerV2;
import hello.servelet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servelet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servelet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servelet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servelet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name ="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();


    //생성자안에 이렇게 넣어놔서 호출되면 아래와 같이 실행되게끔 함.
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paraMap을 넘겨줘야함

        Map<String, String> paraMap = createParaMap(request);

        ModelView mv = controller.process(paraMap);

        String viewName = mv.getViewName();//논리이름 new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParaMap(HttpServletRequest request) {
        Map<String, String> paraMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paraMap.put(paramName, request.getParameter(paramName)));
        return paraMap;
    }
}
