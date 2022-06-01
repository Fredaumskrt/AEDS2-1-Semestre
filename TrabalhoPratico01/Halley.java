class Halley {
    public static void main(String[] argv) {
        int ano = MyIO.readInt();
        int vezes = 0;

        if (ano != 0) {
            vezes = (ano - 10) / 76;
            vezes++;
        }
        MyIO.println(vezes * 76 + 10);
    }
}

