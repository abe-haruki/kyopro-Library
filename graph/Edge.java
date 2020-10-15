class Edge implements Comparable<Edge>{
		int to;
		long v;
		int from;
		public Edge(int to,long v,int from) {
			this.to=to;
			this.v=v;
			this.from=from;
		}
		@Override
    	public boolean equals(Object obj) {
    		if(obj instanceof Edge) {
    			Edge other = (Edge) obj;
    			return other.to==this.to && other.v==this.v&&other.from==this.from;
    		}
    		return false;
    	}//同値の定義
    	@Override
    	public int hashCode() {
    		return java.util.Objects.hash(this.to,this.v,this.from);
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
	} //戻り点もあるやつ(経路復元用)