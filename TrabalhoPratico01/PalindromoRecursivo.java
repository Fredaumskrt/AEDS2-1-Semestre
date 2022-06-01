public class PalindromoRecursivo {
    public static void main(String[] args) {
        String palavra = MyIO.readLine();

        while (true) {
            if (isFim(palavra)) {
                // palavra = palavra.replaceAll("\\s+", "").toLowerCase().replace("\n",
                // "").replace("\r", ""); // tirar espaço
                break;
            }
            MyIO.println(epalindromo(palavra) ? "SIM" : "NAO");
            palavra = MyIO.readLine();
        }

    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    static boolean epalindromo(String str) {

        return epalindromo(str, 0);
    }

    static boolean epalindromo(String str, int i) {
        if (i < (str.length() / 2)) { // CONDICAO DE PARADA

            if (str.charAt(i) == str.charAt(str.length() - i - 1)) {
                return epalindromo(str, ++i); // INCREMENTAR PRIMEIRO O 1
            } else {
                return false; // ovo
            }
        }
        return true;
    }

}
