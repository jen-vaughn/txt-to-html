import java.util.Scanner;
import java.io.*;

public class Webify {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Jennifer Vaughn
		// 10/5/2022
		// .txt to .html
		// This program will convert a given .txt file to a .html file.
		
		Scanner CONSOLE = new Scanner(System.in);
		
		//Scans console for file name
		System.out.println("Please input the .txt file name: ");
		String fileName = CONSOLE.nextLine();
		
		//Trims the file name
		String fileNameWOSuffix = fileName.substring(0, fileName.length()-4);
		//creates and prints the HTML file name
		String htmlFileName = fileNameWOSuffix + ".html";
		System.out.println(htmlFileName);
	
		//Scans the input file
		Scanner input = new Scanner(new File(fileName));	//input file
		PrintStream output = new PrintStream(new File(htmlFileName));	//output file
		
		
				
		//Print beginning of HTML file
		output.println("<html>");
		output.println("<body>");
		
		
		
		
		
		while (input.hasNextLine())	{		//while file has a new line
			String line = input.nextLine();  //gets one line of text
			
			if (line.length() == 0) {
				line = "<p>";
			}
			else {
				if (line.startsWith("_") && line.endsWith("_"))	{
					line = "<h1>" + line.substring(1, line.length()-1)  + "</h1>";
				}
				if (line.startsWith("-"))	{
					output.println("<ul>");
					while (line.startsWith("-")) {
						output.println("<li>" + line.substring(2) + "</li>");
						line = input.nextLine();	//goes to the next line with "-"
					}
					output.println("</ul>");
				}
				//If no rule has been matched...
				else {
					//check for URL
					String newContent = urlCheck(line);
					
					line = newContent + "<br />";
				}
			}
			output.println(line);	//prints entire line into the new output file
		}
		//print ending of HTML file
		output.println("</body>");
		output.println("</html>");

	}
	
	public static String urlCheck(String content)	{
		String script = "";
		
		Scanner lineScan = new Scanner(content);
		
		while (lineScan.hasNext())	{
			String word = lineScan.next();	//get each word of line
			//if one word starts with [[ and ends with ]], then it is a URL
			if (word.startsWith("[[") && word.endsWith("]]"))	{
				//remove the [[ and ]]
				String noBrackets = word.substring(2, word.length()-2);
				
				String x = noBrackets.substring(0, noBrackets.indexOf("]"));	//get x, what's to the left
				String y = noBrackets.substring(noBrackets.indexOf("[")+1);		//get y, what's to the right
				String finalURL = "<a href=\'" + x + "\'>" + y + "</a>";	//convert to hyperlink
				script += finalURL + "\s";	//adds whitespace for each word
			}
			else	{
				script += word + "\s";
			}
		}
		return script.substring(0, script.length()-1);	//remove the final character since it is extra whitespace
	}

}
