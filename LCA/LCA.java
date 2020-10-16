import java.util.*;
class Edge implements Comparable<Edge>{
		int to;
		long v;
		public Edge(int to,long v) {
			this.to=to;
			this.v=v;
		}
		@Override
    	public boolean equals(Object obj) {
    		if(obj instanceof Edge) {
    			Edge other = (Edge) obj;
    			return other.to==this.to && other.v==this.v;
    		}
    		return false;
    	}//同値の定義
    	@Override
    	public int hashCode() {
    		return Objects.hash(this.to,this.v);
    	}
    	@Override
		  public int compareTo( Edge p2 ){
			 if (this.v>p2.v) {
				return 1;
			}
			 else if (this.v<p2.v) {
				return -1;
			}
			 else {
				return 0;
			}
		  }
	} 
class graph{
		ArrayList<Edge>[] list;
		int size;
		long INF=Long.MAX_VALUE/2;
		int[] color;
		@SuppressWarnings("unchecked")
		public graph(int n) {
			size = n;
			list = new ArrayList[n];
			color =new int[n];
			for(int i=0;i<n;i++){
				list[i] = new ArrayList<Edge>();
			}
		}
		void addEdge(int from,int to,long w) {
			list[from].add(new Edge(to,w));
		}
	}
class LCA{
		int[][] par;
		int[] dist;//LCA用
		int K = 1;
		public LCA(int n,int root,graph G) {
	        while ((1 << K) < n) K++;
			par=new int[K][n];
			dist=new int[n];
			Arrays.fill(dist, -1);
			bfs(G, root, 0);
			for (int k = 0; k + 1 < K; k++) {
	            for (int v = 0; v < n; v++) {
	                if (par[k][v] < 0) {
	                    par[k + 1][v] = -1;
	                } else {
	                    par[k + 1][v] = par[k][par[k][v]];
	                }
	            }
	        }
		}
		void bfs(graph G,int v,int d) {
			ArrayDeque<Integer> qArrayDeque=new ArrayDeque<Integer>();
			par[0][v]=-1;
			boolean[] visited=new boolean[G.size];
			qArrayDeque.add(v);
			visited[v]=true;
			while (!qArrayDeque.isEmpty()) {
				int u=qArrayDeque.poll();
				for (Edge nv: G.list[u]){
					if (visited[nv.to]){
						continue;
					}
					visited[nv.to]=true;
					dist[nv.to]=dist[u]+1;
					par[0][nv.to]=u;
					qArrayDeque.add(nv.to);
				}
			}
		}
		int query(int u, int v) {
	        if (dist[u] < dist[v]) {
	        	int swap=u;
	        	u=v;
	        	v=swap;
	        }  // u の方が深いとする
	        int K = par.length;
	        // LCA までの距離を同じにする
	        for (int k = 0; k < K; k++) {
	            if ((((dist[u] - dist[v]) >> k) & 1)==1) {
	                u = par[k][u];
	            }
	        }
	        // 二分探索で LCA を求める
	        if (u == v) return u;
	        for (int k = K - 1; k >= 0; k--) {
	            if (par[k][u] != par[k][v]) {
	                u = par[k][u];
	                v = par[k][v];
	            }
	        }
	        return par[0][u];
	    }//u,vのlcaを返す
	}