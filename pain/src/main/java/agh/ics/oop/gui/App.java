package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class App extends Application implements IRefreshObserver {
    private WorldMap map;
    private final GridPane gridPane = new GridPane();
    private Thread threadEngine;
    private SimulationEngine engine;

    @Override
    public void init() throws Exception{}

    @Override
    public void start(Stage primaryStage){}

    public void draw(){}

    @Override
    public void refresh(){
    }

}
