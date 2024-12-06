const fs = require('fs');

const mapInput = fs.readFileSync('Opdracht6/input.txt', 'utf8').trim();
const map = mapInput.split('\n').map(line => line.split(''));

const directions = [
	[-1, 0],
	[0, 1],
	[1, 0],
	[0, -1],
];

let x = 0, y = 0;
for (let i = 0; i < map.length; i++) {
	for (let j = 0; j < map[i].length; j++) {
		if (map[i][j] === '^') {
			x = i;
			y = j;
			break;
		}
	}
}

let direction = 0;
const visited = new Set();
visited.add(`${x},${y}`);

const isOutOfBounds = (nx, ny) => nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length;

while (true) {
	const [dx, dy] = directions[direction];
	const nextX = x + dx;
	const nextY = y + dy;

	if (isOutOfBounds(nextX, nextY)) {
		break;
	}

	if (map[nextX][nextY] === '#') {
		direction = (direction + 1) % 4;
	} else {
		x = nextX;
		y = nextY;
		visited.add(`${x},${y}`);
	}
}
console.log('Visited cells count:', visited.size);
