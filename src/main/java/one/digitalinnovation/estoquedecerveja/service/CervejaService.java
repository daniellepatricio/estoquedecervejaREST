package one.digitalinnovation.estoquedecerveja.service;


import lombok.AllArgsConstructor;
import one.digitalinnovation.estoquedecerveja.dto.CervejaDTO;
import one.digitalinnovation.estoquedecerveja.entity.Cerveja;
import one.digitalinnovation.estoquedecerveja.exception.CervejaAlreadyRegisteredException;
import one.digitalinnovation.estoquedecerveja.exception.CervejaNotFoundException;
import one.digitalinnovation.estoquedecerveja.exception.CervejaExceededException;
import one.digitalinnovation.estoquedecerveja.mapper.CervejaMapper;
import one.digitalinnovation.estoquedecerveja.repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CervejaService {

    private final CervejaRepository beerRepository;
    private final CervejaMapper beerMapper = CervejaMapper.INSTANCE;

    public CervejaDTO createBeer(CervejaDTO beerDTO) throws CervejaAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(beerDTO.getName());
        Cerveja beer = beerMapper.toModel(beerDTO);
        Cerveja savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }

    public CervejaDTO findByName(String name) throws CervejaNotFoundException {
        Cerveja foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new CervejaNotFoundException(name));
        return beerMapper.toDTO(foundBeer);
    }

    public List<CervejaDTO> listAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws CervejaNotFoundException {
        verifyIfExists(id);
        beerRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws CervejaAlreadyRegisteredException {
        Optional<Cerveja> optSavedBeer = beerRepository.findByName(name);
        if (optSavedBeer.isPresent()) {
            throw new CervejaAlreadyRegisteredException(name);
        }
    }

    private Cerveja verifyIfExists(Long id) throws CervejaNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new CervejaNotFoundException(id));
    }

    public CervejaDTO increment(Long id, int quantityToIncrement) throws CervejaNotFoundException, CervejaExceededException {
        Cerveja beerToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
            Cerveja incrementedBeerStock = beerRepository.save(beerToIncrementStock);
            return beerMapper.toDTO(incrementedBeerStock);
        }
        throw new CervejaExceededException(id, quantityToIncrement);
    }

}
