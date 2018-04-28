
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
	
public class ArtificialNeuralNetwork {
	


		static 
		Random r = new Random();
		
		BufferedReader br=null;
		
		
			double target[][] = {	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
									{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1} };

		double input[][] = {		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
									{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1} };


		double inputHiddenWeights[][] = new double[4][16]; 
		double deltaInputHiddenWeights[][] = new double[4][16];
		
		
		double hiddenOutputWeights[][] = new double[16][4];
		double deltaHiddenOutputWeights[][] = new double[16][4];
		
		double net_h[] = new double[4];
		double out_h[] = new double[4];
		
		double net_o[]= new double [16];
		double out_o[] = new double[16];
		
		double derivative = 0.0;
		
		
		public void WriteHidden1(double hidden_output[]){
		       
		      try{
		          FileWriter fr = new FileWriter("output.txt");
		          BufferedWriter br = new BufferedWriter(fr);
		          PrintWriter out = new PrintWriter(br);
		          
		          for(int i=0; i<hidden_output.length; i++){
		              if(hidden_output[i] != 0)
		                   
		            out.write((int) hidden_output[i]);
		                out.write("\n");       
		          }
		          out.close();
		           
		           
		      }
		       
		      catch(IOException e){
		       System.out.println(e);   
		      }
		  }
			
		public void writeHidden2(double out_h2[]) {
		    try {
		        BufferedWriter bw = new BufferedWriter(new FileWriter("readme.txt"));

		        for(int i=0;i<4;i++)
		        {
		        	double roundOff = Math.round(out_h[i]*100)/100;
		       
		            bw.write(roundOff + "  ");
		            
		            bw.newLine();
		        }
		        bw.flush();
		    } catch (IOException e) {}
		}
		
		
		public void randomFillWeights1()
		{		
			double Low = -0.5;
			double High = 0.5;
			
			//System.out.println("\nWeights 1: \n");
			
			for(int i = 0; i < 4; i++)
			{
				for(int j=0; j<16; j++)
				{
					double random = Low + r.nextDouble() * (High - Low);
					inputHiddenWeights[i][j]  = random;
			        //System.out.println(" "+weight1[i][j]);
				}
			}
		}
		
		
		public void randomFillWeights2()
		{		
			double Low = -0.5;
			double High = 0.5;
			
			
			//System.out.println("\nWeights 2: \n");
			
			for(int i = 0; i < 16; i++)
			{
				for(int j=0; j<4; j++)
				{
					double random = Low + r.nextDouble() * (High - Low);
					hiddenOutputWeights[i][j]  = random;
			        //System.out.println(" "+weight2[i][j]);
				}
			}
		}
		
		
		public void train(int line)
		{
			calculate_net_H(line);
			calculate_net_O(line);
			
			delta_Hidden_Output_Weight(line);
			delta_Input_Hidden_Weight(line);
			
			updateWeights();
			
		}
		
		public void display(int line) throws IOException
		{
		
			calculate_net_H_Display(line);
			
			
		}
		
		public void calculate_net_H_Display(int line) throws IOException
		{
			//BufferedWriter bw = new BufferedWriter(new FileWriter("output"));
			 
			for(int hiddenNeural = 0; hiddenNeural < 4; hiddenNeural++)
			{
				net_h[hiddenNeural]=0;
				for(int inputNeural=0; inputNeural<16; inputNeural++)
				{
					net_h[hiddenNeural] += inputHiddenWeights[hiddenNeural][inputNeural] * input[line][inputNeural];
					
				}
				out_h[hiddenNeural] = 1 / ( 1 + ( Math.exp(-net_h[hiddenNeural]) ) );
				
				WriteHidden1(out_h);
			
				double roundOff = Math.round(out_h[hiddenNeural]*100)/100;
			
				writeHidden2(out_h);
				
				//round for hidden output
				if(out_h[hiddenNeural] < 0.5)
					out_h[hiddenNeural] = 0;
				else if(out_h[hiddenNeural] >= 0.5)
					out_h[hiddenNeural] = 1;
							
							
				System.out.print(hiddenNeural+"--"+out_h[hiddenNeural]);
				System.out.println(" ");
			}
			
	
			System.out.println("");
			
		}
		
		
		
		public void calculate_net_H(int line)
		{
		
			for(int hiddenNeural = 0; hiddenNeural < 4; hiddenNeural++)
			{
				net_h[hiddenNeural]=0;
				
				for(int inputNeural=0; inputNeural<16; inputNeural++)
				{
					net_h[hiddenNeural] += inputHiddenWeights[hiddenNeural][inputNeural] * input[line][inputNeural];
					
				}
				out_h[hiddenNeural]=1 / ( 1 + ( Math.exp(-net_h[hiddenNeural]) ) );
			}
			
		}
		

		
		public void calculate_net_O(int line)
		{
			for(int outputNeural = 0; outputNeural < 16; outputNeural++)
			{
				net_o[outputNeural]=0;
				for(int hiddenNeural = 0; hiddenNeural < 4; hiddenNeural++)
				{
					net_o[outputNeural] += hiddenOutputWeights[outputNeural][hiddenNeural] * out_h[hiddenNeural];
				}
				out_o[outputNeural] = 1 / ( 1 + ( Math.exp(-net_o[outputNeural])  ) );
			}
			
			
		}
		
		
		
		//back propagation until hidden layer
		public void delta_Hidden_Output_Weight(int line)
		{
			
			for(int outputNeural=0; outputNeural<16; outputNeural++)
			{
				for(int hiddenNeural=0; hiddenNeural<4; hiddenNeural++)
				{
					deltaHiddenOutputWeights [outputNeural][hiddenNeural] = ( out_o[outputNeural]- target[line][outputNeural] ) * out_o[outputNeural] * ( 1 - out_o[outputNeural] ) * out_h[hiddenNeural]   ;
				}
			}
			
		}
			

		
		//back propagation until input layer
		public void delta_Input_Hidden_Weight(int line)
		{
				for(int hiddenNeural=0; hiddenNeural<4; hiddenNeural++)
				{
					double derivative=0.0;
					for (int inputNeural=0; inputNeural<16; inputNeural++)
					{
							
						derivative +=  ( out_o[inputNeural]- target[line][inputNeural]) * (out_o[inputNeural]* (1-out_o[inputNeural])) * hiddenOutputWeights[inputNeural][hiddenNeural] ;
						
					}
					for( int y=0; y<16; y++ )
					{
							
						deltaInputHiddenWeights[hiddenNeural][y] = ( derivative *  out_h[hiddenNeural] * ( 1 - out_h[hiddenNeural]) * input[line][y]  );
					}
						
				}
			
		}
		
		
		public void updateWeights()
		{
			//inputHiddenWeights
			
			for(int inputNeural=0; inputNeural<16; inputNeural++)
			{
				for(int hiddenNeural=0; hiddenNeural<4; hiddenNeural++)
				{
					inputHiddenWeights[hiddenNeural][inputNeural] -= 0.5 *  deltaInputHiddenWeights[hiddenNeural][inputNeural];
				}
			}
			
			for(int hiddenNeural=0; hiddenNeural<4; hiddenNeural++)
			{
				for(int outputNeural=0; outputNeural<16; outputNeural++)
				{
					hiddenOutputWeights[outputNeural][hiddenNeural] -= 0.5 *  deltaHiddenOutputWeights[outputNeural][hiddenNeural];
				}
			}
			
		}
		
		
		
	


}
