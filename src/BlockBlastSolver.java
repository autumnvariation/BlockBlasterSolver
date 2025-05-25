import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BlockBlastSolver {

    private final int [][] state = new int[8][8];
    private final Frame solution = new Frame();
    public BlockBlastSolver() {
        setState();
    }
    private void setState() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 8; j++) {
                if(line.charAt(j) == '1'){
                    state[i][j] = 1;
                }
                else if(line.charAt(j) == '2'){
                    state[i][j] = 2;
                }
                else{
                    state[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockBlastSolver solver = new BlockBlastSolver();
        while(true){
            solver.solve(solver.getBlocks());
        }
    }

    private int[] getBlocks() {

        int[] blocks = new int[3];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            blocks[i] = Integer.parseInt(scanner.nextLine());
        }
        System.out.println();
        return blocks;
    }


    private void solve(int[] blocks) {
        solution.setColors(state);
        ArrayList<int[]> orders = new ArrayList<>();
        orders.add(new int[]{0,1,2});
        orders.add(new int[]{0,2,1});
        orders.add(new int[]{1,0,2});
        orders.add(new int[]{1,2,0});
        orders.add(new int[]{2,0,1});
        orders.add(new int[]{2,1,0});
        //test 1572864 combinations :skull:
        //solution defined by block1 + position, block2 + position, block3 + position
        //ex: 11,2,4, 23,4,6, 2,4,8
        ArrayList<int[]> solutions = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            //this gonna be crazy
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    if (!testBlockFits(state,blocks[orders.get(i)[0]], j, k)){
                        //if fail
                        continue;
                    }

                    for (int l = 0; l < 8; l++) {
                        for (int m = 0; m < 8; m++) {
                            if (!testBlock2(blocks[orders.get(i)[0]], j, k, blocks[orders.get(i)[1]], l, m)){
                                //if fail
                                continue;
                            }

                            for (int n = 0; n < 8; n++) {
                                for (int o = 0; o < 8; o++) {
                                    if (!testBlock3(blocks[orders.get(i)[0]], j, k, blocks[orders.get(i)[1]], l, m, blocks[orders.get(i)[2]], n, o)){
                                        //if fail
                                        continue;
                                    }
                                    solutions.add(new int[]{blocks[orders.get(i)[0]], j,k,blocks[orders.get(i)[1]], l,m,blocks[orders.get(i)[2]], n,o });
                                }
                            }
                        }
                    }
                }
            }


        }

        solution.addSolution(findOptimalSolution(solutions));
    }

    private int[] findOptimalSolution(ArrayList<int[]> solutions) {

        ArrayList<int[]> optimalSolutions = new ArrayList<>();
        //optimal solutions would be
        //1. clears only a single line
        //2. leaves as many tiles possible on the board
        optimalSolutions = removeNonClearing(solutions);
        sortByTiles(optimalSolutions);
        int[] optimalSolution = optimalSolutions.getFirst();
        boardAdd(state, optimalSolution[0], optimalSolution[1], optimalSolution[2]);
        boardClear(state);
        boardAdd(state, optimalSolution[3], optimalSolution[4], optimalSolution[5]);
        boardClear(state);
        boardAdd(state, optimalSolution[6], optimalSolution[7], optimalSolution[8]);
        boardClear(state);
        return optimalSolution;
    }

    private void sortByTiles(ArrayList<int[]> solutions) {
        solutions.sort(Comparator.comparing(this::countTiles));
    }
    private int countTiles(int[]solution){
        int[][] stateCopy = deepCopy(state);
        boardAdd(stateCopy, solution[0], solution[1], solution[2]);
        boardClear(stateCopy);
        boardAdd(stateCopy, solution[3], solution[4], solution[5]);
        boardClear(stateCopy);
        boardAdd(stateCopy, solution[6], solution[7], solution[8]);
        boardClear(stateCopy);
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (stateCopy[i][j] == 1){
                    count++;
                }
            }
        }
        return count;
    }
    private static int[][] deepCopy(int[][] src) {
        int[][] cp = new int[src.length][src[0].length];
        for (int r = 0; r < src.length; r++)
            System.arraycopy(src[r], 0, cp[r], 0, src[r].length);
        return cp;
    }
    private ArrayList<int[]> removeNonClearing(ArrayList<int[]> solutions) {
        ArrayList<int[]> clearingSolutions = new ArrayList<>();
        for (int[] solution : solutions) {
            int[][] stateCopy = deepCopy(state);
            boardAdd(stateCopy, solution[0], solution[1], solution[2]);
            if (boardClear(stateCopy)) {
                clearingSolutions.add(solution);
                continue;
            }
            boardAdd(stateCopy, solution[3], solution[4], solution[5]);
            if (boardClear(stateCopy)) {
                clearingSolutions.add(solution);
                continue;
            }
            boardAdd(stateCopy, solution[6], solution[7], solution[8]);
            if (boardClear(stateCopy)) {
                clearingSolutions.add(solution);
            }
        }
        if (!clearingSolutions.isEmpty()){
            return clearingSolutions;
        }
        return solutions;
    }

    private boolean testBlock3(int block, int j, int k, int block1, int l, int m, int block2, int n, int o) {
        int[][] stateCopy = deepCopy(state);
        boardAdd(stateCopy, block, j, k);
        boardClear(stateCopy);
        boardAdd(stateCopy, block1, l, m);
        boardClear(stateCopy);
        return testBlockFits(stateCopy, block2,n,o);
    }

    private boolean testBlock2(int block, int j, int k, int block1, int l, int m) {
        int[][] stateCopy = deepCopy(state);
        boardAdd(stateCopy, block, j, k);
        boardClear(stateCopy);
        return testBlockFits(stateCopy, block1,l,m);
    }

    private boolean testBlockFits(int[][]state, int block, int j, int k) {
        for (int i = 0; i < Blocks.BLOCKS.get(block).length; i++) {
            for (int l = 0; l < Blocks.BLOCKS.get(block)[0].length; l++) {
                try {
                    if (Blocks.BLOCKS.get(block)[i][l] == 1 && state[j + i][k + l] == 1) {
                        return false;
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    return false;
                }
            }
        }

        return true;
    }
    private void boardAdd(int[][] board, int block, int row, int column){
        for (int i = 0; i < Blocks.BLOCKS.get(block).length; i++) {
            for (int j = 0; j < Blocks.BLOCKS.get(block)[0].length; j++) {
                if (Blocks.BLOCKS.get(block)[i][j] == 1){
                    board[row +i][column + j] = 1;
                }
            }
        }
    }
    private boolean boardClear(int[][] state){
        boolean clear = false;
        final int n = state.length;

        boolean[] fullRow = new boolean[n];
        boolean[] fullCol = new boolean[n];

        for (int i = 0; i < n; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += state[i][j];
                colSum += state[j][i];
            }
            fullRow[i] = rowSum == n;
            fullCol[i] = colSum == n;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fullRow[i] || fullCol[j]) {
                    state[i][j] = 0;
                    clear = true;
                }
            }
        }
        return clear;
    }
}