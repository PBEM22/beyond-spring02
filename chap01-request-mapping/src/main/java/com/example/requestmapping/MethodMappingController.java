package com.example.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/* DispatcherServlet 은 웹 요청을 받는 즉시 @Controller가 달린 컨트롤러 클래스에 처리를 위임한다.
* 그 과정은 컨트롤러 클래스의 핸들러 메서드에 선언 된 다양한 @RequestMapping 설정 내용에 */
@Controller
public class MethodMappingController {

    @RequestMapping("/menu/regist")
    public String menuRegist(Model model){

        /* Model 객체에서 addAttribute 메서드를 이용해 key, value를 추가하면
        * View에서 사용할 수 있다. */
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출");

        /* 반환하고자 하는 View의 경로를 포함한 이름을 작성한다.
        * resources/templates 하위부터의 경로를 작성한다. */
        return "mappingResult";
    }

    /* 2. 요청 메소드 지정 */
    @RequestMapping(value = "/menu/modify", method = RequestMethod.POST)
    public String menuModify(Model model){

        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출");

        return "mappingResult";
    }

    /* 3. 요청 메소드 전용 어노테이션 */
//    @GetMapping("/menu/delete")
//    public String

}
