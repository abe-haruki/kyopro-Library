class Pair implements Comparable<Pair>{
    	public int x;
    	public int y;
    	public Pair(int x,int y) {
    		this.x=x;
    		this.y=y;
    	}
    	@Override
    	public boolean equals(Object obj) {
    		if(obj instanceof Pair) {
    			Pair other = (Pair) obj;
    			return other.x==this.x && other.y==this.y;
    		}
    		return false;
    	}//同値の定義
    	@Override
    	public int hashCode() {
    		return java.util.Objects.hash(this.x,this.y);
    	}
    	@Override
    	public int compareTo( Pair p){
    		if (this.x<p.x) {
    			return 1;
    		}
    		else if (this.x>p.x) {
    			return -1;
    		}
    		else {
    			return 0;
    		}
    	}
    }