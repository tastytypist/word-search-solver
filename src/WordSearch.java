import java.io.*;
import java.util.*;

public class WordSearch {

    private String[][] letters;
    private String[] words;
    private int row = 0;
    private int col = 0;
    private int word_count = 0;

    public void inputWordSearch(String file_name) {
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

            for (int i = 0; i < this.row; ++i) {
                for (int j = 0; j < this.col; ++j) {
                    this.letters[i][j] = word_search_input.next("[A-Z]");
                }
            }

            word_search_input.nextLine();
            for (int i = 0; i < this.word_count; ++i) {
                this.words[i] = word_search_input.next();
            }
            word_search_input.close();
        } catch (IOException error) {
            System.out.println("File cannot be accessed!");
            error.printStackTrace();
            System.exit(1);
        }
    }

    public void displayWordSearch() {
        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.col; ++j) {
                System.out.printf("%s ", this.letters[i][j]);
                if (i != this.row - 1 && j == this.col - 1) {
                    System.out.println();
                }
            }
        }
        System.out.println("\n");

        for (int i = 0; i < this.word_count; ++i) {
            System.out.println(this.words[i]);
        }
        System.out.println();
    }
}
