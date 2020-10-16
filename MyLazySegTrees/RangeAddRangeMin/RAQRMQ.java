class LazysegTreemin{
		int size;
		long[] dat;
		long[] lazy;
		long INF=Long.MAX_VALUE/2;
		public LazysegTreemin(int n) {
			int n_=1;
			while (n_<n) {
				n_*=2;
			}
			size=n_;
			dat=new long[2*n_-1];
			lazy=new long[2*n_-1];
		}
		void propagate(int k,int l,int r) {
			if (lazy[k]!=0) {
				dat[k] += lazy[k];
				if(r - l > 1) {
		            lazy[2*k+1] += lazy[k];
		            lazy[2*k+2] += lazy[k];
		        }
		        lazy[k] = 0;
			}
		}
		void add(int a,int b,long x,int k,int l,int r) {//a<=x<b加算　add(...,0,0,seg.size)で呼ぶ
			this.propagate(k,l,r);
			if (a<=l&&r<=b) {
				lazy[k]+=x;
				this.propagate(k,l,r);
			}
			else if (l<b&&a<r) {//交わっている場合
				this.add(a, b, x, k*2+1,l,(l+r)/2);
				this.add(a, b, x, k*2+2, (l+r)/2, r);
				dat[k] = Math.min(dat[2*k+1],dat[2*k+2]);
			}
		}
		void apply(int a,int b,long x) {
			this.add(a, b, x, 0, 0, size);
		}
		long getmin(int a,int b,int k,int l,int r) {//kが節点番号,l rがその節点番号の範囲
			if (r<=a||b<=l) {
				return INF;
			}
			this.propagate(k, l, r);
			if (a<=l&&r<=b) {
				return dat[k];
			}
			else {
				long vl=this.getmin(a, b, k*2+1,l,(l+r)/2);
				long vr=this.getmin(a, b, k*2+2, (l+r)/2, r);
				return (Math.min(vl, vr));
			}
		}
		long prod(int a,int b) {
			return getmin(a, b, 0, 0, size);
		}
	}