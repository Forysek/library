package com.library.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Provided title not found in the database")
public class TitleNameNotFoundException extends Exception{
}
