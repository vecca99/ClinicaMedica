package med.voll.api.infra.errores;

import jakarta.validation.ValidationException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(EntityActionVetoException.class)
    public ResponseEntity tartaError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tartaError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacioDeIntegridad(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacioDeNegocio(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }


}
