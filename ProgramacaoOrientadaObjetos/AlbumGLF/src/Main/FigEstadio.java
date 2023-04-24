
package Main;

public class FigEstadio extends Figurinha{    
    
    private String localCidade;
    private String localEstado;
    private String timeDono;       
    private int lotacaoMaxima;

    public FigEstadio(String localCidade, String localEstado, String timeDono, int lotacaoMaxima, int figurinha, int pagina, String tipo) {
        super(figurinha, pagina, tipo);
        this.localCidade = localCidade;
        this.localEstado = localEstado;
        this.timeDono = timeDono;
        this.lotacaoMaxima = lotacaoMaxima;
    }

    public String getLocalCidade() {
        return localCidade;
    }

    public void setLocalCidade(String localCidade) {
        this.localCidade = localCidade;
    }

    public String getLocalEstado() {
        return localEstado;
    }

    public void setLocalEstado(String localEstado) {
        this.localEstado = localEstado;
    }

    public String getTimeDono() {
        return timeDono;
    }

    public void setTimeDono(String timeDono) {
        this.timeDono = timeDono;
    }

    public int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    public void setLotacaoMaxima(int lotacaoMaxima) {
        this.lotacaoMaxima = lotacaoMaxima;
    }
    
}
