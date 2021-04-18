/**
 * The BinarySearchTree class provides the functionality to create a Binary Search Tree
 * This class provides multiple methods that allow the user to interact with the tree and manipulate the nodes of the tree
 * 
 * @author Conrad Testagrose 
 * @verison 03/24/2021
 */
import java.lang.StringBuilder;

public class BinarySearchTree {

    private Node root;
    int counter = 1;
    Node[] printArr;
    int arrIdx;
    double topDR = 1000;
    double smallestVal = 0;
    double lowDR = 0;
    Node temp;
    private StringBuilder findString = new StringBuilder();
    int numNodes = 0;
 
    /**
     * This is the BinarySearchTree Constructor 
     * 
     */
    public BinarySearchTree(){
        root = null;
    }

    /**
     * This is the insert() method and will insert a state into the tree based on the state name
     * 
     * @param name this contains the state name
     * @param DR this contains the double value of the state's death rate
     */
    public void insert(String name, double DR){

        Node newNode = new Node(name, DR);

        if(root == null){

            root = newNode;
            numNodes++;

        }
        else{

            Node current = root;
            Node parent = current;

            while(parent.leftChild != newNode || parent.rightChild != newNode){

                parent = current;
                if(name.compareTo(current.stateName) < 0){

                    current = current.leftChild;

                    if(current == null){

                        parent.leftChild = newNode;
                        numNodes++;
                        return;
                    }

                }
                else{

                    current = current.rightChild;

                    if(current == null){

                        parent.rightChild = newNode;
                        numNodes++;
                        return;
                    }
                }

            }

        }

    }

    /**
     * This is the find() method and will allow for the searching of the Binary Search Tree for a state
     * This method will build a string which will contain the path to the state
     * 
     * @param name this is the name of the state that the user would like to search
     * @return the double value of the states death rate
     */
    public double find(String name){

        Node current = root;
        
        while(current != null && name.compareToIgnoreCase(current.stateName) != 0){

        	if(current.stateName == "New Mexico") {
        		
        		findString.setLength(0);
        		
        	}
        	
            if(name.compareToIgnoreCase(current.stateName) < 0){

                findString.append(current.stateName);
                findString.append(" ---> ");
                current = current.leftChild;

            }
            else if(name.compareToIgnoreCase(current.stateName) > 0){
                
                findString.append(current.stateName);
                findString.append(" ---> ");
                current = current.rightChild;

            }
            if(current == null){

                System.out.printf("%s not found in binary search tree\n", name);

                return -1;
            }

        }

        findString.append(current.stateName);
        System.out.printf("%s is found with a death rate of: %.2f\n", name, current.deathRate);
        System.out.printf("Path to %s:\n", name);
        System.out.println(findString);
        findString.setLength(0);

        return current.deathRate;
    }

    /**
     * This is the delete() method and allows the user to delete a node from the tree
     * 
     * @param name this the is name of the state that the user would like to delete
     */
    public void delete(String name){

        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        
        while(name.compareToIgnoreCase(current.stateName) != 0){

            parent = current;

            if(name.compareToIgnoreCase(current.stateName) < 0){

                isLeftChild = true;
                current = current.leftChild;

            }
            else{

                isLeftChild = false;
                current = current.rightChild;
            
            }

            if(current == null){

                System.out.println("Node to be deleted not found in binary seach tree");
                return;
            }

        }

        if(current.leftChild == null && current.rightChild == null){
        	
        	numNodes--;

            if(current == root){
                root = null;
                
            }
            else if(isLeftChild == true){
                parent.leftChild = null;
                
            }
            else{
                parent.rightChild = null;
               
            }
        }

        else if(current.rightChild == null){
        	numNodes--;

            if(current == root){
                root = current.leftChild;
               
            }
            else if(isLeftChild == true){
                parent.leftChild = current.leftChild;
                
            }
            else{
                parent.rightChild = current.leftChild;
               
            }
        }

        else if(current.leftChild == null){
        	
        	numNodes--;

            if(current == root){
                root = current.rightChild;
            
            }
            else if(isLeftChild == true){
                parent.leftChild = current.rightChild;
                
            }
            else{
                parent.rightChild = current.rightChild;
               
            }
        }

        else{

            Node successor = getSuccessor(current);

            if(current == root){
                root = successor;
               
            }
            else if(isLeftChild == true){
                parent.leftChild = successor;

            }
            else{
                parent.rightChild = successor;
        
            }

            successor.leftChild = current.leftChild;
            numNodes--;
        } 
        
        System.out.printf("Record containing state name of \"%s\" deleted from binary search tree\n", name);
        System.out.printf("There are %d records remaining in the binary search tree\n\n", numNodes);
        return;
    }

    /**
     * This is the traversalType() method and helps determine the appropriate traversal method to call 
     * 
     * 
     * @param type this integer determines if the user has selected inorder, preorder, or postorder
     */
    public void traversalType(int type){

        if(type == 1){
            printInorder(root);
        }
        else if(type == 2){
            printPreorder(root);
        }
        else if(type == 3){
            printPostorder(root);

        }

    }

    /**
     * This is the printPostorder() method and prints the nodes of the tree in postorder
     * This method is called recursively to print
     * 
     * @param node this is the current node to be printed
     */
    public void printPostorder(Node node) {

        if(node != null){
            printPostorder(node.leftChild);
            printPostorder(node.rightChild);
            node.printNode();
        }

    }

    /**
     * This is the printInorder() method and print the nodes of the tree Inorder
     * This method is called recursively to print
     * 
     * @param node this is the current node to be printed
     */
    public void printInorder(Node node){

        if(node != null){
            printInorder(node.leftChild);
            node.printNode();
            printInorder(node.rightChild);
        }
    }

    /**
     * This is the printPreorder() method and prints the nodes of the tree in preorder
     * This method is called recursively to print
     * 
     * @param node this is the current node to be printed
     */
    public void printPreorder(Node node){

        if(node != null){
            node.printNode();
            printPreorder(node.leftChild);
            printPreorder(node.rightChild);

        }

    }

    /**
     * This is the printBottomStates() and it helps determine how many nodes to print from the highest death rate states
     * 
     * 
     * @param c this parameter is the number of states the user would like to print
     */
    public void printBottomStates(int c){

    	if(c > numNodes) {
        	
        	c = numNodes;
        }
    	
        printArr = new Node[c];
        Node current = root;
        
        if(root == null) {
        	
        	return;
        }


        for(int j = 0; j < c; j++){

            temp = inorderSortBottom(current);
            printArr[j] = temp;
            topDR = temp.deathRate;
            smallestVal = 0;
            
        }

        for(int i = 0; i < printArr.length; i++){

            printArr[i].printNode();
        }

        smallestVal = 0;
        topDR = 1000;

    }


    /**
     * This is the inorderSortTop() and this method will search the tree recursively and add each state to an array based on its order in regard to its death rate
     * 
     * 
     * @param node this is the current node
     * @return this returns the node with the best death rate
     */
    public Node inorderSortTop(Node node){

        if(node == null){

            return null;
        }

        if(node.deathRate < topDR && node.deathRate > smallestVal){

            topDR = node.deathRate;
            temp = node;

            
        }
        inorderSortTop(node.leftChild);
        inorderSortTop(node.rightChild);

        return temp;
    }

    /**
     * This is the inorderSortBottom() and this method will search the tree recursively and add each state to an array based on its order in regard to its death rate
     * 
     * 
     * @param node this is the current node
     * @return this returns the node with the worst death rate
     */
    public Node inorderSortBottom(Node node){

        if(node == null){

            return null;
        }

        if(node.deathRate < topDR && node.deathRate > smallestVal){

            smallestVal = node.deathRate;
            temp = node;

            
        }
        inorderSortBottom(node.leftChild);
        inorderSortBottom(node.rightChild);

        return temp;
    }

    /**
     * This is the printTopstates() and it helps determine how many nodes to print from the lowest death rate states
     * 
     * 
     * @param c this parameter is the number of states the user would like to print
     */
    public void printTopStates(int c){

    	 if(c > numNodes) {
         	
         	c = numNodes;
         }
    	
    	printArr = new Node[c];
        Node current = root;

        for(int j = 0; j < c; j++){

            temp = inorderSortTop(current);
            printArr[j] = temp;
            smallestVal = temp.deathRate;
            topDR = 1000;
            
        }

        for(int i = 0; i < printArr.length; i++){

            printArr[i].printNode();
        }

        smallestVal = 0;
        topDR = 1000;

    }

    /**
     * This is the getSuccessor() method and it is used to determine the successor of a node being deleted with two children nodes
     * 
     * 
     * @param delete this is the node to be deleted
     * @return this method returns the successor to the node to be deleted
     */
    public Node getSuccessor(Node delete){

        Node successorParent = delete;
        Node successor = delete;
        Node current = delete.rightChild;

        while(current != null){

            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if(successor != delete.rightChild){

            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delete.rightChild;
        
        }
        
        return successor;
    }
    
}
