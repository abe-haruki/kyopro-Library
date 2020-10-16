class MinimumSpanningTree{
		public java.util.PriorityQueue<Pair2> priorityQueue;
		public int V;
		public UnionFindTree unionFindTree;
		public MinimumSpanningTree(int V) {
			priorityQueue=new java.util.PriorityQueue<>();
			this.V=V;
			unionFindTree=new UnionFindTree(V); 
		}
		public void addEdge(int a,int b,long w) {
			priorityQueue.offer(new Pair2(a, b, w));
		}
		public long getMiniWeight() {
			long sum=0;
			while (!priorityQueue.isEmpty()) {
				Pair2 pair2=priorityQueue.poll();
				if (unionFindTree.same(pair2.x, pair2.y)) {
					continue;
				}
				sum+=pair2.z;
				unionFindTree.union(pair2.x, pair2.y, 0);
			}
			return sum;
		}
		public class UnionFindTree{
	    	int[] par;
	    	int[] rank;
	    	int[] size;
	    	int[] diff_weight;
	    	int arraysize;
	    	public UnionFindTree(int n) {
	    		this.par=new int[n];
	    		this.rank=new int[n];
	    		this.size=new int[n];
	    		this.diff_weight=new int[n];
	    		arraysize=n;
	    		for (int i = 0; i < arraysize; i++) {
					set(i);
				}
	    	}
	    	public void set(int i) {
	    		par[i]=i;
	    		rank[i]=0;
	    		size[i]=1;
	    		diff_weight[i]=0;
	    	}
	    	public void union(int x,int y,int w) {
	    		w += weight(x); 
	    		w -= weight(y);
	    		int rootx=find(x);
	    		int rooty=find(y);
	    		if (rootx==rooty) {
					return;
				}
	    		if (rank[rootx]>rank[rooty]) {
					par[rooty]=rootx;
					diff_weight[rooty] = w;
					size[rootx]+=size[rooty];
				}
	    		else if (rank[rootx]<rank[rooty]) {
					par[rootx]=rooty;
					w=-w;
					diff_weight[rootx] = w;
					size[rooty]+=size[rootx];
				}
	    		else {
	    			par[rooty]=rootx;
	    			diff_weight[rooty] = w;
	    			rank[rootx]++;
					size[rootx]+=size[rooty];
				}
	    	}
	    	public int find(int x) {
	    		if(par[x] == x) return x;
	    		int r = find(par[x]);
	    		diff_weight[x] += diff_weight[par[x]];
	    		return par[x] = r;
	    	}
	    	public int weight(int x) {
	    		find(x);
	    		return diff_weight[x];
	    	}
	    	public int size(int i) {
	            return size[find(i)];
	        }
	    	public int diff(int x, int y) {
	    		return weight(y) - weight(x);
	    	}
	    	public boolean same(int x, int y) {
	            return find(x) == find(y);
	        }
	    }
		public class Pair2 implements Comparable<Pair2>{
	    	public int x;
	    	public int y;
	    	public long z;
	    	public Pair2(int x,int y,long z) {
	    		this.x=x;
	    		this.y=y;
	    		this.z=z;
	    	}
	    	@Override
	    	public boolean equals(Object obj) {
	    		if(obj instanceof Pair2) {
	    			Pair2 other = (Pair2) obj;
	    			return other.x==this.x && other.y==this.y&& other.z==this.z;
	    		}
	    		return false;
	    	}//同値の定義
	    	@Override
	    	public int hashCode() {
	    		return java.util.Objects.hash(this.x,this.y,this.z);
	    	}//これ書かないと正しく動作しない（要　勉強）
	    	@Override
	    	public int compareTo( Pair2 p2 ){
	    		if (this.z>p2.z) {
	    			return 1;
	    		}
	    		else if (this.z<p2.z) {
	    			return -1;
	    		}
	    		else {
	    			return 0;
	    		}
	    	}
	    }
	}