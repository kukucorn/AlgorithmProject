package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TreeTraversal {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] nodeInfo = new String[N];
        for(int i = 0; i < N; ++i) {
            nodeInfo[i] = br.readLine();
        }

        Map<Character, TreeNode> nodeMap = new HashMap<>();
        for(int i = 0; i < N; ++i) {
            Character nodeName = nodeInfo[i].charAt(0);
            nodeMap.put(nodeName, new TreeNode(nodeName));
        }

        for(int i = 0; i < N; ++i) {
            Character nodeName = nodeInfo[i].charAt(0);
            if(nodeInfo[i].charAt(2) != '.') nodeMap.get(nodeName).connectLeftNode(nodeMap.get(nodeInfo[i].charAt(2)));
            if(nodeInfo[i].charAt(4) != '.') nodeMap.get(nodeName).connectRightNode(nodeMap.get(nodeInfo[i].charAt(4)));
        }

        TreeNode rootNode = nodeMap.get('A');

        StringBuilder preOrder = new StringBuilder();
        rootNode.preOrder(preOrder);

        StringBuilder inOrder = new StringBuilder();
        rootNode.inOrder(inOrder);

        StringBuilder postOrder = new StringBuilder();
        rootNode.postOrder(postOrder);

        System.out.println(preOrder);
        System.out.println(inOrder);
        System.out.println(postOrder);
    }
}

class TreeNode {
    private TreeNode leftNode;
    private TreeNode rightNode;
    private char nodeName;

    public TreeNode(char nodeName) {
        this.nodeName = nodeName;
    }

    public void connectLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void connectRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public void preOrder(StringBuilder order) {
        order.append(nodeName);
        if(leftNode != null) leftNode.preOrder(order);
        if(rightNode != null) rightNode.preOrder(order);
    }

    public void inOrder(StringBuilder order) {
        if(leftNode != null) leftNode.inOrder(order);
        order.append(nodeName);
        if(rightNode != null) rightNode.inOrder(order);
    }

    public void postOrder(StringBuilder order) {
        if(leftNode != null) leftNode.postOrder(order);
        if(rightNode != null) rightNode.postOrder(order);
        order.append(nodeName);
    }
}