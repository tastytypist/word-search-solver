import java.util.*;

public class WordSearchSolver {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("Welcome to Word Search Solver!\n");
        while (!exit) {
            WordSearch word_search = new WordSearch();
            word_search.inputWordSearch(args[0]);
            word_search.displayWordSearch();

            System.out.println("Would you like to exit (y/n)?\040");
            char choice = input.next().charAt(0);
            if (choice == 'y') {
                exit = true;
            }
        }
    }
}
