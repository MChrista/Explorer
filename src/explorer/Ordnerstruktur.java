/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explorer;

import	javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import	java.io.*;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;


public class Ordnerstruktur extends JPanel{
	JTree tree;
	JScrollPane pane;
	
	
	public Ordnerstruktur(){
		
		this.setLayout(new BorderLayout());
		FileSystemNode root=new FileSystemNode(null);
		DefaultTreeModel model=new DefaultTreeModel(root);
		tree=new JTree(model);
        File []laufwerke=File.listRoots();
        for(int i=0;i<laufwerke.length;i++){
        	root.add(new FileSystemNode(laufwerke[i]));    
        }
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				FileSystemNode fsn=(FileSystemNode)tree.getLastSelectedPathComponent();
				if(fsn.file!=null){
				File []files=fsn.file.listFiles();
				for(int i=0;i<files.length;i++){
			    	 if (files[i] != null && files[i].isDirectory()) {
			    		 fsn.add(new FileSystemNode(files[i]));
			    	 }					
				}
				}
				// TODO Auto-generated method stub
				
			}
		});
        
        pane=new JScrollPane(tree);
		this.add(pane,BorderLayout.CENTER);
		this.setVisible(true);
	}

}
