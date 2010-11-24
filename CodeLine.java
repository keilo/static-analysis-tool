/*
* File:		CodeLine.java
* Created:	November 23, 2010 
* Author:	Lucy Davies: 0802331
			csuiaj, CSys MEng
*/

import java.util.*;

public class CodeLine
{
	private int lineNumber;
		public int getLineNumber()
			{ return lineNumber; }
	
	private String name;
		public String getName()
			{ return name; }
	
	private boolean isVulnerableFunction;
		public boolean isVulnerableFunction()
			{ return isVulnerableFunction; }
	
	private Vector<String> functionArgs;
		public Vector<String> getFunctionArgs()
			{ return functionArgs; }
		// used for passing variables through scopes
		// and for seeing if they have come from
		// user input
	
	CodeLine( int n, String nm )
		{ this( n, nm, false ); }
	CodeLine( int n )
		{ this( n, "IGNORE" ); }
	CodeLine( int n, String nm, boolean v )
		{ this( n, nm, v, null ); }
	CodeLine( int n, String nm, boolean v, Vector<String> args )
	{
		lineNumber = n;
		name = nm;
		isVulnerableFunction = v;
		
		// TODO
		// do something with args	
		// not entirely sure what yet...	
	}
	
	public String toString()
	{
		String r = "";
		
		if ( isVulnerableFunction )
			r += "[!] - ";
		
		if ( lineNumber != 0 )
		{
			r += "Line " + ((Integer) lineNumber).toString();	
			r += " - ";
		}
			
		r += name;
		
		return ( r );
	}
}
