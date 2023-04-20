/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athletefx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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
        GridPane midRow1 = new GridPane();
        GridPane midRow2 = new GridPane();
        GridPane midRow3 = new GridPane();
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
        //dummied out alert after handling of radio buttons was changed
        //Alert jobErr = new Alert(Alert.AlertType.ERROR); jobErr.setContentText("Be sure to select a job.");
        
        //disable professional entry fields until an athlete is accepted
        btnAddProf.setDisable(true);
        txtProfName.setDisable(true);
        
        //assigning radio buttons to the same group
        rbtnLaw.setToggleGroup(t);
        rbtnAgent.setToggleGroup(t);
        rbtnPA.setToggleGroup(t);
        rbtnTrainer.setToggleGroup(t);
        
        rbtnLaw.setSelected(true);
        // </editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="linking styling">
        topPane.getStyleClass().add("pane");
        midPane.getStyleClass().add("pane");
        midRow1.getStyleClass().add("pane");
        midRow2.getStyleClass().add("pane");
        midRow3.getStyleClass().add("buttonPane");
        botPane.getStyleClass().add("pane");
        lblAthName.getStyleClass().add("label");
        txtAthName.getStyleClass().add("text250");        
        txtAthSal.getStyleClass().add("text250");
        txtProfName.getStyleClass().add("text250");
        btnSubmitAth.getStyleClass().add("button");
        btnAddProf.getStyleClass().add("button");
        rbtnLaw.getStyleClass().add("rbutton");
        rbtnPA.getStyleClass().add("rbutton");
        rbtnAgent.getStyleClass().add("rbutton");
        rbtnTrainer.getStyleClass().add("rbutton");
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
        col25.setHalignment(HPos.CENTER);
        col33.setHalignment(HPos.CENTER);
        col50.setHalignment(HPos.CENTER);
        col67.setHalignment(HPos.CENTER);
        col100.setHalignment(HPos.CENTER);
        RowConstraints row25 = new RowConstraints();
        RowConstraints row50 = new RowConstraints();
        RowConstraints row20 = new RowConstraints();
        row25.setPercentHeight(25);
        row50.setPercentHeight(50);
        row20.setPercentHeight(20);
        
        root.getColumnConstraints().add(col100);
        root.getRowConstraints().addAll(row25, row25, row50);
        topPane.getColumnConstraints().addAll(col25, col50, col25);
        midPane.getColumnConstraints().addAll(col100);
        midRow1.getColumnConstraints().addAll(col33,col67);
        midRow2.getColumnConstraints().addAll(col25,col25,col25,col25);
        midRow3.getColumnConstraints().addAll(col100);
        botPane.getColumnConstraints().addAll(col12, col75, col12);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="button event handlers">
        //creating an event for the submit button
        btnSubmitAth.setOnAction(event -> {
                try{
                    String name = null;
                    Double salary = null;
                    if(txtAthName.getText().length() > 0) name = txtAthName.getText();  //check a name is entered
                        else nameErr.showAndWait();                    
                    if(TryParse(txtAthSal.getText())) salary = Double.parseDouble(txtAthSal.getText());  //validate salary
                        else salErr.showAndWait();                                      // salary > value check for minimum amount can be added here
                    if(ath.SetName(name) && ath.SetSalary(salary))
                    {
                        //adjust which fields are available after name/salary are confirmed
                        btnSubmitAth.setDisable(true);
                        txtAthName.setDisable(true);
                        txtAthSal.setDisable(true);
                        btnAddProf.setDisable(false);
                        txtProfName.setDisable(false);
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
                    String job;
                    if(txtAthName.getText().length() > 0) name = txtProfName.getText();  //check a name is entered
                        else nameErr.showAndWait();
                    athSal = ath.Salary();
                    //we force lawyer to start toggled else this can throw errors
                    job = ((RadioButton)t.getSelectedToggle()).getText();  //get the current toggled radiobutton using a typecast
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
        
        midRow1.add(lblProfName, 0, 0);
        midRow1.add(txtProfName, 1, 0);        
        midRow2.add(rbtnLaw, 0, 0);
        midRow2.add(rbtnAgent, 1, 0);
        midRow2.add(rbtnPA, 2, 0);
        midRow2.add(rbtnTrainer, 3, 0);
        midRow3.add(btnAddProf, 0, 0);
        midPane.add(midRow1,0,0);
        midPane.add(midRow2,0,1);
        midPane.add(midRow3,0,2);
        
        botPane.add(listProfs, 1,0);
        
        root.add(topPane, 0, 0);
        root.add(midPane, 0, 1);
        root.add(botPane, 0, 2);
        // </editor-fold>
        
        Scene athScene = new Scene(root, 600, 700);
        athScene.getStylesheets().add(AthleteFX.class.getResource("athleteStyles.css").toString());
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
