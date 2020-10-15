import java.util.*;
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
		void Treedfs(int v, int p ) {
			for (Edge nv : list[v]) {
				if (nv.to == p) continue;  
				//処理書く
				Treedfs(nv.to, v);
			}
		}
		boolean nibugraph(int s,int colors) {
			color[s]=colors;
			for (Edge nv: list[s]) {
				if (color[nv.to]==colors) {
					return false;
				}
				if (color[nv.to]==0&&!nibugraph(nv.to, -colors)) {
					return false;
				}
			}
			return true;
		}
		long[] bfs(int s) {
			long[] l=new long[size];
			for (int i = 0; i < l.length; i++) {
				l[i]=-1;
			}
			l[s]=0;
			ArrayDeque<Integer> qArrayDeque=new ArrayDeque<Integer>();
			qArrayDeque.add(s);
			while (!qArrayDeque.isEmpty()) {
				int v=qArrayDeque.poll();
				for (Edge nv: list[v]) {
					if (l[nv.to]==-1) {
						l[nv.to]=l[v]+nv.v;
						qArrayDeque.add(nv.to);
					}
				}
			}
			return l;
		}
		long[] bellman_ford(int sv,int gv) {
			long[] d=new long[size];
			Arrays.fill(d, INF);
			d[sv]=0;
			for (int i = 0; i < d.length; i++) {
				for (int j = 0; j < d.length; j++) {
					if (d[j]==INF) {
						continue;
					}
					for (Edge edge :list[j]) {
						if (d[edge.to]>d[j]+edge.v) {
							d[edge.to]=d[j]+edge.v;
						}
					}
				}
			}
			long ans=d[gv];
			for (int i = 0; i < d.length; i++) {
				for (int j = 0; j < d.length; j++) {
					if (d[j]==INF) {
						continue;
					}
					for (Edge edge :list[j]) {
						if (d[edge.to]>d[j]+edge.v) {
							d[edge.to]=-INF;
							return null; //グラフのどこかに負のループがあれば判定したいとき
						}
					}
				}
			}
			if (ans!=d[gv]) {
				return null;
			}//sv~gv間に負の閉路があれば検出
			return d;
		}
		long[] dijkstra(int s){
	        long[] L = new long[size];
	        for(int i=0;i<size;i++){
	            L[i] = -1;
	        }
	        int[] visited = new int[size];
	        L[s] = 0;
	        PriorityQueue<Edge> Q = new PriorityQueue<Edge>();
	        Q.add(new Edge(s,0L));//Edgeに最短距離候補を持たせる
	        while(!Q.isEmpty()){
	            Edge C = Q.poll();
	            if(visited[C.to]==0){
	                L[C.to] = C.v;
	                visited[C.to] = 1;
	                for(Edge D:list[C.to]){
	                	if (visited[D.to]==1) {
							continue;
						}
	                    Q.add(new Edge(D.to,L[C.to]+D.v));
	                }
	            }
	        }
	        return L;
	    }
	    int[] topological(){
			int[] indegree = new int[size];//入次数
	        for (int i = 0; i < size; i++) {
	        	for (Edge edge: list[i]) {
					indegree[edge.to]++;
				}
	        }
	        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
	        int[] count=new int[size];
	        for (int i = 0; i < size; i++) {
	            if (indegree[i] == 0) {
	                q.addLast(i);
	            }
	        }
	        int cnt = 0;
	        List<Integer> res = new ArrayList<Integer>();
	        while (!q.isEmpty()) {
	            // 接続先の頂点を探索開始
	            int u = q.removeLast();
	            res.add(u);
	            for (Edge edge : list[u]) {
	                indegree[edge.to]--;
	                count[edge.to]=Math.max(count[u]+1,count[edge.to]);
	                if (indegree[edge.to] == 0) {
	                    q.addFirst(edge.to);
	                }
	            }
	            cnt++;
	        }
	        if (cnt<size) {
				return null;
			}//閉路があればその頂点は一度もキューに入らない
	        return count;
	        //countはその頂点までの最大パス長
	        //resにcountの値が小さい順にソートされたものが入っている。
		}
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
	}

