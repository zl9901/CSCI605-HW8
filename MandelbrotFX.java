import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MandelbrotFX extends Application {
    
    WritableImage mandelBrotSetImage;
    final static int IMG_WIDTH 	= 800;
    final static int IMG_HEIGHT = 800;
    long milliSeconds;
    
    public void init() {
        milliSeconds = System.currentTimeMillis();
    }
    
    public void end(String s) {
    	System.err.println(s + ":       " + ( System.currentTimeMillis() - milliSeconds) + "ms" );
    	System.err.println(" # of cores" +   ":       " +Runtime.getRuntime().availableProcessors() );
    }
    
    public void start(Stage theStage) {
   
        	 MandelbrotSet aMandelbrotSet = new MandelbrotSet(IMG_WIDTH,IMG_HEIGHT,0);
    	
        	 
        	 init();
             mandelBrotSetImage = MandelbrotSet.mandelBrotSetImage;
             end("Multiple Thread MandelbrotSet Test");

             ImageView aImage = new ImageView();
             aImage.setImage(mandelBrotSetImage);
             
             StackPane root = new StackPane();
             root.getChildren().add(aImage);
             
             Scene scene = new Scene(root, IMG_WIDTH, IMG_HEIGHT);
             
             theStage.setTitle("Mandelbrot Set");
             theStage.setResizable(false);
             theStage.setScene(scene);
             theStage.show();
    	}


    public static void main(String[] args) {
        	launch(args); 
    }
}


class MandelbrotSet extends Thread {
    
    private static final int    MAX_COLORS 	= 256;
    private static final double BOUNDERY = 1000;
    private static int    width = 800;
    private static int    height = 800;
    static WritableImage mandelBrotSetImage= new WritableImage(width, height);
    static PixelWriter aPixelWriter = mandelBrotSetImage.getPixelWriter();
    private static final Color[] colors = new Color[MAX_COLORS];
    private static double minR  = -2.4;
    private static double maxR  = 0.9;
    private static double minI  = -1.3;
    private static double maxI  = 1.28;
    private static MandelbrotSet[] allThreads;
    private  int index;
    


    
    static {
        for (int index = 0; index < colors.length; index++) {
            colors[index] = Color.RED.interpolate(Color.BLUE, (( 1.0 / colors.length) * index) );
        }
    }

    public MandelbrotSet() {//MandelbrotSet类无参构造方法
    }
    

    public MandelbrotSet(int width,int height,int index) {//MandelbrotSet带参构造方法
    	this.width = width;
    	this.height = height;
    	this.index=index;
    
//    	mandelBrotSetImage = 
//    		mandelBrotSetImage = new WritableImage(width, height);
//    		aPixelWriter = mandelBrotSetImage.getPixelWriter();
    		
    		if ( allThreads == null ) {
    			allThreads = new MandelbrotSet[8];
    			for(int i=0;i<8;i++) {
    				
    				allThreads[i]=new MandelbrotSet(width, height,i);
    				allThreads[i].start();
    			}
    		}
    }
    
    
    private Color getColor(int count) {
	    return count >= colors.length ?  Color.BLACK : colors[count];
    }
    
    private int calc(double re, double img ) {
    	int    counter = 0;
    	double length;
    	double aComplexNumberRe = 0;
    	double aComplexNumberImg = 0;
    	double real = 0;
    	double imaginary = 0;
        do {
        	real= aComplexNumberRe * aComplexNumberRe -aComplexNumberImg * aComplexNumberImg;	 
        	imaginary  = aComplexNumberRe *  aComplexNumberImg + aComplexNumberImg *  aComplexNumberRe;
        	aComplexNumberRe   = real;
        	aComplexNumberImg  = imaginary;
        	aComplexNumberRe   += re;
        	aComplexNumberImg  += img;
        	length = aComplexNumberImg * aComplexNumberImg +aComplexNumberRe * aComplexNumberRe;     
            counter++;
        	}while (counter < MAX_COLORS && ( length < BOUNDERY ) );
        			return counter;
    }
    
    public Color determineColor(int x, int y)	{
    	double re = (minR * (width - x) + x * maxR) / width;
    	double img = (minI * (height - y) + y * maxI) / height;
    	return getColor(calc(re, img));
    }
    

    
    public void createImage()	{

        for ( int x=this.index*100 ; x < this.index*100+100; x++) {
        	for (int y=0 ; y < height; y++) {
        
        		aPixelWriter.setColor(x, y, determineColor(x, y));
        		 
        	}
        }
//        	return mandelBrotSetImage;
    }


	@Override
	public void run( ) {
		// TODO Auto-generated method stub
		createImage();
	}
}

 