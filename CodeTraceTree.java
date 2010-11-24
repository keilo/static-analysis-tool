/*
* File:		CodeTraceTree.java
* Created:	November 23, 2010 
* Author:	Lucy Davies: 0802331
			csuiaj, CSys MEng
*/

import java.util.*;

public class CodeTraceTree
{
	private CodeTraceTreeNode root;
		public CodeTraceTreeNode getRoot()
			{ return root; }
	
	private Vector<CodeTraceTreeNode> problemLines;
		public Vector<CodeTraceTreeNode> getProblemLines()
			{ return problemLines; }
		public void addProblemLine( CodeTraceTreeNode newNode )
		{
			problemLines.add( newNode );
		}
	
	CodeTraceTree(
		CodeTraceTreeNode r
	)
	{
		root = r;
		problemLines = new Vector<CodeTraceTreeNode>();
	}
	CodeTraceTree()
	{
		this(
			new CodeTraceTreeNode(
				new CodeLine(
					1, "main"
				)
			)
		);
	}
	
	public String toString()
	{
		String r = "CodeTraceTree:\n\n" +
			root.toString();
		
		if ( ! problemLines.isEmpty() )
		{
			r += "\n\nProblem Lines:\n";
			
			
			for(int i = 0; i < problemLines.size(); i++)
			{
		    	CodeTraceTreeNode node = problemLines.elementAt( i );
				r += "\n" + ( node.getLine().toString() );
				r += " - {c = " + node.getConditionals()
					+ ", d = " + node.getDepth() + "}";
			}
		}
		
		return r;
	}
}



class Test_CodeTraceTree
{
	public static void main( String[] args )
	{
		CodeTraceTreeNode r = new CodeTraceTreeNode
		(
			new CodeLine ( 1, "main()" )
		);
		CodeTraceTree tree = new CodeTraceTree( r );
		
		// old code: would generate a new tree, with main at line 1
		// CodeTraceTree tree = new CodeTraceTree();
		
		CodeTraceTreeNode root = tree.getRoot();
		
		CodeTraceTreeNode l3 = new CodeTraceTreeNode
		(
			root, new CodeLine ( 3, "f()" )
		);
		
		CodeTraceTreeNode l8 = new CodeTraceTreeNode
		(
			l3, new CodeLine ( 8, "strcpy", true )
		);
		l8.incDepth();
		tree.addProblemLine( l8 );
		
		CodeTraceTreeNode l9 = new CodeTraceTreeNode
		(
			l8, new CodeLine ( 9, "if()" )
		);
		
		CodeTraceTreeNode l11 = new CodeTraceTreeNode
		(
			l9, new CodeLine ( 11, "if()" )
		);
		l11.incDepth();
		l11.incConditionals();
		
		CodeTraceTreeNode lEND = new CodeTraceTreeNode
		(
			l9, new CodeLine ( 0, "END" )
		);
		
		CodeTraceTreeNode l8b = new CodeTraceTreeNode
		(
			l11, new CodeLine ( 8, "ALREADY SEEN" )
		);
		
		CodeTraceTreeNode l12 = new CodeTraceTreeNode
		(
			l8b, new CodeLine ( 12, "strcpy", true )
		);
		tree.addProblemLine( l12 );
		
		
		CodeTraceTreeNode lEND2 = new CodeTraceTreeNode
		(
			l12, new CodeLine ( 0, "END" )
		);

		System.out.println( tree );
	}
}

/*
The above test is based on this example:

1	main()
2	{
3		f();
4	}
5	
6	f()
7	{
8		strcpy;
9		if()
10		{
11			f();
12			strcpy;
13		}
14	}



This would be converted by the parser into an array of trees like:
AOT
{
	1 - main()
	{
		3 - f()
	}
	
	6 - f()
	{
		8 - strcpy - vuln
		9 - if
		{
			11 - f()
			12 - strcpy - vuln
		}
	}
}


*/
