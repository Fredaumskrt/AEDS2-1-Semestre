import java.util.*;

class No {
    public int elemento; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.

    public No(int elemento) {
        this(elemento, null, null);
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    private No raiz; // Raiz da arvore.

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);

        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);

        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public void caminharCentral() {
        System.out.print("In..: ");
        caminharCentral(raiz);
        System.out.println();
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
    }

    public void caminharPre() {
        System.out.print("Pre.: ");
        caminharPre(raiz);
        System.out.println();
    }

    private void caminharPre(No i) {
        if (i != null) {
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
    }

    public void caminharPos() {
        System.out.print("Post: ");
        caminharPos(raiz);
        System.out.println();
    }

    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
        }
    }
}

public class busca {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        int entrada = Integer.parseInt(ler.nextLine().trim());
        
        for (int i = 0; i < entrada; i++) {
            ArvoreBinaria test = new ArvoreBinaria();
            String qtd = ler.nextLine();

            String dados = ler.nextLine();
            String[] array = dados.split(" ");

            for (int j = 0; j < array.length; j++) {
                test.inserir(Integer.parseInt(array[j])); // inserindo elementos na arvore
            }
            MyIO.println("Case " + (i + 1) + ":");
            test.caminharPre();
            test.caminharCentral();
            test.caminharPos();
            System.out.println();
        }

    }

}
