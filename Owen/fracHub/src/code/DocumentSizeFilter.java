package code;

//Code from Oracle
import javax.swing.*;
import javax.swing.text.*;


public class DocumentSizeFilter extends DocumentFilter {
	int maxChars;
	
	public DocumentSizeFilter(int maxChars) {
		this.maxChars = maxChars;
	}

}