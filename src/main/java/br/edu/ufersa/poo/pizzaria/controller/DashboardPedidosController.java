package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.builder.Builder;
import br.edu.ufersa.poo.pizzaria.builder.PedidoBuilderImpl;
import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.*;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DashboardPedidosController {
    @FXML private TextField cliente;
    @FXML private TextField sabor;
    @FXML private TitledPane pedidoAdicionais;
    @FXML private Button buttonFazerPedidos;
    @FXML private TextField procurar;
    @FXML private MenuButton escolher;
    @FXML private ScrollPane infoPedidos;
    @FXML private AnchorPane root;
    @FXML private VBox listaAdicionais;
    @FXML private ChoiceBox checkTipo;


        // Dados e serviços
        private final Builder pedidoBuilder = new PedidoBuilderImpl();

        @FXML
        public void initialize() {/*
            configurarComponentes();
            carregarDadosIniciais();*/
            FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
                try {
                AnchorPane sidebar = sidebarLoader.load();
                root.getChildren().add(sidebar);
            } catch (
            IOException e) {
                throw new RuntimeException(e);
            }

            AdicionalService adicionalService = new AdicionalServiceImpl(EMSingleton.getInstance());
            List<Adicional>  adicionais = new ArrayList<>(adicionalService.getAll());
            for(Adicional adicional: adicionais) {
                CheckBox checkBox = new CheckBox(adicional.getNome());
                listaAdicionais.getChildren().add(checkBox);
            }

        }

        private void configurarComponentes() {
            // Configuração do MenuButton de filtro
            MenuItem filtrarPorCliente = new MenuItem("Cliente");
            MenuItem filtrarPorSabor = new MenuItem("Sabor");
            escolher.getItems().addAll(filtrarPorCliente, filtrarPorSabor);

            // Eventos
            buttonFazerPedidos.setLocation(event -> criarPedido());
            filtrarPorCliente.setLocation(event -> filtrarPedidos("cliente"));
            filtrarPorSabor.setLocation(event -> filtrarPedidos("sabor"));
        }

        private void carregarDadosIniciais() {
            // Carrega adicionais disponíveis
            List<Adicional> adicionais = pedidoService.listarAdicionais();
            adicionais.forEach(adicional -> {
                CheckBox checkBox = new CheckBox(adicional.getNome());
                adicionaisPane.getChildren().add(checkBox);
                adicionaisCheckboxes.add(checkBox);
            });
        }

        @FXML
        private void criarPedido() {
            PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
            try {
                // 1. Obter adicionais selecionados
                List<Adicional> adicionaisSelecionados = new ArrayList<>();
                for (Node node : listaAdicionais.getChildren()) {
                    // Verifica se o node é uma CheckBox
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node; // Faz o casting

                        // Se estiver selecionado, busca o adicional correspondente
                        if (checkBox.isSelected()) {
                            List<Adicional> adicionais = new AdicionalServiceImpl(EMSingleton.getInstance()).getAll();
                            Adicional adicional = null;
                            for(Adicional a: adicionais) {
                                if(a.getNome().equals(checkBox.getText())) {
                                    adicional = a;
                                    return;
                                }
                            }
                            if (adicional != null) {
                                adicionaisSelecionados.add(adicional);
                            }
                        }
                    }
                }

                // 2. Construir pedido com Builder
                Pedido novoPedido = pedidoBuilder
                        .withCliente(parseCliente(cliente.getText()))
                        .withPizza(parsePizza(sabor.getText()))
                        .withAdicionais(adicionaisSelecionados)
                        .withEstado(Estado.NOVO)
                        .withTamanho(Tamanho.M) // Padrão
                        .withData(Date.valueOf(LocalDate.now()))
                        .build();

                // 3. Persistir
                pedidoService.register(novoPedido);

                // 4. Atualizar UI e limpar campos
                atualizarListaPedidos();
                limparCampos();

                mostrarSucesso("Pedido criado com sucesso!");

            } catch (Exception e) {
                mostrarErro("Erro ao criar pedido: " + e.getMessage());
            }
        }

        private void filtrarPedidos(String criterio) {
            PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
            String termo = procurar.getText();
            List<Pedido> pedidosFiltrados;

            switch (criterio) {
                case "cliente":
                    String cpf = new ClienteServiceImpl(EMSingleton.getInstance()).getAll().stream().filter(cliente ->cliente.getNome().equals(termo)).toList().getFirst().getNome();
                    Cliente clienteCpf = new Cliente();
                    clienteCpf.setCpf(cpf);
                    pedidosFiltrados = pedidoService.getByCliente(clienteCpf);
                    break;
                case "sabor":
                    pedidosFiltrados = pedidoService.getAll().stream().filter(pedido -> pedido.getPizza().getPizza().getNome().equals(termo)).toList();
                    break;
                default:
                    pedidosFiltrados = pedidoService.getAll();
            }

            exibirPedidos(pedidosFiltrados);
        }

        // Métodos auxiliares
        private void atualizarListaPedidos() {
            PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
            exibirPedidos(pedidoService.getAll());
        }

        private void exibirPedidos(List<Pedido> pedidos) {
            // Implemente a exibição na ScrollPane (ListView ou TableView)
        }

        private void limparCampos() {
            cliente.clear();
            sabor.clear();
            listaAdicionais.getChildren().forEach(cb -> {
                if(cb instanceof CheckBox) {
                    CheckBox check = (CheckBox) cb;
                    check.setSelected(false);
                }
            });
        }

        private void mostrarSucesso(String mensagem) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setContentText(mensagem);
            alert.show();
        }

        private void mostrarErro(String mensagem) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText(mensagem);
            alert.show();
        }

        // Métodos para converter texto em objetos (implemente conforme sua lógica)
        private Cliente parseCliente(String texto) {
            String cpf = new ClienteServiceImpl(EMSingleton.getInstance()).getAll().stream().filter(cliente ->cliente.getNome().equals(texto)).toList().getFirst().getNome();
            Cliente clienteCpf = new Cliente();
            clienteCpf.setCpf(cpf);
            return new ;
        }

        private Pizza parsePizza(String texto) {
            return new PedidoServiceImpl(EMSingleton.getInstance()).getAll().stream().filter(pizza -> {
                pizza.getPizza().getPizza().getNome().equals(texto);
            });
        }
    }

}
}
