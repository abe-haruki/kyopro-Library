class Binomial{
	int MAX = 510000;//ほしいサイズ
	long[] fac=new long[MAX];
	long[] finv=new long[MAX];
	long[] inv=new long[MAX];
	long MOD;
	public Binomial(long mod){
		MOD=mod;
		fac[0] = fac[1] = 1;
	    finv[0] = finv[1] = 1;
	    inv[1] = 1;
	    for (int i = 2; i < MAX; i++){
	        fac[i] = fac[i - 1] * i % MOD;
	        inv[i] = MOD - inv[(int) (MOD%i)] * (MOD / i) % MOD;
	        finv[i] = finv[i - 1] * inv[i] % MOD;
	    }//facがx!、finvがx!の逆元,10^7くらいまでのテーブル(MAXまで)
	}
	long nCk(int n,int k) {
	    if (n < k) return 0;
	    if (n < 0 || k < 0) return 0;
	    return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD;
	}
	long fac(int n) {
		if (n<0) {
			return 0;
		}
		return fac[n];
	}
	long facinv(int n) {
		if (n<0) {
			return 0;
		}
		return finv[n];
	}
}