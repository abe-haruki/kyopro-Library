class Pair2 implements Comparable<Pair2>{
    	public int x;
    	public int y;
    	public int z;
    	public Pair2(int x,int y,int z) {
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
    		if (this.z<p2.z) {
    			return 1;
    		}
    		else if (this.z>p2.z) {
    			return -1;
    		}
    		else {
    			return 0;
    		}
    	}
    }