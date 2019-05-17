package com.previred.desafio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Periodo {

	private Long id = null;
	private String fechaCreacion = null;
	private String fechaFin = null;
	private List<String> fechas = null;

	public Periodo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<String> getFechas() {
		return fechas;
	}

	public void setFechas(List<String> fechas) {
		this.fechas = fechas;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Periodo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
		sb.append("    fechaFin: ").append(toIndentedString(fechaFin)).append("\n");
		sb.append("    fechas: ").append(toIndentedString(fechas)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
