package br.edu.ufersa.poo.pizzaria.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.UUID;

public class PizzaRegisterController {

    @FXML private Label sabor;
    @FXML private Label valor;

    // Construtor privado para uso interno
    private PizzaRegisterController() {}

    /**
     * Builder para PizzaRegisterController seguindo o mesmo padrão do PizzaBuilderImpl
     */
    public static class Builder {
        private UUID id;
        private String nomeSabor;
        private double valorPizza;
        private String estiloCard;

        public Builder() {
            // Gera um ID padrão como no PizzaBuilderImpl
            this.id = UUID.randomUUID();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withSabor(String nomeSabor) {
            if (nomeSabor == null || nomeSabor.isEmpty()) {
                throw new IllegalArgumentException("Nome do sabor não pode ser nulo ou vazio");
            }
            this.nomeSabor = nomeSabor;
            return this;
        }

        public Builder withValor(double valorPizza) {
            if (valorPizza <= 0) {
                throw new IllegalArgumentException("Valor da pizza deve ser positivo");
            }
            this.valorPizza = valorPizza;
            return this;
        }

        public Builder withEstilo(String estiloCSS) {
            this.estiloCard = estiloCSS;
            return this;
        }

        public Pane build() throws IllegalStateException {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/br/edu/ufersa/poo/pizzaria/PizzaRegister.fxml"));
                Pane pizzaCard = loader.load();

                PizzaRegisterController controller = loader.getController();
                controller.sabor.setText(nomeSabor);
                controller.valor.setText(String.format("R$ %.2f", valorPizza));

                if (estiloCard != null) {
                    pizzaCard.setStyle(estiloCard);
                }

                return pizzaCard;

            } catch (IOException e) {
                throw new IllegalStateException("Falha ao construir o card de pizza", e);
            }
        }
    }

    // Métodos para atualização dinâmica
    public void atualizarSabor(String novoSabor) {
        if (novoSabor == null || novoSabor.isEmpty()) {
            throw new IllegalArgumentException("Nome do sabor não pode ser nulo ou vazio");
        }
        this.sabor.setText(novoSabor);
    }

    public void atualizarValor(double novoValor) {
        if (novoValor <= 0) {
            throw new IllegalArgumentException("Valor da pizza deve ser positivo");
        }
        this.valor.setText(String.format("R$ %.2f", novoValor));
    }
}
