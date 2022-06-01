import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;

// import java.util.regex.Matcher;
class Fila {
    private ClasseFilme[] array;
    private int primeiro;
    private int ultimo;
    // public int count;

    public Fila(int tamanho) {
        array = new ClasseFilme[tamanho + 1];
        primeiro = ultimo = 0;
        // count = 0;
    }

    public void inserir(ClasseFilme x) throws Exception {

        // validar insercao
        if (((ultimo + 1) % array.length) == primeiro) {
            remover();
        }

        array[ultimo] = x;
        ultimo = (ultimo + 1) % array.length;
        // count++;
    }

    public ClasseFilme remover() throws Exception {

        // validar remocao
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover!");
        }

        ClasseFilme resp = array[primeiro];
        primeiro = (primeiro + 1) % array.length;
        // count--;
        return resp;
    }

    public void mostrar() {

        for (int i = primeiro; i != ultimo; i = ((i + 1) % array.length)) {
            System.out.print("[" + ((ultimo - primeiro) == 1 ? "0" : i) + "] ");
            array[i].imprimir();
        }

    }

    public int calculaMedia() {

        double media = 0;
        int count = 0;

        for (int i = primeiro; i != ultimo; i = ((i + 1) % array.length)) {
            media += array[i].getDuracao();
            count++;
        }

        return (int) Math.round(media / count);
    }
}

class ClasseFilme {
    private static final String pastafilmesString = "/tmp/filmes/";

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

    public ClasseFilme() {

        nome = "";
        tituloOriginal = "";
        Duracao = 0;
        genero = "";
        idiomaOriginal = "";
        situacao = "";
        orcamento = 0.0f;
        palavras_chave = new ArrayList<String>();

    }

    public ClasseFilme(String nome, String tituloOriginal, Date dataLancamento, int Duracao, String genero,
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

    public int posPrimeiro(String s, int pos) {

        for (int i = pos; i < s.length(); i++) {

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

                return i;
            }
        }

        return -1;
    }

    public int posPrimeiro(String s) {

        return posPrimeiro(s, 0);
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

    public void lerlancamento(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("class=\"release\"")) {
            linha = br.readLine();
        }
        linha = br.readLine().trim();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(removeTags(buscaParenteses(linha)));
            this.setdataLancamento(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        br.close();

    }

    public void lerGenero(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
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

    public void lerduracao(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
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

    public void lerTituloeSituacao(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
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

    public void lerIdioma(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("Idioma original")) {
            linha = br.readLine();
        }
        this.idiomaOriginal = removeTags(linha).split("original")[1].trim();
        br.close();
    }

    public void lerPalavras(String filmes) throws Exception {
        // criada para abrir o arquivo tmp, onde contem os filmes
        FileReader arq = new FileReader(pastafilmesString + filmes);
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

    public void lerOrcamento(String filmes) throws Exception {
        // criada para abrir o arquivo tmp, onde contem os filmes
        FileReader arq = new FileReader(pastafilmesString + filmes);
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

    public void lerArquivoNome(String filmes) throws Exception {

        // criada para abrir o arquivo tmp, onde contem os filmes
        FileReader arq = new FileReader(pastafilmesString + filmes);
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

public class filaSequencial {
    public static void main(String[] args) throws Exception {
        
        MyIO.setCharset("utf-8");
        Fila film = new Fila(5);

        String[] entrada = new String[1000];
        int numEntrada = 0;

        while (true) {

            entrada[numEntrada] = MyIO.readLine();

            if (entrada[numEntrada].equals("FIM")) {
                break;
            }
            ClasseFilme movie = new ClasseFilme();
            movie.lerTudo(entrada[numEntrada]);


            film.inserir(movie);
            MyIO.println(film.calculaMedia());
        }
       

        String receb = "";
        

        int n = MyIO.readInt();
        for (int i = 0; i < n; i++) {

            receb = MyIO.readLine();
            String reserva = receb.substring(0, 1);
            String test = "";
            if (reserva.compareTo("I") == 0) {

                test = receb.substring(2);
            } else if (reserva.compareTo("R") == 0) {}

            if (reserva.equals("I")) {

                ClasseFilme movieN = new ClasseFilme();
                movieN.lerTudo(test);
                film.inserir(movieN);
                MyIO.println(film.calculaMedia());
            } else if (reserva.compareTo("R") == 0) {

                MyIO.println("(R)" + " " + film.remover().getnome());
            }
        }

        film.mostrar();
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' &&
                s.charAt(2) == 'M');
    }

}
