package org.zerock.springex.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
