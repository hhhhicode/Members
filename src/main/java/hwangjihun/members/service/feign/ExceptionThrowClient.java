package hwangjihun.members.service.feign;

import hwangjihun.members.model.exception.MyException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "errorRequest", url = "http://localhost:8090")
public interface ExceptionThrowClient {

    @PostMapping(value = "/ex/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    String postThrowException(MyException e);
}
