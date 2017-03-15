//WordCounter.java
//A simple class for counting the words in a String.
public class WordCounter {

	public static int countWords( String string )
	{
		int counter = 1;
		for ( int a = 0; a < string.length(); a++ ) {
			if ( string.charAt( a ) == ' ' )
				counter++;
		}
		
		return counter;
	}

}