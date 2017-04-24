package timetable;

import java.util.*;

import timetable.csp.BinaryConstraint;
import timetable.csp.UnaryConstraint;

public class Task {

	private String description;
	private Date startDate;
	private int duration;
	private ArrayList<UnaryConstraint> unaryConstraints;
	private ArrayList<BinaryConstraint> binaryConstraints;

	public Task(String description, Date startDate, int duration, ArrayList<UnaryConstraint> unaryConstraints,
			ArrayList<BinaryConstraint> binaryConstraints) {

		this.description = description;
		this.startDate = startDate;
		this.duration = duration;
		this.unaryConstraints = unaryConstraints;
		this.binaryConstraints = binaryConstraints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<UnaryConstraint> getUnaryConstraints() {
		return unaryConstraints;
	}

	public void setUnaryConstraints(ArrayList<UnaryConstraint> unaryConstraints) {
		this.unaryConstraints = unaryConstraints;
	}

	public ArrayList<BinaryConstraint> getBinaryConstraints() {
		return binaryConstraints;
	}

	public void setBinaryConstraints(ArrayList<BinaryConstraint> binaryConstraints) {
		this.binaryConstraints = binaryConstraints;
	}

	public void removeUC(Integer id) {
		ArrayList<UnaryConstraint> newList = new ArrayList<UnaryConstraint>();
		for (int i = 0; i < unaryConstraints.size(); i++) {
			if (i != id) {
				newList.add(unaryConstraints.get(i));
			}
		}
		unaryConstraints = newList;
	}
	
	public void removeBC(Integer id) {
		ArrayList<BinaryConstraint> newList = new ArrayList<BinaryConstraint>();
		for (int i = 0; i < binaryConstraints.size(); i++) {
			if (i != id) {
				newList.add(binaryConstraints.get(i));
			}
		}
		binaryConstraints = newList;
	}

}
