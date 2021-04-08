class RMaxQ_SparseTable {
	int[] A;
	int[] log_table;
	int[][] table;
	int N;
	public RMaxQ_SparseTable(int[] A) {
		N = A.length;
		this.A = Arrays.copyOf(A, N);
		log_table = new int[N + 1];
		for(int i = 2;i < N + 1;i++){
			log_table[i] = log_table[i >> 1] + 1;
		}
		table = new int[N][log_table[N] + 1];
		for (int i = 0; i < N; i++) {
			table[i][0] = i;
		}
		for (int k = 1;(1 << k) <= N; k++) {
			for (int i = 0; i + (1 << k)<=  N; i++) {
				int first = table[i][k - 1];//前部分
				int second = table[i + (1 << (k - 1))][k - 1];
				if (A[first] > A[second]) {
					table[i][k] = first;
				} else {
					table[i][k] = second;
				}

			}
		}
	}
	public int query(int s, int t) {//A[s:t]の最大値のindexを返す
		int d = t - s + 1;
		int k = log_table[d];
		if (A[table[s][k]] > A[table[t - (1 << k) + 1][k]]) {
			return table[s][k];
		} else {
			return table[t - (1 << k) + 1][k];
		}
	}
}
