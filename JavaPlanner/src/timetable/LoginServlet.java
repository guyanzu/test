package timetable;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timetable.csp.BinaryConstraint;
import timetable.csp.CSP;
import timetable.csp.UnaryConstraint;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ArrayList<Task> tasks = new ArrayList<Task>();

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String description = request.getParameter("description");
			String startDateTemp = request.getParameter("startHour") == null ? "0"
					: (String) request.getParameter("startHour");
			int duration = request.getParameter("duration") == null ? 0
					: Integer.valueOf((String) request.getParameter("duration"));

			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			String temp1 = "20-12-2016 " + startDateTemp + ":0:0";
			Date startDate = sdf.parse(temp1);

			ArrayList<UnaryConstraint> uc = new ArrayList<UnaryConstraint>();
			ArrayList<BinaryConstraint> bc = new ArrayList<BinaryConstraint>();
			if (!(description == null)) {
				Task task = new Task(description, startDate, duration, uc, bc);

				tasks.add(task);
			}
			request.setAttribute("tasks", tasks);

			//

			String id = request.getParameter("tasks");
			String idUC = request.getParameter("ucToDelete");
			String idBC = request.getParameter("bcToDelete");
			String edit = request.getParameter("edit");
			String delete = request.getParameter("delete");
			String deleteUC = request.getParameter("deleteUC");
			String deleteBC = request.getParameter("deleteBC");

			if (id != null && delete != null) {

				int temp = Integer.valueOf(id);
				tasks.remove(temp);
				request.setAttribute("tasks", tasks);
			}

			//

			if (edit != null) {
				request.setAttribute("tasks", tasks);
				request.setAttribute("edit", "edit");
				request.setAttribute("target", id);
			}
			//

			if (deleteUC != null) {
				String[] parts = idUC.split(";");
				int idTaskUC = Integer.valueOf(parts[0]);
				int tempIDuc = Integer.valueOf(parts[1]);
				tasks.get(Integer.valueOf(idTaskUC)).removeUC(Integer.valueOf(tempIDuc));
			}

			if (deleteBC != null) {
				String[] parts = idBC.split(";");
				int idTaskBC = Integer.valueOf(parts[0]);
				int tempIDbc = Integer.valueOf(parts[1]);
				tasks.get(Integer.valueOf(idTaskBC)).removeBC(Integer.valueOf(tempIDbc));
			}

			if (request.getParameter("modifiedTaskUC") != null) {
				String modifiedTaskUC = request.getParameter("modifiedTaskUC");

				int ModifiedID = Integer.valueOf(modifiedTaskUC);

				if (request.getParameter("timeframeA") != null && request.getParameter("timeframeB") != null) {
					String timeframeA = request.getParameter("timeframeA");
					String timeframeB = request.getParameter("timeframeB");

					ArrayList<UnaryConstraint> existingUC = tasks.get(ModifiedID).getUnaryConstraints();
					existingUC.add(new UnaryConstraint(Integer.valueOf(timeframeA), Integer.valueOf(timeframeB)));
					tasks.get(ModifiedID).setUnaryConstraints(existingUC);

				}

				//

				CSP csp = new CSP(tasks);
				csp.execute();

				for (int i = 0; i < csp.x.length; i++) {
					int value = csp.x[i].getValue();
					Date temp = tasks.get(i).getStartDate();
					temp.setHours(value);
					tasks.get(i).setStartDate(temp);
				}

				request.setAttribute("tasks", tasks);
				request.setAttribute("edit", "" + ModifiedID);
				request.setAttribute("target", "" + ModifiedID);
			}
			///

			if (request.getParameter("modifiedTaskBC") != null) {
				String modifiedTaskBC = request.getParameter("modifiedTaskBC");
				int ModifiedID = Integer.valueOf(modifiedTaskBC);

				if (request.getParameter("comparator") != null && request.getParameter("targetC") != null) {
					String comparator = request.getParameter("comparator");
					String target = request.getParameter("targetC");
					int durationC = (request.getParameter("duration") != null)
							? Integer.valueOf((String) request.getParameter("duration")) : null;
					String unit = (request.getParameter("unit") != null) ? request.getParameter("unit") : null;
					String modifier = (request.getParameter("modifier") != null) ? request.getParameter("modifier")
							: null;

					ArrayList<BinaryConstraint> existingBC = tasks.get(ModifiedID).getBinaryConstraints();
					existingBC.add(new BinaryConstraint(comparator, target, durationC, unit, modifier));
					tasks.get(ModifiedID).setBinaryConstraints(existingBC);
				}

				CSP csp = new CSP(tasks);
				csp.execute();

				for (int i = 0; i < csp.x.length; i++) {
					int value = csp.x[i].getValue();
					Date temp = tasks.get(i).getStartDate();
					temp.setHours(value);
					tasks.get(i).setStartDate(temp);
				}

				request.setAttribute("tasks", tasks);
				request.setAttribute("edit", "" + ModifiedID);
				request.setAttribute("target", "" + ModifiedID);

			}

			JDBC jdbc = new JDBC();
			request.setAttribute("contacts", jdbc.contacts);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/javacalendar.jsp").forward(request, response);

	}

}