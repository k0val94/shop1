package shop;

import java.io.*;
public class Main 
{
	

	public static void main(String[] args) throws IOException 
	{
		// u.a. für eingabe über Konsole
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		mainMenue(br);

	}
	private static void mainMenue(BufferedReader br) throws IOException {
		System.out.println("laptop");		
		System.out.println();	
		System.out.println("################################");	
		System.out.println("##  Kundenverwaltung     [1]  ##");	
		System.out.println("##  Artikelverwaltung    [2]  ##");
		System.out.println("##  Auftragsverwaltung   [3]  ##");
		System.out.println("##  Beenden              [4]  ##");	
		System.out.println("################################");	
		String frage = "Was möchten Sie tun?: ";
		String fehlermeldung = "Bitte Zahl eingeben!";
		int auswahlvon = 1, auswahlbis = 4;
		
		int mainauswahl = MyLib.pruefeInt(br, frage, fehlermeldung, 
				auswahlvon, auswahlbis);
		
		switch(mainauswahl){
		case 1: 
		
			kundenverwaltung(br);
		
		break;
		case 2: 
			artikelverwaltung(br);
		
		break;
		case 3: 
			auftragsverwaltung(br);
		
		break;
		case 4: 
			System.out.print("Programm ende...");
			System.exit(0);
		break;
		}
	}

	private static void auftragsverwaltung(BufferedReader br)throws IOException {
		Auftrag auftrag = new Auftrag(); //für Referenz
		
		boolean wiederholen = true;
		System.out.println("Auftragsverwaltung");	
		do
		{
			int auftragsauswahl = runView(br, "Auftrag");
			auswahlFunktionAuswahl(auftrag, auftragsauswahl);
			
		}while(wiederholen);
	}
	private static void auswahlFunktionAuswahl(Auftrag auftrag,
			int auftragsauswahl) throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		switch(auftragsauswahl){
		case 1: auftrag.erzeugeAuftrag();
		break;
		case 2: auftrag.ausgabeAlleAuftrag();
		break;
		case 3: auftrag.findeAuftrag();
		break;
		case 4: auftrag.loescheAuftrag();
		break;
		case 5: auftrag.testDatenfuellen();
		break;
		case 6: auftrag.loadAuftragFromTxt();
		break;
		case 7: mainMenue(br);
		break;
		case 8: System.out.print("Programm ende...");
				System.exit(0);
		break;
		}
		
	}
	

	private static void kundenverwaltung(BufferedReader br) throws IOException {
		Kunde kunde = new Kunde(true); //für Referenz
		
		boolean wiederholen = true;
		System.out.println("Kundenverwaltung");	
		do
		{
			int kundeauswahl = runView(br, "Kunde");
			auswahlFunktionKunde(kunde, kundeauswahl);
			
		}while(wiederholen);
	}
    //Funktionen über Zahlen auszuwählen
	private static void auswahlFunktionKunde(Kunde kunde, int kundeauswahl)
			throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		switch(kundeauswahl){
		case 1: kunde.erzeugeKunde();
		break;
		case 2: kunde.ausgabeAlleKunden();
		break;
		case 3: kunde.findeKunde();
		break;
		case 4: kunde.loescheKunde();
		break;
		case 5: kunde.testDatenfuellen();
		break;
		case 6: kunde.loadKundeFromTxt();;
		break;
		case 7: mainMenue(br);
		break;
		case 8: System.out.print("Programm ende...");
				System.exit(0);
		break;
		}
	}

	private static void artikelverwaltung(BufferedReader br) throws IOException {
		Artikel artikel = new Artikel(true); //für Referenz
		boolean wiederholen = true;
		System.out.println("Artikelverwaltung");	
		do
		{
			int artikelauswahl = runView(br, "Artikel");
			auswahlFunktionArtikel(artikel, artikelauswahl);
			
		}while(wiederholen);
	}
	private static void auswahlFunktionArtikel(Artikel artikel,int artikelauswahl) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
	    switch(artikelauswahl){
					
	    case 1: artikel.erzeugeArtikel();	
	    break;				
	    case 2: artikel.ausgabeAlleArtikel();				
	    break;				
	    case 3: artikel.findeArtikel();				
	    break;
	    case 4: artikel.loescheArtikel();				
	    break;
	    case 5: artikel.testDatenfuellen();			
	    break;			
	    case 6: artikel.loadArtikelFromTxt();;			
	    break;			
	    case 7: mainMenue(br);		
	    break;		
	    case 8: System.out.print("Programm ende...");		
	    System.exit(0);		
	    break;				
	    }
	
	}
	private static int runView(BufferedReader br, String viewstring) throws IOException {
		
		String leerstring = "";
		int count = 12;
		count = count - viewstring.length();
		
		for(int i = 0;i < count;i++)
		{
			leerstring = leerstring + " ";
		}
		
		System.out.println();
		System.out.println("################################");	
		System.out.println("##  "+viewstring+" anlegen "+leerstring+"[1]  ##");	
		System.out.println("##  "+viewstring+" ausgeben"+leerstring+"[2]  ##");	
		System.out.println("##  "+viewstring+" finden  "+leerstring+"[3]  ##");	
		System.out.println("##  "+viewstring+" löschen "+leerstring+"[4]  ##");	
		System.out.println("##  Testdaten füllen     [5]  ##");	
		System.out.println("##  Aus .txt einlesen    [6]  ##");
		System.out.println("##  Zurück               [7]  ##");
		System.out.println("##  Beenden              [8]  ##");	
		System.out.println("################################");	

		int auswahl = MyLib.pruefeInt(br,"Was möchten Sie tun?: ","Bitte Zahl eingeben!",1,8);
		return auswahl;
	}
		
	
	
	
	
}