package application.controller;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import application.database.DBConnector;
import application.model.TableModel;
import application.model.TableModelKoszyk;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class klientController {

     
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_gora;
    @FXML
    private Button btn_dol;
    @FXML
    private Button btn_wierzch;
    @FXML
    private TableView<TableModel> table_dostepne;
    @FXML
    private TableColumn<TableModel, String> col_opis;
    @FXML
    private TableColumn<TableModel, String> col_kolor;
    @FXML
    private TableColumn<TableModel, Integer> col_cena;
    @FXML
    private Button btn_do_koszyka;
    //koszyk
    @FXML
    private TableView<TableModel> table_koszyk;
    @FXML
    private TableColumn<TableModel, String> col_opis_koszyk;
    @FXML
    private TableColumn<TableModel, String> col_kolor_koszyk;
    @FXML
    private TableColumn<TableModel, Integer> col_cena_koszyk;
    @FXML
    private Button btn_usun;
    @FXML
    private Button btn_zamawiam;
    
    
    public DBConnector db;
    public ObservableList<TableModelKoszyk> dataKoszyk;
    public ObservableList<TableModel> data;

    @FXML
    void SelectDol(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = (Connection) db.Connection();
    	data=FXCollections.observableArrayList();
    	ResultSet rs= conn.createStatement().executeQuery("select opis, kolor, cena from ubrania where typ= 'dol';");
    	
    	while(rs.next()){
    		data.add(new TableModel(rs.getString(1), rs.getString(2), rs.getDouble(3)));
    	}
    	col_opis.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
    	col_kolor.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
       	col_cena.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));

    	table_dostepne.setItems(null);
    	table_dostepne.setItems(data);
    }

    @FXML
    void SelectGora(ActionEvent event) throws SQLException, ClassNotFoundException {
    	Connection conn = (Connection) db.Connection();
    	data=FXCollections.observableArrayList();
    	ResultSet rs= conn.createStatement().executeQuery("select opis, kolor, cena from ubrania where typ= 'gora';");
    	
    	while(rs.next()){
    		data.add(new TableModel(rs.getString(1), rs.getString(2), rs.getDouble(3)));
    	}
    	col_opis.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
    	col_kolor.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
    	col_cena.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));

    	table_dostepne.setItems(null);
    	table_dostepne.setItems(data);
    }

    @FXML
    void SelectWierzch(ActionEvent event) throws SQLException, ClassNotFoundException {
    	Connection conn = (Connection) db.Connection();
    	data=FXCollections.observableArrayList();
    	ResultSet rs= conn.createStatement().executeQuery("select opis, kolor, cena from ubrania where typ= 'wierzch';");
    	
    	while(rs.next()){
    		data.add(new TableModel(rs.getString(1), rs.getString(2), rs.getDouble(3)));
    	}
    	col_opis.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
    	col_kolor.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
    	col_cena.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));

    	table_dostepne.setItems(null);
    	table_dostepne.setItems(data);
    }
    
    
    
    // przycisk cofnij
    @FXML
    void btnExitAction(ActionEvent event) throws IOException {
    	Stage stageInfo = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
		Scene sceneInfo = new Scene(parent);
		stageInfo.setScene(sceneInfo);
		stageInfo.setTitle("Info");
		stageInfo.show();
    }

    /* @FXML
    void btnDoKoszykaAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = (Connection) db.Connection();
    	String sql = "insert into ubrania (opis, kolor, cena) values ('"+col_opis.getText()+"', '"+col_kolor.getText()+"', "+col_cena.getText()+");";
        //insert into zamowienia (opis, kolor, cena) values ('XX', 'XX', XX);
    	PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        */
        
       //by wypisa³o?
    	/*data=FXCollections.observableArrayList();
    	ResultSet rs= conn.createStatement().executeQuery("select* from zamowienia;");
    	
    	while(rs.next()){
    		data.add(new TableModel(rs.getString(1), rs.getString(2), rs.getDouble(3)));
    	}
    	col_opis.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
    	col_kolor.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
    	col_cena.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));

    	table_dostepne.setItems(null);
    	table_dostepne.setItems(data);
    	
    }
      */
        
    

    
    
    @FXML
    void initialize() {
    	db=new DBConnector();
    }

}
