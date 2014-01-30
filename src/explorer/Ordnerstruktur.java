package explorer;



import	javax.swing.*;
import static java.nio.file.StandardCopyOption.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.ReplicateScaleFilter;
import	java.io.*;
import java.nio.file.Files;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class Ordnerstruktur extends JPanel{
	JTree tree;
	JScrollPane pane;
	Explorer expl;
	
	
	public Ordnerstruktur(final Explorer expl){
		this.expl=expl;
		this.setLayout(new BorderLayout());
		FileSystemNode root=new FileSystemNode(null);
		DefaultTreeModel model=new DefaultTreeModel(root);
		tree=new JTree(model);
		tree.setDragEnabled(true);
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setDropMode(DropMode.USE_SELECTION);
        tree.setDropTarget(new DropTarget(tree, TransferHandler.MOVE,
                new DropTargetAdapter() {
                    @Override
                    public void drop(DropTargetDropEvent dtde) {
 
                        TreePath selectionPath = tree.getSelectionPath();
                        TreePath sourcePath = selectionPath.getParentPath();
 
                        FileSystemNode selectedNode = (FileSystemNode) selectionPath
                                .getLastPathComponent();
 
                        Point dropLocation = dtde.getLocation();
                        TreePath targetPath = tree.getClosestPathForLocation(
                                dropLocation.x, dropLocation.y);
 
                        System.out.println("###################");
 
                        System.out.println("srcPath: " + sourcePath);
                        System.out.println("targetPath: " + targetPath);
                        System.out.println("selectedNode: " + selectedNode);
 
                        if (isDropAllowed(sourcePath, targetPath, selectedNode)) {
                            System.out.println("drop accept");
                            FileSystemNode targetParentNode = (FileSystemNode) targetPath
                                    .getLastPathComponent();
                            FileSystemNode sourceParentNode = (FileSystemNode) sourcePath
                                    .getLastPathComponent();
                            
                            sourceParentNode.remove(selectedNode);
                            targetParentNode.add(selectedNode);
                            try {
                            	System.out.println("copy");
                            	System.out.println("selected: "+selectedNode.file.toPath());
                            	System.out.println("selected: "+targetParentNode.file.toPath());
								//Files.move(selectedNode.file.toPath(), targetParentNode.file.toPath(),REPLACE_EXISTING);
                         
                                File ziel=new File(targetParentNode.file.getAbsolutePath()+"\\\\"+selectedNode.file.getName());
                                ziel.mkdir();
                            	CopyDirectory copy=new CopyDirectory();
                            	copy.copyDir(selectedNode.file, ziel);
                            	copy.remove(selectedNode.file);
 	
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
 
                            dtde.dropComplete(true);
                            tree.updateUI();
                        } else {
                            System.out.println("drop: reject");
                            dtde.rejectDrop();
                            dtde.dropComplete(false);
                        }
                    }
 
                    private boolean isDropAllowed(TreePath sourcePath,
                            TreePath targetPath,
                            FileSystemNode selectedNode) {
                        if (((FileSystemNode) sourcePath
                                .getLastPathComponent()).isLeaf()) {
                        } else if (targetPath.equals(sourcePath)) {
                            return false;
                        }
                        //return selectedNode.isLeaf();
                        return true;
                    }
 
                }));
 
        for (int currentRowIndex = 0; currentRowIndex < tree.getRowCount(); currentRowIndex++) {
            tree.expandRow(currentRowIndex);
        }
        File []laufwerke=File.listRoots();
        for(int i=0;i<laufwerke.length;i++){
        	root.add(new FileSystemNode(laufwerke[i]));    
        }
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				FileSystemNode fsn=(FileSystemNode)tree.getLastSelectedPathComponent();
				
				if(fsn.file!=null){
					expl.getDst().directorySelected(fsn.file);
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
