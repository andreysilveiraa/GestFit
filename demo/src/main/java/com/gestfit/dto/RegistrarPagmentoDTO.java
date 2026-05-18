package com.gestfit.dto;


import com.gestfit.model.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistrarPagmentoDTO(

        @NotNull(message = "O ID do pagamento é obrigatório")
        Long idPagamento,

        @Positive(message = "O valor do pagamento deve ser positivo")
        double valor,

        @NotNull(message = "A forma de pagamento é obrigatória")
        FormaPagamento formaPagamento
) { }
