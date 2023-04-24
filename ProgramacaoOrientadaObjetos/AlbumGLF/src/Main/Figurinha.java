
package Main;

public class Figurinha {
    private int figurinha;
    private int pagina;
    private String tipo;

    public Figurinha(int figurinha, int pagina, String tipo) {
        this.figurinha = figurinha;
        this.pagina = pagina;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Figurinha{" + "figurinha=" + figurinha + ", pagina=" + pagina + ", tipo=" + tipo + '}';
    }

    public int getFigurinha() {
        return figurinha;
    }

    public void setFigurinha(int figurinha) {
        this.figurinha = figurinha;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
