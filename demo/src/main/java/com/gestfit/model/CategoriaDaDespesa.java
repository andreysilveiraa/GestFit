package com.gestfit.model;

public enum CategoriaDaDespesa {

            ALUGUEL("Aluguel do espaço"),
            MANUTENCAO("Manutenção de equipamentos"),
            SALARIOS("Folha de Pagamento de Funcionários"),
            EQUIPAMENTOS("Compra de Equipamentos");

            private final String descricao;

            CategoriaDaDespesa(String descricao) {
                this.descricao = descricao;
            }

            public String getDescricao() {
                return descricao;
            }

}
