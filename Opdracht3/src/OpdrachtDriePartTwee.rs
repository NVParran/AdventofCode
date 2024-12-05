use regex::Regex;
use regex_split::RegexSplit;
use std::fs;

fn main() {
    let input_string = fs::read_to_string("input.txt").expect("failed to read file");

    let mul = Regex::new(r"mul\((\d{1,3}),(\d{1,3})\)").unwrap();
    let toggle = Regex::new(r"do\(\)|don't\(\)").unwrap();

    let mut enabled = true;
    let mut addition = 0;

    for part in toggle.split_inclusive_left(&input_string) {
        if toggle.is_match(part) {
            let toggle_command = toggle.find(part).unwrap().as_str();
            enabled = toggle_command == "do()";
        }

        if enabled {
            for cap in mul.captures_iter(part) {
                let num1: i32 = cap[1].parse().unwrap();
                let num2: i32 = cap[2].parse().unwrap();
                addition += num1 * num2;
            }
        }
    }
    println!("{}", addition);
}
