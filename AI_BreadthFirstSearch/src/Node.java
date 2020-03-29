/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halim
 */
public class Node {
   
    int state;
    int cost;
    int action;
    Node parent;
    
    public Node() {
        parent = null;
        state = -1;
        action = -1;
        cost = 0;
    }
    
    public Node(int state, int action, int cost,Node parent) 
    {   
        this.state = state;
        this.action = action;
        this.cost = cost;
        this.parent = parent;
    }
    public int getState() {
        return state;
    }

    public int getAction() {
        return action;
    }

    public int getCost() {
        return cost;
    }

    public Node getParent() {
        return parent;
    }    
}
