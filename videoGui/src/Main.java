/*
1. Projeto de linguagem
2. Implementação da linguagem
3. Compilação
4. Carregamento
5. Execução
 */

public class Main {
    public static void main(String[] agrs){
        short numPequeno = 3; //16 bits
        int numInteiro = 3; //32 bits
        long numLongo = 3L; //64 bits

        float numDecimal = 4.2f;
        double numDecimalPreciso = 4.2;
        System.out.println(soma(3,5));
        int[] numeros = {1, 2, 3, 4, 5};
        for(int i = 0; i <= 5; i++){
            System.out.println(soma(i, numeros[i]));
        }
    }

    public static int soma(int a, int b){
        return a+b;
    }
}
