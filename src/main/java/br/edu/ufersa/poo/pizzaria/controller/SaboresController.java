package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.TipoPizza;
import br.edu.ufersa.poo.pizzaria.model.services.TipoPizzaService;
import br.edu.ufersa.poo.pizzaria.model.services.TipoPizzaServiceImpl;
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

public class SaboresController {
    @FXML private AnchorPane root;
    @FXML private TextField txtNovoSaborNome;
    @FXML private TextField txtNovoSaborValor;
    @FXML private TextField txtBuscaSaborNome;
    @FXML private TextField txtBuscaSaborValor;
    @FXML private TextField txtBuscaSaborCodigo;
    @FXML private Button btnNovoSabor;
    @FXML private Button buttonEditarSabor;
    @FXML private Label erro;
    @FXML private VBox clienteContainer;
    
    private TipoPizzaService tipoPizzaService;
    private TipoPizza currentTipoPizza;
    
    @FXML public void initialize(){
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        try {
            AnchorPane sidebar = sidebarLoader.load();
            root.getChildren().add(sidebar);
            
            // Initialize service
            tipoPizzaService = new TipoPizzaServiceImpl(EMSingleton.getInstance());
            
            // Add event listeners for search fields
            txtBuscaSaborNome.setOnKeyReleased(this::handleSearch);
            txtBuscaSaborValor.setOnKeyReleased(this::handleSearch);
            txtBuscaSaborCodigo.setOnKeyReleased(this::handleSearch);
            
            // Add event listener for new sabor button
            btnNovoSabor.setOnAction(event -> createNewSabor());
            
            // Load existing sabores
            loadSabores();
            
            // Hide error message initially
            erro.setVisible(false);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void handleSearch(KeyEvent event) {
        loadSabores();
    }
    
    private void loadSabores() {
        // Clear the container
        clienteContainer.getChildren().clear();
        
        // Get search criteria
        String nameFilter = txtBuscaSaborNome.getText().toLowerCase();
        String valueFilter = txtBuscaSaborValor.getText().toLowerCase();
        String codeFilter = txtBuscaSaborCodigo.getText().toLowerCase();
        
        // Get all sabores
        List<TipoPizza> sabores = tipoPizzaService.getAll();
        
        // Filter sabores based on search criteria
        for (TipoPizza sabor : sabores) {
            boolean nameMatch = nameFilter.isEmpty() || sabor.getNome().toLowerCase().contains(nameFilter);
            boolean valueMatch = valueFilter.isEmpty() || String.valueOf(sabor.getValor()).contains(valueFilter);
            boolean codeMatch = codeFilter.isEmpty() || sabor.getCodigo().toLowerCase().contains(codeFilter);
            
            if (nameMatch && valueMatch && codeMatch) {
                // Create a container for this sabor
                HBox saborBox = createSaborBox(sabor);
                clienteContainer.getChildren().add(saborBox);
            }
        }
    }
    
    private HBox createSaborBox(TipoPizza sabor) {
        HBox box = new HBox(10);
        box.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: #ddd; -fx-border-width: 0 0 1 0;");
        
        VBox infoBox = new VBox(5);
        Text nameText = new Text("Nome: " + sabor.getNome());
        Text valueText = new Text("Valor: R$ " + sabor.getValor());
        Text codeText = new Text("Código: " + sabor.getCodigo());
        infoBox.getChildren().addAll(nameText, valueText, codeText);
        
        Button editButton = new Button("Editar");
        editButton.setOnAction(event -> editSabor(sabor));
        
        Button deleteButton = new Button("Excluir");
        deleteButton.setOnAction(event -> deleteSabor(sabor));
        
        VBox buttonBox = new VBox(5);
        buttonBox.getChildren().addAll(editButton, deleteButton);
        
        box.getChildren().addAll(infoBox, buttonBox);
        return box;
    }
    
    private void editSabor(TipoPizza sabor) {
        currentTipoPizza = sabor;
        txtNovoSaborNome.setText(sabor.getNome());
        txtNovoSaborValor.setText(String.valueOf(sabor.getValor()));
        btnNovoSabor.setText("Atualizar Sabor");
        buttonEditarSabor.setVisible(true);
    }
    
    private void deleteSabor(TipoPizza sabor) {
        try {
            tipoPizzaService.remove(sabor);
            loadSabores();
        } catch (Exception e) {
            erro.setText("Erro ao excluir sabor: " + e.getMessage());
            erro.setVisible(true);
        }
    }
    
    @FXML public void update_cliente() {
        // This method is added to match the onAction reference in Sabores.fxml
        // It calls the properly named method
        update_sabor();
    }
    
    @FXML public void update_sabor() {
        // Implementation for updating a flavor
        try {
            if (currentTipoPizza != null) {
                String nome = txtNovoSaborNome.getText();
                double valor = Double.parseDouble(txtNovoSaborValor.getText());
                
                currentTipoPizza.setNome(nome);
                currentTipoPizza.setValor(valor);
                
                tipoPizzaService.update(currentTipoPizza);
                
                // Reset form
                txtNovoSaborNome.clear();
                txtNovoSaborValor.clear();
                btnNovoSabor.setText("Novo Sabor!");
                currentTipoPizza = null;
                buttonEditarSabor.setVisible(false);
                
                // Refresh the sabores view
                loadSabores();
            }
        } catch (NumberFormatException e) {
            erro.setText("Valor inválido. Digite um número.");
            erro.setVisible(true);
        } catch (Exception e) {
            erro.setText("Erro ao atualizar sabor: " + e.getMessage());
            erro.setVisible(true);
        }
    }
    
    private void createNewSabor() {
        try {
            // Get values from form
            String nome = txtNovoSaborNome.getText();
            String valorText = txtNovoSaborValor.getText();
            
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
            
            // Create new sabor using Factory pattern
            TipoPizza newSabor = TipoPizzaFactory.createTipoPizza(nome, valor);
            
            // Save to database
            tipoPizzaService.register(newSabor);
            
            // Clear form
            txtNovoSaborNome.clear();
            txtNovoSaborValor.clear();
            erro.setVisible(false);
            
            // Refresh the sabores view
            loadSabores();
            
        } catch (Exception e) {
            erro.setText("Erro ao criar sabor: " + e.getMessage());
            erro.setVisible(true);
        }
    }
    
    // Factory pattern for creating TipoPizza objects
    private static class TipoPizzaFactory {
        public static TipoPizza createTipoPizza(String nome, double valor) {
            // Generate a unique code based on the name
            String codigo = nome.substring(0, Math.min(3, nome.length())).toUpperCase() + 
                           String.format("%03d", (int)(Math.random() * 1000));
            
            return new TipoPizza(codigo, nome, valor);
        }
    }
}
