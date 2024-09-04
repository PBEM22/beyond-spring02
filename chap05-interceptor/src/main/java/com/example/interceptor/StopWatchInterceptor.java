package com.example.interceptor;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    /* 인터셉터는 스프링 컨테이너에 존재하는 빈을 의존성 주입 받을 수 있다. */
    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 전처리 메소드로 메소드 실행 시작 시간을 request에 attribute로 저장한다. */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        System.out.println("preHandler 호출함...");

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        /* true이면 컨트롤러를 이어서 호출한다. false이면 핸들러 메소드를 호출하지 않는다. */
        return true;
    }

    /* 후처리 메소드로 메소드 실행 종료 시간을 구해 실행 시작 시간과 연산하여 걸린 시간을 계산한다. */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출함...");

        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");

        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval", endTime - startTime);
    }

    /* 뷰가 렌더링 된 후 호출하는 메소드 */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, @Nullable Exception ex) throws Exception {

        System.out.println("afterComplate 호출함...");

        menuService.method();
    }

}