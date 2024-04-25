import java.util.Arrays;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 4b
 * Teema: Massiivide ja maatriksite omavahel teisendamine
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu4b{

	/**
	 * Tagastab võimalikult väikse ruudukujulise maatriksi, mis on täidetud antud massiivi elementidega.
	 * Maatriksi elemente, mis massiivi pikkuse tõttu väärtust ei saa, ei looda
	 * @param a Antud massiiv
	 * @return Loodud maatriks
	 */
   public static int[][] taidaMassiiv(int[] a){
	   int suurus = 1;
	   while(Math.pow(suurus, 2) < a.length){
		   suurus++;
	   }

	   int[][] maatriks = new int[suurus][];

	   for (int i = 0; i < maatriks.length; i++) {
		   int[] rida;
		   if (a.length - i * suurus > 0){
			   rida = new int[Math.min(suurus, a.length - i * suurus)];

			   for (int j = 0; j < rida.length; j++) {
				   rida[j] = a[i*suurus + j];
			   }
		   } else {
			   rida = new int[0];
		   }

		   maatriks[i] = rida;
	   }
	   return maatriks;
   }

	/**
	 * Tagastab massiivi, mis sisaldab massiivi peadiagonaaliga paralleelsete diagonaalide elemnte, järjestatud vasakult
	 * paremale ja ülevalt alla
	 * @param b Antud maatriks
	 * @return Massiiv maatriksi diagonaalidega
	 */
	public static int[] diagonaalid(int[][] b){
	   int arve = 0; //Kui palju väärtustatud elemente maatriksis on
	   for(int[] b1 : b){
		   for (int b2 : b1){
			   arve++;
		   }
	   }

	   int x = 0;
	   int y = b[0].length-1; //Alumise vasaku nurga koordinaadid

	   int algusX = x;
	   int algusY = y; //Diagonaali alguskoordinaadid

	   int[] tulemus = new int[arve];
	   int i = 0;

	   while(true){
		   if (b[0].length*(y) + x+1 <= arve){
			   tulemus[i] = b[y][x];
			   i++;

			   if (y == 0 && x == b[0].length-1){
				   break;
			   }
		   }

		   if (x == b[0].length-1 || y == b[0].length-1){
			   if (x == b[0].length-1){
				   algusX = Math.min(algusX+1, b[0].length-1);

			   }

			   algusY = Math.max(algusY-1, 0);

			   x = algusX;
			   y = algusY;
		   } else {
			   x++;
			   y++;
		   }
	   }
	   return tulemus;
   }

	/**
	 * Aitab ülesande tulemuste väljastamisega
	 * @param a Massiiv, millest funktsioonide tulemused väljastada
	 */
	public static void prindiTulemused(int[] a){
	   System.out.println("Massiiv:");
	   System.out.println(Arrays.toString(a));
	   System.out.println("\n");

	   int[][] a2=taidaMassiiv(a);
	   System.out.println("Massiivist loodud maatriks:");
	   for (int[] rida : a2){
		   System.out.println(Arrays.toString(rida));
	   }
	   System.out.println("\n");

	   int[] a3 = diagonaalid(a2);
	   System.out.println("Maatriksi diagonaalide massiiv:");
	   System.out.println(Arrays.toString(a3));
   }

	public static void main(String[] args) {
		System.out.println("Kodutöö nr 4b. \t\t\t\t Programmi väljund");
		System.out.println("==============================================");

		int[] a={1,2,3,4,5,6,7};
		int[] b={9,8,7,6,5,4,3,2,1,0};

		prindiTulemused(a);
		System.out.println("\n-----------------------------------\n");
		prindiTulemused(b);

		System.out.println("==============================================");
	}//main

}//klass