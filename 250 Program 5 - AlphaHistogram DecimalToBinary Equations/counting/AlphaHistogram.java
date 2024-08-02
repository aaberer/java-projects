package cs250.ec.counting;

import java.util.*;

public class AlphaHistogram {
    public static void main(String[] args){
        if (args.length != 1){
            System.out.println("java cs250.ec.counting.AlphaHistogram \"<sentence>\"");
            System.exit(-1);
        }
        System.out.println(histogram(args[0]));
    }

    private static String histogram(String str){
        int[] alphabet = new int[26];
        String strLower = str.toLowerCase();
        for (int i = 0; i < strLower.length(); ++i){
            char c = strLower.charAt(i);
            if (Character.isLetter(c)){++alphabet[c - 'a'];}
        }
        List<String> histogramOutput = new ArrayList<>();
        for (int i = 0; i < alphabet.length; ++i){
            if (alphabet[i] > 0){histogramOutput.add((char) (i + 'a') + ": " + alphabet[i]);}
        }
        for (int i = 0; i < histogramOutput.size() - 1; ++i){
            for (int j = 0; j < histogramOutput.size() - i - 1; ++j){ // 2nd iteration
                int countA = Integer.parseInt(histogramOutput.get(j).split(": ")[1]);
                int countB = Integer.parseInt(histogramOutput.get(j + 1).split(": ")[1]);
                if (countA < countB || (countA == countB && histogramOutput.get(j).compareTo(histogramOutput.get(j + 1)) > 0)){
                    String temp = histogramOutput.get(j); // temp swap
                    histogramOutput.set(j, histogramOutput.get(j + 1)); 
                    histogramOutput.set(j + 1, temp);
                }
            }
        }
        return String.join("\n", histogramOutput);
    }
}
