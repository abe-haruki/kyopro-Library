class Pair2<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>> implements Comparable<Pair2>{
	public T1 x;
	public T2 y;
	public Pair2(T1 x,T2 y) {
		this.x=x;
		this.y=y;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pair2) {
			Pair2<T1,T2> other = (Pair2) obj;
			return other.x.equals(this.x) && other.y.equals(this.y);
		}
		return false;
	}//同値の定義
	@Override
	public int hashCode() {
		return java.util.Objects.hash(this.x,this.y);
	}
	@Override
	public int compareTo(Pair2 p2){
		if (this.x.compareTo((T1) p2.x)==0) {
			return -this.y.compareTo((T2) p2.y);
		}
		return this.x.compareTo((T1) p2.x);
	}
}