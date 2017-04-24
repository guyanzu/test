package timetable.csp;

import java.util.ArrayList;

import org.chocosolver.samples.AbstractProblem;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.constraints.Operator;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;
import org.chocosolver.util.ESat;

import timetable.Task;

public class CSP extends AbstractProblem {

	ArrayList<Task> tasks;

	public IntVar[] x;

	public CSP(ArrayList<Task> input) {
		this.tasks = input;
	}

	@Override
	public void buildModel() {
		x = VariableFactory.enumeratedArray("x", tasks.size(), 1, 24, solver);
		int duration;
		ArrayList<UnaryConstraint> ucList;
		ArrayList<BinaryConstraint> bcList;

		int ucStart;
		int ucEnd;

		String comparator;
		String target;
		int durationC;
//		String unit;
		String modifier;

		Task task;

		for (int i = 0; i < tasks.size(); i++) {

			task = tasks.get(i);

			duration = task.getDuration();

			// UC
			ucList = task.getUnaryConstraints();
			for (UnaryConstraint uc : ucList) {
				ucStart = uc.getStart();
				ucEnd = uc.getEnd();

				int thirdAttribute = ucEnd - duration;
				org.chocosolver.solver.constraints.Constraint c1 = IntConstraintFactory.arithm(x[i], ">=", ucStart);
				org.chocosolver.solver.constraints.Constraint c2 = IntConstraintFactory.arithm(x[i], "<=",
						thirdAttribute);
				solver.post(c1);
				solver.post(c2);

			}

			// BC
			bcList = task.getBinaryConstraints();
			for (BinaryConstraint bc : bcList) {
				comparator = bc.getComparator();
				target = bc.getTarget();
				durationC = bc.getDuration();
//				unit = bc.getUnit();
				modifier = bc.getModifier();

				String comparatorCSP = "";
				String comparator2CSP = "";

				if (comparator.equals(">")) {
					comparatorCSP = "" + Operator.GT;
					comparator2CSP = "" + Operator.MN;
				}
				if (comparator.equals(">=")) {
					comparatorCSP = "" + Operator.GE;
					comparator2CSP = "" + Operator.MN;
				}
				if (comparator.equals("<")) {
					comparatorCSP = "" + Operator.LT;
					comparator2CSP = "" + Operator.PL;
				}
				if (comparator.equals("<=")) {
					comparatorCSP = "" + Operator.MN;
					comparator2CSP = "" + Operator.PL;
				}
				if (comparator.equals("dist")) {
					comparatorCSP = "dist";
				}

				int targetCSP = getID(target);

				String modifierCSP = "";
				if (modifier.equals("min")) {
					modifierCSP = "" + Operator.GE;
				}
				if (modifier.equals("max")) {
					modifierCSP = "" + Operator.LE;
				}
				if (modifier.equals("exactly")) {
					modifierCSP = "" + Operator.EQ;
				}

				if (!comparator.equals("dist")) {
					solver.post(IntConstraintFactory.arithm(x[i], comparatorCSP, x[targetCSP]));
					solver.post(
							IntConstraintFactory.arithm(x[i], comparator2CSP, x[targetCSP], modifierCSP, durationC));

				} else {
					// TODO
				}

			}

		}

	}

	private int getID(String target) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getDescription().equals(target)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void createSolver() {
		solver = new Solver("CSP");
	}

	@Override
	public void configureSearch() {
		solver.set(IntStrategyFactory.random_value(x));
	}

	@Override
	public void solve() {
		solver.findSolution();
	}

	@Override
	public void prettyOut() {

		if (solver.isFeasible() == ESat.TRUE) {
			int solutionsCount = 0;
			do {
				System.out.print("x: ");
				for (int i = 0; i < tasks.size(); i++) {
					System.out.print(x[i].getValue() + " ");
				}
				System.out.println();

				solutionsCount++;

			} while (solver.nextSolution() == Boolean.TRUE);

			System.out.println("It was " + solutionsCount + " solutions.");

		} else {
			System.out.println("No solution.");
		}

	}

}
