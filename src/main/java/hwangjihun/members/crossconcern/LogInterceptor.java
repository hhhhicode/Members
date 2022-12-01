package hwangjihun.members.crossconcern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uuid = UUID.randomUUID().toString();
        String requestURI = request.getRequestURI();

        request.setAttribute(LOG_ID, uuid);

        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String uuid = (String) request.getAttribute(LOG_ID);
        String requestURI = request.getRequestURI();

        log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, handler);
    }
}
