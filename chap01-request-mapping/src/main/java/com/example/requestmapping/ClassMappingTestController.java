package com.example.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    @GetMapping("/regist")
    public String registOrder(Model model){
        model.addAttribute("message", "GET 방식의 주문 등록용 핸들러 메소드 호출");

        return "mappingResult";
    }

    /* 여러 개의 패턴 매핑 */
    @RequestMapping(value = {"/modify", "/delete"}, method= RequestMethod.POST)
    public String modifyAndDeleteOrder(Model model){
        model.addAttribute("message", "POST 방식의 주문 수정, 삭제 공통 핸들러 메소드 호출");

        return "mappingResult";
    }

    /* path variable : 요청 주소에 포함 된 변수*/
    @GetMapping("/detail/{orderNo}")
    public String selectOneOrderDetail(Model model, @PathVariable("orderNo") String orderNo){
        model.addAttribute("message", orderNo + "번 주문 상세 내용 조회용 핸들러 메소드 호출");

        return "mappingResult";
    }
    
    /* 아무런 URL을 설정하지 않으면 요청 처리에 대한 핸들러 메소드가 준비 되지 않았을 때 해당 메소드를 매핑한다. */
    @RequestMapping
    public String otherRequest(Model model){
        model.addAttribute("message", "order 요청이긴 하지만 다른 기능은 아직 준비되지 않음");

        return "mappingResult";
    }
}
