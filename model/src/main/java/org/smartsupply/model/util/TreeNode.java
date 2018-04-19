package org.smartsupply.model.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// for pre-expanding nodes
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 7050047486034923728L;

    private TreeNode parent;

    private List<TreeNode> children;

    private String title;

    private String key;

    private String isFolder;

    private String expanded;

    public TreeNode() {

    }

    public TreeNode(TreeNode parent, String title, String key, String isFolder,
                    String expanded) {
        this.setParent(parent);
        this.setTitle(title);
        this.setKey(key);
        this.setIsFolder(isFolder);
        this.setExpanded(expanded);
        this.setChildren(new ArrayList<TreeNode>());
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void addChild(TreeNode node) {
        if (this.getChildren() == null)
            this.setChildren(new ArrayList<TreeNode>());

        this.getChildren().add(node);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }

    public TreeNode copy() throws CloneNotSupportedException {
        TreeNode copy = new TreeNode();

        copy.setExpanded(new String(this.expanded));
        copy.setIsFolder(new String(this.isFolder));
        copy.setKey(new String(this.key));
        copy.setTitle(new String(this.title));
        if (this.parent == null)
            copy.setParent(null);
        else
            copy.setParent(this.getParent());

        copy.setChildren(new ArrayList<TreeNode>(this.getChildren()));
        // for (TreeNode node : this.getChildren())
        // copy.addChild(node.copy());

        return copy;
    }
}
