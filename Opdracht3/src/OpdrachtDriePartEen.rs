use regex::Regex;
use std::fs;

fn main() {
    let input_string = fs::read_to_string("input.txt").expect("failed to read file");
    let filter = Regex::new(r"mul\((\d{1,3}),(\d{1,3})\)").unwrap();

    let mut addition = 0;

    for cap in filter.captures_iter(&input_string) {
        let num1: i32 = cap[1].parse().unwrap();
        let num2: i32 = cap[2].parse().unwrap();
        addition += num1 * num2;
    }
    println!("{}", addition);
}
