
public class EmptyFileException extends Exception {
	private static final long serialVersionUID = 1L;
	public EmptyFileException() {
		
	}
	
	public EmptyFileException(String message) {
		super(message);// �Ѳ������ݸ�Throwable�Ĵ�String�����Ĺ��췽�� 
	}
}
