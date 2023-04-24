
package Main;

public class FigBrasao extends Figurinha{    
    
    private int qtdCopas;    
    private String selecaoBrasao;

    public FigBrasao(int qtdCopas, String selecaoBrasao, int figurinha, int pagina, String tipo) {
        super(figurinha, pagina, tipo);
        this.qtdCopas = qtdCopas;
        this.selecaoBrasao = selecaoBrasao;
    }

    public int getRaridade() {
        return qtdCopas;
    }

    public void setRaridade(int qtdCopas) {
        this.qtdCopas = qtdCopas;
    }

    public String getSelecaoBrasao() {
        return selecaoBrasao;
    }

    public void setSelecaoBrasao(String selecaoBrasao) {
        this.selecaoBrasao = selecaoBrasao;
    }

}
