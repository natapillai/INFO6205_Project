package Utils;

import Algorithms.Edge;
import Algorithms.MinimumSpanningTree;

import java.io.*;
import java.util.*;


public class ReadingData {
	
	public static List<Data> FetchFile(String filePath)
	{
		List<Data> values = new ArrayList<Data>();

		File file= new File(filePath);
		try {
			Scanner sc=new Scanner(file);
			sc.nextLine();
			while(sc.hasNextLine())
			{
				String split[]=sc.nextLine().split(",");
				Data data = new Data(split[0],Double.parseDouble(split[1]),Double.parseDouble(split[2]));
				values.add(data);
			}
			
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}

	public static void writeMST(List<Edge> list_edge)
	{
		String filepath = "MST.csv";

		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));

			bw.write("Source" + "," + "Dest." + "," + "Weight");
			bw.newLine();

			for(Edge edge: list_edge)
			{
				bw.write(edge.getFrom() + "," + edge.getTo() + "," + edge.getWeight());
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeodd(Set<Integer> oddvertices)
	{
		//implement method to write Set values to CSV
		String filepath = "oddvertices.csv";

		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
			bw.write("Vertex");
			bw.newLine();

			for(Integer vertex: oddvertices)
			{
				bw.write(vertex.toString() + ",");
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "crimeSample.csv";
		List<Data> da = FetchFile(filePath);
		List<Edge> le;
		Set<Integer> odd_vertices;

		double matrix[][] = new double[da.size()][da.size()];

		for (int i = 0; i < da.size(); i++)
			for (int j = 0; j < da.size(); j++)
				matrix[i][j] = Utils.HaversineDistance.distance(da.get(i), da.get(j));

		le = MinimumSpanningTree.findMST(matrix); //finding MST using Prim's algorithm

		writeMST(le);

		odd_vertices = MinimumSpanningTree.fetchoddVertices(le);

		writeodd(odd_vertices);


	}
}
