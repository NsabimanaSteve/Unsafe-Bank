import java.util.Scanner;

/**
 * The UnsafeBanks program determines unsafe banks based on their total assets.
 * A bank is considered unsafe if its total assets (balance + borrowed amounts) fall below a specified limit.
 * The program takes user input for the number of banks, the minimum total assets limit,
 * and the balance and borrower information for each bank.
 * It then identifies and displays the unsafe banks.
 */
public class UnsafeBanks {

    /**
     * Main method to execute the UnsafeBanks program.
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of banks and the minimum total assets limit
        System.out.print("Enter the number of banks and the minimum total assets limit: ");
        int n = scanner.nextInt();
        double limit = scanner.nextDouble();

        // Arrays to store borrower information and balances for each bank
        double[][] borrowers = new double[n][n];
        double[] balances = new double[n];

        // Read bank information and borrower information
        for (int i = 0; i < n; i++) {
            System.out.print("Enter balance and number of borrowers for bank " + i + " (e.g., 181 1 2 125): ");
            balances[i] = scanner.nextDouble();

            int numBorrowers = scanner.nextInt();

            // Prompt for borrower information only if there are borrowers
            if (numBorrowers > 0) {
                //System.out.print("Enter borrower ID and amount borrowed for bank " + i + " (e.g., 1 125): ");
                for (int j = 0; j < numBorrowers; j++) {
                    int borrowerID = scanner.nextInt();
                    double amountBorrowed = scanner.nextDouble();
                    borrowers[i][borrowerID] += amountBorrowed; // Use += to accumulate amounts
                }
            }
        }

        // Find unsafe banks
        boolean[] unsafeBanks = new boolean[n];
        for (int i = 0; i < n; i++) {
            double totalAssets = balances[i];
            for (int j = 0; j < n; j++) {
                // Sum up total assets (balance + borrowed amounts) for each bank
                totalAssets += borrowers[i][j];
            }
            if (totalAssets < limit) {
                // Mark the bank as unsafe
                unsafeBanks[i] = true;
            }
        }

        // Display unsafe banks
        System.out.print("Unsafe banks are: ");
        boolean firstUnsafeBankPrinted = false;
        for (int i = 0; i < n; i++) {
            if (unsafeBanks[i]) {
                // Print unsafe bank IDs separated by spaces
                if (firstUnsafeBankPrinted) {
                    System.out.print(" ");
                }
                System.out.print(i);
                firstUnsafeBankPrinted = true;
            }
        }

        scanner.close();
    }
}
