public class IS {

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for (int i = 0; i < numEntrada; i++) {
            MyIO.print(somenteVogais(entrada[i], 0) ? "SIM " : "NAO ");
            MyIO.print(somenteConsoantes(entrada[i], 0) ? "SIM " : "NAO ");
            MyIO.print(somenteInteiros(entrada[i], 0) ? "SIM " : "NAO ");
            MyIO.println(somenteReal(entrada[i], 0, 0) ? "SIM " : "NAO");
            // MyIO.println("");
        }

    }

    public static char converterPraMaiusculo(char c) {
        if (c >= 97 && c <= 122) {
            return c = (char) (c - 32);
        }
        return c;
    }

    public static boolean ehVogal(char c) {
        char tmp = converterPraMaiusculo(c);
        return tmp == 'A' || tmp == 'E'
                || tmp == 'I' || tmp == 'O'
                || tmp == 'U';
    }

    public static boolean somenteVogais(String s, int i) {
        if (i != s.length()) {
            if (!ehVogal(s.charAt(i)))
                return false;
            return somenteVogais(s, i + 1);
        }
        return true;
    }

    public static boolean somenteConsoantes(String s, int i) {

        if (i != s.length()) {
            ehInteiro(s, i);
            if (ehVogal(s.charAt(i)) && (!ehNumero(s.charAt(i))))
                return false;
            return somenteConsoantes(s, i + 1);
        }
        return true;
    }

    public static boolean ehNumero(char c) {
        return c >= 48 && c <= 57;
    }

    public static boolean ehInteiro(String s, int i) {
        if (i != s.length()) {
            if (ehNumero(s.charAt(i)))
                return somenteInteiros(s, i + 1);
            return false;
        }
        return true;

    }

    public static boolean somenteInteiros(String s, int i) {
        if (i != s.length()) {
            if (s.charAt(i) >= 48 && s.charAt(i) <= 57)
                return somenteInteiros(s, i + 1);
            return false;
        }
        return true;
    }

    public static boolean somenteReal(String s, int i, int contador) {
        if (i != s.length()) {
            if (s.charAt(i) == 44 || s.charAt(i) == 46) {
                if (contador + 1 >= 2) {
                    return false;
                }
                return somenteReal(s, i + 1, contador + 1);
            } else if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                return somenteReal(s, i + 1, contador);
            }

            return false;
        }
        return true;

    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' &&
                s.charAt(2) == 'M');
    }
}
