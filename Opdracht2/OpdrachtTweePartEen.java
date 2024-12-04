package Opdracht2;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

class OpdrachtTweePartEen {
    public static void main(String[] args) {
        ReadFile reader = new ReadFile();
        reader.readFile();
        ArrayList<ArrayList<Integer>> list = reader.getList();
        CheckModifications modichk = new CheckModifications();


    }
}

class CheckModifications {

    boolean checkFluctuations(ArrayList<Integer> inputList) {
        ArrayList<Integer> list = new ArrayList<>(inputList);
        ArrayList<Boolean> goingUp = new ArrayList<>();
        int lastNumber = 0;
        boolean firstRound = true;

        if (list.isEmpty()) {
            return false;
        }

        while (!list.isEmpty()) {
            if (firstRound) {
                lastNumber = list.removeFirst();
                firstRound = false;
            } else {
                if (lastNumber < list.getFirst()) {
                    goingUp.add(true);
                } else {
                    goingUp.add(false);
                }
                lastNumber = list.removeFirst();
            }
        }
        return goingUp.stream().allMatch(e -> e.equals(goingUp.get(0)));
    }

    Boolean CheckIncrease(ArrayList<Integer> inputList) {
        ArrayList<Integer> list = new ArrayList<>(inputList);
        
    }

}


class ReadFile {
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    void printList() {
        System.out.println(list);
    }

    ArrayList<ArrayList<Integer>> getList() {
        return list;
    }

    void readFile() {
        try {
            File myObj = new File("Opdracht2/input.txt");
            Scanner myReader = new Scanner(myObj);
            int firstIndex = 0;
            while (myReader.hasNextLine()) {
                list.add(new ArrayList<>());
                String data = myReader.nextLine();
                String[] splitStr = data.split("\\s+");
                for (int secondIndex = 0; secondIndex < splitStr.length; secondIndex++) {
                    list.get(firstIndex).add(secondIndex, Integer.valueOf(splitStr[secondIndex]));
                }
                firstIndex++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
