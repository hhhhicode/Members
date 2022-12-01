package hwangjihun.members.model.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

@Component
public class MyExceptionUtils {
    @Value("${projectId}")
    private int projectId;

    public MyException getMyException(Exception e) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String exceptionStackTrace = getExceptionStackTrace(e);
        return new MyException(projectId, timestamp, exceptionStackTrace);
    }

    private String getExceptionStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);

        printWriter.flush();
        return stringWriter.toString();
    }
}
