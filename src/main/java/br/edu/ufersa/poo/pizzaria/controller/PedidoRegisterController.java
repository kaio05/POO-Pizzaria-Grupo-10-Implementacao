package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.builder.PedidoBuilderImpl;
import br.edu.ufersa.poo.pizzaria.builder.Builder;
import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.PedidoService;
import br.edu.ufersa.poo.pizzaria.model.services.PedidoServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class PedidoRegisterController {
    @FXML private Pane root;
    @FXML private Label nome;
    @FXML private Label sabor;
    @FXML private Label adicional;
    @FXML private Label valor;
    @FXML private ImageView editar;
    @FXML private ImageView excluir;

    private Builder pedidoBuilder;
    private PedidoService pedidoService;
    private Pedido pedidoAtual;

    public void initialize() {
        this.pedidoBuilder = new PedidoBuilderImpl();
        this.pedidoService = new PedidoServiceImpl();

        configurarEventos();
    }

    private void configurarEventos() {
        editar.setOnMouseClicked(event -> editarPedido());
        excluir.setOnMouseClicked(event -> excluirPedido());
    }

    public void carregarPedido(Pedido pedido) {
        this.pedidoAtual = pedido;

        nome.setText(pedido.getCliente().getNome());
        sabor.setText(pedido.getPizza().getPizza());
        adicional.setText(formatarAdicionais(pedido.getAdicional()));
    }

    private String formatarAdicionais(List<Adicional> adicionais) {
        return adicionais.stream()
                .map(Adicional::getNome)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sem adicionais");
    }


    @FXML
    private void editarPedido() {
        try {
            Pedido pedidoEditado = pedidoBuilder
                    .withCliente(pedidoAtual.getCliente())
                    .withPizza(pedidoAtual.getPizza())
                    .withAdicionais(pedidoAtual.getAdicional())
                    .build();
            pedidoService.update(pedidoEditado);

            carregarPedido(pedidoEditado);

        } catch (Exception e) {
            mostrarErro("Erro ao editar pedido: " + e.getMessage());
        }
    }

    @FXML
    private void excluirPedido() {
        try {
            pedidoService.delete(pedidoAtual.getId());
            root.setVisible(false);
        } catch (Exception e) {
            mostrarErro("Erro ao excluir pedido: " + e.getMessage());
        }
    }

    private void mostrarErro(String mensagem) {
        System.err.println(mensagem);
    }
}
