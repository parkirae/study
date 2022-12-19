package org.zerock.springex.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Log4j2
@ControllerAdvice
public class CommonExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String excepNumber(NumberFormatException numberFormatException) {
        log.error("---");
        log.error(numberFormatException.getMessage());

        return "NUMBER FORMAT EXCEPTION";
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String excepCommon(Exception exception) {
        log.error("---");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>" + exception.getMessage() + "</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }
}
