import java.io.IOException;
	
public class Main {


		
		public static void main(String[] args) throws Exception 
		{
			
		
			ArtificialNeuralNetwork f = new ArtificialNeuralNetwork();
			
			f.randomFillWeights1();
			f.randomFillWeights2();
			
			for(int i=0; i<10000; i++)
			{
		
				for(int line=0; line<16; line++)
				{
					f.train(line);
					//f.display(line);
				}
				
			}
			
			for(int line=0; line<16; line++)
			{
				f.display(line);
			}
			
			
		}
	






}
