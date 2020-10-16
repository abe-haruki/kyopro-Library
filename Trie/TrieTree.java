class Trie{
		class Node{
			int[] next=new int[26];//子
			java.util.ArrayList<Integer> accept=new java.util.ArrayList<>();//この頂点を末端とする文字列のID
			int c;
			int common;
			public Node(int c) {
				this.c=c;//nodeの表す文字
				java.util.Arrays.fill(next, -1);
			}
		}
		java.util.ArrayList<Node> nodes=new java.util.ArrayList<>();
		public Trie() {
			nodes.add(new Node(0));
		}
		void insert(String string,int str_id) {
			char[] cs=string.toCharArray();
			int node_id=0;
			for (int i = 0; i < cs.length; i++) {
				nodes.get(node_id).common++;
				int x=(int)cs[i]-'a';//辺に対応する文字
				int nextnode=nodes.get(node_id).next[x];
				if (nextnode==-1) {
					nextnode=nodes.size();
					nodes.get(node_id).next[x]=nodes.size();
					nodes.add(new Node(x));
				}
				node_id=nextnode;
			}
			nodes.get(node_id).common++;
			nodes.get(node_id).accept.add(str_id);
		}
		boolean search(String string) {
			char[] cs=string.toCharArray();
			int node_id=0;
			for (int i = 0; i < cs.length; i++) {
				int x=(int)cs[i]-'a';
				int nextnode=nodes.get(node_id).next[x];
				if (nextnode==-1) {
					return false;
				}
				node_id=nextnode;
			}
			if (nodes.get(node_id).accept.size()>0) {
				return true;
			}
			return false;
		}
	}