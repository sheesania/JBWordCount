//JBWordCount.java
//This program counts the number of words in each Jonax and Bertha section and outputs
//a chart showing the size of sections over time.

import java.util.*;
import org.jfree.data.xy.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import javax.swing.*;
import java.awt.*;

public class JBWordCount {

	public static void main( String[] args )
	{
		new JBWordCount().go();
	}
	
	public void go()
	{
		//get section word counts for each file
		int[] jb1 = new SectionWordCounter( "JB_1.txt" ).countSectionWords();
		int[] jb2 = new SectionWordCounter( "JB_2.txt" ).countSectionWords();
		int[] jb3 = new SectionWordCounter( "JB_3.txt" ).countSectionWords();
		int[] jb4 = new SectionWordCounter( "JB_4.txt" ).countSectionWords();
		int[] jb5 = new SectionWordCounter( "JB_5.txt" ).countSectionWords();
		int[] jb6 = new SectionWordCounter( "JB_6.txt" ).countSectionWords();
		int[] jb7 = new SectionWordCounter( "JB_7.txt" ).countSectionWords();
		int[] jb8 = new SectionWordCounter( "JB_8.txt" ).countSectionWords();
		int[] jb9 = new SectionWordCounter( "JB_9.txt" ).countSectionWords();
		int[] jb10 = new SectionWordCounter( "JB_10.txt" ).countSectionWords();
		int[] jb11 = new SectionWordCounter( "JB_11.txt" ).countSectionWords();
		int[] jb12 = new SectionWordCounter( "JB_12.txt" ).countSectionWords();
		int[] jb13 = new SectionWordCounter( "JB_13.txt" ).countSectionWords();
		int[] jb14 = new SectionWordCounter( "JB_14.txt" ).countSectionWords();
		int[] jb15 = new SectionWordCounter( "JB_15.txt" ).countSectionWords();
		
		//add these values to a chart data series
		XYSeries series = new XYSeries( "Section Word Counts" );
		int sectionNum1 = addArrayToSeries( series, jb1, 0 );
		int sectionNum2 = addArrayToSeries( series, jb2, sectionNum1 );
		int sectionNum3 = addArrayToSeries( series, jb3, sectionNum2 );
		int sectionNum4 = addArrayToSeries( series, jb4, sectionNum3 );
		int sectionNum5 = addArrayToSeries( series, jb5, sectionNum4 );
		int sectionNum6 = addArrayToSeries( series, jb6, sectionNum5 );
		int sectionNum7 = addArrayToSeries( series, jb7, sectionNum6 );
		int sectionNum8 = addArrayToSeries( series, jb8, sectionNum7 );
		int sectionNum9 = addArrayToSeries( series, jb9, sectionNum8 );
		int sectionNum10 = addArrayToSeries( series, jb10, sectionNum9 );
		int sectionNum11 = addArrayToSeries( series, jb11, sectionNum10 );
		int sectionNum12 = addArrayToSeries( series, jb12, sectionNum11 );
		int sectionNum13 = addArrayToSeries( series, jb13, sectionNum12 );
		int sectionNum14 = addArrayToSeries( series, jb14, sectionNum13 );
		addArrayToSeries( series, jb15, sectionNum14 );
		
		//make another chart data series with a trend line, calculated by averaging
		//every six values
		XYSeries trendLine = new XYSeries( "Trend Line" );
		for ( int a = 5; a < series.getItemCount(); a += 6 ) {
			double one = ( Double ) series.getY( a );
			double two = ( Double ) series.getY( a - 1 );
			double three = ( Double ) series.getY( a - 2 );
			double four = ( Double ) series.getY( a - 3 );
			double five = ( Double ) series.getY( a - 4 );
			double six = ( Double ) series.getY( a - 5 );
			double average = ( one + two + three + four + five + six ) / 6.0;
			trendLine.add( a, average );
		}
		
		//output total average while we're at it...
		//RESULTS: Average is 368 words per section
		double total = 0;
		for ( int counter = 0; counter < series.getItemCount(); counter++ )
			total += ( Double ) series.getY( counter );
		double average = total / series.getItemCount();
		System.out.println( "Average Word Count: " + average );
		
		//make a final dataset with both lines
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries( series );
		dataset.addSeries( trendLine );
		
		//make the chart
		JFreeChart chart = ChartFactory.createXYLineChart( "Section Word Counts", "Number of Section", "Number of Words", dataset, PlotOrientation.VERTICAL, false, false, false );
		
		//make a window, etc. to show the chart
		makeGUI( chart );
	}
	
	public void makeGUI( JFreeChart chart )
	{
		JFrame frame = new JFrame( "Section Word Counts" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		JPanel panel = new JPanel();
		panel.setLayout( new BorderLayout() );
		
		ChartPanel chartPanel = new ChartPanel( chart );
		panel.add( chartPanel, BorderLayout.CENTER );
		panel.validate();
		
		//set the trend line to be thicker
		chart.getXYPlot().getRenderer().setSeriesStroke( 1, new BasicStroke( 2.5f ) );
		
		frame.getContentPane().add( panel );
		frame.setSize( 800, 600 );
		frame.setVisible( true );
	}
	
	public int addArrayToSeries( XYSeries series, int[] wordCountArray, int sectionNumber )
	{
		for ( int a = 0; a < wordCountArray.length; a++ ) {
			series.add( sectionNumber, wordCountArray[ a ] );
			sectionNumber++;
		}
		
		return sectionNumber;
	}

}