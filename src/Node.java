import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Vortex {
	private int value;
	private String name;
	private List<Node> adjList = new ArrayList<Node>();

	public Node(int j) {
		this.value = j;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public List<Node> getNeighboursList() {
		return adjList;
	}
	public void setNeighboursList(List<Node> adjList) {
		this.adjList = adjList;
	}
	public void addNeighbour(Node vor) {
		adjList.add(vor);
	}
	@Override
	public void addNeighbour(Vortex... n) {
		for( Vortex item: n )
			this.addNeighbour((Node)item);
	}
	@Override
	public void deleteNeighbour(Vortex... n) {
		adjList.remove(n);
	}
	@Override
	public List getNeighbours() {
		return adjList;
	}
	
	public String toString(){
		return " " + value;
	}
}
