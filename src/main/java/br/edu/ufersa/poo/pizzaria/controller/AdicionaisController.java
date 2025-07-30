package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.model.services.AdicionalService;
import br.edu.ufersa.poo.pizzaria.model.services.AdicionalServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import br.edu.ufersa.poo.pizzaria.view.Tela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class AdicionaisController {
    @FXML private AnchorPane root;
    @FXML private TextField txtNovoNomeAdicional;
    @FXML private TextField txtNovoValorAdicional;
    @FXML private TextField txtNomeAdicional;
    @FXML private TextField txtValorAdicional;
    @FXML private TextField txtCodigoAdicional;
    @FXML private Button btnNovoAdicional;
    @FXML private Button buttonEditarCliente;
    @FXML private Label erro;
    @FXML private VBox clienteContainer;
    
    private AdicionalService adicionalService;
    private Adicional currentAdicional;
    
    @FXML public void initialize() {
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        try {
            AnchorPane sidebar = sidebarLoader.load();
            root.getChildren().add(sidebar);
            
            // Initialize service
            adicionalService = new AdicionalServiceImpl(EMSingleton.getInstance());
            
            // Add event listeners for search fields
            txtNomeAdicional.setOnKeyReleased(this::handleSearch);
            txtValorAdicional.setOnKeyReleased(this::handleSearch);
            txtCodigoAdicional.setOnKeyReleased(this::handleSearch);
            
            // Add event listener for new adicional button
            btnNovoAdicional.setOnAction(event -> createNewAdicional());
            
            // Load existing adicionais
            loadAdicionais();
            
            // Hide error message initially
            erro.setVisible(false);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void handleSearch(KeyEvent event) {
        loadAdicionais();
    }
    
    private void loadAdicionais() {
        // Clear the container
        clienteContainer.getChildren().clear();
        
        // Get search criteria
        String nameFilter = txtNomeAdicional.getText().toLowerCase();
        String valueFilter = txtValorAdicional.getText().toLowerCase();
        String codeFilter = txtCodigoAdicional.getText().toLowerCase();
        
        // Get all adicionais
        List<Adicional> adicionais = adicionalService.getAll();
        
        // Filter adicionais based on search criteria
        for (Adicional adicional : adicionais) {
            boolean nameMatch = nameFilter.isEmpty() || adicional.getNome().toLowerCase().contains(nameFilter);
            boolean valueMatch = valueFilter.isEmpty() || String.valueOf(adicional.getValor()).contains(valueFilter);
            boolean codeMatch = codeFilter.isEmpty() || adicional.getCodigo().toLowerCase().contains(codeFilter);
            
            if (nameMatch && valueMatch && codeMatch) {
                // Create a container for this adicional
                HBox adicionalBox = createAdicionalBox(adicional);
                clienteContainer.getChildren().add(adicionalBox);
            }
        }
    }
    
    private HBox createAdicionalBox(Adicional adicional) {
        HBox box = new HBox(10);
        box.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: #ddd; -fx-border-width: 0 0 1 0;");
        
        VBox infoBox = new VBox(5);
        Text nameText = new Text("Nome: " + adicional.getNome());
        Text valueText = new Text("Valor: R$ " + adicional.getValor());
        Text codeText = new Text("Código: " + adicional.getCodigo());
        infoBox.getChildren().addAll(nameText, valueText, codeText);
        
        Button editButton = new Button("Editar");
        editButton.setOnAction(event -> editAdicional(adicional));
        
        Button deleteButton = new Button("Excluir");
        deleteButton.setOnAction(event -> deleteAdicional(adicional));
        
        VBox buttonBox = new VBox(5);
        buttonBox.getChildren().addAll(editButton, deleteButton);
        
        box.getChildren().addAll(infoBox, buttonBox);
        return box;
    }
    
    private void editAdicional(Adicional adicional) {
        currentAdicional = adicional;
        txtNovoNomeAdicional.setText(adicional.getNome());
        txtNovoValorAdicional.setText(String.valueOf(adicional.getValor()));
        btnNovoAdicional.setText("Atualizar Adicional");
        buttonEditarCliente.setVisible(true);
    }
    
    private void deleteAdicional(Adicional adicional) {
        try {
            adicionalService.remove(adicional);
            loadAdicionais();
        } catch (Exception e) {
            erro.setText("Erro ao excluir adicional: " + e.getMessage());
            erro.setVisible(true);
        }
    }
    
    @FXML public void update_adicional() {
        // Implementation for updating an additional topping
        try {
            if (currentAdicional != null) {
                String nome = txtNovoNomeAdicional.getText();
                double valor = Double.parseDouble(txtNovoValorAdicional.getText());
                
                currentAdicional.setNome(nome);
                currentAdicional.setValor(valor);
                
                adicionalService.update(currentAdicional);
                
                // Reset form
                txtNovoNomeAdicional.clear();
                txtNovoValorAdicional.clear();
                btnNovoAdicional.setText("Novo Adicional!");
                currentAdicional = null;
                buttonEditarCliente.setVisible(false);
                
                // Refresh the adicionais view
                loadAdicionais();
            }
        } catch (NumberFormatException e) {
            erro.setText("Valor inválido. Digite um número.");
            erro.setVisible(true);
        } catch (Exception e) {
            erro.setText("Erro ao atualizar adicional: " + e.getMessage());
            erro.setVisible(true);
        }
    }
    
    private void createNewAdicional() {
        try {
            // Get values from form
            String nome = txtNovoNomeAdicional.getText();
            String valorText = txtNovoValorAdicional.getText();
            
            // Validate input
            if (nome.isEmpty() || valorText.isEmpty()) {
                erro.setText("Preencha todos os campos.");
                erro.setVisible(true);
                return;
            }
            
            double valor;
            try {
                valor = Double.parseDouble(valorText);
            } catch (NumberFormatException e) {
                erro.setText("Valor inválido. Digite um número.");
                erro.setVisible(true);
                return;
            }
            
            // Create new adicional using Factory pattern
            Adicional newAdicional = AdicionalFactory.createAdicional(nome, valor);
            
            // Save to database
            adicionalService.register(newAdicional);
            
            // Clear form
            txtNovoNomeAdicional.clear();
            txtNovoValorAdicional.clear();
            erro.setVisible(false);
            
            // Refresh the adicionais view
            loadAdicionais();
            
        } catch (Exception e) {
            erro.setText("Erro ao criar adicional: " + e.getMessage());
            erro.setVisible(true);
        }
    }
    
    // Factory pattern for creating Adicional objects
    private static class AdicionalFactory {
        public static Adicional createAdicional(String nome, double valor) {
            // Generate a unique code based on the name
            String codigo = nome.substring(0, Math.min(3, nome.length())).toUpperCase() + 
                           String.format("%03d", (int)(Math.random() * 1000));
            
            return new Adicional(codigo, nome, valor);
        }
    }
}
