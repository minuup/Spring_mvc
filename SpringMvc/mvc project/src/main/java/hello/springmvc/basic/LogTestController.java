package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());
//@Slf4j 이걸 넣음으로써 위에 있는걸 안써도됨


    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";


//        System.out.println("name = " + name);
//        운영시스템에선 print하면 안됨, 항상 다 남기니까

//       log.trace(" trace log = " + name)해도 찍히지만 이렇게 쓰면안됨
//        자바언어는 trace가 호출되기전에 안에있는 문자들의 더하기를 먼저해버림
//        연산이 일어나면서 메모리, CPU를 사용 그래서 문제가됨
//        트레이스 로그를 사용하지도 않는데 쓸모없이 리소스를 사용하게됨

//        이것은 파라미터로 넘기는거라서 출력안하면 안씀.연산이 안일어남
        log.trace(" trace log = {}", name);
        log.debug(" debug log = {}", name);
        log.info(" info log={}", name);
        log.warn(" warn log = {}", name);
        log.error(" error log = {}", name);



        return "Ok";
    }

}
