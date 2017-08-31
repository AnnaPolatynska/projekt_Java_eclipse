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
import javafx.scene.control.TextField;
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
    private TableColumn<TableModel, Integer> col_id;
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
    private TableColumn<TableModel, Integer> col_id_koszyk;
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
    
    @FXML
    private TextField tf_suma;

    @FXML
    private TableView<?> TV_suma;
    @FXML
    private TableColumn<?, ?> tb_suma;
    
    @FXML
    private Button btn_odswiez;

    
    
    
    public DBConnector db;
    public ObservableList<TableModelKoszyk> dataKoszyk;
    public ObservableList<TableModel> data;
// przyciski wyboru typu asortymentu
    @FXML
    void SelectDol(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = (Connection) db.Connection();
    	data=FXCollections.observableArrayList();
    	ResultSet rs= conn.createStatement().executeQuery("select id, opis, kolor, cena from ubrania where typ= 'dol';");
    	
    	while(rs.next()){
    		data.add(new TableModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
    	}
    	col_id.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));
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
    	ResultSet rs= conn.createStatement().executeQuery("select id, opis, kolor, cena from ubrania where typ= 'gora';");
    	
    	while(rs.next()){
    		data.add(new TableModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
    	}
    	col_id.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));
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
    	ResultSet rs= conn.createStatement().executeQuery("select id, opis, kolor, cena from ubrania where typ= 'wierzch';");
    	
    	while(rs.next()){
    		data.add(new TableModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
    	}
    	col_id.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));
    	col_opis.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
    	col_kolor.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
       	col_cena.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));
       	
    	table_dostepne.setItems(null);
    	table_dostepne.setItems(data);
    }
    
// przycisk cofnij do ekranu logowania
    @FXML
    void btnExitAction(ActionEvent event) throws IOException {
    	Stage stageInfo = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
		Scene sceneInfo = new Scene(parent);
		stageInfo.setScene(sceneInfo);
		stageInfo.setTitle("Info");
		stageInfo.show();
    }
//przycisk dodania wybranego produktu do koszyka
    @FXML
    void btnDoKoszykaAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = (Connection) db.Connection();
    	
    	//by przechwyci³o
    	 int id_select = table_dostepne.getSelectionModel().getSelectedItem().getId();
    	 String id_opis = table_dostepne.getSelectionModel().getSelectedItem().getOpis();
    	 String id_kolor = table_dostepne.getSelectionModel().getSelectedItem().getKolor();
    	 Double id_cena = table_dostepne.getSelectionModel().getSelectedItem().getCena();
    	 String sql = "select * from ubrania where opis='"+id_select+"';";
	     System.out.println(id_select);
	     PreparedStatement ps = conn.prepareStatement(sql);
	     ps.executeQuery();
	   
	     String sql1 = "insert into zamowienia (id, opis, kolor, cena) values ("+id_select+", '"+id_opis+"', '"+id_kolor+"', "+id_cena+");";
	     PreparedStatement ps1 = conn.prepareStatement(sql1);
	     ps1.executeUpdate();
	     //insert into zamowienia (id, 'opis', 'kolor', cena) values(00, 'XXX', 'XXX', 00);
    	
    	
    	//wypisuje w tabeli zamówiania
    	data=FXCollections.observableArrayList();
        ResultSet rs= conn.createStatement().executeQuery("select id, opis, kolor, cena from zamowienia;");
        	
        while(rs.next()){
        	data.add(new TableModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
        }
        col_id_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));	
        col_opis_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
        col_kolor_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
        col_cena_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));
           
        table_koszyk.setItems(null);
        table_koszyk.setItems(data);
        }
    	
    
// przycisk usuniêcia towaru z koszyka
    @FXML
    void btnUsunAction(ActionEvent event) throws SQLException, ClassNotFoundException {
    	Connection conn = (Connection) db.Connection();
    	try{
	        int id_del_koszyk = table_koszyk.getSelectionModel().getSelectedItem().getId();
	        //delete from zamowienia where id=?;
	        String sql = "delete from zamowienia where id="+id_del_koszyk+";";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.executeUpdate();
	        btnOdswiezAction(event);
	        System.out.println(id_del_koszyk);
	        
	    } catch (NullPointerException e){
	        	Alert a = new Alert(AlertType.INFORMATION);
	        	a.setContentText("Musisz zaznaczyæ produkt, który chcesz usun¹æ z koszyka!");
	        	a.setHeaderText("Error!");
	        	a.setTitle("Error");
	        	a.showAndWait();
	        }
	       }
   
  //dodaæ select by siê zaktualizowa³o ===> select
    
    @FXML
    void btnOdswiezAction(ActionEvent event) throws ClassNotFoundException, SQLException{
    	Connection conn = (Connection) db.Connection();
    	data=FXCollections.observableArrayList();
        ResultSet rs= conn.createStatement().executeQuery("select id, opis, kolor, cena from zamowienia;");
        	
        while(rs.next()){
        	data.add(new TableModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
        }
        col_id_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));	
        col_opis_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
        col_kolor_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
        col_cena_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));
           
        table_koszyk.setItems(null);
        table_koszyk.setItems(data);
        }
    
    
   
    
//przycisk z³o¿enia zamówienia   
    @FXML
    void btnZamawiamAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = (Connection) db.Connection();
       
    	// usuniêcie zamówienia z widocznej tabeli
    	String sql = "delete from zamowienia;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        btnOdswiezAction(event);
        
             
        //dodaæ select by siê zaktualizowa³o ===> select
        data=FXCollections.observableArrayList();
        ResultSet rs= conn.createStatement().executeQuery("select id, opis, kolor, cena from zamowienia;");
        	
        while(rs.next()){
        	data.add(new TableModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
        }
        col_id_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));	
        col_opis_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
        col_kolor_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
        col_cena_koszyk.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));
           
        table_koszyk.setItems(null);
        table_koszyk.setItems(data);
        }
  
      
    
    @FXML
    void initialize() {
    	db=new DBConnector();
    }

}
