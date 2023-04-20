/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athletefx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Mesa
 */
public class AthleteFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                
        Scene scene = AthleteGUI();
        
        primaryStage.setTitle("Athlete");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Scene AthleteGUI(){
        // fields for the main window
        Athlete ath = new Athlete();
        ProfessionalList profList = new ProfessionalList();
        
        // <editor-fold defaultstate="collapsed" desc="component creation">
        GridPane root = new GridPane();
        GridPane topPane = new GridPane();
        GridPane midPane = new GridPane();
        GridPane botPane = new GridPane();
        Label lblAthName = new Label("Athlete's name: ");
        Label lblAthSalary = new Label("Athlete's salary: ");
        Label lblProfName = new Label("Professional's name: ");
        Button btnSubmitAth = new Button("Submit");
        Button btnAddProf = new Button("Add");
        RadioButton rbtnLaw = new RadioButton("Lawyer");
        RadioButton rbtnAgent = new RadioButton("Agent");
        RadioButton rbtnPA = new RadioButton("Personal Assistant");
        RadioButton rbtnTrainer = new RadioButton("Trainer");
        ToggleGroup t = new ToggleGroup();
        TextField txtAthName = new TextField();
        TextField txtProfName = new TextField();
        TextField txtAthSal = new TextField();
        ListView listProfs = new ListView();
        
        //alerts for input validation
        Alert nameErr = new Alert(Alert.AlertType.ERROR); nameErr.setContentText("Be sure to properly enter a name.");
        Alert salErr = new Alert(Alert.AlertType.ERROR); salErr.setContentText("Be sure to enter a valid number for salary.");
        Alert jobErr = new Alert(Alert.AlertType.ERROR); jobErr.setContentText("Be sure to select a job.");
        
        //assigning radio buttons to the same group
        rbtnLaw.setToggleGroup(t);
        rbtnAgent.setToggleGroup(t);
        rbtnPA.setToggleGroup(t);
        rbtnTrainer.setToggleGroup(t);
        // </editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="creating constraints">
        ColumnConstraints col100 = new ColumnConstraints();
        ColumnConstraints col25 = new ColumnConstraints();
        ColumnConstraints col50 = new ColumnConstraints();
        ColumnConstraints col33 = new ColumnConstraints();
        ColumnConstraints col67 = new ColumnConstraints();
        ColumnConstraints col75 = new ColumnConstraints();
        ColumnConstraints col12 = new ColumnConstraints();
        col100.setPercentWidth(100);
        col25.setPercentWidth(25);
        col50.setPercentWidth(50);
        col33.setPercentWidth(33);
        col67.setPercentWidth(67);
        col75.setPercentWidth(75);
        col12.setPercentWidth(12.5);
        RowConstraints row25 = new RowConstraints();
        RowConstraints row50 = new RowConstraints();
        RowConstraints row20 = new RowConstraints();
        row25.setPercentHeight(25);
        row50.setPercentHeight(50);
        row20.setPercentHeight(20);
        
        root.getColumnConstraints().add(col100);
        root.getRowConstraints().addAll(row25, row25, row50);
        topPane.getColumnConstraints().addAll(col25, col50, col25);
        botPane.getColumnConstraints().addAll(col12, col75, col12);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="button event handlers">
        //creating an event for the submit button
        btnSubmitAth.setOnAction(event -> {
                try{
                    String name = null;
                    Double salary = null;
                    if(txtAthName.getText().length() > 0) name = txtAthName.getText();
                        else nameErr.showAndWait();
                    if(TryParse(txtAthSal.getText())) salary = Double.parseDouble(txtAthSal.getText());
                        else salErr.showAndWait();                    
                    if(ath.SetName(name) && ath.SetSalary(salary))
                    {
                        btnSubmitAth.setDisable(true);
                        txtAthName.setDisable(true);
                        txtAthSal.setDisable(true);
                    }
                }
                catch(Exception ex){
                    Logging.StampLog(ex);
                }
            });
        //creating an event for the add professional button
        btnAddProf.setOnAction(event -> {
                try{
                    String name = null;
                    Double athSal;
                    String job = null;
                    if(txtAthName.getText().length() > 0) name = txtProfName.getText();
                        else nameErr.showAndWait();
                    athSal = Double.parseDouble(txtAthSal.getText());
                    if(rbtnLaw.isSelected()) job = rbtnLaw.getText();
                    else if(rbtnPA.isSelected()) job = rbtnPA.getText();
                    else if(rbtnTrainer.isSelected()) job = rbtnTrainer.getText();
                    else if(rbtnAgent.isSelected()) job = rbtnAgent.getText();
                    else jobErr.showAndWait();
                    if(profList.Insert(name, job, athSal)){
                        listProfs.setItems(FXCollections.observableArrayList(profList.Items()));
                    }
                    //Add(name, job, athSal, profList);
                    //listProfs.setItems(FXCollections.observableArrayList(profList));
                }
                catch(Exception ex){
                    Logging.StampLog(ex);
                }
            });
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="add controls to the panes">
        topPane.add(lblAthName, 0, 1);
        topPane.add(lblAthSalary, 0, 3);
        topPane.add(txtAthName, 1, 1);
        topPane.add(txtAthSal, 1, 3);
        topPane.add(btnSubmitAth, 2, 2);
        
        midPane.add(lblProfName, 1, 0);
        midPane.add(txtProfName, 2, 0);
        midPane.add(rbtnLaw, 0, 1);
        midPane.add(rbtnAgent, 1, 1);
        midPane.add(rbtnPA, 2, 1);
        midPane.add(rbtnTrainer, 3, 1);
        midPane.add(btnAddProf, 1, 2);
        
        botPane.add(listProfs, 1,0);
        
        root.add(topPane, 0, 0);
        root.add(midPane, 0, 1);
        root.add(botPane,0,2);
        // </editor-fold>
        Scene athScene = new Scene(root, 500, 700);
        return athScene;
    }
    
    // method to validate input before attempting to parse
    public boolean TryParse(String val){
        try{
            Double.parseDouble(val);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
