package one.digitalinnovation.estoquedecerveja.builder;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import one.digitalinnovation.estoquedecerveja.dto.CervejaDTO;
import one.digitalinnovation.estoquedecerveja.enums.CervejaType;

@Builder
public class CervejaDTOBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Brahma";

    @Builder.Default
    private String brand = "Ambev";

    @Builder.Default
    private int max = 50;

    @Builder.Default
    private int quantity = 10;

    @Builder.Default
    private CervejaType type = CervejaType.LAGER;

    public CervejaDTO toBeerDTO() {
        return new CervejaDTO(id,
                name,
                brand,
                max,
                quantity,
                type);
    }
}
