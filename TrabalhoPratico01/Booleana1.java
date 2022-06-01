public class Booleana1 {

    public static void main(String[] args) {

        String entrada = "3 0 0 0 and(or(A , B) , not(and(B , C)))";

        int n = entrada.charAt(0) - 48;

        int[] entry_values = get_expression_values(entrada, n);

        String boolean_expression = "";

        for (int i = (n * 2) + 2; i < entrada.length(); i++)
            boolean_expression += entrada.charAt(i);

        String resultado = "";

        boolean resolvido = true;

        while (!resolvido) {
            int i = 0;
            if (and(boolean_expression, i)) {
                if (does_not_contain_expression(s, i + 3)) {
                    i += 4;

                    int[] valores_expressao; // -> and(A,B,C)

                    boolean resultado_and = and(valores_expressao);

                    if (resultado_and == true) {
                        resultado += '1';
                    } else
                        resultado += '0';
                }
            } else if (or(boolean_expression, i)) {
                if (does_not_contain_expression(s, i + 2)) {
                    i += 3;

                    int[] valores_expressao; // -> or(A,B,C)

                    boolean resultado_or = or(valores_expressao);

                    if (resultado_or == true) {
                        resultado += '1';
                    } else
                        resultado += '0';
                }
            } else if (not(boolean_expression, i)) {
                if (does_not_contain_expression(s, i + 3)) {
                    i += 4;

                    

                    boolean expressao = entry_values[valor];
                    expressao = !expressao;
                    if (resultado_not == true) {
                        resultado += '1';
                    } else
                        resultado += '0';
                }
            } else {
                resultado += entrada.charAt(i);
            }

            if (resultado.length() == 1) {
                resolvido = true;
            } else {
                boolean_expression = resolvido;
                resolvido = "";
            }

        }

    }

    public static boolean and(String s, int i) {
        return s.charAt(i) == 'a' && s.charAt(i + 1) == 'n' && s.charAt(i + 2) == 'd';
    }

    public static boolean not(String s, int i) {
        return s.charAt(i) == 'n' && s.charAt(i + 1) == 'o' && s.charAt(i + 2) == 't';
    }

    public static boolean or(String s, int i) {
        return s.charAt(i) == 'o' && s.charAt(i + 1) == 'r';
    }

    /**
     * Verificar se existe expressoes dentro de expressoes
     */
    public static boolean does_not_contain_expression(String s, int i) {
        boolean result = true;

        while (s.charAt(i) != ')') {
            if (and(s, i) || not(s, i) || or(s, i)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public static int[] recover_entry_values(String s, int n) {
        int[] valores = new int[n];

        for (int i = 0, j = 2; i < n; i++, j += 2) {
            valores[i] = entrada.charAt(j) - 48;
        }

        return valores;
    }

    public static boolean isFim(String s) {
        return (s.length() == 0);
    }
}