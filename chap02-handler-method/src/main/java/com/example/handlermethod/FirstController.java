package com.example.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
@Controller
@RequestMapping
public class FirstController {

    /* 핸들러 메서드의 반환 값을 void로 설정하면 요청 주소가 곧 view의 이름이 된다.
     * => /first/regist 뷰를 응답한다.
     * => resources/templates/first/regist.html 파일을 만들어서 작업한다.
     * */
    @GetMapping("/regist")
    public void regist(){}

    /* 핸들러 메서드에 파라미터로 특정 몇 가지 타입을 선언하게 되면 핸들러 메서드 호출 시 인자로 값을 전달해준다. */

    /* 1. WebRequest로 요청 파라미터 전달 받기
     * HttpServletRequest/Response, ServletRequest/ServletResponse도 핸들러 메소드 매개변수로 사용 가능하지만
     * WebRequest가 Servlet에 종속적이지 않으므로 Spring 기반의 프로젝트에서 더 자주 사용 된다.
     * */
    @PostMapping("/regist")
    public String registMenu(WebRequest request, Model model) {

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 " + price
                + "원으로 등록하였습니다.";
        model.addAttribute("message", message);

        return "messagePrinter";
    }

    @GetMapping("/modify")
    public void modify() {}

    /* 2. @RequestParam으로 요청 파라미터 전달 받기
     * 요청 파라미터를 매핑하여 핸들러 메소드 호출 시 값을 넣어주는 어노테이션으로 매개변수 앞에 작성한다.
     * name 속성과 매개변수 명이 다른 경우 @RequestParam("name") 으로 설정한다. (어노테이션 생략도 가능하다.)
     *
     * 전달하는 name 속성과 일치하는 것이 없는 경우 400 에러가 발생하는데 이는 required 속성의 기본 값이 true이기 때문이다.
     * 해당 속성을 false로 설정하면 값이 존재하지 않을 경우 null 처리가 된다.
     *
     * 값을 입력하지 않고 넘기면 "" 빈 문자열이 요청으로 넘어오는데 이 때 parsing 관련 에러가 날 수 잇다.
     * defaultValue 속성을 이용하면 기본 값 설정이 가능하다.
     * */
    @PostMapping("/modify")
    public String modifyMenu(
            @RequestParam(value = "nam", required = false) String modifyName,
            @RequestParam(value = "price", defaultValue = "0") int modifyPrice,
            Model model
    ) {

        String message = modifyName + " 메뉴의 가격을 " + modifyPrice + "원으로 변경하였습니다.";
        model.addAttribute("message", message);

        return "messagePrinter";
    }

    /* 파라미터가 여러 개인 경우 Map 으로 한 번에 처리할 수 있다. Map의 key는 name 속성이 된다. */
    @PostMapping("/modifyAll")
    public String modifyAllMenu(@RequestParam Map<String, String> parameters, Model model) {

        String name = parameters.get("name");
        int price = Integer.parseInt(parameters.get("price"));

        String message = name + " 메뉴의 가격을 " + price + "원으로 변경하였습니다.";
        model.addAttribute("message", message);

        return "messagePrinter";
    }

    @GetMapping("/search")
    public void search(){}

    /* 3. @ModelAttribute를 이용하는 방법
    * DTO 같은 모델을 커멘드 객체로 전달 받는 어노테이션으로 Model에 담기는 작업도 자동으로 일어난다.
    * 단 @ModelAttribute("key")를 지정하지 않으면 타입이 앞글자를 소문자로 하여 저장 된다. 어노테이션 생략도 가능하다. */
    @PostMapping("/search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu){

        return "searchResult";
    }


}