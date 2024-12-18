import java.util.ArrayDeque;

/*
1926. Nearest Exit from Entrance in Maze
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 */
public class NearestExit_LC1296 {
    public static void main(String[] args) {
        char[][] maze = {{'+','+','+'}, {'.','.','.'}, {'+','+','+'}};

        nearestExit(maze, new int[]{1, 0});
    }

    public static int nearestExit(char[][] maze, int[] entrance) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        // queue element - [row, column, distance frm starting]
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{entrance[0], entrance[1], 0});

        while (!queue.isEmpty()) {
            int[] queueElement = queue.poll();

            if (!(queueElement[0] == entrance[0] && queueElement[1] == entrance[1]) &&
                    isExit(queueElement, maze)) {
                return queueElement[2];
            }

            if (!visited[queueElement[0]][queueElement[1]]) {
                visited[queueElement[0]][queueElement[1]]= true;

                //up
                if (queueElement[0]-1 >= 0 &&
                        maze[queueElement[0]-1][queueElement[1]] == '.' &&
                        visited[queueElement[0]-1][queueElement[1]] == false) {
                    queue.offer(new int[]{queueElement[0]-1, queueElement[1], queueElement[2]+1});
                }

                //down
                if (queueElement[0]+1 < maze.length &&
                        maze[queueElement[0]+1][queueElement[1]] == '.' &&
                        visited[queueElement[0]+1][queueElement[1]] == false) {
                    queue.offer(new int[]{queueElement[0]+1, queueElement[1], queueElement[2]+1});
                }

                //right
                if (queueElement[1] + 1 < maze[0].length &&
                        maze[queueElement[0]][queueElement[1]+1] == '.' &&
                        visited[queueElement[0]][queueElement[1]+1] == false) {
                    queue.offer(new int[]{queueElement[0], queueElement[1]+1, queueElement[2] + 1});
                }

                //left
                if (queueElement[1] - 1 >= 0 &&
                        maze[queueElement[0]][queueElement[1]-1] == '.' &&
                        visited[queueElement[0]][queueElement[1]-1] == false) {
                    queue.offer(new int[]{queueElement[0], queueElement[1]-1, queueElement[2] + 1});
                }
            }
        }

        return -1;
    }

    private static boolean isExit(int[] queueElement, char[][] maze) {
        return queueElement[0] == 0 || queueElement[0] == maze.length-1 ||
                queueElement[1] == 0 || queueElement[1] == maze[0].length-1;
    }


}
