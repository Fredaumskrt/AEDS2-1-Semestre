
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;;

class NoAN {
    public boolean cor;
    public classeFilme elemento;
    public NoAN esq, dir;

    public NoAN() {
        // this(-1);
    }

    public NoAN(classeFilme elemento) {
        this(elemento, false, null, null);
    }

    public NoAN(classeFilme elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN(classeFilme elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Alvinegra {
    private NoAN raiz; // Raiz da arvore.

    /**
     * Construtor da classe.
     */
    public Alvinegra() {
        raiz = null;
    }

    public boolean pesquisar(String elemento) {
        // System.out.print("raiz" + " ");
        // return pesquisar(x, raiz);
        System.out.println(elemento);
        System.out.print("raiz" + " ");
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(String elemento, NoAN i) {
        boolean resp;
        if (i == null) {
            System.out.print("NAO\n");
            resp = false;
        } else if (elemento.compareTo(i.elemento.getTituloOString()) == 0) {
            System.out.print("SIM\n");
            resp = true;
        } else if (elemento.compareTo(i.elemento.getTituloOString()) < 0) {
            // cmp++;
            System.out.print("esq ");
            resp = pesquisar(elemento, i.esq);
        } else {
            // cmp++;
            System.out.print("dir ");
            resp = pesquisar(elemento, i.dir);
        }

        return resp;
    }

    private void inserir(classeFilme elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            if (elemento.getTituloOString().compareTo(pai.elemento.getTituloOString()) < pai.elemento.getTituloOString()
                    .compareTo(elemento.getTituloOString())) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getTituloOString().compareTo(i.elemento.getTituloOString()) < i.elemento.getTituloOString()
                    .compareTo(elemento.getTituloOString())) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getTituloOString().compareTo(i.elemento.getTituloOString()) > i.elemento
                    .getTituloOString().compareTo(elemento.getTituloOString())) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }

    }

    public void inserir(classeFilme elemento) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(elemento);
            // System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento +
            // ").");

            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            if (elemento.getTituloOString().compareTo(raiz.elemento.getTituloOString()) < raiz.elemento
                    .getTituloOString().compareTo(elemento.getTituloOString())) {
                raiz.esq = new NoAN(elemento);
                // System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e
                // esq(" + raiz.esq.elemento + ").");
            } else {
                raiz.dir = new NoAN(elemento);
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (elemento.getTituloOString().compareTo(raiz.elemento.getTituloOString()) < raiz.elemento
                    .getTituloOString().compareTo(elemento.getTituloOString())) {
                raiz.esq = new NoAN(elemento);
                // System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else if (elemento.getTituloOString().compareTo(raiz.elemento.getTituloOString()) < raiz.elemento
                    .getTituloOString().compareTo(elemento.getTituloOString())) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                // System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
                // System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (elemento.getTituloOString().compareTo(raiz.elemento.getTituloOString()) > raiz.elemento
                    .getTituloOString().compareTo(elemento.getTituloOString())) {
                raiz.dir = new NoAN(elemento);
                // System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else if (elemento.getTituloOString().compareTo(raiz.elemento.getTituloOString()) > raiz.elemento
                    .getTituloOString().compareTo(elemento.getTituloOString())) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                // System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");

            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
                // System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento +
                // "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.getTituloOString().compareTo(avo.elemento.getTituloOString()) > avo.elemento
                    .getTituloOString().compareTo(pai.elemento.getTituloOString())) { // rotacao a esquerda ou
                                                                                      // direita-esquerda
                if (i.elemento.getTituloOString().compareTo(pai.elemento.getTituloOString()) > pai.elemento
                        .getTituloOString().compareTo(i.elemento.getTituloOString())) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getTituloOString().compareTo(pai.elemento.getTituloOString()) < pai.elemento
                        .getTituloOString().compareTo(i.elemento.getTituloOString())) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getTituloOString().compareTo(bisavo.elemento.getTituloOString()) < bisavo.elemento
                    .getTituloOString().compareTo(avo.elemento.getTituloOString())) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
            // System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e
            // avo.esq / avo.dir("
            // + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
        } // if(pai.cor == true)
    }

    private NoAN rotacaoDir(NoAN no) {
        
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        // System.out.println("Rotacao ESQ(" + no.elemento + ")");
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

class classeFilme {
    private static final String pastaclasseFilmeString = "/tmp/filmes/";

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String nome;
    private String tituloOriginal;
    private Date dataLancamento;
    private int Duracao;
    private String genero;
    private String idiomaOriginal;
    private String situacao;
    private String palavrasChave[];

    private float orcamento;
    private ArrayList<String> palavras_chave;

    public classeFilme() {
        this.nome = "";
        tituloOriginal = "";
        Duracao = 0;
        genero = "";
        idiomaOriginal = "";
        situacao = "";
        orcamento = 0.0f;
        palavras_chave = new ArrayList<String>();

    }

    public classeFilme(String nome, String tituloOriginal, Date dataLancamento, int Duracao, String genero,
            String idiomaOriginal, String situacao, float orcamento, ArrayList<String> palavras_chave) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.tituloOriginal = tituloOriginal;
        this.Duracao = Duracao;
        this.genero = genero;
        this.idiomaOriginal = idiomaOriginal;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.palavras_chave = palavras_chave;

    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public void setdataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getTituloOString() {
        return tituloOriginal;
    }

    public void settituloOriginal(String tituloString) {
        this.tituloOriginal = tituloString;
    }

    public Date getDataLancamento() {
        if (this.dataLancamento == null) {
            return new Date();
        }
        return this.dataLancamento;
    }

    public int getDuracao() {
        return Duracao;
    }

    public void setDuracao(int Duracao) {
        this.Duracao = Duracao;
    }

    public String getgenero() {
        return genero;
    }

    public void setgenero(String genero) {
        this.genero = genero;
    }

    public String getidiomaOriginal() {
        return idiomaOriginal;
    }

    public void setidiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getsituacao() {
        return situacao;
    }

    public void setsituacao(String situacao) {
        this.situacao = situacao;
    }

    public ArrayList<String> getPalavrasChave() { // <> operador diamante
        return palavras_chave;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public float getOrcamento() {
        return this.orcamento;
    }

    public void setorcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public void imprimir() {

        System.out.println(getnome() + " " + getTituloOString() + " " + sdf.format(getDataLancamento()) + " "
                + getDuracao() + " " +
                getgenero() + " " + getidiomaOriginal() + " " + getsituacao() + " " + getOrcamento() + " "
                + "[" + palavras_chave() + "]");
    }

    public classeFilme clone() {

        classeFilme clone = new classeFilme();

        clone.nome = this.nome;
        clone.tituloOriginal = this.tituloOriginal;
        clone.dataLancamento = this.dataLancamento;
        clone.Duracao = this.Duracao;
        clone.genero = this.genero;
        clone.idiomaOriginal = this.idiomaOriginal;
        clone.situacao = this.situacao;
        clone.orcamento = this.orcamento;
        clone.palavras_chave = this.palavras_chave;

        return clone;
    }

    // chamar metodo vazia
    public String removeTags(String original) {
        String remover = "";
        for (int i = 0; i < original.length(); i++) { // tags de abertuda e fechamento <>

            if (original.charAt(i) == '<') {
                while (original.charAt(i) != '>')
                    i++;
            } else {
                remover += original.charAt(i);
            }
        }
        return remover;
    }

    public String palavras_chave() {
        String vazia = "";

        if (this.palavras_chave.size() == 0) {
            return vazia;
        }

        for (String s : this.palavras_chave) { // s = proprio elemento do array

            vazia += s + ", ";
        }
        vazia = vazia.substring(0, vazia.length() - 2);
        return vazia;
    }

    public String buscaParenteses(String original) {
        String removeParenteses = "";

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) != '(') {
                removeParenteses += original.charAt(i);
            }
        }

        return removeParenteses;
    }

    public void lerlancamento(String classeFilme) throws Exception {

        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("span class=\"release\"")) {
            linha = br.readLine();
        }
        linha = br.readLine().trim();
        linha = linha.substring(0, 10);
        Date result = new SimpleDateFormat("dd/MM/yyyy").parse(linha);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(removeTags(buscaParenteses(linha)));
            this.setdataLancamento(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        br.close();

    }

    public void lerGenero(String classeFilme) throws Exception {

        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();
        this.genero = "";
        while (!linha.contains("\"genres\"")) {
            linha = br.readLine();
        }
        linha = br.readLine();
        linha = br.readLine();
        for (String genero : removeTags(linha).trim().split("&nbsp;")) {
            this.genero += genero;
        }
        br.close();
    }

    public void lerduracao(String classeFilme) throws Exception {

        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("\"runtime\"")) {
            linha = br.readLine();
        }
        linha = br.readLine();
        linha = br.readLine().trim();
        if (linha.contains("h")) {
            if (linha.contains("m"))
                this.Duracao = Integer.parseInt(linha.split(" ")[1].replace("m", ""));
            this.Duracao += Integer.parseInt(linha.split("h")[0]) * 60;
        } else {
            this.Duracao = Integer.parseInt(linha.replace("m", ""));
        }
        br.close();
    }

    public void lerTituloeSituacao(String classeFilme) throws Exception {

        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("Título original") || !linha.contains("Situação")) {
            if (linha.contains("Título original")) {
                this.tituloOriginal = removeTags(linha).split("original")[1].trim(); // PRINTAR POSICAO [1] DO TITULO
                                                                                     // ORIGINAL
            } else if (linha.contains("Situação") && linha.contains("strong")) { // se conter
                this.situacao = removeTags(linha).trim().split(" ")[1];

                break;
            }
            linha = br.readLine();
        }
        if (this.tituloOriginal.isEmpty()) {
            this.tituloOriginal = nome;
        }
        br.close();
    }

    public void lerIdioma(String classeFilme) throws Exception {

        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("Idioma original")) {
            linha = br.readLine();
        }
        this.idiomaOriginal = removeTags(linha).split("original")[1].trim();
        br.close();
    }

    public void lerPalavras(String classeFilme) throws Exception {
        // criada para abrir o arquivo tmp, onde contem os classeFilme
        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();
        this.palavras_chave.clear();
        while (!linha.contains("<section class=\"keywords right_column\">")) {
            linha = br.readLine();
        }
        while (!linha.contains("</ul>")) {

            if (linha.contains("<li>"))
                this.palavras_chave.add(removeTags(linha).trim());
            linha = br.readLine();
            if (linha.contains("Nenhuma palavra-chave")) {
                linha = br.readLine();
                break;
            }
        }
        br.close();
    }

    public void lerTudo(String filme) throws Exception {

        this.lerArquivoNome(filme);
        this.lerTituloeSituacao(filme);
        this.lerduracao(filme);
        this.lerIdioma(filme);
        this.lerOrcamento(filme);
        this.lerPalavras(filme);
        this.lerlancamento(filme);
        this.lerIdioma(filme);
        this.lerGenero(filme);
    }

    public void lerOrcamento(String classeFilme) throws Exception {
        // criada para abrir o arquivo tmp, onde contem os classeFilme
        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);
        String linha = br.readLine();

        while (!linha.contains("Orçamento")) {
            linha = br.readLine();
        }
        if (!linha.contains("-")) {
            String[] orcamentoString = removeTags(linha).trim().split(" ")[1].split("\\.");
            orcamentoString[0] = orcamentoString[0].replace(",", "").replace("$", "");
            this.orcamento = Float.parseFloat(orcamentoString[0]);
            this.orcamento += Float.parseFloat(orcamentoString[1]) * Math.pow(10, -orcamentoString[1].length());
        }
        br.close();
    }

    public void lerArquivoNome(String classeFilme) throws Exception {

        // criada para abrir o arquivo tmp, onde contem os classeFilme
        FileReader arq = new FileReader(pastaclasseFilmeString + classeFilme);
        BufferedReader br = new BufferedReader(arq);
        String linha = br.readLine();

        while (!linha.contains("h2 class")) {
            linha = br.readLine();
        }
        linha = br.readLine().trim();
        setnome(removeTags(linha));
        br.close();
    }
}

public class arvoreAlvinegra {
    public static void main(String[] args) throws Exception {

        String entrada[] = new String[1000];
        int numEntrada = 0;
        MyIO.setCharset("UTF-8");

        Alvinegra filme = new Alvinegra();

        while (true) {

            entrada[numEntrada] = MyIO.readLine();

            if (entrada[numEntrada].equals("FIM")) {
                break;
            }

            classeFilme teste = new classeFilme();
            teste.lerTudo(entrada[numEntrada]);
            filme.inserir(teste);

        }

        String novaEntrada[] = new String[1000];
        int quant = 0;

        while (true) {
            novaEntrada[quant] = MyIO.readLine();
            if (novaEntrada[quant].equals("FIM")) {
                break;
            } else {
                filme.pesquisar(novaEntrada[quant]);
            }
        }

    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

}
