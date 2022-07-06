
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;;

class No {
    public classeFilme elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    public No(classeFilme elemento) {
        this(elemento, null, null);
    }

    public No(classeFilme elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Arvore {
	private No raiz; // Raiz da arvore.
    public int cmp = 0;

	public Arvore() {
		raiz = null;
	}

	public void inserir(classeFilme x) throws Exception {
		raiz = inserir(x, raiz);
	}

	private No inserir(classeFilme x, No i) throws Exception {
		if (i == null) {
            i = new No(x);
        } 
        else if (x.getTituloOString().compareTo(i.elemento.getTituloOString()) < 0 ) {
            cmp++;
            i.esq = inserir(x, i.esq);
        } 
        else if (x.getTituloOString().compareTo(i.elemento.getTituloOString()) > 0 ) {
            cmp++;
            i.dir = inserir(x, i.dir);
        } 
        else {
            throw new Exception("Erro ao inserir!");
        }

		return i;
	}

	public void remover(String x) throws Exception {
		raiz = remover(x, raiz);
	}

	private No remover(String x, No i) throws Exception {
		if (i == null) {
            throw new Exception("Erro ao remover!");
        } 
        else if (x.compareTo(i.elemento.getTituloOString()) < 0 ) {
            cmp++;
            i.esq = remover(x, i.esq);
        } 
        else if (x.compareTo(i.elemento.getTituloOString()) > 0 ) {
            cmp++;
            i.dir = remover(x, i.dir);
        } 
        else if (i.dir == null) {
            i = i.esq;
        } 
        else if (i.esq == null) {
            i = i.dir;
        } 
        else {
            i.esq = maiorEsq(i, i.esq);
        }

		return i;
	}

	private No maiorEsq(No i, No j) {

		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

		} else {
			j.dir = maiorEsq(i, j.dir);
        }
        
		return j;
	}

    public boolean pesquisar(String x) {
        System.out.print("=>raiz ");
		return pesquisar(x, raiz);
	}

	private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i == null) {
            System.out.print("NAO");
            resp = false;
        }
        else if (x.compareTo(i.elemento.getTituloOString()) == 0 ) {
            System.out.print("SIM");
            resp = true;
        } 
        else if (x.compareTo(i.elemento.getTituloOString()) < 0 ) {
            cmp++;
            System.out.print("esq ");
            resp = pesquisar(x, i.esq);
        } 
        else {
            cmp++;
            System.out.print("dir ");
            resp = pesquisar(x, i.dir);
        }

        return resp;
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

public class arvoreB {
    public static void main(String[] args) throws Exception {

        String entrada[] = new String[1000];
        int numEntrada = 0;
        MyIO.setCharset("UTF-8");

        Arvore filme = new Arvore();

        while (true) {

            entrada[numEntrada] = MyIO.readLine();

            if (entrada[numEntrada].equals("FIM")) {
                break;
            }
            classeFilme teste = new classeFilme();
            
          
            
             teste.lerTudo(entrada[numEntrada]);
            
             filme.inserir(teste);

        }
        int x;
        x = MyIO.readInt(); // fazendo um for com valor de 25

        for (int i = 0; i < x; i++) {

            entrada[numEntrada] = MyIO.readLine();
            String recebe = entrada[numEntrada].substring(0, 1); // II e IF POREM DEPENDE
            String reserva = ""; // recebe nome novo
            if (recebe.compareTo("I") == 0) {
                reserva = entrada[numEntrada].substring(2);
                classeFilme film = new classeFilme();
                film.lerTudo(reserva);
                filme.inserir(film);
            } else if (recebe.compareTo("R") == 0) {
                reserva = entrada[numEntrada].substring(2);
                filme.remover(reserva);
            }

        }

        String novaEntrada[] = new String[1000];
        int quant = 0;

        while (true) {

            novaEntrada[quant] = MyIO.readLine();

            if (novaEntrada[quant].equals("FIM")) {
                break;
            }
            else{
                System.out.println(novaEntrada[quant]);
                filme.pesquisar(novaEntrada[quant]);   
                System.out.println();
            }
        }

    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

}
