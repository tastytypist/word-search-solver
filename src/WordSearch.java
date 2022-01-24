import java.io.*;
import java.util.*;

public class WordSearch {

    private String[][] letters;
    private String[] words;
    private int[][] word_start;
    private Vector<String> direction;
    private int row = 0;
    private int col = 0;
    private int word_count = 0;

    void inputWordSearch(String file_name) {
        String suffix = ".txt";

        if (!file_name.endsWith(suffix)) {
            file_name += suffix;
        }

        try {
            String file_path = new File("../test/" + file_name).getCanonicalPath();
            File file = new File(file_path);
            Scanner file_input = new Scanner(file);

            while (file_input.hasNextLine()) {
                if (this.row == 0) {
                    String word_search_row = file_input.nextLine();
                    String[] array_row = word_search_row.split(" ");
                    this.col = array_row.length;
                }
                this.row += 1;
                String word_search_row = file_input.nextLine();
                if (Objects.equals(word_search_row, "")) {
                    break;
                }
            }

            while (file_input.hasNextLine()) {
                this.word_count += 1;
                file_input.nextLine();
            }
            file_input.close();

            this.letters = new String[this.row][this.col];
            this.words = new String[this.word_count];

            Scanner word_search_input = new Scanner(file);

            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    this.letters[i][j] = word_search_input.next("[A-Z]");
                }
            }

            word_search_input.nextLine();
            for (int i = 0; i < this.word_count; i++) {
                this.words[i] = word_search_input.next();
            }
            word_search_input.close();
        } catch (IOException error) {
            System.out.println("File cannot be accessed!");
            error.printStackTrace();
            System.exit(1);
        }
    }

    void displayWordSearch() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.printf("%s ", this.letters[i][j]);
                if (i != this.row - 1 && j == this.col - 1) {
                    System.out.println();
                }
            }
        }
        System.out.println("\n");

        for (int i = 0; i < this.word_count; i++) {
            System.out.println(this.words[i]);
        }
        System.out.println();
    }

    void solveWordSearch() {
        this.word_start = new int[this.word_count][2];
        this.direction = new Vector<>();
        int w = 0;

        for (String word : this.words) {
            checkWord(w, word);
            w++;
        }
    }

    private void checkWord(int w, String word) {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (Objects.equals(this.letters[i][j], word.substring(0, 1))) {
                    String word_direction = this.findDirection(i, j, word);
                    if (word_direction != null) {
                        this.direction.add(word_direction);
                        this.word_start[w][0] = i;
                        this.word_start[w][1] = j;
                        return;
                    }
                }
            }
        }
    }

    private String findDirection(int row, int column, String word) {
        String direction = null;

        if (this.tryDirection("north", row, column, word)) {
            direction = "north";
        } else if (this.tryDirection("northeast", row, column, word)) {
            direction = "northeast";
        } else if (this.tryDirection("east", row, column, word)) {
            direction = "east";
        } else if (this.tryDirection("southeast", row, column, word)) {
            direction = "southeast";
        } else if (this.tryDirection("south", row, column, word)) {
            direction = "south";
        } else if (this.tryDirection("southwest", row, column, word)) {
            direction = "southwest";
        } else if (this.tryDirection("west", row, column, word)) {
            direction = "west";
        } else if (this.tryDirection("northwest", row, column, word)) {
            direction = "northwest";
        }

        return direction;
    }

    private boolean tryDirection(String direction, int row, int column, String word) {
        boolean correct_direction = false;

        try {
            switch (direction) {
                case "north" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row - i][column], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
                case "northeast" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row - i][column + i], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
                case "east" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row][column + i], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
                case "southeast" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row + i][column + i], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
                case "south" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row + i][column], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
                case "southwest" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row + i][column - i], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
                case "west" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row][column - i], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
                case "northwest" -> {
                    for (int i = 1; i < word.length(); i++) {
                        if (!Objects.equals(this.letters[row - i][column - i], word.substring(i, i + 1))) {
                            break;
                        } else if (i == word.length() - 1) {
                            correct_direction = true;
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException ignored) {}

        return correct_direction;
    }
    
    void displaySolutions() {
        
    }
}
