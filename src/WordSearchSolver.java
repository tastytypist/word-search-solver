public class WordSearchSolver {

    public static void main(String[] args) {
        System.out.println("Welcome to Word Search Solver!\n");
        WordSearch word_search = new WordSearch();
        word_search.inputWordSearch(args[0]);
        word_search.displayWordSearch();
        word_search.solveWordSearch();
        word_search.displaySolutions();
    }
}
