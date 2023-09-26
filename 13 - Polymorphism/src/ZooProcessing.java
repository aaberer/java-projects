import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ZooProcessing {
    ArrayList<Flora> floraList = new ArrayList<>();
    ArrayList<Fauna> faunaList = new ArrayList<>();

    public void processFile(String fileName) {
        try {            
            Scanner scnr = new Scanner(new File(fileName));
            while(scnr.hasNextLine()) {
                String organismLine = scnr.nextLine();
                
                addOrganism(organismLine);
            }

            scnr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void interact() {
        System.out.println("CSV File has been loaded, Flora and Fauna lists have been populated!");
        printSelections();
        Scanner scnr = new Scanner(System.in);

        String input = scnr.nextLine();
        while(!input.equalsIgnoreCase("X")) {
            if(input.equalsIgnoreCase("D")) {
                randFact();
            }
            else if(input.equalsIgnoreCase("L")) {
                listCounts();
            }
            else {
                System.out.println("Unrecognized command.");
            }

            printSelections();
            input = scnr.nextLine();
        }

        scnr.close();
    }

    /** Self-Explanation
     * addOrganism method will append an object after calling process file to get contents of csv
     * then can get the values between commas then find the category that organism belongs to
     * depending on category add it to corresponding list
     */
    public void addOrganism(String organismLine) {
        //TODO: Student
        String[] split = organismLine.split(",");
        String organismIndex = split[0];

        if (organismIndex.equals("Flora")){
            String organismType = split[1];
            if(organismType.equals("Angiosperm")){
                Angiosperm angiosperm = new Angiosperm(organismLine);
                floraList.add(angiosperm);
            } else if(organismType.equals("Fern")){
                Fern fern = new Fern(organismLine);
                floraList.add(fern);
            } else if(organismType.equals("Gymnosperm")){
                Gymnosperm gymnosperm = new Gymnosperm(organismLine);
                floraList.add(gymnosperm);
            } else if(organismType.equals("Moss")){
                Moss moss = new Moss(organismLine);
                floraList.add(moss);
            }
        } else if (organismIndex.equals("Fauna")) {
            String organismType = split[1];
            if(organismType.equals("Amphibian")){
                Amphibian amphibian = new Amphibian(organismLine);
                faunaList.add(amphibian);
            } else if(organismType.equals("Bird")){
                Bird bird = new Bird(organismLine);
                faunaList.add(bird);
            } else if(organismType.equals("Fish")){
                Fish fish = new Fish(organismLine);
                faunaList.add(fish);
            } else if(organismType.equals("Invertebrate")){
                Invertebrate invertebrate = new Invertebrate(organismLine);
                faunaList.add(invertebrate);
            } else if(organismType.equals("Mammal")){
                Mammal mammal = new Mammal(organismLine);
                faunaList.add(mammal);
            } else if(organismType.equals("Reptile")){
                Reptile reptile = new Reptile(organismLine);
                faunaList.add(reptile);
            }

        }
    }
    
    public void randFact() {
        Random rand = new Random();
        if(rand.nextInt(1) == 0) {
            Fauna randFauna = faunaList.get(rand.nextInt(faunaList.size()-1));
            System.out.println(randFauna.species + " fact: " + randFauna.fact);
        }
        else {
            Flora randFlora = floraList.get(rand.nextInt(floraList.size()-1));
            System.out.println(randFlora.species + " fact: " + randFlora.fact);
        }
    }
    
    /** Self-Explanation
     * displays the count for the flora and fauna arraylists and total the number of
     * organisms in each category.
     * 
     */
    public void listCounts() {
        //TODO: Student
        int angiospermCount = 0;
        int fernCount = 0;
        int gymnospermCount = 0;
        int mossCount = 0;
        int amphibianCount = 0;
        int birdCount = 0;
        int fishCount = 0;
        int invertebrateCount = 0;
        int mammalCount = 0;
        int reptileCount = 0;

        int floraCount = 0;
        int faunaCount = 0;

        for (Flora flora : floraList){
            if (flora instanceof Angiosperm){
                angiospermCount++;
                floraCount++;
            } else if(flora instanceof Fern){
                fernCount++;
                floraCount++;
            } else if(flora instanceof Gymnosperm){
                gymnospermCount++;
                floraCount++;
            } else if(flora instanceof Moss){
                mossCount++;
                floraCount++;
            }
        }

        for (Fauna fauna : faunaList){
            if (fauna instanceof Amphibian){
                amphibianCount++;
                faunaCount++;
            } else if(fauna instanceof Bird){
                birdCount++;
                faunaCount++;
            } else if(fauna instanceof Fish){
                fishCount++;
                faunaCount++;
            } else if(fauna instanceof Invertebrate){
                invertebrateCount++;
                faunaCount++;
            } else if(fauna instanceof Mammal){
                mammalCount++;
                faunaCount++;
            } else if(fauna instanceof Reptile){
                reptileCount++;
                faunaCount++;
            }
        }

        System.out.println("Flora Count: " + floraCount);
        System.out.println("Angiosperm count: " + angiospermCount);
        System.out.println("Fern count: " + fernCount);
        System.out.println("Gymnosperm count: " + gymnospermCount);
        System.out.println("Moss count: " + mossCount);
        System.out.println("");
        System.out.println("Fauna Count: " + faunaCount);
        System.out.println("Amphibian count: " + amphibianCount);
        System.out.println("Bird count: " + birdCount);
        System.out.println("Fish count: " + fishCount);
        System.out.println("Invertebrate count: " + invertebrateCount);
        System.out.println("Mammal count: " + mammalCount);
        System.out.println("Reptile count: " + reptileCount);
    }
    
    public void printSelections() {
        System.out.println("What would you like to do?");
        System.out.println("[D]isplay a random fact?");
        System.out.println("[L]ist current organism type counts?");
        System.out.println("[X] to exit out of the Zoo DB?");
    }
}
