package Opdracht2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class OpdrachtTweePartEen {
    public static void main(String[] args) {
        ReadFilePartOne reader = new ReadFilePartOne();
        reader.readFile();

        ArrayList<ArrayList<Integer>> list = reader.getList();
        CheckModificationsPartOne checker = new CheckModificationsPartOne();

        int checksPassed = 0;

        for (ArrayList<Integer> item : list) {
            if (checker.checkFluctuations(item) && checker.CheckIncrease(item)) {
                checksPassed++;
            }
        }

        System.out.println(checksPassed);
    }
}

class CheckModificationsPartOne {

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
                lastNumber = list.getFirst();
                firstRound = false;
            } else if (lastNumber < list.getFirst()) {
                goingUp.add(true);
            } else {
                goingUp.add(false);

            }
            lastNumber = list.removeFirst();
        }
        return goingUp.stream().allMatch(e -> e.equals(goingUp.get(0)));
    }

    Boolean CheckIncrease(ArrayList<Integer> inputList) {
        ArrayList<Integer> list = new ArrayList<>(inputList);
        int lastNumber = 0;
        boolean firstRound = true;

        if (list.isEmpty()) {
            return false;
        }
        while (!list.isEmpty()) {
            if (firstRound) {
                lastNumber = list.getFirst();
                firstRound = false;
            } else if (difference(lastNumber, list.getFirst()) >= 1 &&
                    difference(lastNumber, list.getFirst()) <= 3) {
            } else {
                return false;
            }
            lastNumber = list.removeFirst();
        }
        return true;
    }

    int difference(int num1, int num2) {
        return Math.abs(num1 - num2);
    }
}

class ReadFilePartOne {
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
