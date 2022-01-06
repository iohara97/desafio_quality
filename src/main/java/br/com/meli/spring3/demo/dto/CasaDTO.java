package br.com.meli.spring3.demo.dto;

import br.com.meli.spring3.demo.entity.Casa;
import exception.PayloadException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CasaDTO {

    @NotNull(message = "O nome da propriedade não pode estar vazio.")
    @NotEmpty(message = "O nome da propriedade não pode estar vazio.")
    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z].*$", message = "O nome da propriedade deve começar com uma letra maiúscula.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    private String nome;

    @Valid
    private BairroDTO bairroDTO;

    @Valid
    private List<ComodoDTO> comodosDTO;

    public static Casa converte(CasaDTO dto) {
        try{
            Casa casa = Casa.builder()
                .nome(dto.getNome()).bairro(BairroDTO.converte(dto.getBairroDTO()))
                .comodos(ComodoDTO.converte(dto.getComodosDTO()))
                .build();
            return casa;
        } catch (Exception e) {
            throw new PayloadException("");
        }
    }

}
