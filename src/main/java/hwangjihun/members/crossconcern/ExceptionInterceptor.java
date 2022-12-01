package hwangjihun.members.crossconcern;

import hwangjihun.members.service.exception.ExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ExceptionInterceptor implements HandlerInterceptor {

    @Autowired
    private ExceptionService exceptionService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if (ex != null) {
            log.info("---------Handler Exception---------", ex);

            String returnValue = exceptionService.sendExceptionToErrorCenter(ex);

            log.info("Receive Exception returnValue !! = {}", returnValue);
        }
    }
}
//TODO 왜 DispatcherService 까지 예외가 올라가지?
