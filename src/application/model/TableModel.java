package application.model;


public class TableModel {
		
	private int id;
	private String typ;
	private String opis;
	private String kolor;
	private double cena;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getKolor() {
		return kolor;
	}
	public void setKolor(String kolor) {
		this.kolor = kolor;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	// konstruktor automatyczny
	public TableModel(){}
	// konstruktor wyboru klienta
	
	public TableModel(int id, String opis, String kolor, double cena) {
		super();
		this.id = id;
		this.opis = opis;
		this.kolor = kolor;
		this.cena = cena;
		}
	
	// typ, opis, kolor, wz, cena, cenaB)
	//  konstruktor wyboru pracownika
	public TableModel(int id, String typ, String opis, String kolor, double cena) {
		super();
		this.id = id;
		this.typ = typ;
		this.opis = opis;
		this.kolor = kolor;
		this.cena = cena;
	}
}
	
	


