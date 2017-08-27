package application.model;


public class TableModelKoszyk {
		
	private int id_z;
	private String typ;
	private String opis;
	private String kolor;
	private double cena;
	private double cenaB;
	
	public int getId_z() {
		return id_z;
	}
	public void setId_z(int id_z) {
		this.id_z = id_z;
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
	
	public double getCenaB() {
		return cenaB;
	}
	public void setCenaB(double cenaB) {
		this.cenaB = cenaB;
	}
	
	// konstruktor 
	public TableModelKoszyk(){}
	
	// ! nazwy kolumn takie same jak w mysqlu
	public TableModelKoszyk(int id_z, String opis, String kolor, double cena, double cenaB) {
		super();
		this.id_z= id_z;
		this.opis = opis;
		this.kolor = kolor;
		this.cena = cena;
		this.cenaB = cenaB;
	}
	
	public TableModelKoszyk(String opis, String kolor, double cena, double cenaB) {
		super();
		
		this.opis = opis;
		this.kolor = kolor;
		this.cena = cena;
		this.cenaB = cenaB;
	}
	
}


