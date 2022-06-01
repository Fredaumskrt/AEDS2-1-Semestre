import java.time.*;

class Celula {
    //Atributos
    public int elemento;
    public Celula inf, sup, esq, dir;

    
    public Celula() {
        this(0, null, null, null, null);
    }
    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }
    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

class matriz {
    //Atributos
    private Celula inicio;
    private int linha, coluna;

 
    public matriz() {
        this(3, 3);
    }
    public matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        this.inicio = new Celula();

     
        Celula a = this.inicio;
        for (int b = 1; b < this.linha; b++) {
            a.inf = new Celula();
            a.inf.sup = a;
            a = a.inf;
        }
        a = null;

     
        for (int b = 0; b < this.linha; b++) {
     
            a = this.inicio;
            for (int c = 0; c < b; c++) {
                a = a.inf;
            }

            //Criando as celulas a direita
            for (int d = 1; d < this.coluna; d++) {
                a.dir = new Celula();
                a.dir.esq = a;
                a = a.dir;
            }
        }
        a = null;

        //Conectando a celula de cima com a celula de baixo
        if (this.linha > 1) {
            a = this.inicio.inf;
            for (int b = 1; b < this.linha; b++) {
                Celula c = a.sup; //Ponteiro superior
                Celula d = a;     //Ponteiro inferior

                //Caminhando na matriz e conectando a celula de cima com a celula de baixo
                while (c != null && d != null) {
                    c.inf = d;
                    d.sup = c;

                    //c++ e d++
                    c = c.dir;
                    d = d.dir;
                }

                a = a.inf;
            }
        }

    }
    public matriz soma(matriz m) throws Exception {
        matriz resp = null;

        if (this.linha != m.linha || this.coluna != m.coluna) {
            throw new Exception("Erro: Nao foi possivel somar!\n As matrizes tem tamanhos diferentes.\n");
        }

        resp = new matriz(m.linha, m.coluna);
        Celula a = resp.inicio;
        for (int i = 0; i < this.linha; i++) {
            Celula b = a;
            for (int j = 0; j < this.coluna; j++) {
                b.elemento = somar(this, m, j, i);

                b = b.dir;
            }
            a = a.inf;
        }

        return resp;
    }
    private int somar(matriz a, matriz b, int x, int y) {
        int soma;
        Celula c = a.inicio;
        Celula d = b.inicio;

        //Caminhando no eixo x
        for (int i = 0; i < x; i++) {
            c = c.dir;
            d = d.dir;
        }

        //Caminhando no eixo y
        for (int i = 0; i < y; i++) {
            c = c.inf;
            d = d.inf;
        }

        //Somando
        soma = c.elemento + d.elemento;

        return soma;

    }
    public matriz multiplicacao(matriz m) throws Exception {
        matriz resp = null;

        if (this.linha != m.linha || this.coluna != m.coluna) {
            throw new Exception("Erro: Nao foi possivel multiplicar!\n As matrizes tem tamanhos diferentes.\n");
        }

        resp = new matriz(m.linha, m.coluna);
        Celula a = resp.inicio;
        int i = 0;
        while (a != null && i < this.linha) {
            Celula b = a;
            int j = 0;
            while (b != null && j < this.coluna) {
                b.elemento = multiplicar(this, m, i, j);
                b = b.dir;
                j++;
            }
            a = a.inf;
            i++;
        }

        return resp;
    }
    private int multiplicar(matriz a, matriz b, int x, int y) {
        int multiplicacao = 0;
        Celula c = a.inicio;
        Celula d = b.inicio;

        
        for (int i = 0; i < x; i++) {
            c = c.inf;
        }

        
        for (int i = 0; i < y; i++) {
            d = d.dir;
        }

        
        int e, f;
        while (c != null && d != null) {
            e = c.elemento;
            f = d.elemento;
            multiplicacao += e * f;
            c = c.dir;
            d = d.inf;
        }

        return multiplicacao;
    }
    public boolean isQuadrada() {
        return (this.linha == this.coluna);
    }
    public void mostrarDiagonalPrincipal() {
        if (isQuadrada()) {
            Celula i = inicio;
            while (i != null) {
                MyIO.print(i.elemento + " ");

                i = i.dir;
                if (i != null) {
                    i = i.inf;
                }
            }
            MyIO.println("");

        }
    }
    public void mostrarDiagonalSecundaria() {
        if (isQuadrada()) {
            Celula i;

           
            for (i = inicio; i.dir != null; i = i.dir) ;

            
            while (i != null) {
                MyIO.print(i.elemento + " ");
                i = i.inf;
                if (i != null) {
                    i = i.esq;
                }
            }
            MyIO.println("");
        }
    }
    public void mostrar() {
        for (Celula i = inicio; i != null; i = i.inf) {
            for (Celula j = i; j != null; j = j.dir) {
                MyIO.print(j.elemento + " ");
            }
            MyIO.println("");
        }
    }
    public void lematriz() {
        for (Celula a = this.inicio; a != null; a = a.inf) {
            for (Celula b = a; b != null; b = b.dir) {
                b.elemento = MyIO.readInt();
            }
        }
    }

}

public class Matriz {
    
    public static void main(String[] args) throws Exception {
        int numeroOperacoes = MyIO.readInt();
        for (int i = 0; i < numeroOperacoes; i++) {
            
            int linhas = MyIO.readInt();
            int colunas = MyIO.readInt();
            matriz matriz1 = new matriz(linhas, colunas);
            matriz1.lematriz();

            
            linhas = MyIO.readInt();
            colunas = MyIO.readInt();
            matriz matriz2 = new matriz(linhas, colunas);
            matriz2.lematriz();

            
            matriz soma = matriz1.soma(matriz2);

            
            matriz multiplicacao = matriz1.multiplicacao(matriz2);

            
            matriz1.mostrarDiagonalPrincipal();
            matriz1.mostrarDiagonalSecundaria();
            soma.mostrar();
            multiplicacao.mostrar();
        }
    }
}