package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Vaga;



public class VagasDao {
	
	JDBCConnect connect = new JDBCConnect();
	
	public List<Vaga> buscaVagas() throws Exception{
		Connection connection = connect.getConnection();
		String sql = "select * from vagas_status";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		
		List<Vaga> vagas = new ArrayList<Vaga>();
		//Map<Integer, Boolean> vagas = new HashMap<Integer, Boolean>();
		while (rs.next()) {	
			Vaga vaga = new Vaga();
			vaga.setId_arduino(rs.getInt("id_arduino"));
			vaga.setId(rs.getInt("id"));
			vaga.setStatus(rs.getBoolean("status"));
			vagas.add(vaga);
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
		
		return vagas;
	}
	
	public void persisteVagas(Long id, Long disponibilidade) throws Exception{
		Connection connection = connect.getConnection();
		String sql = "select * from vagas_status vg where vg.id_arduino = (?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Vaga vg = new Vaga();
		while (rs.next()) {	
			vg.setId(rs.getInt("id"));
			vg.setId_arduino(rs.getInt("id_arduino"));
			vg.setStatus(rs.getBoolean("status"));
		}
		
		String update = "update vagas_status set status = (?), data_atualizacao = (?) where id = (?)";
		PreparedStatement stmtUpdate = connection.prepareStatement(update);
		stmtUpdate.setLong(3, vg.getId());
		
		boolean status = false;
		if(disponibilidade == 1){
			status = true;
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		stmtUpdate.setTimestamp(2, now);
		stmtUpdate.setBoolean(1, status);
		stmtUpdate.executeUpdate();
		
		boolean conFechada = false;
		
		int timeOut = 0;
		while (!conFechada && timeOut < 10) {
			if (connect.closeConnection(connection)) {
				conFechada = true;
			} else {
				timeOut++;
			}
		}
	}
	public void persisteVagasHistorico(Long id, Long disponibilidade) throws Exception{
		Connection connection = connect.getConnection();
		
		String update = "insert into vagas_status_historico(id_arduino, status, data_atualizacao) values(status = (?), data_atualizacao = (?), id = (?))";
		PreparedStatement stmtUpdate = connection.prepareStatement(update);
		stmtUpdate.setLong(3, id);
		
		boolean status = false;
		if(disponibilidade == 1){
			status = true;
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		stmtUpdate.setTimestamp(2, now);
		stmtUpdate.setBoolean(1, status);
		stmtUpdate.execute();
		
		boolean conFechada = false;
		
		int timeOut = 0;
		while (!conFechada && timeOut < 10) {
			if (connect.closeConnection(connection)) {
				conFechada = true;
			} else {
				timeOut++;
			}
		}
	}

}
