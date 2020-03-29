
import java.util.Queue;
import java.util.Set;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halim
 */
public class BreadthFirsthSeacrh {
    int noOfStates;
    int noOfActions;
    int noOfProblems;
    String[] states;
    String[] actions;
    int[][] transitionMatrix;
    String[][] problems;
//Problem[] problems;
    Queue<Node> frontier;
    ArrayList<Node> exploredSet;
    public void BreadthFirstSearch()
    {
        Scanner scanner = new Scanner(System.in);
        Scanner stringSc = new Scanner(System.in);
        System.out.print("How many states? ");
        noOfStates = Integer.parseInt(scanner.next());
        
        System.out.println();
        System.out.print("How many actions? ");
        noOfActions = Integer.parseInt(scanner.next());
        
        System.out.println();
        System.out.print("How many problems? ");
        noOfProblems = Integer.parseInt(scanner.next());
        
        
        states=new String[noOfStates];
        actions=new String[noOfActions];
        problems=new String[noOfProblems][2];
//problems=new Problem[noOfProblems];
        transitionMatrix=new int[noOfStates][noOfActions];
        
        System.out.println("Enter states: ");
        for(int i = 0 ; i < noOfStates ; i++)
         {
         System.out.print("State " + (i+1) + ": ");
         states[i] = stringSc.nextLine();
         }
        
        System.out.println("Enter actions: ");
        for(int i = 0 ; i < noOfActions ; i++)
         {
         System.out.print("Action " + (i+1)+ ": ");
         actions[i] = stringSc.nextLine();
         }
        
        
        System.out.println("Enter transition matrix enteries: ");
        for(int i = 0 ; i < noOfStates ; i++)
         {
            for(int j = 0 ; j < noOfActions ; j++)
            {
                transitionMatrix[i][j] = scanner.nextInt();
            }
         }
        
        
        System.out.println("Enter problems: ");
        for(int i = 0 ; i < noOfProblems ; i++)
         {
         System.out.print("Problem " + (i+1) + " initial state: ");
         String initial=stringSc.nextLine();
         
         //problems[i].initialState = stringSc.nextLine();
         System.out.print("Problem " + (i+1) + " final state: ");
         String finalState=stringSc.nextLine();
         //problems[i].finalState = stringSc.nextLine();
         problems[i][0]=initial;
         problems[i][1]=finalState;
         //problems[i].setFinalState(finalState);
         }
         
        exploredSet = new ArrayList<>();
        frontier = new LinkedList<>();
        
        Node solution;
        for (int i = 0; i < noOfProblems; i = i + 1)
		{
			solution = BFS(problems[i]);
			
			if (solution.state == -1 && solution.parent == null)
			{
                            System.out.println("No solution exits");
				//cout << "no solution exists" << endl;
			}
			else if (solution.parent == null)
			{
                            System.out.println("Initial state is the goal state");
				//cout << "Initial state is the goal state" << endl;
			}
			else
			{
				getSolution(solution);
                                System.out.println();
			}
			exploredSet.clear();
			frontier.clear();
			//clearSolutionQueue();
		}
    }
    
    public void getSolution(Node node)
    {
        if(node.parent == null)
        {
            return;
        }
        else
        {
            getSolution(node.parent);
            System.out.print(actions[node.action] + "->");
        }
        
    }
    
    public int getStateNumber(String state)
    {
        for (int i = 0; i < noOfStates; i = i + 1)
	{
		if (states[i].equals(state))
		{
			return i;
		}
        }
        return -1;
    }
    
    public boolean goalTest(int initialState, int finalState)
    {
		return (initialState==finalState);
    }
    
    Node generateChild(Node parent, int action)
    {
	int childState = transitionMatrix[parent.state][action];
         Node child = new Node(childState, action, (parent.cost+1),parent);
         return child;
    }
    
    
    public Node BFS(String[] problem)
	{
                int initialStateNumber=getStateNumber(problem[0]);
                int finalStateNumber=getStateNumber(problem[1]);
		//int initialStateNumber = getStateNumber(problem.initialState);
		//int finalStateNumber = getStateNumber(problem.finalState);
		Node initial = new Node(initialStateNumber,-1,0,null);
		if (goalTest(initialStateNumber, finalStateNumber))
		{
			return initial;
		}
                frontier.add(initial);
		//frontier.push(initial);
		while (!frontier.isEmpty())
		{
			//Node n = frontier.front();
                        Node n = frontier.remove();
                        
                        exploredSet.add(n);
			for (int i = 0; i < noOfActions; i = i + 1)
			{
				Node childNode = generateChild(n, i);
				boolean flagFrontier = inFrontier(childNode);
				boolean flagExploredSet = inExploredSet(childNode);
				if (flagFrontier==false && flagExploredSet == false)
				{
					if (goalTest(childNode.state, finalStateNumber))
					{
						return childNode;
					}
					//cout << childNode.action << endl << endl;
					frontier.add(childNode);
					//solution.push(childNode.action);
					//s.push(childNode);
				}
			}
                }
                return new Node();
        }
    
 public boolean inExploredSet(Node node) {
        Iterator<Node> it = exploredSet.iterator();
        for (int q = 0; it.hasNext(); q++) {
            Node n = it.next();
            if (n.getState() == node.getState()) {
                return true;
            }
        }
        return false;
    }

    public boolean inFrontier(Node node) {
        Iterator<Node> it = frontier.iterator();
        for (int q = 0; it.hasNext(); q++) {
            Node n = it.next();
            if (n.getState() == node.getState()) {
                return true;
            }
        }
        return false;
    }
}
