def main
  grid = File.readlines("Opdracht4/input.txt").map(&:strip)
  puts find_x_patterns(grid) 
end

def find_x_patterns(grid)
  rows = grid.size
  cols = grid[0].size
  count = 0

  (1...rows - 1).each do |r|
    (1...cols - 1).each do |c|
      count += 1 if valid_x_pattern?(grid, r, c)
    end
  end
  
  count
end

def valid_x_pattern?(grid, r, c)
  return false unless grid[r][c] == 'A'

  top_left = grid[r - 1][c - 1]
  top_right = grid[r - 1][c + 1]
  bottom_left = grid[r + 1][c - 1]
  bottom_right = grid[r + 1][c + 1]

  valid_diagonal?(top_left, bottom_right) && valid_diagonal?(top_right, bottom_left)
end

def valid_diagonal?(first, third)
  (first == 'M' && third == 'S') || (first == 'S' && third == 'M')
end

main
