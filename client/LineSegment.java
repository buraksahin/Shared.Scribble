
import java.awt.Graphics;

class LineSegment {
	int x1,y1,x2,y2;
	LineSegment next;
		
	LineSegment (int x1, int y1, int x2, int y2, LineSegment next) {
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.next=next;
	}
	void drawLine(Graphics g) {
		g.drawLine(x1,y1,x2,y2);
	}
	@Override
	public String toString() {
		return "xYz-" + x1 + "-" + y1 + "-" + x2 + "-"	+ y2;
	}	
}
	
	