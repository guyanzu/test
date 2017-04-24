package timetable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;

public class EventsManager extends DHXEventsManager {

	public EventsManager(HttpServletRequest request) {
		super(request);
	}

	public Iterable<DHXEv> getEvents() {

		ArrayList<DHXEv> events = new ArrayList<DHXEv>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/users/julien/desktop/data.txt"));

			String line = br.readLine();

			int id = 0;

			while (line != null) {
				String[] result = line.split(";");

				DHXEvent ev = new DHXEvent();
				ev.setId(id);

				String description = result[0];
				int startHour = Integer.valueOf(result[1]);
				int duration = Integer.valueOf(result[2]);
				int endHour = startHour + duration;

				SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
				String temp1 = "20-12-2016 " + startHour + ":0:0";
				Date startDate = sdf.parse(temp1);

				String temp2 = "20-12-2016 " + endHour + ":0:0";
				Date endDate = sdf.parse(temp2);

				ev.setText(description);
				ev.setStart_date(startDate);
				ev.setEnd_date(endDate);

				events.add(ev);
				id++;
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return events;
	}
}
