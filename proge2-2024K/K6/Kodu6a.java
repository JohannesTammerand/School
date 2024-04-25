import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class Kodu6a {

	static boolean kasSobib(int[] x, int s){
		if (x.length == 0) {
			return false;
		} else if (s == 0){
			return true;
		} else if (x[0] > s) {
			return false;
		}

		for (int i = 0; i < x.length; i++) {
			if (kasSobib(osaJarjend(x, i+1), s - x[i])){
				return true;
			}
		}

		return false;
	}

	static int[] osaJarjend(int[] a, int algus){
		int[] uus = new int[a.length - algus];

		for (int i = algus; i < a.length; i++) {
			uus[i- algus] = a[i];
		}

		return uus;
	}


	static String[] sõnepõime(String[] a, String[] b){
		/*
		ArrayList<String[]> c = põimeAbi(a, b);
		int pikkusteSumma = a.length + b.length;
		String[] d = new String[c.length / pikkusteSumma];


		d[0] = c[0];
		for (int i = 1; i <= c.length; i++) {
			d[i / pikkusteSumma] += c[i - 1];
		}

		return d;
		*/
		return null;
	}
/*
	static ArrayList<String[]> põimeAbi(String[] a, String[] b){

		ArrayList<String[]> c = new ArrayList<String[]>();

		if (a.length > 0){
			c.addAll(põimeAbi(eemaldaEsimene(a), b));
		}

		if (b.length > 0){
			c.addAll(põimeAbi(a, eemaldaEsimene(b)));
		}

		return c;

 */


		/*
		ArrayList<String> c = new ArrayList<String>();

		if (a.length == 0 && b.length == 1){
			return new String[] {b[0]};
		} else if  (a.length == 1 && b.length == 0){
			return new String[] {a[0]};
		} else if (a.length != 0){
			c.addAll(Arrays.asList(põimeAbi(eemaldaEsimene(a), b)));
		} else if (b.length != 0){
			c.addAll(Arrays.asList(põimeAbi(a, eemaldaEsimene(b))));
		}


		String[] d = new String[c.size()];
		d = c.toArray(d);

		return d;



	}
	*/
/*
	static String[] eemaldaEsimene (String[] a){
		String[] uus = new String[a.length-1];

		for (int i = 1; i < a.length; i++){
			uus[i-1] = a[i];
		}

		return uus;
	}

 */

    public static void main(String[] args) {

		System.out.println("Test");

    }//peameetod




}//Kodu6a
