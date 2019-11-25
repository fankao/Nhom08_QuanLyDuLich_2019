package utils.address;

import java.util.List;

public class District {
	private int id;
	private String name;
	private List<Ward> wards;
	private List<Street> streets;
	private List<Project> projects;

	public District(int id, String name, List<Ward> wards, List<Street> streets, List<Project> projects) {
		super();
		this.id = id;
		this.name = name;
		this.wards = wards;
		this.streets = streets;
		this.projects = projects;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ward> getWards() {
		return wards;
	}

	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
