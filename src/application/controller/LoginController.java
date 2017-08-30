package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import application.database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private PasswordField pf_pass;

    @FXML
    private TextField tf_login;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_db;

    public DBConnector db;
    
    
    //logowanie klienta    
    @FXML
    void clientLogin(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
    	Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	ResultSet rs = stat.executeQuery("select * from users where login='"+tf_login.getText()+"' and pass='"+pf_pass.getText()+"' "+"and perm='100';");
    	
    	if(rs.next()){
    		Stage stageInfo = new Stage();
    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/klientView.fxml"));
    		Scene sceneInfo = new Scene(parent);
    		stageInfo.setScene(sceneInfo);
    		stageInfo.setTitle("Info");
    		stageInfo.show();
    		System.out.print("zalogowano poprawnie u¿ytkownika");
    	} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Podaj poprawne has³o i login");
    		a.setHeaderText("Warning!");
    		a.setTitle("B³¹d");
    		a.showAndWait();
    		
    	}   	
    }
//logowanie admina 
    @FXML
    void emplLogin(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {
    	Connection conn2 = db.Connection();
    	Statement stat = conn2.createStatement();
    	ResultSet rs = stat.executeQuery("select * from users where login='"+tf_login.getText()+"' and pass='"+pf_pass.getText()+"' "+"and perm='111';");
    	if((rs.next())){
    		Stage stageInfo = new Stage();
    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/pracownikView.fxml"));
    		Scene sceneInfo = new Scene(parent);
    		stageInfo.setScene(sceneInfo);
    		stageInfo.setTitle("Info");
    		stageInfo.show();
    		System.out.print("zalogowano poprawnie admina");
    		} else {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Podaj poprawne has³o i login");
    		a.setHeaderText("Warning!");
    		a.setTitle("B³¹d");
    		a.showAndWait();
    	}   	
    
 		
    }

    
    public void initialize(){
    	db = new DBConnector();
    }

}
