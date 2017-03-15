//SectionWordCounter.java
//This class takes a huge text file containing the text of Jonax and Bertha, stripped of 
//paragraph breaks and with sections divided by § marks, and then counts the number 
//of words in each section.

import java.io.*;

public class SectionWordCounter {

	private String textFile;
	
	public SectionWordCounter( String inputFile )
	{
		textFile = inputFile;
	}
	
	public int[] countSectionWords()
	{	
		//read in line
		String line;
		
		try {
			BufferedReader reader = new BufferedReader( new FileReader( textFile ) );
			line = reader.readLine();
			reader.close();
		} catch ( IOException e ) {
			e.printStackTrace();
			return null;
		}
		
		if ( line == null )
			return null;
		
		//split into sections delimited by § marks
		String[] sections = line.split( "§" );
		
		//go through these sections, counting the words in each and adding that count
		//to the final int[] of counts
		int[] sectionCounts = new int[ sections.length ];
		for ( int a = 0; a < sectionCounts.length; a++ ) {
			sectionCounts[ a ] = WordCounter.countWords( sections[ a ] );
		}
		
		return sectionCounts;
	}
	
	public static void main( String[] args )
	{
		SectionWordCounter swc = new SectionWordCounter( args[ 0 ] );
		int[] sectionCounts = swc.countSectionWords();
		for ( int a = 0; a < sectionCounts.length; a++ )
			System.out.print( sectionCounts[ a ] + " " );
	}

}