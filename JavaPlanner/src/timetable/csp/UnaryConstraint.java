package timetable.csp;

public class UnaryConstraint extends Constraint {
	private int start;
	private int end;

	public UnaryConstraint(int start, int end) {

		this.setStart(start);
		this.setEnd(end);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
