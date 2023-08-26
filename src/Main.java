import java.io.*;
import java.util.HashMap;

public class Main {

    public static final String SEPARADOR ="\\s+";   //Para separar las palabras por espacio :)
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("src/divina_comedia_sp.txt"));
        HashMap<Integer, Integer> histograma = new HashMap<>();
        String line;
        try {
            while((line = br.readLine()) != null){
                String [] palabras = line.split(SEPARADOR);
                for(String palabra : palabras){
                    palabra = palabra.replaceAll("[^a-zA-Z]", "" ); //Quitar caracteres que no son del alfabeto
                    if(palabra.length() >=2 && palabra.length() <= 10 ){    //if que delimita la longitud de las palabras a considerar de 2 hasta 10 caracteres
                    int length = palabra.length();
                    histograma.put(length, histograma.getOrDefault(length, 0) + 1);
                    }
                }
            } br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Histograma de longitud de palabras en la divina comedia:");
        for (int length = 2; length <= 10; length++) {
            int contador = histograma.getOrDefault(length, 0);
            System.out.printf("%d: %s%n", length, "[]".repeat(contador));   //imprime el histograma
            System.out.println(contador +" palabras de " + length + " letras");
        }
    }
}

