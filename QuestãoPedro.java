import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numeroNomes = scanner.nextInt();
        scanner.nextLine();
        String[] nomes = new String[numeroNomes];

        for (int i = 0; i < numeroNomes; i++) {
            StringBuilder nome1 = new StringBuilder(scanner.nextLine());
            StringBuilder nome2 = new StringBuilder(scanner.nextLine());
            nomes[i] = "";

            while (nome1.length() > 0 || nome2.length() > 0) {
                nomes[i] += nome1.charAt(0);
                nome1.deleteCharAt(0);

                if (nome1.length() > 0) {
                    nomes[i] += nome1.charAt(0);
                    nome1.deleteCharAt(0);
                }

                nomes[i] += nome2.charAt(0);
                nome2.deleteCharAt(0);

                if (nome2.length() > 0) {
                    nomes[i] += nome2.charAt(0);
                    nome2.deleteCharAt(0);
                }
            }
        }

        for (int j = 0; j < nomes.length; j++) {
            System.out.println(nomes[j]);
        }
    }
}
