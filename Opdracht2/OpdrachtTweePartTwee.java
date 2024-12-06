package Opdracht2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class OpdrachtTweePartTwee {
	public static void main(String[] args) {
		ReadFile reader = new ReadFile();
		reader.readFile();

		ArrayList<ArrayList<Integer>> list = reader.getList();
		CheckModificationsPartTwo checker = new CheckModificationsPartTwo();

		int passedTest = 0;

		for (ArrayList<Integer> item : list) {
			if (checker.evaluateList(item) <= 1) {
				passedTest++;
			}
		}

		System.out.println(passedTest);
	}
}

class CheckModificationsPartTwo {
	int evaluateList(ArrayList<Integer> list) {
		if (isValid(list)) {
			return 0; // Already valid
		} else if (canFixWithOneRemoval(list)) {
			return 1; // Fixable by removing one element
		} else {
			return 2; // Needs more than one fix
		}
	}

	boolean isValid(ArrayList<Integer> list) {
		return countRemovalsForMonotonic(list) == 0 && countRemovalsForRange(list) == 0;
	}

	boolean canFixWithOneRemoval(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			ArrayList<Integer> modifiedList = new ArrayList<>(list);
			modifiedList.remove(i);
			if (isValid(modifiedList)) {
				return true;
			}
		}
		return false;
	}

	int countRemovalsForMonotonic(ArrayList<Integer> list) {
		return Math.min(countRemovals(list, true), countRemovals(list, false));
	}

	int countRemovals(ArrayList<Integer> list, boolean increasing) {
		int removals = 0;
		int lastValid = list.get(0);

		for (int i = 1; i < list.size(); i++) {
			int current = list.get(i);
			if ((increasing && current < lastValid) || (!increasing && current > lastValid)) {
				removals++;
			} else {
				lastValid = current;
			}
		}
		return removals;
	}

	int countRemovalsForRange(ArrayList<Integer> list) {
		int removals = 0;
		int lastNumber = list.get(0);

		for (int i = 1; i < list.size(); i++) {
			int current = list.get(i);
			if (difference(lastNumber, current) < 1 || difference(lastNumber, current) > 3) {
				removals++;
			} else {
				lastNumber = current;
			}
		}
		return removals;
	}

	int difference(int num1, int num2) {
		return Math.abs(num1 - num2);
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
