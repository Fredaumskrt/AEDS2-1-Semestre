// 1) Palíndromo - Faça um programa recebe várias frases e identifica se elas são palíndromos

// Entrada : composta por várias linhas sendo que a última contém a palavra FIM

// Saída: para cada linha de entrada, escreva SIM / NÃO, indicando se a linha é um palíndromo
// String verificador;
// palavra = palavra.replaceAll("\\s+", "").toLowerCase().replace("\n", "").replace("\r", ""); // tirar espaço

public class Palindromo01 {
    public static void main(String[] args) {
        
        String palavra = MyIO.readLine();
        while(true){
            if(isFim(palavra)){
                // palavra = palavra.replaceAll("\\s+", "").toLowerCase().replace("\n", "").replace("\r", ""); // tirar espaço
                break;
            }
            MyIO.println(epalindromo(palavra) ? "SIM": "NAO");
            palavra = MyIO.readLine();
        }
        
        
    }

    static boolean epalindromo(String str) {
        for (int i = 0; i < (str.length() / 2) + 1; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1))
                return false; // ovo
        }

        return true;

    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}

// java.palindro < pub.in.txt > teste.txt
// for (int i = palavra.length() - 1; i >= 0; i--) {
// palindromo += palavra.charAt
// {
