package shop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Artikel {
	String fehlermeldung = "Fehlerhafte Eingabe!";
	private boolean bearbeiten;
	private int id;
	private String bezeichnung;
	private int preis;
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	
	
	public ArrayList<Artikel> artikeln = new ArrayList<Artikel>();
	//GETTER & SETTER
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public int getPreis() {
		return preis;
	}
	public void setPreis(int preis) {
		this.preis = preis;
	}
	//GETTER & SETTER END
    public Artikel(){
		
	}
	public Artikel(boolean bearbeiten){
		this.bearbeiten = bearbeiten;
	}
	
	public void erzeugeArtikel() throws IOException
	{
		
		//Daten werden in artikel geschrieben
		System.out.println("Anlegen von Kunde...");
		String artikelbezeichnung = MyLib.pruefeString(br,"Bezeichnung: ", fehlermeldung);
		int artikelpreis = MyLib.pruefeInt(br,"Preis/Stück: ", "Bitte Zahl eingeben!", 
				1, 10000);
		beschreibeArtikel(artikelbezeichnung, artikelpreis);
		
	}
	public void beschreibeArtikel(String artikelbezeichnung,
			int artikelpreis){
		Artikel artikel = new Artikel();
		artikel.setBezeichnung(artikelbezeichnung);;
		artikel.setPreis(artikelpreis);
		compareID();
		artikel.setID(id);
		//kunde wird in die Liste hinzugefügt
		artikeln.add(artikel);
	}
	private int compareID() {
		for(int i=0;i < artikeln.size();i++){
			
			if((artikeln.get(i).getID() == id)){
				
				id++;
			}
		}
		return id;
	}
	
	public void ausgabeAlleArtikel() throws IOException{
		System.out.println("Ausgabe aller Artikeln..."); 	
		System.out.println("Artikelnanzahl: "+ artikeln.size());
		ArrayList<String> attribute = new ArrayList<String>(Arrays.asList("Bezeichnung","Preis","ID"));
		
		int laengste_bezeichnung = attribute.get(0).length();
		int laengster_preis = attribute.get(1).length();
		int laengste_id = attribute.get(2).length();

		
		for(int i = 0; i < artikeln.size(); i++) //Ermittle die laengsten Attribute
		{
			int laenge_bezeichnung = artikeln.get(i).getBezeichnung().length();
			laengste_bezeichnung = MyLib.laengsteTupel(laengste_bezeichnung, laenge_bezeichnung);
			int laenge_preis = String.valueOf(artikeln.get(i).getPreis()).length();
			laengster_preis  = MyLib.laengsteTupel(laengster_preis, laenge_preis);
			int laenge_id = String.valueOf(artikeln.get(i).getID()).length();
			laengste_id  = MyLib.laengsteTupel(laengste_id, laenge_id);
		}
		
		int laenge_bezeichnung = 0;
		int laenge_preis = 0;
		int laenge_id = 0;
		
		String leerstring_bezeichnung = "";
		String leerstring_preis = "";
		String leerstring_id = "";
		
		
		for(int i = 0; i < artikeln.size(); i++)
		{
			if(i == 0){ //Für Attribute
				
				printAttribute(attribute, laenge_bezeichnung,
						laengster_preis, laengste_id);
		
			}

			
			laenge_bezeichnung = artikeln.get(i).getBezeichnung().length();
			laenge_preis = String.valueOf(artikeln.get(i).getPreis()).length();
			laenge_id = String.valueOf(artikeln.get(i).getID()).length();
			
			leerstring_bezeichnung = MyLib.generateSpace(laenge_bezeichnung, laengste_bezeichnung);
			leerstring_preis = MyLib.generateSpace(laenge_preis, laengster_preis);
			leerstring_id = MyLib.generateSpace(laenge_id, laengste_id);
			
			System.out.print(artikeln.get(i).getBezeichnung());
			System.out.print(leerstring_bezeichnung);
			System.out.print("|");
			System.out.print(String.valueOf(artikeln.get(i).getPreis()));
			System.out.print(leerstring_preis);
			System.out.print("|");
			System.out.print(String.valueOf(artikeln.get(i).getID()));
			System.out.print(leerstring_id);
			System.out.print("|");
			System.out.println();	
			
			leerstring_bezeichnung = "";
			leerstring_preis = "";
			leerstring_id = "";
	
		}
		
		
		
		if((artikeln.size() != 0)&&(bearbeiten))
		{
			saveArtikelInTxt();
		}	
		
	}
	
	protected void printAttribute(ArrayList<String> attribute,
			int laengste_bezeichnung, int laengster_preis,int laengste_id) {
		int laenge_bezeichnung;
		int laenge_preis;
		int laenge_id;
		String leerstring_bezeichnung;
		String leerstring_preis;
		String leerstring_id;
		laenge_bezeichnung = attribute.get(0).length();
		laenge_preis = attribute.get(1).length();
		laenge_id = attribute.get(2).length();
		
		leerstring_bezeichnung = MyLib.generateSpace(laenge_bezeichnung, laengste_bezeichnung);
		leerstring_preis = MyLib.generateSpace(laenge_preis, laengster_preis);;
		leerstring_id = MyLib.generateSpace(laenge_id, laengste_id);
		
		System.out.print(attribute.get(0));
		System.out.print(leerstring_bezeichnung);
		System.out.print("|");
		System.out.print(attribute.get(1));
		System.out.print(leerstring_preis);
		System.out.print("|");
		System.out.print(attribute.get(2));
		System.out.print(leerstring_id);
		System.out.println("|");
	}
	protected void printArtikel(String leerstring_bezeichnung, String leerstring_preis,String leerstring_id, int i) {
		
	}
//	private void printArtikel(int i) {
//		
//		System.out.print(artikeln.get(i).getBezeichnung());
//		System.out.print("|");
//		System.out.print(artikeln.get(i).getPreis());
//		System.out.print("|");
//		System.out.print(String.valueOf(artikeln.get(i).getID()));
//		System.out.print("|");
//		System.out.println();
//	
//	}
	

	
	private void saveArtikelInTxt() throws IOException {
		System.out.print("geben Sie [s] ein um die Liste zu speichern: ");
		String saveartikel = br.readLine();
		
		if(saveartikel.equals("s"))
		{
			String nameTxt = MyLib.pruefeString(br,"geben Sie der .txt einen Namen: ", fehlermeldung);
			FileWriter fp = new FileWriter(nameTxt+".txt");
			BufferedWriter bw = new BufferedWriter(fp);
			printArtikelInTxt(bw);
			System.out.println("gespeichert..."); 
		}
		else
		{
			System.out.println("nicht gespeichert..."); 
		}
	}
	private void printArtikelInTxt(BufferedWriter bw) throws IOException {
		for(int i = 0; i < artikeln.size(); i++)
		{

			bw.write(artikeln.get(i).getBezeichnung());
			bw.write("|");
			bw.write(String.valueOf(artikeln.get(i).getPreis()));
			bw.write("|");
			bw.write(String.valueOf(artikeln.get(i).getID()));
			bw.write("|");
			bw.newLine();
			bw.close();
		}

	}
	public void findeArtikel() throws IOException{
		System.out.println("finden Sie ein Artikel..."); 	
	
		int suchtyp = MyLib.pruefeInt(br,"Suchen per [1]Bezeichnung oder [2]ID?: ","Bitte Zahl eingeben!",1,2);
		
		switch(suchtyp)
		{  
		
			case 1:
				String bezeichnung = MyLib.pruefeString(br,"Geben Sie eine Bezeichnung ein: ", fehlermeldung);
				
				for(int i = 0; i < artikeln.size(); i++){
					
					if (artikeln.get(i).getBezeichnung().equals(bezeichnung))
					{
						gefundenerArtikel(i);
					}	
				}
			break;	
			case 2:
	
				int id = MyLib.pruefeInt(br,"Geben Sie die ID ein: ","Bitte Zahl eingeben!",0,1000);
				
				for(int i = 0; i < artikeln.size(); i++)
				{
	
					if (artikeln.get(i).getID() == id)
					{
						gefundenerArtikel(i);
					}	
				}	
		}	
	}
	private void gefundenerArtikel(int i) 
	{
		int treffer=0;
		treffer++;
		System.out.println();
		//printArtikel(i); //TODO
		System.out.println((treffer)+ ". Treffer");
		System.out.println();
	}
	public void testDatenfuellen() throws IOException{
		ArrayList<String> bezeichnung = new ArrayList<String>(Arrays.asList("Stift","Radiergummi","Linial","Spitzer"));
		ArrayList<Integer> id = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4));
		ArrayList<Integer> preis = new ArrayList<Integer>(Arrays.asList(5,4,3,4,1));
		
		for(int i = 0;i< bezeichnung.size(); i++)
		{
			fuelleArtikel(bezeichnung,preis,id, i);
		}
		
	System.out.println("Testdaten gefüllt...");
		
	}
	private void fuelleArtikel(ArrayList<String> nachnamen, ArrayList<Integer> preis,ArrayList<Integer> id,  int i) {
		Artikel artikel = new Artikel();
		
		artikel.setBezeichnung(nachnamen.get(i));
		artikel.setPreis(preis.get(i));
		artikel.setID(id.get(i));
		
		//artikel wird in die Liste hinzugefügt
		artikeln.add(artikel);
	}
	public void loadArtikelFromTxt() throws IOException{
		String loadartikel = MyLib.pruefeString(br,"[Artikel]Welche .txt (ohne .txt eingeben) wollen Sie laden?: ", fehlermeldung);
		FileReader fr = new FileReader(loadartikel +".txt");
		BufferedReader br = new BufferedReader(fr);		
		FileReader cfr = new FileReader(loadartikel +".txt");  //Baustelle --> lieber mit br.mark(0) und br.reset()
		BufferedReader cbr = new BufferedReader(cfr);
		
		int count = 0;
		//zählt die Zeilen/Artikeln
		while(cbr.readLine() != null)
		{
			count++;
		}
		
		for(int i = 0; i < count; i++)
		{
			Artikel artikel = new Artikel();
			String txtartikel = br.readLine();
			String[] attribute = txtartikel.split(Pattern.quote("|")); //Trennung des Strings
							
			artikel.setBezeichnung(attribute[0]);
			artikel.setPreis(Integer.parseInt(attribute[1]));
			artikel.setID(Integer.parseInt(attribute[2]));
			artikeln.add(artikel);
		}
	
	br.close();
	cbr.close();
	}
	public void findeArtikel(int id) throws IOException{
			
			for(int i = 0; i < artikeln.size(); i++)
			{
				if (artikeln.get(i).getID() == id)
				{
						gefundenerArtikel(i);
				}	
			}	
	}
	public void loescheArtikel() throws IOException 
	{
		findeArtikel();
		int auswahl = MyLib.pruefeInt(br, "Geben Sie die ID des zu löschenden Artikel ein: ", fehlermeldung, 0, 1000);
		int indexliste = 0;
		boolean kundeexistiert = false;
		String artikelbezeichnung = null;

		for(int i = 0;i < artikeln.size();i++) //mithilfe der id den Namen aus der Liste Kunde suchen
		{
			if(auswahl == artikeln.get(i).getID())
			{
				kundeexistiert = true;
				artikelbezeichnung = artikeln.get(i).getBezeichnung();
				indexliste =artikeln.indexOf(artikeln.get(i));
				artikeln.remove(indexliste);
				System.out.println("Arikel "+ artikelbezeichnung +" mit der ID: "+indexliste+" wurde erfolgreich gelöscht");

			}
			if((!kundeexistiert)&&(i == (artikeln.size()-1))) //Damit nicht bei jedem Kunden die Nachricht kommt
			{
				System.out.println("Dieser Artikel existiert nicht!"); 
			}
		}
	}
	
}
