package shop;

import java.io.BufferedReader;
import java.io.IOException;

class MyLib {
	
		public static int pruefeInt(BufferedReader br,
			String frage, String fehlermeldung, int auswahlvon, int auswahlbis)
			throws IOException {

			int auswahl = -1000;
			do{
			System.out.print(frage);	
				try
				{
					auswahl = Integer.parseInt(br.readLine());
				}
				catch(NumberFormatException e)
				{
					System.out.println(fehlermeldung);	
				}
				if((auswahl<auswahlvon)||(auswahl>auswahlbis))
				{
					System.out.println("Fehlerhafte Auswahl");
				}
			}while((auswahl<auswahlvon)||(auswahl>auswahlbis));
			return auswahl;
			
	}
		
	public static String pruefeString(BufferedReader br, String frage, String fehlermeldung) {
		String eingabe = null;
		do
		{
			System.out.print(frage);
			try
			{
				eingabe = br.readLine();
			}
			catch(Exception e)
			{
				System.out.println(fehlermeldung);
			}	
			if(eingabe.equals(""))
			{
				System.out.println("Eingabe darf nicht leer sein!");
			}
			}while(eingabe.equals(""));
		return eingabe;
	}
	
	public static int laengsteTupel(int laengste_tupel, int laenge_tupel) {
		
		if(laengste_tupel <= laenge_tupel)
		{
			laengste_tupel = laenge_tupel;
		}
		return laengste_tupel;
	}
	
	public static String generateSpace(int laenge_tupel, int laengste_tupel) {
		String leerstring = "";
		int count = laengste_tupel;
		count = count - laenge_tupel;
		
		for(int j = 0;j <= count;j++)
		{
			leerstring += " ";
		}
		return leerstring;
	}
}
