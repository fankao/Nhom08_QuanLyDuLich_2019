package utils.address;

public class Ward {
	private int id;
	private String name;
	private String prefix;

	public Ward(int id, String name, String prefix) {
		super();
		this.id = id;
		this.name = name;
		this.prefix = prefix;
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String toString() {
		return this.name;
		
	}
}
