/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Michael Christa
 */
public class Baumstruktur extends JPanel{
    JTree tree;
    
    public Baumstruktur(){
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("");
        DefaultTreeModel model=new DefaultTreeModel(root);
        this.setLayout(new GridLayout(1,1));
        tree=new JTree(model);
        tree.setSize(400, 300);
        File []laufwerke=File.listRoots();
        for(int i=0;i<laufwerke.length;i++){
        	root.add(new DefaultMutableTreeNode(laufwerke[i].getPath()));
        }
        tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println(e.getPath());
				
				
			}
		});
        this.add(tree);
        
    }
}