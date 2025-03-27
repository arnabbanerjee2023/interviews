/**
 * Copyright © 2025 ARNAB BANERJEE. All rights reserved.
 * <p>
 * This program is proprietary and confidential. It is licensed for use only by authorized users.
 * Unauthorized use, copying, distribution, or modification is strictly prohibited and may result
 * in severe civil and criminal penalties.
 * <p>
 * THIS PROGRAM IS PROVIDED 'AS IS' WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR NONINFRINGEMENT.
 * <p>
 * ARNAB BANERJEE DISCLAIMS ALL LIABILITY FOR DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS PROGRAM, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package unique_path_matrix;

public class Main {
    public static int uniquePaths(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];

        // Initialize the first cell
        dp[0][0] = grid[0][0] == 0 ? 1 : 0;

        // Initialize the first row
        for (int i = 1; i < n; i++) {
            if (grid[0][i] == 0 && dp[0][i - 1] != 0) {
                dp[0][i] = 1;
            }
        }

        // Initialize the first column
        for (int i = 1; i < n; i++) {
            if (grid[i][0] == 0 && dp[i - 1][0] != 0) {
                dp[i][0] = 1;
            }
        }

        // Fill up the rest of the dp table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[n - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{0, 0}, {0, 0}};
        System.out.println(uniquePaths(grid1)); // Output: 2

        int[][] grid2 = {{0, 0}, {1, 0}};
        System.out.println(uniquePaths(grid2)); // Output: 1

        int[][] grid3 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePaths(grid3)); // Output: 2

        int[][] grid4 = {
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        System.out.println(uniquePaths(grid4)); // Output: 30
    }
}