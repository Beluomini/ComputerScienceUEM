# include <stdio.h>
# include <string.h>
# include <time.h>

int somaBinario(int decimal1, int decimal2, int *soma){
    // Inicializacao de variaveis
    int binario1[TAM_BINARIO] = {0};
    int binario2[TAM_BINARIO] = {0};
    int carryVet[TAM_BINARIO] = {0};
    zerarBinario(soma, TAM_BINARIO);
    int resto = 0;
    int posicao = 0;
    int carry = 0;

    // Se os numeros tiverem o mesmo sinal, sera feita uma soma comum mantendo o sinal
    if((decimal1 <= 0 && decimal2 <= 0) || (decimal1 >= 0 && decimal2 >= 0)){
        printf("\nSoma entre valores do mesmo sinal %d e %d (soma e mantem o sinal)\n\n", decimal1, decimal2);
        
        // Converte os numeros decimais para binario
        decimalBinarioVetor(decimal1, binario1, TAM_BINARIO);
        decimalBinarioVetor(decimal2, binario2, TAM_BINARIO);

        // Percorre o vetor dos binarios
        for(int i = 0; i <= TAM_BINARIO-1; i++){
            // Binario Soma = Binario1 + Binario2 + Carry
            soma[i] = carry + binario1[i] + binario2[i];
            if(soma[i] == 2){
                // Se o resultado for 2, o carry sera 1 e o resultado sera 0
                carry = 1;
                soma[i] = 0;
            }else if(soma[i] == 3){
                // Se o resultado for 3, o carry sera 1 e o resultado sera 1
                carry = 1;
                soma[i] = 1;
            }else{
                // Caso contrario, o carry sera 0 e o resultado sera o proprio resultado da soma
                carry = 0;
            }
            // CarryVet é um vetor de carrys ultilizado apenas para printar os carrys
            carryVet[i] = carry;
        }

        // Imprime o processo de soma
        printf(" ");
        imprimeBinario(carryVet, TAM_BINARIO-1);
        printf("  -> Carry\n");
        imprimeBinarioSM(binario1, TAM_BINARIO);
        printf(" -> Binario 1\n + \n");
        imprimeBinarioSM(binario2, TAM_BINARIO);
        printf(" -> Binario 2\n --------------------------------\n");
        imprimeBinarioSM(soma, TAM_BINARIO);

        // Caso o Ultimo carry seja 1 o resultado deu Overflow (nao cabe no numero de bits do binario)
        if(carryVet[TAM_BINARIO-1] == 1){
            printf("\n\nOverflow");
        }

        // Mantem se o sinal magnitude no resultado
        soma[TAM_BINARIO-1] = binario1[TAM_BINARIO-1];

        // Retorna o resultado da soma em decimal
        return binarioVetorDecimal(soma, TAM_BINARIO);

    }else{

        // Se os numeros tiverem sinais diferentes, sera feita uma subtracao comum mantendo o sinal do maior valor
        printf("\nSoma entre valores de sinal diferente %d e %d (subtrai os modulos e mantem o sinal do maior)\n\n", decimal1, decimal2);
        // verifica qual é o maior valor
        if(abs(decimal1) < abs(decimal2)){ // se o segundo valor for maior
            if(decimal2 < 0){
                // Se o segundo valor for negativo, inverte o seu sinal e subtrai do primeiro
                decimal2 = decimal2 * -1;
                return subtracaoBinario(decimal1, decimal2, soma);
            }else{
                // Se o segundo valor for positivo, inverte o sinal do primeiro e subtrai do segundo
                decimal1 = decimal1 * -1;
                return subtracaoBinario(decimal2, decimal1, soma);
            }
        }else{ // se o primeiro valor for o maior
            if(decimal1 < 0){
                // Se o primeiro valor for negativo, inverte o seu sinal e subtrai do segundo
                decimal1 = decimal1 * -1;
                return subtracaoBinario(decimal2, decimal1, soma);
            }else{
                // Se o primeiro valor for positivo, inverte o sinal do segundo e subtrai do primeiro
                decimal2 = decimal2 * -1;
                return subtracaoBinario(decimal1, decimal2, soma);
            }
        }

    }
}