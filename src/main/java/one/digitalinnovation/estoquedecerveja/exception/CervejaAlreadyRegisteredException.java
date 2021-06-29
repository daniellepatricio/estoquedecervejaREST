package one.digitalinnovation.estoquedecerveja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CervejaAlreadyRegisteredException extends Exception{

    public CervejaAlreadyRegisteredException(String beerName) {
        super(String.format("Cerveja com o nome %s já existe no sistema.", beerName));
    }
}
