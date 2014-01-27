package explorer;
import java.io.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class FileSystemNode extends DefaultMutableTreeNode{
	String name;
	File file;
	
	public FileSystemNode(File f){
		if(f==null){
			name="";
		}else if(f.getAbsolutePath().split("/").length==1){
			name=f.getAbsolutePath();
		}else{
			name=f.getName();
		}
		file=f;
	}
	
	@Override
	public String toString() {
	    return this.name;
	}
	
	

}
