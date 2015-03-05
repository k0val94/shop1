package shop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Auftrag {
		
		String fehlermeldung = "Fehlerhafte Eingabe!";
		private int id;
		private int kundenid;
		private String kundenvorname;
		private String kundennachname;
		private int artikelid;
		private String artikelbezeichnung;
		private int preis;
		private int stueck;
	
		ArrayList<Auftrag> auftraege = new ArrayList<Auftrag>();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		//GETTER & SETTER
		public int getID() {
			return id;
		}
		public void setID(int id) {
			this.id = id;
		}
		public int getPreis() {
			return preis;
		}
		public void setPreis(int preis) {
			this.preis = preis;
		}
		public int getStueck() {
			return stueck;
		}
		public void setStueck(int stueck) {
			this.stueck = stueck;
		}
		public int getKundenid() {
			return kundenid;
		}
		public void setKundenid(int kundenid) {

			this.kundenid = kundenid;
		}
		public String getKundenvorname() {
			return kundenvorname;
		}
		public void setKundenvorname(String kundenvorname) {
			this.kundenvorname = kundenvorname;
		}
		public String getKundennachname() {
			return kundennachname;
		}
		public void setKundennachname(String kundennachname) {
			this.kundennachname = kundennachname;
		}
		public String getArtikelbezeichnung() {
			return artikelbezeichnung;
		}
		public void setArtikelbezeichnung(String artikelbezeichnung) {
			this.artikelbezeichnung = artikelbezeichnung;
		}
		public int getArtikelid() {
			return artikelid;
		}
		//GETTER & SETTER END
		
		
		public void setArtikelid(int artikelid) {
			this.artikelid = artikelid;
		}
		public void erzeugeAuftrag() throws IOException
		{
			
			//Daten werden in auftrag geschrieben
			//Instaziierung
			System.out.println("Anlegen von Auftrag...");
			Auftrag auftrag = new Auftrag();
			Artikel artikel = new Artikel();
			Kunde kunde = new Kunde();
			
			compareID();	
			auftrag.setID(id);
			//Kunde und Artikel werden aus der Txt. gelesen und in eine Liste "auftraege" gespeichert.
			//if(kunde.kunden.size() == 0)
			//{
				kunde.loadKundeFromTxt(); //[...].size() immer 0 wegen instanziierung, muss behoben werden!
			//}
			

			kunde.ausgabeAlleKunden();

			kundenid = MyLib.pruefeInt(br, "Welcher Kunde bestellt? (id eingeben): ","Bitte Zahl eingeben!", 
					0, 1000);
			auftrag.setKundenid(kundenid);
			kunde.findeKunde(kundenid);
			
			String kundennachname = null;
			String kundenvorname = null;
			for(int i = 0;i < kunde.kunden.size();i++) //mithilfe der id den Namen aus der Liste Kunde suchen
			{
				if(auftrag.getKundenid() == kunde.kunden.get(i).getID())
				{
					kundennachname = kunde.kunden.get(i).getName();
					kundenvorname = kunde.kunden.get(i).getVorname();
				}	
			}
			auftrag.setKundennachname(kundennachname);
			auftrag.setKundenvorname(kundenvorname);
			artikel.loadArtikelFromTxt();
			artikel.ausgabeAlleArtikel();
			artikelid =  MyLib.pruefeInt(br, "Was bestellt der Kunde? (id eingeben): ","Bitte Zahl eingeben!", 
					0, 1000);
			auftrag.setArtikelid(artikelid);
			artikel.findeArtikel(artikelid);
			auftrag.setStueck( MyLib.pruefeInt(br, "Wieviel von diesem Objekt?: ","Bitte Zahl eingeben!", 
					0, 1000));
			String artikelbezeichnung = null;
			int einzelpreis = 0;	
			for(int i = 0;i < artikel.artikeln.size();i++) //mithilfe der id den preis aus der Liste Artikel suchen
			{
				if(auftrag.getArtikelid() == artikel.artikeln.get(i).getID())
				{
					einzelpreis = artikel.artikeln.get(i).getPreis();
					artikelbezeichnung = artikel.artikeln.get(i).getBezeichnung();
				}	
			}
			
			auftrag.setPreis(auftrag.getStueck()*einzelpreis);
			auftrag.setArtikelbezeichnung(artikelbezeichnung);
			
			//auftrag wird in die Liste hinzugefügt
			auftraege.add(auftrag);
			
		}
		private int compareID() {
			for(int i=0;i < auftraege.size();i++){
				
				if((auftraege.get(i).getID() == id)){
					
					id++;
				}
			}
			return id;
		}

		
		public void ausgabeAlleAuftrag() throws IOException {
			System.out.println("Ausgabe aller Aufträge..."); 	
			System.out.println("Auftragsanzahl: "+ auftraege.size());
		
			for(int i = 0; i < auftraege.size(); i++)
			{
				printAuftrag(i);	
			}
			
			if(auftraege.size() != 0)
			{
				saveAuftragInTxt();
			}	
			
		}
		private void printAuftrag(int i) {
			
			System.out.print(auftraege.get(i).getKundenid());
			System.out.print("|");
			System.out.print(auftraege.get(i).getKundennachname());
			System.out.print("|");
			System.out.print(auftraege.get(i).getKundenvorname());
			System.out.print("|");
			System.out.print(auftraege.get(i).getArtikelid());
			System.out.print("|");
			System.out.print(auftraege.get(i).getArtikelbezeichnung());
			System.out.print("|");
			System.out.print(auftraege.get(i).getPreis());
			System.out.print("|");
			System.out.print(auftraege.get(i).getStueck());
			System.out.print("|");
			System.out.print(auftraege.get(i).getID());
			System.out.print("|");
			System.out.println();
			
		}
		private void saveAuftragInTxt() throws IOException{
			System.out.print("geben Sie [s] ein um die Liste zu speichern: "); 
			String saveauftrag = br.readLine();
			
			if(saveauftrag.equals("s"))
			{
				String nameTxt = MyLib.pruefeString(br,"geben Sie der .txt einen Namen: ", fehlermeldung);
				FileWriter fp = new FileWriter(nameTxt+".txt");
				BufferedWriter bw = new BufferedWriter(fp);
				printAuftragInTxt(bw);
				System.out.println("gespeichert..."); 
			
			}
			else
			{
				System.out.println("nicht gespeichert..."); 
			}
		}
		public void printAuftragInTxt(BufferedWriter bw) throws IOException {
			for(int i = 0; i < auftraege.size(); i++)
			{

				bw.write(String.valueOf(auftraege.get(i).getKundenid()));
				bw.write("|");
				bw.write(auftraege.get(i).getKundennachname());
				bw.write("|");
				bw.write(auftraege.get(i).getKundenvorname());
				bw.write("|");
				bw.write(String.valueOf(auftraege.get(i).getArtikelid()));
				bw.write("|");
				bw.write(auftraege.get(i).getArtikelbezeichnung());
				bw.write("|");
				bw.write(String.valueOf(auftraege.get(i).getPreis()));
				bw.write("|");
				bw.write(String.valueOf(auftraege.get(i).getStueck()));
				bw.write("|");
				bw.write(String.valueOf(auftraege.get(i).getID()));
				bw.write("|");
				bw.newLine();
				bw.close();
				
			}
		}
	
	
		public void findeAuftrag() throws IOException{
			System.out.println("finden Sie einen Auftrag..."); 	

			int suchtyp = MyLib.pruefeInt(br,"Suchen per [1]KundenID, [2]ArtikelID oder [3]AuftragsID?: ","Bitte Zahl eingeben!", 1,3);
		
			
			switch(suchtyp)
			{  
			
				case 1:
					int kundenid = MyLib.pruefeInt(br, "Geben Sie die KundenID ein: ", "Bitte Zahl eingeben!", 1, 1000);
					
					for(int i = 0; i < auftraege.size(); i++){
						
						if (auftraege.get(i).getKundenid() == kundenid)
						{
							gefundenerAuftrag(i);
						}	
				}
				break;
				case 2:
					System.out.print("Geben Sie die ArtikelID ein: ");

					int artikelid = MyLib.pruefeInt(br,"Geben Sie die ArtikelID ein: ","Bitte Zahl eingeben!",0,1000);
					for(int i = 0; i < auftraege.size(); i++){
						
						if (auftraege.get(i).getArtikelid() == artikelid)
						{
							gefundenerAuftrag(i);
						}	
				}
					break;
				case 3:
					int id = MyLib.pruefeInt(br,"Geben Sie die AuftragsID ein: ","Bitte Zahl eingeben!",0,1000);
					
					for(int i = 0; i < auftraege.size(); i++)
					{

						if (auftraege.get(i).getID() == id)
						{
							gefundenerAuftrag(i);
						}	
				}	
			}	
			
		}
		private void gefundenerAuftrag(int i) {
			int treffer=0;
			treffer++;
			System.out.println();
			printAuftrag(i);
			System.out.println((treffer)+ ". Treffer");
			System.out.println();
			
		}
		public void testDatenfuellen() {
			// TODO Auto-generated method stub

			
		}
		public void loadAuftragFromTxt() throws IOException{		
			String loadauftrag = MyLib.pruefeString(br,"[Artikel]Welche .txt (ohne .txt eingeben) wollen Sie laden?: ", fehlermeldung);
			
			FileReader fr = new FileReader(loadauftrag +".txt");
			BufferedReader br = new BufferedReader(fr);		
			FileReader cfr = new FileReader(loadauftrag +".txt");  //Baustelle --> lieber mit br.mark(0) und br.reset()
			BufferedReader cbr = new BufferedReader(cfr);
			
			int count = 0;
			//zählt die Zeilen/Aufträge
			while(cbr.readLine() != null)
			{
				count++;
			}
			
			for(int i = 0; i < count; i++)
			{
				Auftrag auftrag = new Auftrag();
				String txtauftrag = br.readLine();
				String[] attribute = txtauftrag.split(Pattern.quote("|")); //Trennung des Strings
								
				auftrag.setKundenid(Integer.parseInt(attribute[0]));
				auftrag.setKundennachname(attribute[1]);
				auftrag.setKundenvorname(attribute[2]);
				auftrag.setArtikelid(Integer.parseInt(attribute[3]));
				auftrag.setArtikelbezeichnung(attribute[4]);
				auftrag.setPreis(Integer.parseInt(attribute[5]));
				auftrag.setStueck(Integer.parseInt(attribute[6]));
				auftrag.setID(Integer.parseInt(attribute[7]));
				auftraege.add(auftrag);
			}
			
		br.close();
		cbr.close();	
		}
		public void loescheAuftrag() throws IOException {
			findeAuftrag();
			int auswahl = MyLib.pruefeInt(br, "Geben Sie die ID des zu löschenden Auftrags ein: ", fehlermeldung, 0, 1000);
			int indexliste = 0;
			boolean kundeexistiert = false;


			for(int i = 0;i < auftraege.size();i++) //mithilfe der id den Namen aus der Liste Kunde suchen
			{
				if(auswahl == auftraege.get(i).getID())
				{
					kundeexistiert = true;
					indexliste =auftraege.indexOf(auftraege.get(i));
					auftraege.remove(indexliste);
					System.out.println("Der Auftrag mit der ID: "+indexliste+" wurde erfolgreich gelöscht");

				}
				if((!kundeexistiert)&&(i == (auftraege.size()-1))) //Damit nicht bei jedem Kunden die Nachricht kommt
				{
					System.out.println("Dieser Artikel existiert nicht!"); 
				}
			}
			
		}
		
}
