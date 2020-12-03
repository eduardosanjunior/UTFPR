package sistema;

import ambiente.*;
import comuns.*;
import java.util.*;

public class No implements CoordenadasGeo {
	public int[] pos;
	private double custo = 0;
	public int dir;
	
	public No(int[] pos) {
		this.pos = pos;
	}
	
	private double DistanciaEuclidiana() {
		int posx = pos[0] - 2;
		int posy = pos[1] - 6;
		return (int)Math.sqrt(posx*posx + posy*posy);
	}

	public double getCusto() {
		if(custo == 0) {
			return DistanciaEuclidiana();
			//return 0;
		} else if(pos[0] == 2 && pos[1] == 6) {
			return 0;
		}
		else {
			return custo;
		}
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}
}
