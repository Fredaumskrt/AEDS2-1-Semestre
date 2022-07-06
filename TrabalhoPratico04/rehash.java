
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;;

class Hash {
    classeFilme tabela[];
    int m1, m2, m, reserva;
    // final int NULO = -1;

    public Hash(int m) {
        this.m = m;
        this.tabela = new classeFilme[this.m];
        for (int i = 0; i < m; i++) {
            tabela[i] = null;
        }
        reserva = 0;
    }

    public int reh(String elemento) {
        int soma = 0;
        for (char c : elemento.toCharArray()) {
            soma += c;
        }
        return soma % m;
    }

    public int h(String elemento) {
        int soma = 0;
        for (char c : elemento.toCharArray()) {
            soma += c;
        }
        return soma % m;
    }

    public boolean inserir(classeFilme elemento) {
        boolean resp = false;
        if (elemento != null) {
            int pos = h(elemento.getTituloOString());
            if (tabela[pos] == null) {
                tabela[pos] = elemento;
                resp = true;
            } else {
                pos = reh(elemento.getTituloOString());
                if (tabela[pos] == null) {
                    tabela[pos] = elemento;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public boolean pesquisar(String elemento) {
        boolean resp = false;
        int pos = h(elemento);
        if (tabela[pos] != null) {
            if (tabela[pos].getTituloOString().compareTo(elemento) == 0) {
                System.out.println("Posicao: " + pos);
                resp = true;
            } else {
                pos = reh(elemento);
                if (tabela[pos] != null) {
                    if (tabela[pos].getTituloOString().compareTo(elemento) == 0) {
                        System.out.println("Posicao: " + pos);
                        resp = true;
                    }
                }
            }
        }

        return resp;
    }
}

class classeFilme {
    private static final String pastaclasseFilmeString = "tmp/filmes/";

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

public class rehash {
    public static void main(String[] args) throws Exception {

        String entrada[] = new String[1000];
        int numEntrada = 0;
        MyIO.setCharset("UTF-8");

        Hash filme = new Hash(23);

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
            }
            System.out.println("=> " + novaEntrada[quant]);
            if (!filme.pesquisar(novaEntrada[quant])) {
                System.out.println("NAO");

            }
        }
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

}
