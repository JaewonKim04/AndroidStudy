package gugudan;

import java.util.Scanner;
import io.reactivex.Observable;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Gugudan Input:");
		int dan = Integer.parseInt(in.nextLine());
		in.close();
		Observable<Integer> source = Observable.range(1,9);
		source.subscribe(row -> System.out.println(dan + " * "+row+" = "+dan * row));
	}
	
}
