package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class HengerController {
    @FXML private TextField radiusField;
    @FXML private TextField heightField;
    @FXML private Label resultLabel;
    @FXML private TableView<Henger> tableView;
    @FXML private TableColumn<Henger, Double> colRadius;
    @FXML private TableColumn<Henger, Double> colHeight;
    @FXML private TableColumn<Henger, Double> colSurface;

    private ObservableList<Henger> hengerLista = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colRadius.setCellValueFactory(cellData -> cellData.getValue().radiusProperty().asObject());
        colHeight.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        colSurface.setCellValueFactory(cellData -> cellData.getValue().surfaceProperty().asObject());

        tableView.setItems(hengerLista);
        loadDatabaseData();
    }

    @FXML
    private void calculateAndSave() {
        try {
            double r = Double.parseDouble(radiusField.getText());
            double h = Double.parseDouble(heightField.getText());
            double surface = 2 * Math.PI * r * (r + h);

            resultLabel.setText("Felszín: " + String.format("%.2f", surface));

            Database.insertCylinder(r, h, surface);
            hengerLista.add(new Henger(r, h, surface));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Hibás bemenet!");
        }
    }

    private void loadDatabaseData() {
        hengerLista.clear();
        hengerLista.addAll(Database.getAllCylinders());
    }
}
