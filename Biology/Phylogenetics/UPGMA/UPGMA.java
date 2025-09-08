import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Map<String, Double>> distanceMatrix = new HashMap<>();
    private static Map<String, TreeNode> treeNodes = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("What do you have?");
        System.out.println("1. DNA sequence");
        System.out.println("2. Distance matrix");
    // /todo: Add option 3 for binary character (0/1) presence/absence matrix input
        System.out.print("Enter your choice (1/2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

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
            populateDistanceMatrix(speciesNames, sequences);
        } else if (choice == 2) { // User chose Distance matrix
            // Initialize maps and tree nodes first
            for (int i = 0; i < numOfSpecies; i++) {
                distanceMatrix.put(speciesNames[i], new HashMap<>());
                treeNodes.put(speciesNames[i], new TreeNode(speciesNames[i]));
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

        TreeNode newNode = new TreeNode(newCluster);
        newNode.left = treeNodes.get(species1);
        newNode.right = treeNodes.get(species2);

        treeNodes.put(newCluster, newNode);
        treeNodes.remove(species1);
        treeNodes.remove(species2);

        for (String otherSpecies : distanceMatrix.keySet()) {
            if (!otherSpecies.equals(species1) && !otherSpecies.equals(species2)) {
                double newDistance = (distanceMatrix.get(species1).get(otherSpecies) + distanceMatrix.get(species2).get(otherSpecies)) / 2;
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
            for (int j = 0; j < species.length; j++) {
                double distance = calculatePairwiseDistance(sequences.get(i), sequences.get(j));
                distanceMatrix.get(species[i]).put(species[j], distance);
            }
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



    private static class TreeNode {
        String name;
        TreeNode left;
        TreeNode right;

        TreeNode(String name) {
            this.name = name;
        }
    }
}