
package Main;

public class FigJogador extends Figurinha{    
    
    private String nomeJogador;
    private int numCamisa;
    private int idadeJogador;
    private String timeJogador;       
    private String selecaoJogador;
    private String nacionalidadeJogador;
    private String posicaoJogador;
    private Boolean titular;

    public FigJogador(String nomeJogador, int numCamisa, int idadeJogador, String timeJogador, String selecaoJogador, String nacionalidadeJogador, String posicaoJogador, Boolean titular, int figurinha, int pagina, String tipo) {
        super(figurinha, pagina, tipo);
        this.nomeJogador = nomeJogador;
        this.numCamisa = numCamisa;
        this.idadeJogador = idadeJogador;
        this.timeJogador = timeJogador;
        this.selecaoJogador = selecaoJogador;
        this.nacionalidadeJogador = nacionalidadeJogador;
        this.posicaoJogador = posicaoJogador;
        this.titular = titular;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getNumCamisa() {
        return numCamisa;
    }

    public void setNumCamisa(int numCamisa) {
        this.numCamisa = numCamisa;
    }

    public int getIdadeJogador() {
        return idadeJogador;
    }

    public void setIdadeJogador(int idadeJogador) {
        this.idadeJogador = idadeJogador;
    }

    public String getTimeJogador() {
        return timeJogador;
    }

    public void setTimeJogador(String timeJogador) {
        this.timeJogador = timeJogador;
    }

    public String getSelecaoJogador() {
        return selecaoJogador;
    }

    public void setSelecaoJogador(String selecaoJogador) {
        this.selecaoJogador = selecaoJogador;
    }

    public String getNacionalidadeJogador() {
        return nacionalidadeJogador;
    }

    public void setNacionalidadeJogador(String nacionalidadeJogador) {
        this.nacionalidadeJogador = nacionalidadeJogador;
    }
    
    public String getPosicaoJogador() {
        return posicaoJogador;
    }

    public void setPosicaoJogador(String posicaoJogador) {
        this.posicaoJogador = posicaoJogador;
    }

    public Boolean getTitular() {
        return titular;
    }

    public void setTitular(Boolean titular) {
        this.titular = titular;
    }
    
}
