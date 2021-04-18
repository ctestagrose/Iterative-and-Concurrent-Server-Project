/**
 * The node class provides the functionality to create a Binary Tree node
 * Each node will hold the state name and the state's death rate
 * Each node will contain the reference to its left and right child
 * 
 * @author Conrad Testagrose
 * @version 03/20/2021
 *
 */

public class Node {

    String stateName;
    Double deathRate;
    Node leftChild;
    Node rightChild;

    /**
     * This is the Node class constructor
     * 
     * @param stateName this parameter holds the passed state name
     * @param deathRate this parameter holds the death rate of each state
     */
    public Node(String stateName, Double deathRate){

        this.stateName = stateName;
        this.deathRate = deathRate;
        leftChild = rightChild = null;

    }

    /**
     * The printNode() method will print the node state name along with the death rate
     * 
     * 
     */
    public void printNode(){

        System.out.printf("%-15s  %8.2f\n", stateName, deathRate);
    }
    
}
