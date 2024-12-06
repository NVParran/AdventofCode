package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Rule struct {
	x, y int
}

func ParseRules(lines []string) []Rule {
	var rules []Rule
	for _, line := range lines {
		parts := strings.Split(line, "|")
		x, _ := strconv.Atoi(parts[0])
		y, _ := strconv.Atoi(parts[1])
		rules = append(rules, Rule{x, y})
	}
	return rules
}

func ParseSequences(lines []string) [][]int {
	var sequences [][]int
	for _, line := range lines {
		parts := strings.Split(line, ",")
		var sequence []int
		for _, p := range parts {
			num, _ := strconv.Atoi(p)
			sequence = append(sequence, num)
		}
		sequences = append(sequences, sequence)
	}
	return sequences
}

func IsValidSequence(sequence []int, rules []Rule) bool {
	indexMap := make(map[int]int)
	for i, num := range sequence {
		indexMap[num] = i
	}

	for _, rule := range rules {
		xIndex, xExists := indexMap[rule.x]
		yIndex, yExists := indexMap[rule.y]

		if xExists && yExists && xIndex > yIndex {
			return false
		}
	}

	return true
}

func FindMiddleNumber(sequence []int) int {
	mid := len(sequence) / 2
	return sequence[mid]
}

func ReadInputFile(filename string) ([]string, []string, error) {
	file, err := os.Open(filename)
	if err != nil {
		return nil, nil, err
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	var rules []string
	var sequences []string
	isSequenceSection := false

	for scanner.Scan() {
		line := scanner.Text()
		if line == "" {
			isSequenceSection = true
			continue
		}

		if isSequenceSection {
			sequences = append(sequences, line)
		} else {
			rules = append(rules, line)
		}
	}

	return rules, sequences, scanner.Err()
}

func main() {
	filename := "Opdracht5/input.txt"

	ruleLines, sequenceLines, err := ReadInputFile(filename)
	if err != nil {
		fmt.Println("Error reading file:", err)
		return
	}

	ruleSet := ParseRules(ruleLines)
	sequenceList := ParseSequences(sequenceLines)

	total := 0
	for _, sequence := range sequenceList {
		if IsValidSequence(sequence, ruleSet) {
			total += FindMiddleNumber(sequence)
		}
	}

	fmt.Println(total)
}
