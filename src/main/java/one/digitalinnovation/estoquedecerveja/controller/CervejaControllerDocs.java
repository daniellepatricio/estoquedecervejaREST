package one.digitalinnovation.estoquedecerveja.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import one.digitalinnovation.estoquedecerveja.dto.CervejaDTO;
import one.digitalinnovation.estoquedecerveja.dto.QuantidadeDTO;
import one.digitalinnovation.estoquedecerveja.exception.CervejaAlreadyRegisteredException;
import one.digitalinnovation.estoquedecerveja.exception.CervejaNotFoundException;
import one.digitalinnovation.estoquedecerveja.exception.CervejaExceededException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Gerenciar Estoque Cervejas")
public interface CervejaControllerDocs {
    @ApiOperation(value = "Beer creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cerveja criada com sucesso."),
            @ApiResponse(code = 400, message = "Campos obrigatórios faltando ou valor de intervalo  errado de campo.")
    })
    CervejaDTO createBeer(CervejaDTO beerDTO) throws CervejaAlreadyRegisteredException;

    @ApiOperation(value = "Returns beer found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cerveja encontrada no sistema com sucesso."),
            @ApiResponse(code = 404, message = "Cerveja com esse nome não foi encontrada.")
    })
    CervejaDTO findByName(@PathVariable String name) throws CervejaNotFoundException;

    @ApiOperation(value = "Retorna uma lista de todas as cervejas registradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de todas as cervejas registradas no sistema."),
    })
    List<CervejaDTO> listBeers();

    @ApiOperation(value = "Deletar uma cerveja encontrada com um id válido")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cerveja deletada do sistema com sucesso"),
            @ApiResponse(code = 404, message = "Cerveja com esse id não foi encontrada.")
    })
    void deleteById(@PathVariable Long id) throws CervejaNotFoundException;
}

