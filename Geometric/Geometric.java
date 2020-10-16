class Geometric{
		static final double eps = 0.0000000001;
		double[] project(double x1, double y1, double x2, double y2, double px, double py){
			//(x1,y1)と(x2,y2)を直線lとして、(px,py)からlに下した垂線の足をもとめる。
	        if(y1 == y2) {
	        	double[] ans= {px,y1};
	        	return ans;
	        	}
	        else if(x1 == x2) {
	        	double[] ans= {x1,py};
	        	return ans;
	        }
	        else{
	            double katamuki1 = (y2 - y1)/(x2 - x1);
	            double seppen1 = -katamuki1 * x1 + y1;
	            double katamuki2 = -1.0 / katamuki1;
	            double seppen2 = -katamuki2 * px + py;
	            double ansx = (seppen2 - seppen1)/(katamuki1 - katamuki2);
	            double ansy = ansx * katamuki1 + seppen1;
	            double[] ans= {ansx,ansy};
	            return ans;
	        }
	    }
	    double[] project2(double x1, double y1, double x2, double y2, double px, double py){
	    	//(x1,y1)と(x2,y2)を直線lとして、lに対して(px,py)の対称な点をもとめる。
	        if(y1 == y2) {
	        	double[] ans= {px,(py - 2*(py - y1))};
	        	return ans;
	        }
	        else if(x1 == x2) {
	        	double[] ans= {(px - 2*(px - x1)),py};
	        	return ans;
	        }
	        else{
	            double katamuki1 = (y2 - y1)/(x2 - x1);
	            double seppen1 = -1.0 * katamuki1 * x1 + y1;
	            double katamuki2 = -1.0 / katamuki1;
	            double seppen2 = -1.0 * katamuki2 * px + py;
	            double ansx = (seppen2 - seppen1)/(katamuki1 - katamuki2);
	            double ansy = ansx * katamuki1 + seppen1;
	            double[] ans= {(px - 2*(px - ansx)),(py- 2 * (py - ansy))};
	            return ans;
	        }
	    }
	    public double d2(double x0,double y0,double x1,double y1){
			return (x0-x1)*(x0-x1) + (y0-y1)*(y0-y1);
		}//ユークリッド距離の2乗
		public double d1(double x0,double y0,double x1,double y1){
			return Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
		}//ユークリッド距離
		boolean iscrosssegmentline(double x0,double y0,double x1,double y1,double x2,double y2,double x3,double y3) {
			//	交差するなら１しないなら0を返す。
			double s=0;
			double t=0;
			double s_=0;
			double t_=0;
		    s = (x0 - x1) * (y2 - y0) - (y0 - y1) * (x2 - x0);
		    t = (x0 - x1) * (y3 - y0) - (y0 - y1) * (x3 - x0);
		    if (s * t > 0) return false;
		    s_ = (x2 - x3) * (y0 - y2) - (y2 - y3) * (x0 - x2);
		    t_ = (x2 - x3) * (y1 - y2) - (y2 - y3) * (x1 - x2);
		    if (s_ * t_ > 0) return false;
		    if (s*t==0&&s_*t_==0) {
				if (this.direction(x0, y0, x1, y1, x2, y2)==5||this.direction(x0, y0, x1, y1, x3, y3)==5) {
					return true;
				}
				else if (this.direction(x2, y2, x3, y3, x0, y0)==5||this.direction(x2, y2, x3, y3, x1, y1)==5) {
					return true;
				}
				else {
					return false;
				}
			}
		    return true;
		}
	    int direction(double x0,double y0,double x1,double y1,double x2,double y2){
			//x0 y0を起点としてp1ベクトル（x1-x0,y1-y0）から見てp2ベクトルが、反時計回りなら１、時計回りなら２、反対方向なら３、
	    	//同じ方向かつp2のほうが大きい場合４、以下の場合５．
			if(x1 == x0 && y1 == y0){
				return 4;
			}
			else if(x2 == x0 && y2 == y0){
				return 5;
			}
			
			//外積の第三成分 = |→p1||→p2|sinθ
			double det = (x1 -x0)*(y2-y0) - (x2 -x0)*(y1 - y0);
			
			if(det>eps){
				return 1;
			}
			else if(det<-eps){
				return 2;
			}
			else{	//同一直線状
				if((x1-x0 >0 == x2-x0 <0) && x1-x0 != 0){	//反対側
					return 3;
				}
				else if((y1-y0 >0 == y2-y0 <0) && y1-y0 != 0){	//反対側
					return 3;
				}
				else{
					double d10 = d2(x0,y0,x1,y1);	//p1とp0の距離^2
					double d20 = d2(x0,y0,x2,y2);	//p2とp0の距離^2
					if(d10 < d20){	//p2のほうが遠い
						return 4;
					}
					else{
						return 5;
					}
				}
			}
		}
		double[] getCrossPoint(double x0,double y0,double x1,double y1,double x2,double y2,double x3,double y3) {
			double x;
			double y;
			if(x1 == x0) {
				x = x0;
				y = ((y3 - y2) / (x3 - x2)) * x + (y2 - ((y3 - y2) / (x3 - x2)) * x2);
			} else if(x3 == x2) {
				x = x2;
				y = ((y1 - y0) / (x1 - x0)) * x + (y0 - ((y1 - y0) / (x1 - x0)) * x0);
			} else {
				x = ((y0 - ((y1 - y0) / (x1 - x0)) * x0) - (y2 - ((y3 - y2) / (x3 - x2)) * x2))/(((y3 - y2) / (x3 - x2)) - ((y1 - y0) / (x1 - x0)));
				y = ((y1 - y0) / (x1 - x0)) * x + (y0 - ((y1 - y0) / (x1 - x0)) * x0);
			}
			double[] ans= {x,y};
			return ans;
	    }//二つの線分の交点を返す。AOJのジャッジだと負の方向に誤差があるとなぜかWAになる
	    int paralell_or_vertical(double x0,double y0,double x1,double y1,double x2,double y2,double x3,double y3) {
	    	//平行なら2　垂直なら1　それ以外は０を返す。
	    	double naiseki=(x1-x0)*(x3-x2)+(y1-y0)*(y3-y2);
	    	double d1=Math.sqrt(d2(x0, y0, x1, y1));
	    	double d2=Math.sqrt(d2(x2, y2, x3, y3));
	    	double cos=naiseki/(d1*d2);
	    	
	    	if (cos+eps>=1||cos-eps<=-1) {
				return 2;
			}
	    	else if(cos<eps&&cos>-eps){
				return 1;
			}
	    	else {
				return 0;
			}
	    }
	}