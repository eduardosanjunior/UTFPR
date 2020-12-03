package beltraoluis.Estrutura.Grafo;
public class NoCardinal<T>{
    protected boolean bsup;
    protected boolean binf;
    protected boolean besq;
    protected boolean bdir;
    protected T dado;
    protected NoCardinal<T> n;
    protected NoCardinal<T> nl;
    protected NoCardinal<T> l;
    protected NoCardinal<T> sl;
    protected NoCardinal<T> s;
    protected NoCardinal<T> so;
    protected NoCardinal<T> o;
    protected NoCardinal<T> no;     

    // construtores
	public NoCardinal(T dado){
		this.bsup = false;
		this.binf = false;
		this.besq = false;
		this.bdir = false;
		this.dado = dado;
		this.n = null;
		this.nl = null;
		this.l = null;
		this.sl = null;
		this.s = null;
		this.so = null;
		this.o = null;
		this.no = null;
	}

	//getters e setters
	public boolean isBsup() {
		return bsup;
	}
	
	public void setBsup(boolean bsup) {
		this.bsup = bsup;
	}
	
	public boolean isBinf() {
		return binf;
	}
	
	public void setBinf(boolean binf) {
		this.binf = binf;
	}
	
	public boolean isBesq() {
		return besq;
	}
	
	public void setBesq(boolean besq) {
		this.besq = besq;
	}
	
	public boolean isBdir() {
		return bdir;
	}
	
	public void setBdir(boolean bdir) {
		this.bdir = bdir;
	}
	
	public T getDado() {
		return dado;
	}
	
	public void setDado(T dado) {
		this.dado = dado;
	}
	
	public NoCardinal<T> getN() {
		return n;
	}
	
	public void setN(NoCardinal<T> n) {
		this.n = n;
	}
	
	public NoCardinal<T> getNl() {
		return nl;
	}
	
	public void setNl(NoCardinal<T> nl) {
		this.nl = nl;
	}
	
	public NoCardinal<T> getL() {
		return l;
	}
	
	public void setL(NoCardinal<T> l) {
		this.l = l;
	}
	
	public NoCardinal<T> getSl() {
		return sl;
	}
	
	public void setSl(NoCardinal<T> sl) {
		this.sl = sl;
	}
	
	public NoCardinal<T> getS() {
		return s;
	}
	
	public void setS(NoCardinal<T> s) {
		this.s = s;
	}
	
	public NoCardinal<T> getSo() {
		return so;
	}
	
	public void setSo(NoCardinal<T> so) {
		this.so = so;
	}
	
	public NoCardinal<T> getO() {
		return o;
	}
	
	public void setO(NoCardinal<T> o) {
		this.o = o;
	}
	
	public NoCardinal<T> getNo() {
		return no;
	}
	
	public void setNo(NoCardinal<T> no) {
		this.no = no;
	}
}