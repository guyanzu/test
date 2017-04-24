package timetable.csp;

public class BinaryConstraint extends Constraint {

	private String comparator;
	private String target;
	private int duration;
	private String unit;
	private String modifier;
	
	public BinaryConstraint(String comparator, String target, int duration, String unit, String modifier) {
		
		this.setComparator(comparator);
		this.target = target;
		this.setDuration(duration);
		this.setUnit(unit);
		this.modifier = modifier;
	}
	
	public String getComparator() {
		return comparator;
	}


	public void setComparator(String comparator) {
		this.comparator = comparator;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getModifier() {
		return modifier;
	}


	public void setModifier(String modifier) {
		this.modifier = modifier;
	}




}
