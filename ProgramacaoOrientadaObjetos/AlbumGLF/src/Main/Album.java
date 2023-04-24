
package Main;

public class Album {
    private int ano;
    private int qtdFigurinhas;
    private int qtdPaginas;


    public Album() {
    }

    public Album(int ano, int qtdFigurinhas, int qtdPaginas) {
        this.ano = ano;
        this.qtdFigurinhas = qtdFigurinhas;
        this.qtdPaginas = qtdPaginas;
    }

    @Override
    public String toString() {
        return "Album{" + "ano=" + ano + ", qtdFigurinhas=" + qtdFigurinhas + ", qtdPaginas=" + qtdPaginas + '}';
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQtdFigurinhas() {
        return qtdFigurinhas;
    }

    public void setQtdFigurinhas(int qtdFigurinhas) {
        this.qtdFigurinhas = qtdFigurinhas;
    }

    public int getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(int qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }
    
}
