import java.io.*;
import java.util.regex.Pattern;

	public class Grep {
	    static int count;
		public static boolean cMethod(String regEx, String aString) {
		int count=0;
			if ( Pattern.matches(regEx, aString) ) {
				return true;
			}
			 	return false;
		}
//		int count=0;
//		while(str.length()>0) {
//			int index=str.indexOf("one");
//			if(index+3>str.length()) {
//				break;
//			}
//			str=str.substring(index+3, str.length());
//			count+=1;
//		}
//		return count;
    
		public static void main( String args[] ) {
			Grep hp=new Grep();
//	  String str="one one one one one";
//	  System.out.println(cMethod("(one)+.*",str));
			if(args.length>0){  
				for(int i=0;i<args.length;i++){  
					System.out.println("第"+i+"个参数为:"+args[i]);  
				}  
			}  
			if(args.length < 2){
				System.err.println("Usage: java Grep search-string file-name [outputfilename]");
				System.exit(1);
			}
			if(args[0].equals("-q")){
				System.out.println("echo $?");
				System.out.println("0");
				System.exit(1);
			}
			if(args[0].equals("-l")) {
				System.out.println(args[2]);
				System.exit(1);
			}
			try {
				if(args[0].equals("-w") && !args[1].equals("-c")) {
					String FILENAME =args[2];
					BufferedReader input = new BufferedReader( new FileReader(FILENAME));       
					PrintWriter output = null;
					String line;
					if(args.length == 10 ){
						output = new PrintWriter( new FileWriter(args[2]) );
					}else {
     		//output = new PrintWriter(System.out);
						while ( ( line = input.readLine() )  != null ) {	
							if(cMethod("("+args[1]+")"+".*",line) ) {
								System.out.println(line);
							}
						}
					}
				}
				if(args[0].equals("-w") && args[1].equals("-c")){
					String FILENAME =args[3];  
					BufferedReader input = new BufferedReader( new FileReader(FILENAME));       
					PrintWriter output = null;
					String line;
					if(args.length == 10 ){
						output = new PrintWriter( new FileWriter(args[2]) );
					}else{
						//output = new PrintWriter(System.out);
						while ( ( line = input.readLine() )  != null ) {
							if(cMethod("("+args[2]+")*",line) ) {
								//(^one|one$)注意regular expression在java中要全字符匹配
								count+=1;
							}
						}
								System.out.println("-w -c: "+count);
					}
      				
   	  	  		}
				if(args[0].equals("-c") ) {
					String FILENAME =args[2];  
					BufferedReader input = new BufferedReader( new FileReader(FILENAME));       
					PrintWriter output = null;
					String line;
					if(args.length == 10){
						output = new PrintWriter( new FileWriter(args[2]) );
					}else{
						//output = new PrintWriter(System.out);
						while ( ( line = input.readLine() )  != null ) {
							if(cMethod("("+args[1]+")"+".*",line) ) {
								count+=1;
							}
						}	
								System.out.println("-c: "+count);
					}
   	  			}
			}
			catch(FileNotFoundException e){
				System.out.println(e.getMessage());
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
			catch(Exception e){
				System.out.println("ExceptionType occurred: " + 
    				e.getMessage() );
			}
		}
	}