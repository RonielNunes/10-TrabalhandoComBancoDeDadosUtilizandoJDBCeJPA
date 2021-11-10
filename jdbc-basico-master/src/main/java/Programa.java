import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        List<Integer> idadeHomens = new ArrayList<>();
        List<Integer> idadeMulheres = new ArrayList<>();
        char sexo;
        Integer idade;
        int tamnho = 0;
        Scanner sc = new Scanner(System.in);
        boolean verifica = true;

        while (verifica){
            System.out.println("Digite o sexo: ");
            sexo = sc.next().charAt(0);
            if(sexo == '0'){
                System.out.println("Entrou");
                verifica = false;
            }else{
                System.out.println("Digite a idade: ");
                idade = sc.nextInt();
                if (sexo == 'm'){
                    idadeHomens.add(idade);
                }else if(sexo == 'f'){
                    idadeMulheres.add(idade);
                }
            }

        }
        tamnho = idadeHomens.size() + idadeMulheres.size();
        System.out.println("Quantas pessoas preencheram a pesquisa: " + tamnho);
        System.out.println("Idade do homem mais novo" + idadeMaisNovo(idadeHomens));
        System.out.println("Idade da mulher mais nova" + idadeMaisNovo(idadeMulheres));
        System.out.println("Idade do homem mais velho " + idadeMaisVelho(idadeHomens));
        System.out.println("Idade da mulher mais velha: " + idadeMaisVelho(idadeMulheres));
        System.out.println("Média de idade dos homens " + idadeMedia(idadeHomens));
        System.out.println("Média de idade das mulheres " + idadeMedia(idadeMulheres));
        System.out.println("Média de idade geral: " + idadeMedia(idadeHomens,idadeMulheres) );
        sc.close();
    }
    public static int idadeMaisNovo(List<Integer> lista){
        int menor = lista.get(0);
        for (Integer x:lista){
            if(x < menor){
                menor = x;
            }
        }
        return menor;
    }

    public static int idadeMaisVelho(List<Integer> lista){
        int maior = lista.get(0);
        for (Integer x:lista){
            if(x > maior){
                maior = x;
            }
        }
        return maior;
    }

    public static float idadeMedia(List<Integer> lista){
        int soma = 0;
        for (Integer x:lista){
            soma +=x;
        }
        return soma/lista.size();
    }
    public static float idadeMedia(List<Integer> listaH, List<Integer> listaM){
        int soma = 0;
        int n = listaH.size() + listaM.size();
        for (Integer x:listaH){
            soma +=x;
        }
        for (Integer x:listaM){
            soma +=x;
        }
        return soma/n;
    }
}
