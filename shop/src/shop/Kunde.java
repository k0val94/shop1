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



public class Kunde 
{

	String fehlermeldung = "Fehlerhafte Eingabe!";
	private boolean bearbeiten = false;
	private int id;
	private String name;
	private String vorname;
	private String adresse;
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	
	public ArrayList<Kunde> kunden = new ArrayList<Kunde>();
	//GETTER & SETTER
	public int getID() 
	{
		return id;
	}
	public void setID(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getVorname() 
	{
		return vorname;
	}
	public void setVorname(String vorname) 
	{
		this.vorname = vorname;
	}
	public String getAdresse() 
	{
		return adresse;
	}
	public void setAdresse(String adresse) 
	{
		this.adresse = adresse;
	}
	//GETTER & SETTER END
	
	public Kunde(){
		
	}
	public Kunde(boolean bearbeiten){
		this.bearbeiten = bearbeiten;
	}
	public void erzeugeKunde() throws IOException
	{

		//Daten werden in kunde geschrieben
		System.out.println("Anlegen von Kunde...");
		String kundenname = MyLib.pruefeString(br,"Nachname: ", fehlermeldung);
		String kundenvorname = MyLib.pruefeString(br,"Vorname: ", fehlermeldung);
		String kundenadresse = MyLib.pruefeString(br,"Adresse: ", fehlermeldung);
		beschreibeKunde(kundenname, kundenvorname, kundenadresse);
		
	}
	
	public void beschreibeKunde(String kundenname,
			String kundenvorname, String kundenadresse) {
		Kunde kunde = new Kunde();
		kunde.setName(kundenname);
		kunde.setVorname(kundenvorname);
		kunde.setAdresse(kundenadresse);
		compareID();
		kunde.setID(id);
		//kunde wird in die Liste hinzugefügt
		kunden.add(kunde);
	}
	private int compareID(){ //TODO keine gelöschten ID benutzen
		for(int i=0;i < kunden.size();i++){
			
			//if((kunden.get(i).getID() == id)&&(i == (kunden.size()-1))){
			if((kunden.get(i).getID() == id)){	
				id++;
			}
		}
		return id;
	}
	
	public void ausgabeAlleKunden() throws IOException  //TODO wenn tupel > attr = OK attr > tupel != OK
	{
		System.out.println("Ausgabe aller Kunden..."); 	
		System.out.println("Kundenanzahl: "+ kunden.size());

		ArrayList<String> attribute = new ArrayList<String>(Arrays.asList("Nachname","Vorname","Adresse","ID"));
		//Init laengste Attribute
		int laengster_nachname = attribute.get(0).length();
		int laengster_vorname = attribute.get(1).length();
		int laengste_adresse = attribute.get(2).length();
		int laengste_id = attribute.get(3).length();
		
		for(int i = 0; i < kunden.size(); i++) //Ermittle die laengsten Attribute
		{
			int laenge_nachname = kunden.get(i).getName().length();
			laengster_nachname = MyLib.laengsteTupel(laengster_nachname, laenge_nachname);
			int laenge_vorname = kunden.get(i).getVorname().length();
			laengster_vorname = MyLib.laengsteTupel(laengster_vorname, laenge_vorname);
			int laenge_adresse = kunden.get(i).getAdresse().length();
			laengste_adresse = MyLib.laengsteTupel(laengste_adresse, laenge_adresse);
			int laenge_id = String.valueOf(kunden.get(i).getID()).length();
			laengste_id  = MyLib.laengsteTupel(laengste_id, laenge_id);
		}
		
		int laenge_nachname = 0;
		int laenge_vorname = 0;
		int laenge_adresse = 0;
		int laenge_id = 0;
		
		String leerstring_nachname = "";
		String leerstring_vorname = "";
		String leerstring_adresse = "";
		String leerstring_id = "";
		for(int i = 0; i < kunden.size(); i++)
		{
			if(i == 0){ //Für Attribute
				
				printAttribute(attribute, laengster_nachname,
						laengster_vorname, laengste_adresse, laengste_id);
		
			}

			
			laenge_nachname = kunden.get(i).getName().length();
			laenge_vorname = kunden.get(i).getVorname().length();
			laenge_adresse = kunden.get(i).getAdresse().length();
			laenge_id = String.valueOf(kunden.get(i).getID()).length();
			
			leerstring_nachname = MyLib.generateSpace(laenge_nachname, laengster_nachname);
			leerstring_vorname = MyLib.generateSpace(laenge_vorname, laengster_vorname);
			leerstring_adresse = MyLib.generateSpace(laenge_adresse , laengste_adresse);
			leerstring_id = MyLib.generateSpace(laenge_id, laengste_id);
			
			printKunde(leerstring_nachname, leerstring_vorname,
					leerstring_adresse, leerstring_id, i);
		
			
		}
		
		
		
		if((kunden.size() != 0)&&(bearbeiten))
		{
			saveKundeInTxt();
		}	
	}
	protected void printAttribute(ArrayList<String> attribute,
			int laengster_nachname, int laengster_vorname,
			int laengste_adresse, int laengste_id) {
		int laenge_nachname;
		int laenge_vorname;
		int laenge_adresse;
		int laenge_id;
		String leerstring_nachname;
		String leerstring_vorname;
		String leerstring_adresse;
		String leerstring_id;
		laenge_nachname = attribute.get(0).length();
		laenge_vorname = attribute.get(1).length();
		laenge_adresse = attribute.get(2).length();
		laenge_id = attribute.get(3).length();
		
		leerstring_nachname = MyLib.generateSpace(laenge_nachname, laengster_nachname);
		leerstring_vorname = MyLib.generateSpace(laenge_vorname, laengster_vorname);
		leerstring_adresse = MyLib.generateSpace(laenge_adresse , laengste_adresse);
		leerstring_id = MyLib.generateSpace(laenge_id, laengste_id);
		
		System.out.print(attribute.get(0));
		System.out.print(leerstring_nachname);
		System.out.print("|");
		System.out.print(attribute.get(1));
		System.out.print(leerstring_vorname);
		System.out.print("|");
		System.out.print(attribute.get(2));
		System.out.print(leerstring_adresse);
		System.out.print("|");
		System.out.print(attribute.get(3));
		System.out.print(leerstring_id);
		System.out.println("|");
	}
	protected void printKunde(String leerstring_nachname,
			String leerstring_vorname, String leerstring_adresse,
			String leerstring_id, int i) {
		System.out.print(kunden.get(i).getName());
		System.out.print(leerstring_nachname);
		System.out.print("|");
		System.out.print(kunden.get(i).getVorname());
		System.out.print(leerstring_vorname);
		System.out.print("|");
		System.out.print(kunden.get(i).getAdresse());
		System.out.print(leerstring_adresse);
		System.out.print("|");
		System.out.print(String.valueOf(kunden.get(i).getID()));
		System.out.print(leerstring_id);
		System.out.print("|");
		System.out.println();	
		
		leerstring_nachname = "";
		leerstring_vorname = "";
		leerstring_adresse = "";
		leerstring_id = "";
	}
	
	


	private void saveKundeInTxt() throws IOException {
		System.out.print("geben Sie [s] ein um die Liste zu speichern: ");
		String savekunde = br.readLine();
		
		if(savekunde.equals("s"))
		{
			String nameTxt = MyLib.pruefeString(br,"geben Sie der .txt einen Namen: ", fehlermeldung);
			FileWriter fp = new FileWriter(nameTxt+".txt");
			BufferedWriter bw = new BufferedWriter(fp);
			
			printKundeInTxt(bw);			
			System.out.println("gespeichert..."); 
		}
		else
		{
			System.out.println("nicht gespeichert..."); 
		}
	}
	private void printKundeInTxt(BufferedWriter bw) throws IOException {
		for(int i = 0; i < kunden.size(); i++)
		{

			bw.write(kunden.get(i).getName());
			bw.write("|");
			bw.write(kunden.get(i).getVorname());
			bw.write("|");
			bw.write(kunden.get(i).getAdresse());
			bw.write("|");
			bw.write(String.valueOf(kunden.get(i).getID()));
			bw.write("|");
			bw.newLine();
			bw.close();
		}
	}
	public void findeKunde() throws IOException 
	{  //Nicht komplet
		System.out.println("finden Sie einen Kunden..."); 
		 
		int suchtyp = MyLib.pruefeInt(br,"Suchen per [1]Nachame, [2]Vorname oder [3]ID?: ","Bitte Zahl eingeben!",1,3);
		switch(suchtyp)
		{	  		
			case 1:
				String nachname = MyLib.pruefeString(br,"Geben Sie den Nachnamen ein: ", fehlermeldung);
				for(int i = 0; i < kunden.size(); i++)
				{
						if (kunden.get(i).getName().equals(nachname))
						{
							gefundenerKunde(i);
						}
				}
			break;
			case 2:
				String vorname = MyLib.pruefeString(br,"Geben Sie den Vornamen ein: ", fehlermeldung);				
				for(int i = 0; i < kunden.size(); i++)
				{
						if (kunden.get(i).getVorname().equals(vorname))
						{
							gefundenerKunde(i);
						}	
				}
			break;
			case 3:
				int id = MyLib.pruefeInt(br,"Geben Sie die ID ein: ","Bitte Zahl eingeben!",0,1000);				
				for(int i = 0; i < kunden.size(); i++)
				{
						if (kunden.get(i).getID() == id)
						{
							gefundenerKunde(i);
						}
				}	
		}	
	}
	private void gefundenerKunde(int i) 
	{
		int treffer=0;
		treffer++;
		System.out.println();
		//printKunde(i); //TODO
		System.out.println((treffer)+ ". Treffer");
		System.out.println();
	}
	public void testDatenfuellen() throws IOException 
	{
		ArrayList<String> nachnamen = new ArrayList<String>(Arrays.asList("Mueller","Schmidt","Mueller","Laender","Schild"));
		ArrayList<String> vornamen = new ArrayList<String>(Arrays.asList("Anna","Katy","Manuel","Kai","Benni"));
		ArrayList<String> adressen = new ArrayList<String>(Arrays.asList("DE, Kyllburg, Am Hof 4","DE, Berlin, Hauptstraße 4","DE, Bitburg, Am Industriegebiet 7","DE, Bremen, Am Baumbach 23","NL, Amsterdam, Maas 9"));
		ArrayList<Integer> id = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4));
		
		for(int i = 0;i< nachnamen.size(); i++)
		{
			fuelleKunde(nachnamen, vornamen, adressen, id, i);
		}
		
	System.out.println("Testdaten gefüllt...");
	}
	
	private void fuelleKunde(ArrayList<String> nachnamen,
			ArrayList<String> vornamen, ArrayList<String> adressen,
			ArrayList<Integer> id, int i) {
		Kunde kunde = new Kunde();
		
		kunde.setName(nachnamen.get(i));
		kunde.setVorname(vornamen.get(i));
		kunde.setAdresse(adressen.get(i));
		kunde.setID(id.get(i));
		
		//kunde wird in die Liste hinzugefügt
		kunden.add(kunde);
	}
	public void loadKundeFromTxt() throws IOException
	{
		
		//TODO bei falscher Wahl absturz abfangen
		String loadkunde = MyLib.pruefeString(br,"[Kunde]Welche .txt (ohne .txt eingeben) wollen Sie laden?: ", fehlermeldung);
		FileReader fr = new FileReader(loadkunde +".txt");
		BufferedReader br = new BufferedReader(fr);		
		FileReader cfr = new FileReader(loadkunde +".txt");  //TODO Baustelle --> lieber mit br.mark(0) und br.reset()
		BufferedReader cbr = new BufferedReader(cfr);
		
		int count = 0;
		while(cbr.readLine() != null)
		{
			count++;
		}
		for(int i = 0; i < count; i++)
		{
			Kunde kunde = new Kunde();
			String txtkunde = br.readLine();
			String[] attribute = txtkunde.split(Pattern.quote("|")); //Trennung des Strings
							
			kunde.setName(attribute[0]);
			kunde.setVorname(attribute[1]);
			kunde.setAdresse(attribute[2]);
			kunde.setID(Integer.parseInt(attribute[3]));
			kunden.add(kunde);
		}
	br.close();
	cbr.close();
	}
	public void findeKunde(int id) throws IOException{
		
		System.out.println("Sucht...");
				
			for(int i = 0; i < kunden.size(); i++)
			{

				if (kunden.get(i).getID() == id)
				{
						gefundenerKunde(i);
				}	
			}
	}
	public void loescheKunde() throws IOException { //TODO kunde den es nicht gibt nicht löschen
		findeKunde();
		int auswahl = MyLib.pruefeInt(br, "Geben Sie die ID des zu löschenden Kunden ein: ", fehlermeldung, 0, 1000);
		int indexliste = 0;
		boolean kundeexistiert = false;
		String kundennachname = null;
		String kundenvorname = null;
		for(int i = 0;i < kunden.size();i++) //mithilfe der id den Namen aus der Liste Kunde suchen
		{
			if(auswahl == kunden.get(i).getID())
			{
				kundeexistiert = true;
				kundennachname = kunden.get(i).getName();
				kundenvorname = kunden.get(i).getVorname();
				indexliste =kunden.indexOf(kunden.get(i));
				kunden.remove(indexliste);
				System.out.println("Kunde "+ kundennachname +" "+ kundenvorname+" mit der ID: "+indexliste+" wurde erfolgreich gelöscht");

			}
			if((!kundeexistiert)&&(i == (kunden.size()-1))) //Damit nicht bei jedem Kunden die Nachricht kommt
			{
				System.out.println("Dieser Kunde existiert nicht!"); 
			}
		}
	}
}
