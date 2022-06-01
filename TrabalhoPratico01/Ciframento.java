class Ciframento {
    public static void main(String[] args) {
        // MyIO.setCharset("UTF-8");
        String[] entrada = new String[1000];
        int numEntrada = 0;

        do {
            entrada[numEntrada] = MyIO.readLine();
        
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;
        
        for (int i = 0; i < numEntrada; i++) {
            MyIO.println(encriptar(entrada[i]));
        }

    }

    // char ch = entrada.charAt(i);
    public static String encriptar(String entrada) {

        String cifrador = "";
        entrada.length();
        int chave = 3;
        for (int i = 0; i < entrada.length(); i++) {
            cifrador += (char) (entrada.charAt(i)  + chave);
        
        }
        return cifrador;
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' &&
                s.charAt(2) == 'M');
    
          }

}
