package one.digitalinnovation.estoquedecerveja.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CervejaExceededException extends Exception  {
    public CervejaExceededException(Long id, int quantityToIncrement) {
        super(String.format("Cervejas com %s ID informado excede a capacidade máxima de estoque: %s", id, quantityToIncrement));
    }
}
