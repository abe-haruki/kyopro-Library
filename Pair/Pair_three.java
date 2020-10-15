class Pair2<T1 extends Comparable<? super T1>,T2 extends Comparable<? super T2>,T3 extends Comparable<? super T3>> implements Comparable<Pair2>{
	public T1 x;
	public T2 y;
	public T3 z;
	public Pair2(T1 x,T2 y,T3 z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pair2) {
			Pair2<T1,T2,T3> other = (Pair2) obj;
			return other.x.equals(this.x) && other.y.equals(this.y) && other.z.equals(this.z);
		}
		return false;
	}//同値の定義
	@Override
	public int hashCode() {
		return java.util.Objects.hash(this.x,this.y,this.z);
	}
	@Override
	public int compareTo(Pair2 p2){
		return this.x.compareTo((T1) p2.x);
	}
}