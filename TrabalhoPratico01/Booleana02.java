public class Booleana02 {

    public static boolean isFim(String s) {
        return (s.length() == 0);
    }

    public static boolean getExp(String expressao, String valor1, String valor2, String resultado) {

        boolean r = false;
        if (expressao.contains("+")) {
            if (valor1.equals(valor2) && valor1.equals("0")) {
                r = (resultado.equals("0") ? true : false);
            } else if (valor1.equals(valor2) && valor1.equals("1")) {
                r = (resultado.equals("1") ? true : false);
            } else {
                r = (resultado.equals("1") ? true : false);
            }
        } else if (expressao.contains(".")) {
            if (valor1.equals(valor2) && valor1.equals("0")) {
                r = (resultado.equals("0") ? true : false);
            } else if (valor1.equals(valor2) && valor1.equals("1")) {
                r = (resultado.equals("1") ? true : false);
            } else {
                r = (resultado.equals("0") ? true : false);
            }
        }

        return r;
    }
    public static boolean contain_expression(String expressao, String valor1, String valor2, String resultado){
                
        if (getExp(expressao, valor1, valor2, resultado)) {
            MyIO.println(expressao + " é verdadeiro");
        } else {
            MyIO.println(expressao + " é falso");
        return true;
        }
        
        return false;
    }
    public static void main(String[] args) {

        int numEntrada = 0;
        String[] entrada = new String[1000];
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for (int i = 0; i < numEntrada; i++) {
            
        }

    }

}