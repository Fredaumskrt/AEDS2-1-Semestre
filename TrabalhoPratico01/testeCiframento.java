class testeCiframento {
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static char encriptar(char charOriginal, int chave) {
        char charCifrado;
        if (charOriginal >= 97 && charOriginal <= 122) {
            charCifrado = (char) ((charOriginal - 97 + chave) % 26 + 97);
        }
        else if (charOriginal >= 65 && charOriginal <= 90) {
            charCifrado = (char) ((charOriginal - 65 + chave) % 26 + 65);
        }
        else if (charOriginal >= 192 && charOriginal <= 256){
            charCifrado = (char) ((charOriginal - 192 + chave) % 65 + 192);
        }
        else {
            charCifrado = charOriginal;
        }
 
        return charCifrado;
    }

    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");// definir charset

        String palavra = new String();
        int chave = 3;
        // String cifrada = new String();

        do {
            String cifrada = new String();
            palavra = MyIO.readLine();
            for (int i = 0; i < palavra.length(); i++) {
                cifrada += encriptar(palavra.charAt(i), chave);
            }

            MyIO.println("" + cifrada);
        } while (!isFim(palavra));
    }
}