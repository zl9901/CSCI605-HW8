import java.io.*;
import java.lang.management.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
	public class PiEvenOddImprovement {
		private static final String FILENAME = "number.txt";
		static double even=0;
		static double odd=0;

		
		public  void emptyFile(String ssr)throws EmptyFileException {
			if(ssr==null) {
				throw new EmptyFileException("EmptyFileException: null is empty");
			}
		}
		
		/** Get CPU time in nanoseconds. */
		public long getCpuTime( ) {
		    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
		    return bean.isCurrentThreadCpuTimeSupported( ) ?
		        bean.getCurrentThreadCpuTime( ) : 0L;
		}
		 
		/** Get user time in nanoseconds. */
		public long getUserTime( ) {
		    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
		    return bean.isCurrentThreadCpuTimeSupported( ) ?
		        bean.getCurrentThreadUserTime( ) : 0L;
		}

		/** Get system time in nanoseconds. */
		public long getSystemTime( ) {
		    ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
		    return bean.isCurrentThreadCpuTimeSupported( ) ?
		        (bean.getCurrentThreadCpuTime( ) - bean.getCurrentThreadUserTime( )) : 0L;
		}
		
		
		//static double quotient=odd/even;
		public static void main(String[] args) {
		// TODO Auto-generated method stub
				PiEvenOddImprovement hp=new PiEvenOddImprovement();
				BufferedReader br = null;
				FileReader fr = null;
				long startSystemTimeNano = hp.getSystemTime( );
				long startUserTimeNano   = hp.getUserTime( );
				long startCpuTimeNano   =hp.getCpuTime();
				System.out.println("System: "+startSystemTimeNano);
				System.out.println("User: "+startUserTimeNano);
				System.out.println("Cpu: "+startCpuTimeNano);
				try{
					//br = new BufferedReader(new FileReader(FILENAME));
					fr = new FileReader(FILENAME);
					br = new BufferedReader(fr);
					String sCurrentLine;
					while ((sCurrentLine = br.readLine()) != null) {
						for(int index=0;index<sCurrentLine.length();index++) {
							int number=Integer.parseInt(sCurrentLine.substring(index, index+1));
							if(number%2==0) {
								even+=1;
							}else {
								odd+=1;
							}
							//System.out.println(number);
						}
					}
					System.out.println(even);
					System.out.println(odd);
					System.out.println(odd/even);
					if(even==0) {
						String ssr=null;
						hp.emptyFile(ssr);
					}
				}catch (IOException e) {
					e.printStackTrace();
				} catch (EmptyFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if (br != null)
							br.close();
						if (fr != null)
							fr.close();
					}catch (IOException ex) {
						ex.printStackTrace();
					}
				}

				long endSystemTimeNano = hp.getSystemTime( )-startSystemTimeNano;
				long endUserTimeNano   = hp.getUserTime( )-startUserTimeNano;
				long endCpuTimeNano   =hp.getCpuTime()-startCpuTimeNano;
				System.out.println("System: "+endSystemTimeNano);
				System.out.println("User: "+endUserTimeNano);
				System.out.println("Cpu: "+endCpuTimeNano);
				
			}
		}
