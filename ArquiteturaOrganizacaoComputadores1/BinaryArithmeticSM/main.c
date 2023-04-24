# include <stdio.h>
# include <string.h>
# include <time.h>
# include "tools.c"
# include "soma.c"
# include "subtracao.c"
# include "divisao.c"
# include "multiplicacao.c"
# include "test.c"

int main(){
    // Inicialização de variáveis
    int decimal1 = 0; // Primeiro decimal da operacao
    int decimal2 = 0; // Segundo decimal da operacao
    int operacao = 0; // Tipo da peracao a ser realizada
    int binario1[TAM_BINARIO] = {0}; // Primeiro decimal da operacao em binario
    int binario2[TAM_BINARIO] = {0}; // Segundo decimal da operacao em binario
    // Variaveis de resultado
    int resultado[TAM_BINARIO] = {0}; // Resultado da operacao em binario
    int resultadoMult[TAM_BINARIO*2] = {0}; // Resultado da opoeracao de multiplicacao em binario
    int resto[TAM_BINARIO] = {0}; // Resto da divisao em binario
    int resposta = 0; // Resultado da operacao em decimal

    // Funcao utilizada para testes
    // return test();

    // Menu de opções
    printf("\nDigite a operacao:");
    printf("\n1 - Adicao");
    printf("\n2 - Subtracao");
    printf("\n3 - Multiplicacao");
    printf("\n4 - Divisao");
    printf("\n>>> ");
    scanf("%d", &operacao);

    printf("\nDigite o primeiro numero decimal: ");
    scanf("%d", &decimal1);
    decimalBinarioVetor(decimal1, binario1, TAM_BINARIO);

    printf("\nDigite o segundo numero decimal: ");
    scanf("%d", &decimal2);
    decimalBinarioVetor(decimal2, binario2, TAM_BINARIO);

    if(operacao == 1){ // Adicao
        // Funcao no arquivo soma.c
        resposta = somaBinario(decimal1, decimal2, resultado);
        printf("\n\nResultado da soma: %d -> ", resposta);
        imprimeBinario(resultado, TAM_BINARIO);
    }
    else if(operacao == 2){ // Subtracao
        // Funcao no arquivo subtracao.c
        resposta = subtracaoBinario(decimal1, decimal2, resultado);
        printf("\n\nResultado da subtracao: %d -> ", resposta);
        imprimeBinario(resultado, TAM_BINARIO);
    }
    else if(operacao == 3){ // Multiplicacao
        // Funcao no arquivo multiplicacao.c
        resposta = multiplicacaoBinario(decimal1, decimal2, resultadoMult);
        printf("\n\nResultado da multiplicacao: %d -> ", resposta);
        imprimeBinario(resultadoMult, TAM_BINARIO*2);
    }
    else if(operacao == 4){ // Divisao
        // Funcao no arquivo divisao.c
        resposta = divisaoBinario(decimal1, decimal2, resultado, resto);
        printf("\n\nResultado da divisao: %d -> ", resposta);
        imprimeBinario(resultado, TAM_BINARIO);
        printf("\nResto da divisao: ");
        imprimeBinario(resto, TAM_BINARIO);
    }
    else{
        printf("\nOperacao invalida");
    }

    printf("\n\nPrograma finalizado :)");

}

