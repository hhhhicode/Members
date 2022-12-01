package hwangjihun.members.service.exception;

import hwangjihun.members.model.exception.MyException;
import hwangjihun.members.model.exception.MyExceptionUtils;
import hwangjihun.members.service.feign.ExceptionThrowClient;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    private final ExceptionThrowClient client;
    private final MyExceptionUtils myExceptionUtils;

    public ExceptionService(ExceptionThrowClient client, MyExceptionUtils myExceptionUtils) {
        this.client = client;
        this.myExceptionUtils = myExceptionUtils;
    }

    /**
     * Members -> Error Center -> Members
     * @param e
     * @return ExceptionMessage
     */
    public String sendExceptionToErrorCenter(Exception e) {
        MyException myException = myExceptionUtils.getMyException(e);
        String returnValue = client.postThrowException(myException);

        return returnValue;
    }
}
