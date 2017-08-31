package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.database.DBConnector;
import application.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	public class pracownikController {
	    @FXML
	    private TableView<TableModel> Table1;
	    @FXML
	    private TableColumn<TableModel, Integer> col_id;
	    @FXML
	    private TableColumn<TableModel, String> col_typ;
	    @FXML
	    private TableColumn<TableModel, String> col_opis;
	    @FXML
	    private TableColumn<TableModel, String> col_kolor;
	    @FXML
	    private TableColumn<TableModel, Integer> col_cena;
	    @FXML
	    private Button select;
	    @FXML
	    private Button delete;
	    @FXML
	    private Button insert;
	    @FXML
	    private Button update;
	    @FXML
	    private VBox V2;
	    @FXML
	    private TextField tf_typ;
	    @FXML
	    private TextField tf_opis;
	    @FXML
	    private TextField tf_kolor;
	    @FXML
	    private TextField tf_cena;
	    @FXML
	    private Button btn_insert_commit;
	    @FXML
	    private Button btn_update_commit;
	    
	    public DBConnector db;
	    public ObservableList<TableModel> data;
	    

	    @FXML
	    void btnDeleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	Connection conn = (Connection) db.Connection();
	        try{
	        int id_del = Table1.getSelectionModel().getSelectedItem().getId();
	        String sql = "delete from ubrania where id="+id_del+";";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.executeUpdate();
	        btnSelectAction(event);
	        } catch (NullPointerException e){
	        	Alert a = new Alert(AlertType.INFORMATION);
	        	a.setContentText("Musisz zaznaczyæ rekord, który chcesz usun¹æ!");
	        	a.setHeaderText("Error!");
	        	a.setTitle("Error");
	        	a.showAndWait();
	        }
	       }
	
	    @FXML
	    void btnInsertAction(ActionEvent event) {
	    	btn_update_commit.setDisable(true);
	    	tf_typ.setDisable(false);
	    	tf_opis.setDisable(false);
	    	tf_kolor.setDisable(false);
	    	tf_cena.setDisable(false);
	    	
	    }

	    	    
	    @FXML
	    void btnInsertCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
	        Connection conn = db.Connection();
	        if(tf_typ.getText().equals("") || tf_opis.getText().equals("") || tf_kolor.getText().equals("") ||tf_cena.getText().equals("")){
	        	Alert a = new Alert(AlertType.INFORMATION);
	        	a.setContentText("Proszê wpisaæ dane do wszystkich pól!");
	        	a.setHeaderText("Error!");
	        	a.setTitle("Error");
	        	a.showAndWait();
	        } else{
	        	try{
	        	String sql = "insert into ubrania (typ, opis, kolor, cena) values ('"+tf_typ.getText()+"', '"+tf_opis.getText()+"', '"+tf_kolor.getText()+"', "+tf_cena.getText()+");";
	            //insert into ubrania (typ, opis, kolor, cena) values('XX','XX', 'XX', XX);
	        	PreparedStatement ps = conn.prepareStatement(sql);
	            ps.executeUpdate();
	            btnSelectAction(event);
	            btn_update_commit.setDisable(false);
	            tf_typ.setDisable(true);
		    	tf_opis.setDisable(true);
		    	tf_kolor.setDisable(true);
		    	tf_cena.setDisable(true);
	           
	        	} catch (SQLException e){
	        	Alert a = new Alert(AlertType.INFORMATION);
	        	a.setContentText("cena musi byæ liczb¹!");
	        	a.setHeaderText("Error!");
	        	a.setTitle("Error");
	        	a.showAndWait();
	        	}
	        		    
	    	}
	    }
	   

	    @FXML
	    void btnSelectAction(ActionEvent event) throws SQLException, ClassNotFoundException {
	    	btn_insert_commit.setDisable(true);
	    	Connection conn = (Connection) db.Connection();
	    	data=FXCollections.observableArrayList();
	    	ResultSet rs= conn.createStatement().executeQuery("select * from ubrania");
	    	
	    	while(rs.next()){
	    	data.add(new TableModel(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5)));
	    	}
	    	col_id.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));
	    	col_typ.setCellValueFactory(new PropertyValueFactory<TableModel,String>("typ"));
	    	col_opis.setCellValueFactory(new PropertyValueFactory<TableModel,String>("opis"));
	    	col_kolor.setCellValueFactory(new PropertyValueFactory<TableModel,String>("kolor"));
	       	col_cena.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("cena"));

	    	Table1.setItems(null);
	    	Table1.setItems(data);
	    	btn_insert_commit.setDisable(false);
	    }

	    @FXML
	    void btnUpdateAction(ActionEvent event) {
	    	btn_insert_commit.setDisable(true);
	    	tf_typ.setDisable(false);
	    	tf_opis.setDisable(false);
	    	tf_kolor.setDisable(false);
	    	tf_cena.setDisable(false);    	
	    	
	    	try{
	        	tf_typ.setText(Table1.getSelectionModel().getSelectedItem().getTyp());
	    	    tf_opis.setText(Table1.getSelectionModel().getSelectedItem().getOpis());
	    	    tf_kolor.setText(Table1.getSelectionModel().getSelectedItem().getKolor());
	    	    tf_cena.setText(String.valueOf(Table1.getSelectionModel().getSelectedItem().getCena()));
	    	    
	    	    } catch (NullPointerException e){
	    			Alert a = new Alert(AlertType.INFORMATION);
	    			a.setContentText("Musisz zaznaczyæ rekord, który chcesz zmodyfikowaæ!");
	    			a.setHeaderText("Error!");
	    			a.setTitle("Error");
	    			a.showAndWait();
	        	}
	    	// btn_insert_commit.setDisable(false);
	    }
	    
	    
	  //int id, String typ, String opis, String kolor, double cena, double cenaB)
	    @FXML
	    void btnUpdateCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
	    	   try{
	    		   Connection conn = db.Connection();
	    		   int id_update = Table1.getSelectionModel().getSelectedItem().getId();
	    		   String sql = "update ubrania set typ='"+tf_typ.getText()+"', opis='"+tf_opis.getText()+"', kolor='"+tf_kolor.getText()+"',cena="+tf_cena.getText()+" where id="+id_update+";";
	    		   PreparedStatement ps = conn.prepareStatement(sql);    	
	    		   ps.executeUpdate();
	    		   btnSelectAction(event);
	    	   } catch (SQLException e){
	    		Alert a = new Alert(AlertType.INFORMATION);
	    		a.setContentText("cena musi byæ liczb¹!");
	    		a.setHeaderText("Error!");
	    		a.setTitle("Error");
	    		a.showAndWait();
	    	   }
	    	}
	    
	    //powrót do logowania
	    @FXML
	    void btnExitAction(ActionEvent event) throws IOException {
	    	Stage stageInfo = new Stage();
    		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
    		Scene sceneInfo = new Scene(parent);
    		stageInfo.setScene(sceneInfo);
    		stageInfo.setTitle("Info");
    		stageInfo.show();
	    }
	    
	    
	    @FXML
	    void initialize() {
	    	db = new DBConnector();
	    }
	    
	}
