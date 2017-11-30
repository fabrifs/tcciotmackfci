package entity;

public class Vaga {
	
	private int id;
	private int id_arduino;
	private boolean status;
	
	public Vaga() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_arduino() {
		return id_arduino;
	}

	public void setId_arduino(int id_arduino) {
		this.id_arduino = id_arduino;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Vaga(int id, int id_arduino, boolean status) {
		super();
		this.id = id;
		this.id_arduino = id_arduino;
		this.status = status;
	}
	

}
