
public class EmptyFileException extends Exception {
	private static final long serialVersionUID = 1L;
	public EmptyFileException() {
		
	}
	
	public EmptyFileException(String message) {
		super(message);// 把参数传递给Throwable的带String参数的构造方法 
	}
}
