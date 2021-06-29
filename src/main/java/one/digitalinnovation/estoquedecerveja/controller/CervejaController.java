package one.digitalinnovation.estoquedecerveja.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.estoquedecerveja.dto.CervejaDTO;
import one.digitalinnovation.estoquedecerveja.dto.QuantidadeDTO;
import one.digitalinnovation.estoquedecerveja.exception.CervejaAlreadyRegisteredException;
import one.digitalinnovation.estoquedecerveja.exception.CervejaNotFoundException;
import one.digitalinnovation.estoquedecerveja.exception.CervejaExceededException;
import one.digitalinnovation.estoquedecerveja.service.CervejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CervejaController implements CervejaControllerDocs {
    private final CervejaService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CervejaDTO createBeer(@RequestBody @Valid CervejaDTO beerDTO) throws CervejaAlreadyRegisteredException {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/{name}")
    public CervejaDTO findByName(@PathVariable String name) throws CervejaNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    public List<CervejaDTO> listBeers() {
        return beerService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws CervejaNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public CervejaDTO increment(@PathVariable Long id, @RequestBody @Valid QuantidadeDTO quantityDTO) throws CervejaNotFoundException, CervejaExceededException {
        return beerService.increment(id, quantityDTO.getQuantity());
    }
}

