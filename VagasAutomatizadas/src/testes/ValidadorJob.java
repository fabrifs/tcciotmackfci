package testes;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import data.BuscaArduino;

public class ValidadorJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		BuscaArduino ba = new BuscaArduino();
		ba.buscaArduino();
		System.out.println("Buscando Arduino at: " + new Date());

	}

}
