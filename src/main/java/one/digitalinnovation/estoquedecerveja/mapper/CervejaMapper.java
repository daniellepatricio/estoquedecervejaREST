package one.digitalinnovation.estoquedecerveja.mapper;

import one.digitalinnovation.estoquedecerveja.dto.CervejaDTO;
import one.digitalinnovation.estoquedecerveja.entity.Cerveja;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CervejaMapper{
    CervejaMapper INSTANCE = Mappers.getMapper(CervejaMapper.class);

    Cerveja toModel(CervejaDTO beerDTO);

    CervejaDTO toDTO(Cerveja beer);
}
