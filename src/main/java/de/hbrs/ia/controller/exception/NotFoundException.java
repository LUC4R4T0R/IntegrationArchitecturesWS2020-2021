package de.hbrs.ia.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No object with the given params was found in the database!")
public class NotFoundException extends RuntimeException{
}
