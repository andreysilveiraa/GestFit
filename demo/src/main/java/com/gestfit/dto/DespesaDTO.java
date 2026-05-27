package com.gestfit.dto;

import com.gestfit.model.CategoriaDaDespesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record DespesaDTO(

        @NotBlank(message = "A descrição da despesa é obrigatória")
        String descricao,


        @NotNull(message = "O valor não pode ser nulo")
        Double valor,

        @NotNull(message = "A data de vencimento é obrigatória")
        LocalDate dataVencimento,

        @NotNull(message = "A categoria da despesa é obrigatória")
        CategoriaDaDespesa categoria


) { }
