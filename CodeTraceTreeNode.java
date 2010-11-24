/*
* File:		CodeTraceTreeNode.java
* Created:	November 23, 2010 
* Author:	Lucy Davies: 0802331
			csuiaj, CSys MEng
*/

import java.util.*;

public class CodeTraceTreeNode
{
	private CodeTraceTreeNode parent;
		public  CodeTraceTreeNode getParent()
			{ return parent; }
	
	private Vector<CodeTraceTreeNode> children;
		public  Vector<CodeTraceTreeNode> getChildren()
			{ return children; }
		public void addChild( CodeTraceTreeNode c )
		{
			children.add( c );
		}
	
	private Vector<String> userinputs;
		// TODO
		// will contain a list of variables which are
		// from user input at this point in the code.
		// should allow comparison with arguments to
		// vulnerable functions
	
	private int conditionals;
		public void incConditionals()
			{ conditionals++; }
		public int getConditionals()
			{ return conditionals; }
	
	private int depth;
		public void incDepth()
			{ depth++; }
		public int getDepth()
			{ return depth; }
	
	private CodeLine line;
		public CodeLine getLine()
			{ return line; }
		public void setLine( CodeLine l )
			{ line = l; }


	CodeTraceTreeNode(
		CodeTraceTreeNode p,
		Vector<CodeTraceTreeNode> c,
		Vector<String> u,
		int conds,
		int d,
		CodeLine l
	)
	{
		parent = p;
		p.addChild( this );
		
		children = c;
		userinputs = u;
		conditionals = conds;
		depth = d;
		line = l;
	}
	
	CodeTraceTreeNode( CodeLine l )
	{
		parent = null;
		children = new Vector<CodeTraceTreeNode>();
		conditionals = 0;
		depth = 0;
		line = l;
	}
	
	CodeTraceTreeNode( CodeTraceTreeNode p, CodeLine l )
	{
		parent = p;
		p.addChild( this );
		
		children = new Vector<CodeTraceTreeNode>();
		conditionals = p.getConditionals();
		depth = p.getDepth();
		line = l;
	}
	
	public String toString()
	{
		return this.toString( 0 );
	}
	public String toString( int tabs )
	{
		String r = "";
		for ( int i = 0; i < tabs; i++ )
		{
			r += "\t";
		}
		
		r += line;
		r += " - {c = " + conditionals + ", d = " + depth + "}";

		tabs++;
		
		for(int i = 0; i < children.size(); i++)
		{
	    	CodeTraceTreeNode child = children.elementAt(i);
			r += "\n" + ( child.toString( tabs ) );
		}
		
		return r;
	}
}
