package jsp.SpringBoot;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exp){
		ResponseStructure<String> str = new ResponseStructure<String>();
		
		str.setStatusCode(HttpStatus.NOT_FOUND.value());
		str.setMessage("Not Found");
		str.setData(exp.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(str, HttpStatus.NOT_FOUND);
	}

}
