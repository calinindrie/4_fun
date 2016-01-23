import java.util.ArrayList;
import java.util.List;

/* we should place something here */
public class Graph {
	private List<Vortex>myGraph = new ArrayList<Vortex>();
	private List<Vortex>myRestrictions=new ArrayList<Vortex>();
	
	public void createNodes(int i){
		for( int j = 0; j<i ; j++ ){
			myGraph.add(new Node(j));
		}
	}
	
	private Vortex getVortex( int index ){
		return (Vortex)myGraph.get(index);
	}
	
	private Vortex getRestriction( int index ){
		return (Vortex)myRestrictions.get(index);
	}	
	
	public void showGraph(){
		for( Vortex item: getMyGraph() )
			System.out.println("Node: " + item);
	}
	
	public void showConnections(){
		for( Vortex item: getMyGraph() ){
			System.out.println("\nNode: " + item);
			List<Node> list = (List<Node>)item.getNeighbours();
			if( list != null )
				for( Vortex neigh: list){
					System.out.print( "\t Neighbour: " + neigh );
				}
		}
	}
		
	public void connect(){
		getVortex(0).addNeighbour(getVortex(1),getVortex(12));
		getVortex(1).addNeighbour(getVortex(2),getVortex(11));
		getVortex(2).addNeighbour(getVortex(3),getVortex(4),getVortex(10));
		getVortex(3).addNeighbour(getVortex(4));
		getVortex(4).addNeighbour(getVortex(5),getVortex(9));
		getVortex(5).addNeighbour(getVortex(6));
		getVortex(6).addNeighbour(getVortex(7));
		getVortex(7).addNeighbour(getVortex(19));
		getVortex(8).addNeighbour(getVortex(7),getVortex(18));
		getVortex(9).addNeighbour(getVortex(6),getVortex(8));
		getVortex(10).addNeighbour(getVortex(8),getVortex(9),getVortex(17));
		getVortex(11).addNeighbour(getVortex(10),getVortex(15));
		getVortex(12).addNeighbour(getVortex(11),getVortex(13));
		getVortex(13).addNeighbour(getVortex(11),getVortex(14));
		getVortex(14).addNeighbour(getVortex(15),getVortex(16));
		getVortex(15).addNeighbour(getVortex(16),getVortex(17));
		getVortex(16).addNeighbour(getVortex(17));
		getVortex(17).addNeighbour(getVortex(18));
		getVortex(18).addNeighbour(getVortex(19));
	}
	
	public void addRestrictions(){
		myRestrictions.add(new Restrictions("Monday", getVortex(11)));
		myRestrictions.add(new Restrictions("Tuesday", getVortex(17), getVortex(3)));
		myRestrictions.add(new Restrictions("Wednesday", getVortex(8), getVortex(3)));
		myRestrictions.add(new Restrictions("Thursday", getVortex(6), getVortex(3)));
		myRestrictions.add(new Restrictions("Friday", getVortex(5), getVortex(3), getVortex(10)));
	}

	public void showRestrictions(){
		for( Vortex item: getMyRestrictions() ){
			System.out.println( ((Restrictions)item).getName() );
			for( Vortex item2: (List<Vortex>)((Restrictions)item).getNeighbours() ){
				System.out.print( (Node)item2 + " " );
				System.out.println(" end");
			}
		}
	}
	
	public List<Vortex> getMyGraph() {
		return myGraph;
	}

	public void setMyGraph(List<Vortex> myGraph) {
		this.myGraph = myGraph;
	}

	public List<Vortex> getMyRestrictions() {
		return myRestrictions;
	}

	public void setMyRestrictions(List<Vortex> myRestrictions) {
		this.myRestrictions = myRestrictions;
	}

	public void dfs(Vortex startNode, Vortex endNode, Vortex restr, List <Vortex> path ){
		List<Vortex> neighbour = (List<Vortex>)(startNode.getNeighbours());
		List<Vortex> restrList = (List<Vortex>)(restr.getNeighbours());
		
		if( path.isEmpty() )
			path.add(startNode);
		
		/* should the current node be inside the restricted list, then exit from here */
		if( restrList.contains(startNode) ){
			return;
		}
		
		/* reached the destination */
		if( startNode == endNode ){
			if( !path.isEmpty() ){
				System.out.print(((Restrictions)(restr)).getName() + " : ");
				for( Vortex item: path )
					System.out.print( item + " -> " );
				System.out.println( "end" );
			}
			((Restrictions)restr).incrCount();
			return;
			
		}

		/* going deeper in the tree */
		if( !neighbour.isEmpty())
			for( Vortex item: neighbour ){
				path.add(item);
				dfs( item, endNode, restr, path );
				if( !path.isEmpty())
					path.remove(path.size()-1);
			}

		
		/* we reached a leaf and since we are here, it is not useful to us */
		if( neighbour.isEmpty() )
			return;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph();
		g.createNodes(20);
		g.connect();
		g.showGraph();
		g.addRestrictions();
		for( Vortex restr: g.getMyRestrictions() ){
			g.dfs(g.getVortex(0), g.getVortex(19), restr, new ArrayList<Vortex>());
			System.out.println(((Restrictions)restr).getName()+" : "+((Restrictions)restr).getCount());
		}
	}

}
