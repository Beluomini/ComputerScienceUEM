/*
Autor: Lucas Beluomini
Endereçamento Hash
Funções: 
*/
#include "tools.c"

int main(){
    int opcao = 0;
    int tamanho = 0;
    int tamanhoHash = 0;

    do{
        printf("\nPrograma de alocacao Hash --------------------");
        printf("\n0 - Sair");
        printf("\n1 - Alocacao Hash com Enderecamento Aberto");
        printf("\n2 - Alocacao Hash com Lista Encadeada");
        printf("\nOpcao --> ");

        scanf("%d", &opcao);

        if(opcao==1){
            printf("\nAlocacao Hash com Enderecamento Aberto");
            printf("\nDigite o numero de caracteres: ");
            scanf("%d", &tamanho);
            char *palavra[tamanho];
            char *palavraHash[tamanho];
            printf("\nDigite a palavra: ");
            scanf("%s", &palavra);
            enderecamentoAberto(palavra, palavraHash, tamanho);
            imprimirStr(palavraHash, tamanho);
            break;
        
        }else if(opcao==2){
            printf("\nAlocacao Hash com Lista Encadeada");
            printf("\nem producao...");
            // printf("\nDigite o numero de caracteres: ");
            // scanf("%d", &tamanho);
            // printf("\nDigite o tamanho do vetor hash: ");
            // scanf("%d", &tamanhoHash);
            // char *palavra[tamanho];
            // TipoLista *palavraHash[tamanhoHash];
            // printf("\nDigite a palavra: ");
            // scanf("%s", &palavra);
            // listaEncadeada(palavra, palavraHash, tamanho, tamanhoHash);
            // imprimirListaEncadeada(palavraHash, tamanhoHash);
            break;
        }else{
            printf("\nOpcao invalida escolha outro valor\n");
            break;
        }

    }while(opcao != 0);

    return 0;
}