package com.previred.desafio;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.previred.desafio.model.Periodo;

/**
 * Clase utilitaria que contiene metodos para procesar el periodo obtenido desde
 * el servicio REST.
 * 
 * @author romina-taras
 */
public final class ApplicationUtils {

	private static List<String> receivedDates;
	private static List<String> missingDates;

	private ApplicationUtils() {
	}

	public static void processPeriodo(Periodo periodo) {
		findMissingDates(periodo);
		writeFile(periodo);
	}

	private static List<String> findMissingDates(Periodo periodo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Calendar beginCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		try {
			beginCalendar.setTime(dateFormat.parse(periodo.getFechaCreacion()));
			endCalendar.setTime(dateFormat.parse(periodo.getFechaFin()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		endCalendar.add(Calendar.MONTH, 1);

		receivedDates = periodo.getFechas();
		missingDates = new ArrayList<String>();
		// obtener todas las fechas entre el rango de inicio y fin
		while (beginCalendar.before(endCalendar)) {
			String date = dateFormat.format(beginCalendar.getTime()).toUpperCase();
			// obtener fechas faltantes
			if (!receivedDates.contains(date + "-01")) {
				missingDates.add(date);
			}
			beginCalendar.add(Calendar.MONTH, 1);
		}
		return missingDates;
	}

	private static void writeFile(Periodo periodo) {
		final String filePath = "result";
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));

			writer.write("fecha creacion: " + periodo.getFechaCreacion());
			writer.write("\n");
			writer.write("fecha fin: " + periodo.getFechaFin());
			writer.write("\n");
			writer.write("fechas recibidas:");
			for (String date : receivedDates) {
				writer.write(" " + date);
			}
			writer.write("\n");
			writer.write("fechas faltantes:");
			for (String date : missingDates) {
				writer.write(" " + date + "-01");
			}
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
