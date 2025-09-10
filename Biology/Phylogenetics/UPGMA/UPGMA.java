import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Map<String, Double>> distanceMatrix = new HashMap<>();
    private static Map<String, TreeNode> treeNodes = new HashMap<>();
    private static boolean showMatrices = false;
    private static boolean useUPGMA = true; // if false, WPGMA
    private static Map<String, Integer> clusterSizes = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("What do you have?");
    System.out.println("1. DNA sequence");
    System.out.println("2. Distance matrix");
    System.out.println("3. Binary presence/absence (0/1) characters");
    System.out.print("Enter your choice (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

    System.out.print("Use UPGMA (size-weighted) or WPGMA (unweighted)? Enter 'u' or 'w': ");
    String methodAns = scanner.nextLine().trim().toLowerCase();
    useUPGMA = !methodAns.startsWith("w");

    System.out.print("Show distance matrix after each clustering step? (y/n): ");
    String showAns = scanner.nextLine().trim().toLowerCase();
    showMatrices = showAns.startsWith("y");

        System.out.print("Enter the number of species: ");
        int numOfSpecies = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        String[] speciesNames = new String[numOfSpecies];

        // Prompt user to input the name of each species
        for (int i = 0; i < numOfSpecies; i++) {
            System.out.print("Enter name for species " + (i + 1) + ": ");
            speciesNames[i] = scanner.nextLine();
        }

    if (choice == 1) { // User chose DNA sequence
            List<String> sequences = new ArrayList<>();
            for (int i = 0; i < numOfSpecies; i++) {
                System.out.print("Enter sequence for " + speciesNames[i] + ": ");
                sequences.add(scanner.nextLine());
            }
            distanceMatrix.clear();
            treeNodes.clear();
            clusterSizes.clear();
            populateDistanceMatrix(speciesNames, sequences);
            if (showMatrices) {
                System.out.println("Initial distance matrix:");
                printDistanceMatrix();
            }
    } else if (choice == 2) { // User chose Distance matrix
            // Initialize maps and tree nodes first
            distanceMatrix.clear();
            treeNodes.clear();
            clusterSizes.clear();
            for (int i = 0; i < numOfSpecies; i++) {
                distanceMatrix.put(speciesNames[i], new HashMap<>());
                treeNodes.put(speciesNames[i], new TreeNode(speciesNames[i]));
                clusterSizes.put(speciesNames[i], 1);
            }

            // Only ask for distances where j > i (upper triangle) and set diagonals to 0
            for (int i = 0; i < numOfSpecies; i++) {
                for (int j = i; j < numOfSpecies; j++) {
                    if (i == j) {
                        distanceMatrix.get(speciesNames[i]).put(speciesNames[j], 0.0); // self-distance
                    } else {
                        System.out.print("Enter distance between " + speciesNames[i] + " and " + speciesNames[j] + ": ");
                        double distance = scanner.nextDouble();
                        // Store symmetrically
                        distanceMatrix.get(speciesNames[i]).put(speciesNames[j], distance);
                        distanceMatrix.get(speciesNames[j]).put(speciesNames[i], distance);
                    }
                }
            }
            scanner.nextLine(); // consume leftover newline once after matrix input
            if (showMatrices) {
                System.out.println("Initial distance matrix:");
                printDistanceMatrix();
            }
        } else if (choice == 3) { // Binary presence/absence matrix
            List<String> binaryVectors = new ArrayList<>();
            System.out.print("Enter number of characters (columns) in the binary matrix: ");
            int numChars = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < numOfSpecies; i++) {
                while (true) {
                    System.out.print("Enter 0/1 pattern (length " + numChars + ") for " + speciesNames[i] + ": ");
                    String pattern = scanner.nextLine().trim();
                    if (pattern.length() == numChars && pattern.matches("[01]+")) {
                        binaryVectors.add(pattern);
                        break;
                    } else {
                        System.out.println("Invalid pattern. Ensure only 0/1 and correct length.");
                    }
                }
            }
            distanceMatrix.clear();
            treeNodes.clear();
            clusterSizes.clear();
            populateDistanceMatrixBinary(speciesNames, binaryVectors);
            if (showMatrices) {
                System.out.println("Initial distance matrix:");
                printDistanceMatrix();
            }
        }

        while (distanceMatrix.size() > 1) {
            String[] minPair = findMinPair();
            updateDistanceMatrix(minPair[0], minPair[1]);
        }

        printTree(treeNodes.values().iterator().next(), "", true);
    // /todo: Re-audit UPGMA clustering logic (average distance calc, tie handling)
    }


    private static String[] findMinPair() {
        String[] pair = new String[2];
        double minDistance = Double.MAX_VALUE;

        for (String species1 : distanceMatrix.keySet()) {
            for (String species2 : distanceMatrix.get(species1).keySet()) {
                if (!species1.equals(species2) && distanceMatrix.get(species1).get(species2) < minDistance) {
                    minDistance = distanceMatrix.get(species1).get(species2);
                    pair[0] = species1;
                    pair[1] = species2;
                }
            }
        }

        return pair;
    }

    private static void updateDistanceMatrix(String species1, String species2) {
        String newCluster = "(" + species1 + "," + species2 + ")";
    Map<String, Double> newDistances = new HashMap<>();
    int size1 = clusterSizes.getOrDefault(species1, 1);
    int size2 = clusterSizes.getOrDefault(species2, 1);

        TreeNode newNode = new TreeNode(newCluster);
        newNode.left = treeNodes.get(species1);
        newNode.right = treeNodes.get(species2);

        treeNodes.put(newCluster, newNode);
        treeNodes.remove(species1);
        treeNodes.remove(species2);

        for (String otherSpecies : distanceMatrix.keySet()) {
            if (!otherSpecies.equals(species1) && !otherSpecies.equals(species2)) {
                double d1 = distanceMatrix.get(species1).get(otherSpecies);
                double d2 = distanceMatrix.get(species2).get(otherSpecies);
                double newDistance;
                if (useUPGMA) {
                    newDistance = ((size1 * d1) + (size2 * d2)) / (size1 + size2);
                } else {
                    newDistance = (d1 + d2) / 2.0; // WPGMA
                }
                newDistances.put(otherSpecies, newDistance);
            }
        }

        distanceMatrix.remove(species1);
        distanceMatrix.remove(species2);

    for (String otherSpecies : distanceMatrix.keySet()) {
            distanceMatrix.get(otherSpecies).remove(species1);
            distanceMatrix.get(otherSpecies).remove(species2);
            distanceMatrix.get(otherSpecies).put(newCluster, newDistances.get(otherSpecies));
        }

    distanceMatrix.put(newCluster, newDistances);
    // ensure diagonal defined
    distanceMatrix.get(newCluster).put(newCluster, 0.0);

    // update cluster sizes
    clusterSizes.put(newCluster, size1 + size2);
    clusterSizes.remove(species1);
    clusterSizes.remove(species2);

        if (showMatrices) {
            System.out.println("\nMerged: " + species1 + " + " + species2 + " -> " + newCluster);
            printDistanceMatrix();
        }
    }

    private static void printTree(TreeNode node, String prefix, boolean isLastChild) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isLastChild ? "+-- " : "|-- ") + node.name);
        String newPrefix = prefix + (isLastChild ? "    " : "|   ");

        if (node.left != null) {
            printTree(node.left, newPrefix, node.right == null);
        }
        if (node.right != null) {
            printTree(node.right, newPrefix, true);
        }
    }
    private static void populateDistanceMatrix(String[] species, List<String> sequences) {
        for (int i = 0; i < species.length; i++) {
            distanceMatrix.put(species[i], new HashMap<>());
            treeNodes.put(species[i], new TreeNode(species[i]));
            clusterSizes.put(species[i], 1);
            for (int j = 0; j < species.length; j++) {
                double distance = calculatePairwiseDistance(sequences.get(i), sequences.get(j));
                distanceMatrix.get(species[i]).put(species[j], distance);
            }
            distanceMatrix.get(species[i]).put(species[i], 0.0);
        }
    }

    private static void populateDistanceMatrixBinary(String[] species, List<String> binaryVectors) {
        for (int i = 0; i < species.length; i++) {
            distanceMatrix.put(species[i], new HashMap<>());
            treeNodes.put(species[i], new TreeNode(species[i]));
            clusterSizes.put(species[i], 1);
            for (int j = 0; j < species.length; j++) {
                double distance = calculateBinaryDistance(binaryVectors.get(i), binaryVectors.get(j));
                distanceMatrix.get(species[i]).put(species[j], distance);
            }
            distanceMatrix.get(species[i]).put(species[i], 0.0);
        }
    }


    private static double calculatePairwiseDistance(String seq1, String seq2) {
        double distance = 0;
        for (int i = 0; i < seq1.length(); i++) {
            if (seq1.charAt(i) != seq2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    private static double calculateBinaryDistance(String v1, String v2) {
        // Simple Hamming distance (could later normalize by length if desired)
        double dist = 0;
        for (int i = 0; i < v1.length(); i++) {
            if (v1.charAt(i) != v2.charAt(i)) dist++;
        }
        return dist;
    }

    private static void printDistanceMatrix() {
        // Collect ordered list of keys for consistent columns
        List<String> labels = new ArrayList<>(distanceMatrix.keySet());
        Collections.sort(labels);
        System.out.print("\t");
        for (String col : labels) {
            System.out.print(col + "\t");
        }
        System.out.println();
        for (String row : labels) {
            System.out.print(row + "\t");
            for (String col : labels) {
                Double d = distanceMatrix.get(row).get(col);
                if (d == null && distanceMatrix.get(col) != null) {
                    d = distanceMatrix.get(col).get(row); // attempt symmetric lookup
                }
                System.out.print(String.format(Locale.US, "%.2f\t", d == null ? Double.NaN : d));
            }
            System.out.println();
        }
    }



    private static class TreeNode {
        String name;
        TreeNode left;
        TreeNode right;

        TreeNode(String name) {
            this.name = name;
        }
    }
}