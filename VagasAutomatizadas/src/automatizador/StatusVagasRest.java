package automatizador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import persistence.JDBCConnect;
import persistence.VagasDao;
import data.BuscaArduino;
import entity.Vaga;

@Path("/restServiceVagas")
// o @path define a URI do recurso que nesse caso será /helloworld
public class StatusVagasRest {

	JDBCConnect connect = new JDBCConnect();

	@GET
	// utilizando apenas o verbo GET, ou seja, vou apenas ler o recurso
	// @Produces("text/plain")
	@Produces("application/json")
	// define qual tipo MIME é retornado para o cliente
	@Path("/vagasStatus")
	public String vagasStatus() {
		try {

			//BuscaArduino ba = new BuscaArduino();
			if (/*ba.buscaArduino()*/ true) {

				VagasDao vd = new VagasDao();
				List<Vaga> vagas = vd.buscaVagas();

				String retorno = "{\"data\": [";
				int vagasQtd = 0;

				for (Vaga vg : vagas) {
					System.out
							.println(vg.getId_arduino() + "/" + vg.isStatus());
					if (vagasQtd != 0) {
						retorno += ",";
					}
					retorno += "{\"id\" : " + vg.getId_arduino()
							+ ",\"disponibilidade\" : " + vg.isStatus() + "}";

					vagasQtd++;
				}

				retorno += "]}";

				return retorno;
			} else {
				throw new Exception("Busca ao arduino falhou.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@GET
	// utilizando apenas o verbo GET, ou seja, vou apenas ler o recurso
	@Produces("text/plain")
	// define qual tipo MIME é retornado para o cliente
	@Path("/vagasStatusNF")
	public String vagasStatusNF() {
		try {
			Connection connection = connect.getConnection();
			String sql = "select * from vagas_status";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			String retorno = "";
			while (rs.next()) {

				retorno += "id_Arduino: " + rs.getInt("id_arduino")
						+ ", status: " + rs.getBoolean("status")
						+ ", data_Atualização: "
						+ rs.getTimestamp("data_atualizacao") + "\n";
			}
			boolean conFechada = false;
			int timeOut = 0;
			while (!conFechada && timeOut < 10) {
				if (connect.closeConnection(connection)) {
					conFechada = true;
				} else {
					timeOut++;
				}
			}

			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return "Deu ruim!";
		}
	}

	@GET
	// utilizando apenas o verbo GET, ou seja, vou apenas ler o recurso
	// @Produces("text/plain")
	@Produces("application/json")
	// define qual tipo MIME é retornado para o cliente
	@Path("/startaBusca")
	public String startaBusca() {
		BuscaArduino ba = new BuscaArduino();
		ba.buscaArduino();
		return "ok";

	}
}