package utils.address;

import java.util.List;

public class Province {
	private int id;
	private String code;
	private String name;
	private List<District> districts;

	public Province(int id, String code, String name, List<District> districts) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.districts = districts;
	}

	public Province() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
