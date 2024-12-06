def main
  grid = File.readlines("Opdracht4/input.txt").map(&:chomp)
  result = find_xmas_in_grid(grid)
  puts result
end

def find_xmas_in_grid(grid)
  directions = [
    [0, 1],   
    [0, -1],  
    [1, 0],   
    [-1, 0],  
    [1, 1],   
    [-1, -1], 
    [1, -1],  
    [-1, 1]   
  ]
  
  word = "XMAS"
  count = 0
  rows = grid.length
  cols = grid[0].length
  
  def is_valid?(r, c, rows, cols)
    r >= 0 && r < rows && c >= 0 && c < cols
  end
  
  def search_from(r, c, dr, dc, grid, word)
    word.length.times do |i|
      nr, nc = r + i * dr, c + i * dc
      return false unless is_valid?(nr, nc, grid.length, grid[0].length) && grid[nr][nc] == word[i]
    end
    true
  end
  
  (0...rows).each do |r|
    (0...cols).each do |c|
      directions.each do |dr, dc|
        count += 1 if search_from(r, c, dr, dc, grid, word)
      end
    end
  end
  count
end

main
