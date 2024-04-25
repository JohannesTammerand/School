/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 8a
 * Teema: Rekursioon
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

import java.util.Arrays;

class Kodu8a {

	/**
	 * Meetodi ülesandeks on antud järjendis asetada paarisarvud algusesse ning paaritud arvud lõppu, ilma paaris-
	 * ja paaritute arvude sisemist järjekorda muutmata. Lõpus väljastab muudetud järjendi.
	 * @param a Antud järjend
	 */
	public static void teePaarisPaaritud(int[] a){
		if (a.length >= 2){
			int paarisValesti = paarisValesti(a, false, 0);

			if (paarisValesti != -1) {
				int temp = a[paarisValesti];
				a[paarisValesti] = a[paarisValesti-1];
				a[paarisValesti - 1] = temp;
				teePaarisPaaritud(a);
			} else {
				System.out.println(Arrays.toString(a));
			}
		}
	}

	/**
	 * Abifunktsioon eelmise funktsiooni jaoks. Ülesandeks on otsida järjendist esimene paarisarv, mis asub peale
	 * paaritut. Seda sooritab rekursiivselt järjendit uurides. Kui leiab paarisarvu, mis asub peale paaritut,
	 * tagastab selle indeksi.
	 * @param a Järjend, kust otsida
	 * @param paarituLeitud Annab rekursioonis järgnevatele teada, et kas järjendist on paaritu arv leitud.
	 * @param i Näitab, kui sügaval järjendis funktsioon on
	 * @return Leitud paarisarvu indeks
	 */
	public static int paarisValesti(int[] a, boolean paarituLeitud, int i){
		if (paarituLeitud && a[i] % 2 == 0){
			return i;
		} else if (!paarituLeitud && a[i] % 2 != 0){
			paarituLeitud = true;
		} else if (i == a.length - 1){
			return -1;
		}

		return paarisValesti(a, paarituLeitud, i + 1);
	}

	/**
	 * Leiab unaarset rekursiooni kasutades, mitu märgimuutu antud järjendis on
	 * @param a Antud järjend
	 * @return Märgimuutude arv
	 */
	public static int muududUnaarne(int[] a){
		return muududUnaarneAbi(a, 0, 0);
	}

	/**
	 * Abifunktsioon eelmisele funktsioonile, mis leiab järjendis olevate märgimuutude arvu unaarset rekursiooni kasutades
	 * @param a Antud järjend
	 * @param eelmine Eelmine nullist erinev arv, mis oli märgimuudu osa. Kui leiab sama märgiga nullist erineva arvu,
	 *                ei muudeta selle väärtust
	 * @param n Indeks, kui sügaval rekursioon on
	 * @return Märgimuutude arv
	 */
	public static int muududUnaarneAbi(int[] a, int eelmine, int n){
		if (n == a.length){ //KKontrollib, kas funktsioon on järjendi lõppu jõudnud
			return 0;
		} else if (eelmine == 0){ //Muutujale esimese väärtuse andmine
			return muududUnaarneAbi(a, a[n], n + 1);
		} else if ((eelmine < 0) != (a[n] < 0) && a[n] != 0){ //Vaatab, kas eelmine ja praegune arv on erimärgilised
			return muududUnaarneAbi(a, a[n], n + 1) + 1;
		} else {
			return muududUnaarneAbi(a, eelmine, n + 1);
		}
	}

	public static int muududBinaarne(int[] a){
		throw new UnsupportedOperationException();
	}

    public static void main(String[] args) {
		System.out.println("Kodutöö nr 7a. \t\t\t\t Programmi väljund");
		System.out.println("==============================================");
		int[] a = {-1, 0, -7, 3, 10, 4, 0, 2, -1, -5, 6};
		System.out.println("Antud järjend:");
		System.out.println(Arrays.toString(a));
		System.out.println("Sorteeritud järjend:");
		teePaarisPaaritud(a);
		System.out.println('\n');

		System.out.println("Antud järjend:");
		int[] b = {24, 0, 0, -1, -6, 0, 0, 38, 19, 0, 28, 0, -2, 0, 9};
		System.out.println(Arrays.toString(b));
		System.out.println("Märgimuutude arv järjendis:");
		System.out.println(muududUnaarne(b));
		System.out.println("==============================================");
	}
}
