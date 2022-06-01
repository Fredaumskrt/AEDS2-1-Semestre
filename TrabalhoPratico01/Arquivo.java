import java.io.RandomAccessFile;

public class Arquivo {
    public static void main(String[] args) throws Exception {
        int qtdDoubles = digitarArquivo("NumerosInteiros.txt", "rw"); 
        lerContrario("NumerosInteiros.txt", "r", qtdDoubles);
    }
    
    public static int digitarArquivo(String arquivo, String teste) throws Exception {
        RandomAccessFile rafEscrita = new RandomAccessFile(arquivo, teste);
        int entrada = MyIO.readInt();

        rafEscrita.writeInt(entrada);

        for (int i = 0; i < entrada; i++) {

            rafEscrita.writeDouble(MyIO.readDouble()); // enquanto nao acabar linhas, vai escrevendo no novo arquivo
        }
        rafEscrita.close();
        return entrada;
    }

    public static void lerContrario(String arquivo, String teste2, int x) throws Exception {
        int qtd = x;
        int multiplicador = 8;

        RandomAccessFile rafLeitura = new RandomAccessFile(arquivo, teste2);
        do {
            rafLeitura.seek((long) (4 + --qtd * multiplicador)); // reposiciona o ponteiro de leitura para a posicao
                                                                 // inicial (0)
            MyIO.println(rafLeitura.readDouble()); // ler valor double
        } while (rafLeitura.getFilePointer() - multiplicador >= 12); 
        rafLeitura.close();
    }
}