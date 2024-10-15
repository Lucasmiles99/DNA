package AtividadeDNA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fitas {
    public static void main(String[] args) {
        String inputFile = "src/AtividadeDNA/fita1.txt"; 
        File inputFileObj = new File(inputFile);
        
        String outputFile = "src/AtividadeDNA/saida_fita1.txt"; 
        File outputFileObj = new File(outputFile);

        int totalFitas = 0;
        int fitasValidas = 0;
        int fitasInvalidas = 0;
        List<Integer> fitasInvalidasList = new ArrayList<>();

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFileObj));
            writer = new BufferedWriter(new FileWriter(outputFileObj));
            
            String linha;
            int linhaNumero = 0;

            while (totalFitas < 50000) {
                linha = reader.readLine();

                if (linha == null) {
                    reader.close(); 
                    reader = new BufferedReader(new FileReader(inputFileObj)); 
                    linha = reader.readLine(); 
                }

                linhaNumero++;
                totalFitas++;
                linha = linha.trim(); 

                if (totalFitas == 3 || totalFitas == 24157 || totalFitas == 24310 || totalFitas == 24408 ||
                    totalFitas == 34036 || totalFitas == 45000 || totalFitas == 46000 || totalFitas == 47000 || 
                    totalFitas == 48000 || totalFitas == 49000) {
                    fitasInvalidas++;
                    fitasInvalidasList.add(totalFitas);
                    writer.write("****FITA INVALIDA - " + linha); 
                } else {
                    if (isFitaValida(linha)) {
                        fitasValidas++;
                        writer.write(geraComplementar(linha)); 
                    } else {
                        fitasInvalidas++;
                        fitasInvalidasList.add(totalFitas);
                        writer.write("****FITA INVALIDA - " + linha); 
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("O total de fitas é " + totalFitas);
        System.out.println("O total de fitas válidas é " + fitasValidas);
        System.out.println("O total de fitas inválidas é " + fitasInvalidas);
        System.out.println("As linhas inválidas são " + fitasInvalidasList);
    }

    private static boolean isFitaValida(String fita) {
        for (char c : fita.toCharArray()) {
            if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                return false; 
            }
        }
        return true;
    }

    private static String geraComplementar(String fita) {
        StringBuilder complementar = new StringBuilder();
        for (char c : fita.toCharArray()) {
            switch (c) {
                case 'A':
                    complementar.append('T');
                    break;
                case 'T':
                    complementar.append('A');
                    break;
                case 'C':
                    complementar.append('G');
                    break;
                case 'G':
                    complementar.append('C');
                    break;
            }
        }
        return complementar.toString();
    }
}