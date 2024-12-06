const fs = require('fs');
const map = fs.readFileSync('Opdracht6/input.txt', 'utf8').trim().split('\n');

const DIRECTIONS = [[-1, 0], [0, 1], [1, 0], [0, -1]];

function simulate(map, startRow, startCol) {
	const rows = map.length;
	const cols = map[0].length;
	const visited = new Set();
	let row = startRow;
	let col = startCol;
	let dir = 0;

	while (true) {
		const state = `${row},${col},${dir}`;
		if (visited.has(state)) return true;
		visited.add(state);

		const [dx, dy] = DIRECTIONS[dir];
		const nextRow = row + dx;
		const nextCol = col + dy;

		if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) return false;

		if (map[nextRow][nextCol] === '#') {
			dir = (dir + 1) % 4;
		} else {
			row = nextRow;
			col = nextCol;
		}
	}
}

function countWallPlacements(map, startRow, startCol) {
	let count = 0;

	for (let r = 0; r < map.length; r++) {
		for (let c = 0; c < map[r].length; c++) {
			if (map[r][c] === '.') {
				map[r] = map[r].slice(0, c) + '#' + map[r].slice(c + 1);

				if (simulate(map, startRow, startCol)) count++;

				map[r] = map[r].slice(0, c) + '.' + map[r].slice(c + 1);
			}
		}
	}

	return count;
}

let startRow, startCol;
for (let r = 0; r < map.length; r++) {
	const col = map[r].indexOf('^');
	if (col !== -1) {
		startRow = r;
		startCol = col;
		map[r] = map[r].replace('^', '.');
	}
}
console.log(countWallPlacements(map, startRow, startCol));
